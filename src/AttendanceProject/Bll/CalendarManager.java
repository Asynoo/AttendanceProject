package AttendanceProject.Bll;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarManager {
    Calendar calendar;

    public  CalendarManager(){
        calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"), Locale.GERMAN);
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

    public int getFirstWeekDay(){
        return (getCurrentDate()-7*(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)-1))-(calendar.get(Calendar.DAY_OF_WEEK)-2);
    }

    public void dateToFirstWeekMonthDay(){
        int daysToFirstWMD = Math.abs(getFirstWeekDay()-getCurrentDate());
        for (int i = 1; i <= daysToFirstWMD;i++) {
            cycleDayDown();
        }
    }

    public void cycleDayUp() {
        if (getCurrentDate() == calendar.getActualMaximum(Calendar.DATE)) {
            calendar.roll(Calendar.DATE, true);
            calendar.roll(Calendar.MONTH, true);
        }
        else {
            calendar.roll(Calendar.DATE, true);
        }
    }
    public void cycleDayDown(){
        if (getCurrentDate() == 1) {
            calendar.roll(Calendar.MONTH, false);
        }
        calendar.roll(Calendar.DATE, false);
    }
    public void cycleMonthUp(){
        calendar.roll(Calendar.MONTH, true);
    }
    public void cycleMonthDown(){
        calendar.roll(Calendar.MONTH, false);
    }
}
