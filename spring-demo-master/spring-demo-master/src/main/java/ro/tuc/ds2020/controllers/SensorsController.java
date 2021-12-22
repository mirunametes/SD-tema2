package ro.tuc.ds2020.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.services.SensorsService;

import java.util.List;

@RestController
@RequestMapping(value="/sensor")
@CrossOrigin
public class SensorsController {

    @Autowired
    private SensorsService sensorsService;

    @RequestMapping(method =  RequestMethod.GET)
    public List<SensorDTO> dispalyAllSensors(){
        return sensorsService.viewSensors();
    }

    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    public Integer insertSensorById(@RequestBody(required = true) SensorDTO sensorDTO){

        return sensorsService.insertSensorById(sensorDTO);
    }

    @RequestMapping(value="/get/{smartDeviceID}", method = RequestMethod.GET)
    public SensorDTO displaySensorByDevice(@PathVariable("smartDeviceID")Integer smartDeviceId){
        List<SensorDTO> sensorDTOS=dispalyAllSensors();
        //SensorDTO sensorDTO=new SensorDTO;
        int k=-1;
        for(int i=0; i<sensorDTOS.size();i++){
            if(sensorDTOS.get(i).getSmartDeviceId()==smartDeviceId){
                System.out.println("senzor gasit");
                //sensorDTO=sensorDTOS.get(i);
                k=i;
            }
        }
        return sensorDTOS.get(k);
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public Integer deleteSensor(@PathVariable("id") Integer id){

        return sensorsService.deleteSensor(id);
    }

    /*
    @RequestMapping(value = "/update/{id}",method= RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateSensorById(@PathVariable("id")@RequestBody SensorDTO sensorDTO,Integer id){

        return sensorsService.updateSensorById(sensorDTO,id);
    }

     */

    @RequestMapping(value = "/update",method= RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateSensor(@RequestBody(required = true) SensorDTO sensorDTO){

        return sensorsService.updateSensor(sensorDTO);
    }

}
