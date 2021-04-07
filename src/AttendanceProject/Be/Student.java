package AttendanceProject.Be;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int studyClassId;

    public Student (int id, String firstName, String lastName, String username, String password, int studyClassId){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.studyClassId = studyClassId;
    }

    public int getId() {
        return id;
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

    public int getStudyClassId() {
        return studyClassId;
    }

    public void setStudyClassId(int studyClassId) {
        this.studyClassId = studyClassId;
    }
}
