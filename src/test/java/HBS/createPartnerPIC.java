package HBS;

import BasePage.createPartnerPIC.Contact_information;
import BasePage.createPartnerPIC.Delivery_conditions;
import Common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static Variable.Variable.*;

public class createPartnerPIC {
    @Test
    public void main() throws InterruptedException {
        //Init Common function
        Common cm = new Common();

        // Config Webdriver
        WebDriver driver = cm.setupWebdriver();

        // Init Actions
        Actions key = new Actions(driver);

        //Login
        cm.login(driver, url + register_contact_path, master_email, master_password);

        //****** 取引先担当者情報 ****** //
        // Contact information
        // Init Contact information function
        Contact_information ci = new Contact_information();

        // Contact name
        ci.contact_name(driver);

        // Affiliated business partner
        ci.affiliated_business_partner(driver, key);

        // TO
        ci.TO(driver);

        // CC
        ci.CC(driver);

        // TEL
        ci.tel(driver);

        // Position
        ci.position(driver);

        // Department
        ci.department(driver);

        // In-house person in charge
        ci.in_house_person_in_charge(driver, key);

        // Last visit date
        ci.last_visit_date(driver, key);

        // Tag
        ci.tag(driver, key);

        // Compatibility
        ci.compatibility(driver);

        // Comment
        ci.comment(driver);

        // Fixed Comment
        ci.fixed_comment(driver);

        // Switch to Delivery conditions tab
        ci.switch_to_delivery_conditions_tab(driver);

        //****** 取引先担当者情報 ****** //
        // Delivery conditions
        // Init Delivery conditions function
        Delivery_conditions dc = new Delivery_conditions();

        // Proposal delivery
        dc.proposal_delivery(driver);

        // Personnel delivery
        dc.personnel_delivery(driver);

        // Desired area
        dc.desired_area(driver);

        // Click Register button
        dc.complete_create_partner_pic(driver);

        // Close browser
        cm.closeBrowser(driver);
    }
}
