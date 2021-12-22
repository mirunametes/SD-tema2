package ro.tuc.ds2020.entities;


import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @Column(name="name", unique = false, nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "role",fetch =  FetchType.EAGER)
    private List<ClientUser> clientUsers;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(){

    }

    public Role(String name) {
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



}
