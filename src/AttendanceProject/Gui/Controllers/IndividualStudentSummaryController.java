package AttendanceProject.Gui.Controllers;

import AttendanceProject.Bll.AttendanceManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class IndividualStudentSummaryController implements Initializable {


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
        studentName.setText(attendanceManager.getAllStudents1().get(new Random().nextInt(10)).getName());

        Image imHead = new Image("images/faceYellow.png");
        headPicture.setImage(imHead);


        int tmp = (int) ( Math.random() * 3 + 1);
        switch (tmp) {
            case 1:
                Image imBar = new Image("images/Group 17.png");
                absenceBar.setImage(imBar);
                break;
            case 2:
                Image imBar1 = new Image("images/Group 18.png");
                absenceBar.setImage(imBar1);
                break;
            case 3:
                Image imBar2 = new Image("images/Group 19.png");
                absenceBar.setImage(imBar2);
                break;
        }


    }

    final String lexicon = "ABEHIMOSTUY";

    final java.util.Random rand = new java.util.Random();

    final Set<String> identifiers = new HashSet<String>();

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

}
