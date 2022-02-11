package CreateDummyData;

import BasePage.createPartner.Business_partner_branch_information;
import BasePage.createPartner.Business_partner_information;
import BasePage.createPartner.Transaction_terms;
import Common.Common;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static Variable.Variable.*;

public class RegisterPartner {
    @Test
    public void main() throws InterruptedException {

        // Init Common function
        Common cm = new Common();

        // Setup Webdriver
        WebDriver driver = cm.setupWebdriver();

        //Login
        cm.login(driver,url + register_partner_path, master_email, master_password);

        //*** 取引先情報 ***//
        // Business partner information
        // Init Business partner information function
        Business_partner_information bpi = new Business_partner_information();

        // Block list
        bpi.block_list(driver);

        // Corporate number
        bpi.corporate_number(driver);

        // Customer name
        bpi.customer_name(driver);

        // Account status
        bpi.account_status(driver);

        // Customer evaluation
        bpi.customer_evaluation(driver);

        // Country of Citizenship
        bpi.country_of_citizenship(driver);

        // Establishment date
        bpi.establishment_date(driver);

        // Fiscal year
        bpi.fiscal_year(driver);

        // Address
        bpi.address(driver);

        // TEL
        bpi.bpi_tel(driver);

        // FAX
        bpi.bpi_fax(driver);

        //URL
        bpi.url(driver);

        // Number of employees
        bpi.number_of_employees(driver);

        // Commercial distribution
        bpi.commercial_distribution(driver);

        //Contract
        bpi.contract(driver);

        // Capital
        bpi.capital(driver);

        // Qualifications
        // P mark / ISMS
        bpi.P_mark_or_ISMS(driver);

        // Invoice registration company
        bpi.invoice_registration_company(driver);

        // Worker dispatch business
        bpi.worker_dispatch_business(driver);

        // Comment
        bpi.comment(driver);

        // Fixed comment
        bpi.fixed_comment(driver);

        // Scroll up and switch to Business partner branch information tab
        bpi.scroll_up_and_switch_to_Business_partner_branch_information_tab(driver);

        //*** 取引先支店情報 ***//
        // Business partner branch information
        // Init Business partner branch information function
        Business_partner_branch_information bpbi = new Business_partner_branch_information();

        // Click 支店を登録する button
        bpbi.register_a_branch(driver);

        // Branch name
        bpbi.branch_name(driver);

        // Address
        bpbi.branch_address(driver);

        // TEL
        bpbi.branch_tel(driver);

        // FAX
        bpbi.branch_fax(driver);

        // Switch to Transaction terms tab
        bpbi.switch_to_transaction_terms(driver);

        //*** 取引条件 ***//
        // Transaction terms
        // Init Transaction terms function
        Transaction_terms tt = new Transaction_terms();

        // Number of years required for transactions in range 1-999
        tt.number_of_years_required_for_transactions(driver);

        // Capital required for transactions in range 0 - 9999999999999
        tt.capital_required_for_transactions(driver);

        // Qualifications required for transactions
        tt.qualifications_required_for_transactions(driver);

        // Click 登 録 button
        tt.finish_register_partner(driver);

        // Close browser
        cm.closeBrowser(driver);

    }
}
