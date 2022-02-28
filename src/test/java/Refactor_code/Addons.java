package Refactor_code;

import BasePage.paymentProcess.addons;
import Common.Common;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Addons {
    Common cm;
    Actions key;
    WebDriver driver;
    addons addOns;

    @BeforeMethod
    @Parameters({"headless", "domain", "email", "password", "username_admin_page", "password_admin_page"})
    public void init(boolean headless, String domain, String email, String password, String username_admin_page, String password_admin_page) throws InterruptedException {
        // Init Common function
        cm = new Common();

        // Config Webdriver
        driver = cm.setupWebdriver(headless);

        // Init Actions
        key = new Actions(driver);

        //Login
        cm.login(driver, domain, email, password);

        // Init Addons function
        addOns = new addons(driver, domain, username_admin_page, password_admin_page);
    }

    @Test
    public void TC_01_acquisition_of_delivery_opening_information_Add() throws InterruptedException {
        addOns.acquisition_of_delivery_opening_information_Add();
    }

    @Test
    public void TC_02_acquisition_of_delivery_opening_information_Remove() throws InterruptedException {
        addOns.acquisition_of_delivery_opening_information_Remove();
    }

    @Test
    public void test() {
        ((JavascriptExecutor) driver).executeScript
                ("window.open('https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.1.1');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
//        ((JavascriptExecutor) driver).executeScript
//                ("window.open('" + "test" + "');");
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);

        if (result.getStatus() == ITestResult.FAILURE) {
            File scrShot = screenshot.getScreenshotAs(OutputType.FILE);
            File dest = new File(System.getProperty("user.dir") + "\\img\\" + result.getName() + ".jpg");
            FileUtils.copyFile(scrShot, dest);
        }
//        driver.quit();
    }
}
