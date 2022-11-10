package bhcc.edu.mandip;

public class ClientInfo implements java.io.Serializable {
    private String firstname;

    private String lastname;
    private String username;
    private String password;

    public ClientInfo(String firstname, String lastname, String username, String passwrod) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = passwrod;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
