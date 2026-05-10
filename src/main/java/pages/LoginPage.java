package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for XYZ Bank
    private final By customerLoginBtn = By.cssSelector("button[ng-click='customer()']");
    private final By managerLoginBtn = By.cssSelector("button[ng-click='manager()']");
    private final By userSelectDropdown = By.id("userSelect");
    private final By loginBtn = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickCustomerLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(customerLoginBtn)).click();
    }

    public void selectUser(String userName) {
        Select userSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(userSelectDropdown)));
        userSelect.selectByVisibleText(userName);
    }

    public DashboardPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return new DashboardPage(driver);
    }

    public void loginAs(String userName) {
        clickCustomerLogin();
        selectUser(userName);
        clickLogin();
    }

    public ManagerDashboardPage clickManagerLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(managerLoginBtn)).click();
        return new ManagerDashboardPage(driver);
    }
}
