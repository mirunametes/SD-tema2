package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.entities.Sensor;

public class SensorBuilder {

    public SensorBuilder(){

    }

    public static SensorDTO generateDTOFormatFromEntity(Sensor sensors){
        return new SensorDTO(
                sensors.getId(),
                sensors.getDescription(),
                sensors.getMaxValue(),
                sensors.getSmartDevice().getId()
        );
    }

    public static Sensor generateEntityFromDTO(SensorDTO sensorDTO){

        //vaildate sensors
        return new Sensor(
                sensorDTO.getId(),
                sensorDTO.getDescription(),
                sensorDTO.getMaxValue()
        );
    }
}
