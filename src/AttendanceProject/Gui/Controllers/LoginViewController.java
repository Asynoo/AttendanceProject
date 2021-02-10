package AttendanceProject.Gui.Controllers;

import AttendanceProject.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginViewController  implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ;
    }


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
        if (user.equals("admin") && pass.equals("password")) {
            Homepage();
        } else
            System.out.println("Not Poggers");
    }


    public void Homepage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Gui/Views/HomepageView.fxml"));
        Stage MovieSettingsStage = new Stage();
        MovieSettingsStage.setTitle("Attendance Checker");
        MovieSettingsStage.setResizable(false);
        MovieSettingsStage.show();


    }
}
