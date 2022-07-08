package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;
    //public static String nodeUrl = "http://10.10.10.121:5555/wd/hub";
//    public static String nodeUrl1 = " http://10.10.10.214:5555/wd/hub";

    String path = System.getProperty("user.dir");

    public BaseClass() throws IOException {
        prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(path+"\\src\\main\\java\\config\\config.properties");
        prop.load(fileInputStream);
    }

    public  static void initialization() throws MalformedURLException {

        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setBrowserName("chrome");
//        capabilities.setPlatform(Platform.WIN10);
//        //driver = new RemoteWebDriver(new URL(nodeUrl),capabilities);
//        driver = new RemoteWebDriver(new URL(nodeUrl1),capabilities);

        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
    }
}
