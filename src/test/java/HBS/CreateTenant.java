package HBS;

import BasePage.createTenant.Company_profile_information;
import BasePage.createTenant.Member_information;
import BasePage.createTenant.Payment_information;
import BasePage.createTenant.Personal_profile_information;
import Common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Variable.Variable.create_tenant_path;
import static Variable.Variable.url;

public class CreateTenant {
    Common cm;
    WebDriver driver;
    Member_information mi;
    Company_profile_information cpi;
    Personal_profile_information ppi;
    Payment_information pi;
    Actions key;

    @BeforeTest()
    public void init() {
        // Init Common function
        cm = new Common();

        // Config Webdriver
        driver = cm.setupWebdriver();
    }

    @BeforeGroups("Create tenant process")
    public void int_group_1() {
        // Init Member information function
        mi = new Member_information();

        // Init Company profile information function
        cpi = new Company_profile_information();

        // Init Personal profile information function
        ppi = new Personal_profile_information();

        // Init Payment information function
        pi = new Payment_information();
    }

    @BeforeGroups("Member information validation")
    public void init_group_2() {
        // Init Member information function
        mi = new Member_information();

        // Init Actions
        key = new Actions(driver);

        // Link to Create tenant - Member information tab
        mi.link_to_member_information(driver, url + create_tenant_path);
    }

    @BeforeGroups("Company profile information validation")
    public void init_group_3() {
        // Init Company profile information function
        cpi = new Company_profile_information();

        // Init Actions
        key = new Actions(driver);

        driver.get("https://test.app.cmrb.jp/tenant?auth_token=IjlhYjMwODQzLTE1NWUtNGZkNy1hN2JkLTA4OGY5MmY1NmNkYyI:1nJp5c:XkNjVp3bU_MJgvz4hnZjsZPsS8s");

//        // Init Member information function
//        mi = new Member_information();

//        // Member information
//        // Link to Create tenant - Member information tab
//        mi.link_to_member_information(driver, url + create_tenant_path);
//
//        // Email address
//        mi.email_address(driver);
//
//        // Password and Confirm password
//        mi.password_and_confirm_password(driver);
//
//        // Recaptcha checkbox
//        mi.recaptcha_checkbox(driver);
//
//        // Agree term of use
//        mi.agree_term_of_use(driver);
//
//        // Wait and click Register member information button
//        mi.click_register_member_information_button(driver);
//
//        // Next to Company profile information
//        mi.next_to_company_profile_information(driver);
    }

    @Test(groups = "Create tenant process")
    public void createTenant() throws InterruptedException {
        //****** 会員情報 ****** //
        // Member information
        // Link to Create tenant - Member information tab
        mi.link_to_member_information(driver, url + create_tenant_path);

        // Email address
        mi.email_address(driver);

        // Password and Confirm password
        mi.password_and_confirm_password(driver);

        // Recaptcha checkbox
        mi.recaptcha_checkbox(driver);

        // Agree term of use
        mi.agree_term_of_use(driver);

        // Wait and click Register member information button
        mi.click_register_member_information_button(driver);

        // Next to Company profile information
        mi.next_to_company_profile_information(driver);

        //****** 自社プロフィール情報 ****** //
        // Company profile information
        // Company name
        cpi.company_name(driver);

        // Establishment date
        cpi.establishment_date(driver);

        // Address
        cpi.address(driver);

        // Url
        cpi.url(driver);

        // Commercial distribution
        cpi.commercial_distribution(driver);

        // Capital
        cpi.capital(driver);

        // Qualifications
        // P mark / ISMS
        cpi.P_mark_or_ISMS(driver);

        // Invoice registration company
        cpi.invoice_registration_company(driver);

        // Worker dispatch business
        cpi.worker_dispatch_business(driver);

        // Next to Personal profile information step
        cpi.next_to_personal_profile_information(driver);

        //****** 個人プロフィール情報 ****** //
        // Personal profile information
        // Username
        ppi.username(driver);

//        // TEL
//        ppi.tel(driver);
//
//        // Password
//        ppi.password(driver);
//
//        // Email signature
//        ppi.email_signature(driver);

        // Next to Payment information step
        ppi.next_to_payment_information(driver);

        //****** お支払い情報 ****** //
        // Payment information
        // Get card number from testcard page
        pi.get_card_number_from_testcard_page(driver);

        // Back to Payment information step from testcard page
        pi.back_to_payment_information(driver);

        // Register Payment information
        // Open Payment information popup
        pi.register_payment_information(driver);

        // Card number
        pi.card_number_register(driver);

        //Date of Expiry
        pi.date_of_expiry(driver);

        //CVC
        pi.cvc(driver);

        //Card Name
        pi.card_name(driver);

        //Register card
        pi.complete_add_payment_information(driver);

        // Update Payment information
        // Open Payment information popup
        pi.update_payment_information(driver);

        // Card Number
        pi.card_number_update(driver);

        //Date of Expiry
        pi.date_of_expiry(driver);

        //CVC
        pi.cvc(driver);

        //Card Name
        pi.card_name(driver);

        //Update card
        pi.complete_add_payment_information(driver);

        // Complete tenant registration
        pi.complete_tenant_registration(driver);
    }

    @Test(groups = "Member information validation")
    public void leave_all_blank_member_information() throws InterruptedException {
        // Leave email blank and verify message
        mi.leave_email_blank(driver, key);

        // Leave password blank and verify message
        mi.leave_password_blank(driver, key);

        // Leave confirm password blank and verify message
        mi.leave_confirm_password_blank(driver, key);

        // Verify Register member information button is getting disable
        mi.register_member_information_should_be_disable(driver);
    }

    @Test(groups = "Member information validation")
    public void email_exceed_100_characters() {
        // Input email exceed 100 characters and verify message
        mi.email_exceed_100_characters(driver);
    }

    @Test(groups = "Member information validation")
    public void password_and_confirm_password_less_than_8_characters() {
        // Input password less than 8 characters and verify message
        mi.password_less_than_8_characters(driver);

        // Input confirm password less than 8 characters and verify message
        mi.confirm_password_less_than_8_characters(driver);
    }

    @Test(groups = "Member information validation")
    public void password_and_confirm_password_exceed_50_characters() {
        // Input password exceed 50 characters and verify message
        mi.password_exceed_50_characters(driver);

        // Input confirm password exceed 50 characters and verify message
        mi.confirm_password_exceed_50_characters(driver);
    }

    @Test(groups = "Member information validation")
    public void password_and_confirm_password_does_not_match() throws InterruptedException {
        // Link to Create tenant - Member information tab
        mi.link_to_member_information(driver, url + create_tenant_path);

        // Input password and confirm password does not match
        mi.password_and_confirm_password_does_not_match(driver);
    }

    @Test(groups = "Company profile information validation")
    public void leave_all_blank_company_profile_information() throws InterruptedException {
        // Leave company name blank and verify message
        cpi.leave_company_name_blank(driver, key);

        // Leave date of establishment blank and verify message
        cpi.leave_date_of_establishment_blank(driver,key);

        // Leave address blank and verify message
        cpi.leave_address_blank(driver, key);

        // Leave url blank and verify message
        cpi.leave_url_blank(driver, key);

        // Leave capital and verify message
        cpi.leave_capital_blank(driver, key);
    }

    @Test(groups = "Company profile information validation")
    public void company_name_exceed_100_characters() throws InterruptedException {
        // Input company name exceed 100 characters and verify message
        cpi.company_name_exceed_100_characters(driver);
    }

    @Test(groups = "Company profile information validation")
    public void address_exceed_100_characters() throws InterruptedException {
        // Input address exceed 100 characters and verify message
        cpi.address_exceed_100_characters(driver);
    }

    @Test(groups = "Company profile information validation")
    public void url_exceed_50_characters() throws InterruptedException {
        // Input URL exceed 50 characters and verify message
        cpi.url_exceed_50_characters(driver);
    }

    @Test(groups = "Company profile information validation")
    public void capital_exceed_13_characters() throws InterruptedException {
        // Input company name exceed 100 characters and verify message
        cpi.capital_exceed_13_characters(driver);
    }

    @AfterTest
    public void tearDown() {
        // Close browser
        driver.quit();
    }
}
