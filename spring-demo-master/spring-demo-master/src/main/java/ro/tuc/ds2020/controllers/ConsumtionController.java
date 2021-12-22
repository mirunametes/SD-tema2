package ro.tuc.ds2020.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ConsumtionDTO;
import ro.tuc.ds2020.services.ConsumtionService;

import java.util.List;

@RestController
@RequestMapping(value="/consumtion")
@CrossOrigin
public class ConsumtionController {

    @Autowired
    private ConsumtionService consumtionService;


    @RequestMapping(method =  RequestMethod.GET)
    public List<ConsumtionDTO> displayConsumtions(){
        return consumtionService.viewConsumtions();
    }

    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    public Integer insertConsumtion(@RequestBody(required = true) ConsumtionDTO consumtionDTO){

        return consumtionService.insertConsumtion(consumtionDTO);
    }


    @RequestMapping(value = "/update",method= RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateConsumtion(@RequestBody(required = true) ConsumtionDTO consumtionDTO){

        return consumtionService.updateConsumtion(consumtionDTO);
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public Integer deleteSensor(@PathVariable("id") Integer id){

        return consumtionService.deleteConsumtion(id);
    }
}

