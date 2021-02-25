package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.CalendarButton;
import AttendanceProject.Bll.CalendarManager;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.*;


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
    @FXML
    private PieChart chart;


    List<ArrayList<CalendarButton>> columnList;
    int displayedMonth;

    //Calendar colors
    static String weekendBgColor = "#999999";
    static String weekendTxtColor = "#FFFFFF";
    static String weekdayPresentBgColor = "#4390ff";
    static String weekdayAbsentBgColor = "#81b3f9";
    static String weekdayPresentTxtColor = "#FFFFFF";
    static String weekdayAbsentTxtColor = "#FFFFFF";
    static String unsetBgColor = "#FFFFFF";
    static String unsetTxtColor = "#000000";
    //Mock data settings
    static int absencePercentage = 90;

    public boolean attendance;

    Date today;

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
            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                    new PieChart.Data("Attending", 67),
                    new PieChart.Data("Not Attending", 331)
            );
            chart.setData(pieData);
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
        today = calendarManager.getCurrentDate();
        setupCalendar();
        calendarFillDates();
    }

    public void setupCalendar() {
        columnList = new ArrayList<ArrayList<CalendarButton>>();
        for(int y = 0;y < 6;y++){
            columnList.add(new ArrayList<CalendarButton>());
            for(int x=0;x < 7;x++){
                CalendarButton calendarButton = new CalendarButton(":)");
                calendarButton.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
                calendarButton.setAlignment(Pos.CENTER);
                calendarButton.setFont(Font.font(21));
                columnList.get(y).add(calendarButton);
                calendarGrid.add(calendarButton,x,y);
            }
        }
    }

    public void calendarFillDates(){
        displayedMonth = calendarManager.getCurrentMonth();
        calendarInfoLbl.setText(calendarManager.getCurrentMonthName()+ " " + calendarManager.getCurrentYear());
        calendarManager.dateToFirstWeekMonthDay();
        for (ArrayList<CalendarButton> rowList:columnList) {
            for (CalendarButton calendarButton:rowList) {
                calendarButton.setText(calendarManager.getCurrentDay() + "");

                if(Math.random()*100 < absencePercentage) calendarButton.setPresent(true);
                else calendarButton.setPresent(false);
                if(calendarManager.getCurrentMonth() != displayedMonth){
                    calendarButton.setOpacity(0.7);
                }
                if(calendarManager.getCurrentWeekday() == 1 || calendarManager.getCurrentWeekday() == 7){
                    calendarButton.setStyle("-fx-background-color:"+ weekendBgColor + ";-fx-text-fill:" + weekendTxtColor);
                }
                else if(calendarManager.getCurrentDate().before(today)){
                    if(calendarButton.isPresent()){
                        calendarButton.setStyle("-fx-background-color:"+ weekdayPresentBgColor + ";-fx-text-fill:" + weekdayPresentTxtColor);
                    }
                    else {
                        calendarButton.setStyle("-fx-background-color:" + weekdayAbsentBgColor + ";-fx-text-fill:" + weekdayAbsentTxtColor);
                    }
                }
                else calendarButton.setStyle("-fx-background-color:"+ unsetBgColor + ";-fx-text-fill:" + unsetTxtColor);
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
