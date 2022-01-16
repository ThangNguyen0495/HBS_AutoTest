package CreateDummyData;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static Variable.Variable.*;

public class CreateTenant {

    @Test
    //Create tenant - Member information
    public void テナント作成_会員情報() {

        //Config Webdriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        String baseURL = url + create_tenant_path;
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //Link to create tenant - Member information
        driver.get(baseURL);

        // Generate dummy mail, password
        String mail = RandomStringUtils.randomAlphabetic(8);
        String password = RandomStringUtils.randomAlphabetic(50);

        //Input mail, password, reconfirm password
        driver.findElement(By.cssSelector("#email")).sendKeys(mail + "@@cheapnitros.com");
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(password);

        //Handle recaptcha checkbox
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border")))
                .click();

        // Back to main page
        driver.switchTo().defaultContent();

        // Agree term of use
        driver.findElement(By.cssSelector("input[id^='agree_to']")).click();

        // Wait and click Register member information button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .elementToBeClickable(By.cssSelector("button.ant-btn.ant-btn-primary")))
                .click();

        // Accept mail
        driver.get("https://generator.email/" + mail + "@cheapnitros.com");


//        String subject = RandomStringUtils.randomAlphabetic(8);
//        String insertion = RandomStringUtils.randomAlphabetic(50);
//        driver.findElement(By.cssSelector("input[type='text']")).sendKeys(subject);
//        driver.findElement(By.cssSelector("textarea[id='text']")).sendKeys(insertion);
//        driver.findElement(By.xpath("//div[@class='ant-col ant-col-6']//div//button[@class='ant-btn ant-btn-primary']")).click();
//
//        sleep(1000);
//        driver.findElement(By.xpath("//div[@class='ant-col ant-col-6']//div//button[@class='ant-btn ant-btn-primary']")).click();
//
//        sleep(1000);
//        driver.findElement(By.cssSelector("#searchtype> label:nth-child(3) > span.ant-radio > input")).click();
//        driver.findElement(By.cssSelector("div.ant-col.ant-col-12>div>button[type='submit']")).click();
//        driver.findElement(By.cssSelector("div.ant-table-selection>*>*>input")).click();
//        driver.findElement(By.xpath("//div[@class='ant-col ant-col-6']//div//button[@class='ant-btn ant-btn-primary']")).click();
//
//        sleep(1000);
//        driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>div:nth-child(4)>div>button")).click();
//        WebElement date = driver.findElement(By.cssSelector("div.ant-col>div:nth-child(1)>div>div>div:nth-child(1)>div>div>input"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly',0);", date); // Enables the from date box
//
//        date.sendKeys("2022-01-31");
//        Actions select_calendar = new Actions(driver);
//        select_calendar.sendKeys(Keys.ENTER).perform();
//        driver.findElement(By.cssSelector("div.ant-modal-footer>button")).click();
//        sleep(1000);
//        driver.findElement(By.cssSelector("div.ant-modal-confirm-btns>button.ant-btn.ant-btn-primary")).click();

    }
}
