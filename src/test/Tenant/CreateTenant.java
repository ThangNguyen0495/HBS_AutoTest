package test.Tenant;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Common.Common;
import org.openqa.selenium.WebDriver;
import page.Tenant.Step2_Company_profile_information;
import page.Tenant.Step1_Member_information;
import page.Tenant.Step4_Payment_information;
import page.Tenant.Step3_Personal_profile_information;

import java.io.File;
import java.io.IOException;

public class CreateTenant {
    Common common;
    WebDriver driver;
    Step1_Member_information memberInformation;
    Step2_Company_profile_information companyProfileInformation;
    Step3_Personal_profile_information personalProfileInformation;
    Step4_Payment_information paymentInformation;

    @BeforeClass
    public void clear_old_test_error() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\img\\Create_Tenant"));
    }

    @BeforeMethod
    @Parameters({"headless", "browser_name", "domain", "register_token_cpi", "register_token_ppi"})
    public void setup(String headless, String browser_name, String domain, String register_token_cpi, String register_token_ppi) {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Member information function
        memberInformation = new Step1_Member_information(driver, domain);

        // Init Company profile information function
        companyProfileInformation = new Step2_Company_profile_information(driver, domain, register_token_cpi);

        // Init Personal profile information function
        personalProfileInformation = new Step3_Personal_profile_information(driver, domain, register_token_ppi);

        // Init page.Payment information function
        paymentInformation = new Step4_Payment_information(driver, domain);
    }

    @Test
    public void TC01_process_create_tenant() throws InterruptedException {
        //****** 会員情報 ****** //
        // Member information
        // Link to Create tenant - Member information tab
        memberInformation.link_to_member_information();

        // Email address
        memberInformation.email_address();

        // Password and Confirm password
        memberInformation.password_and_confirm_password();

        // Recaptcha checkbox
        memberInformation.recaptcha_checkbox();

        // Agree term of use
        memberInformation.agree_term_of_use();

        // Wait and click Register member information button
        memberInformation.click_register_member_information_button();

        // Next to Company profile information
        memberInformation.next_to_company_profile_information();

        //****** 自社プロフィール情報 ****** //
        // Company profile information
        // Company name
        companyProfileInformation.company_name();

        // Establishment date
        companyProfileInformation.establishment_date();

        // Address
        companyProfileInformation.address();

        // url
        companyProfileInformation.url();

        // Commercial distribution
        companyProfileInformation.commercial_distribution();

        // Capital
        companyProfileInformation.capital();

        // Qualifications
        // P mark / ISMS
        companyProfileInformation.P_mark_or_ISMS();

        // Invoice registration company
        companyProfileInformation.invoice_registration_company();

        // Worker dispatch business
        companyProfileInformation.worker_dispatch_business();

        // Next to Personal profile information step
        companyProfileInformation.next_to_personal_profile_information();

        //****** 個人プロフィール情報 ****** //
        // Personal profile information
        // Username
        personalProfileInformation.username();

        // TEL
//        personalProfileInformation.tel();
//
//        // Password
//        personalProfileInformation.password();

        // Email signature
        personalProfileInformation.email_signature();

        // Next to page.Payment information step
        personalProfileInformation.next_to_payment_information();

        //****** お支払い情報 ****** //
        // page.Payment information
        // Get card number from testcard page
        paymentInformation.get_card_number_from_testcard_page();

        // Back to page.Payment information step from testcard page
        paymentInformation.back_to_payment_information();

        // Register page.Payment information
        // Open page.Payment information popup
        paymentInformation.register_payment_information();

        // Card number
        paymentInformation.card_number_register();

        //Date of Expiry
        paymentInformation.date_of_expiry();

        //CVC
        paymentInformation.cvc();

        //Card Name
        paymentInformation.card_name();

        //Register card
        paymentInformation.complete_add_payment_information();

        // Update page.Payment information
        // Open page.Payment information popup
        paymentInformation.update_payment_information();

        // Card Number
        paymentInformation.card_number_update();

        //Date of Expiry
        paymentInformation.date_of_expiry();

        //CVC
        paymentInformation.cvc();

        //Card Name
        paymentInformation.card_name();

        //Update card
        paymentInformation.complete_add_payment_information();

        // Complete tenant registration
        paymentInformation.complete_tenant_registration();
    }

    @Test
    public void TC02_leave_all_blank_member_information() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformation.link_to_member_information();

        // Leave email blank and verify message
        memberInformation.leave_email_blank();

        // Leave password blank and verify message
        memberInformation.leave_password_blank();

        // Leave confirm password blank and verify message
        memberInformation.leave_confirm_password_blank();
    }

    @Test
    public void TC03_email_password_and_confirm_password_contains_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformation.link_to_member_information();

        // Input email contains full width characters and verify message
        memberInformation.email_contains_full_width_characters();

        // Input password contains full width characters and verify message
        memberInformation.password_contains_full_width_characters();

        // Input confirm password contains full width characters and verify message
        memberInformation.confirm_password_contains_full_width_characters();
    }

    @Test
    public void TC04_email_contains_space_characters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformation.link_to_member_information();

        // Input email contains space characters and verify message
        memberInformation.email_contains_space_characters();
    }

    @Test
    public void TC05_email_exceed_100_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformation.link_to_member_information();

        // Input email exceed 100 half width characters and verify message
        memberInformation.email_exceed_100_half_width_characters();
    }

    @Test
    public void TC06_password_and_confirm_password_less_than_10_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformation.link_to_member_information();

        // Input password less than 8 half width characters and verify message
        memberInformation.password_less_than_10_half_width_characters();

        // Input confirm password less than 8 half width characters and verify message
        memberInformation.confirm_password_less_than_10_half_width_characters();
    }

    @Test
    public void TC07_password_and_confirm_password_exceed_50_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformation.link_to_member_information();

        // Input password exceed 50 half width characters and verify message
        memberInformation.password_exceed_50_half_width_characters();

        // Input confirm password exceed 50 half width characters and verify message
        memberInformation.confirm_password_exceed_50_half_width_characters();
    }

    @Test
    public void TC08_password_and_confirm_password_does_not_match() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformation.link_to_member_information();

        // Input password, confirm password does not match and verify message
        memberInformation.password_and_confirm_password_does_not_match();
    }

    @Test
    public void TC09_password_and_confirm_password_does_not_mix_alphanumerical_characters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformation.link_to_member_information();

        // Input password, confirm password not mix alphanumerical half width characters and verify message
        memberInformation.password_and_confirm_password_does_not_mix_alphanumerical_characters();
    }


    @Test
    public void TC10_leave_all_blank_company_profile_information() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Leave company name blank and verify message
        companyProfileInformation.leave_company_name_blank();

        // Leave date of establishment blank and verify message
        companyProfileInformation.leave_date_of_establishment_blank();

        // Leave address blank and verify message
        companyProfileInformation.leave_address_blank();

        // Leave domain blank and verify message
        companyProfileInformation.leave_url_blank();

        // Leave capital and verify message
        companyProfileInformation.leave_capital_blank();
    }

    @Test
    public void TC11_company_name_exceed_100_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input company name exceed 100 half width characters and verify message
        companyProfileInformation.company_name_exceed_100_half_width_characters();
    }

    @Test
    public void TC12_company_name_exceed_100_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input company name exceed 100 full width characters and verify message
        companyProfileInformation.company_name_exceed_100_full_width_characters();
    }

    @Test
    public void TC13_company_name_exceed_mix_100_half_and_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input company name exceed mix 100 half, full width characters and verify message
        companyProfileInformation.company_name_exceed_mix_100_half_and_full_width_characters();
    }

    @Test
    public void TC14_address_exceed_100_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input address exceed 100 half width characters and verify message
        companyProfileInformation.address_exceed_100_half_width_characters();
    }

    @Test
    public void TC15_address_exceed_100_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input address exceed 100 full width characters and verify message
        companyProfileInformation.address_exceed_100_full_width_characters();
    }

    @Test
    public void TC16_address_exceed_mix_100_half_and_and_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input address exceed 100 mix half, full width characters and verify message
        companyProfileInformation.address_exceed_mix_100_half_and_and_full_width_characters();
    }

    @Test
    public void TC17_url_exceed_50_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input domain exceed 50 half width characters and verify message
        companyProfileInformation.url_exceed_50_half_width_characters();
    }

    @Test
    public void TC18_url_exceed_50_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input domain exceed 50 full width characters and verify message
        companyProfileInformation.url_exceed_50_full_width_characters();
    }

    @Test
    public void TC19_url_exceed_mix_50_half_and_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input domain exceed mix 50 half, full width characters and verify message
        companyProfileInformation.url_exceed_mix_50_half_and_full_width_characters();
    }

    @Test
    public void TC20_capital_exceed_13_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformation.link_to_company_profile_information();

        // Input company name exceed 100 half width characters and verify message
        companyProfileInformation.capital_exceed_13_half_width_characters();
    }

    @Test
    public void TC21_leave_username_blank_personal_profile_information() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Leave username blank and verify message
        personalProfileInformation.leave_username_blank();
    }

    @Test
    public void TC22_username_exceed_50_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input username exceed 50 half width characters and verify message
        personalProfileInformation.username_exceed_50_half_width_characters();
    }

    @Test
    public void TC23_username_exceed_50_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input username exceed 50 full width characters and verify message
        personalProfileInformation.username_exceed_50_full_width_characters();
    }

    @Test
    public void TC24_username_exceed_mix_50_half_and_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input username exceed mix 50 half and full width characters and verify message
        personalProfileInformation.username_exceed_mix_50_half_and_full_width_characters();
    }

    @Test
    public void TC25_username_contains_space_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input username contains space half width characters and verify message
        personalProfileInformation.username_contains_space_characters();
    }

    @Test
    public void TC26_do_not_fill_in_all_tel_fields() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Do not fill in all tel fields and verify message
        personalProfileInformation.do_not_fill_in_all_tel_fields();
    }

    @Test
    public void TC27_tel_exceed_15_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input TEL exceed 50 half width characters and verify message
        personalProfileInformation.tel_exceed_15_half_width_characters();
    }

    @Test
    public void TC28_tel_contains_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input TEL contains full width half width characters and verify message
        personalProfileInformation.tel_contains_full_width_characters();
    }

    @Test
    public void TC29_tel_contains_letter_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input TEL contains letters half width characters and verify message
        personalProfileInformation.tel_contains_letter_characters();
    }

    @Test
    public void TC30_password_less_than_10_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input password less than 10 half width characters and verify message
        personalProfileInformation.password_less_than_10_half_width_characters();
    }

    @Test
    public void TC31_password_exceed_50_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input password exceed 50 half width characters and verify message
        personalProfileInformation.password_exceed_50_half_width_characters();
    }

    @Test
    public void TC32_password_do_not_mix_alphanumerical_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input password not mix alphanumerical half width characters and verify message
        personalProfileInformation.password_do_not_mix_alphanumerical_characters();
    }

    @Test
    public void TC33_password_contains_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input password not mix alphanumerical half width characters and verify message
        personalProfileInformation.password_contains_full_width_characters();
    }

    @Test
    public void TC34_email_signature_exceed_1000_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input email signature exceed 1000 half width characters and verify message
        personalProfileInformation.email_signature_exceed_1000_half_width_characters();
    }

    @Test
    public void TC35_email_signature_exceed_1000_full_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input email signature exceed 1000 full width characters and verify message
        personalProfileInformation.email_signature_exceed_1000_full_width_characters();
    }


    @Test
    public void TC36_email_signature_exceed_1000_half_width_characters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformation.link_to_personal_profile_information();

        // Input email signature exceed mix 1000 half and full width characters and verify message
        personalProfileInformation.email_signature_exceed_mix_1000_half_and_full_width_characters();
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        // take screenshot when test failed
        common.take_screenshot_when_test_fail(driver, result, "Create_Tenant");

        // close all browsers
        driver.quit();
    }
}
