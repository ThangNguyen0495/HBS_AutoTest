import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class User {

    @Test
    public void main() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        String baseURL = "https://test.app.cmrb.jp/users/invite";
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseURL);
        driver.findElement(By.id("username")).sendKeys("master@codun.site");
        driver.findElement(By.id("password")).sendKeys("12345678a");
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.LoginForm-button-3lInS")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        for (int i = 1; i < 2; i++) {
            String mail = RandomStringUtils.randomAlphabetic(8);
            driver.findElement(By.id("email")).sendKeys(mail + "@gmail.com");
            driver.findElement(By.id("role")).click();
            Actions select_role = new Actions(driver);
            select_role.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
            driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.EditForm-button-3IRWY")).click();
            driver.get(baseURL);

        }
    }
}
