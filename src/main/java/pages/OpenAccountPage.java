package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OpenAccountPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By customerSelectDropdown = By.id("userSelect");
    private final By currencySelectDropdown = By.id("currency");
    private final By processBtn = By.cssSelector("button[type='submit']");

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openAccount(String customerName, String currency) {
        Select custSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(customerSelectDropdown)));
        custSelect.selectByVisibleText(customerName);
        
        Select currSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(currencySelectDropdown)));
        currSelect.selectByVisibleText(currency);
        
        wait.until(ExpectedConditions.elementToBeClickable(processBtn)).click();
    }

    public String getAlertTextAndAccept() {
        wait.until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }
}
