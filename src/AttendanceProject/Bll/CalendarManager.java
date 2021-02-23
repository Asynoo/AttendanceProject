package AttendanceProject.Bll;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarManager {
    Calendar calendar;

    public  CalendarManager(){
        calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"), Locale.FRANCE);
    }

    public int getCurrentDate(){
        return calendar.get(Calendar.DATE);
    }

    public int getCurrentMonth(){
        return calendar.get(Calendar.MONTH)+1;
    }

    public String getCurrentMonthName(){
        switch (getCurrentMonth()){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "¯\\_(ツ)_/¯";
        }
    }
}
