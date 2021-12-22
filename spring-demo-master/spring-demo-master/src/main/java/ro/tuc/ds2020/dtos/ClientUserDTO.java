package ro.tuc.ds2020.dtos;



import ro.tuc.ds2020.entities.SmartDevice;

import java.time.LocalDateTime;
import java.util.List;

public class ClientUserDTO {

    private Integer id;
    private String name;
    private LocalDateTime birthDate;
    private String address;
    private String username;
    private String password;
    private String nameRole;
    private List<SmartDevice> smartDevices;

    public ClientUserDTO(Integer id, String name, LocalDateTime birthDate, String address,String username,String password, String nameRole) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.username=username;
        this.password = password;
        this.nameRole = nameRole;
        //this.smartDevices = smartDevices;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }


    public List<SmartDevice> getSmartDevices() {
        return smartDevices;
    }

    public void setSmartDevices(List<SmartDevice> smartDevices) {
        this.smartDevices = smartDevices;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
