package attendanceTest;

import AttendanceProject.Gui.Controllers.IndividualStudentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IndividualStudentControllerTest {

    @Test
    void calculatePercentageAttending() {
        // Arrange
        IndividualStudentController controller = new IndividualStudentController();
        // Act
        int actualValue = (int)controller.calculatePercentageAttending(20,60);
        int expectedValue = 33;
        // Assert
        Assertions.assertEquals(expectedValue,actualValue);

        //Act+Assert
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.calculatePercentageAttending(10,0));

        //Act
        String actualMessage = ex.getMessage();
        String expectedMessage = "Total can't be zero";

        //Extra assert
        Assertions.assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void calculatePercentageNotAttending() {
        // Arrange
        IndividualStudentController controller = new IndividualStudentController();
        // Act
        int actualValue = (int)controller.calculatePercentageAttending(10,40);
        int expectedValue = 25;
        // Assert
        Assertions.assertEquals(expectedValue,actualValue);

        //Act+Assert
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.calculatePercentageAttending(10,0));

        //Act
        String actualMessage = ex.getMessage();
        String expectedMessage = "Total can't be zero";

        //Extra assert
        Assertions.assertEquals(expectedMessage,actualMessage);
    }
}