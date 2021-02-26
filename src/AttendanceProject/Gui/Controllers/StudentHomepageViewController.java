package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.CalendarButton;
import AttendanceProject.Bll.CalendarManager;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.time.LocalDate;
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
    static String weekendTxtColor = "#bfbfbf";
    static String weekdayPresentBgColor = "#85e085";
    static String weekdayAbsentBgColor = "#ff6666";
    static String weekdayPresentTxtColor = "#FFFFFF";
    static String weekdayAbsentTxtColor = "#FFFFFF";
    static String unsetBgColor = "#FFFFFF";
    static String unsetTxtColor = "#000000";
    //Mock data settings
    static int absencePercentage = 90;

    private boolean attendance;
    private boolean attendanceSet;

    LocalDate today;

    @FXML
    private void isAttendingAction() {
        attendance = true;
        attendanceSet = true;
        System.out.println("You have been submitted as Attending");
        isNotAttending.setVisible(false);
        calendarFillDates();
    }

    @FXML
    private void isNotAttendingAction() {
        attendance = false;
        attendanceSet = true;
        System.out.println("You have been submitted as Not Attending");
        isAttending.setVisible(false);
        calendarFillDates();
    }

        public void showStatistics() {
        chartPane.setVisible(true);
        statusPane.setVisible(false);
        calendarPane.setVisible(false);
        Random rand = new Random();
        int rand_int1 = rand.nextInt(60);
        int rand_int2 = rand.nextInt(40);
            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                    new PieChart.Data("Attending", rand_int1),
                    new PieChart.Data("Not Attending", rand_int2)
            );
            chart.setData(pieData);
    }

    public void showStatus() {
        chartPane.setVisible(false);
        statusPane.setVisible(true);
        calendarPane.setVisible(false);
    }

    public void showHistory() {
        chartPane.setVisible(false);
        statusPane.setVisible(false);
        calendarPane.setVisible(true);
    }

    public void initialize() {
        today = LocalDate.now();
        setupCalendar();
        calendarFillDates();
        calendarManager.setDateToday();
    }

    public void setupCalendar() {
        columnList = new ArrayList<ArrayList<CalendarButton>>();
        for(int y = 0;y < 6;y++){
            columnList.add(new ArrayList<CalendarButton>());
            for(int x=0;x < 7;x++){
                CalendarButton calendarButton = new CalendarButton(":)",today);
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
                calendarButton.setLocalDate(LocalDate.of(calendarManager.getCurrentYear(),calendarManager.getCurrentMonth(),calendarManager.getCurrentDay()));
                //Generate mock values for the calendar
                if(Math.random()*100 < absencePercentage) calendarButton.setPresent(true);
                else calendarButton.setPresent(false);
                setCalendarButtonToday();
                //Opacity for days not in current month
                if(calendarManager.getCurrentMonth() != displayedMonth){
                    calendarButton.setOpacity(0.7);
                }
                else calendarButton.setOpacity(1);
                //Coloring days in the calendar
                if(calendarManager.getCurrentWeekday() == 1 || calendarManager.getCurrentWeekday() == 7){
                    calendarButton.setStyle("-fx-background-color:"+ weekendBgColor + ";-fx-text-fill:" + weekendTxtColor);
                }
                else if(today.compareTo(calendarButton.getLocalDate()) > 0 || (today.compareTo(calendarButton.getLocalDate()) == 0 && attendanceSet)){
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

    public void setCalendarButtonToday() {
        for (ArrayList<CalendarButton> rowList:columnList) {
            for (CalendarButton calendarButton:rowList) {
                if(calendarButton.getLocalDate().isEqual(today)){
                    calendarButton.setPresent(attendance);
                }
            }
        }
    }
}
