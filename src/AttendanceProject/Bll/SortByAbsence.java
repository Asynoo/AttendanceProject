package AttendanceProject.Bll;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Be.Student;
import AttendanceProject.Gui.Models.AttendanceModel;
import java.util.Comparator;
import java.util.List;

/**
 * The purpose of this class is to sort the list of students provided to it.
 * It implements Comperator
 * */

public class SortByAbsence implements Comparator<Student> {


    @Override
    public int compare(Student o1, Student o2) {
        return getAbsence(o2) - getAbsence(o1);
    }

    /**
     * There is a performance penalty to the current implementation because I am creating attendance model
     * I believe for every comparison which is not good, but I did not figure out how I can feed data dicrectly to this class.
     * */

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

        /**
         * Following logic is here because compare() method requires integers for the comparison, but I am working with
         * double and therefore multiplied values of absence by a factor of 100, which does not affect anything as all
         * the values are multiplied by this number.
         * */
        double percentageAbsence = (double) notAttending/(double)attandingDays;
        double times100 = percentageAbsence*100;
        int to2dp = (int)times100;
        return to2dp;
    }
    }

