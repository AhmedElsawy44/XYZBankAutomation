package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CustomersListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox = By.xpath("//input[@ng-model='searchCustomer']");
    private By customerRows = By.xpath("//table/tbody/tr");
    private By deleteBtn = By.cssSelector("button[ng-click='deleteCust(cust)']");

    public CustomersListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchCustomer(String name) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(name);
    }

    public int getCustomersCount() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(customerRows));
            List<WebElement> rows = driver.findElements(customerRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void deleteFirstCustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
    }
}
