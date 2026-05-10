package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class TransactionsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By resetBtn = By.cssSelector("button[ng-click='reset()']");
    private final By backBtn = By.cssSelector("button[ng-click='back()']");
    private final By transactionsTableRows = By.xpath("//table/tbody/tr");

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickReset() {
        wait.until(ExpectedConditions.elementToBeClickable(resetBtn)).click();
    }

    public DashboardPage clickBack() {
        wait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();
        return new DashboardPage(driver);
    }

    public int getTransactionsCount() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(transactionsTableRows));
            List<WebElement> rows = driver.findElements(transactionsTableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }
}
