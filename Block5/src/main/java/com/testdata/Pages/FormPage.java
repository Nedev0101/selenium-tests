package com.testdata.Pages;

import com.testdata.testdata.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class FormPage<TForm> extends BasePage {
    protected WebDriverWait wait;
    protected WebDriver driver;

    protected FormPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
//        super(driver, wait);
    }

    public static final By NEXT_BUTTON = By.xpath("//*[@data-testid='cs-new-request-wizard-next']");
    protected static final By CANCEL_BUTTON = By.xpath("//*[@data-testid='cs-new-request-wizard-cancel']");

    public FormPage() {
        super();
    }

    public abstract void fillForm(TForm formData);
    public abstract void submitForm();
}