package pl.esky.pages.HomePage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.esky.pages.Resources.Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class US02_CheckLinksInLinksSection extends Base {

    WebDriver driver;

    @BeforeTest
    public void setup() throws IOException {
        driver = initializeDriver();
        driver.get(prop.getProperty("MainUrl"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterTest
    public void end() {
        driver.quit();
    }

    @Test
    public void checkLinksInLinksSection() throws IOException {
        FlightSearch flightSearch = new FlightSearch(driver);
        flightSearch.acceptCookies();
        LinksSection linksSection = new LinksSection(driver);
        linksSection.checkLinksInLinksSection();
    }
}
