import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class Contact {
    @Test
    public void main() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        String baseURL = "https://test.app.cmrb.jp/contacts/register";
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseURL);
        driver.findElement(By.id("username")).sendKeys("master@codun.site");
        driver.findElement(By.id("password")).sendKeys("12345678a");
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.LoginForm-button-3lInS")).click();
        sleep(1000);

        for (int i = 1; i < 100; i++) {
            String fname = RandomStringUtils.randomAlphabetic(3);
            String lname = RandomStringUtils.randomAlphabetic(3);
            driver.findElement(By.id("last_name")).sendKeys(lname);
            driver.findElement(By.id("first_name")).sendKeys(fname);
            sleep(3000);
            driver.findElement(By.id("organization")).click();
            Actions select_role = new Actions(driver);
            select_role.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
            String mail = RandomStringUtils.randomAlphabetic(8);
            driver.findElement(By.cssSelector("[id='email']")).sendKeys(mail + "@gmail.com");
            driver.findElement(By.xpath("//*[contains(@type,'submit')]")).click();
            driver.get(baseURL);
        }
    }
}
