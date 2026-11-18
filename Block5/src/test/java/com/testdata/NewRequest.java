package com.testdata;

import com.testdata.Base.BaseTest;
import com.testdata.Components.Header;
import com.testdata.Models.NewRequestForm;
import com.testdata.Pages.LoginPage;
import com.testdata.Pages.newRequestPage.NewRequestPage;
import org.junit.jupiter.api.Test;

public class NewRequest extends BaseTest {

    @Test
    void createRequest() {
        LoginPage loginPage = new LoginPage(driver, wait, BASE_URL);
        loginPage.open();
        loginPage.login("john@example.com", "password123");

        NewRequestForm request = new NewRequestForm("Road damage", "Roads & Infrastructure", "High");

        Header header = new Header(driver, wait);
        header.openNewRequest();

        NewRequestPage newRequestPage = new NewRequestPage(driver, wait);
        newRequestPage.fillForm(request);
        newRequestPage.submitRequest();
    }
}