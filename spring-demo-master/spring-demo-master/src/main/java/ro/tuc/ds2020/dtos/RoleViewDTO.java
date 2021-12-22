package ro.tuc.ds2020.dtos;

public class RoleViewDTO {

    private String name;
    public RoleViewDTO(String name) {
        this.name=name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
