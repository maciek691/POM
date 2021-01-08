package pl.esky.pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FlightSearch {

    // Variables
    private final WebDriver driver;
    static final String E_SKY_URL_MAIN = "https://www.esky.pl/";

    // Constructor
    public FlightSearch(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators

    @FindBy(xpath = "//button[@mode='primary']")
    WebElement acceptCookiesButton;

    @FindBy(id = "TripTypeOneway")
    WebElement tripOneWayRadiobutton;

    @FindBy(id = "serviceClass")
    WebElement tripClass;

    @FindBy(id = "departureOneway")
    WebElement departureIfOneWay;

    @FindBy(id = "arrivalOneway")
    WebElement arrivalIfOneWay;

    @FindBy(id = "departureDateOneway")
    WebElement departureDateIfOneWay;

    @FindBy(className = "ui-datepicker-month")
    WebElement month;

    @FindBy(xpath = "//span[@class='ui-icon ui-icon-circle-triangle-e']")
    WebElement plusMonth;

    @FindBy(id = "adultPax")
    WebElement adultPassengers;

    @FindBy(id = "childPax")
    WebElement childPassengers;

    @FindBy(css = "div[data-pax-type='adult'] [class='plus']")
    WebElement plusAdult;

    @FindBy(css = "div[data-pax-type='child'] [class='plus']")
    WebElement plusChild;

    @FindBy(xpath = "//fieldset[@class='trip-search']/button")
    WebElement searchButton;

    // Actions

    public void acceptCookies() {
        acceptCookiesButton.click();
    }

    public void setOneWayTrip() {
        tripOneWayRadiobutton.click();
    }

    public void setTripClass(String journeyClass) {
        Select journey = new Select(tripClass);
        journey.selectByValue(journeyClass);
    }

    public void setDepartureIfOneWay (String departureCity) {
        departureIfOneWay.sendKeys(departureCity);
    }

    public void setArrivalCity (String arrivalCity) {
        arrivalIfOneWay.sendKeys(arrivalCity);
    }

    public void setMonth (String lookingMonth) {
        departureDateIfOneWay.click();
        while (!month.getText().contains(lookingMonth)) {
            plusMonth.click();
        }
    }

    public void setDay (String lookingDay) {
        int numberOfDays = driver.findElements(By.cssSelector("td[data-handler='selectDay']")).size();

        for (int i = 0; i < numberOfDays; i++) {
            String day = driver.findElements(By.className("ui-state-default")).get(i).getText();
            if (day.equalsIgnoreCase(lookingDay)) {
                driver.findElements(By.className("ui-state-default")).get(i).click();
                break;
            }
        }
    }

    public void setAdultPassengers(String numberOfAdultPassengers) {
        while (!adultPassengers.getAttribute("value").equalsIgnoreCase(numberOfAdultPassengers)) {
            plusAdult.click();
        }
    }

    public void setNumberOfChildPassengers (String numberOfChildPassengers) {
        while (!childPassengers.getAttribute("value").equalsIgnoreCase(numberOfChildPassengers)) {
            plusChild.click();
        }
    }

    public void search() {
        searchButton.click();
    }

    }

