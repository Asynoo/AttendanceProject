package AttendanceProject.Bll;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Dal.DAOAttendance;
import AttendanceProject.Dal.DAOAttendanceDB;

import java.util.List;

public class AttendanceManager {
    DAOAttendance daoAttendance;

    public AttendanceManager(){
        daoAttendance = new DAOAttendanceDB();
    }

    public List<Attendance> getAttendances(){
        return daoAttendance.getAttendances();
    }

    public void editAttendance(Attendance attendance){
        daoAttendance.editAttendance(attendance);
    }

    public void addAttendance(Attendance attendance) {
        daoAttendance.submitAttendance(attendance);
    }

    public void confirmAttendance(Attendance attendance){daoAttendance.confirmEditAttendance(attendance);}

    public void cancelSubmission(Attendance attendance) {daoAttendance.cancelSubmission(attendance);}
}
