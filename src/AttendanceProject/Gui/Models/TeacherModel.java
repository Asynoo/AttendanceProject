package AttendanceProject.Gui.Models;

import AttendanceProject.Be.Student;
import AttendanceProject.Be.Teacher;
import AttendanceProject.Bll.StudentManager;
import AttendanceProject.Bll.TeacherManager;

import java.util.List;

public class TeacherModel {

    private TeacherManager teacherManager;
    private List<Teacher> listOfTeachers;

    public TeacherModel(){
        this.teacherManager = new TeacherManager();

    }

    public List<Teacher> getListOfTeachers() {
        listOfTeachers = teacherManager.getTeachers();
        return listOfTeachers;
    }
}
