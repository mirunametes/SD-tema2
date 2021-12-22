package ro.tuc.ds2020.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name= "clientUser")

public class ClientUser  {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @Column(name="name", unique = false, nullable = false, length = 100)
    private String name;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Column(name="birthDate", unique = false, nullable = false)
    private LocalDateTime birthDate;

    @Column(name="address",unique = false, nullable = false)
    private String address;

    @NotNull
    @Column(name="username",unique = false, nullable = false)
    private String username;

    @NotNull
    @Column(name="password",unique = false, nullable = false)
    private String password;

    @OneToMany(mappedBy= "clientUser")
    private List<SmartDevice> smartDevices;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;



    public ClientUser(Integer id, String name, LocalDateTime birthDate, String address,String username,String password, Role role) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.username=username;
        this.password = password;
        this.role = role;
    }

    public ClientUser(){

    }

    public ClientUser(Integer id, String name, LocalDateTime birthDate, String address, String username, String password, List<SmartDevice> smartDevices, Role role) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.username = username;
        this.password = password;
        this.smartDevices = smartDevices;
        this.role = role;
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

    public List<SmartDevice> getSmartDevices() {
        return smartDevices;
    }

    public void setSmartDevices(List<SmartDevice> smartDevices) {
        this.smartDevices = smartDevices;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
/*
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {

        return true;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

 */


}

