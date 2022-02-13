package HBS;

import BasePage.createPartner.Business_partner_branch_information;
import BasePage.createPartner.Business_partner_information;
import BasePage.createPartner.Transaction_terms;
import Common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static Variable.Variable.*;

public class createPartner {
    @Test()
    public void 取引先登録() throws InterruptedException {

        // Init Common function
        Common cm = new Common();

        // Setup Webdriver
        WebDriver driver = cm.setupWebdriver();

        // Init Actions
        Actions key = new Actions(driver);

        //Login
        cm.login(driver, url + register_partner_path, master_email, master_password);

        //*** 取引先情報 ***//
        // Business partner information
        // Init Business partner information function
        Business_partner_information bpi = new Business_partner_information();

        // Block list
        bpi.block_list(driver, role, cm);

        // Corporate number
        bpi.corporate_number(driver, role, cm);

        // Customer name
        bpi.customer_name(driver, role, cm);

        // Account status
        bpi.account_status(driver, role, cm);

        // Customer evaluation
        bpi.customer_evaluation(driver, role, cm);

        // Country of Citizenship
        bpi.country_of_citizenship(driver, role, cm);

        // Establishment date
        bpi.establishment_date(driver, role, cm);

        // Fiscal year
        bpi.fiscal_year(driver, key,role, cm);

        // Address
        bpi.address(driver, role, cm);

        // TEL
        bpi.bpi_tel(driver, role, cm);

        // FAX
        bpi.bpi_fax(driver, role, cm);

        //URL
        bpi.url(driver, role, cm);

        // Number of employees
        bpi.number_of_employees(driver, role, cm);

        // Commercial distribution
        bpi.commercial_distribution(driver, role, cm);

        //Contract
        bpi.contract(driver, role, cm);

        // Capital
        bpi.capital(driver, role, cm);

        // Qualifications
        // P mark / ISMS
        bpi.P_mark_or_ISMS(driver, role, cm);

        // Invoice registration company
        bpi.invoice_registration_company(driver, role, cm);

        // Worker dispatch business
        bpi.worker_dispatch_business(driver, role, cm);

        // Comment
        bpi.comment(driver, role, cm);

        // Fixed comment
        bpi.fixed_comment(driver, role, cm);

        // Scroll up and switch to Business partner branch information tab
        bpi.scroll_up_and_switch_to_Business_partner_branch_information_tab(driver, role, cm);

        //*** 取引先支店情報 ***//
        // Business partner branch information
        // Init Business partner branch information function
        Business_partner_branch_information bpbi = new Business_partner_branch_information();

        // Click 支店を登録する button
        bpbi.register_a_branch(driver, role, cm);

        // Branch name
        bpbi.branch_name(driver, role, cm);

        // Address
        bpbi.branch_address(driver, role, cm);

        // TEL
        bpbi.branch_tel(driver, role, cm);

        // FAX
        bpbi.branch_fax(driver, role, cm);

        // Switch to Transaction terms tab
        bpbi.switch_to_transaction_terms(driver, role, cm);

        //*** 取引条件 ***//
        // Transaction terms
        // Init Transaction terms function
        Transaction_terms tt = new Transaction_terms();

        // Number of years required for transactions in range 1-999
        tt.number_of_years_required_for_transactions(driver, role, cm);

        // Capital required for transactions in range 0 - 9999999999999
        tt.capital_required_for_transactions(driver, role, cm);

        // Qualifications required for transactions
        tt.qualifications_required_for_transactions(driver, role, cm);

        // Click 登 録 button
        tt.finish_register_partner(driver, role, cm);

        // Close browser
        cm.closeBrowser(driver);
    }
}
