package AttendanceProject.Dal;

import AttendanceProject.Be.StudyClass;
import AttendanceProject.Be.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOStudyClassDB implements DAOStudyClass{

    private static DataAccess dataAccess = new DataAccess();

    @Override
    public List<StudyClass> getStudyClasses() {
        ArrayList<StudyClass> studyClasses = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM Class;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("Id");
                String studyClassName = rs.getString("ClassName");
                StudyClass studyClass = new StudyClass(studyClassName);
                studyClasses.add(studyClass);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return studyClasses;
    }
}
