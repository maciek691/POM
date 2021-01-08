package pl.esky.pages.Resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    public Properties prop;

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/pl/esky/pages/Resources/data.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if(browserName.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/java/pl/esky/pages/Resources/Drivers/chromedriver");
            driver = new ChromeDriver();
        } else if (browserName.equals("FireFox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/java/pl/esky/pages/Resources/Drivers/geckodriver");
            driver = new FirefoxDriver();
        } else if (browserName.equals("Safari")) {
            driver = new SafariDriver();
        } else if (browserName.equals( "Opera")) {
            System.setProperty("webdriver.chrome.driver", "src/main/java/pl/esky/pages/Resources/Drivers/operadriver");
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
