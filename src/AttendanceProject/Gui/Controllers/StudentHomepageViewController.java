package AttendanceProject.Gui.Controllers;

import AttendanceProject.Bll.CalendarManager;
import com.jfoenix.controls.JFXButton;
import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;


public class StudentHomepageViewController {
    CalendarManager calendarManager = new CalendarManager();

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
    private Label calendarInfoLbl;
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

    List<ArrayList<Label>> columnList;
    int displayedMonth;

    public boolean attendance;

    @FXML
    void isAttendingAction(ActionEvent actionEvent) {

        boolean attendance = true;
        if (attendance){
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
        if(!attendance) {
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
        calendarFillDates();
    }

    public void setupCalendar() {
        columnList = new ArrayList<ArrayList<Label>>();
        for(int y = 0;y < 6;y++){
            columnList.add(new ArrayList<Label>());
            for(int x=0;x < 7;x++){
                Label label = new Label(":)");
                label.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
                label.setAlignment(Pos.CENTER);
                label.setFont(Font.font(21));
                if(x>4){
                    label.setStyle("-fx-background-color:#999999;-fx-text-fill:#FFFFFF");
                }
                columnList.get(y).add(label);
                calendarGrid.add(label,x,y);
            }
        }
    }

    public void calendarFillDates(){
        displayedMonth = calendarManager.getCurrentMonth();
        calendarInfoLbl.setText(calendarManager.getCurrentMonthName()+ " " + calendarManager.getCurrentYear());
        calendarManager.dateToFirstWeekMonthDay();
        for (ArrayList<Label> rowList:columnList) {
            for (Label dateLabel:rowList) {
                dateLabel.setText(calendarManager.getCurrentDate() + "");
                calendarManager.cycleDayUp();
            }
        }
    }

    public void actionMonthForward() {
        if(calendarManager.getCurrentMonth() != displayedMonth+1)calendarManager.cycleMonthUp();
        calendarFillDates();
    }

    public void actionMonthBack() {
        if(calendarManager.getCurrentMonth() != displayedMonth-1)calendarManager.cycleMonthDown();
        calendarManager.cycleMonthDown();
        calendarFillDates();
    }
}
