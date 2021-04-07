package AttendanceProject.Gui.Models;

import AttendanceProject.Be.Student;
import AttendanceProject.Bll.StudentManager;

import java.util.List;

public class StudentModel {

    private StudentManager studentManager;
    private List<Student> studentList;

    public StudentModel(){
        this.studentManager = new StudentManager();
        studentList = studentManager.getStudents();
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
