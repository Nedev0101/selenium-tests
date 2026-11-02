package com.testdata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPortal {

    @Test
    public void openLoginPage() {

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:5173/login");

        WebElement pageTitle = driver.findElement(By.xpath("//div//h1[text()='City Services Login']"));
        WebElement buttonText = driver.findElement(By.xpath("//div//button[text()='Login']"));

        Assert.assertEquals(pageTitle.getText(), "City Services Login", "Incorrect login page title");
        Assert.assertEquals(buttonText.getText(), "Login", "Incorrect login button text");

        driver.quit();
    }
}