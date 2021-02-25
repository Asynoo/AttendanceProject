package AttendanceProject.Be;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attendance {

    private StringProperty studentName;
    private DoubleProperty absence;

    public Attendance(String name, double absence) {
        this.studentName = new SimpleStringProperty(name);
        this.absence = new SimpleDoubleProperty(absence);
    }

    public String getName() {
        return studentName.get();
    }

    public void setName(String name) {
        this.studentName.set(name);
    }

    public double getAbsence() {
        return absence.get();
    }

    public void setAbsence(double absence) {
        this.absence.set(absence);
    }

}