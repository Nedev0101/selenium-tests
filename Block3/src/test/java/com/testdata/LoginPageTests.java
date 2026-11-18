package com.testdata;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPageTests extends BaseTest {

    @Test
    void passwordField_ShouldBeMasked() {

        driver.get(BASE_URL + "/login");

        WebElement passwordField = driver.findElement(By.xpath("//*[@data-testid='cs-login-password']"));

        Assertions.assertEquals("password", passwordField.getAttribute("type"), "Password field should be masked");
    }

    @Test
    void email_ShouldRemainAfterFailedLogin() {

        driver.get(BASE_URL + "/login");

        String email = "john@example.com";

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']"))).sendKeys(email);

        driver.findElement(By.xpath("//*[@data-testid='cs-login-password']//input")).sendKeys("wrongpassword");

        driver.findElement(By.xpath("//*[@data-testid='cs-login-submit']")).click();

        String actualEmail = driver.findElement(By.xpath("//input[@type='email']")).getAttribute("value");

        Assertions.assertEquals(email, actualEmail, "Email should remain after unsuccessful login");
    }
}