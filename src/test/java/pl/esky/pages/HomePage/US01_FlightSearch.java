package pl.esky.pages.HomePage;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.esky.pages.Resources.Base;
import pl.esky.pages.SearchingFlightResultPage.SearchingFlightResultPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class US01_FlightSearch extends Base {
        /* As a Client
        I want to find a flight for me and my family
        from Warsaw Chopin Airport to Cracow Balice Airport  */

    WebDriver driver;
    private static Logger log = LogManager.getLogger(US01_FlightSearch.class);

    @BeforeTest
    public void setup () throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/maciejwachowski/SeleniumDriver/chromedriver");
        driver = initializeDriver();
        driver.get(prop.getProperty("MainUrl"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest
    public void end () {
        driver.quit();
    }

    @Test
    public void searchFlight() throws InterruptedException {
        // testing data
        String journeyClass = "economy-premium";
        String departureCity = "Warszawa";
        String arrivalCity = "Kraków";
        String lookingMonth = "Luty";
        String lookingDay = "10";
        String numberOfAdultPassengers = "2";
        String numberOfChildPassengers = "2";

        FlightSearch flightSearch = new FlightSearch(driver);
        flightSearch.acceptCookies();
        log.info("cookies accepted");
        flightSearch.setOneWayTrip();
        log.info("One way trip ok");
        flightSearch.setTripClass(journeyClass);
        log.info("Class trip ok");
        flightSearch.setDepartureIfOneWay(departureCity);
        Thread.sleep(1000);
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.id("departureOneway")).sendKeys(Keys.ARROW_DOWN);
        }
        driver.findElement(By.id("departureOneway")).sendKeys(Keys.ENTER);
        log.info("Departure City set");

        flightSearch.setArrivalCity(arrivalCity);
        Thread.sleep(1000);
        driver.findElement(By.id("arrivalOneway")).sendKeys(Keys.ENTER);
        log.info("Arrival City set");

        flightSearch.setMonth(lookingMonth);
        log.info("Month set");
        flightSearch.setDay(lookingDay);
        log.info("Day set");
        flightSearch.setAdultPassengers(numberOfAdultPassengers);
        log.info("Adult passengers set");
        flightSearch.setNumberOfChildPassengers(numberOfChildPassengers);
        log.info("Children passengers set");
        flightSearch.search();

        SearchingFlightResultPage searchingFlightResultPage = new SearchingFlightResultPage(driver);
        Thread.sleep(3000);
        Assert.assertEquals("Warszawa (WAW) - Kraków (KRK) - eSky.pl",searchingFlightResultPage.getTitle());
        log.info("Landing Page ok");
    }

}
