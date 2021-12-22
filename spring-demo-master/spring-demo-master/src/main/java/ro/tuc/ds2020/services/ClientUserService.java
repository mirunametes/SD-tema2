package ro.tuc.ds2020.services;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.ClientUserDTO;
import ro.tuc.ds2020.dtos.builders.ClientUserBuilder;
import ro.tuc.ds2020.entities.ClientUser;
import ro.tuc.ds2020.entities.Role;
import ro.tuc.ds2020.errorHandler.ResourceNotFoundException;
import ro.tuc.ds2020.repositories.ClientUserRepository;
import ro.tuc.ds2020.repositories.RoleRepository;
import ro.tuc.ds2020.repositories.SmartDeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//import static com.Security.SecurityConstants.TOKEN_PREFIX;

@Service
public class ClientUserService {

    @Autowired
    private ClientUserRepository clientUserRepository;
    /*
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;


     */
    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private SmartDeviceRepository smartDeviceRepository;

/*
    @Autowired
    private AuthenticationManager authenticationManager;
/*
    public Integer insertUser(ClientUserDTO clientUserDTO){
        try{
            //UserFieldValidator.validateUser(userDTO);
            Optional<Role> role= roleRepository.findRoleByName(clientUserDTO.getNameRole());
            ClientUser clientUser = ClientUserBuilder.generateEntityFromDTO(clientUserDTO);
            clientUser.setRole(role.get());
            clientUserRepository.save(clientUser);
            return clientUser.getId();
        }catch(Exception e){
            throw new InvalidDateException("Invalid new user", clientUserDTO.getName());
        }
    }

 */

    public Integer insertUser(ClientUserDTO clientUserDTO){
        //UserFieldValidator.validateUser(userDTO);

        Optional<Role> role= roleRepository.findRoleByName(clientUserDTO.getNameRole());
        if(role == null){
            throw new ResourceNotFoundException("Role","role id", clientUserDTO.getNameRole());
        }
        ClientUser clientUser = ClientUserBuilder.generateEntityFromDTO(clientUserDTO);
        clientUser.setRole(role.get());
        clientUserRepository.save(clientUser);
        return clientUser.getId();


    }
    public Integer updateUser(ClientUserDTO clientUserDTO){
        Optional<ClientUser> verifyUser = clientUserRepository.findById(clientUserDTO.getId());
        System.out.println(clientUserDTO.getId());
        if(!verifyUser.isPresent()){
            throw new ResourceNotFoundException("User","user id", clientUserDTO.getId());
        }

        Optional<Role> role= roleRepository.findRoleByName(clientUserDTO.getNameRole());
        if(role == null){
            throw new ResourceNotFoundException("Role","role id", clientUserDTO.getNameRole());
        }

        ClientUser clientUser = ClientUserBuilder.generateEntityFromDTO(clientUserDTO);
        clientUser.setRole(role.get());
        clientUserRepository.save(clientUser);
        return clientUser.getId();
    }

    public Integer deleteUser(Integer id){
        Optional<ClientUser> user = clientUserRepository.findById(id);
        if(!user.isPresent()){
            throw new ResourceNotFoundException("User","user id",id);
        }
        clientUserRepository.delete(user.get());
        return id;

    }

    public List<ClientUserDTO> viewUsers(){
        List<ClientUser> clientUsers = StreamSupport
                .stream(clientUserRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        return clientUsers.stream().map(ClientUserBuilder::generateDTOFormatFromEntity)
                .collect(Collectors.toList());
    }

    /*
    public String loginUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return TOKEN_PREFIX + tokenProvider.generateToken(authentication);
    }

    public ClientUser saveUser (ClientUser newClientUser){
        try{
            newClientUser.setPassword(bCryptPasswordEncoder.encode(newClientUser.getPassword()));
            newClientUser.setUsername(newClientUser.getUsername());
            return clientUserRepository.save(newClientUser);
        }catch(Exception e){
            throw new UsernameNotFoundException("Not found!");
        }
    }

     */




}
