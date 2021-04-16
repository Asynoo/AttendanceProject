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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class StudentHomepageViewController {

    /**Retrives the CalenderManager Class.*/
    CalendarManager calendarManager = new CalendarManager();

    /**Imports all the @FXML data needed from the fxml file.*/
    @FXML
    private Label changeDescLbl;
    @FXML
    private Label changeTitleLbl;
    @FXML
    private JFXButton submitBtn;
    @FXML
    private VBox changeBox;
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
    private boolean attendanceSet;
    private int isAttendant = 0;
    private int isNotAttendant = 0;
    private int iA;
    private int iNA;
    LocalDate today;

    /**This method gives the user the ability to toggle the isAttending Function, to signal the database you're attending the current day.*/
    @FXML
    private void isAttendingAction() {
        if (!attendanceSet) {
            attendanceModel.addAttendance(new Attendance(today,null,user.getId(),true,false));
        }
        else {
            for (Attendance a:attendanceModel.getAttendanceList()) {
                if(a.getDate().isEqual(today)){
                    a.setPresent(true);
                    attendanceModel.editAttendance(a);
                }
            }
        }
        System.out.println("You have been submitted as Attending");
        updateAttendanceSet();
        calendarFillDates();
    }

    /**This method gives the user the ability to toggle the isNotAttending Function, to signal the database you're not attending the current day.*/
    @FXML
    private void isNotAttendingAction() {
        if (!attendanceSet) {
            attendanceModel.addAttendance(new Attendance(today,null,user.getId(),false,false));

        }
        else {
            for (Attendance a:attendanceModel.getAttendanceList()) {
                if(a.getDate().isEqual(today)){
                    a.setPresent(false);
                    attendanceModel.editAttendance(a);
                }
            }
        }
        System.out.println("You have been submitted as Not Attending");
        updateAttendanceSet();
        calendarFillDates();
    }

    /**This method is used to display the logged in users name at the top of the UI.*/
    public void setUser(Student user) {
        this.user = user;
        userLbl.setText(user.getFirstName() + " " + user.getLastName());
    }

    /**This method sets the AttendanceModel, and activates a few other methods.*/
    public void setAttendanceModel(AttendanceModel attendanceModel) {
        this.attendanceModel = attendanceModel;
        updateAttendanceSet();
        calendarFillDates();
        calendarManager.setDateToday();
    }

    /**Here we convert the attendance data into percentages and display them visually utilizing a diagram/piechart.
     * We use some imports, local variables and a ObservableList to connect and create the piechart.*/
    public void showStatistics() {
        int obtainedIs = isAttendant;
        int obtainedIsNot = isNotAttendant;
        int total = isAttendant+isNotAttendant;
        chartPane.setVisible(true);
        statusPane.setVisible(false);
        calendarPane.setVisible(false);
            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                    new PieChart.Data("Attending :" + iA +" days "+ this.calculatePercentageAttending(obtainedIs, total) +"%", isAttendant),
                    new PieChart.Data("Absent : " + iNA +" days "+ this.calculatePercentageNotAttending(obtainedIsNot, total) +"%", isNotAttendant)
            );
            chart.setData(pieData);
    }

    /**Dont touch the MaxPercentage "101" in these two methods.
     * I have no clue as to why it's accurate when I set it to 101% instead of 100%, just dont touch it.*/
    public double calculatePercentageAttending(int obtainedIs, int total) {
        return obtainedIs * 101 / total;
    }
    public double calculatePercentageNotAttending(int obtainedIsNot, int total) {
        return obtainedIsNot * 101 / total;
    }

    /**opens up the Statistics View/ The diagram for seeing your attendance in percentage.*/
    public void showStatus() {
        chartPane.setVisible(false);
        statusPane.setVisible(true);
        calendarPane.setVisible(false);
    }

    /**Opens up the Attendance history/the calender, and de-toggles the other panes.*/
    public void showHistory() {
        chartPane.setVisible(false);
        statusPane.setVisible(false);
        calendarPane.setVisible(true);
    }

    /**This method initializes some data for the calender.*/
    public void initialize() {
        today = LocalDate.now();
        setupCalendar();
    }

    /**This method sets up the the entire calender and the buttons visually, trough the use of ArrayLists onto the calender grid.*/
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

    /**This long method adds the calenders data onto the buttons, this contains the days, months and such.
     * It also fills out the appropriate colors for each button depending on if you've been attending or not.
     * Its very visual so the user can easily navigate throughout the entire calender and get a easy overview.
     * It utilizes a ArrayList which is located in a for loop, which in turn uses a lot of if/else statements.
     * In the method there is also the appropriate data and variables in the start.
     * In the end there is a simple System.out.print for you to see how many days you have/have not been attending for the given month.*/
    public void calendarFillDates(){
        displayedMonth = calendarManager.getCurrentMonth();
        calendarInfoLbl.setText(calendarManager.getCurrentMonthName()+ " " + calendarManager.getCurrentYear());
        calendarManager.dateToFirstWeekMonthDay();
        isAttendant=0;
        isNotAttendant=0;
        for (ArrayList<CalendarButton> rowList:columnList) {
            for (CalendarButton calendarButton:rowList) {
                //Filling the button with relevant Data
                for (Attendance a:attendanceModel.getStudentAttendances(user)) {
                    if(a.getDate().atStartOfDay().isEqual(calendarManager.getLocalDate().atStartOfDay())) {
                        calendarButton.setAttendance(a);
                        if(!a.getDate().isEqual(today)) {
                            if (!a.hasChangeRequest()) {
                                submitChangeAction(calendarButton);
                            } else {
                                cancelChangeAction(calendarButton);
                            }
                        }
                        else{
                            calendarButton.setOnAction(actionEvent -> showStatus());
                        }
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
                        iA = isAttendant;
                    }
                    else{
                        calendarButton.setStyle("-fx-background-color:" + weekdayAbsentBgColor + ";-fx-text-fill:" + weekdayAbsentTxtColor);
                        isNotAttendant=++isNotAttendant;
                        iNA = isNotAttendant;
                    }
                    if(calendarButton.getAttendance().hasChangeRequest()){
                        calendarButton.setStyle(calendarButton.getStyle() + ";-fx-text-fill:" + editTxtColor);
                    }
                }
                else calendarButton.setStyle("-fx-background-color:"+ unsetBgColor + ";-fx-text-fill:" + unsetTxtColor);
                calendarManager.cycleDayUp();
            }
        }
        System.out.println("Days Attending: "+isAttendant);
        System.out.println("Days Not Attending: "+isNotAttendant);
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

    public void cancelChange() {
        calendarGrid.setDisable(false);
        calendarGrid.setOpacity(1);
        changeBox.setVisible(false);
    }

    /**This method deals with some visuals, for example adjusts the opacity depending on you attendance.*/
    private void updateAttendanceSet(){
        for (Attendance a: attendanceModel.getStudentAttendances(user)) {
            if (a.getDate().isEqual(today)){
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

    /**This method clears the Calenders days.*/
    private void calendarClearDates(){
        for (ArrayList<CalendarButton> rowList:columnList) {
            for (CalendarButton calendarButton:rowList) {
                calendarButton.setAttendance(null);
            }
        }
    }

    private void submitChangeAction(CalendarButton calendarButton){
        calendarButton.setOnAction(actionEvent -> {
            calendarGrid.setDisable(true);
            calendarGrid.setOpacity(.3);
            changeBox.setVisible(true);
            changeTitleLbl.setText("Change attendance to present?");
            changeDescLbl.setText("The request will be sent to your class teacher");
            submitBtn.setText("Submit");
            submitBtn.setOnAction(ae -> {
                calendarButton.getAttendance().setChangeRequest(true);
                attendanceModel.editAttendance(calendarButton.getAttendance());
                cancelChangeAction(calendarButton);
                calendarButton.setStyle(calendarButton.getStyle() + ";-fx-text-fill:" + editTxtColor);
                cancelChange();
            });
        });
    }

    private void cancelChangeAction(CalendarButton calendarButton){
        calendarButton.setOnAction(actionEvent -> {
            calendarGrid.setDisable(true);
            calendarGrid.setOpacity(.3);
            changeBox.setVisible(true);
            changeTitleLbl.setText("Remove edit request?");
            changeDescLbl.setText("");
            submitBtn.setText("Remove");
            submitBtn.setOnAction(ae -> {
                calendarButton.getAttendance().setChangeRequest(false);
                attendanceModel.editAttendance(calendarButton.getAttendance());
                submitChangeAction(calendarButton);
                calendarButton.setStyle(calendarButton.getStyle().replaceFirst(";-fx-text-fill:" + editTxtColor,""));
                cancelChange();
            });
        });
    }
}
