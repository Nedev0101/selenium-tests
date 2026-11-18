package com.testdata.testdata.pages.newRequestPage;

import com.testdata.testdata.Models.ReviewAndSubmitFromData;
import com.testdata.testdata.pages.FormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewRequestReviewAndSubmitPage extends FormPage<ReviewAndSubmitFromData> {

    public NewRequestReviewAndSubmitPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public void fillForm(ReviewAndSubmitFromData data) {

    }

    @Override
    public void submitForm() {

    }

    private static final By SUBMIT_REQUEST = By.xpath("//*[@data-testid='cs-new-request-wizard-submit']");

    public NewRequestReviewAndSubmitPage submitRequest() {
        click(SUBMIT_REQUEST);
        return this;
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}