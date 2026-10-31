package com.testdata;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoRolesLogin {


    @Test
    public void checkCitizenRole() {
        loginAndCheckRole("Citizen", "CITIZEN");
    }

    @Test
    public void checkClerkRole() {
        loginAndCheckRole("Clerk", "CLERK");
    }

    @Test
    public void checkSupervisorRole() {
        loginAndCheckRole("Supervisor", "SUPERVISOR");
    }

    @Test
    public void checkFieldAgentRole() {
        loginAndCheckRole("Field Agent", "FIELD_AGENT");
    }

    @Test
    public void checkAdministratorRole() {
        loginAndCheckRole("Administrator", "ADMIN");
    }

    public void loginAndCheckRole(String loginRole, String expectedRole) {

        //adding chrome options to test in headless mode and to avoid Google browser password manager recommendations
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("http://localhost:5173/login");

        WebElement buttonText = driver.findElement(By.xpath("//*[@id='root']/div//form/button"));

        //selecting a role
        driver.findElement(By.xpath("//button[contains(@aria-label,'" + loginRole + "')]")).click();

        //login
        buttonText.click();
        Assert.assertEquals(buttonText.getText(), "Login", "Unexpected text in Login button");

        //open the user menu
        WebElement userMenuButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='cs-user-menu-button']")));
        userMenuButton.click();

        //verify the role
        WebElement roleChip = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-testid='cs-user-menu']//span[contains(@class,'MuiChip-label')]")));

        Assert.assertEquals(roleChip.getText(), expectedRole, "Wrong demo role");

        driver.quit();
    }
}