package AttendanceProject.Be;

import com.jfoenix.controls.JFXButton;

public class CalendarButton extends JFXButton {
    boolean present;
    public CalendarButton(String text){
        super(text);
        present = false;
    }

    public CalendarButton(String text,boolean present){
        super(text);
        this.present = present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean isPresent() {
        return present;
    }

}
