package AttendanceProject.Bll;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Dal.DAOAttendance;
import javafx.collections.ObservableList;

public class AttendanceManager {

    private final DAOAttendance daoAttendance = new DAOAttendance();


    public ObservableList<Attendance> getAllStudents1(){
        return daoAttendance.getStudentData();
    }
    public ObservableList<Attendance> getAllStudentsTwo(){
        return daoAttendance.getStudentDataTwo();
    }

}
