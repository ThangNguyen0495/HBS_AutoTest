package HBS;

import BasePage.createTenant.Company_profile_information;
import BasePage.createTenant.Member_information;
import BasePage.createTenant.Payment_information;
import BasePage.createTenant.Personal_profile_information;
import Common.Common;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static Variable.Variable.create_tenant_path;
import static Variable.Variable.url;

public class CreateTenant {

    @Test
    // Member information
    public void createTenant() throws InterruptedException {

        // Init Common function
        Common cm = new Common();

        // Config Webdriver
        WebDriver driver = cm.setupWebdriver();

        //****** 会員情報 ****** //
        // Member information
        // Init Member information function
        Member_information mi = new Member_information();

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
        // Init Company profile information function
        Company_profile_information cpi = new Company_profile_information();

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
        // Init Personal profile information function
        Personal_profile_information ppi = new Personal_profile_information();

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
        // Init Payment information function
        Payment_information pi = new Payment_information();

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
        cm.closeBrowser(driver);
    }
}
