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
    public void 取引先担当者登録() throws InterruptedException {
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
        ci.contact_name(driver, role, cm);

        // Affiliated business partner
        ci.affiliated_business_partner(driver, key, role, cm);

        // TO
        ci.TO(driver, role, cm);

        // CC
        ci.CC(driver, role, cm);

        // TEL
        ci.tel(driver, role, cm);

        // Position
        ci.position(driver, role, cm);

        // Department
        ci.department(driver, role, cm);

        // In-house person in charge
        ci.in_house_person_in_charge(driver, key, role, cm);

        // Last visit date
        ci.last_visit_date(driver, key, role, cm);

        // Tag
        ci.tag(driver, key, role, cm);

        // Compatibility
        ci.compatibility(driver, role, cm);

        // Comment
        ci.comment(driver, role, cm);

        // Fixed Comment
        ci.fixed_comment(driver, role, cm);

        // Switch to Delivery conditions tab
        ci.switch_to_delivery_conditions_tab(driver, role, cm);

        //****** 取引先担当者情報 ****** //
        // Delivery conditions
        // Init Delivery conditions function
        Delivery_conditions dc = new Delivery_conditions();

        // Proposal delivery
        dc.proposal_delivery(driver, role, cm);

        // Personnel delivery
        dc.personnel_delivery(driver, role, cm);

        // Desired area
        dc.desired_area(driver, role, cm);

        // Click Register button
        dc.complete_create_partner_pic(driver, role, cm);

        // Close browser
        cm.closeBrowser(driver);
    }
}
