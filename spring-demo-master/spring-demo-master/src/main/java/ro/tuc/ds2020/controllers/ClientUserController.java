package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ClientUserDTO;
import ro.tuc.ds2020.services.ClientUserService;
import ro.tuc.ds2020.services.MapValidationErrorService;

import javax.validation.Valid;
import java.util.List;

///import static com.Security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping(value="/user")
@CrossOrigin
public class ClientUserController {

    @Autowired
    private ClientUserService clientUserService;
    /*
        @Autowired
        JwtTokenProvider tokenProvider;



        @Autowired
        private AuthenticationManager authenticationManager;
     */
    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    public Integer insertUser(@RequestBody(required = true) ClientUserDTO clientUserDTO){

        return clientUserService.insertUser(clientUserDTO);
    }

    @RequestMapping(value = "/update",method= RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateUser(@RequestBody(required = true) ClientUserDTO clientUserDTO){

        return clientUserService.updateUser(clientUserDTO);
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)

    public Integer deleteUser(@PathVariable("id") Integer id){
        return clientUserService.deleteUser(id);
    }

    @RequestMapping(method =  RequestMethod.GET)
    public List<ClientUserDTO> displayAllUsers(){
        return clientUserService.viewUsers();
    }

/*
    @RequestMapping(method = RequestMethod.POST, consumes  = MediaType.APPLICATION_JSON_VALUE , value = "/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null) return errorMap;
         return ResponseEntity.ok(new JWTLoginSucessResponse(true,clientUserService.loginUser(loginRequest)));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ClientUser clientUser, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)
            return errorMap;
        ClientUser newClientUser = clientUserService.saveUser(clientUser);
        return new ResponseEntity<ClientUser>(newClientUser, HttpStatus.CREATED);

    }



    /*
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSucessResponse(true,jwt));
    }

     */

}