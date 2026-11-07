package com.testdata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ParametrizedTests extends BaseTest {

    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "Citizen, john@example.com, /dashboard",
            "Clerk, clerk@example.com, /clerk",
            "Supervisor, supervisor@example.com, /supervisor",
            "Field Agent, agent@example.com, /agent",
            "Administrator, admin@example.com, /admin"})

    void login_WithDifferentRoles(String role, String email, String expectedPage) {

        driver.get(BASE_URL + "/login");

        driver.findElement(By.xpath("//button[contains(@aria-label,'" + role + "')]")).click();

        driver.findElement(By.xpath("//*[@id='root']/div//form/button")).click();

        wait.until(ExpectedConditions.urlContains(expectedPage));

        Assertions.assertTrue(driver.getCurrentUrl().contains(expectedPage), "User was redirected to wrong page");
    }
}