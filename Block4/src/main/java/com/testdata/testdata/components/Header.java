package com.testdata.testdata.components;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {
    private static final By HEADER = By.cssSelector("[data-testid='cs-app-header']");
    private static final By DASHBOARD_LINK = By.cssSelector("[data-testid='cs-nav-dashboard']");
    private static final By RESOLVED_CASES_LINK = By.cssSelector("[data-testid='cs-nav-resolved-cases']");
    private static final By RANKLIST_LINK = By.cssSelector("[data-testid='cs-nav-ranklist']");
    private static final By MY_REQUESTS_LINK = By.cssSelector("[data-testid='cs-nav-my-requests']");
    private static final By NEW_REQUEST_LINK = By.cssSelector("[data-testid='cs-nav-new-request']");
    private static final By USER_MENU_BUTTON = By.cssSelector("[data-testid='cs-user-menu-button']");
    private static final By USER_ROLE = By.cssSelector("[data-testid='cs-user-menu'] .MuiChip-label");
    private static final By LOGOUT = By.cssSelector("[data-testid='cs-user-menu-logout']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Header(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(HEADER)).isDisplayed();
    }

    public void openDashboard() {
        click(DASHBOARD_LINK);
    }

    public void openResolvedCases() {
        click(RESOLVED_CASES_LINK);
    }

    public void openRanklist() {
        click(RANKLIST_LINK);
    }

    public void openMyRequests() {
        click(MY_REQUESTS_LINK);
    }

    public void openNewRequest() {
        click(NEW_REQUEST_LINK);
    }

    public String getUserRole() {
        click(USER_MENU_BUTTON);
        String role = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_ROLE)).getText();
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(USER_ROLE));
        return role;
    }

    public void logout() {
        click(USER_MENU_BUTTON);
        click(LOGOUT);
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
