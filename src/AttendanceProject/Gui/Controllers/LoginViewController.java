package AttendanceProject.Gui.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginViewController{


    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    public void LoginAction(javafx.event.ActionEvent actionEvent) throws Exception {
        String user = username.getText();
        String pass = password.getText();
        if (user.equals("admin") && pass.equals("password")) {
            Homepage();
        } else
            System.out.println("Not Poggers");
    }



    public void Homepage() throws Exception {
        Stage HomepageViewStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/AttendanceProject/Gui/Views/HomepageView.FXML"));
        HomepageViewStage.setTitle("Attendance Checker");
        HomepageViewStage.setScene(new Scene(root));
        HomepageViewStage.setResizable(false);
        HomepageViewStage.show();
    }

}
