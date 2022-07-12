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
public class TestFailedDonationViaNetBanking extends BaseClass {

    public HomePage homePage;
    private BrowseFundRaise browseFundRaise;

    public TestFailedDonationViaNetBanking() throws IOException {
    }

    @BeforeTest
    public void setUp() throws IOException {
        //Launch the chrome browser
        initialization();
        homePage = new HomePage();
        browseFundRaise = new BrowseFundRaise();

    }

    @Test(priority = 1)
    public void ValidatePageTitle() throws IOException, InterruptedException {
        //Validate the page title is displayed
        Assert.assertTrue(homePage.getTitle().contains("Crowdfunding in India: Best Fundraising Platform Online | ImpactGuru"),"" +
                "Expected page title is not displayed.");
    }

    @Test(priority = 2)
    public void enterSlugAndClickOnSearchButton() throws InterruptedException, IOException {
        Thread.sleep(2000);
        //Enter 'Help Aahaan raise funds' on search field and click on search button.
        //browseFundRaise = homePage.enterTextOnFieldAncClick();
        sendKeysOn(driver, homePage.searchField,10,"test","searchField");



    }

    @Test(priority = 3)
    public void performFailedDonation() throws InterruptedException {
        browseFundRaise.performFailDonationWithNetBanking();

    }

    @AfterTest
    public void closeTheBrowser(){
        driver.quit();
    }
}
