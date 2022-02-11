package Common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;


public class Common {
    public WebDriver setupWebdriver() {
        //Config Webdriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public void login(WebDriver driver, String url, String mail, String password) throws InterruptedException {
        driver.get(url);
        driver.findElement(By.cssSelector("#username")).sendKeys(mail);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        sleep(1000);
    }

    public void closeBrowser(WebDriver driver) {
        // Close browser
        driver.close();
    }
}
