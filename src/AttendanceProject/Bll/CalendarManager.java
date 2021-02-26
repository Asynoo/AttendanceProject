package AttendanceProject.Bll;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarManager {
    Calendar calendar;

    public  CalendarManager(){
        calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"), Locale.GERMAN);
    }

    public int getCurrentDay(){
        return calendar.get(Calendar.DATE);
    }

    public int getCurrentWeekday(){
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int getCurrentMonth(){
        return calendar.get(Calendar.MONTH)+1;
    }

    public int getCurrentYear(){ return calendar.get(Calendar.YEAR);}

    public String getCurrentMonthName(){
        return switch (getCurrentMonth()) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "¯\\_(ツ)_/¯";
        };
    }

    public int getFirstWeekDay(){
        return (getCurrentDay()-7*(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)))-(calendar.get(Calendar.DAY_OF_WEEK)-2);
    }

    public void dateToFirstWeekMonthDay(){
        int daysToFirstWMD = Math.abs(getFirstWeekDay()-getCurrentDay());
        for (int i = 1; i <= daysToFirstWMD;i++) {
            cycleDayDown();
        }
    }

    public void cycleDayUp() {
        if (getCurrentDay() == calendar.getActualMaximum(Calendar.DATE)) {
            calendar.roll(Calendar.DATE, true);
            cycleMonthUp();
        }
        else {
            calendar.roll(Calendar.DATE, true);
        }
    }
    public void cycleDayDown(){
        if (getCurrentDay() == 1) {
            cycleMonthDown();
        }
        calendar.roll(Calendar.DATE, false);
    }
    public void cycleMonthUp(){
        if (getCurrentMonth() == 12) {
            calendar.roll(Calendar.MONTH, true);
            cycleYearUp();
        }
        else {
            calendar.roll(Calendar.MONTH, true);
        }
    }
    public void cycleMonthDown(){
        if (getCurrentMonth() == 1) {
            cycleYearDown();
        }
        calendar.roll(Calendar.MONTH, false);
    }
    public void cycleYearUp(){
        calendar.roll(Calendar.YEAR,true);
    }
    public void cycleYearDown(){
        calendar.roll(Calendar.YEAR,false);
    }

    public void setDateToday(){
        calendar.setTime(new Date());
    }
}
