package com.testdata;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;

public class LoginPortal extends BaseTest {

    @Test
    public void open_LoginPage() {

        driver.get(BASE_URL + "/login");

        WebElement pageTitle = driver.findElement(By.xpath("//div//h1[text()='City Services Login']"));

        WebElement buttonText = driver.findElement(By.xpath("//div//button[text()='Login']"));

        Assertions.assertEquals("City Services Login", pageTitle.getText(), "Incorrect login page title");

        Assertions.assertEquals("Login", buttonText.getText(), "Incorrect login button text");
    }
}