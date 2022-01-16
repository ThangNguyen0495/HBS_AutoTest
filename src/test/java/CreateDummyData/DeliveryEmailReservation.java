package CreateDummyData;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static Variable.Variable.*;
import static Variable.Variable.master_password;
import static java.lang.Thread.sleep;

public class DeliveryEmailReservation {
    @Test
    public void main() throws InterruptedException {

        //Config Webdriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        String baseURL = url + delivery_email_reservation_path;
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //Login
        driver.get(baseURL);
        driver.findElement(By.cssSelector("#username")).sendKeys(master_email);
        driver.findElement(By.cssSelector("#password")).sendKeys(master_password);
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.LoginForm-button-3lInS")).click();
        sleep(3000);

        String subject = RandomStringUtils.randomAlphabetic(8);
        String insertion = RandomStringUtils.randomAlphabetic(50);
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys(subject);
        driver.findElement(By.cssSelector("textarea[id='text']")).sendKeys(insertion);
        driver.findElement(By.xpath("//div[@class='ant-col ant-col-6']//div//button[@class='ant-btn ant-btn-primary']")).click();

        sleep(1000);
        driver.findElement(By.xpath("//div[@class='ant-col ant-col-6']//div//button[@class='ant-btn ant-btn-primary']")).click();

        sleep(1000);
        driver.findElement(By.cssSelector("#searchtype> label:nth-child(3) > span.ant-radio > input")).click();
        driver.findElement(By.cssSelector("div.ant-col.ant-col-12>div>button[type='submit']")).click();
        driver.findElement(By.cssSelector("div.ant-table-selection>*>*>input")).click();
        driver.findElement(By.xpath("//div[@class='ant-col ant-col-6']//div//button[@class='ant-btn ant-btn-primary']")).click();

        sleep(1000);
        driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>div:nth-child(4)>div>button")).click();
        WebElement date = driver.findElement(By.cssSelector("div.ant-col>div:nth-child(1)>div>div>div:nth-child(1)>div>div>input"));
        ((JavascriptExecutor) driver).executeScript ("arguments[0].removeAttribute('readonly',0);", date); // Enables the from date box

        date.sendKeys("2022-01-31");
        Actions select_calendar = new Actions(driver);
        select_calendar.sendKeys(Keys.ENTER).perform();
        driver.findElement(By.cssSelector("div.ant-modal-footer>button")).click();
        sleep(1000);
        driver.findElement(By.cssSelector("div.ant-modal-confirm-btns>button.ant-btn.ant-btn-primary")).click();

    }
}
