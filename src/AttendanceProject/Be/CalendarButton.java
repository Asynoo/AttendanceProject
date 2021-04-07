package AttendanceProject.Be;

import com.jfoenix.controls.JFXButton;

import java.time.LocalDate;

public class CalendarButton extends JFXButton {
    Attendance attendance;

    public CalendarButton(Attendance attendance){
        this.attendance = attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Attendance getAttendance() {
        return attendance;
    }
}
