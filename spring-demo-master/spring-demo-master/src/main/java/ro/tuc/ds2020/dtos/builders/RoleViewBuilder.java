package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.RoleViewDTO;
import ro.tuc.ds2020.entities.Role;

public class RoleViewBuilder {

    public RoleViewBuilder() {
    }

    public static RoleViewDTO generateDTOFormatFromEntity(Role role){
        return new RoleViewDTO(
                role.getName());

    }
    public static Role generateEntityFromDTO(RoleViewDTO roleDTO){
        return  new Role(
                roleDTO.getName()
        );
    }
}
