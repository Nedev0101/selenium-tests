package com.testdata;

import pages.LoginPage;
import components.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class NegativeDataDrivenTests {

    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "Wrong password, john@example.com, wrongpassword",
            "Unknown user, unknown@example.com, password123",
            "Empty email, '', password123"
    })
    void negativeLoginTests(String scenario,
                            String email,
                            String password) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("http://localhost:5173/login");

        LoginPage loginPage =
                new LoginPage(driver, wait, "http://localhost:5173");

        Header header =
                new Header(driver, wait);

        try {

            loginPage.login(email, password);

            Assertions.assertTrue(
                    driver.getCurrentUrl().contains("/login"),
                    scenario + ": User should remain on login page."
            );

            Assertions.assertFalse(
                    header.isDisplayed(),
                    scenario + ": User should not be logged in."
            );

        } finally {
            driver.quit();
        }
    }
}