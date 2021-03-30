package AttendanceProject.Gui.Controllers;

import AttendanceProject.Bll.AttendanceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class TeacherHomepageViewController implements Initializable {
    AttendanceManager attendanceManager;
    @FXML
    private ScrollPane scrollStudentSummary;
    @FXML
    private ScrollPane scrollClassAttendance;

    private Boolean studentsClassVisible = false;
    private Boolean studentsSummaryVisible = false;
    private final TilePane tilePaneClass = new TilePane();
    private final TilePane individualStudentTiles = new TilePane();
    private final int numberOfStudents = 19;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillStudentsClass();
        fillStudentsIndividually();
        scrollClassAttendance.setVisible(false);
        scrollStudentSummary.setVisible(false);
    }

    public void showClassAttendance() {
        if (!studentsClassVisible){
            scrollClassAttendance.setVisible(true);
            scrollStudentSummary.setVisible(false);
            studentsClassVisible = true;
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
        }else{
            scrollStudentSummary.setVisible(false);
            studentsSummaryVisible = false;
        }
    }

        private void fillStudentsClass() {
            attendanceManager = new AttendanceManager();

            tilePaneClass.setPrefTileHeight(110);
            tilePaneClass.setPrefTileWidth(110);
            tilePaneClass.setPrefSize(351, 253);


            for (int i = 0; i < numberOfStudents; i++) {

                int tmp = (int) ( Math.random() * 2 + 1);

                if (tmp == 1){

                    ImageView imgView = new ImageView("images/facetry.png");
                    imgView.setFitHeight(75);
                    imgView.setFitWidth(75);
                    Label lblContent = new Label(attendanceManager.getAllStudents1().get(new Random().nextInt(10)).getName());
                    VBox vbox = new VBox();
                    vbox.getChildren().add(imgView);
                    vbox.getChildren().add(lblContent);

                    tilePaneClass.getChildren().add(vbox);

                }else {

                    ImageView imgView = new ImageView("images/faceRedtry.png");
                    imgView.setFitHeight(75);
                    imgView.setFitWidth(75);

                    Label lblContent = new Label(attendanceManager.getAllStudentsTwo().get(new Random().nextInt(9)).getName());

                    VBox vbox = new VBox();
                    vbox.getChildren().add(imgView);
                    vbox.getChildren().add(lblContent);

                    tilePaneClass.getChildren().add(vbox);

                }

            }
            scrollClassAttendance.setContent(tilePaneClass);
    }

    private void fillStudentsIndividually() {
        individualStudentTiles.setPrefTileWidth(340);
        individualStudentTiles.setPrefColumns(1);


        scrollStudentSummary.setContent(individualStudentTiles);

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
