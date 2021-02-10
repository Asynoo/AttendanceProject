package AttendanceProject.Gui.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginViewController {


    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    public void LoginAction(javafx.event.ActionEvent actionEvent) throws IOException {
        String user = username.getText();
        String pass = password.getText();
        if(user.equals("admin")&&pass.equals("password"))
        {
            System.out.println("Poggers");
        }else
            System.out.println("Not Poggers");
    }

    public void Homepage(Stage homepageView) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/Views/HomepageView.fxml"));
        homepageView.setTitle("Attendance Checker");
        homepageView.setScene(new Scene(root));
        homepageView.setResizable(false);
        homepageView.show();
    }

}
