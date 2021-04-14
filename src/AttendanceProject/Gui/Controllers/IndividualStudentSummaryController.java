package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.Student;
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

    Student student;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentName.setText("Name Name");
        Image imHead = new Image("images/faceYellow.png");
        headPicture.setImage(imHead);
        int tmp = (int) ( Math.random() * 3 + 1);
        switch (tmp) {
            case 1 -> {
                Image imBar = new Image("images/Group 17.png");
                absenceBar.setImage(imBar);
            }
            case 2 -> {
                Image imBar1 = new Image("images/Group 18.png");
                absenceBar.setImage(imBar1);
            }
            case 3 -> {
                Image imBar2 = new Image("images/Group 19.png");
                absenceBar.setImage(imBar2);
            }
        }
    }

    public void setStudent(Student student) {
       this.student = student;
    }
}
