package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.SmartDeviceDTO;
import ro.tuc.ds2020.dtos.SmartDeviceViewDTO;
import ro.tuc.ds2020.dtos.builders.SmartDeviceAdminBuilder;
import ro.tuc.ds2020.dtos.builders.SmartDeviceBuilder;
import ro.tuc.ds2020.dtos.builders.SmartDeviceUserBuilder;
import ro.tuc.ds2020.entities.ClientUser;
import ro.tuc.ds2020.entities.Role;
import ro.tuc.ds2020.entities.SmartDevice;
import ro.tuc.ds2020.errorHandler.ResourceNotFoundException;
import ro.tuc.ds2020.repositories.ClientUserRepository;
import ro.tuc.ds2020.repositories.RoleRepository;
import ro.tuc.ds2020.repositories.SensorsRepository;
import ro.tuc.ds2020.repositories.SmartDeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SmartDeviceService {

    @Autowired
    private SmartDeviceRepository smartDeviceRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SensorsRepository sensorsRepository;

    @Autowired
    private ClientUserRepository clientUserRepository;

    public List<SmartDeviceViewDTO> rolePrivilegeCheckList(List<SmartDevice> smartDevices,String roleName){
        Optional<Role> role = roleRepository.findRoleByName(roleName);
        if(!role.isPresent()){
            throw new ResourceNotFoundException("Role","role_id",roleName);
        }

        if(role.get().getName().equals("admin")){
            return smartDevices.stream().map(SmartDeviceAdminBuilder::generateDTOFormatFromEntity)
                    .collect(Collectors.toList());
        }

        return smartDevices.stream().map(SmartDeviceUserBuilder::generateDTOFormatFromEntity)
                .collect(Collectors.toList());

    }

    public SmartDeviceViewDTO rolePrivilegeCheck(SmartDevice smartDevice, String roleName){
        Optional<Role> role = roleRepository.findRoleByName(roleName);
        if(!role.isPresent()){
            throw new ResourceNotFoundException("Role","role_id",roleName);
        }

        if(role.get().equals("admin")){
            return SmartDeviceAdminBuilder.generateDTOFormatFromEntity(smartDevice);
        }

        return SmartDeviceUserBuilder.generateDTOFormatFromEntity(smartDevice);


    }

    public List<SmartDeviceViewDTO> viewSmartDevices(String roleName){
        Optional<Role> role = roleRepository.findRoleByName(roleName);

        if(!role.isPresent()){
            throw new ResourceNotFoundException("Role","role_id",roleName);
        }

        List<SmartDevice> smartDevices = StreamSupport
                .stream(smartDeviceRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        return rolePrivilegeCheckList(smartDevices,roleName);
    }

    public SmartDeviceDTO findSmartDeviceById(Integer id){
        Optional<SmartDevice> smartDevice = smartDeviceRepository.findById(id);

        return SmartDeviceBuilder.generateDTOFromEntity(smartDevice.get());
    }

    public SmartDevice findDeviceByIdRaw(int id){
        Optional<SmartDevice> deviceOptional = smartDeviceRepository.findById(id);

        return deviceOptional.get();
    }

    public SmartDeviceViewDTO viewSmartDeviceById(Integer id,String roleName){

        Optional<Role> role = roleRepository.findRoleByName(roleName);
        if(!role.isPresent()){
            throw new ResourceNotFoundException("Role","role_id",roleName);
        }

        Optional<SmartDevice> smartDevice = smartDeviceRepository.findById(id);
        if(!smartDevice.isPresent()){
            throw new ResourceNotFoundException("Smart device","smartDevice_id",id);
        }

        return rolePrivilegeCheck(smartDevice.get(),roleName);
    }

    public List<SmartDeviceDTO> viewSmartDevices(){
        List<SmartDevice> smartDevices = StreamSupport
                .stream(smartDeviceRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        return smartDevices.stream().map(SmartDeviceBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }





    public Integer insertSmartDevice(SmartDeviceDTO smartDeviceDTO){
        // SmartDeviceFieldValidator.validateSmartDevice(smartDeviceDTO);
        Optional<ClientUser> clientUser=clientUserRepository.findUserByName(smartDeviceDTO.getUserName());

        if(!clientUser.isPresent()){
            throw new ResourceNotFoundException("Smart device","smartDevice_id",smartDeviceDTO.getUserName());
        }

       /* Optional<Sensor> sensor =sensorsRepository.findByDescription(smartDeviceDTO.getSensorName());

        if(!sensor.isPresent()){
            throw new ResourceNotFoundException("Sensor","sensor_id",smartDeviceDTO.getSensorName());
        }


        */
        SmartDevice smartDevice = SmartDeviceBuilder.generateEntityFromDTO(smartDeviceDTO);
        // smartDevice.setSensor(sensor.get());
        smartDevice.setUser(clientUser.get());
        smartDeviceRepository.save(smartDevice);
        return smartDevice.getId();
    }

    public Integer updateSmartDevice(SmartDeviceDTO smartDeviceDTO){
        Optional<SmartDevice> verifySmartDevice = smartDeviceRepository.findById(smartDeviceDTO.getId());
        if(!verifySmartDevice.isPresent()){
            throw new ResourceNotFoundException("Smart device","smartDevice_id",smartDeviceDTO.getId());
        }

        Optional<ClientUser> clientUser=clientUserRepository.findUserByName(smartDeviceDTO.getUserName());

        if(!clientUser.isPresent()){
            throw new ResourceNotFoundException("Smart device","smartDevice_id",smartDeviceDTO.getUserName());
        }

        SmartDevice smartDevice = SmartDeviceBuilder.generateEntityFromDTO(smartDeviceDTO);
        smartDevice.setUser(clientUser.get());
        smartDeviceRepository.save(smartDevice);
        return smartDevice.getId();

    }

    public Integer deleteSmartDevice(Integer id){

        Optional<SmartDevice> verifySmartDevice = smartDeviceRepository.findById(id);
        if(!verifySmartDevice.isPresent()){
            throw new ResourceNotFoundException("Smart device","smartDevice_id",id);
        }

        smartDeviceRepository.delete(verifySmartDevice.get());
        return id;

    }

    /*

    public int insert(DeviceDTO deviceDTO){
        Device device = DeviceBuilder.dtoTOEntity1(deviceDTO);
        device = deviceRepository.save(device);
        return device.getId();
    }

    public DeviceDTO findDeviceById(int id){
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if(!deviceOptional.isPresent()){
            throw new ResourceNotFoundException(Device.class.getSimpleName() + "with id: " + id);
        }
        return DeviceBuilder.entityToDTO1(deviceOptional.get());
    }

     */






}
