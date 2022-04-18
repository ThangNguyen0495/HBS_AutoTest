package test.Login;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import page.Login.Check_Captcha;
import utilities.Common.Common;

import java.io.File;
import java.io.IOException;

public class Login {
    Common common;
    WebDriver driver;
    Check_Captcha check_captcha;

    @BeforeClass
    public void clear_old_test_data_and_error() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/img/Login"));
    }

    @BeforeMethod
    @Parameters({"headless", "browser_name", "domain"})
    public void setup(String headless, String browser_name, String domain) {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Check Captcha functions
        check_captcha = new Check_Captcha(driver, common, domain);
    }

    @Test
    public void TC01_verify_captcha_display_after_login_fail_3_times() throws InterruptedException {
        // Login fail 3 times
        check_captcha.Login_fail_3_times();

        // Verify captcha is displayed after login fail 3 times
        check_captcha.Captcha_should_be_displayed();
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        // take screenshot when test failed
        common.take_screenshot_when_test_fail(driver, result, "Login");

        // close all browsers
        driver.quit();
    }
}
