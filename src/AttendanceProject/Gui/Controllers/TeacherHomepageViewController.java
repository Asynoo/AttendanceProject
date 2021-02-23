package AttendanceProject.Gui.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class TeacherHomepageViewController {
    @FXML
    public TilePane classSummary;
    
    private Boolean studentsFilled = false;

    public void showClassAttendance(ActionEvent actionEvent) {

        if (!studentsFilled){
            fillStudents();
        }

        if(!classSummary.isVisible()){

            classSummary.setVisible(true);
        }else {
            classSummary.setVisible(false);
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


    private void fillStudents() {

        studentsFilled = true;

        for (int i = 0; i < 30; i++) {

            int tmp = (int) ( Math.random() * 2 + 1);

            //System.out.println(tmp);

            if (tmp == 1){
                File file = new File("src/AttendanceProject/resources/images/facetry.png");
                Image img = new Image(file.toURI().toString());
                ImageView imgView = new ImageView();
                imgView.setImage(img);
                imgView.setFitHeight(75);
                imgView.setFitWidth(75);
                Label lblContent = new Label(randomIdentifier());

                VBox vbox = new VBox();
                vbox.getChildren().add(imgView);
                vbox.getChildren().add(lblContent);

                classSummary.getChildren().add(vbox);
            }else {
                File file = new File("src/AttendanceProject/resources/images/faceRedtry.png");
                Image img = new Image(file.toURI().toString());
                ImageView imgView = new ImageView();
                imgView.setImage(img);
                imgView.setFitHeight(75);
                imgView.setFitWidth(75);

                Label lblContent = new Label(randomIdentifier());

                VBox vbox = new VBox();
                vbox.getChildren().add(imgView);
                vbox.getChildren().add(lblContent);

                classSummary.getChildren().add(vbox);
            }


        }
    }
}
