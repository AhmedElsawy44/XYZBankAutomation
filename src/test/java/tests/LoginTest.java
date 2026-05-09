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

        // Verify that the login is successful by checking dashboard elements
        Assert.assertTrue(dashboardPage.isTransactionsTabDisplayed(), "Dashboard should be displayed and Transactions tab should be visible.");
    }
    
    // Note: Invalid login scenario removed because XYZ Bank only allows selecting from a dropdown without a password.
}
