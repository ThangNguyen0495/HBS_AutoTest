package test.Payment;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import utilities.Common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import page.Payment.addons;
import page.Payment.addons_add_remove_check;

import java.io.File;
import java.io.IOException;

import static utilities.Link_and_Path.HBS.*;

public class Addons {
    Common common;
    Actions key;
    WebDriver driver;
    addons addOns;
    addons_add_remove_check addonsAddRemoveCheck;

    @BeforeClass
    public void clear_old_test_error() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\img\\Addons"));
    }

    @BeforeMethod
    @Parameters({"headless", "browser_name", "domain", "email", "password", "username_admin_page", "password_admin_page"})
    public void setup(boolean headless, String browser_name, String domain, String email, String password, String username_admin_page, String password_admin_page) throws InterruptedException {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Actions
        key = new Actions(driver);

        //Login
        common.login(driver, domain + addons_path, email, password);

        // Init Addons function
        addOns = new addons(driver, domain, username_admin_page, password_admin_page);

        addonsAddRemoveCheck = new addons_add_remove_check(driver,domain, username_admin_page,password_admin_page);
    }

    @Test
    public void TC_01_recommendation_acquisition_of_delivery_opening_information_Add() throws InterruptedException {
        addOns.Add(0);
        addOns.check_addons_related(0, 6);
    }

    @Test
    public void TC_02_recommendation_acquisition_of_delivery_opening_information_Remove() throws InterruptedException {
        addOns.Remove(0);
        addOns.check_addons_related(0, 6);
    }

    @Test
    public void TC_03_recommendation_maximum_number_of_deliveries_Add() throws InterruptedException {
        addOns.Add(1);
        addOns.check_addons_related(1, 10);
    }

    @Test
    public void TC_04_recommendation_maximum_number_of_deliveries_Remove() throws InterruptedException {
        addOns.Remove(1);
        addOns.check_addons_related(1, 10);
    }

    @Test
    public void TC_05_recommendation_shortening_the_delivery_interval_Add() throws InterruptedException {
        addOns.Add(2);
        addOns.check_addons_related(2, 11);
    }

    @Test
    public void TC_06_recommendation_shortening_the_delivery_interval_Remove() throws InterruptedException {
        addOns.Remove(2);
        addOns.check_addons_related(2, 11);
    }

    @Test
    public void TC_07_account_management_number_of_search_condition_template_registrations_Add() throws InterruptedException {
        addOns.Add(3);
        addOns.check_addons_related(3, 5);
    }

    @Test
    public void TC_08_account_management_number_of_search_condition_template_registrations_Remove() throws InterruptedException {
        addOns.Remove(3);
        addOns.check_addons_related(3, 5);
    }

    @Test
    public void TC_09_account_management_number_of_comment_template_registrations_Add() throws InterruptedException {
        addOns.Add(4);
    }

    @Test
    public void TC_10_account_management_number_of_comment_template_registrations_Remove() throws InterruptedException {
        addOns.Remove(4);
    }

    @Test
    public void TC_11_delivery_mail_management_number_of_search_condition_template_registrations_Add() throws InterruptedException {
        addOns.Add(5);
        addOns.check_addons_related(5, 3);
    }

    @Test
    public void TC_12_delivery_mail_management_number_of_search_condition_template_registrations_Remove() throws InterruptedException {
        addOns.Remove(5);
        addOns.check_addons_related(5, 3);
    }

    @Test
    public void TC_13_delivery_mail_management_acquisition_of_delivery_opening_information_Add() throws InterruptedException {
        addOns.Add(6);
        addOns.check_addons_related(6, 0);
    }

    @Test
    public void TC_14_delivery_mail_management_acquisition_of_delivery_opening_information_Remove() throws InterruptedException {
        addOns.Remove(6);
        addOns.check_addons_related(6, 0);
    }

    @Test
    public void TC_15_delivery_mail_management_extension_of_acquisition_period_of_delivery_opening_information_Add() throws InterruptedException {
        addOns.Add(7);
    }

    @Test
    public void TC_16_delivery_mail_management_extension_of_acquisition_period_of_delivery_opening_information_Remove() throws InterruptedException {
        addOns.Remove(7);
    }

    @Test
    public void TC_17_delivery_mail_management_delivery_attachment_capacity_Add() throws InterruptedException {
        addOns.Add(8);

    }

    @Test
    public void TC_18_delivery_mail_management_delivery_attachment_capacity_Remove() throws InterruptedException {
        addOns.Remove(8);
    }

    @Test
    public void TC_19_delivery_mail_management_advertising_display_during_html_distribution_Add() throws InterruptedException {
        addOns.Add(9);
    }

    @Test
    public void TC_20_delivery_mail_management_advertising_display_during_html_distribution_Remove() throws InterruptedException {
        addOns.Remove(9);
    }

    @Test
    public void TC_21_delivery_mail_management_maximum_number_of_deliveries_Add() throws InterruptedException {
        addOns.Add(10);
        addOns.check_addons_related(10, 1);
    }

    @Test
    public void TC_22_delivery_mail_management_maximum_number_of_deliveries_Remove() throws InterruptedException {
        addOns.Remove(10);
        addOns.check_addons_related(10, 1);
    }

    @Test
    public void TC_23_delivery_mail_management_shortening_the_delivery_interval_Add() throws InterruptedException {
        addOns.Add(11);
        addOns.check_addons_related(11, 2);
    }

    @Test
    public void TC_24_delivery_mail_management_shortening_the_delivery_interval_Remove() throws InterruptedException {
        addOns.Remove(11);
        addOns.check_addons_related(11, 2);
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException, InterruptedException {
        // take screenshot when test failed
        common.take_screenshot_when_test_fail(driver, result, "Addons");

        // close all browsers
//        driver.quit();
    }
}
