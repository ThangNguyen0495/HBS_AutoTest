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

public class CreateMail {

    Common cm;
    WebDriver driver;
    Actions key;
    Basic_information bi;
    Attachment a;
    Destination_selection ds;
    Final_confirmation fc;

    @BeforeMethod
    @Parameters({"headless", "email", "password", "url", "role", "url_mail_list", "capacity","partnerPIC_url"})
    public void init(Boolean headless, String email, String password, String url, String role, String url_mail_list, int capacity, String partnerPIC_url) throws InterruptedException {
        // Init Common function
        cm = new Common();

        // Config Webdriver
        driver = cm.setupWebdriver(headless);

        // Init Actions
        key = new Actions(driver);

        //Login
        cm.login(driver, url, email, password);

        // Init Basic information function
        bi = new Basic_information(driver, key, role, cm, url_mail_list);

        // Init Attachment function
        a = new Attachment(driver, key, role, cm, url_mail_list, capacity);

        // Init Destination selection function
        ds = new Destination_selection(driver, key, role, cm, url_mail_list, partnerPIC_url);

        // Init Final confirmation function
        fc = new Final_confirmation(driver, key, role, cm, url_mail_list);
    }

    @Test(priority = 1)
    public void TC01_process_create_mail() throws InterruptedException {
        //****** 基本情報 ****** //
        // Basic information
        // Format
        bi.format();

        // Distributor
        // random distributor in range 1-20
        bi.distributor();

        // Subject
        bi.subject();

        // Insertion
        bi.insertion();

        // Send a copy to the distributor
        bi.send_a_copy_to_the_distributor();

        // Next to 添付ファイル_Step
        bi.next_to_attachment_step();

        //****** 添付ファイル ****** //
        // Attachment
        // Upload file
        a.upload_file();

        // Next to 宛先選択_Step
        a.next_to_destination_selection_step();

        //****** 宛先選択 ****** //
        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Select contact
        ds.select_contact();

        // Next to 最終確認_step
        ds.next_to_final_confirmation();

        //****** 最終確認 ****** //
        // Final confirmation
        // Open delivery time setting popup
        fc.open_delivery_time_setting_popup();

        // Select date
        fc.select_date();

        // Select time and select again when time incorrect
        fc.select_time_and_select_again_when_time_incorrect();

        // Close browser
        driver.close();
    }

    @Test(priority = 2)
    public void TC_02_leave_all_blank_basic_information() throws InterruptedException {
        // Leave distributor blank and verify error message
        bi.leave_distributor_blank();

        // Leave subject blank and verify error message
        bi.leave_subject_blank();

        // Leave insertion blank and verify error message
        bi.leave_insertion_blank();

        // Close browser
        driver.close();
    }

    @Test(priority = 3)
    public void TC_03_subject_exceed_100_half_width_characters() {
        // Input subject exceed 100 half width character and verify error message
        bi.subject_exceed_100_half_width_characters();

        // Close browser
        driver.close();
    }

    @Test(priority = 4)
    public void TC_04_subject_exceed_100_full_width_characters() {
        // Input subject exceed 100 full width character and verify error message
        bi.subject_exceed_100_full_width_characters();

        // Close browser
        driver.close();
    }

    @Test(priority = 5)
    public void TC_05_subject_exceed_100_mix_half_and_full_width_characters() {
        // Input subject exceed mix 100 half, full width character and verify error message
        bi.subject_exceed_100_mix_half_and_full_width_characters();

        // Close browser
        driver.close();
    }


    @Test(priority = 6)
    public void TC_06_insertion_exceed_10000_half_width_characters() {
        // Input insertion exceed 10000 half width character and verify error message
        bi.insertion_exceed_10000_half_width_characters();
        driver.close();
    }

    @Test(priority = 7)
    public void TC_07_insertion_exceed_5000_full_width_characters() {
        // Input insertion exceed 5000 full width character and verify error message
        bi.insertion_exceed_5000_full_width_characters();

        // Close browser
        driver.close();
    }

    @Test(priority = 8)
    public void TC_08_insertion_exceed_5000_mix_half_and_full_width_characters() {
        // Input insertion exceed mix 5000 half,full width character and verify error message
        bi.insertion_exceed_5000_mix_half_and_full_width_characters();

        // Close browser
        driver.close();
    }

    @Test(priority = 9)
    public void TC_09_delete_button_should_be_disable() {
        // Verify delete button getting disable
        bi.delete_button_should_be_disable();

        // Close browser
        driver.close();
    }

    @Test(priority = 10)
    public void TC_10_do_you_want_to_delete_this_delivered_email_OK_basic_information() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Back to basic information step
        a.back_to_basic_information_step();

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        bi.do_you_want_to_delete_this_delivered_email_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 11)
    public void TC_11_do_you_want_to_delete_this_delivered_email_Cancel_basic_information() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Back to basic information step
        a.back_to_basic_information_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        bi.do_you_want_to_delete_this_delivered_email_Cancel();

        // Close browser
        driver.close();
    }

    @Test(priority = 12)
    public void TC_12_make_a_copy_basic_information() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Back to basic information step
        a.back_to_basic_information_step();

        // Click make a copy button => create a copy mail and link to mail list page
        bi.make_a_copy();

        // Close browser
        driver.close();
    }

    @Test(priority = 13)
    public void TC_13_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_basic_information() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Back to basic information step
        a.back_to_basic_information_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        bi.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 14)
    public void TC_14_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_basic_information() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Back to basic information step
        a.back_to_basic_information_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        bi.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();

        // Close browser
        driver.close();
    }


    @Test(priority = 15)
    public void TC_15_upload_maximum_capacity_1_file() throws IOException, InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Upload 1 file with maximum capacity and verify message
        a.upload_maximum_capacity_1_file();

        // Close browser
        driver.close();
    }

    @Test(priority = 16)
    public void TC_16_upload_maximum_capacity_multi_file() throws IOException, InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Upload multi file with maximum capacity and verify message
        a.upload_maximum_capacity_multi_file();

        // Close browser
        driver.close();
    }

    @Test(priority = 17)
    public void TC_17_upload_exceed_maximum_capacity_1_file() throws IOException, InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Upload 1 file with exceed maximum capacity and verify message
        a.upload_exceed_maximum_capacity_1_file();

        // Close browser
        driver.close();
    }

    @Test(priority = 18)
    public void TC_18_upload_exceed_maximum_capacity_multi_file() throws IOException, InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Upload multi file with exceed maximum capacity and verify message
        a.upload_exceed_maximum_capacity_multi_file();

        // Close browser
        driver.close();
    }

    @Test(priority = 19)
    public void TC_19_do_you_want_to_delete_this_delivered_email_OK_attachment() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click OK button => delivered mail should be deleted and link to url mail list page
        a.do_you_want_to_delete_this_delivered_email_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 20)
    public void TC_20_do_you_want_to_delete_this_delivered_email_Cancel_attachment() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        a.do_you_want_to_delete_this_delivered_email_Cancel();

        //Close browser
        driver.close();
    }

    @Test(priority = 21)
    public void TC_21_make_a_copy_attachment() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Click make a copy button => create a copy mail and link to mail list page
        a.make_a_copy();

        // Close browser
        driver.close();
    }

    @Test(priority = 22)
    public void TC_22_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_attachment() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        a.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 23)
    public void TC_23_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_attachment() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        a.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();

        // Close browser
        driver.close();
    }

    @Test(priority = 24)
    public void TC_24_deliver_the_matter_do_not_select_condition() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Click search button
        ds.deliver_the_matter_do_not_select_condition();

        // Close browser
        driver.close();
    }

    @Test(priority = 25)
    public void TC_25_deliver_the_matter_only_select_delivery_occupation_development() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Select delivery occupation: development
        // Click search button
        ds.deliver_the_matter_only_select_delivery_occupation_development();

        // Close browser
        driver.close();
    }

    @Test(priority = 26)
    public void TC_26_deliver_the_matter_only_select_delivery_occupation_infrastructure() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Select delivery occupation: infrastructure
        // Click search button
        ds.deliver_the_matter_only_select_delivery_occupation_infrastructure();

        // Close browser
        driver.close();
    }

    @Test(priority = 27)
    public void TC_27_deliver_the_matter_only_select_delivery_occupation_others() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Select delivery occupation: others
        // Click search button
        ds.deliver_the_matter_only_select_delivery_occupation_others();

        // Close browser
        driver.close();
    }

    @Test(priority = 28)
    public void TC_28_deliver_the_personnel_do_not_select_condition() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Click search button
        ds.deliver_the_personnel_do_not_select_condition();

        // Close browser
        driver.close();
    }

    @Test(priority = 29)
    public void TC_29_deliver_the_personnel_only_select_delivery_occupation_development() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: development
        // Click search button
        ds.deliver_the_personnel_only_select_delivery_occupation_development();

        // Close browser
        driver.close();
    }

    @Test(priority = 30)
    public void TC_30_deliver_the_personnel_only_select_delivery_occupation_infrastructure() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: infrastructure
        // Click search button
        ds.deliver_the_personnel_only_select_delivery_occupation_infrastructure();

        // Close browser
        driver.close();
    }

    @Test(priority = 31)
    public void TC_31_deliver_the_personnel_only_select_delivery_occupation_others() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: others
        // Click search button
        ds.deliver_the_personnel_only_select_delivery_occupation_others();

        // Close browser
        driver.close();
    }

    @Test(priority = 32)
    public void TC_32_deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: development, infrastructure, others
        // Click search button
        ds.deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others();

        // Close browser
        driver.close();
    }

    @Test(priority = 33)
    public void TC_33_do_not_select_account_status() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Account status: ON
        // Do not select account status
        // Click search button
        ds.do_not_select_account_status();

        // Close browser
        driver.close();
    }

    @Test(priority = 34)
    public void TC_34_do_not_select_in_house_person_in_charge() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // In-house person in charge: ON
        // Do not select in-house person in charge
        // Click search button
        ds.do_not_select_in_house_person_in_charge();

        // Close browser
        driver.close();
    }

    @Test(priority = 35)
    public void TC_35_do_not_select_compatibility() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Compatibility: ON
        // Do not select compatibility
        // Click search button
        ds.do_not_select_compatibility();

        // Close browser
        driver.close();
    }

    @Test(priority = 36)
    public void TC_36_do_not_select_tag() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Tag: ON
        // Do not select tag
        // Click search button
        ds.do_not_select_tag();

        // Close browser
        driver.close();
    }

    @Test(priority = 37)
    public void TC_37_select_exceeds_5_tags() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Tag: ON
        // Select exceed 5 tags
        // Click search button
        ds.select_exceed_5_tags();

        // Close browser
        driver.close();
    }

    @Test(priority = 38)
    public void TC_38_do_you_want_to_delete_this_delivered_email_OK_destination_selection() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        ds.do_you_want_to_delete_this_delivered_email_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 39)
    public void TC_39_do_you_want_to_delete_this_delivered_email_Cancel_destination_selection() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        ds.do_you_want_to_delete_this_delivered_email_Cancel();

        // Close browser
        driver.close();
    }

    @Test(priority = 40)
    public void TC_40_make_a_copy_destination_selection() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Click make a copy button => create a copy mail and link to mail list page
        ds.make_a_copy();

        // Close browser
        driver.close();
    }

    @Test(priority = 41)
    public void TC_41_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_destination_selection() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        ds.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 42)
    public void TC_42_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_destination_selection() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        ds.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();

        // Close browser
        driver.close();
    }

    @Test(priority = 43)
    public void TC_43_back_to_basic_information_from_destination_selection() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Back to basic information step
        ds.back_to_basic_information_step();

        // Close browser
        driver.close();
    }

    @Test(priority = 44)
    public void TC_44_back_to_attachment_from_destination_selection() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Back to attachment step
        ds.back_to_attachment_step();

        // Close browser
        driver.close();
    }

    @Test(priority = 45)
    public void TC_45_leave_search_template_name_blank() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Leave search template name blank and verify error message
        ds.leave_search_template_name_blank();

        // Close browser
        driver.close();
    }

    @Test(priority = 46)
    public void TC_46_search_template_name_exceed_50_half_width_characters() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Input search template name exceed 50 half width characters and verify error message
        ds.search_template_name_exceed_50_half_width_characters();

        // Close browser
        driver.close();
    }

    @Test(priority = 47)
    public void TC_47_search_template_name_exceed_50_full_width_characters() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Input search template name exceed 50 full width characters and verify error message
        ds.search_template_name_exceed_50_full_width_characters();

        // Close browser
        driver.close();
    }

    @Test(priority = 48)
    public void TC_48_search_template_name_exceed_mix_50_half_and_full_width_characters() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Input search template name exceed mix 50 half, full width characters and verify error message
        ds.search_template_name_exceed_mix_50_half_and_full_width_characters();

        // Close browser
        driver.close();
    }

    @Test(priority = 49)
    public void TC_49_create_search_template_name_OK() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Create search template with valid name
        ds.create_search_template_name_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 50)
    public void TC_50_create_search_template_name_Cancel() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Close create search template popup
        ds.create_search_template_name_Cancel();

        // Close browser
        driver.close();
    }

    @Test(priority = 51)
    public void TC_51_input_available_search_template_name() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Verify error message when create search template with available name.
        ds.input_available_search_template_name();

        // Close browser
        driver.close();
    }

    @Test(priority = 52)
    public void TC_52_set_cancel_search_template_as_default() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Verify can set and cancel template as default
        ds.set_cancel_search_template_as_default();

        // Close browser
        driver.close();
    }

    @Test(priority = 53)
    public void TC_53_delete_search_template_OK() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Verify that can delete template
        ds.delete_search_template_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 54)
    public void TC_54_delete_search_template_Cancel() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Close delete template popup
        ds.delete_search_template_Cancel();

        // Close browser
        driver.close();
    }

    @Test(priority = 55)
    public void TC_55_reset_search_criteria() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Verify that can reset search condition
        ds.reset_search_criteria();

        // Close browser
        driver.close();
    }

    @Test(priority = 56)
    public void TC_56_link_to_partner_PIC_edit_from_destination_selection() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Click first record and verify partner PIC edit page should be open
        ds.link_to_partner_PIC_edit_from_destination_selection();

        // Close browser
        driver.close();
    }

    @Test(priority = 57)
    public void TC_57_pagination_destination_selection() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Verify that pagination should be work normally
        ds.pagination_destination_selection();

        // Close browser
        driver.close();
    }

    @Test(priority = 58)
    public void TC_58_do_you_want_to_delete_this_delivered_email_OK_final_confirmation() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Select contact
        ds.select_contact();

        // Next to Final confirmation step
        ds.next_to_final_confirmation();

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        fc.do_you_want_to_delete_this_delivered_email_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 59)
    public void TC_59_do_you_want_to_delete_this_delivered_email_Cancel_final_confirmation() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Select contact
        ds.select_contact();

        // Next to Final confirmation step
        ds.next_to_final_confirmation();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        fc.do_you_want_to_delete_this_delivered_email_Cancel();

        // Close browser
        driver.close();
    }

    @Test(priority = 60)
    public void TC_60_make_a_copy_final_confirmation() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Select contact
        ds.select_contact();

        // Next to Final confirmation step
        ds.next_to_final_confirmation();

        // Click make a copy button => create a copy mail and link to mail list page
        fc.make_a_copy();

        // Close browser
        driver.close();
    }

    @Test(priority = 61)
    public void TC_61_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_final_confirmation() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Select contact
        ds.select_contact();

        // Next to Final confirmation step
        ds.next_to_final_confirmation();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        fc.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();

        // Close browser
        driver.close();
    }

    @Test(priority = 62)
    public void TC_62_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_final_confirmation() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Select contact
        ds.select_contact();

        // Next to Final confirmation step
        ds.next_to_final_confirmation();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        fc.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();

        // Close browser
        driver.close();
    }

    @Test(priority = 63)
    public void TC_63_back_to_destination_selection_from_final_confirmation() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Select contact
        ds.select_contact();

        // Next to Final confirmation step
        ds.next_to_final_confirmation();

        // Back to destination selection step
        fc.back_to_destination_selection_step();

        // Close browser
        driver.close();
    }

    @Test(priority = 64)
    public void TC_64_back_to_attachment_from_final_confirmation() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Select contact
        ds.select_contact();

        // Next to Final confirmation step
        ds.next_to_final_confirmation();

        // Back to attachment step
        fc.back_to_attachment_step();

        // Close browser
        driver.close();
    }

    @Test(priority = 65)
    public void TC_65_back_to_basic_information_from_final_confirmation() throws InterruptedException {
        // Input valid subject
        bi.subject();

        // Input valid insertion
        bi.insertion();

        // Next to attachment step
        bi.next_to_attachment_step();

        // Next to destination selection step
        a.next_to_destination_selection_step();

        // Delivery information
        ds.delivery_information();

        // Commitment
//        ds.commitment();

        // Search
        ds.search_contact_by_condition();

        // Select contact
        ds.select_contact();

        // Next to Final confirmation step
        ds.next_to_final_confirmation();

        // Back to basic information step
        fc.back_to_basic_information_step();

        // Close browser
        driver.close();
    }
}