package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final By EMAIL = By.xpath("//input[@type='email']");
    private static final By PASSWORD = By.xpath("//input[@type='password']");
    private static final By LOGIN_BUTTON = By.xpath("//*[@data-testid='cs-login-submit']");
    private static final By ERROR_MESSAGE = By.xpath("//*[@data-testid='cs-login-error']");

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String url;

    public LoginPage(WebDriver driver, WebDriverWait wait, String baseUrl) {
        this.driver = driver;
        this.wait = wait;
        this.url = baseUrl + "/Login/";
    }

    public LoginPage open(){
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    public void login(String email, String password) {
        driver.findElement(EMAIL).sendKeys(email);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return error.getText();
    }

    public boolean isLoginButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON)).isDisplayed();
    }
}