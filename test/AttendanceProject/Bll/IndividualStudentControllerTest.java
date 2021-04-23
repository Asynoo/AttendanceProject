package AttendanceProject.Bll;

import AttendanceProject.Gui.Controllers.IndividualStudentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IndividualStudentControllerTest {

    @DisplayName("Percentage calculator")
    @Test
    void calculatePercentage() {
        // Arrange
        IndividualStudentController controller = new IndividualStudentController();
        // Act
        int actualValue = (int) controller.calculatePercentage(20, 60);
        int expectedValue = 33;

        //Act+Assert
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> controller.calculatePercentage(10, 0));

        //Act
        String actualMessage = ex.getMessage();
        String expectedMessage = "Total can't be zero";

        //Extra assert
        Assertions.assertAll(() -> {
            Assertions.assertEquals(expectedMessage, actualMessage);
            Assertions.assertEquals(expectedValue, actualValue);
        });
    }
}