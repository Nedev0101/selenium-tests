package com.testdata.Pages.newRequestPage;

import com.testdata.testdata.Models.LocationDetailsFromData;
import com.testdata.testdata.pages.FormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewRequestLocationDetailsPage extends FormPage<LocationDetailsFromData> {

    private static final By STREET_ADDRESS = By.xpath("//*[@name='streetAddress']");
    private static final By CITY = By.xpath("//*[@name='city']");
    private static final By POSTAL_CODE = By.xpath("//*[@name='postalCode']");
    private static final By LOCATION = By.xpath("//*[@name='locationText']");

    public NewRequestLocationDetailsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public void fillForm(LocationDetailsFromData formData) {
        inputStreetAddress(formData.address());
        inputCity(formData.city());
        inputPostalCode(formData.postalCode());
        inputLocationDescription(formData.locationDescription());
    }

    @Override
    public void submitForm() {
        click(NEXT_BUTTON);

    }

    public NewRequestLocationDetailsPage inputStreetAddress(String address) {
        driver.findElement(STREET_ADDRESS).sendKeys(address);
        return this;
    }

    public NewRequestLocationDetailsPage inputCity(String city) {
        driver.findElement(CITY).sendKeys(city);
        return this;
    }

    public NewRequestLocationDetailsPage inputPostalCode(String postalCode) {
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        return this;
    }

    public NewRequestLocationDetailsPage inputLocationDescription(String location) {
        driver.findElement(LOCATION).sendKeys(location);
        return this;
    }
}