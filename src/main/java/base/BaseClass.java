package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;
    String path = System.getProperty("user.dir");

    public BaseClass() throws IOException {
        prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(path+"\\src\\main\\java\\config\\config.properties");
        prop.load(fileInputStream);
    }

    public  static void initialization(){

        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();

        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
    }
}
