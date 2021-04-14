package AttendanceProject.Gui.Models;

import AttendanceProject.Be.StudyClass;
import AttendanceProject.Bll.StudyClassManager;

import java.util.List;

public class StudyClassModel {

    private StudyClassManager studyClassManager;
    private List<StudyClass> listOfStudyClasses;

    public StudyClassModel(){
        this.studyClassManager = new StudyClassManager();
        listOfStudyClasses = studyClassManager.getAllStudyClasses();
    }

    public List<StudyClass> getListOfStudyClasses() {
        return listOfStudyClasses;
    }
}
