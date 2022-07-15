package testcases;

import base.BaseClass;
import listeners.CustomListeners;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BrowseFundRaise;
import pages.HomePage;

import java.io.IOException;

@Listeners(CustomListeners.class)
public class TestSuccessDonationNetBankingTest extends BaseClass {
    public HomePage homePage;
    private BrowseFundRaise browseFundRaise;

    public TestSuccessDonationNetBankingTest() throws IOException {
    }

    @BeforeTest
    public void setUp() throws IOException {
        System.out.println("Launch the browser");
        //Launch the chrome browser
        initialization();
        homePage = new HomePage();
        browseFundRaise = new BrowseFundRaise();
        System.out.println("Browser launched successfully");

    }

    @Test(priority = 1)
    public void ValidatePageTitle() throws IOException, InterruptedException {
        System.out.println("Validate the page title");
        //Validate the page title is displayed
        Assert.assertTrue(homePage.getTitle().contains("Crowdfunding in India: Best Fundraising Platform Online | ImpactGuru"),"" +
                "Expected page title is not displayed.");
        System.out.println("Page title validate successfully");
    }

    @Test(priority = 2)
    public void enterSlugAndClickOnSearchButton() throws InterruptedException, IOException {
        System.out.println("enter the slug and click on search field");
        //Enter 'Help Aahaan raise funds' on search field and click on search button.
        browseFundRaise = homePage.enterTextOnFieldAncClick();
        System.out.println("Slug entered and search button clicked successfully");

    }

    @Test(priority = 3)
    public void performSuccessDonation() throws InterruptedException, IOException {

        System.out.println("Perform success donation");
        browseFundRaise.performSuccessDonationWithNetBanking();
        System.out.println("Success donation performed successfully");

    }

    @AfterTest
    public void closeTheBrowser(){
        System.out.println("Close the browser");
        driver.quit();
        System.out.println("Browser closed successfully");
    }
}
