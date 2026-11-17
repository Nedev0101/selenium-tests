package com.testdata.Pages;

import com.testdata.testdata.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyRequestsPage extends BasePage {

    private static final By FIRST_ROW_TITLE = By.xpath("(//*[@data-field='title'])[2]");
    private static final By FIRST_ROW_PRIORITY = By.xpath("(//*[@data-field='priority'])[2]");
    private static final By FIRST_ROW_STATUS = By.xpath("(//*[@data-field='status'])[2]");
    private static final By FIRST_ROW = By.xpath("(//*[contains(@class,'MuiDataGrid-row')])[1]");
    private static final By ADD_COMMENT_BUTTON = By.xpath("//*[@data-testid='cs-request-comment-button']");
    private static final By ADD_COMMENT_FIELD = By.xpath("//*[text()='Public Comment']");
    private static final By PRIORITY_FILTER = By.xpath("//*[@aria-label='Priority']"); // dropdown-а
    private static final By FILTERED_PRIORITIES = By.xpath("//*[@data-field='title']/following-sibling::*[@data-field='priority']");

    public String getLatestRequestTitle() {
        return driver.findElement(FIRST_ROW_TITLE).getText();
    }

    public String getLatestRequestPriority() {
        return driver.findElement(FIRST_ROW_PRIORITY).getText();
    }

    public String getLatestRequestStatus() {
        return driver.findElement(FIRST_ROW_STATUS).getText();
    }

    public MyRequestsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openLatestRequest() {click(FIRST_ROW);}

    public void clickOnAddComment() {click(ADD_COMMENT_BUTTON);}

    public void addComment(String text) {driver.findElement(ADD_COMMENT_FIELD).sendKeys(text);}

    public void clickOnPublicComment() {click(ADD_COMMENT_FIELD);}

    public void filterHighPriority() {
        click(PRIORITY_FILTER);
    }

    public List<String> priorities() {
        return driver.findElements(FILTERED_PRIORITIES)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}