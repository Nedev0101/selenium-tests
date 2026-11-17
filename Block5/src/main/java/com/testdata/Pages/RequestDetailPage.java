package com.testdata.Pages;

import com.testdata.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RequestDetailPage extends BasePage {

    private static final By TITLE = By.xpath("...");
    private static final By REQUEST_ID = By.xpath("...");
    private static final By PRIORITY = By.xpath("...");
    private static final By STATUS = By.xpath("...");

    private static final By COMMENT_INPUT = By.xpath("...");
    private static final By ADD_COMMENT_BUTTON = By.xpath("...");
    private static final By COMMENTS = By.xpath("...");

    public RequestDetailPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String title() {
        return driver.findElement(TITLE).getText();
    }

    public String requestId() {
        return driver.findElement(REQUEST_ID).getText();
    }

    public String priority() {
        return driver.findElement(PRIORITY).getText();
    }

    public String status() {
        return driver.findElement(STATUS).getText();
    }

    public RequestDetailPage addComment(String text) {
        driver.findElement(COMMENT_INPUT).sendKeys(text);
        driver.findElement(ADD_COMMENT_BUTTON).click();
        return this;
    }

    public List<String> comments() {
        return driver.findElements(COMMENTS)
                .stream()
                .map(element -> element.getText())
                .toList();
    }
}