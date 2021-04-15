package AttendanceProject.Dal;

import AttendanceProject.Be.Attendance;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOAttendanceDB implements DAOAttendance{
    DataAccess da = new DataAccess();

    @Override
    public List<Attendance> getAttendances() {
        ArrayList<Attendance> attendances = new ArrayList<>();
        try (Connection con = da.getConnection()) {
            String sql = "SELECT * FROM Attendance;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Id");
                LocalDate date = rs.getDate("Date").toLocalDate();
                int studentId = rs.getInt("StudentID");
                boolean presence = rs.getBoolean("Presence");
                boolean change = rs.getBoolean("Change");
                Attendance attendance = new Attendance(date, id, studentId, presence, change);
                System.out.println("Fetched attendance id: " + id);
                attendances.add(attendance);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return attendances;
    }

    @Override
    public void submitAttendance(Attendance attendance) {
        try(Connection con = da.getConnection()){
            String sql = "INSERT INTO Attendance (StudentId,Date,Presence,Change) VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, attendance.getStudentId());
            statement.setDate(2, java.sql.Date.valueOf(attendance.getDate()));
            statement.setBoolean(3, attendance.isPresent());
            statement.setBoolean(4, attendance.hasChangeRequest());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void editAttendance(Attendance attendance) {
        try(Connection con = da.getConnection()){
            String sql = "UPDATE Attendance SET [StudentID] = ?, [Date] = ?, [Presence] = ?, [Change] = ?  WHERE Id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, attendance.getStudentId());
            statement.setDate(2, java.sql.Date.valueOf(attendance.getDate()));
            statement.setBoolean(3, attendance.isPresent());
            statement.setBoolean(4, attendance.hasChangeRequest());
            statement.setInt(5, attendance.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void confirmEditAttendance(Attendance attendance) {

    }
}
