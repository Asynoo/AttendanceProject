package AttendanceProject.Dal;

import AttendanceProject.Be.Attendance;

import java.util.List;

public interface DAOAttendance {
    List<Attendance> getAttendances();

    void submitAttendance(Attendance attendance);

    void editAttendance(Attendance attendance);

    void confirmEditAttendance(Attendance attendance);

    void cancelSubmission(Attendance attendance);
}
