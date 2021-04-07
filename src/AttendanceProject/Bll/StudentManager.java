package AttendanceProject.Bll;

import AttendanceProject.Be.Student;
import AttendanceProject.Dal.DAOStudentDB;

import java.util.List;

public class StudentManager {
    DAOStudentDB DAOStudentDB;

    public StudentManager(){
        DAOStudentDB = new DAOStudentDB();
    }

    public List<Student> getStudents(){
        return DAOStudentDB.getStudents();
    }


}
