package HBS;

import BasePage.createMail.Attachment;
import BasePage.createMail.Basic_information;
import BasePage.createMail.Destination_selection;
import BasePage.createMail.Final_confirmation;
import Common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.IOException;

import static Variable.Variable.*;

public class CreateMail {
    @Test(invocationCount = 10)

    public void 配信メール予約() throws InterruptedException, IOException {

        // Init Common function
        Common cm = new Common();

        // Config Webdriver
        WebDriver driver = cm.setupWebdriver();

        // Init Actions
        Actions key = new Actions(driver);

        //Login
        cm.login(driver, url + delivery_email_reservation_path, master_email, master_password);

        //****** 基本情報 ****** //
        // Basic information
        // Init Basic information function
        Basic_information bi = new Basic_information();

        // Format
        bi.format(driver);

        // Distributor
        // random distributor in range 1-20
        bi.distributor(driver, key);

        // Subject
        bi.subject(driver);

        // Insertion
        bi.insertion(driver);

        // Send a copy to the distributor
        bi.send_a_copy_to_the_distributor(driver);

        // Next to 添付ファイル_Step
        bi.next_to_attachment_step(driver);

        //****** 添付ファイル ****** //
        // Attachment
        // Init Attachment function
        Attachment a = new Attachment();

        // Generate test file
        // Capacity: MB
        a.generate_test_file(2);

        // Upload file
        a.upload_file(driver);

        // Next to 宛先選択_Step
        a.next_to_destination_selection_step(driver);

        //****** 宛先選択 ****** //
        // Init Destination selection function
        Destination_selection ds = new Destination_selection();

        // Delivery information
        ds.delivery_information(driver);

        // Commitment
        ds.commitment(driver, key);

        // Search
        ds.search_contact_by_condition(driver);

        // Select contact
        ds.select_contact(driver);

        // Next to 最終確認_step
        ds.next_to_final_confirmation(driver);

        //****** 最終確認 ****** //
        // Final confirmation
        // Init Final confirmation function
        Final_confirmation fc = new Final_confirmation();

        // Open delivery time setting popup
        fc.open_delivery_time_setting_popup(driver);

        // Select date
        fc.select_date(driver);

        // Select time and select again when time incorrect
        fc.select_time_and_select_again_when_time_incorrect(driver,key);

        // Close browser
        cm.closeBrowser(driver);
    }
}