package AttendanceProject.Bll;

import AttendanceProject.Be.Student;
import AttendanceProject.Dal.DAOStudentDB;

import java.util.List;

public class StudentManager {
    AttendanceProject.Dal.DAOStudent daoStudent;

    public StudentManager(){
        daoStudent = new DAOStudentDB();
    }

    public List<Student> getStudents(){
        return daoStudent.getStudents();
    }
}
