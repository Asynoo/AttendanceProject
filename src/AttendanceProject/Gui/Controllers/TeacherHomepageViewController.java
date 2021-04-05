package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.Student;
import AttendanceProject.Bll.AttendanceManager;
import AttendanceProject.Gui.Models.StudentModel;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.Random;
import java.util.ResourceBundle;

public class TeacherHomepageViewController implements Initializable {
    public Label testLabel;
    @FXML
    private VBox individualStudentPane;
    AttendanceManager attendanceManager;
    @FXML
    private Label paneDescription;
    @FXML
    private ScrollPane scrollStudentSummary;
    @FXML
    private ScrollPane scrollClassAttendance;

    private Boolean studentsClassVisible = false;
    private Boolean studentsSummaryVisible = false;
    private final TilePane tilePaneClass = new TilePane();
    private final TilePane individualStudentTiles = new TilePane();
    private final int numberOfStudents = 19;
    StudentModel studentModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentModel = new StudentModel();
        fillStudentsClass();
        fillStudentsIndividually();
        scrollClassAttendance.setVisible(false);
        scrollStudentSummary.setVisible(false);
        individualStudentPane.setVisible(false);
        paneDescription.setText("");
    }

    public void showClassAttendance() {
        if (!studentsClassVisible){
            scrollClassAttendance.setVisible(true);
            scrollStudentSummary.setVisible(false);
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
            studentsSummaryVisible = true;
            paneDescription.setText("Student Summary");
        }else{
            scrollStudentSummary.setVisible(false);
            studentsSummaryVisible = false;
        }
    }

        private void fillStudentsClass() {
            attendanceManager = new AttendanceManager();

            studentModel.getListOfStudents();

            for (Student student: studentModel.getListOfStudents()) {
                ImageView imgView = new ImageView("images/facetry.png");
                imgView.setFitHeight(75);
                imgView.setFitWidth(75);
                Label lblContent = new Label(student.getFirstName() + student.getLastName());
                VBox vbox = new VBox();
                vbox.getChildren().add(imgView);
                vbox.getChildren().add(lblContent);

                tilePaneClass.getChildren().add(vbox);
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
        individualStudentPane.getChildren().add(studentName);

    }

    private void fillStudentsIndividually() {
        individualStudentTiles.setPrefTileWidth(375);
        individualStudentTiles.setPrefColumns(1);
        individualStudentTiles.setPadding(new Insets(10,0,0,0));
        scrollStudentSummary.setContent(individualStudentTiles);
        scrollStudentSummary.setPannable(false);

        for (int i = 0; i <numberOfStudents ; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AttendanceProject/Gui/Views/IndividualStudentSummary.fxml"));
            HBox hb = null;
            try {
                hb = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            individualStudentTiles.getChildren().add(hb);
        }
    }



}
