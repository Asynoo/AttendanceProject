package AttendanceProject.Be;

import java.time.LocalDate;

public class Attendance {

    private LocalDate date;
    private int studentId;
    private boolean present;
    private boolean changeRequest;

    public Attendance(LocalDate date,int studentId,boolean presence, boolean changeRequest) {
        this.date = date;
        this.studentId = studentId;
        this.present = presence;
        this.changeRequest = changeRequest;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean isPresent() {
        return present;
    }

    public void setChangeRequest(boolean changeRequest) {
        this.changeRequest = changeRequest;
    }

    public boolean hasChangeRequest() {
        return changeRequest;
    }
}