package com.testdata;

import com.testdata.Base.BaseTest;
import com.testdata.Components.Header;
import com.testdata.Pages.LoginPage;
import com.testdata.Pages.MyRequestsPage;
import com.testdata.Pages.RequestDetailPage;
import com.testdata.Pages.newRequestPage.NewRequestAdditionalDetailsPage;
import com.testdata.Pages.newRequestPage.NewRequestBasicInfoPage;
import com.testdata.Pages.newRequestPage.NewRequestContactInformationPage;
import com.testdata.Pages.newRequestPage.NewRequestLocationDetailsPage;
import com.testdata.Pages.newRequestPage.NewRequestReviewAndSubmitPage;
import com.testdata.testdata.Models.AdditionalDetailsFromData;
import com.testdata.testdata.Models.BasicInfoFromData;
import com.testdata.testdata.Models.ContactInformationFromData;
import com.testdata.testdata.Models.LocationDetailsFromData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestAndComment extends BaseTest {

    private LoginPage loginPage;
    private Header header;
    private NewRequestBasicInfoPage newRequestBasicInfoPage;
    private NewRequestLocationDetailsPage newRequestLocationDetailsPage;
    private NewRequestContactInformationPage newRequestContactInformationPage;
    private NewRequestAdditionalDetailsPage newRequestAdditionalDetailsPage;
    private NewRequestReviewAndSubmitPage newRequestReviewAndSubmitPage;
    private MyRequestsPage myRequestsPage;
    private RequestDetailPage requestDetailPage;

    @BeforeEach
    void setup() {
        loginPage = new LoginPage(driver, wait, BASE_URL);
        header = new Header(driver, wait);

        newRequestBasicInfoPage = new NewRequestBasicInfoPage(driver, wait);
        newRequestLocationDetailsPage = new NewRequestLocationDetailsPage(driver, wait);
        newRequestContactInformationPage = new NewRequestContactInformationPage(driver, wait);
        newRequestAdditionalDetailsPage = new NewRequestAdditionalDetailsPage(driver, wait);
        newRequestReviewAndSubmitPage = new NewRequestReviewAndSubmitPage(driver, wait);

        myRequestsPage = new MyRequestsPage(driver, wait);
        requestDetailPage = new RequestDetailPage(driver, wait);

        loginPage.open();
        loginPage.login("john@example.com", "password123");
    }

    @Test
    void verifyRequestDetailsPage() {

        String commentText = "This traffic is unusual for this time of the day";


        header.openNewRequest();

        com.testdata.testdata.Models.BasicInfoFromData basicInfo = new BasicInfoFromData(
                "Issue Title",
                "This is a high priority request",
                "11/10/2026",
                "Roads & Infrastructure",
                "High",
                true);

        com.testdata.testdata.Models.LocationDetailsFromData locationData = new LocationDetailsFromData(
                "Main Street 1",
                "Sofia",
                "1000",
                "Near city center"
        );

        com.testdata.testdata.Models.ContactInformationFromData contactInfoData = new ContactInformationFromData(
                "EMAIL",
                "john@example.com",
                "088123",
                "123123"
        );

        com.testdata.testdata.Models.AdditionalDetailsFromData additionalDetailsData = new AdditionalDetailsFromData(true);

        newRequestBasicInfoPage.fillForm(basicInfo);
        newRequestBasicInfoPage.submitForm();

        newRequestLocationDetailsPage.fillForm(locationData);
        newRequestLocationDetailsPage.submitForm();

        newRequestContactInformationPage.fillForm(contactInfoData);
        newRequestContactInformationPage.submitForm();

        newRequestAdditionalDetailsPage.fillForm(additionalDetailsData);
        newRequestAdditionalDetailsPage.submitForm();

        newRequestReviewAndSubmitPage.submitRequest();

        header.openMyRequests();
        myRequestsPage.openLatestRequest();
        myRequestsPage.clickOnAddComment();
        myRequestsPage.addComment("This traffic is unusual for this time of the day");
        myRequestsPage.clickOnPublicComment();

        driver.navigate().refresh();

        Assertions.assertTrue(requestDetailPage.comments().contains(commentText), "Comment was not found after page refresh.");

        header.openMyRequests();

        myRequestsPage.filterHighPriority();

        for (String priority : myRequestsPage.priorities()) {
            Assertions.assertEquals("High", priority);
        }
    }
}