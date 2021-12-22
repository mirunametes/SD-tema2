package ro.tuc.ds2020.dtos.builders;
import ro.tuc.ds2020.dtos.ClientUserDTO;
import ro.tuc.ds2020.dtos.RoleViewDTO;
import ro.tuc.ds2020.entities.ClientUser;

public class ClientUserBuilder {

    public ClientUserBuilder(){

    }

    public static ClientUserDTO generateDTOFormatFromEntity(ClientUser clientUser){
        return new ClientUserDTO(
                clientUser.getId(),
                clientUser.getName(),
                clientUser.getBirthDate(),
                clientUser.getAddress(),
                clientUser.getUsername(),
                clientUser.getPassword(),
                clientUser.getRole().getName()
                //clientUser.getSmartDevices()
        );
    }

    public static ClientUser generateEntityFromDTO(ClientUserDTO clientUserDTO){
        //validate user
        return new ClientUser(
                clientUserDTO.getId(),
                clientUserDTO.getName(),
                clientUserDTO.getBirthDate(),
                clientUserDTO.getAddress(),
                clientUserDTO.getUsername(),
                clientUserDTO.getPassword(),
                clientUserDTO.getSmartDevices(),
                RoleViewBuilder.generateEntityFromDTO(new RoleViewDTO(clientUserDTO.getNameRole()))

        );
    }
}
