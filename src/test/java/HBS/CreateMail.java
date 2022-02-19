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
//        ds.commitment(driver, key, role, cm);

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
    public void TC_03_subject_exceed_100_half_width_characters() {
        bi.subject_exceed_100_half_width_characters(driver);
        driver.close();
    }

    @Test(priority = 4)
    public void TC_04_subject_exceed_100_full_width_characters() {
        bi.subject_exceed_100_full_width_characters(driver);
        driver.close();
    }

    @Test(priority = 5)
    public void TC_05_subject_exceed_100_mix_half_and_full_width_characters() {
        bi.subject_exceed_100_mix_half_and_full_width_characters(driver);
        driver.close();
    }


    @Test(priority = 6)
    public void TC_06_insertion_exceed_10000_half_width_characters() {
        bi.insertion_exceed_10000_half_width_characters(driver);
        driver.close();
    }

    @Test(priority = 7)
    public void TC_07_insertion_exceed_5000_full_width_characters() {
        bi.insertion_exceed_5000_full_width_characters(driver);
        driver.close();
    }

    @Test(priority = 8)
    public void TC_08_insertion_exceed_5000_mix_half_and_full_width_characters() {
        bi.insertion_exceed_5000_mix_half_and_full_width_characters(driver);
        driver.close();
    }

    @Test(priority = 9)
    public void TC_09_delete_button_should_be_disable() {
        bi.delete_button_should_be_disable(driver);
        driver.close();
    }

    @Test(priority = 10)
    @Parameters({"role", "url_mail_list"})
    public void TC_10_do_you_want_to_delete_this_delivered_email_OK_basic_information(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.do_you_want_to_delete_this_delivered_email_OK(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 11)
    @Parameters({"role"})
    public void TC_11_do_you_want_to_delete_this_delivered_email_Cancel_basic_information(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.do_you_want_to_delete_this_delivered_email_Cancel(driver);
        driver.close();
    }

    @Test(priority = 12)
    @Parameters({"role", "url_mail_list"})
    public void TC_12_make_a_copy_basic_information(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.make_a_copy(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 13)
    @Parameters({"role", "url_mail_list"})
    public void TC_13_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_basic_information(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.would_you_like_to_change_this_delivery_email_to_Draft_status_OK(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 14)
    @Parameters({"role"})
    public void TC_14_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_basic_information(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.back_to_basic_information_step(driver, role, cm);
        bi.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel(driver);
        driver.close();
    }


    @Test(priority = 15)
    @Parameters({"role", "capacity"})
    public void TC_15_upload_maximum_capacity_1_file(String role, long capacity) throws IOException, InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        sleep(3000);
        a.upload_maximum_capacity_1_file(driver, capacity);
        driver.close();
    }

    @Test(priority = 16)
    @Parameters({"role", "capacity"})
    public void TC_16_upload_maximum_capacity_multi_file(String role, long capacity) throws IOException, InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        sleep(3000);
        a.upload_maximum_capacity_multi_file(driver, capacity);
        driver.close();
    }

    @Test(priority = 17)
    @Parameters({"role", "capacity"})
    public void TC_17_upload_exceed_maximum_capacity_1_file(String role, long capacity) throws IOException, InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        sleep(3000);
        a.upload_exceed_maximum_capacity_1_file(driver, capacity);
        driver.close();
    }

    @Test(priority = 18)
    @Parameters({"role", "capacity"})
    public void TC_18_upload_exceed_maximum_capacity_multi_file(String role, long capacity) throws IOException, InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        sleep(3000);
        a.upload_exceed_maximum_capacity_multi_file(driver, capacity);
        driver.close();
    }

    @Test(priority = 19)
    @Parameters({"role", "url_mail_list"})
    public void TC_19_do_you_want_to_delete_this_delivered_email_OK_attachment(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.do_you_want_to_delete_this_delivered_email_OK(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 20)
    @Parameters({"role"})
    public void TC_20_do_you_want_to_delete_this_delivered_email_Cancel_attachment(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.do_you_want_to_delete_this_delivered_email_Cancel(driver);
        driver.close();
    }

    @Test(priority = 21)
    @Parameters({"role", "url_mail_list"})
    public void TC_21_make_a_copy_attachment(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.make_a_copy(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 22)
    @Parameters({"role", "url_mail_list"})
    public void TC_22_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_attachment(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.would_you_like_to_change_this_delivery_email_to_Draft_status_OK(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 23)
    @Parameters({"role"})
    public void TC_23_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_attachment(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel(driver);
        driver.close();
    }

    @Test(priority = 24)
    @Parameters("role")
    public void TC_24_deliver_the_matter_do_not_select_condition(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_matter_do_not_select_condition(driver);
        driver.close();
    }

    @Test(priority = 25)
    @Parameters("role")
    public void TC_25_deliver_the_matter_only_select_delivery_occupation_development(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_matter_only_select_delivery_occupation_development(driver);
        driver.close();
    }

    @Test(priority = 26)
    @Parameters("role")
    public void TC_26_deliver_the_matter_only_select_delivery_occupation_infrastructure(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_matter_only_select_delivery_occupation_infrastructure(driver);
        driver.close();
    }

    @Test(priority = 27)
    @Parameters("role")
    public void TC_27_deliver_the_matter_only_select_delivery_occupation_others(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_matter_only_select_delivery_occupation_others(driver);
        driver.close();
    }

    @Test(priority = 28)
    @Parameters("role")
    public void TC_28_deliver_the_personnel_do_not_select_condition(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_do_not_select_condition(driver);
        driver.close();
    }

    @Test(priority = 29)
    @Parameters("role")
    public void TC_29_deliver_the_personnel_only_select_delivery_occupation_development(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_only_select_delivery_occupation_development(driver);
        driver.close();
    }

    @Test(priority = 30)
    @Parameters("role")
    public void TC_30_deliver_the_personnel_only_select_delivery_occupation_infrastructure(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_only_select_delivery_occupation_infrastructure(driver);
        driver.close();
    }

    @Test(priority = 31)
    @Parameters("role")
    public void TC_31_deliver_the_personnel_only_select_delivery_occupation_others(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_only_select_delivery_occupation_others(driver);
        driver.close();
    }

    @Test(priority = 32)
    @Parameters("role")
    public void TC_32_deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others(driver);
        driver.close();
    }

    @Test(priority = 33)
    @Parameters("role")
    public void TC_33_do_not_select_account_status(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_not_select_account_status(driver, role, cm);
        driver.close();
    }

    @Test(priority = 34)
    @Parameters("role")
    public void TC_34_do_not_select_in_house_person_in_charge(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_not_select_in_house_person_in_charge(driver, role, cm);
        driver.close();
    }

    @Test(priority = 35)
    @Parameters("role")
    public void TC_35_do_not_select_compatibility(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_not_select_compatibility(driver, role, cm);
        driver.close();
    }

    @Test(priority = 36)
    @Parameters("role")
    public void TC_36_do_not_select_tag(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_not_select_tag(driver, role, cm);
        driver.close();
    }

    @Test(priority = 37)
    @Parameters("role")
    public void TC_37_select_exceeds_5_tags(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.select_exceeds_5_tags(driver, role, cm, key);
        driver.close();
    }

    @Test(priority = 38)
    @Parameters({"role", "url_mail_list"})
    public void TC_38_do_you_want_to_delete_this_delivered_email_OK_destination_selection(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_you_want_to_delete_this_delivered_email_OK(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 39)
    @Parameters({"role"})
    public void TC_39_do_you_want_to_delete_this_delivered_email_Cancel_destination_selection(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.do_you_want_to_delete_this_delivered_email_Cancel(driver);
        driver.close();
    }

    @Test(priority = 40)
    @Parameters({"role", "url_mail_list"})
    public void TC_40_make_a_copy_destination_selection(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.make_a_copy(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 41)
    @Parameters({"role", "url_mail_list"})
    public void TC_41_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_destination_selection(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.would_you_like_to_change_this_delivery_email_to_Draft_status_OK(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 42)
    @Parameters({"role"})
    public void TC_42_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_destination_selection(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel(driver);
        driver.close();
    }

    @Test(priority = 43)
    @Parameters({"role"})
    public void TC_43_back_to_basic_information_from_destination_selection(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.back_to_basic_information_step(driver, role, cm);
        driver.close();
    }

    @Test(priority = 44)
    @Parameters({"role"})
    public void TC_44_back_to_attachment_from_destination_selection(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.back_to_attachment_step(driver, key,role, cm);
        driver.close();
    }

    @Test(priority = 45)
    @Parameters({"role"})
    public void TC_45_leave_search_template_name_blank(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.leave_search_template_name_blank(driver, key);
        driver.close();
    }

    @Test(priority = 46)
    @Parameters({"role"})
    public void TC_46_search_template_name_exceed_50_half_width_characters(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.search_template_name_exceed_50_half_width_characters(driver);
        driver.close();
    }

    @Test(priority = 47)
    @Parameters({"role"})
    public void TC_47_search_template_name_exceed_50_full_width_characters(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.search_template_name_exceed_50_full_width_characters(driver);
        driver.close();
    }

    @Test(priority = 48)
    @Parameters({"role"})
    public void TC_48_search_template_name_exceed_mix_50_half_and_full_width_characters(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.search_template_name_exceed_mix_50_half_and_full_width_characters(driver);
        driver.close();
    }

    @Test(priority = 49)
    @Parameters({"role"})
    public void TC_49_create_search_template_name_OK(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.create_search_template_name_OK(driver);
        driver.close();
    }

    @Test(priority = 50)
    @Parameters({"role"})
    public void TC_50_create_search_template_name_Cancel(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.create_search_template_name_Cancel(driver);
        driver.close();
    }

    @Test(priority = 51)
    @Parameters({"role"})
    public void TC_51_input_available_search_template_name(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.input_available_search_template_name(driver);
        driver.close();
    }

    @Test(priority = 52)
    @Parameters({"role"})
    public void TC_52_set_cancel_search_template_as_default(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.set_cancel_search_template_as_default(driver);
        driver.close();
    }

    @Test(priority = 53)
    @Parameters({"role"})
    public void TC_53_delete_search_template_OK(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.delete_search_template_OK(driver);
        driver.close();
    }

    @Test(priority = 54)
    @Parameters({"role"})
    public void TC_54_delete_search_template_Cancel(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.delete_search_template_Cancel(driver);
        driver.close();
    }

    @Test(priority = 55)
    @Parameters({"role"})
    public void TC_55_reset_search_criteria(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        ds.reset_search_criteria(driver);
        driver.close();
    }

    @Test(priority = 56)
    @Parameters({"role", "partnerPIC_url"})
    public void TC_56_link_to_partner_PIC_edit_from_destination_selection(String role, String partnerPIC_url) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Click first record and verify partner PIC edit page should be open
        ds.link_to_partner_PIC_edit_from_destination_selection(driver, partnerPIC_url);

        driver.close();
    }

    @Test(priority = 57)
    @Parameters({"role"})
    public void TC_57_pagination_destination_selection(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Click first record and verify partner PIC edit page should be open
        ds.pagination_destination_selection(driver);

        driver.close();
    }

    @Test(priority = 58)
    @Parameters({"role", "url_mail_list"})
    public void TC_58_do_you_want_to_delete_this_delivered_email_OK_final_confirmation(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Select contact
        ds.select_contact(driver, role, cm);

        // Next to Final confirmation step
        ds.next_to_final_confirmation(driver, role, cm);

        fc.do_you_want_to_delete_this_delivered_email_OK(driver, url_mail_list);
        driver.close();
    }

    @Test(priority = 59)
    @Parameters({"role"})
    public void TC_59_do_you_want_to_delete_this_delivered_email_Cancel_final_confirmation(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Select contact
        ds.select_contact(driver, role, cm);

        // Next to Final confirmation step
        ds.next_to_final_confirmation(driver, role, cm);
        fc.do_you_want_to_delete_this_delivered_email_Cancel(driver);
        driver.close();
    }

    @Test(priority = 60)
    @Parameters({"role", "url_mail_list"})
    public void TC_60_make_a_copy_final_confirmation(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Select contact
        ds.select_contact(driver, role, cm);

        // Next to Final confirmation step
        ds.next_to_final_confirmation(driver, role, cm);
        fc.make_a_copy(driver, key,url_mail_list);
        driver.close();
    }

    @Test(priority = 61)
    @Parameters({"role", "url_mail_list"})
    public void TC_61_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_final_confirmation(String role, String url_mail_list) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Select contact
        ds.select_contact(driver, role, cm);

        // Next to Final confirmation step
        ds.next_to_final_confirmation(driver, role, cm);
        fc.would_you_like_to_change_this_delivery_email_to_Draft_status_OK(driver, key,url_mail_list);
        driver.close();
    }

    @Test(priority = 62)
    @Parameters({"role"})
    public void TC_62_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_final_confirmation(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Select contact
        ds.select_contact(driver, role, cm);

        // Next to Final confirmation step
        ds.next_to_final_confirmation(driver, role, cm);
        fc.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel(driver, key);
        driver.close();
    }

    @Test(priority = 63)
    @Parameters({"role"})
    public void TC_63_back_to_destination_selection_from_final_confirmation(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Select contact
        ds.select_contact(driver, role, cm);

        // Next to Final confirmation step
        ds.next_to_final_confirmation(driver, role, cm);
        fc.back_to_destination_selection_step(driver, key,role, cm);
//        driver.close();
    }

    @Test(priority = 64)
    @Parameters({"role"})
    public void TC_64_back_to_attachment_from_final_confirmation(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Select contact
        ds.select_contact(driver, role, cm);

        // Next to Final confirmation step
        ds.next_to_final_confirmation(driver, role, cm);
        fc.back_to_attachment_step(driver,key, role, cm);
        driver.close();
    }

    @Test(priority = 65)
    @Parameters({"role"})
    public void TC_65_back_to_basic_information_from_final_confirmation(String role) throws InterruptedException {
        bi.subject(driver, role, cm);
        bi.insertion(driver, role, cm);
        bi.next_to_attachment_step(driver, role, cm);
        a.next_to_destination_selection_step(driver, role, cm);
        // Delivery information
        ds.delivery_information(driver, role, cm);

        // Commitment
//        ds.commitment(driver, key, role, cm);

        // Search
        ds.search_contact_by_condition(driver, role, cm);

        // Select contact
        ds.select_contact(driver, role, cm);

        // Next to Final confirmation step
        ds.next_to_final_confirmation(driver, role, cm);
        fc.back_to_basic_information_step(driver, key,role, cm);
        driver.close();
    }
}