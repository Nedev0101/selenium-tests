package com.testdata.Pages.newRequestPage;

import com.testdata.testdata.Models.AdditionalDetailsFromData;
import com.testdata.testdata.pages.FormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewRequestAdditionalDetailsPage extends FormPage<AdditionalDetailsFromData> {

    private static final By TERMS_BUTTON = By.xpath("//*[@data-testid='cs-new-request-agree-terms']");
    WebDriverWait wait;

    public NewRequestAdditionalDetailsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public void fillForm(AdditionalDetailsFromData formData) {

        if (formData.shouldAcceptTerms())
            acceptTerms();
    }

    @Override
    public void submitForm() {
        click(NEXT_BUTTON);
    }

    public NewRequestAdditionalDetailsPage acceptTerms() {
        click(TERMS_BUTTON);
        return this;
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}