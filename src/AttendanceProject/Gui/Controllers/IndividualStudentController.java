package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Be.CalendarButton;
import AttendanceProject.Be.Student;
import AttendanceProject.Bll.CalendarManager;
import AttendanceProject.Gui.Models.AttendanceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class IndividualStudentController {

    /**Retrieves the CalenderManager Class.*/
    CalendarManager calendarManager = new CalendarManager();

    /**Imports all the @FXML data needed from the fxml file.*/

    @FXML
    private Label presentLbl;
    @FXML
    private Label weekdayLbl;
    @FXML
    private Label absentLbl;
    @FXML
    private Label studentLbl;
    @FXML
    private BorderPane calendarPane;
    @FXML
    private Label calendarInfoLbl;
    @FXML
    private GridPane calendarGrid;
    @FXML
    private HBox chartPane;
    @FXML
    private PieChart chart;

    Student student;
    AttendanceModel attendanceModel;
    List<ArrayList<CalendarButton>> columnList;
    Month displayedMonth;

    /**Set the Calendar Colors.*/
    static String weekendBgColor = "#999999";
    static String weekendTxtColor = "#bfbfbf";
    static String weekdayPresentBgColor = "#85e085";
    static String weekdayAbsentBgColor = "#ff6666";
    static String weekdayPresentTxtColor = "#FFFFFF";
    static String weekdayAbsentTxtColor = "#FFFFFF";
    static String unsetBgColor = "#FFFFFF";
    static String unsetTxtColor = "#000000";
    static String editTxtColor = "#E6EF34";

    /**Variables.*/
    private int isAttendant = 0;
    private int isNotAttendant = 0;
    LocalDate today;

    /**This method is used to display the logged in users name at the top of the UI.*/
    public void setStudent(Student student) {
        this.student = student;
        studentLbl.setText(student.getFirstName() + " " + student.getLastName() + "'s attendance");
    }

    /**This method sets the AttendanceModel, and activates a few other methods.*/
    public void setAttendanceModel(AttendanceModel attendanceModel) {
        this.attendanceModel = attendanceModel;
        calendarFillDates();
        calendarManager.setDateToday();
        countDaysAttendance();
        presentLbl.setText("Days present: " + isAttendant);
        absentLbl.setText("Days absent: " + isNotAttendant);
        weekdayLbl.setText("Most absent weekday:\n" + calculateMostAbsentWeekdays().toString());
    }

    /**Here we convert the attendance data into percentages and display them visually utilizing a diagram/piechart.
     * We use some imports, local variables and a ObservableList to connect and create the piechart.*/
    public void showStatistics() {
        int obtainedIs = isAttendant;
        int obtainedIsNot = isNotAttendant;
        int total = isAttendant+isNotAttendant;
        chartPane.setVisible(true);
        calendarPane.setVisible(false);
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Attending "+ this.calculatePercentageAttending(obtainedIs, total) +"%", isAttendant),
                new PieChart.Data("Not Attending "+ this.calculatePercentageNotAttending(obtainedIsNot, total) +"%", isNotAttendant)
        );
        chart.setData(pieData);
    }

    /**Dont touch the MaxPercentage "101" in these two methods.
     * I have no clue as to why it's accurate when I set it to 101% instead of 100%, just dont touch it.*/
    public double calculatePercentageAttending(int obtainedIs, int total) {
        if (total == 0)
            throw new IllegalArgumentException("Total can't be zero");
        return obtainedIs * 101 / total;
    }

    public double calculatePercentageNotAttending(int obtainedIsNot, int total) {
        if (total == 0)
            throw new IllegalArgumentException("Total can't be zero");
        return obtainedIsNot * 101 / total;
    }

    /**Opens up the Attendance history/the calender, and de-toggles the other panes.*/
    public void showHistory() {
        chartPane.setVisible(false);
        calendarPane.setVisible(true);
    }

    /**This method initializes some data for the calendar.*/
    public void initialize() {
        today = LocalDate.now();
        setupCalendar();
    }

    /**This method sets up the the entire calendar and the buttons visually, through the use of ArrayLists onto the calender grid.*/
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

    /**This long method adds the calendar's data onto the buttons, this contains the days, months and such.
     * It also fills out the appropriate colors for each button depending on if you've been attending or not.
     * Its very visual so the user can easily navigate throughout the entire calender and get a easy overview.
     * It utilizes a ArrayList which is located in a for loop, which in turn uses a lot of if/else statements.
     * In the method there is also the appropriate data and variables in the start.
     * In the end there is a simple System.out.print for you to see how many days you have/have not been attending for the given month.*/
    public void calendarFillDates(){
        displayedMonth = calendarManager.getCurrentMonth();
        calendarInfoLbl.setText(calendarManager.getCurrentMonthName()+ " " + calendarManager.getCurrentYear());
        calendarManager.dateToFirstWeekMonthDay();
        for (ArrayList<CalendarButton> rowList:columnList) {
            for (CalendarButton calendarButton:rowList) {
                //Filling the button with relevant Data
                for (Attendance a:attendanceModel.getStudentAttendances(student)) {
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
                    }
                    else{
                        calendarButton.setStyle("-fx-background-color:" + weekdayAbsentBgColor + ";-fx-text-fill:" + weekdayAbsentTxtColor);
                    }
                    if(calendarButton.getAttendance().hasChangeRequest()){
                        calendarButton.setStyle(calendarButton.getStyle() + ";-fx-text-fill:" + editTxtColor);
                    }
                }
                else calendarButton.setStyle("-fx-background-color:"+ unsetBgColor + ";-fx-text-fill:" + unsetTxtColor);
                calendarManager.cycleDayUp();
            }
        }
    }

    /**This method enables the user to move forward onto the next month in the calender.*/
    public void actionMonthForward() {
        if(calendarManager.getCurrentMonth() == displayedMonth)calendarManager.cycleMonthUp();
        calendarClearDates();
        calendarFillDates();
    }

    /**This method enables the user to move backward onto the last month in the calender.*/
    public void actionMonthBack() {
        if(calendarManager.getCurrentMonth() == displayedMonth.plus(1))calendarManager.cycleMonthDown();
        calendarManager.cycleMonthDown();
        calendarClearDates();
        calendarFillDates();
    }

    /**This method clears the Calenders days.*/
    private void calendarClearDates(){
        for (ArrayList<CalendarButton> rowList:columnList) {
            for (CalendarButton calendarButton:rowList) {
                calendarButton.setAttendance(null);
                calendarButton.setOnAction(null);
            }
        }
    }

    private void countDaysAttendance(){
        for (Attendance a:attendanceModel.getAttendanceList()) {
            if(a.getStudentId() == student.getId()) {
                if (a.isPresent()) {
                    isAttendant++;
                } else {
                    isNotAttendant++;
                }
            }
        }
    }

    private Set<DayOfWeek> calculateMostAbsentWeekdays(){
        TreeMap<Integer,Set<DayOfWeek>> absentWeekdays = new TreeMap<>();
        int monday = 0,tuesday = 0,wednesday = 0,thursday = 0,friday = 0;
        for (Attendance a:attendanceModel.getAttendanceList()) {
            if(!a.isPresent() && a.getStudentId() == student.getId()) {
                switch (a.getDate().getDayOfWeek()) {
                    case MONDAY -> monday++;
                    case TUESDAY -> tuesday++;
                    case WEDNESDAY -> wednesday++;
                    case THURSDAY -> thursday++;
                    case FRIDAY -> friday++;
                }
            }
        }
        //This is super yucky but it works
        absentWeekdays.put(monday,new TreeSet<>());
        absentWeekdays.get(monday).add(DayOfWeek.MONDAY);
        if(!absentWeekdays.containsKey(tuesday)) absentWeekdays.put(tuesday,new TreeSet<>());
        absentWeekdays.get(tuesday).add(DayOfWeek.TUESDAY);
        if(!absentWeekdays.containsKey(wednesday)) absentWeekdays.put(wednesday,new TreeSet<>());
        absentWeekdays.get(wednesday).add(DayOfWeek.WEDNESDAY);
        if(!absentWeekdays.containsKey(thursday)) absentWeekdays.put(thursday,new TreeSet<>());
        absentWeekdays.get(thursday).add(DayOfWeek.THURSDAY);
        if(!absentWeekdays.containsKey(friday)) absentWeekdays.put(friday,new TreeSet<>());
        absentWeekdays.get(friday).add(DayOfWeek.FRIDAY);
        return absentWeekdays.lastEntry().getValue();
    }
}
