package pl.esky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchingFlightResultPage {

    // Variables
    private final WebDriver driver;

    //Constructor
    public SearchingFlightResultPage (final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Actions

    public String getTitle () {
        String title = driver.getTitle();
        return title;
    }
}
