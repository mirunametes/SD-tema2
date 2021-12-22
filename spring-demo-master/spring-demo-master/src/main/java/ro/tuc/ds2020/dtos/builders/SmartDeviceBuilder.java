package ro.tuc.ds2020.dtos.builders;


import ro.tuc.ds2020.dtos.SmartDeviceDTO;
import ro.tuc.ds2020.entities.SmartDevice;

public class SmartDeviceBuilder {

    public SmartDeviceBuilder() {

    }

    public static SmartDeviceDTO generateDTOFromEntity(SmartDevice smartDevice){
        return new SmartDeviceDTO(
                smartDevice.getId(),
                smartDevice.getDescription(),
                smartDevice.getAddress(),
                smartDevice.getMaxEnergyConsumtion(),
                smartDevice.getAvgConsumtion(),
                //smartDevice.getSensor().getDescription(),
                smartDevice.getUser().getName()

        );
    }

    public static SmartDevice generateEntityFromDTO(SmartDeviceDTO smartDeviceDTO){
        //validate smart device
        return new SmartDevice(
                smartDeviceDTO.getId(),
                smartDeviceDTO.getDescription(),
                smartDeviceDTO.getAddress(),
                smartDeviceDTO.getMaxEnergyConsumtion(),
                smartDeviceDTO.getAvgConsumtion()

        );
    }
}
