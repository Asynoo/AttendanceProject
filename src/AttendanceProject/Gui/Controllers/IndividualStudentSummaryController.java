package AttendanceProject.Gui.Controllers;

import AttendanceProject.Bll.AttendanceManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class IndividualStudentSummaryController implements Initializable {

    @FXML
    private HBox backgroundFill;
    @FXML
    private ImageView headPicture;
    @FXML
    private Label studentName;
    @FXML
    private ImageView absenceBar;

    AttendanceManager attendanceManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        attendanceManager = new AttendanceManager();
    }
}
