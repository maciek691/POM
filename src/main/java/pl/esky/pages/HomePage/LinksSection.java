package pl.esky.pages.HomePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pl.esky.pages.Resources.Base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LinksSection extends Base {

    private static Logger log = LogManager.getLogger(LinksSection.class);
    // Variables
    private WebDriver driver;

    // Constructor
    public LinksSection(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
//    @FindBy(xpath = "//div[contains(@class,'col')]/ul/li/a")
//    WebElement linksInLinksSection;

    //Actions
    public void checkLinksInLinksSection() throws IOException {
        SoftAssert a = new SoftAssert();

        List<WebElement> links = driver.findElements(By.xpath("//div[contains(@class,'col')]/ul/li/a"));
        String prywatnoscLink = "Prywatność"; // As Prywatność link use JavaScript
        for (WebElement link : links) {
            try {
                String url = link.getAttribute("href");
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();
                int respondCode = connection.getResponseCode();

                if (respondCode < 400) {
                    log.info("the link " + link.getText() + " is ok");
                } else {
                    log.error("the link " + link.getText() + " is broke with code " + respondCode);
                }

                a.assertTrue(respondCode < 400, "The link with text " + link.getText() + " is broke with code "
                        + respondCode);

            } catch (MalformedURLException e) {
                Assert.assertEquals(prywatnoscLink, link.getText());
                log.warn("The link with text " + link.getText() + " throw Exception" + e.getMessage());
                System.out.println("The link with text " + link.getText() + " throw Exception" + e.getMessage());
            }

        }

        a.assertAll();
    }
}
