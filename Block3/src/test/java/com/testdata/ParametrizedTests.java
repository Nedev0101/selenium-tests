package com.testdata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ParametrizedTests {

    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "Citizen, john@example.com, /dashboard",
            "Clerk, clerk@example.com, /clerk",
            "Supervisor, supervisor@example.com, /supervisor",
            "Field Agent, agent@example.com, /agent",
            "Administrator, admin@example.com, /admin"
    })

    void loginWithDifferentRoles(String role, String email, String expectedPage) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.get("http://localhost:5173/login");

            driver.findElement(By.xpath("//button[contains(@aria-label,'" + role + "')]")).click();

            driver.findElement(By.xpath("//*[@id='root']/div//form/button")).click();

            wait.until(ExpectedConditions.urlContains(expectedPage));

            Assertions.assertTrue(driver.getCurrentUrl().contains(expectedPage), "User was redirected to wrong page");

        } finally {
            driver.quit();
        }
    }
}