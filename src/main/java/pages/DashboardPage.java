package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for XYZ Bank Dashboard
    private By transactionsTab = By.cssSelector("button[ng-click='transactions()']");
    private By depositTab = By.cssSelector("button[ng-click='deposit()']");
    private By withdrawTab = By.cssSelector("button[ng-click='withdrawl()']");
    private By balanceValue = By.xpath("//div[@ng-hide='noAccount']//strong[2]");
    private By accountSelectDropdown = By.id("accountSelect");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isTransactionsTabDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(transactionsTab)).isDisplayed();
    }

    public boolean isDepositTabDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(depositTab)).isDisplayed();
    }

    public String getBalance() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(balanceValue)).getText();
    }
    
    public boolean isBalanceNumeric() {
        String balance = getBalance().trim();
        return balance.matches("\\d+(\\.\\d+)?");
    }

    public DepositPage navigateToDeposit() {
        wait.until(ExpectedConditions.elementToBeClickable(depositTab)).click();
        return new DepositPage(driver);
    }

    public WithdrawalPage navigateToWithdrawal() {
        wait.until(ExpectedConditions.elementToBeClickable(withdrawTab)).click();
        return new WithdrawalPage(driver);
    }

    public TransactionsPage navigateToTransactions() {
        wait.until(ExpectedConditions.elementToBeClickable(transactionsTab)).click();
        return new TransactionsPage(driver);
    }

    public void selectAccount(String accountNumber) {
        org.openqa.selenium.support.ui.Select accountSelect = new org.openqa.selenium.support.ui.Select(wait.until(ExpectedConditions.visibilityOfElementLocated(accountSelectDropdown)));
        accountSelect.selectByVisibleText(accountNumber);
    }
}
