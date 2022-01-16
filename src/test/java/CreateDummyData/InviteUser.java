package CreateDummyData;

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
import static Variable.Variable.*;


public class InviteUser {

    @Test
    public void main() {

        //Config Webdriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions Options = new ChromeOptions();
        Options.setHeadless(true);
        WebDriver driver = new ChromeDriver(Options);

        //Login
        driver.get(url + invite_user_path);
        driver.findElement(By.id("username")).sendKeys(master_email);
        driver.findElement(By.id("password")).sendKeys(master_password);
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.LoginForm-button-3lInS")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //Loop for dummy test data
        for (int i = 1; i < 100; i++) {
            String mail = RandomStringUtils.randomAlphabetic(8);
            driver.findElement(By.id("email")).sendKeys(mail + "@gmail.com");
            driver.findElement(By.id("role")).click();
            Actions select_role = new Actions(driver);
            select_role.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
            driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.EditForm-button-3IRWY")).click();
            driver.get(url + invite_user_path);

        }
    }
}
