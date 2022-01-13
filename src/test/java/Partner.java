import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Partner {
    @Test
    public void main() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        String baseURL = "https://test.app.cmrb.jp/organizations/register";
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseURL);
        driver.findElement(By.id("username")).sendKeys("master@codun.site");
        driver.findElement(By.id("password")).sendKeys("12345678a");
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.LoginForm-button-3lInS")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        for (int i = 1; i < 100; i++) {
            String company = RandomStringUtils.randomAlphabetic(8);
            driver.findElement(By.id("name")).sendKeys(company);
            driver.findElement(By.cssSelector("[value='prospective']")).click();
            driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.EditForm-button-3IRWY")).click();
            driver.get(baseURL);

        }
    }
}
