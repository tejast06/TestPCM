package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
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

    public void getFailedScreenShot(String methodName) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File(path+"\\src\\main\\java\\screenshot\\"+methodName+".jpeg"));
    }

    public  void sendKeysOn(WebDriver driver, WebElement element, int timeout, String value, String name)throws IOException{
        if(element.isDisplayed())
        {
            new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
            element.click();
            element.clear();
            element.sendKeys(value);

        }
        else {
            //Copy the file to a location and use try catch block to handle exception
            try {
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);
                String destination=System.getProperty("user.dir")+"\\reports\\"+name+".png";
                FileUtils.copyFile(source, new File(destination));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            element.sendKeys(value);
        }
    }
}
