package com.testdata.testdata.pages.newRequestPage;

import com.testdata.testdata.Models.BasicInfoFromData;
import com.testdata.testdata.pages.FormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewRequestBasicInfoPage extends FormPage<BasicInfoFromData> {
    private static final By REQUEST_TITLE_FIELD = By.xpath("//*[@name='title']");
    private static final By DESCRIPTION = By.xpath("//*[@name='description']");
    private static final By DATE_OF_REQUEST = By.xpath("//*[@name='dateOfRequest']");
    private static final By CATEGORY_DROPDOWN = By.xpath("//*[@id='mui-component-select-category']");
    private static final By OPEN_DROPDOWN_LIST = By.cssSelector("ul[role='listbox']");
    private static final By CATEGORY_ROADS = By.xpath("//li[@data-value='roads-and-infrastructure']");
    private static final By HIGH_PRIORITY = By.xpath("//span[normalize-space()='High']");
    private static final By EMERGENCY_BUTTON = By.xpath("//*[@data-testid='cs-new-request-emergency']");

    public NewRequestBasicInfoPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
    }

    public NewRequestBasicInfoPage inputRequestTitle(String title) {
        driver.findElement(REQUEST_TITLE_FIELD).sendKeys(title);
        return this;
    }

    public NewRequestBasicInfoPage inputDescription(String description) {
        driver.findElement(DESCRIPTION).sendKeys(description);
        return this;
    }

    public NewRequestBasicInfoPage inputDate(String date) {
        driver.findElement(DATE_OF_REQUEST).sendKeys(date);
        return this;
    }
    public NewRequestBasicInfoPage clickEmergency() {
        click(EMERGENCY_BUTTON);
        return this;
    }

    public NewRequestBasicInfoPage selectCategory(String category) {
        driver.findElement(CATEGORY_DROPDOWN).click();
        // MUI Select is not a native <select>: the options are <li role="option"> in a popup list.
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@role='option' and normalize-space()='" + category + "']"))).click();
        // Wait until the dropdown list is closed, otherwise its fading backdrop swallows the next click.
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OPEN_DROPDOWN_LIST));
        return this;
    }

    public NewRequestBasicInfoPage selectPriority(String priority) {
        driver.findElement(By.xpath("//div[@data-testid='cs-new-request-priority']//label[.//input[@value='"
                + priority.toUpperCase() + "']]")).click();
        return this;
    }

    @Override
    public void fillForm(BasicInfoFromData formData) {
        inputRequestTitle(formData.title());
        inputDescription(formData.description());
        inputDate(formData.inputDate());
        selectCategory(formData.category());
        selectPriority(formData.priority());
        if (formData.isEmergency()) {
            clickEmergency();
        }
    }

    @Override
    public void submitForm() {
        click(NEXT_BUTTON);
    }
}