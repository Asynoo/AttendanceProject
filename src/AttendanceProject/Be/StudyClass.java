package AttendanceProject.Be;

public class StudyClass {
    private String className;
    private int id;

    public StudyClass (String ClassName){
        this.className = ClassName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
