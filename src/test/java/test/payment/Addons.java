package test.payment;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import utilities.common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import page.payment.AddonsAddOrRemoveCheck;

import java.io.File;
import java.io.IOException;

import static utilities.links.HBS.*;

public class Addons {
    Common common;
    Actions key;
    WebDriver driver;
    page.payment.Addons addOns;
    AddonsAddOrRemoveCheck addonsAddRemoveCheck;

    @BeforeClass
    public void clearOldTestFailedImage() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/img/Addons"));
    }

    @BeforeMethod
    @Parameters({"headless", "browser_name", "domain", "email", "password", "username_admin_page", "password_admin_page"})
    public void setup(String headless, String browser_name, String domain, String email, String password, String username_admin_page, String password_admin_page) throws InterruptedException {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Actions
        key = new Actions(driver);

        //Login
        common.login(driver, domain + ADDONS_PATH, email, password);

        // Init Addons function
        addOns = new page.payment.Addons(driver, domain, username_admin_page, password_admin_page);

        addonsAddRemoveCheck = new AddonsAddOrRemoveCheck(driver,domain, username_admin_page,password_admin_page);
    }

    @Test
    public void TC01_verifyThatCanAddRecommendationAcquisitionOfDeliveryOpeningInformationAddons() throws InterruptedException {
        addOns.Add(0);
        addOns.checkAddonsRelated(0, 6);
    }

    @Test
    public void TC02_verifyThatCanRemoveRecommendationAcquisitionOfDeliveryOpeningInformationAddons() throws InterruptedException {
        addOns.Remove(0);
        addOns.checkAddonsRelated(0, 6);
    }

    @Test
    public void TC03_verifyThatCanAddRecommendationMaximumNumberOfDeliveriesAddons() throws InterruptedException {
        addOns.Add(1);
        addOns.checkAddonsRelated(1, 10);
    }

    @Test
    public void TC04_verifyThatCanRemoveRecommendationMaximumNumberOfDeliveriesAddons() throws InterruptedException {
        addOns.Remove(1);
        addOns.checkAddonsRelated(1, 10);
    }

    @Test
    public void TC05_verifyThatCanAddRecommendationShorteningTheDeliveryIntervalAddons() throws InterruptedException {
        addOns.Add(2);
        addOns.checkAddonsRelated(2, 11);
    }

    @Test
    public void TC06_verifyThatCanRemoveRecommendationShorteningTheDeliveryIntervalAddons() throws InterruptedException {
        addOns.Remove(2);
        addOns.checkAddonsRelated(2, 11);
    }

    @Test
    public void TC07_verifyThatCanAddAccountManagementNumberOfSearchConditionTemplateRegistrationsAddons() throws InterruptedException {
        addOns.Add(3);
        addOns.checkAddonsRelated(3, 5);
    }

    @Test
    public void TC08_verifyThatCanRemoveAccountManagementNumberOfSearchConditionTemplateRegistrationsAddons() throws InterruptedException {
        addOns.Remove(3);
        addOns.checkAddonsRelated(3, 5);
    }

    @Test
    public void TC09_verifyThatCanAddAccountManagementNumberOfCommentTemplateRegistrationsAddons() throws InterruptedException {
        addOns.Add(4);
    }

    @Test
    public void TC10_verifyThatCanRemoveAccountManagementNumberOfCommentTemplateRegistrationsAddons() throws InterruptedException {
        addOns.Remove(4);
    }

    @Test
    public void TC11_verifyThatCanAddDeliveryMailManagementNumberOfSearchConditionTemplateRegistrationsAddons() throws InterruptedException {
        addOns.Add(5);
        addOns.checkAddonsRelated(5, 3);
    }

    @Test
    public void TC12_verifyThatCanRemoveDeliveryMailManagementNumberOfSearchConditionTemplateRegistrationsAddons() throws InterruptedException {
        addOns.Remove(5);
        addOns.checkAddonsRelated(5, 3);
    }

    @Test
    public void TC13_verifyThatCanAddDeliveryMailManagementAcquisitionOfDeliveryOpeningInformationAddons() throws InterruptedException {
        addOns.Add(6);
        addOns.checkAddonsRelated(6, 0);
    }

    @Test
    public void TC14_verifyThatCanRemoveDeliveryMailManagementAcquisitionOfDeliveryOpeningInformationAddons() throws InterruptedException {
        addOns.Remove(6);
        addOns.checkAddonsRelated(6, 0);
    }

    @Test
    public void TC15_verifyThatCanAddDeliveryMailManagementExtensionOfAcquisitionPeriodOfDeliveryOpeningInformationAddons() throws InterruptedException {
        addOns.Add(7);
    }

    @Test
    public void TC16_verifyThatCanRemoveDeliveryMailManagementExtensionOfAcquisitionPeriodOfDeliveryOpeningInformationAddons() throws InterruptedException {
        addOns.Remove(7);
    }

    @Test
    public void TC17_verifyThatCanAddDeliveryMailManagementDeliveryAttachmentCapacityAddons() throws InterruptedException {
        addOns.Add(8);
    }

    @Test
    public void TC18_verifyThatCanRemoveDeliveryMailManagementDeliveryAttachmentCapacityAddons() throws InterruptedException {
        addOns.Remove(8);
    }

    @Test
    public void TC19_verifyThatCanAddDeliveryMailManagementAdvertisingDisplayDuringHtmlDistributionAddons() throws InterruptedException {
        addOns.Add(9);
    }

    @Test
    public void TC20_verifyThatCanRemoveDeliveryMailManagementAdvertisingDisplayDuringHtmlDistributionAddons() throws InterruptedException {
        addOns.Remove(9);
    }

    @Test
    public void TC21_verifyThatCanAddDeliveryMailManagementMaximumNumberOfDeliveriesAddons() throws InterruptedException {
        addOns.Add(10);
        addOns.checkAddonsRelated(10, 1);
    }

    @Test
    public void TC22_verifyThatCanRemoveDeliveryMailManagementMaximumNumberOfDeliveriesAddons() throws InterruptedException {
        addOns.Remove(10);
        addOns.checkAddonsRelated(10, 1);
    }

    @Test
    public void TC23_verifyThatCanAddDeliveryMailManagementShorteningTheDeliveryIntervalAddons() throws InterruptedException {
        addOns.Add(11);
        addOns.checkAddonsRelated(11, 2);
    }

    @Test
    public void TC24_verifyThatCanRemoveDeliveryMailManagementShorteningTheDeliveryIntervalAddons() throws InterruptedException {
        addOns.Remove(11);
        addOns.checkAddonsRelated(11, 2);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        // take screenshot when test failed
        common.takeScreenshotWhenTestFailForDebugging(driver, result, "Addons");

        // close all browsers
        driver.quit();
    }
}
