package AttendanceProject.Dal;

import AttendanceProject.Be.Attendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public interface DAOAttendance {
    boolean getAttendance(LocalDate date);

    void submitAttendance(LocalDate date,boolean attendance);

    void queryEditAttendance(LocalDate date,boolean attendance);

    void confirmEditAttendance(LocalDate date,boolean attendance);
}
