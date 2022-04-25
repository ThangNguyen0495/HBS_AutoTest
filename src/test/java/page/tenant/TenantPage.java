package page.tenant;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TenantPage {
    WebDriver driver;
    Actions key;
    WebDriverWait wait;
    String domain;


    public TenantPage(WebDriver driver, String domain) {
        this.driver = driver;
        this.domain = domain;
        key = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
