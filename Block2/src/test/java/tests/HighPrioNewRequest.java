package tests;

import base.BaseTest;
import components.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.MyRequestsPage;
import pages.NewRequestPage;

public class HighPrioNewRequest extends BaseTest {

    private LoginPage loginPage;
    private Header header;
    private NewRequestPage newRequestPage;
    private MyRequestsPage myRequestsPage;

    private static final String EMAIL = "john@example.com";
    private static final String PASSWORD = "password123";

    @BeforeEach
    void setupTest() {

        loginPage = new LoginPage(driver, wait, BASE_URL).open();
        header = new Header(driver, wait);
        newRequestPage = new NewRequestPage(driver, wait);
        myRequestsPage = new MyRequestsPage(driver);

        loginPage.login(EMAIL, PASSWORD);
    }

    @Test
    void createHighPriorityRequest() {

        String title = "Road high priority request";

        header.openNewRequestsLink();

        newRequestPage
                .inputRequestTitle(title)
                .inputDescription("This is a high priority request for something happened on the road")
                .inputDate("11/01/2026")
                .selectCategory()
                .selectPriority()
                .clickOnNext()
                .inputStreetAddress("Main Street 1")
                .inputCity("Sofia")
                .inputPostalCode("1000")
                .inputLocationDescription("Near city center")
                .clickOnNext()
                .selectContactMethod("EMAIL")
                .inputEmail("john@example.com")
                .inputPhone("088123")
                .clickOnNext()
                .acceptTerms()
                .clickOnNext()
                .submitRequest();

        header.openMyRequests();

        Assertions.assertTrue(myRequestsPage.hasRequest(title), "Request is missing");
    }
}