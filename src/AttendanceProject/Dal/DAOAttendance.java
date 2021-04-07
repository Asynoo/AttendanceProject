package AttendanceProject.Dal;

import AttendanceProject.Be.Attendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public interface DAOAttendance {
    List<Attendance> getAttendances();

    void submitAttendance(Attendance attendance);

    void queryEditAttendance(LocalDate date,boolean attendance);

    void confirmEditAttendance(LocalDate date,boolean attendance);
}
