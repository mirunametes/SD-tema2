package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SmartDeviceAdminViewDTO;
import ro.tuc.ds2020.entities.SmartDevice;

public class SmartDeviceAdminBuilder {

    public SmartDeviceAdminBuilder() {
    }

    public static SmartDeviceAdminViewDTO generateDTOFormatFromEntity(SmartDevice smartDevice){
        return new SmartDeviceAdminViewDTO(
                smartDevice.getDescription(),
                smartDevice.getAddress(),
                smartDevice.getMaxEnergyConsumtion(),
                smartDevice.getAvgConsumtion(),
                smartDevice.getSensor().getDescription(),
                smartDevice.getUser().getName()
        );


    }


    public static SmartDevice generateEntityFromDTO(SmartDeviceAdminViewDTO smartDeviceAdminViewDTO){
        return new SmartDevice(
                smartDeviceAdminViewDTO.getDescription(),
                smartDeviceAdminViewDTO.getAddress(),
                smartDeviceAdminViewDTO.getMaxEnergyConsumtion(),
                smartDeviceAdminViewDTO.getAvgConsumtion()
        );
    }
}
