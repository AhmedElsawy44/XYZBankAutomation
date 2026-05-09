package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ManagerDashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addCustomerTab = By.cssSelector("button[ng-click='addCust()']");
    private By openAccountTab = By.cssSelector("button[ng-click='openAccount()']");
    private By customersTab = By.cssSelector("button[ng-click='showCust()']");

    public ManagerDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public AddCustomerPage navigateToAddCustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(addCustomerTab)).click();
        return new AddCustomerPage(driver);
    }

    public OpenAccountPage navigateToOpenAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(openAccountTab)).click();
        return new OpenAccountPage(driver);
    }

    public CustomersListPage navigateToCustomers() {
        wait.until(ExpectedConditions.elementToBeClickable(customersTab)).click();
        return new CustomersListPage(driver);
    }
}
