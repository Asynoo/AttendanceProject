package AttendanceProject.Be;

public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String credentials;

    public Teacher(int id, String firstName, String lastName, String username, String password, String credentials){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.credentials = credentials;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getCredentials() {
        return credentials;
    }
}
