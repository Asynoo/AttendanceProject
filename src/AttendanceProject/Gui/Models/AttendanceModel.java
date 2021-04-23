package AttendanceProject.Gui.Models;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Be.Student;
import AttendanceProject.Bll.AttendanceManager;

import java.util.ArrayList;
import java.util.List;

public class AttendanceModel {

    private AttendanceManager attendanceManager;
    private List<Attendance> attendanceList;

    public AttendanceModel(){
        this.attendanceManager = new AttendanceManager();
        updateAttendanceList();
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public List<Attendance> getStudentAttendances(Student student){
        ArrayList<Attendance> attendances = new ArrayList<>();
        for (Attendance a: attendanceList) {
            if(a.getStudentId() == student.getId()){
                attendances.add(a);
            }
        }
        return attendances;
    }
    public void addAttendance(Attendance attendance){
        attendanceManager.addAttendance(attendance);
        updateAttendanceList();
    }

    public void updateAttendanceList(){
        attendanceList = attendanceManager.getAttendances();
    }

    public void editAttendance(Attendance attendance){
        attendanceManager.editAttendance(attendance);
    }

    public void confirmAttendance(Attendance attendance){attendanceManager.confirmAttendance(attendance);}

    public void cancelSubmission(Attendance attendance){attendanceManager.cancelSubmission(attendance);}
}
