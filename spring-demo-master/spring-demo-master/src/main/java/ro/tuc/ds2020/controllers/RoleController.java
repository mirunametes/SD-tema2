package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.RoleDTO;
import ro.tuc.ds2020.repositories.RoleRepository;
import ro.tuc.ds2020.services.RoleService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value="/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public RoleDTO displayRoleViewById(@PathVariable("id")Integer id){
        return roleService.findRoleViewById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RoleDTO> displayAllRoles(){
        return roleService.viewRoles();
    }

    @RequestMapping(method =  RequestMethod.POST, value = "/insert")
    public Integer insertRoleById(@RequestBody(required = true) RoleDTO roleDTO){
        return roleService.insertRoleById(roleDTO);
    }



    @RequestMapping(method =  RequestMethod.PUT, value = "/update")
    public Integer updateRole(@RequestBody(required = true) RoleDTO roleDTO){
        return roleService.updateRole(roleDTO);
    }

    @RequestMapping(method =  RequestMethod.DELETE, value = "/delete/{id}")
    public Integer deleteRole(@PathVariable("id") Integer id){
        return roleService.deleteRole(id);
    }



}
