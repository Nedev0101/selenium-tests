package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyRequestsPage {

    private final WebDriver driver;

    private static final By FIRST_ROW_TITLE = By.xpath("(//*[@data-field='title'])[2]");
    private static final By FIRST_ROW_PRIORITY = By.xpath("(//*[@data-field='priority'])[2]");
    private static final By FIRST_ROW_STATUS = By.xpath("(//*[@data-field='status'])[2]");

    public String getLatestRequestTitle() {
        return driver.findElement(FIRST_ROW_TITLE).getText();
    }

    public String getLatestRequestPriority() {
        return driver.findElement(FIRST_ROW_PRIORITY).getText();
    }

    public String getLatestRequestStatus() {
        return driver.findElement(FIRST_ROW_STATUS).getText();
    }

    public MyRequestsPage(WebDriver driver) {
        this.driver = driver;
    }
}