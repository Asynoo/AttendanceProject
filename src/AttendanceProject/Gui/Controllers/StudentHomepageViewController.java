package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Be.CalendarButton;
import AttendanceProject.Be.Student;
import AttendanceProject.Bll.CalendarManager;
import AttendanceProject.Gui.Models.AttendanceModel;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.NumberFormat;
import java.util.Scanner;


public class StudentHomepageViewController {
    CalendarManager calendarManager = new CalendarManager();

    @FXML
    private Label userLbl;
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
    private PieChart chart;

    Student user;

    AttendanceModel attendanceModel;

    List<ArrayList<CalendarButton>> columnList;
    Month displayedMonth;

    //Calendar colors
    static String weekendBgColor = "#999999";
    static String weekendTxtColor = "#bfbfbf";
    static String weekdayPresentBgColor = "#85e085";
    static String weekdayAbsentBgColor = "#ff6666";
    static String weekdayPresentTxtColor = "#FFFFFF";
    static String weekdayAbsentTxtColor = "#FFFFFF";
    static String unsetBgColor = "#FFFFFF";
    static String unsetTxtColor = "#000000";

    private boolean attendanceSet;
    private int isAttendant = 0;
    private int isNotAttendant = 0;

    LocalDateTime today;

    @FXML
    private void isAttendingAction() {
        if (!attendanceSet) {
            attendanceModel.addAttendance(new Attendance(today.toLocalDate(),user.getId(),true,false));
            System.out.println("You have been submitted as Attending");
            updateAttendanceSet();
            calendarFillDates();
        }
    }

    @FXML
    private void isNotAttendingAction() {
        if (!attendanceSet) {
            attendanceModel.addAttendance(new Attendance(today.toLocalDate(),user.getId(),false,false));
            System.out.println("You have been submitted as Not Attending");
            updateAttendanceSet();
            isAttending.setOpacity(0.6);
            isNotAttending.setOpacity(1);
            calendarFillDates();
        }
    }

    public void setUser(Student user) {
        this.user = user;
        userLbl.setText(user.getFirstName() + " " + user.getLastName());
    }

    public void setAttendanceModel(AttendanceModel attendanceModel) {
        this.attendanceModel = attendanceModel;
        updateAttendanceSet();
        calendarFillDates();
        calendarManager.setDateToday();
    }

    public void showStatistics() {
        StudentHomepageViewController pc = new StudentHomepageViewController();
        int obtainedIs = isAttendant;
        int obtainedIsNot = isNotAttendant;
        int total = isAttendant+isNotAttendant;
        chartPane.setVisible(true);
        statusPane.setVisible(false);
        calendarPane.setVisible(false);
            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                    new PieChart.Data("Attending "+String.valueOf(pc.calculatePercentageAttending(obtainedIs, obtainedIsNot ,total))+"%", isAttendant),
                    new PieChart.Data("Not Attending "+String.valueOf(pc.calculatePercentageNotAttending(obtainedIs, obtainedIsNot ,total))+"%", isNotAttendant)
            );
            chart.setData(pieData);
    }

    //Dont touch the MaxPercentage "101" in these two methods. I have no clue as to why it's accurate when I set it to 101% instead of 100%, just dont touch it.
    public double calculatePercentageAttending(int obtainedIs,int obtainedIsNot , int total) {
        return obtainedIs * 101 / total;
    }

    public double calculatePercentageNotAttending(int obtainedIs,int obtainedIsNot , int total) {
        return obtainedIsNot * 101 / total;
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
        today = LocalDate.now().atStartOfDay();
        setupCalendar();
    }

    public void calculatePercentage(){
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(1);
        defaultFormat.setMaximumIntegerDigits(100);
        System.out.println("Is Attending: " + defaultFormat.format(isAttendant)+"Is Not Attending: " + defaultFormat.format(isNotAttendant));
    }




    public void setupCalendar() {
        columnList = new ArrayList<>();
        for(int y = 0;y < 6;y++){
            columnList.add(new ArrayList<>());
            for(int x=0;x < 7;x++){
                CalendarButton calendarButton = new CalendarButton(null);
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
                //Filling the button with relevant Data
                for (Attendance a:attendanceModel.getStudentAttendances(user)) {
                    if(a.getDate().atStartOfDay().isEqual(calendarManager.getLocalDate().atStartOfDay())) {
                        calendarButton.setAttendance(a);
                    }
                }
                calendarButton.setText(Integer.toString(calendarManager.getCurrentDay()));
                //Opacity for days not in current month
                if(calendarManager.getCurrentMonth() != displayedMonth){
                    calendarButton.setOpacity(0.7);
                }
                else calendarButton.setOpacity(1);
                //Coloring days in the calendar
                if(calendarManager.getCurrentWeekday() == DayOfWeek.SATURDAY || calendarManager.getCurrentWeekday() == DayOfWeek.SUNDAY){
                    calendarButton.setStyle("-fx-background-color:"+ weekendBgColor + ";-fx-text-fill:" + weekendTxtColor);
                }
                else if(calendarButton.getAttendance() != null){
                    if(calendarButton.getAttendance().isPresent()){
                        calendarButton.setStyle("-fx-background-color:"+ weekdayPresentBgColor + ";-fx-text-fill:" + weekdayPresentTxtColor);
                        isAttendant=++isAttendant;
                    }
                    else {
                        calendarButton.setStyle("-fx-background-color:" + weekdayAbsentBgColor + ";-fx-text-fill:" + weekdayAbsentTxtColor);
                        isNotAttendant=++isNotAttendant;
                    }
                }
                else calendarButton.setStyle("-fx-background-color:"+ unsetBgColor + ";-fx-text-fill:" + unsetTxtColor);
                calendarManager.cycleDayUp();
            }
        }
        System.out.println(isAttendant);
        System.out.println(isNotAttendant);
    }

    public void actionMonthForward() {
        if(calendarManager.getCurrentMonth() == displayedMonth)calendarManager.cycleMonthUp();
        calendarClearDates();
        calendarFillDates();
    }

    public void actionMonthBack() {
        if(calendarManager.getCurrentMonth() == displayedMonth.plus(1))calendarManager.cycleMonthDown();
        calendarManager.cycleMonthDown();
        calendarClearDates();
        calendarFillDates();
    }

    private void updateAttendanceSet(){
        for (Attendance a: attendanceModel.getStudentAttendances(user)) {
            if (a.getDate().atStartOfDay().isEqual(today)){
                attendanceSet = true;
                if(a.isPresent()){
                    isNotAttending.setOpacity(0.6);
                    isAttending.setOpacity(1);
                }
                else {
                    isNotAttending.setOpacity(1);
                    isAttending.setOpacity(0.6);
                }
                break;
            }
        }
    }

    private void calendarClearDates(){
        for (ArrayList<CalendarButton> rowList:columnList) {
            for (CalendarButton calendarButton:rowList) {
                calendarButton.setAttendance(null);
            }
        }
    }
}
