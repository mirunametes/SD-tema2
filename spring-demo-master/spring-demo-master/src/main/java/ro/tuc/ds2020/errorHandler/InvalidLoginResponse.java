package ro.tuc.ds2020.errorHandler;

public class InvalidLoginResponse {
    private String username;
    private String password;


    public InvalidLoginResponse(){
        this.username = "Invalid email";
        this.password = "Invalid password";
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

