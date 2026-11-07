package com.testdata;

import pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;

public class NegativeDataDrivenTests extends BaseTest {

    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "Wrong password, john@example.com, wrongpassword",
            "Unknown user, unknown@example.com, password123",
            "Empty email, '', password123"
    })
    void negative_LoginTests(String scenario,
                            String email,
                            String password) {

        LoginPage loginPage =
                new LoginPage(driver, wait, BASE_URL)
                        .open();

        loginPage.login(email, password);

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                scenario + ": User should remain on login page."
        );

        Assertions.assertFalse(
                header.isDisplayed(),
                scenario + ": User should not be logged in."
        );
    }
}