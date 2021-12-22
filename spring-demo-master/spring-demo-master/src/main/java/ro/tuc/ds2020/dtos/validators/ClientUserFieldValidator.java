package ro.tuc.ds2020.dtos.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ro.tuc.ds2020.dtos.ClientUserDTO;
import ro.tuc.ds2020.errorHandler.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ClientUserFieldValidator implements Validator {

    public ClientUserFieldValidator(){

    }

    public static void validateUser(ClientUserDTO clientUserDTO){
        List<String> errors= new ArrayList<>();
        if(clientUserDTO == null){
            errors.add("User DTO null");
            throw new ResourceNotFoundException("UserDTO","UserDTO", clientUserDTO);
        }
        if(clientUserDTO.getName() == null){
            errors.add("User DTO name null");
            throw new ResourceNotFoundException("UserDTO","UserDTO", clientUserDTO.getName());
        }
        if(clientUserDTO.getAddress() == null){
            errors.add("User DTO address null");
            throw new ResourceNotFoundException("UserDTO","UserDTO", clientUserDTO.getAddress());
        }
        if(clientUserDTO.getBirthDate() == null){
            errors.add("User DTO birth date null");
            throw new ResourceNotFoundException("UserDTO","UserDTO", clientUserDTO.getBirthDate());
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
