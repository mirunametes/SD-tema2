package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SmartDeviceUserViewDTO;
import ro.tuc.ds2020.entities.SmartDevice;

public class SmartDeviceUserBuilder {

    public SmartDeviceUserBuilder() {
    }

    public static SmartDeviceUserViewDTO generateDTOFormatFromEntity(SmartDevice smartDevice){
        return new SmartDeviceUserViewDTO(
                smartDevice.getDescription(),
                smartDevice.getAddress(),
                smartDevice.getMaxEnergyConsumtion(),
                smartDevice.getAvgConsumtion(),
                smartDevice.getSensor().getDescription(),
                smartDevice.getUser().getName()

        );
    }

    public static SmartDevice generateEntityFromDTO(SmartDeviceUserViewDTO smartDeviceUserViewDTO){
        return new SmartDevice(
                smartDeviceUserViewDTO.getDescription(),
                smartDeviceUserViewDTO.getAddress(),
                smartDeviceUserViewDTO.getMaxEnergyConsumtion(),
                smartDeviceUserViewDTO.getAvgConsumtion()
        );
    }


}
