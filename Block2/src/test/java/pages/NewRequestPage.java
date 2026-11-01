package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewRequestPage {

    private static final By REQUEST_TITLE_FIELD = By.xpath("//*[@name='title']");
    private static final By DESCRIPTION = By.xpath("//*[@name='description']");
    private static final By DATE_OF_REQUEST = By.xpath("//*[@name='dateOfRequest']");
    private static final By CATEGORY_DROPDOWN = By.xpath("//*[@id='mui-component-select-category']");
    private static final By CATEGORY_ROADS = By.xpath("//li[@data-value='roads-and-infrastructure']");
    private static final By HIGH_PRIORITY = By.xpath("//span[normalize-space()='High']");
    private static final By NEXT_BUTTON = By.xpath("//*[@data-testid='cs-new-request-wizard-next']");
    private static final By CANCEL_BUTTON = By.xpath("//*[@data-testid='cs-new-request-wizard-cancel']");
    private static final By STREET_ADDRESS = By.xpath("//*[@name='streetAddress']");
    private static final By CITY = By.xpath("//*[@name='city']");
    private static final By POSTAL_CODE = By.xpath("//*[@name='postalCode']");
    private static final By LOCATION = By.xpath("//*[@name='locationText']");
    private static final By CONTACT_METHOD = By.xpath("//*[@id='mui-component-select-contactMethod']");
    private static final By EMAIL_METHOD = By.xpath("//li[@data-value='EMAIL']");
    private static final By EMAIL_FIELD = By.xpath("//*[@name='email']");
    private static final By PHONE_FIELD = By.xpath("//*[@name='phone']");
    private static final By TERMS_BUTTON = By.xpath("//*[@data-testid='cs-new-request-agree-terms']");
    private static final By SUBMIT_REQUEST = By.xpath("//*[@data-testid='cs-new-request-wizard-submit']");


    private final WebDriverWait wait;
    private final WebDriver driver;

    public NewRequestPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private void click(By locator) {wait.until(ExpectedConditions.elementToBeClickable(locator)).click();}

    public NewRequestPage inputRequestTitle(String title) {
        driver.findElement(REQUEST_TITLE_FIELD).sendKeys(title);
        return this;
    }

    public NewRequestPage inputDescription(String description) {
        driver.findElement(DESCRIPTION).sendKeys(description);
        return this;
    }

    public NewRequestPage inputDate() {
        driver.findElement(DATE_OF_REQUEST).sendKeys();
        return this;
    }

    public NewRequestPage inputDate(String date) {
        driver.findElement(DATE_OF_REQUEST).sendKeys(date);
        return this;
    }

    public NewRequestPage selectCategory() {
        click(CATEGORY_DROPDOWN);
        click(CATEGORY_ROADS);
        return this;
    }

    public NewRequestPage selectPriority() {
        click(HIGH_PRIORITY);
        return this;
    }

    public NewRequestPage clickOnNext() {
        click(NEXT_BUTTON);
        return this;
    }

    public NewRequestPage inputStreetAddress(String address) {
        driver.findElement(STREET_ADDRESS).sendKeys(address);
        return this;
    }

    public NewRequestPage inputCity(String city) {
        driver.findElement(CITY).sendKeys(city);
        return this;
    }

    public NewRequestPage inputPostalCode(String postalCode) {
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        return this;
    }

    public NewRequestPage inputLocationDescription(String location) {
        driver.findElement(LOCATION).sendKeys(location);
        return this;
    }

    public NewRequestPage selectContactMethod(String method) {
        driver.findElement(CONTACT_METHOD).click();
        driver.findElement(EMAIL_METHOD).click();
        return this;
    }

    public NewRequestPage inputEmail(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        return this;
    }

    public NewRequestPage inputPhone(String phone) {
        driver.findElement(PHONE_FIELD).sendKeys(phone);
        return this;
    }

    public NewRequestPage acceptTerms() {
        click(TERMS_BUTTON);
        return this;
    }

    public NewRequestPage submitRequest() {
        click(SUBMIT_REQUEST);
        return this;
    }
}