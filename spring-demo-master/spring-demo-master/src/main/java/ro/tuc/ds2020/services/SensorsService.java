package ro.tuc.ds2020.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.SmartDevice;
import ro.tuc.ds2020.errorHandler.ResourceNotFoundException;
import ro.tuc.ds2020.repositories.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SensorsService {

    @Autowired
    private SensorsRepository sensorsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ConsumtionRepository consumtionRepository;

    @Autowired
    private ClientUserRepository clientUserRepository;

    @Autowired
    private SmartDeviceRepository smartDeviceRepository;

    public List<SensorDTO> viewSensors(){
        List<Sensor> sensors = StreamSupport
                .stream(sensorsRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        return sensors.stream().map(SensorBuilder::generateDTOFormatFromEntity)
                .collect(Collectors.toList());
    }

    public Sensor findSensorByIdRaw(Integer id){
        Optional<Sensor> sensor = sensorsRepository.findById(id);

        return sensor.get();
    }

    public Integer insertSensorById(SensorDTO sensorDTO){

        Optional<SmartDevice> smartDevice= smartDeviceRepository.findById(sensorDTO.getSmartDeviceId());

        if(!smartDevice.isPresent()){

            throw new ResourceNotFoundException("sensor","sensor_id",sensorDTO.getSmartDeviceId());
        }

        // SensorFieldValidator.validateSensor(sensorDTO);
        Sensor newSensor = SensorBuilder.generateEntityFromDTO(sensorDTO);
        //sensorDTO.setDescription(sensorDTO.getDescription());
        newSensor.setDescription(sensorDTO.getDescription());
        newSensor.setSmartDevice(smartDevice.get());
        sensorsRepository.save(newSensor);
        return newSensor.getId();
    }

    /*
    public Integer updateSensorById(SensorDTO sensorDTO,Integer id){

        Optional<Sensor> sensor= sensorsRepository.findById(id);

        if(!sensor.isPresent()){
            throw new ResourceNotFoundException("Sensor","sensor id",id);
        }
        sensorDTO.setDescription(sensorDTO.getDescription());
        Sensor sensor1 = SensorBuilder.generateEntityFromDTO(sensorDTO);
        sensorsRepository.save(sensor1);
        return sensor1.getId();

    }

     */
    public Integer updateSensor(SensorDTO sensorDTO){

        //SensorFieldValidator.validateSensor(sensorDTO);
        Optional<Sensor> verifySensor= sensorsRepository.findById(sensorDTO.getId());

        if(!verifySensor.isPresent()){
            throw new ResourceNotFoundException("Sensor","sensor id",sensorDTO.getId());
        }

        sensorDTO.setDescription(sensorDTO.getDescription());
        Sensor sensor = SensorBuilder.generateEntityFromDTO(sensorDTO);
        sensorsRepository.save(sensor);
        return sensor.getId();
    }

    public Integer deleteSensor(Integer id){
        Optional<Sensor> sensor= sensorsRepository.findById(id);

        if(!sensor.isPresent()){
            throw new ResourceNotFoundException("Sensor","sensor id",id);
        }
        sensorsRepository.delete(sensor.get());
        return id;
    }


}
