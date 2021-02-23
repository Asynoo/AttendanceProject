package AttendanceProject.Gui.Controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class LoginViewController{


    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    public void LoginAction() throws Exception {
        String user = username.getText();
        String pass = password.getText();

        if (user.equals("student") && pass.equals("password")) {
            HomepageStudent();
        } else
            System.out.println("Not Poggers");

        if (user.equals("teacher") && pass.equals("password")) {
            HomepageTeacher();
        } else
            System.out.println("Not Poggers");
    }



    public void HomepageStudent() throws Exception {
        Stage HomepageViewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/AttendanceProject/Gui/Views/StudentHomepageView.fxml"));
        HomepageViewStage.setTitle("Student AC");
        HomepageViewStage.setScene(new Scene(root));
        HomepageViewStage.setResizable(false);
        HomepageViewStage.show();
    }

    public void HomepageTeacher() throws Exception {
        Stage HomepageViewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/AttendanceProject/Gui/Views/TeacherHomepageView.fxml"));
        HomepageViewStage.setTitle("Teacher AC");
        HomepageViewStage.setScene(new Scene(root));
        HomepageViewStage.setResizable(false);
        HomepageViewStage.show();
    }


}
