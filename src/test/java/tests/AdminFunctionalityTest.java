package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminHomePage;
import pages.AdminLoginPage;
import pages.AdminSignupPage;
import pages.HomePage;
import util.TestData;

public class AdminFunctionalityTest extends BaseTest {

    @Test(description = "Create Admin and verify login.")
    public void verifyAdminCreation() throws InterruptedException {

        extentTest = extentReports.createTest("verifyAdminCreation");

        String userName = TestData.userName();
        String password = TestData.password();

        HomePage homePage = new HomePage(driver);
        homePage.navigateToAdminHomePage()
                .navigateToAdminSignupPage()
                .createNewAdmin(userName, password)
                .adminLogin(userName, password);

        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8000/admin-dashboard");
    }

    @Test
    public void testToShowFailure () {
        extentTest = extentReports.createTest("testToShowFailure");

        Assert.assertEquals(driver.getTitle(), "LazyCoder || sumit");
    }
}