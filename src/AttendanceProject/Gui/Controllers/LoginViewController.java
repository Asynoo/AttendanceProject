package AttendanceProject.Gui.Controllers;

import AttendanceProject.Be.Student;
import AttendanceProject.Be.Teacher;
import AttendanceProject.Gui.Models.AttendanceModel;
import AttendanceProject.Gui.Models.StudentModel;
import AttendanceProject.Gui.Models.StudyClassModel;
import AttendanceProject.Gui.Models.TeacherModel;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class LoginViewController{
    private StudentModel studentModel;
    private TeacherModel teacherModel;
    private AttendanceModel attendanceModel;
    private StudyClassModel studyClassModel;

    private List<Student> students;
    private List<Teacher> teachers;

    @FXML
    private javafx.scene.control.Button login;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    @FXML
    public void LoginAction() throws Exception {
        String user = username.getText();
        String pass = password.getText();

        for (Student s: students) {
            if(s.getUsername().equals(user) && s.getPassword().equals(pass)){
                HomepageStudent(s);
                closeButtonAction();
            }
        }

        for (Teacher t: teachers) {
            if(t.getUsername().equals(user) && t.getPassword().equals(pass)){
                HomepageTeacher(t);
                closeButtonAction();
            }
        }
    }

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
    }

    public void initialize(){
        attendanceModel = new AttendanceModel();
        studentModel = new StudentModel();
        teacherModel = new TeacherModel();
        studyClassModel = new StudyClassModel();
        students = studentModel.getStudentList();
        teachers = teacherModel.getTeacherList();
    }

    public void HomepageStudent(Student student) throws Exception {
        Stage HomepageViewStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AttendanceProject/Gui/Views/StudentHomepageView.fxml"));
        Parent root = loader.load();
        ((StudentHomepageViewController)loader.getController()).setUser(student);
        ((StudentHomepageViewController)loader.getController()).setAttendanceModel(attendanceModel);
        HomepageViewStage.setTitle(student.getFirstName() + " " + student.getLastName());
        HomepageViewStage.setScene(new Scene(root));
        HomepageViewStage.setResizable(false);
        HomepageViewStage.show();
    }

    public void HomepageTeacher(Teacher teacher) throws Exception {
        Stage HomepageViewStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AttendanceProject/Gui/Views/TeacherHomepageView.fxml"));
        Parent root = loader.load();
        ((TeacherHomepageViewController)loader.getController()).setStudentModel(studentModel);
        ((TeacherHomepageViewController)loader.getController()).setAttendanceModel(attendanceModel);
        ((TeacherHomepageViewController)loader.getController()).setStudyClassModel(studyClassModel);

        ((TeacherHomepageViewController)loader.getController()).setUser(teacher);
        HomepageViewStage.setTitle(teacher.getFirstName() + " " + teacher.getLastName());
        HomepageViewStage.setScene(new Scene(root));
        HomepageViewStage.setResizable(true);
        HomepageViewStage.show();
    }


}
