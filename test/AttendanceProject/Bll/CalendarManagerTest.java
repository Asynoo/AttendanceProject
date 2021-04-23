package AttendanceProject.Bll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

class CalendarManagerTest {

    @DisplayName("Cycling days downwards")
    @Test
    void cycleDayUp() {
        //Arrange
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.setLocalDate(LocalDate.of( 2021,4,10));
        //Act
        calendarManager.cycleDayUp();
        LocalDate actualDate = calendarManager.getLocalDate();
        LocalDate expectedDate = LocalDate.of(2021,4,11);
        //Assert
        Assertions.assertEquals(expectedDate,actualDate);
    }

    @DisplayName("Cycling days upwards")
    @Test
    void cycleDayDown() {
        //Arrange
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.setLocalDate(LocalDate.of( 2021,4,10));
        //Act
        calendarManager.cycleDayDown();
        LocalDate actualDate = calendarManager.getLocalDate();
        LocalDate expectedDate = LocalDate.of(2021,4,9);
        //Assert
        Assertions.assertEquals(expectedDate,actualDate);
    }

    @DisplayName("Cycling months upwards")
    @Test
    void cycleMonthUp() {
        //Arrange
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.setLocalDate(LocalDate.of( 2021,4,10));
        //Act
        calendarManager.cycleMonthUp();
        LocalDate actualDate = calendarManager.getLocalDate();
        LocalDate expectedDate = LocalDate.of(2021,5,10);
        //Assert
        Assertions.assertEquals(expectedDate,actualDate);
    }

    @DisplayName("Cycling months downwards")
    @Test
    void cycleMonthDown() {
        //Arrange
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.setLocalDate(LocalDate.of( 2021,4,10));
        //Act
        calendarManager.cycleMonthDown();
        LocalDate actualDate = calendarManager.getLocalDate();
        LocalDate expectedDate = LocalDate.of(2021,3,10);
        //Assert
        Assertions.assertEquals(expectedDate,actualDate);
    }

    @DisplayName("Setting the calendar to the first day of the first week of the month")
    @Test
    void dateToFirstWeekMonthDay() {
        //Arrange
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.setLocalDate(LocalDate.of( 2021,1,10));
        //Act
        calendarManager.dateToFirstWeekMonthDay();
        LocalDate actualDate = calendarManager.getLocalDate();
        LocalDate expectedDate = LocalDate.of(2020,12,28);
        DayOfWeek expectedDay = DayOfWeek.MONDAY;
        DayOfWeek actualDay = calendarManager.getCurrentWeekday();
        //Assert
        Assertions.assertAll(() -> {
            Assertions.assertEquals(expectedDate,actualDate);
            Assertions.assertEquals(expectedDay,actualDay);
        });

    }
}