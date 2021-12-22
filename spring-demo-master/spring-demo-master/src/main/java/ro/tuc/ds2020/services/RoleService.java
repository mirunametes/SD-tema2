package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.RoleDTO;
import ro.tuc.ds2020.dtos.builders.RoleBuilder;
import ro.tuc.ds2020.entities.Role;
import ro.tuc.ds2020.errorHandler.ResourceNotFoundException;
import ro.tuc.ds2020.repositories.ClientUserRepository;
import ro.tuc.ds2020.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ClientUserRepository clientUserRepository;

    public RoleDTO findRoleViewById(Integer id){
        Optional<Role> role = roleRepository.findById(id);
        if(!role.isPresent()){
            throw new ResourceNotFoundException("Role","role id",id);
        }

        return RoleBuilder.generateDTOFormatFromEntity(role.get());
    }

    public List<RoleDTO> viewRoles(){
        List<Role> roles = StreamSupport
                .stream(roleRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return roles.stream().map(RoleBuilder::generateDTOFormatFromEntity).collect(Collectors.toList());
    }

    public Integer insertRoleById(RoleDTO roleDTO){
        //RoleFieldValidator.validateRole(roleDTO);
        Role newRole=RoleBuilder.generateEntityFromDTO(roleDTO);
        roleDTO.setName(roleDTO.getName());
        roleRepository.save(newRole);
        return newRole.getId();
    }

    public Integer updateRole(RoleDTO roleDTO){
        // RoleFieldValidator.validateRole(roleDTO);

        Optional<Role> verifyRole = roleRepository.findById(roleDTO.getId());
        if(!verifyRole.isPresent()){
            throw new ResourceNotFoundException("Role","role id",roleDTO.getId());
        }

        roleDTO.setName(roleDTO.getName());
        Role role=RoleBuilder.generateEntityFromDTO(roleDTO);
        roleRepository.save(role);
        return role.getId();
    }

    public Integer deleteRole(Integer id){
        Optional<Role> role =roleRepository.findById(id);

        if(!role.isPresent()){
            throw new ResourceNotFoundException("Role","role id",id);
        }
        roleRepository.delete(role.get());
        return id;

    }
}

