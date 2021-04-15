package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.Attendance;
import AttendanceProject.Be.Student;
import AttendanceProject.Bll.AttendanceManager;
import AttendanceProject.Gui.Models.AttendanceModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class IndividualStudentSummaryController implements Initializable {
    @FXML
    private ProgressIndicator percentagesAbsence;
    @FXML
    private ProgressBar absenceBar;
    @FXML
    private HBox backgroundFill;
    @FXML
    private ImageView headPicture;
    @FXML
    private Label studentName;

    Student student;
    private AttendanceModel attendanceModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imHead = new Image("images/faceYellow.png");
        headPicture.setImage(imHead);
    }

    public void setStudent(Student student) {
       this.student = student;
        studentName.setText(student.getFirstName());
    }

    public void setAbsenceModel(AttendanceModel attendanceModel) {
        this.attendanceModel = attendanceModel;
        int attandingDays = 0;
        int attending = 0;
        int notAttending = 0;
        List<Attendance> attendanceInfo = attendanceModel.getStudentAttendances(student);
        for (Attendance attendance: attendanceInfo) {
            attandingDays ++;
            if(attendance.isPresent()) {
                attending++;
            }else if(!attendance.isPresent()){
                notAttending ++;
            }
        }
        double percentageAbsence = (double) notAttending/(double)attandingDays;
        percentagesAbsence.setProgress(percentageAbsence);
        absenceBar.setProgress(percentageAbsence);
    }
}
