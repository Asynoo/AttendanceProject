package AttendanceProject.Be;

import com.jfoenix.controls.JFXButton;

import java.time.LocalDate;

public class CalendarButton extends JFXButton {
    boolean present;
    LocalDate date;

    public CalendarButton(String text, LocalDate date){
        super(text);
        present = false;
        this.date = date;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean isPresent() {
        return present;
    }

    public void setLocalDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getLocalDate() {
        return date;
    }
}
