package ro.tuc.ds2020.services;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.ConsumtionDTO;
import ro.tuc.ds2020.dtos.builders.ConsumtionBuilder;
import ro.tuc.ds2020.entities.Consumtion;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.SmartDevice;
import ro.tuc.ds2020.errorHandler.ResourceNotFoundException;
import ro.tuc.ds2020.repositories.ConsumtionRepository;
import ro.tuc.ds2020.repositories.SensorsRepository;
import ro.tuc.ds2020.repositories.SmartDeviceRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ConsumtionService {

    @Autowired
    private ConsumtionRepository consumtionRepository;

    @Autowired
    private SmartDeviceRepository smartDeviceRepository;

    @Autowired
    private SensorsRepository sensorsRepository;

    @Autowired
    private SmartDeviceService smartDeviceService;

    @Autowired
    private SensorsService sensorsService;

    public List<ConsumtionDTO> viewConsumtions(){
        List<Consumtion> consumtions = StreamSupport
                .stream(consumtionRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        return consumtions.stream().map(ConsumtionBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }


    public ConsumtionDTO insert(ConsumtionDTO consumtionDTO,int deviceId){
        SmartDevice smartDevice= smartDeviceService.findDeviceByIdRaw(deviceId);
        Sensor sensor= sensorsService.findSensorByIdRaw(deviceId);
        System.out.println(smartDevice.getUser());
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Consumtion consumtion = new Consumtion( consumtionDTO.getDate(), consumtionDTO.getEnergyConsumed(),sensor);
        Consumtion insertedConsumtions = consumtionRepository.saveAndFlush(consumtion);
        sensor.getConsumtions().add(consumtion);
        return ConsumtionBuilder.generateDTOFromEntity(insertedConsumtions);
    }

    public Integer insertConsumtion(ConsumtionDTO consumtionDTO){
        Optional<Sensor> sensor = sensorsRepository.findById(consumtionDTO.getSmartSensorId());
        if(sensor == null){
            throw new ResourceNotFoundException("Smart Device","smartDevice id", consumtionDTO.getId());
        }
        Consumtion consumtion = ConsumtionBuilder.generateEntityFromDTO(consumtionDTO);
        consumtion.setSensor(sensor.get());
        consumtionRepository.save(consumtion);
        return consumtion.getId();
    }

    public Integer updateConsumtion(ConsumtionDTO consumtionDTO){
        Optional<Consumtion> verifyConsumtion = consumtionRepository.findById(consumtionDTO.getId());
        if(!verifyConsumtion.isPresent()){
            throw new ResourceNotFoundException("Consumtion","consumtion id", consumtionDTO.getId());

        }
        Optional<Sensor> sensor = sensorsRepository.findSensorById(consumtionDTO.getSmartSensorId());
        if(sensor == null){
            throw new ResourceNotFoundException("Sensor","sensor id", consumtionDTO.getSmartSensorId());
        }

        Consumtion  consumtion=ConsumtionBuilder.generateEntityFromDTO(consumtionDTO);
        consumtion.setSensor(sensor.get());

        consumtionRepository.save(consumtion);
        return consumtion.getId();
    }

    public Integer deleteConsumtion(Integer id){
        Optional<Consumtion> consumtion = consumtionRepository.findById(id);
        if(!consumtion.isPresent()){
            throw new ResourceNotFoundException("Consumtion","consumtion id", id);

        }
        consumtionRepository.delete(consumtion.get());
        return id;

    }
}

