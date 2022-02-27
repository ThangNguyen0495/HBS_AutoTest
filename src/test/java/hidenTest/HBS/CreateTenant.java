package hidenTest.HBS;

import BasePage.createTenant.Company_profile_information;
import BasePage.createTenant.Member_information;
import BasePage.createTenant.Payment_information;
import BasePage.createTenant.Personal_profile_information;
import Common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateTenant {
    Common cm;
    WebDriver driver;
    Member_information mi;
    Company_profile_information cpi;
    Personal_profile_information ppi;
    Payment_information pi;
    Actions key;

    @BeforeMethod()
    @Parameters("headless")
    public void init(Boolean headless) {
        // Init Common function
        cm = new Common();

        // Config Webdriver
        driver = cm.setupWebdriver(headless);

        // Init Actions
        key = new Actions(driver);

        // Init Member information function
        mi = new Member_information();

        // Init Company profile information function
        cpi = new Company_profile_information();

        // Init Personal profile information function
        ppi = new Personal_profile_information();

        // Init Payment information function
        pi = new Payment_information();
    }

    @Test
    @Parameters("url")
    public void TC01_process_create_tenant(String url) throws InterruptedException {
        //****** 会員情報 ****** //
        // Member information
        // Link to Create tenant - Member information tab
        mi.link_to_member_information(driver, url);

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
        cpi.establishment_date(driver, key);

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

        // Close browser
        driver.close();
    }

    @Test(priority = 1)
    @Parameters("url")
    public void TC02_leave_all_blank_member_information(String url) throws InterruptedException {
        // Link to Create tenant - Member information tab
        driver.get(url);

        // Leave email blank and verify message
        mi.leave_email_blank(driver, key);

        // Leave password blank and verify message
        mi.leave_password_blank(driver, key);

        // Leave confirm password blank and verify message
        mi.leave_confirm_password_blank(driver, key);

        // Verify Register member information button is getting disable
        mi.register_member_information_should_be_disable(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 2)
    @Parameters("url")
    public void TC03_email_password_and_confirm_password_contains_full_width_characters(String url) throws InterruptedException {
        // Link to Create tenant - Member information tab
        driver.get(url);

        // Input email contains full width characters and verify message
        mi.email_contains_full_width_characters(driver);

        // Input password contains full width characters and verify message
        mi.password_contains_full_width_characters(driver);

        // Input confirm password contains full width characters and verify message
        mi.confirm_password_contains_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 2)
    @Parameters("url")
    public void TC04_email_contains_space_characters(String url) throws InterruptedException {
        // Link to Create tenant - Member information tab
        driver.get(url);

        // Input email contains space characters and verify message
        mi.email_contains_space_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 4)
    @Parameters("url")
    public void TC05_email_exceed_100_half_width_characters(String url) throws InterruptedException {
        // Link to Create tenant - Member information tab
        driver.get(url);

        // Input email exceed 100 half width characters and verify message
        mi.email_exceed_100_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 5)
    @Parameters("url")
    public void TC06_password_and_confirm_password_less_than_10_half_width_characters(String url) throws InterruptedException {
        // Link to Create tenant - Member information tab
        driver.get(url);

        // Input password less than 8 half width characters and verify message
        mi.password_less_than_10_half_width_characters(driver);

        // Input confirm password less than 8 half width characters and verify message
        mi.confirm_password_less_than_10_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 6)
    @Parameters("url")
    public void TC07_password_and_confirm_password_exceed_50_half_width_characters(String url) throws InterruptedException {
        // Link to Create tenant - Member information tab
        driver.get(url);

        // Input password exceed 50 half width characters and verify message
        mi.password_exceed_50_half_width_characters(driver);

        // Input confirm password exceed 50 half width characters and verify message
        mi.confirm_password_exceed_50_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 7)
    @Parameters("url")
    public void TC08_password_and_confirm_password_does_not_match(String url) throws InterruptedException {
        // Link to Create tenant - Member information tab
        driver.get(url);

        // Input password, confirm password does not match and verify message
        mi.password_and_confirm_password_does_not_match(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 8)
    @Parameters("url")
    public void TC09_password_and_confirm_password_does_not_mix_alphanumerical_characters(String url) throws InterruptedException {
        // Link to Create tenant - Member information tab
        driver.get(url);

        // Input password, confirm password not mix alphanumerical half width characters and verify message
        mi.password_and_confirm_password_does_not_mix_alphanumerical_characters(driver);

        // Close browser
        driver.close();
    }


    @Test(priority = 9)
    @Parameters("url_cpi")
    public void TC10_leave_all_blank_company_profile_information(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Leave company name blank and verify message
        cpi.leave_company_name_blank(driver, key);

        // Leave date of establishment blank and verify message
        cpi.leave_date_of_establishment_blank(driver);

        // Leave address blank and verify message
        cpi.leave_address_blank(driver, key);

        // Leave url blank and verify message
        cpi.leave_url_blank(driver, key);

        // Leave capital and verify message
        cpi.leave_capital_blank(driver, key);

        // Close browser
        driver.close();
    }

    @Test(priority = 10)
    @Parameters("url_cpi")
    public void TC11_company_name_exceed_100_half_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input company name exceed 100 half width characters and verify message
        cpi.company_name_exceed_100_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 11)
    @Parameters("url_cpi")
    public void TC12_company_name_exceed_100_full_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input company name exceed 100 full width characters and verify message
        cpi.company_name_exceed_100_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 12)
    @Parameters("url_cpi")
    public void TC13_company_name_exceed_mix_100_half_and_full_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input company name exceed mix 100 half, full width characters and verify message
        cpi.company_name_exceed_mix_100_half_and_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 13)
    @Parameters("url_cpi")
    public void TC14_address_exceed_100_half_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input address exceed 100 half width characters and verify message
        cpi.address_exceed_100_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 14)
    @Parameters("url_cpi")
    public void TC15_address_exceed_100_full_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input address exceed 100 full width characters and verify message
        cpi.address_exceed_100_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 15)
    @Parameters("url_cpi")
    public void TC16_address_exceed_mix_100_half_and_and_full_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input address exceed 100 mix half, full width characters and verify message
        cpi.address_exceed_mix_100_half_and_and_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 16)
    @Parameters("url_cpi")
    public void TC17_url_exceed_50_half_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input URL exceed 50 half width characters and verify message
        cpi.url_exceed_50_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 17)
    @Parameters("url_cpi")
    public void TC18_url_exceed_50_full_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input URL exceed 50 full width characters and verify message
        cpi.url_exceed_50_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 18)
    @Parameters("url_cpi")
    public void TC19_url_exceed_mix_50_half_and_full_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input URL exceed mix 50 half, full width characters and verify message
        cpi.url_exceed_mix_50_half_and_full_width_characters(driver);

        // Close browser
        driver.close();
    }
    
    @Test(priority = 19)
    @Parameters("url_cpi")
    public void TC20_capital_exceed_13_half_width_characters(String url_cpi) throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        driver.get(url_cpi);

        // Input company name exceed 100 half width characters and verify message
        cpi.capital_exceed_13_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 20)
    @Parameters("url_ppi")
    public void TC21_leave_username_blank_personal_profile_information(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Leave username blank and verify message
        ppi.leave_username_blank(driver, key);

        // Close browser
        driver.close();
    }

    @Test(priority = 21)
    @Parameters("url_ppi")
    public void TC22_username_exceed_50_half_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input username exceed 50 half width characters and verify message
        ppi.username_exceed_50_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 22)
    @Parameters("url_ppi")
    public void TC23_username_exceed_50_full_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input username exceed 50 full width characters and verify message
        ppi.username_exceed_50_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 23)
    @Parameters("url_ppi")
    public void TC24_username_exceed_mix_50_half_and_full_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input username exceed mix 50 half and full width characters and verify message
        ppi.username_exceed_mix_50_half_and_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 24)
    @Parameters("url_ppi")
    public void TC25_username_contains_space_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input username contains space half width characters and verify message
        ppi.username_contains_space_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 25)
    @Parameters("url_ppi")
    public void TC26_do_not_fill_in_all_tel_fields(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Do not fill in all tel fields and verify message
        ppi.do_not_fill_in_all_tel_fields(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 26)
    @Parameters("url_ppi")
    public void TC27_tel_exceed_15_half_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input TEL exceed 50 half width characters and verify message
        ppi.tel_exceed_15_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 27)
    @Parameters("url_ppi")
    public void TC28_tel_contains_full_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input TEL contains full width half width characters and verify message
        ppi.tel_contains_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 28)
    @Parameters("url_ppi")
    public void TC29_tel_contains_letter_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input TEL contains letters half width characters and verify message
        ppi.tel_contains_letter_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 29)
    @Parameters("url_ppi")
    public void TC30_password_less_than_10_half_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input password less than 10 half width characters and verify message
        ppi.password_less_than_10_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 30)
    @Parameters("url_ppi")
    public void TC31_password_exceed_50_half_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input password exceed 50 half width characters and verify message
        ppi.password_exceed_50_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 31)
    @Parameters("url_ppi")
    public void TC32_password_do_not_mix_alphanumerical_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input password not mix alphanumerical half width characters and verify message
        ppi.password_do_not_mix_alphanumerical_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 32)
    @Parameters("url_ppi")
    public void TC33_password_contains_full_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input password not mix alphanumerical half width characters and verify message
        ppi.password_contains_full_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 33)
    @Parameters("url_ppi")
    public void TC34_email_signature_exceed_1000_half_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input email signature exceed 1000 half width characters and verify message
        ppi.email_signature_exceed_1000_half_width_characters(driver);

        // Close browser
        driver.close();
    }

    @Test(priority = 34)
    @Parameters("url_ppi")
    public void TC35_email_signature_exceed_1000_full_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input email signature exceed 1000 full width characters and verify message
        ppi.email_signature_exceed_1000_full_width_characters(driver);

        // Close browser
        driver.close();
    }


    @Test(priority = 35)
    @Parameters("url_ppi")
    public void TC36_email_signature_exceed_1000_half_width_characters(String url_ppi) throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        driver.get(url_ppi);

        // Input email signature exceed mix 1000 half and full width characters and verify message
        ppi.email_signature_exceed_mix_1000_half_and_full_width_characters(driver);

        // Close browser
        driver.close();
    }
}
