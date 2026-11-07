package com.testdata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RolesLogin extends BaseTest {

    @Test
    void check_CitizenRole() {
        loginAndCheckRole("Citizen", "CITIZEN");
    }

    @Test
    void check_ClerkRole() {
        loginAndCheckRole("Clerk", "CLERK");
    }

    @Test
    void check_SupervisorRole() {
        loginAndCheckRole("Supervisor", "SUPERVISOR");
    }

    @Test
    void check_FieldAgentRole() {
        loginAndCheckRole("Field Agent", "FIELD_AGENT");
    }

    @Test
    void check_AdministratorRole() {
        loginAndCheckRole("Administrator", "ADMIN");
    }

    private void loginAndCheckRole(String loginRole, String expectedRole) {

        driver.get(BASE_URL + "/login");
        WebElement buttonText = driver.findElement(By.xpath("//*[@id='root']/div//form/button"));

        driver.findElement(By.xpath("//button[contains(@aria-label,'" + loginRole + "')]")).click();

        Assertions.assertEquals("Login", buttonText.getText(), "Unexpected text in Login button");

        buttonText.click();

        WebElement userMenuButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='cs-user-menu-button']")));

        userMenuButton.click();

        WebElement roleChip = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-testid='cs-user-menu']//span[contains(@class,'MuiChip-label')]")));

        Assertions.assertEquals(expectedRole, roleChip.getText(), "Wrong demo role");
    }
}