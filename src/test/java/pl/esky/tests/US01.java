package pl.esky.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.esky.pages.HomePage;
import pl.esky.pages.SearchingFlightResultPage;

import java.util.concurrent.TimeUnit;

public class US01 {
        /* As a Client
        I want to find a flight for me and my family
        from Warsaw Chopin Airport to Cracow Balice Airport  */

    WebDriver driver;

    @Before
    public void setup () {
        System.setProperty("webdriver.chrome.driver", "/Users/maciejwachowski/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.esky.pl/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
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

        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        homePage.setOneWayTrip();
        homePage.setTripClass(journeyClass);

        homePage.setDepartureIfOneWay(departureCity);
        Thread.sleep(1000);
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.id("departureOneway")).sendKeys(Keys.ARROW_DOWN);
        }
        driver.findElement(By.id("departureOneway")).sendKeys(Keys.ENTER);

        homePage.setArrivalCity(arrivalCity);
        Thread.sleep(1000);
        driver.findElement(By.id("arrivalOneway")).sendKeys(Keys.ENTER);

        homePage.setMonth(lookingMonth);
        homePage.setDay(lookingDay);
        homePage.setAdultPassengers(numberOfAdultPassengers);
        homePage.setNumberOfChildPassengers(numberOfChildPassengers);
        homePage.search();

        SearchingFlightResultPage searchingFlightResultPage = new SearchingFlightResultPage(driver);
        Thread.sleep(3000);
        Assert.assertEquals("Warszawa (WAW) - Kraków (KRK) - eSky.pl",searchingFlightResultPage.getTitle());

    }

}
