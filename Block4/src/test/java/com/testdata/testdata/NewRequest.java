package com.testdata.testdata;

import com.testdata.testdata.Models.NewRequestForm;
import com.testdata.testdata.pages.LoginPage;
import org.junit.jupiter.api.Test;

public class NewRequest extends BaseTest{

    @Test
    void createRequest() {

        LoginPage loginPage = new LoginPage(driver, wait, BASE_URL);
        loginPage.open();
        loginPage.login("john@example.com", "password123");

        NewRequestForm request = new NewRequestForm("Road damage", "Roads & Infrastructure", "High");

        header.openNewRequestsLink();

        newRequestPage.fillForm(request);

        newRequestPage.submitRequest();
    }
}