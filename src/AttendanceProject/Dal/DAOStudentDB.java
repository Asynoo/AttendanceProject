package AttendanceProject.Dal;

import AttendanceProject.Be.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOStudentDB implements DAOStudent{
    private static DataAccess dataAccess = new DataAccess();

    public List<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM Student;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("Id");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                int studyClassId = rs.getInt("ClassId");
                Student student = new Student(id, firstName, lastName, username, password, id);
                System.out.println("Fetched student id: " + id);
                students.add(student);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return students;
    }

}
