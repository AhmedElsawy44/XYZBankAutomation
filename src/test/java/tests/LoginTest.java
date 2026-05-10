package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends TestBase {

    @Test(description = "Verify valid customer login scenario for XYZ Bank")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.clickCustomerLogin();
        loginPage.selectUser("Harry Potter");
        DashboardPage dashboardPage = loginPage.clickLogin();

        Assert.assertTrue(dashboardPage.isTransactionsTabDisplayed(), "Dashboard should be displayed and Transactions tab should be visible.");
    }
    
}
