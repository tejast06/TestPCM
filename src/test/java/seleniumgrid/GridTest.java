package seleniumgrid;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GridTest {
    public WebDriver driver;
    public String nodeUrl = "http://10.10.10.214:5555/wd/hub";

    @Test
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WIN10);

        System.setProperty("webdriver.chrome.driver","C:\\Windows\\System32\\cmd.exe\\chromedriver.exe");
        driver= new RemoteWebDriver(new URL(nodeUrl),capabilities);
        driver.get("https://www.impactguru.com/");
        driver.quit();

    }
}
