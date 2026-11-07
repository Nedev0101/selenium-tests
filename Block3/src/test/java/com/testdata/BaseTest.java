package com.testdata;

import my.testdata.Header;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public abstract class BaseTest {

    protected static final String BASE_URL = "http://localhost:5173";

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Header header;

    @BeforeEach
    void setUp() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--window-size=1920,1080");

        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        header = new Header(driver, wait);
    }

    @AfterEach
    void tearDown() {

        try {

            if (header != null && header.isDisplayed()) {
                header.logout();
            }

        } catch (Exception ignored) {

        } finally {

            if (driver != null) {
                driver.quit();
            }
        }
    }
}