package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WithdrawalPage {
    private final WebDriverWait wait;

    private final By amountInput = By.xpath("//input[@ng-model='amount']");
    private final By submitWithdrawBtn = By.cssSelector("button[type='submit']");

    public WithdrawalPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput)).sendKeys(amount);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitWithdrawBtn)).click();
    }

    public void performWithdrawal(String amount) {
        enterAmount(amount);
        clickSubmit();
    }

    public String getMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'error')]"))).getText();
    }
}
