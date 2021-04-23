package AttendanceProject.Bll;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Be.Student;
import AttendanceProject.Gui.Models.AttendanceModel;

import java.util.Comparator;
import java.util.List;

public class SortByAbsence implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return getAbsence(o2) - getAbsence(o1);
    }

    private int getAbsence(Student student) {
        AttendanceModel attendanceModel = new AttendanceModel();
        int attandingDays = 0;
        int attending = 0;
        int notAttending = 0;
        List<Attendance> attendanceInfo = attendanceModel.getStudentAttendances(student);
        for (Attendance attendance: attendanceInfo) {
            attandingDays ++;
            if(attendance.isPresent()) {
                attending++;
            }else if(!attendance.isPresent()){
                notAttending ++;
            }
        }
        double percentageAbsence = (double) notAttending/(double)attandingDays;
        double times100 = percentageAbsence*100;
        int to2dp = (int)times100;
        return to2dp;
    }
    }

