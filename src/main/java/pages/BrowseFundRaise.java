package pages;

import base.BaseClass;
import excelutility.Xls_Reader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class BrowseFundRaise extends BaseClass {

    public HomePage homePage;
    String path = System.getProperty("user.dir");

    @FindBy(xpath = "//p[text()='by Naman Kazi']")
    WebElement donateBtn;

    @FindBy(xpath = "//button[text()='Donate ']")
    WebElement donateNow;
    public BrowseFundRaise() throws IOException {
        PageFactory.initElements(driver,this);
    }

    public void performFailDonationWithNetBanking() throws InterruptedException {

        //Click on Donate button
        Thread.sleep(5000);
        donateBtn.click();

        Thread.sleep(4000);
        Set<String> wins = driver.getWindowHandles();
        String newWin;
        Iterator<String> it = wins.iterator();
        while(it.hasNext()){
            newWin = it.next();
            driver.switchTo().window(newWin);
        }

        //Click on Donate button now.
        Thread.sleep(3000);

        donateNow.click();

        Xls_Reader xls_reader = new Xls_Reader(path+"\\src\\main\\java\\TestData\\data.xlsx");
        int rowCount = xls_reader.getRowCount("Sheet1");



        for (int i = 2; i <= rowCount; i++) {

                String firstName = xls_reader.getCellData("Sheet1", "FullName", i);
                String email = xls_reader.getCellData("Sheet1", "Email", i);
                String mobile = xls_reader.getCellData("Sheet1", "Mobile", i);
                String city = xls_reader.getCellData("Sheet1", "City", i);

                //enter full name.
                driver.findElement(By.cssSelector("#full_name")).sendKeys(firstName);

                //enter emailid
                driver.findElement(By.cssSelector("[id = 'email_receipt']")).sendKeys(email);

                //enter phone number
                driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys(mobile);

                //enter city
                driver.findElement(By.cssSelector("#city_text")).sendKeys(city);

                //click on yes for 'Are you an indian citizen
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.getElementById('campaign_is_in_yes').click();");

                //click on donate button
                driver.findElement(By.cssSelector("#story-popup-donate-button")).click();
                Thread.sleep(2000);

                //select net banking option
                driver.findElement(By.cssSelector("#payment-netbanking-nav")).click();
                Thread.sleep(3000);

                //select SBI
                driver.findElement(By.cssSelector(".bank-sbi")).click();
                Thread.sleep(3000);

                //Click on contribute button
                driver.findElement(By.cssSelector("#donate-netbanking-contribute")).click();
                Thread.sleep(3000);

                //Select 'Failure' option from the dropdown
                driver.findElement(By.xpath("//select[@id='BankStatus']/option[text()='Failure']")).click();

                //click on submit button
                driver.findElement(By.xpath("//button[text()='Submit']")).click();

                System.out.print(i);
                Thread.sleep(3000);

            if (driver.findElement(By.xpath("//i[@class='fas fa-exclamation-triangle text-warning']")).isDisplayed()){
                xls_reader.setCellData("Sheet1","status",i,"Passed");
            }
            else {
                xls_reader.setCellData("Sheet1","status",i,"Failed");
            }
            driver.findElement(By.xpath("(//*[@class='guessUserName'])[2]")).click();
        }
    }

    /**
     * Created by TToley
     * This method perform success donation.
      * @throws InterruptedException
     * @throws IOException
     */
    public void performSuccessDonationWithNetBanking() throws InterruptedException, IOException {

        //Click on Donate button
        Thread.sleep(7000);
        donateBtn.click();

        Thread.sleep(5000);
        Set<String> wins = driver.getWindowHandles();
        String newWin;
        Iterator<String> it = wins.iterator();
        while(it.hasNext()){
            newWin = it.next();
            driver.switchTo().window(newWin);
        }

        //Click on Donate button now.
        Thread.sleep(5000);

        donateNow.click();

        Xls_Reader xls_reader = new Xls_Reader(path+"\\src\\main\\java\\TestData\\data.xlsx");
        int rowCount = xls_reader.getRowCount("Sheet1");

        for (int i = 2; i <= rowCount; i++) {
            String firstName = xls_reader.getCellData("Sheet1", "FullName", i);
            String email = xls_reader.getCellData("Sheet1", "Email", i);
            String mobile = xls_reader.getCellData("Sheet1", "Mobile", i);
            String city = xls_reader.getCellData("Sheet1", "City", i);

            //enter full name.
            driver.findElement(By.cssSelector("#full_name")).sendKeys(firstName);

            //enter emailid
            driver.findElement(By.cssSelector("[id = 'email_receipt']")).sendKeys(email);

            //enter phone number
            driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys(mobile);

            //enter city
            driver.findElement(By.cssSelector("#city_text")).sendKeys(city);

            //click on yes for 'Are you an indian citizen
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('campaign_is_in_yes').click();");

            //click on donate button
            driver.findElement(By.cssSelector("#story-popup-donate-button")).click();
            Thread.sleep(2000);

            //select net banking option
            driver.findElement(By.cssSelector("#payment-netbanking-nav")).click();
            Thread.sleep(3000);

            //select SBI
            driver.findElement(By.cssSelector(".bank-sbi")).click();
            Thread.sleep(3000);

            //Click on contribute button
            driver.findElement(By.cssSelector("#donate-netbanking-contribute")).click();
            Thread.sleep(3000);

            //Select 'Failure' option from the dropdown
            driver.findElement(By.xpath("//select[@id='BankStatus']/option[text()='Success']")).click();

            //click on submit button
            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            System.out.print(i);
            Thread.sleep(2000);

            driver.navigate().to("https://whitehat.impactguru.com/fundraiser/help-aahaan?utm_source=browse&utm_medium=donate_button&utm_campaign=help-aahaan&iglsmp=1");
            Thread.sleep(5000);

            driver.findElement(By.xpath("(//*[@class='guessUserName'])[4]")).click();

        }
    }
}
