package AttendanceProject.Gui.Controllers;

import AttendanceProject.Bll.AttendanceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Random;

public class TeacherHomepageViewController {
    AttendanceManager attendanceManager;

    @FXML
    private ScrollPane scrollPane;

    private Boolean studentsFilled = false;
    private final TilePane tilePaneClass = new TilePane();
    private final TilePane individualStudentTiles = new TilePane();
    private final int numberOfStudents = 19;


    public void showClassAttendance() {
        if(studentsFilled){
            tilePaneClass.setVisible(false);
            studentsFilled = false;
        }else{
            fillStudentsClass();
        }

    }
        

    public void showStudentSummary() throws IOException {
        fillStudentsIndividually();
    }

        private void fillStudentsClass() {
            attendanceManager = new AttendanceManager();
        if(tilePaneClass.isVisible()){
            studentsFilled = true;


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
            scrollPane.setContent(tilePaneClass);
        }else{
            tilePaneClass.setVisible(true);
            studentsFilled = true;
        }


    }

    private void fillStudentsIndividually() throws IOException {
        individualStudentTiles.setPrefTileWidth(340);
        individualStudentTiles.setPrefColumns(1);


        scrollPane.setContent(individualStudentTiles);

        for (int i = 0; i <numberOfStudents ; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AttendanceProject/Gui/Views/IndividualStudentSummary.fxml"));
            HBox hb = loader.load();
            individualStudentTiles.getChildren().add(hb);
        }



    }

}
