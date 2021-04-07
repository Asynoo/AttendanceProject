package AttendanceProject.Dal;

import java.time.LocalDate;

public class DAOAttendanceDB implements DAOAttendance{
    DataAccess da = new DataAccess();

    @Override
    public boolean getAttendance(LocalDate date) {
        return false;
    }

    @Override
    public void submitAttendance(LocalDate date, boolean attendance) {

    }

    @Override
    public void queryEditAttendance(LocalDate date, boolean attendance) {

    }

    @Override
    public void confirmEditAttendance(LocalDate date, boolean attendance) {

    }
}
