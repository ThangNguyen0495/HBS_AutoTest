package test.Payment;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import utilities.Common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import page.Payment.usage_plan;

import java.io.File;
import java.io.IOException;

public class Usage_plan {
    Common common;
    Actions key;
    WebDriver driver;
    usage_plan usagePlan;

    @BeforeClass
    public void clear_old_test_error() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\img\\Usage_Plan"));
    }

    @BeforeMethod
    @Parameters({"headless","browser_name", "domain", "email", "password","username_admin_page","password_admin_page"})
    public void setup(boolean headless,String browser_name, String domain, String email, String password, String username_admin_page, String password_admin_page) throws InterruptedException {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Actions
        key = new Actions(driver);

        //Login
        common.login(driver, domain, email, password);

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
    public void teardown(ITestResult result) throws IOException, InterruptedException {
        // take screenshot when test failed
        common.take_screenshot_when_test_fail(driver, result, "Usage_Plan");

        // close all browsers
        driver.quit();
    }
}
