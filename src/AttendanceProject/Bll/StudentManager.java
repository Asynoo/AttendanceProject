package AttendanceProject.Bll;

import AttendanceProject.Be.Student;
import AttendanceProject.Dal.DaoStudent;

import java.util.List;

public class StudentManager {
    DaoStudent daoStudent;

    public StudentManager(){
        daoStudent = new DaoStudent();
    }

    public List<Student> getStudents(){
        return daoStudent.getStudents();
    }


}
