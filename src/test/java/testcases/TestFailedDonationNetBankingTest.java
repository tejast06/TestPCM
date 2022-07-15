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
public class TestFailedDonationNetBankingTest extends BaseClass {

    public HomePage homePage;
    private BrowseFundRaise browseFundRaise;

    public TestFailedDonationNetBankingTest() throws IOException {
    }

    @BeforeTest
    public void setUp() throws IOException {

        //Launch the chrome browser
        System.out.println("Launch the browser");
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
        Thread.sleep(2000);
        System.out.println("Enter slug on search field and click on search button");
        //Enter 'Help Aahaan raise funds' on search field and click on search button.
        browseFundRaise = homePage.enterTextOnFieldAncClick();
        System.out.println("Slug entered on search bar and search button clicked successfully");
    }

    @Test(priority = 3)
    public void performFailedDonation() throws InterruptedException {
        System.out.println("Perform fail donation");
        browseFundRaise.performFailDonationWithNetBanking();
        System.out.println("Failed donation performed successfully");

    }
    @AfterTest
    public void closeTheBrowser(){
        System.out.println("Close the browser");
        driver.quit();
        System.out.println("Browser closed successfully");
    }
}
