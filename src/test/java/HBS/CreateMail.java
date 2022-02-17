package HBS;

import BasePage.createMail.Attachment;
import BasePage.createMail.Basic_information;
import BasePage.createMail.Destination_selection;
import BasePage.createMail.Final_confirmation;
import Common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class CreateMail {

    Common cm;
    WebDriver driver;
    Actions key;
    Basic_information bi;
    Attachment a;
    Destination_selection ds;
    Final_confirmation fc;

    @BeforeMethod
    @Parameters({"headless", "email", "password", "url"})
    public void init(Boolean headless, String email, String password, String url) throws InterruptedException {
        // Init Common function
        cm = new Common();

        // Config Webdriver
        driver = cm.setupWebdriver(headless);

        // Init Actions
        key = new Actions(driver);

        //Login
        cm.login(driver, url, email, password);

        // Init Basic information function
        bi = new Basic_information();

        // Init Attachment function
        a = new Attachment();

        // Init Destination selection function
        ds = new Destination_selection();

        // Init Final confirmation function
        fc = new Final_confirmation();
    }

    @Test(priority = 1)
    @Parameters({"role", "url_mail_list"})
    public void TC01_process_create_mail(String role, String url_mail_list) throws InterruptedException, IOException {
        //****** 基本情報 ****** //
        // Basic information
        // Format
        bi.format(driver, role, cm);

        // Distributor
        // random distributor in range 1-20
        bi.distributor(driver, key, role, cm);

        // Subject
        bi.subject(driver, role, cm);

        // Insertion
        bi.insertion(driver, role, cm);

        // Send a copy to the distributor
        bi.send_a_copy_to_the_distributor(driver, role, cm);

        // Next to 添付ファイル_Step
        bi.next_to_attachment_step(driver, role, cm);

        //****** 添付ファイル ****** //
        // Attachment
        // Generate test file
        // Capacity: MB
        a.generate_test_file(2);

        // Upload file
        a.upload_file(driver, role, cm);

        // Next to 宛先選択_Step
        a.next_to_destination_selection_step(driver, role, cm);

        //****** 宛先選択 ****** //
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Select contact
        ds.select_contact(driver, role, cm);

        // Next to 最終確認_step
        ds.next_to_final_confirmation(driver, role, cm);

        //****** 最終確認 ****** //
        // Final confirmation
        // Open delivery time setting popup
        fc.open_delivery_time_setting_popup(driver, role, cm);

        // Select date
        fc.select_date(driver, role, cm, "");

        // Select time and select again when time incorrect
        fc.select_time_and_select_again_when_time_incorrect(driver, key, role, cm, url_mail_list);

        // Close browser
        driver.close();
    }

    @Test(priority = 1)
    public void TC_02_leave_all_blank_basic_information() throws InterruptedException {
        bi.leave_distributor_blank(driver);
        bi.leave_subject_blank(driver, key);
        bi.leave_insertion_blank(driver, key);
        driver.close();
    }

    @Test(priority = 3)
    public void TC_03_subject_exceed_100_half_width_characters() throws InterruptedException {
        bi.subject_exceed_100_half_width_characters(driver);
        driver.close();
    }

    @Test(priority = 4)
    public void TC_04_subject_exceed_100_full_width_characters() throws InterruptedException {
        bi.subject_exceed_100_full_width_characters(driver);
        driver.close();
    }

    @Test(priority = 5)
    public void TC_05_subject_exceed_100_mix_half_and_full_width_characters() throws InterruptedException {
        bi.subject_exceed_100_mix_half_and_full_width_characters(driver);
        driver.close();
    }


    @Test(priority = 6)
    public void TC_06_insertion_exceed_10000_half_width_characters() throws InterruptedException {
        bi.insertion_exceed_10000_half_width_characters(driver);
        driver.close();
    }

    @Test(priority = 7)
    public void TC_07_insertion_exceed_5000_full_width_characters() throws InterruptedException {
        bi.insertion_exceed_5000_full_width_characters(driver);
        driver.close();
    }

    @Test(priority = 8)
    public void TC_08_insertion_exceed_5000_mix_half_and_full_width_characters() throws InterruptedException {
        bi.insertion_exceed_5000_mix_half_and_full_width_characters(driver);
        driver.close();
    }

    @Test(priority = 9)
    public void TC_09_delete_button_should_be_disable() {
        bi.delete_button_should_be_disable(driver);
        driver.close();
    }

    @Test(priority = 10)
    @Parameters({"role","url_mail_list"})
    public void TC_10_do_you_want_to_delete_this_delivered_email_OK_basic_information(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.do_you_want_to_delete_this_delivered_email_OK(driver, url_mail_list);
    }

    @Test(priority = 11)
    @Parameters({"role","url"})
    public void TC_11_do_you_want_to_delete_this_delivered_email_Cancel_basic_information(String role, String url) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.do_you_want_to_delete_this_delivered_email_Cancel(driver, url);
    }

    @Test(priority = 12)
    @Parameters({"role","url_mail_list"})
    public void TC_12_make_a_copy_basic_information(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.make_a_copy(driver, url_mail_list);
    }

    @Test(priority = 13)
    @Parameters({"role","url_mail_list"})
    public void TC_13_save_as_draft_basic_information(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.save_as_draft(driver, url_mail_list);
    }

    @Test(priority = 9)
    @Parameters({"role", "capacity"})
    public void TC_09_upload_maximum_capacity_1_file(String role, long capacity) throws IOException, InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        sleep(3000);
        a.upload_maximum_capacity_1_file(driver, capacity);
        driver.close();
    }

    @Test(priority = 10)
    @Parameters({"role", "capacity"})
    public void TC_10_upload_maximum_capacity_multi_file(String role, long capacity) throws IOException, InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        sleep(3000);
        a.upload_maximum_capacity_multi_file(driver, capacity);
        driver.close();
    }

    @Test(priority = 11)
    @Parameters({"role", "capacity"})
    public void TC_11_upload_exceed_maximum_capacity_1_file(String role, long capacity) throws IOException, InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        sleep(3000);
        a.upload_exceed_maximum_capacity_1_file(driver, capacity);
        driver.close();
    }

    @Test(priority = 12)
    @Parameters({"role", "capacity"})
    public void TC_12_upload_exceed_maximum_capacity_multi_file(String role, long capacity) throws IOException, InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        sleep(3000);
        a.upload_exceed_maximum_capacity_multi_file(driver, capacity);
        driver.close();
    }

    @Test(priority = 13)
    @Parameters("role")
    public void TC_13_deliver_the_matter_do_not_select_condition(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_matter_do_not_select_condition(driver);
        driver.close();
    }

    @Test(priority = 14)
    @Parameters("role")
    public void TC_14_deliver_the_matter_only_select_delivery_occupation_development(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_matter_only_select_delivery_occupation_development(driver);
        driver.close();
    }

    @Test(priority = 15)
    @Parameters("role")
    public void TC_15_deliver_the_matter_only_select_delivery_occupation_infrastructure(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_matter_only_select_delivery_occupation_infrastructure(driver);
        driver.close();
    }

    @Test(priority = 16)
    @Parameters("role")
    public void TC_16_deliver_the_matter_only_select_delivery_occupation_others(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_matter_only_select_delivery_occupation_others(driver);
        driver.close();
    }

    @Test(priority = 17)
    @Parameters("role")
    public void TC_17_deliver_the_personnel_do_not_select_condition(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_do_not_select_condition(driver);
        driver.close();
    }

    @Test(priority = 18)
    @Parameters("role")
    public void TC_18_deliver_the_personnel_only_select_delivery_occupation_development(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_only_select_delivery_occupation_development(driver);
        driver.close();
    }

    @Test(priority = 19)
    @Parameters("role")
    public void TC_19_deliver_the_personnel_only_select_delivery_occupation_infrastructure(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_only_select_delivery_occupation_infrastructure(driver);
        driver.close();
    }

    @Test(priority = 20)
    @Parameters("role")
    public void TC_20_deliver_the_personnel_only_select_delivery_occupation_others(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_only_select_delivery_occupation_others(driver);
        driver.close();
    }

    @Test(priority = 21)
    @Parameters("role")
    public void TC_21_deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others(driver);
        driver.close();
    }

    @Test(priority = 22)
    @Parameters("role")
    public void TC_22_do_not_select_account_status(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_not_select_account_status(driver, role, cm);
        driver.close();
    }

    @Test(priority = 23)
    @Parameters("role")
    public void TC_23_do_not_select_in_house_person_in_charge(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_not_select_in_house_person_in_charge(driver, role, cm);
        driver.close();
    }

    @Test(priority = 24)
    @Parameters("role")
    public void TC_24_do_not_select_compatibility(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_not_select_compatibility(driver, role, cm);
        driver.close();
    }

    @Test(priority = 25)
    @Parameters("role")
    public void TC_25_do_not_select_tag(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_not_select_tag(driver, role, cm);
        driver.close();
    }

    @Test(priority = 26)
    @Parameters("role")
    public void TC_26_do_not_select_tag(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.select_exceeds_5_tags(driver, role, cm, key);
        driver.close();
    }

    @Test(priority = 27)
    @Parameters({"role", "url_mail_list"})
    public void TC_27(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.do_you_want_to_delete_this_delivered_email_OK(driver, url_mail_list);
//        driver.close();
    }
}