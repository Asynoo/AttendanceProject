package AttendanceProject.Gui.Models;

import AttendanceProject.Be.Student;
import AttendanceProject.Bll.StudentManager;

import java.util.List;

public class StudentModel {

    private StudentManager studentManager;
    private List<Student> listOfStudents;
    StudentModel studentModel;


    public StudentModel(){
        this.studentManager = new StudentManager();

    }

    /**
     * public StudentModel getStudentModelInstance() {
        if(studentModel == null){
            studentModel = new StudentModel();
            return studentModel;
        }else
            return studentModel;
    }
     */

    public List<Student> getListOfStudents() {
        listOfStudents = studentManager.getStudents();
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }
}
