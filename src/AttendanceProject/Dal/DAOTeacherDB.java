package AttendanceProject.Dal;

import AttendanceProject.Be.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOTeacherDB implements DAOTeacher{

    private static DataAccess dataAccess = new DataAccess();

    public List<Teacher> getTeachers(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM Teacher;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("Id");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String credentials = rs.getString("Credentials");
                Teacher teacher = new Teacher(id, firstName, lastName, username, password, credentials);
                System.out.println("Fetched teacher id: " + id);
                teachers.add(teacher);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return teachers;
    }

}
