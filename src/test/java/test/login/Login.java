package test.login;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import page.login.CheckCaptcha;
import utilities.common.Common;

import java.io.File;
import java.io.IOException;

public class Login {
    Common common;
    WebDriver driver;
    CheckCaptcha check_captcha;

    @BeforeClass
    public void clearOldTestFailedImage() throws IOException {
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
        check_captcha = new CheckCaptcha(driver, common, domain);
    }

    @Test
    public void TC01_verifyThatAfterLoginFail3TimesCaptchaShouldBeDisplayed() throws InterruptedException {
        // Login fail 3 times
        check_captcha.loginFail3times();

        // Verify captcha is displayed after login fail 3 times
        check_captcha.verifyThatAfterLoginFail3TimesCaptchaShouldBeDisplayed();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        // take screenshot when test failed
        common.takeScreenshotWhenTestFailForDebugging(driver, result, "Login");

        // close all browsers
        driver.quit();
    }
}
