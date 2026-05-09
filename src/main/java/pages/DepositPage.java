package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DepositPage {
    private WebDriverWait wait;

    // Locators for XYZ Bank Deposit Page
    private By amountInput = By.xpath("//input[@ng-model='amount']");
    private By submitDepositBtn = By.cssSelector("button[type='submit']");
    private By successMessage = By.xpath("//span[contains(@class, 'error') and contains(text(), 'Deposit Successful')]");

    public DepositPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput)).sendKeys(amount);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitDepositBtn)).click();
    }

    public void performDeposit(String amount) {
        enterAmount(amount);
        clickSubmit();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}
