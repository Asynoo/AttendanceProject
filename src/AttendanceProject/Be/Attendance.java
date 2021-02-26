package AttendanceProject.Be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attendance {

    private final StringProperty studentName;

    public Attendance(String name) {
        this.studentName = new SimpleStringProperty(name);
    }

    public String getName() {
        return studentName.get();
    }

}