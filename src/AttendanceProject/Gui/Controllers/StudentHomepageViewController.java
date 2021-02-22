package AttendanceProject.Gui.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class StudentHomepageViewController {

    @FXML
    private JFXButton historyBtn;
    @FXML
    private ImageView historyIcon;
    @FXML
    private JFXButton statusBtn;
    @FXML
    private JFXButton statisticsBtn;

    @FXML
    private BorderPane calendarPane;
    @FXML
    private HBox chartPane;
    @FXML
    private HBox statusPane;


    public void showStatistics(ActionEvent actionEvent) {
        chartPane.setVisible(true);
        statusPane.setVisible(false);
        calendarPane.setVisible(false);
    }

    public void showStatus(ActionEvent actionEvent) {
        chartPane.setVisible(false);
        statusPane.setVisible(true);
        calendarPane.setVisible(false);
    }

    public void showHistory(ActionEvent actionEvent) {
        chartPane.setVisible(false);
        statusPane.setVisible(false);
        calendarPane.setVisible(true);
    }
}
