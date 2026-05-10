package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddCustomerPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameInput = By.xpath("//input[@ng-model='fName']");
    private final By lastNameInput = By.xpath("//input[@ng-model='lName']");
    private final By postCodeInput = By.xpath("//input[@ng-model='postCd']");
    private final By addCustomerBtn = By.cssSelector("button[type='submit']");

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addCustomer(String fName, String lName, String postCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(fName);
        driver.findElement(lastNameInput).sendKeys(lName);
        driver.findElement(postCodeInput).sendKeys(postCode);
        wait.until(ExpectedConditions.elementToBeClickable(addCustomerBtn)).click();
    }

    public String getAlertTextAndAccept() {
        wait.until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }
}
