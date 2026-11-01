package pages;

import org.openqa.selenium.WebDriver;

public class MyRequestsPage {

    private final WebDriver driver;

    public MyRequestsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean hasRequest(String title) {

        return driver.getPageSource().contains(title)
                && driver.getPageSource().contains("Submitted")
                && driver.getPageSource().contains("High");
    }
}