package test.payment;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import utilities.common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class UsagePlan {
    Common common;
    Actions key;
    WebDriver driver;
    page.payment.UsagePlan usagePlan;

    @BeforeClass
    public void clearOldTestFailedImage() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/img/Usage_Plan"));
    }

    @BeforeMethod
    @Parameters({"headless","browser_name", "domain", "email", "password","username_admin_page","password_admin_page"})
    public void setup(String headless,String browser_name, String domain, String email, String password, String username_admin_page, String password_admin_page) throws InterruptedException {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Actions
        key = new Actions(driver);

        //Login
        common.login(driver, domain, email, password);

        // Init Usage plan function
        usagePlan = new page.payment.UsagePlan(driver, domain, username_admin_page, password_admin_page);
    }

    @Test
    public void TC01_verifyThatCanBuyUsagePlan() throws InterruptedException {
        usagePlan.buyPlan();
    }

    @Test
    public void TC02_verifyThatCanAddUpperLimit() throws InterruptedException {
        usagePlan.addUpperLimit();
    }

    @Test
    public void TC03_verifyThatCanRemoveUpperLimit() throws InterruptedException {
        usagePlan.removeUpperLimit();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        // take screenshot when test failed
        common.takeScreenshotWhenTestFailForDebugging(driver, result, "Usage_Plan");

        // close all browsers
        driver.quit();
    }
}
