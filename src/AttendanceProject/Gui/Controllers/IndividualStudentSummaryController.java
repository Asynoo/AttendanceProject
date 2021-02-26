package AttendanceProject.Gui.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class IndividualStudentSummaryController implements Initializable {


    @FXML
    private ImageView headPicture;
    @FXML
    private Label studentName;
    @FXML
    private ImageView absenceBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imHead = new Image("images/faceYellow.png");
        headPicture.setImage(imHead);

        Image imBar = new Image("images/Group 17.png");
        absenceBar.setImage(imBar);
    }

}
