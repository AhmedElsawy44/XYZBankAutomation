package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass; // تم التعديل لتكون BeforeClass
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.DepositPage;
import pages.LoginPage;

public class BankFunctionalityTest extends TestBase {

    private DashboardPage dashboardPage;

    // استخدمنا BeforeClass عشان نعمل لوجين مرة واحدة ونحتفظ بالرصيد (Session) خلال باقي الاختبارات
    @BeforeClass
    public void loginBeforeTests() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Harry Potter");
        dashboardPage = new DashboardPage(driver);
    }

    @Test(priority = 1, description = "Verify Transactions and Deposit tabs are visible")
    public void testTabsVisibility() {
        Assert.assertTrue(dashboardPage.isTransactionsTabDisplayed(), "Transactions tab is not visible.");
        Assert.assertTrue(dashboardPage.isDepositTabDisplayed(), "Deposit tab is not visible.");
    }

    @Test(priority = 2, description = "Verify balance is displayed in a numeric format")
    public void testBalanceFormat() {
        Assert.assertTrue(dashboardPage.isBalanceNumeric(), "Balance is not displayed in numeric format.");
    }

    @Test(priority = 3, description = "Verify deposit functionality and success message")
    public void testDeposit() {
        DepositPage depositPage = dashboardPage.navigateToDeposit();

        depositPage.performDeposit("500");

        String successMsg = depositPage.getSuccessMessage();
        Assert.assertTrue(successMsg.toLowerCase().contains("success"), "Deposit was not successful.");
    }

    @Test(priority = 4, description = "Verify withdrawal functionality and message", dependsOnMethods = "testDeposit")
    public void testWithdrawal() throws InterruptedException {
        pages.WithdrawalPage withdrawalPage = dashboardPage.navigateToWithdrawal();

        // [الحل] انتظار قصير حتى يقوم AngularJS بتحديث الرصيد في خلفية صفحة السحب
        Thread.sleep(1500);

        withdrawalPage.performWithdrawal("500");

        String msg = withdrawalPage.getMessage();
        Assert.assertTrue(msg.toLowerCase().contains("successful"), "Withdrawal was not successful. Message: " + msg);
    }

    @Test(priority = 5, description = "Verify transactions history", dependsOnMethods = "testDeposit")
    public void testTransactionsHistory() throws InterruptedException {
        pages.TransactionsPage transactionsPage = dashboardPage.navigateToTransactions();

        // [الحل] انتظار قصير حتى يتم رسم جدول المعاملات بناءً على البيانات المحفوظة
        Thread.sleep(2000);

        int count = transactionsPage.getTransactionsCount();
        
        // Workaround for Angular app delay: if transactions didn't load, click back and try again
        if (count == 0) {
            dashboardPage = transactionsPage.clickBack();
            Thread.sleep(1000);
            transactionsPage = dashboardPage.navigateToTransactions();
            Thread.sleep(2000);
            count = transactionsPage.getTransactionsCount();
        }

        Assert.assertTrue(count > 0, "No transactions found in the history table.");

        transactionsPage.clickReset();

        dashboardPage = transactionsPage.clickBack();
        Assert.assertTrue(dashboardPage.isTransactionsTabDisplayed(), "Failed to navigate back to dashboard.");
    }
}