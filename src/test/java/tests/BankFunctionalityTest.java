package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.DepositPage;
import pages.LoginPage;

public class BankFunctionalityTest extends TestBase {

    private DashboardPage dashboardPage;

    @BeforeMethod
    public void loginBeforeTests() {
        // Precondition: User must be logged in to test bank functionalities
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Harry Potter");
        dashboardPage = new DashboardPage(driver);
    }

    @Test(description = "Verify Transactions and Deposit tabs are visible")
    public void testTabsVisibility() {
        Assert.assertTrue(dashboardPage.isTransactionsTabDisplayed(), "Transactions tab is not visible.");
        Assert.assertTrue(dashboardPage.isDepositTabDisplayed(), "Deposit tab is not visible.");
    }

    @Test(description = "Verify balance is displayed in a numeric format")
    public void testBalanceFormat() {
        Assert.assertTrue(dashboardPage.isBalanceNumeric(), "Balance is not displayed in numeric format. Current value: " + dashboardPage.getBalance());
    }

    @Test(description = "Verify deposit functionality and success message")
    public void testDeposit() {
        DepositPage depositPage = dashboardPage.navigateToDeposit();
        
        // Perform a deposit of 500
        depositPage.performDeposit("500");
        
        // Verify success message
        String successMsg = depositPage.getSuccessMessage();
        Assert.assertTrue(successMsg.toLowerCase().contains("success"), "Deposit was not successful. Message: " + successMsg);
    }

    @Test(description = "Verify withdrawal functionality and message", dependsOnMethods = "testDeposit")
    public void testWithdrawal() {
        pages.WithdrawalPage withdrawalPage = dashboardPage.navigateToWithdrawal();
        
        // Withdraw 500
        withdrawalPage.performWithdrawal("500");
        
        String msg = withdrawalPage.getMessage();
        Assert.assertTrue(msg.toLowerCase().contains("successful"), "Withdrawal was not successful. Message: " + msg);
    }

    @Test(description = "Verify transactions history", dependsOnMethods = "testDeposit")
    public void testTransactionsHistory() {
        pages.TransactionsPage transactionsPage = dashboardPage.navigateToTransactions();
        
        int count = transactionsPage.getTransactionsCount();
        Assert.assertTrue(count > 0, "No transactions found in the history table.");
        
        transactionsPage.clickReset();
        
        // Back to dashboard to refresh state if needed, or simply pass
        dashboardPage = transactionsPage.clickBack();
        Assert.assertTrue(dashboardPage.isTransactionsTabDisplayed(), "Failed to navigate back to dashboard.");
    }
}
