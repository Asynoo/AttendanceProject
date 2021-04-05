package AttendanceProject.Be;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private int studyClassId;

    public Student (int id, String firstName, String lastName, String username, int studyClassId){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.studyClassId = studyClassId;
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


    public int getStudyClassId() {
        return studyClassId;
    }

    public void setStudyClassId(int studyClassId) {
        this.studyClassId = studyClassId;
    }
}
