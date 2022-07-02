package pages;

import base.BaseClass;
import excelutility.Xls_Reader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class HomePage extends BaseClass {

    String path = System.getProperty("user.dir");

    @FindBy(css = "[type='text']")
    WebElement searchField;

    @FindBy(xpath = "//button[@id='nav-search-btn']")
    WebElement searchBtn;

    public HomePage() throws IOException {
        PageFactory.initElements(driver,this);
    }

    /**
     * Created by TTOLEY
     * This method return the title of the page
     * @return
     */
    public String getTitle(){
        return driver.getTitle();
    }

    public BrowseFundRaise enterTextOnFieldAncClick() throws InterruptedException, IOException {
        Xls_Reader xls_reader = new Xls_Reader(path+"\\src\\main\\java\\TestData\\data.xlsx");
        String searchTxt = xls_reader.getCellData("Sheet1","slug",2);
        searchField.sendKeys(searchTxt);
        Thread.sleep(2000);
        searchBtn.click();
        return new BrowseFundRaise();
    }



}
