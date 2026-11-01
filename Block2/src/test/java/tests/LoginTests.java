package tests;

import base.BaseTest;
import components.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    private static final String CITIZEN_EMAIL = "john@example.com";
    private static final String PASSWORD = "password123";

    private LoginPage loginPage;
    private Header header;

    @BeforeEach
    void openLoginPage() {
        loginPage = new LoginPage(driver, wait, BASE_URL).open();
        header = new Header(driver, wait);
    }

    @Test
    void citizenLoginSuccessfully() {
        loginPage.login(CITIZEN_EMAIL, PASSWORD);

        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assertions.assertTrue(header.isDisplayed(), "Header isn't displayed after successful login.");
        Assertions.assertEquals("CITIZEN", header.getUserRole(), "User role is not as expected after login.");

        header.logout();
        Assertions.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button isn't displayed after logout.");
    }

    @Test
    void wrongPasswordError() {
        loginPage.login(CITIZEN_EMAIL, "wrongpassword");

        Assertions.assertFalse(loginPage.getErrorMessage().isBlank(), "Error message should be displayed for wrong password");
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"), "User is not on the login page");
    }
}