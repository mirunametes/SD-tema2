package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.SmartDeviceDTO;
import ro.tuc.ds2020.dtos.SmartDeviceViewDTO;
import ro.tuc.ds2020.services.SmartDeviceService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="/smartDevice")
public class SmartDeviceController {

    @Autowired
    private SmartDeviceService smartDeviceService;

    @RequestMapping(value="/viewAll/{roleName}",method = RequestMethod.GET)
    public List<SmartDeviceViewDTO> viewSmartDevices(@PathVariable("roleName") String roleName){

        return smartDeviceService.viewSmartDevices(roleName);
    }

    @RequestMapping(value= "/getSmartDevice/{smartDeviceId}/{roleName}",method = RequestMethod.GET)
    public SmartDeviceViewDTO displaySmartDeviceById(@PathVariable("smartDeviceId") Integer smartDeviceId,@PathVariable("roleName") String roleName){
        return smartDeviceService.viewSmartDeviceById(smartDeviceId,roleName);
    }

    @RequestMapping(method =  RequestMethod.GET)
    public List<SmartDeviceDTO> displayAllSmartDevices(){
        return smartDeviceService.viewSmartDevices();
    }

    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    public Integer insertSmartDevice(@RequestBody(required = true) SmartDeviceDTO smartDeviceDTO){

        return smartDeviceService.insertSmartDevice(smartDeviceDTO);
    }

    @RequestMapping(value = "/update",method= RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateSmartDevice(@RequestBody(required = true) SmartDeviceDTO smartDeviceDTO){

        return smartDeviceService.updateSmartDevice(smartDeviceDTO);
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public Integer deleteSmartDevice(@PathVariable("id") Integer id){
        return smartDeviceService.deleteSmartDevice(id);
    }


    @RequestMapping(value ="/get/{clientName}", method = RequestMethod.GET)
    public List<SmartDeviceDTO> displaySmartDeviceByClientId(@PathVariable("clientName")String clientName){
        // return smartDeviceService.viewSmartDeviceByClientId(id);

        System.out.println(clientName);
        List<SmartDeviceDTO> smartDeviceDTOS=smartDeviceService.viewSmartDevices();
        List<SmartDeviceDTO> smartDeviceDTOList =new ArrayList<SmartDeviceDTO>();
        for(int i=0;i<smartDeviceDTOS.size();i++){
            if(smartDeviceDTOS.get(i).getUserName().equals(clientName)){
                smartDeviceDTOList.add(smartDeviceDTOS.get(i));
                System.out.println(smartDeviceDTOS.get(i).getId());
            }
        }
        return smartDeviceDTOList;
    }

}
