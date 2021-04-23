package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Be.Student;
import AttendanceProject.Be.Teacher;
import AttendanceProject.Gui.Models.AttendanceModel;
import AttendanceProject.Gui.Models.StudentModel;
import AttendanceProject.Gui.Models.StudyClassModel;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherHomepageViewController implements Initializable {
    public Label testLabel;
    @FXML
    private HBox acceptDeclineHbox;
    @FXML
    private JFXButton acceptSubmissionBtn;
    @FXML
    private JFXButton declineSubmissionBtn;
    @FXML
    private JFXButton backToLogin;
    @FXML
    private Label userLabel;
    @FXML
    private VBox activeSubmissions;
    @FXML
    private Label paneDescription;
    @FXML
    private ScrollPane scrollStudentSummary;
    @FXML
    private ScrollPane scrollClassAttendance;

    private Boolean studentsClassVisible = false;
    private Boolean studentsSummaryVisible = false;
    private Boolean pendingSubmissionsVisible = false;
    private final TilePane tilePaneClass = new TilePane();
    private final TilePane individualStudentTiles = new TilePane();
    private ListView<Attendance> listOfSubmissions;


    private StudentModel studentModel;
    private AttendanceModel attendanceModel;
    private StudyClassModel studyClassModel;

    private Teacher user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollClassAttendance.setVisible(false);
        scrollStudentSummary.setVisible(false);
        activeSubmissions.setVisible(false);
        paneDescription.setText("");

    }

    public void setStudyClassModel(StudyClassModel studyClassModel){
        this.studyClassModel = studyClassModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;

    }

    public void setAttendanceModel(AttendanceModel attendanceModel) {
        System.out.println(attendanceModel);
        this.attendanceModel = attendanceModel;
        fillStudentsIndividually();
        fillSubmissions();
        fillStudentsClass();
    }

    public void setUser(Teacher user) {
        this.user = user;
        userLabel.setText(user.getFirstName() + " " + user.getLastName());
    }

    public void showClassAttendance() {
        if (!studentsClassVisible){
            scrollClassAttendance.setVisible(true);
            scrollStudentSummary.setVisible(false);
            activeSubmissions.setVisible(false);
            studentsClassVisible = true;
            paneDescription.setText("Class attendance");
        }else {
            scrollClassAttendance.setVisible(false);
            studentsClassVisible = false;

        }
    }

    public void showStudentSummary() {
        if(!studentsSummaryVisible){
            scrollStudentSummary.setVisible(true);
            scrollClassAttendance.setVisible(false);
            activeSubmissions.setVisible(false);
            studentsSummaryVisible = true;
            paneDescription.setText("Student Summary");
        }else{
            scrollStudentSummary.setVisible(false);
            studentsSummaryVisible = false;
        }
    }

    public void showPendingSubmissions(ActionEvent actionEvent) {
        if(!pendingSubmissionsVisible){
            activeSubmissions.setVisible(true);
            scrollStudentSummary.setVisible(false);
            scrollClassAttendance.setVisible(false);
            pendingSubmissionsVisible = true;
            paneDescription.setText("Pending Submissions");
        }else{
            activeSubmissions.setVisible(false);
            pendingSubmissionsVisible = false;
        }
    }

    private List<Attendance> studentsAttendanceToday(){
        LocalDate todaysDate = LocalDate.now();
        System.out.println(todaysDate);

        List<Attendance> studentStatusToday = new ArrayList<>();

        for (Attendance att: attendanceModel.getAttendanceList()){
            if (att.getDate().equals(todaysDate)){
                System.out.println("Todays date added to the list");
                studentStatusToday.add(att);
            }
        }
        return studentStatusToday;
    };

    private void fillStudentsClass() {

            studentModel.getStudentList();

            List<Attendance> listToCheck = studentsAttendanceToday();


            for (Student student: studentModel.getStudentList()) {

                for(Attendance att: listToCheck){
                    if(att.getStudentId() == student.getId()){
                        //System.out.println("student id   " +att.getStudentId()+ "is present   "+ att.isPresent() );
                        if(att.isPresent()){
                            ImageView imgView = new ImageView("images/facetry.png");
                            imgView.setFitHeight(75);
                            imgView.setFitWidth(75);
                            Button headButton = new Button();
                            headButton.setGraphic(imgView);
                            headButton.setStyle("-fx-border-radius: 50; -fx-background-color: null");
                            headButton.setOnAction((e) -> openIndividualStudent(student));
                            Label lblContent = new Label(student.getFirstName() + student.getLastName());
                            VBox vbox = new VBox();
                            vbox.getChildren().add(headButton);
                            vbox.getChildren().add(lblContent);

                            tilePaneClass.getChildren().add(vbox);
                        }else if(!att.isPresent()){
                            ImageView imgView = new ImageView("images/faceRedtry.png");
                            imgView.setFitHeight(75);
                            imgView.setFitWidth(75);
                            Button headButton = new Button();
                            headButton.setGraphic(imgView);
                            headButton.setStyle("-fx-border-radius: 50; -fx-background-color: null");
                            headButton.setOnAction((e) -> openIndividualStudent(student));
                            Label lblContent = new Label(student.getFirstName() + student.getLastName());
                            VBox vbox = new VBox();
                            vbox.getChildren().add(headButton);
                            vbox.getChildren().add(lblContent);

                            tilePaneClass.getChildren().add(vbox);
                        }
                    }
                }


            }

            tilePaneClass.setPrefTileHeight(110);
            tilePaneClass.setPrefTileWidth(110);
            tilePaneClass.setPrefSize(351, 253);



            /** Here is logic for adding students randomly with randomly assigned attendance resembled by different color of icon */

//            for (int i = 0; i < numberOfStudents; i++) {
//
//                int tmp = (int) ( Math.random() * 2 + 1);
//
//                if (tmp == 1){
//
//                    ImageView imgView = new ImageView("images/facetry.png");
//                    imgView.setFitHeight(75);
//                    imgView.setFitWidth(75);
//                    Label lblContent = new Label(attendanceManager.getAllStudents1().get(new Random().nextInt(10)).getName());
//                    VBox vbox = new VBox();
//                    vbox.getChildren().add(imgView);
//                    vbox.getChildren().add(lblContent);
//
//                    tilePaneClass.getChildren().add(vbox);
//
//                }else {
//
//                    ImageView imgView = new ImageView("images/faceRedtry.png");
//                    imgView.setFitHeight(75);
//                    imgView.setFitWidth(75);
//
//                    Label lblContent = new Label(attendanceManager.getAllStudentsTwo().get(new Random().nextInt(9)).getName());
//
//                    VBox vbox = new VBox();
//                    vbox.getChildren().add(imgView);
//                    vbox.getChildren().add(lblContent);
//
//                    tilePaneClass.getChildren().add(vbox);
//                    String random = "bruh";
//                    imgView.setOnMouseClicked(e -> showInividualStudent(random));
//
//                }
//
//            }
            scrollClassAttendance.setContent(tilePaneClass);


    }

    private void showInividualStudent(String s){
        System.out.println("called it");
        scrollClassAttendance.setVisible(false);
        scrollStudentSummary.setVisible(false);
        testLabel.setVisible(true);
        Label studentName = new Label(s);
        activeSubmissions.getChildren().add(studentName);

    }

    private void fillStudentsIndividually() {
        individualStudentTiles.setPrefTileWidth(375);
        individualStudentTiles.setPrefColumns(1);
        individualStudentTiles.setPadding(new Insets(10,0,0,0));
        scrollStudentSummary.setContent(individualStudentTiles);
        scrollStudentSummary.setPannable(false);

        List<Student> sortedList = sortListStudents(studentModel.getStudentList());
        for(Student student: studentModel.getStudentList()){
            System.out.println(student.getFirstName());
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AttendanceProject/Gui/Views/IndividualStudentSummary.fxml"));

            HBox hb = null;
            try {
                hb = loader.load();
                ((IndividualStudentSummaryController)loader.getController()).setStudent(student);
                ((IndividualStudentSummaryController)loader.getController()).setAbsenceModel(attendanceModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
            individualStudentTiles.getChildren().add(hb);
        }
    }

    private List<Student> sortListStudents(List<Student> unsortedList) {

        List<Student> descendingOrder = new ArrayList<>();

        int maxValue = 0;
        int attending = 0;
        int notAttending = 0;

        for (Student student: unsortedList) {

        }
        return descendingOrder;
    }

    public void openIndividualStudent(Student student) {

        Stage IndividualStudentStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AttendanceProject/Gui/Views/IndividualStudent.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            ((IndividualStudentController)loader.getController()).setStudent(student);
            ((IndividualStudentController)loader.getController()).setAttendanceModel(attendanceModel);
            IndividualStudentStage.setTitle(student.getFirstName() + " " + student.getLastName());
            IndividualStudentStage.setScene(new Scene(root));
            IndividualStudentStage.setResizable(true);
            IndividualStudentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void fillSubmissions(){
        ObservableList<Attendance> pendingApproval;
        pendingApproval = FXCollections.observableArrayList();

        List<Attendance> listAttendance = attendanceModel.getAttendanceList();
        for (Attendance att: listAttendance) {
            if (att.hasChangeRequest()){
                pendingApproval.add(att);
            }
        }

        listOfSubmissions = new ListView();
        listOfSubmissions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                acceptDeclineHbox.setVisible(true);
            }
        });

        listOfSubmissions.setItems(pendingApproval);
        listOfSubmissions.getSelectionModel().getSelectedItems();
        activeSubmissions.getChildren().add(listOfSubmissions);

    }

    public void backToLogin(ActionEvent actionEvent) {
        Stage LoginView = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AttendanceProject/Gui/Views/LoginView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            LoginView.setTitle("Attendance Checker");
            LoginView.setScene(new Scene(root));
            LoginView.setResizable(false);
            LoginView.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeButtonAction();
    }


    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) backToLogin.getScene().getWindow();
        stage.close();
    }

    public void acceptSubmission(ActionEvent actionEvent) {
        for (Attendance att: listOfSubmissions.getSelectionModel().getSelectedItems()) {
            System.out.println(listOfSubmissions.getSelectionModel().getSelectedItems());
            listOfSubmissions.getItems().remove(listOfSubmissions.getSelectionModel().getSelectedItem());
            attendanceModel.confirmAttendance(att);
        }
    }

    public void declineSubmission(ActionEvent actionEvent) {
        attendanceModel.cancelSubmission(listOfSubmissions.getSelectionModel().getSelectedItem());
        listOfSubmissions.getItems().remove(listOfSubmissions.getSelectionModel().getSelectedItem());

    }
}