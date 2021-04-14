package AttendanceProject.Bll;

import AttendanceProject.Be.StudyClass;
import AttendanceProject.Dal.DAOStudyClass;
import AttendanceProject.Dal.DAOStudyClassDB;

import java.util.List;

public class StudyClassManager {

    DAOStudyClass daoStudyClass;
    
    public StudyClassManager(){
        this.daoStudyClass = new DAOStudyClassDB();
    }
    public List<StudyClass> getAllStudyClasses(){
        return daoStudyClass.getStudyClasses();
    } 
}
