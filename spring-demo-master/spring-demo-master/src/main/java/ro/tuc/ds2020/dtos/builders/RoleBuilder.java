package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.RoleDTO;
import ro.tuc.ds2020.entities.Role;

public class RoleBuilder {

    public RoleBuilder(){

    }

    public static RoleDTO generateDTOFormatFromEntity(Role role){
        return new RoleDTO(
                role.getId(),
                role.getName()

                //role.getUserId()
        );
    }

    public static Role generateEntityFromDTO(RoleDTO roleDTO){

        //validate role
        return new Role(
                roleDTO.getId(),
                roleDTO.getName()
                //roleDTO.getUserId()
        );
    }
}

