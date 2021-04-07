package AttendanceProject.Bll;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class CalendarManager {
    LocalDate calendar;

    public  CalendarManager(){
        setDateToday();
    }

    public int getCurrentDay(){
        return calendar.getDayOfMonth();
    }

    public DayOfWeek getCurrentWeekday(){
        return calendar.getDayOfWeek();
    }

    public Month getCurrentMonth(){
        return calendar.getMonth();
    }

    public int getCurrentYear(){ return calendar.getYear();}

    public String getCurrentMonthName(){
        return getCurrentMonth().toString();
    }

    public void dateToFirstWeekMonthDay(){
        calendar = calendar.minusDays(getCurrentDay());
        calendar = calendar.minusDays(getCurrentWeekday().getValue()-1);
    }

    public void cycleDayUp() {
        calendar = calendar.plusDays(1);
    }
    public void cycleDayDown(){
        calendar = calendar.minusDays(1);
    }

    public void cycleMonthUp(){
        calendar = calendar.plusMonths(1);
    }
    public void cycleMonthDown(){
        calendar = calendar.minusMonths(1);
    }

    public void setDateToday(){
        calendar = LocalDate.now(Clock.systemDefaultZone());
    }

    public LocalDate getLocalDate(){
        return calendar;
    }
}
