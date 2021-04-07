package AttendanceProject.Bll;

import AttendanceProject.Be.Teacher;
import AttendanceProject.Dal.DAOTeacher;
import AttendanceProject.Dal.DAOTeacherDB;

import java.util.List;

public class TeacherManager {
    DAOTeacher daoTeacher;

    public TeacherManager(){
        daoTeacher = new DAOTeacherDB();
    }

    public List<Teacher> getTeachers(){
        return daoTeacher.getTeachers();
    }
}
