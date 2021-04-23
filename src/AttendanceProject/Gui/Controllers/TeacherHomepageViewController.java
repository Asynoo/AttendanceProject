package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Be.Student;
import AttendanceProject.Be.Teacher;
import AttendanceProject.Bll.SortByAbsence;
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
import java.util.*;

public class TeacherHomepageViewController implements Initializable {
    @FXML
    private Label testLabel;
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
    private Teacher user;


    private StudentModel studentModel;
    private AttendanceModel attendanceModel;
    private StudyClassModel studyClassModel;


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

    public void setStudentModel(StudentModel studentModel) { this.studentModel = studentModel; }

    public void setAttendanceModel(AttendanceModel attendanceModel) {
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
        List<Attendance> studentStatusToday = new ArrayList<>();
        for (Attendance att: attendanceModel.getAttendanceList()){
            if (att.getDate().equals(todaysDate)){
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
            scrollClassAttendance.setContent(tilePaneClass);
    }

    private void fillStudentsIndividually() {
        individualStudentTiles.setPrefTileWidth(375);
        individualStudentTiles.setPrefColumns(1);
        individualStudentTiles.setPadding(new Insets(10,0,0,0));
        scrollStudentSummary.setContent(individualStudentTiles);
        scrollStudentSummary.setPannable(false);

        List<Student> orderedList = studentModel.getStudentList();
        Collections.sort(orderedList,new SortByAbsence());
        for(Student student: orderedList){
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
            listOfSubmissions.getItems().remove(listOfSubmissions.getSelectionModel().getSelectedItem());
            attendanceModel.confirmAttendance(att);
        }
    }

    public void declineSubmission(ActionEvent actionEvent) {
        attendanceModel.cancelSubmission(listOfSubmissions.getSelectionModel().getSelectedItem());
        listOfSubmissions.getItems().remove(listOfSubmissions.getSelectionModel().getSelectedItem());

    }
}