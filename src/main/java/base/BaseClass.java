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


    String path = System.getProperty("user.dir");

    public BaseClass() throws IOException {
        prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(path + "\\src\\main\\java\\config\\config.properties");
        prop.load(fileInputStream);
    }

    public static void initialization() throws MalformedURLException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    //This method captures screen shot
    public void getFailedScreenShot(String methodName) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(path + "\\src\\main\\java\\screenshot\\" + methodName + ".jpeg"));
    }


    public void clickOn(WebDriver driver, WebElement element, int timeout, String name) throws IOException {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
            element.click();
            System.out.println("Successfully Click on To Element " + name);
        } catch (Exception e) {

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = System.getProperty("user.dir") + "\\reports\\" + name + ".png";
            FileUtils.copyFile(source, new File(destination));
            System.out.println("Fail Click on To Element " + name);
            element.click();
        }
    }

    public void sendKeysOn(WebDriver driver, WebElement element, int timeout, String value, String name) throws IOException {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
            element.click();
            element.clear();
            element.sendKeys(value);
            System.out.println("Successfully Sent value" + value + " To Element " + name);
        } catch (Exception e) {

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = System.getProperty("user.dir") + "\\reports\\" + name + ".png";
            FileUtils.copyFile(source, new File(destination));
            System.out.println("Fail to Send value" + value + " To Element " + name);
            element.sendKeys(value);

        }
    }
}
