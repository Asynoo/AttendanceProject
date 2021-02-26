package AttendanceProject.Dal;

import AttendanceProject.Be.Attendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOAttendance {

    public ObservableList<Attendance> getStudentData(){

        return FXCollections.observableArrayList(

                new Attendance("Marco"),
                new Attendance("Danylo"),
                new Attendance("Tomas"),
                new Attendance("Wojciech"),
                new Attendance("Oliver"),
                new Attendance("Rolf"),
                new Attendance("Jeppe"),
                new Attendance("Jakub"),
                new Attendance("Kamila"),
                new Attendance("Ivan")
        );
    }
    public ObservableList<Attendance> getStudentDataTwo(){

        return FXCollections.observableArrayList(

                new Attendance("Augustin"),
                new Attendance("Kenneth"),
                new Attendance("Ricky"),
                new Attendance("Mikkel"),
                new Attendance("Kjell"),
                new Attendance("Faustas"),
                new Attendance("Adam"),
                new Attendance("Mantas"),
                new Attendance("Darius")
        );
    }


}
