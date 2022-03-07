package test.Mail;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import page.Mail.Step1_Basic_Information;
import page.Mail.Step2_Attachment;
import page.Mail.Step3_Destination_selection;
import page.Mail.Step4_Final_confirmation;
import utilities.Common.Common;

import java.io.File;
import java.io.IOException;

import static utilities.Link_and_Path.HBS.mail_list_path;

public class Edit_Delivered_Mail {
    Common common;
    WebDriver driver;
    Actions key;
    Step1_Basic_Information basicInformation;
    Step2_Attachment attachment;
    Step3_Destination_selection destinationSearch;
    Step4_Final_confirmation finalConfirmation;

    @BeforeClass
    public void clear_old_test_data_and_error() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\Test_Data"));
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\img\\Edit_Mail"));
    }

    @BeforeMethod
    @Parameters({"headless", "browser_name", "email", "password", "domain", "mail_id", "role", "capacity", "mail_status"})
    public void setup(Boolean headless, String browser_name, String email, String password, String domain, String mail_id, String role, int capacity, String mail_status) throws InterruptedException {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Actions
        key = new Actions(driver);

        //Login
        common.login(driver, domain + mail_list_path + "/" + mail_id, email, password);

        // Init Basic information function
        basicInformation = new Step1_Basic_Information(driver, role, common, domain, mail_id, mail_status, "Edit");

        // Init Attachment function
        attachment = new Step2_Attachment(driver, role, common, domain, capacity, mail_id, mail_status, "Edit");

        // Init Destination selection function
        destinationSearch = new Step3_Destination_selection(driver, role, common, domain, mail_id, mail_status, "Edit");

        // Init Final confirmation function
        finalConfirmation = new Step4_Final_confirmation(driver, role, common, domain, mail_id, mail_status, "Edit");
    }

    @Test
    public void TC01_process_edit_mail() throws InterruptedException {
        //****** 基本情報 ****** //
        // Basic information
        // Format
        basicInformation.format();

        // Distributor
        // random distributor in range 1-20
        basicInformation.distributor();

        // Subject
        basicInformation.subject();

        // Insertion
        basicInformation.insertion();

        // Send attachment copy to the distributor
        basicInformation.send_a_copy_to_the_distributor();

        // Next to 添付ファイル_Step
        basicInformation.next_to_attachment_step();

        //****** 添付ファイル ****** //
        // Attachment
        // Upload file
//        attachment.upload_file();

        // Next to 宛先選択_Step
        attachment.next_to_destination_selection_step();

        //****** 宛先選択 ****** //
        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to 最終確認_step
        destinationSearch.next_to_final_confirmation();

        //****** 最終確認 ****** //
        // Final confirmation
        // Open delivery time setting popup
        finalConfirmation.open_delivery_time_setting_popup();

        // Select date
        finalConfirmation.select_date();

        // Select time and select again when time incorrect
        finalConfirmation.select_time_and_select_again_when_time_incorrect();
    }

    @Test
    public void TC_02_leave_all_blank_basic_information() {
        // Leave distributor blank and verify error message
        basicInformation.leave_distributor_blank();

        // Leave subject blank and verify error message
        basicInformation.leave_subject_blank();

        // Leave insertion blank and verify error message
        basicInformation.leave_insertion_blank();
    }

    @Test
    public void TC_03_subject_exceed_100_half_width_characters() throws InterruptedException {
        // Input subject exceed 100 half width character and verify error message
        basicInformation.subject_exceed_100_half_width_characters();
    }

    @Test
    public void TC_04_subject_exceed_100_full_width_characters() throws InterruptedException {
        // Input subject exceed 100 full width character and verify error message
        basicInformation.subject_exceed_100_full_width_characters();
    }

    @Test
    public void TC_05_subject_exceed_100_mix_half_and_full_width_characters() throws InterruptedException {
        // Input subject exceed mix 100 half, full width character and verify error message
        basicInformation.subject_exceed_100_mix_half_and_full_width_characters();
    }


    @Test
    public void TC_06_insertion_exceed_10000_half_width_characters() throws InterruptedException {
        // Input insertion exceed 10000 half width character and verify error message
        basicInformation.insertion_exceed_10000_half_width_characters();
        driver.close();
    }

    @Test
    public void TC_07_insertion_exceed_5000_full_width_characters() throws InterruptedException {
        // Input insertion exceed 5000 full width character and verify error message
        basicInformation.insertion_exceed_5000_full_width_characters();
    }

    @Test
    public void TC_08_insertion_exceed_5000_mix_half_and_full_width_characters() throws InterruptedException {
        // Input insertion exceed mix 5000 half,full width character and verify error message
        basicInformation.insertion_exceed_5000_mix_half_and_full_width_characters();
    }

    @Test
    public void TC_09_update_delivery_with_valid_data_basic_information() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Verify that can update delivered mail with valid data
        basicInformation.update_delivery_with_valid_data();
    }

    @Test
    public void TC_10_make_a_copy_basic_information() throws InterruptedException {
        // Click make attachment copy button => create attachment copy mail and link to mail list page
        basicInformation.make_a_copy();
    }

    @Test
    public void TC_11_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_basic_information() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        basicInformation.switch_url_before_run_test("save as draft");

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        basicInformation.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();
    }

    @Test
    public void TC_12_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_basic_information() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        basicInformation.switch_url_before_run_test("save as draft");

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        basicInformation.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();
    }

    @Test
    public void TC_13_do_you_want_to_delete_this_delivered_email_Cancel_basic_information() throws InterruptedException {
        // Switch to mail id for delete testcase
        basicInformation.switch_url_before_run_test("delete");

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        basicInformation.do_you_want_to_delete_this_delivered_email_Cancel();
    }

    @Test
    public void TC_14_do_you_want_to_delete_this_delivered_email_OK_basic_information() throws InterruptedException {
        // Switch to mail id for delete testcase
        basicInformation.switch_url_before_run_test("delete");

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        basicInformation.do_you_want_to_delete_this_delivered_email_OK();
    }

    @Test
    public void TC_15_upload_maximum_capacity_1_file() throws IOException, InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Upload 1 file with maximum capacity and verify message
        attachment.upload_maximum_capacity_1_file();
    }

    @Test
    public void TC_16_upload_maximum_capacity_multi_file() throws InterruptedException, IOException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Upload multi file with maximum capacity and verify message
        attachment.upload_maximum_capacity_multi_file();
    }

    @Test
    public void TC_17_upload_exceed_maximum_capacity_1_file() throws IOException, InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Upload 1 file with exceed maximum capacity and verify message
        attachment.upload_exceed_maximum_capacity_1_file();
    }

    @Test
    public void TC_18_upload_exceed_maximum_capacity_multi_file() throws InterruptedException, IOException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Upload multi file with exceed maximum capacity and verify message
        attachment.upload_exceed_maximum_capacity_multi_file();
    }

    @Test
    public void TC_19_update_delivery_with_valid_data_attachment() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Verify that can update delivered mail with valid data
        attachment.update_delivery_with_valid_data();
    }

    @Test
    public void TC_20_make_a_copy_attachment() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click make attachment copy button => create attachment copy mail and link to mail list page
        attachment.make_a_copy();
    }

    @Test
    public void TC_21_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_attachment() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        attachment.switch_url_before_run_test("save as draft");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        attachment.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();
    }

    @Test
    public void TC_22_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_attachment() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        attachment.switch_url_before_run_test("save as draft");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        attachment.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();
    }

    @Test
    public void TC_23_do_you_want_to_delete_this_delivered_email_Cancel_attachment() throws InterruptedException {
        // Switch to mail id for delete testcase
        attachment.switch_url_before_run_test("delete");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        attachment.do_you_want_to_delete_this_delivered_email_Cancel();

        //Close browser
        driver.close();
    }

    @Test
    public void TC_24_do_you_want_to_delete_this_delivered_email_OK_attachment() throws InterruptedException {
        // Switch to mail id for delete testcase
        attachment.switch_url_before_run_test("delete");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click OK button => delivered mail should be deleted and link to url mail list page
        attachment.do_you_want_to_delete_this_delivered_email_OK();
    }

    @Test
    public void TC_25_deliver_the_matter_do_not_select_condition() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Click search button
        destinationSearch.deliver_the_matter_do_not_select_condition();
    }

    @Test
    public void TC_26_deliver_the_matter_only_select_delivery_occupation_development() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Select delivery occupation: development
        // Click search button
        destinationSearch.deliver_the_matter_only_select_delivery_occupation_development();
    }

    @Test
    public void TC_27_deliver_the_matter_only_select_delivery_occupation_infrastructure() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Select delivery occupation: infrastructure
        // Click search button
        destinationSearch.deliver_the_matter_only_select_delivery_occupation_infrastructure();
    }

    @Test
    public void TC_28_deliver_the_matter_only_select_delivery_occupation_others() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Select delivery occupation: others
        // Click search button
        destinationSearch.deliver_the_matter_only_select_delivery_occupation_others();
    }

    @Test
    public void TC_29_deliver_the_personnel_do_not_select_condition() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Click search button
        destinationSearch.deliver_the_personnel_do_not_select_condition();
    }

    @Test
    public void TC_30_deliver_the_personnel_only_select_delivery_occupation_development() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: development
        // Click search button
        destinationSearch.deliver_the_personnel_only_select_delivery_occupation_development();
    }

    @Test
    public void TC_31_deliver_the_personnel_only_select_delivery_occupation_infrastructure() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: infrastructure
        // Click search button
        destinationSearch.deliver_the_personnel_only_select_delivery_occupation_infrastructure();
    }

    @Test
    public void TC_32_deliver_the_personnel_only_select_delivery_occupation_others() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: others
        // Click search button
        destinationSearch.deliver_the_personnel_only_select_delivery_occupation_others();
    }

    @Test
    public void TC_33_deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: development, infrastructure, others
        // Click search button
        destinationSearch.deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others();
    }

    @Test
    public void TC_34_do_not_select_account_status() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Account status: ON
        // Do not select account status
        // Click search button
        destinationSearch.do_not_select_account_status();
    }

    @Test
    public void TC_35_do_not_select_in_house_person_in_charge() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // In-house person in charge: ON
        // Do not select in-house person in charge
        // Click search button
        destinationSearch.do_not_select_in_house_person_in_charge();
    }

    @Test
    public void TC_36_do_not_select_compatibility() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Compatibility: ON
        // Do not select compatibility
        // Click search button
        destinationSearch.do_not_select_compatibility();
    }

    @Test
    public void TC_37_do_not_select_tag() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Tag: ON
        // Do not select tag
        // Click search button
        destinationSearch.do_not_select_tag();
    }

    @Test
    public void TC_38_select_exceeds_5_tags() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Tag: ON
        // Select exceed 5 tags
        // Click search button
        destinationSearch.select_exceed_5_tags();
    }

    @Test
    public void TC_39_update_delivery_with_valid_data_destination_selection() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Verify that can update delivered mail with valid data
        destinationSearch.update_delivery_with_valid_data();
    }

    @Test
    public void TC_40_make_a_copy_destination_selection() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click make attachment copy button => create attachment copy mail and link to mail list page
        destinationSearch.make_a_copy();
    }

    @Test
    public void TC_41_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_destination_selection() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        destinationSearch.switch_url_before_run_test("save as draft");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        destinationSearch.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();
    }

    @Test
    public void TC_42_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_destination_selection() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        destinationSearch.switch_url_before_run_test("save as draft");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        destinationSearch.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();
    }

    @Test
    public void TC_43_do_you_want_to_delete_this_delivered_email_Cancel_destination_selection() throws InterruptedException {
        // Switch to mail id for delete testcase
        destinationSearch.switch_url_before_run_test("delete");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        destinationSearch.do_you_want_to_delete_this_delivered_email_Cancel();
    }

    @Test
    public void TC_44_do_you_want_to_delete_this_delivered_email_OK_destination_selection() throws InterruptedException {
        // Switch to mail id for delete testcase
        destinationSearch.switch_url_before_run_test("delete");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        destinationSearch.do_you_want_to_delete_this_delivered_email_OK();
    }


    @Test
    public void TC_45_back_to_basic_information_from_destination_selection() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Back to basic information step
        destinationSearch.back_to_basic_information_step();
    }

    @Test
    public void TC_46_back_to_attachment_from_destination_selection() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Back to attachment step
        destinationSearch.back_to_attachment_step();
    }

    @Test
    public void TC_47_leave_search_template_name_blank() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Leave search template name blank and verify error message
        destinationSearch.leave_search_template_name_blank();
    }

    @Test
    public void TC_48_search_template_name_exceed_50_half_width_characters() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Input search template name exceed 50 half width characters and verify error message
        destinationSearch.search_template_name_exceed_50_half_width_characters();
    }

    @Test
    public void TC_49_search_template_name_exceed_50_full_width_characters() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Input search template name exceed 50 full width characters and verify error message
        destinationSearch.search_template_name_exceed_50_full_width_characters();
    }

    @Test
    public void TC_50_search_template_name_exceed_mix_50_half_and_full_width_characters() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Input search template name exceed mix 50 half, full width characters and verify error message
        destinationSearch.search_template_name_exceed_mix_50_half_and_full_width_characters();
    }

    @Test
    public void TC_51_create_search_template_name_OK() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Create search template with valid name
        destinationSearch.create_search_template_name_OK();
    }

    @Test
    public void TC_52_create_search_template_name_Cancel() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Close create search template popup
        destinationSearch.create_search_template_name_Cancel();
    }

    @Test
    public void TC_53_input_available_search_template_name() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Verify error message when create search template with available name.
        destinationSearch.input_available_search_template_name();
    }

    @Test
    public void TC_54_set_cancel_search_template_as_default() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Verify can set and cancel template as default
        destinationSearch.set_cancel_search_template_as_default();
    }

    @Test
    public void TC_55_delete_search_template_OK() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Verify that can delete template
        destinationSearch.delete_search_template_OK();
    }

    @Test
    public void TC_56_delete_search_template_Cancel() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Close delete template popup
        destinationSearch.delete_search_template_Cancel();
    }

    @Test
    public void TC_57_reset_search_criteria() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Verify that can reset search condition
        destinationSearch.reset_search_criteria();
    }

    @Test
    public void TC_58_link_to_partner_PIC_edit_from_destination_selection() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Click first record and verify partner PIC edit page should be open
        destinationSearch.link_to_partner_PIC_edit_from_destination_selection();
    }

    @Test
    public void TC_59_pagination_destination_selection() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Verify that pagination should be work normally
        destinationSearch.pagination_destination_selection();
    }

    @Test
    public void TC_60_update_delivery_with_valid_data_final_confirmation() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Verify that can update delivered mail with valid data
        finalConfirmation.update_delivery_with_valid_data();
    }

    @Test
    public void TC_61_make_a_copy_final_confirmation() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Click make attachment copy button => create attachment copy mail and link to mail list page
        finalConfirmation.make_a_copy();

        // Close browser
//        driver.close();
    }

    @Test
    public void TC_62_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_final_confirmation() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        finalConfirmation.switch_url_before_run_test("save as draft");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        finalConfirmation.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();
    }

    @Test
    public void TC_63_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_final_confirmation() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        finalConfirmation.switch_url_before_run_test("save as draft");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        finalConfirmation.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();
    }

    @Test
    public void TC_64_do_you_want_to_delete_this_delivered_email_Cancel_final_confirmation() throws InterruptedException {
        // Switch to mail id for delete testcase
        finalConfirmation.switch_url_before_run_test("delete");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        finalConfirmation.do_you_want_to_delete_this_delivered_email_Cancel();
    }

    @Test
    public void TC_65_do_you_want_to_delete_this_delivered_email_OK_final_confirmation() throws InterruptedException {
        // Switch to mail id for delete testcase
        finalConfirmation.switch_url_before_run_test("delete");

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        finalConfirmation.do_you_want_to_delete_this_delivered_email_OK();
    }

    @Test
    public void TC_66_back_to_destination_selection_from_final_confirmation() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Back to destination selection step
        finalConfirmation.back_to_destination_selection_step();
    }

    @Test
    public void TC_67_back_to_attachment_from_final_confirmation() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Back to attachment step
        finalConfirmation.back_to_attachment_step();
    }

    @Test
    public void TC_68_back_to_basic_information_from_final_confirmation() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
//        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Back to basic information step
        finalConfirmation.back_to_basic_information_step();
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException, InterruptedException {
        // take screenshot when test failed
        common.take_screenshot_when_test_fail(driver, result, "Edit_Mail");

        // close all browsers
        driver.quit();
    }
}
