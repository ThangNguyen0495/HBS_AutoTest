package utilities.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


public class Common {

    public WebDriver setupWebdriver(String headless, String browser_name) {
        //Config Webdriver
        switch (browser_name) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if (headless.equals("true")) {
                    options.addArguments("--headless");
                }
                options.addArguments("--disable-gpu");
//                options.addArguments("--incognito");
                options.addArguments("--no-sandbox");
                options.addArguments("--window-size=1280,800");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--allow-insecure-localhost");
                WebDriver driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
                return driver;
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--disable-gpu");
//                options.addArguments("--incognito");
                options.addArguments("--no-sandbox");
                options.addArguments("--window-size=1280,800");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--allow-insecure-localhost");
                WebDriver driver = new EdgeDriver(options);
                driver.manage().window().maximize();
                return driver;
            }
            case "safari" -> {
                WebDriverManager.safaridriver().setup();
                WebDriver driver = new SafariDriver();
                driver.manage().window().maximize();
                return driver;
            }
            case "ie" -> {
                WebDriverManager.iedriver().setup();
                WebDriver driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                return driver;
            }
            default -> {
                // chrome is setting default
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (headless.equals("true")) {
                    options.addArguments("--headless");
                }
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--window-size=1280,800");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--allow-insecure-localhost");
                WebDriver driver = new ChromeDriver(options);
                return driver;
            }
        }
    }
    public void login(WebDriver driver, String url, String mail, String password) throws InterruptedException {
      // wait for webdriver is generated
        driver.get(url);
        sleep(3000);
        driver.findElement(By.cssSelector("#username")).sendKeys(mail);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        sleep(2000);
        driver.manage().deleteAllCookies();
    }

    public Boolean authorized(String role, List<String> role_list) {
        return role_list.contains(role);
    }

    /**
     * @param number_of_role: 1: Master, 2: Administrator, 3: Responsible person, 4: Leader, 5: Member, 6: Guest
     **/
    public List<String> roleList(int number_of_role) {

        List<String> role_list = new ArrayList<>();
        switch (number_of_role) {
            case 1 -> role_list = List.of("Master");
            case 2 -> role_list = List.of("Master", "Administrator");
            case 3 -> role_list = List.of("Master", "Administrator", "Responsible person");
            case 4 -> role_list = List.of("Master", "Administrator", "Responsible person", "Leader");
            case 5 -> role_list = List.of("Master", "Administrator", "Responsible person", "Leader", "Member");
            case 6 -> role_list = List.of("Master", "Administrator", "Responsible person", "Leader", "Member", "Guest");
        }
        return role_list;
    }

    public String generateDateTimeForCaptureScreenShoot() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return dateTimeFormatter.format(dateTime);
    }

    public void takeScreenshotWhenTestFailForDebugging(WebDriver driver, ITestResult result, String test_result_path) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);

        if (result.getStatus() == ITestResult.FAILURE) {
            File scrShot = screenshot.getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/img/" + test_result_path + "/" + result.getName() + "_" + generateDateTimeForCaptureScreenShoot() + ".jpg";
            File destination = new File(path);
            FileUtils.copyFile(scrShot, destination);
        }
    }
}
