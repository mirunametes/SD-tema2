package ro.tuc.ds2020.dtos;


public class RoleDTO {

    private Integer id;
    private String name;
    //private Integer userId;


    public RoleDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
        // this.userId = userId;

    }

    // public RoleDTO(String nameRole) {
    // }

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



    /*
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

     */
}
