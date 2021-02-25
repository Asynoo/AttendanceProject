package AttendanceProject.Bll;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Dal.DAOAttendance;
import javafx.collections.ObservableList;

public class AttendanceManager {

    private DAOAttendance daoAttendance = new DAOAttendance();


    public ObservableList<Attendance> getAllStudents(){
        return daoAttendance.getStudentData();
    }

}
