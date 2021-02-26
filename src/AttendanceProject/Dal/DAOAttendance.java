package AttendanceProject.Dal;

import AttendanceProject.Be.Attendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class DAOAttendance {

    public ObservableList<Attendance> getStudentData(){
        Random r = new Random();

        ObservableList<Attendance> studentData1 = FXCollections.observableArrayList(

                new Attendance("Marco", r.nextInt(10)),
                new Attendance("Danylo", r.nextInt(10)),
                new Attendance("Tomas",  r.nextInt(10)),
                new Attendance("Wojciech", r.nextInt(10)),
                new Attendance("Oliver",  r.nextInt(10)),
                new Attendance("Rolf",  r.nextInt(10)),
                new Attendance("Jeppe",  r.nextInt(10)),
                new Attendance("Jakub",  r.nextInt(10)),
                new Attendance("Kamila",  r.nextInt(10)),
                new Attendance("Ivan",  r.nextInt(10))
        );
        return studentData1;
    }
    public ObservableList<Attendance> getStudentDataTwo(){
        Random r = new Random();

        ObservableList<Attendance> studentDataTwo = FXCollections.observableArrayList(

                new Attendance("Augustin",  r.nextInt(10)),
                new Attendance("Kenneth",  r.nextInt(10)),
                new Attendance("Ricky",  r.nextInt(10)),
                new Attendance("Mikkel",  r.nextInt(10)),
                new Attendance("Kjell",  r.nextInt(10)),
                new Attendance("Faustas",  r.nextInt(10)),
                new Attendance("Adam",  r.nextInt(10)),
                new Attendance("Mantas",  r.nextInt(10)),
                new Attendance("Darius",  r.nextInt(10))
        );
        return studentDataTwo;
    }


}
