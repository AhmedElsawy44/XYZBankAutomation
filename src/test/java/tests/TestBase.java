package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;   // تم التعديل هنا
import org.testng.annotations.BeforeClass;  // تم التعديل هنا

import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

    // Virtual Banking Website link
    protected final String URL = "https://globalsqa.com/angularJs-protractor/BankingProject/#/login";

    @BeforeClass // تم التعديل هنا ليفتح المتصفح مرة واحدة قبل كل اختبارات الكلاس
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Uncomment the line below to run tests in headless mode
        // options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.get(URL);
    }

    @AfterClass // تم التعديل هنا ليغلق المتصفح مرة واحدة بعد انتهاء كل اختبارات الكلاس
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}