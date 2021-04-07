package AttendanceProject.Gui.Models;

import AttendanceProject.Be.Teacher;
import AttendanceProject.Bll.TeacherManager;

import java.util.List;

public class TeacherModel {

    private TeacherManager teacherManager;
    private List<Teacher> teacherList;

    public TeacherModel(){
        this.teacherManager = new TeacherManager();
        teacherList = teacherManager.getTeachers();
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }
}
