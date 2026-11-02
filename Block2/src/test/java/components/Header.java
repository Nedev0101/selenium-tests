package components;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {
    private static final By HEADER = By.ByXPath.xpath("//header[@class='header']");
    private static final By DASHBOARD_LINK = By.ByXPath.xpath("//a[@data-testid='cs-nav-dashboard']");
    private static final By NEW_REQUESTS_LINK = By.ByXPath.xpath("//a[@data-testid='cs-nav-new-request']");
    private static final By USER_MENU_BUTTON = By.ByXPath.xpath("//header[@class='header']");
    private static final By USER_ROLE = By.ByXPath.xpath("//header[@class='header']");
    private static final By LOGOUT = By.ByXPath.xpath("//header[@class='header']");
    private static final By MY_REQUESTS = By.xpath("//a[@data-testid='cs-nav-my-requests']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Header(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private void click(By locator) {wait.until(ExpectedConditions.elementToBeClickable(locator)).click();}

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(HEADER)).isDisplayed();
    }

    public void openNewRequestsLink() { click(NEW_REQUESTS_LINK);
    }

    public void openMyRequests() {click(MY_REQUESTS);
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
}