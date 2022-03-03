package test.Payment;

import utilities.Common.Common;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.Payment.usage_plan;

import java.io.File;
import java.io.IOException;

public class Usage_plan {
    Common cm;
    Actions key;
    WebDriver driver;
    usage_plan usagePlan;

    @BeforeMethod
    @Parameters({"headless","browser_name", "domain", "email", "password","username_admin_page","password_admin_page"})
    public void init(boolean headless,String browser_name, String domain, String email, String password, String username_admin_page, String password_admin_page) throws InterruptedException {
        // Init utilities.Common function
        cm = new Common();

        // Config Webdriver
        driver = cm.setupWebdriver(headless, browser_name);

        // Init Actions
        key = new Actions(driver);

        //Login
        cm.login(driver, domain, email, password);

        // Init Usage plan function
        usagePlan = new usage_plan(driver, domain, username_admin_page, password_admin_page);
    }

    @Test
    public void TC_01_buy_usage_plan() throws InterruptedException {
        usagePlan.buy_plan();
    }

    @Test
    public void TC_02_add_upper_limit() throws InterruptedException {
        usagePlan.add_upper_limit();
    }

    @Test
    public void TC_03_remove_upper_limit() throws InterruptedException {
        usagePlan.remove_upper_limit();
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        if (result.getStatus() == ITestResult.FAILURE) {
            File scrShot = screenshot.getScreenshotAs(OutputType.FILE);
            File dest = new File(System.getProperty("user.dir") +"\\img\\Usage_plan"+ result.getName() + ".jpg");
            FileUtils.copyFile(scrShot, dest);
        }
        driver.quit();
    }
}
