package AttendanceProject.Gui.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class StudentHomepageViewController {

    @FXML
    private JFXButton historyBtn;
    @FXML
    private ImageView historyIcon;
    @FXML
    private JFXButton statusBtn;
    @FXML
    private JFXButton statisticsBtn;
    @FXML
    private BorderPane calendarPane;
    @FXML
    private GridPane calendarGrid;

    @FXML
    private HBox chartPane;
    @FXML
    private HBox statusPane;
    @FXML
    private JFXButton isAttending;
    @FXML
    private JFXButton isNotAttending;
    @FXML
    private ImageView statusIcon;

    public boolean attendance;

    @FXML
    void isAttendingAction(ActionEvent actionEvent) {

    boolean attendance = true;
    if (attendance==true){
        System.out.println("You have been submitted as Attending");
    }
    else{
        System.out.println("You have been submitted as Not Attending");
    }
    isNotAttending.setVisible(false);


    }


    @FXML
    void isNotAttendingAction(ActionEvent actionEven) {

    boolean attendance = false;
    if(attendance==false) {
        System.out.println("You have been submitted as Not Attending");
    }
        else{
            System.out.println("You have been submitted as Attending");
        }
     isAttending.setVisible(false);
    }


    public void showStatistics(ActionEvent actionEvent) {
        chartPane.setVisible(true);
        statusPane.setVisible(false);
        calendarPane.setVisible(false);
    }

    public void showStatus(ActionEvent actionEvent) {
        chartPane.setVisible(false);
        statusPane.setVisible(true);
        calendarPane.setVisible(false);
    }

    public void showHistory(ActionEvent actionEvent) {
        chartPane.setVisible(false);
        statusPane.setVisible(false);
        calendarPane.setVisible(true);
    }

    public void initialize() {
        setupCalendar();
    }

    public void setupCalendar() {
        System.out.println(Calendar.getInstance().getTime());
        calendarGrid.add(new Label("cock"),1,1);
    }
}
