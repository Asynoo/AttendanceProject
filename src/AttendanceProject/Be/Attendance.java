package AttendanceProject.Be;

import java.time.LocalDate;

public class Attendance {

    private LocalDate date;
    private int studentId;
    private Integer id;
    private boolean present;
    private boolean changeRequest;

    public Attendance(LocalDate date, Integer id, int studentId, boolean presence, boolean changeRequest) {
        this.id = id;
        this.date = date;
        this.studentId = studentId;
        this.present = presence;
        this.changeRequest = changeRequest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Student with ID: "+studentId+ "   Change on:  " + date;
    }
}