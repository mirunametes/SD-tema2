package ro.tuc.ds2020.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.json.simple.JSONObject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.parser.JSONParser;
import ro.tuc.ds2020.dtos.ConsumtionDTO;
import ro.tuc.ds2020.dtos.SmartDeviceDTO;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class ConsumerRabbitMq {

    private final SimpMessagingTemplate messageTemplate;

    static final String QUEUE_NAME = "queue";
    private final ConsumtionService consumtionService;
    private final SmartDeviceService smartDeviceService;

    Integer count = 0;
    Double previous_quantity = 0.0;
    long previous_timestamp;

    public ConsumerRabbitMq(ConsumtionService consumtionService, SmartDeviceService smartDeviceService, SimpMessagingTemplate template){
        this.messageTemplate = template;
        this.consumtionService = consumtionService;
        this.smartDeviceService = smartDeviceService;
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void run(String incomingMessage) throws Exception {
        JSONParser jsonParser = new JSONParser();
        try{
            JSONObject jsonObject = (JSONObject) jsonParser.parse(incomingMessage);
            Double quantity = Double.parseDouble(jsonObject.get("measurementValue").toString());
            int deviceId = Integer.parseInt(jsonObject.get("sensorId").toString());
            String date_string = jsonObject.get("timestamp").toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(date_string, formatter);
            long timestamp = Timestamp.valueOf(dateTime).getTime();

            SmartDeviceDTO deviceDTO = smartDeviceService.findSmartDeviceById(deviceId);
            count++;
            if(count > 1 ){
                Double peakComputation = (quantity - previous_quantity)/((timestamp - previous_timestamp)/1000);
                if(peakComputation > deviceDTO.getMaxEnergyConsumtion()){
                    sendPeakNotification("Peak value was detected!");
                }
                else{
                    String str = jsonObject.get("timestamp").toString();
                    DateTimeFormatter formatter_new = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime_new = LocalDateTime.parse(str, formatter_new);
                    ConsumtionDTO measurementDTO = new ConsumtionDTO(dateTime_new, (float)(quantity-previous_quantity));
                    consumtionService.insert(measurementDTO, deviceId);
                }
                System.out.println(peakComputation);
            }
            previous_timestamp = timestamp;
            previous_quantity = quantity;
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @MessageMapping("/send/message")
    public void sendPeakNotification(String message) {
        String url = "/message";
        System.out.println("------------------------------------- SENT -------------------------");
        this.messageTemplate.convertAndSend(url, message);
    }
}
