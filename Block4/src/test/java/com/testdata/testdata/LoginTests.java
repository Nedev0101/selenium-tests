package com.testdata.testdata;

import com.testdata.testdata.Models.LoginForm;
import com.testdata.testdata.components.Header;
import com.testdata.testdata.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    void citizenLogsInSuccessfully() {
        LoginForm loginData = new LoginForm(CITIZEN_EMAIL, PASSWORD);
        loginPage.fillForm(loginData);
        loginPage.submitForm();

        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assertions.assertTrue(header.isDisplayed(), "Header is not displayed after login");
        Assertions.assertEquals("CITIZEN", header.getUserRole(), "Wrong role in the user menu");

        header.logout();
        Assertions.assertTrue(loginPage.isLoginButtonDisplayed(), "Login page is not shown after logout");
    }

    @Test
    void wrongPasswordShowsError() {
        LoginForm loginData = new LoginForm(CITIZEN_EMAIL, "wrongPassword");
        loginPage.fillForm(loginData);
        loginPage.submitForm();

        Assertions.assertFalse(loginPage.getErrorMessage().isBlank(), "Error message is empty");
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"), "User left the login page");
    }
}