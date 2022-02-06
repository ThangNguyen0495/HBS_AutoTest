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
import static java.lang.Thread.sleep;
import static Variable.Variable.*;

public class RegisterContact {
    @Test
    public void main() throws InterruptedException {

        //Config Webdriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver(options);

        //Login
        driver.get(url + register_contact_path);
        driver.findElement(By.id("username")).sendKeys(master_email);
        driver.findElement(By.id("password")).sendKeys(master_password);
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.LoginForm-button-3lInS")).click();
        sleep(1000);

        //Loop for dummy test data
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
            driver.get(url + register_contact_path);
        }
    }
}
