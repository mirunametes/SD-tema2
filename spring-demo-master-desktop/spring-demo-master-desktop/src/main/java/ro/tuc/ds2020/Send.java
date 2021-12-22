package ro.tuc.ds2020;

import net.minidev.json.JSONObject;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class Send implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public Send(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("[" + LocalDateTime.now() + "] >> "+ " Starting producer...");
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Miruna\\Desktop\\spring-demo-master-desktop\\spring-demo-master-desktop\\sensor.csv")))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                LocalDateTime time = LocalDateTime.now();
                rabbitTemplate.convertAndSend("rabbitMQ", "rabbit", toJSONString(Double.parseDouble(sCurrentLine), time, 1).getBytes());
                try {
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toJSONString(double quantity, LocalDateTime time, int sensorId) {
        JSONObject jsonObject = new JSONObject();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = time.format(formatter);
        jsonObject.put("timestamp", formattedDateTime);
        jsonObject.put("measurementValue",  quantity);
        jsonObject.put("sensorId", sensorId);
        System.out.println(jsonObject);
        return jsonObject.toString();
    }
}
