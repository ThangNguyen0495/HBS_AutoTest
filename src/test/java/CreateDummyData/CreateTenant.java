package CreateDummyData;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class CreateTenant {
    public static String Step_2_url;

//    @Test
    //Member information
//    public void テナント作成_会員情報() {
//
//        //Config Webdriver
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(false);
//        String baseURL = url + create_tenant_path;
//        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//
//        //Link to create tenant - Member information
//        driver.get(baseURL);
//
//        // Generate dummy mail, password
//        String mail = RandomStringUtils.randomAlphabetic(8);
//        String password = RandomStringUtils.randomAlphabetic(50);
//
//        //Input mail, password, reconfirm password
//        driver.findElement(By.cssSelector("#email")).sendKeys(mail + "@@cheapnitros.com");
//        driver.findElement(By.cssSelector("#password")).sendKeys(password);
//        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(password);
//
//        //Handle recaptcha checkbox
//        new WebDriverWait(driver, Duration.ofSeconds(20))
//                .until(ExpectedConditions
//                        .frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
//        new WebDriverWait(driver, Duration.ofSeconds(20))
//                .until(ExpectedConditions
//                        .elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border")))
//                .click();
//
//        // Back to main page
//        driver.switchTo().defaultContent();
//
//        // Agree term of use
//        driver.findElement(By.cssSelector("input[id^='agree_to']")).click();
//
//        // Wait and click Register member information button
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions
//                        .elementToBeClickable(By.cssSelector("button.ant-btn.ant-btn-primary")))
//                .click();
//
//        // Accept mail
//        driver.get("https://generator.email/" + mail + "@cheapnitros.com");
//    }

    @Test
    public void Checkmail() throws InterruptedException {

        //Config Webdriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Link to register mail
        driver.get("https://generator.email/dydmike@bedul.net");

        //Waiting for ads
        sleep(5000);

        // Reload page to remove Ads
        driver.get("https://generator.email/inbox1/");

        // Open [コモレビ]コモレビへ新規登録 mail
        driver.findElement(By.cssSelector("div.e7m.list-group-item.list-group-item-info")).click();

        // Get link in  [コモレビ]コモレビへ新規登録 mail
        WebElement link = driver.findElement(By.cssSelector("div.e7m.mess_bodiyy>p>a"));
        Step_2_url = link.getAttribute("href");
    }
//
//    @Test
//    // Company profile information
//    public void 自社プロフィール情報(){
//
//
//    }
}
