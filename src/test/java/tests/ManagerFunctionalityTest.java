package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class ManagerFunctionalityTest extends TestBase {

    private ManagerDashboardPage managerDashboard;

    @BeforeMethod
    public void loginAsManager() {
        LoginPage loginPage = new LoginPage(driver);
        managerDashboard = loginPage.clickManagerLogin();
    }

    @Test(description = "Verify Manager can add a new customer")
    public void testAddCustomer() {
        AddCustomerPage addCustomerPage = managerDashboard.navigateToAddCustomer();
        addCustomerPage.addCustomer("John", "Doe", "12345");
        
        String alertText = addCustomerPage.getAlertTextAndAccept();
        Assert.assertTrue(alertText.contains("Customer added successfully"), "Customer was not added successfully. Alert: " + alertText);
    }

    @Test(description = "Verify Manager can open an account for a customer")
    public void testOpenAccount() {
        OpenAccountPage openAccountPage = managerDashboard.navigateToOpenAccount();
        openAccountPage.openAccount("Harry Potter", "Dollar");
        
        String alertText = openAccountPage.getAlertTextAndAccept();
        Assert.assertTrue(alertText.contains("Account created successfully"), "Account was not created successfully. Alert: " + alertText);
    }

    @Test(description = "Verify Manager can search and delete a customer")
    public void testDeleteCustomer() {
        CustomersListPage customersPage = managerDashboard.navigateToCustomers();
        
        customersPage.searchCustomer("Harry");
        int countBeforeDelete = customersPage.getCustomersCount();
        Assert.assertTrue(countBeforeDelete > 0, "Customer Harry not found.");
        
        customersPage.deleteFirstCustomer();
        
        // Search again to verify deletion
        customersPage.searchCustomer("Harry");
        int countAfterDelete = customersPage.getCustomersCount();
        Assert.assertEquals(countAfterDelete, countBeforeDelete - 1, "Customer was not deleted successfully.");
    }
}
