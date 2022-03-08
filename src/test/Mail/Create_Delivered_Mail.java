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
import static utilities.Link_and_Path.HBS.register_path;

public class Create_Delivered_Mail {
    Common common;
    WebDriver driver;
    Actions key;
    Step1_Basic_Information basicInformation;
    Step2_Attachment attachment;
    Step3_Destination_selection destinationSearch;
    Step4_Final_confirmation finalConfirmation;

    @BeforeClass
    public void clear_old_test_data_and_error() throws IOException {
        try {
            FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\Test_Data"));
        } catch (IOException ex) {
            // nothing
        }
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\img\\Create_Mail"));
    }

    @BeforeMethod
    @Parameters({"headless", "browser_name", "email", "password", "domain", "role", "capacity"})
    public void setup(String headless, String browser_name, String email, String password, String domain, String role, int capacity) throws InterruptedException {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Actions
        key = new Actions(driver);

        //Login
        common.login(driver, domain + mail_list_path + register_path, email, password);

        // Init Basic information function
        basicInformation = new Step1_Basic_Information(driver, role, common, domain, "Create");

        // Init Attachment function
        attachment = new Step2_Attachment(driver, role, common, domain, capacity, "Create");

        // Init Destination selection function
        destinationSearch = new Step3_Destination_selection(driver, role, common, domain, "Create");

        // Init Final confirmation function
        finalConfirmation = new Step4_Final_confirmation(driver, role, common, domain, "Create");
    }

    @Test(priority = 1)
    public void TC01_process_create_mail() throws InterruptedException {
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
        // Next to 宛先選択_Step
        attachment.next_to_destination_selection_step();

        //****** 宛先選択 ****** //
        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

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

    @Test(priority = 2)
    public void TC_02_leave_all_blank_basic_information() throws InterruptedException {
        // Leave distributor blank and verify error message
        basicInformation.leave_distributor_blank();

        // Leave subject blank and verify error message
        basicInformation.leave_subject_blank();

        // Leave insertion blank and verify error message
        basicInformation.leave_insertion_blank();
    }

    @Test(priority = 3)
    public void TC_03_subject_exceed_100_half_width_characters() throws InterruptedException {
        // Input subject exceed 100 half width character and verify error message
        basicInformation.subject_exceed_100_half_width_characters();
    }

    @Test(priority = 4)
    public void TC_04_subject_exceed_100_full_width_characters() throws InterruptedException {
        // Input subject exceed 100 full width character and verify error message
        basicInformation.subject_exceed_100_full_width_characters();
    }

    @Test(priority = 5)
    public void TC_05_subject_exceed_100_mix_half_and_full_width_characters() throws InterruptedException {
        // Input subject exceed mix 100 half, full width character and verify error message
        basicInformation.subject_exceed_100_mix_half_and_full_width_characters();
    }


    @Test(priority = 6)
    public void TC_06_insertion_exceed_10000_half_width_characters() throws InterruptedException {
        // Input insertion exceed 10000 half width character and verify error message
        basicInformation.insertion_exceed_10000_half_width_characters();
//        driver.close();
    }

    @Test(priority = 7)
    public void TC_07_insertion_exceed_5000_full_width_characters() throws InterruptedException {
        /* Input insertion exceed 5000 full width character and verify error message */
        basicInformation.insertion_exceed_5000_full_width_characters();
    }

    @Test(priority = 8)
    public void TC_08_insertion_exceed_5000_mix_half_and_full_width_characters() throws InterruptedException {
        // Input insertion exceed mix 5000 half,full width character and verify error message
        basicInformation.insertion_exceed_5000_mix_half_and_full_width_characters();
    }

    @Test(priority = 9)
    public void TC_09_delete_button_should_be_disable() {
        // Verify delete button getting disable
        basicInformation.delete_button_should_be_disable();
    }

    @Test(priority = 10)
    public void TC_10_do_you_want_to_delete_this_delivered_email_OK_basic_information() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Back to basic information step
        attachment.back_to_basic_information_step();

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        basicInformation.do_you_want_to_delete_this_delivered_email_OK();
    }

    @Test(priority = 11)
    public void TC_11_do_you_want_to_delete_this_delivered_email_Cancel_basic_information() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Back to basic information step
        attachment.back_to_basic_information_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        basicInformation.do_you_want_to_delete_this_delivered_email_Cancel();
    }

    @Test(priority = 12)
    public void TC_12_make_a_copy_basic_information() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Back to basic information step
        attachment.back_to_basic_information_step();

        // Click make attachment copy button => create attachment copy mail and link to mail list page
        basicInformation.make_a_copy();
    }

    @Test(priority = 13)
    public void TC_13_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_basic_information() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Back to basic information step
        attachment.back_to_basic_information_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        basicInformation.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();
    }

    @Test(priority = 14)
    public void TC_14_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_basic_information() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Back to basic information step
        attachment.back_to_basic_information_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        basicInformation.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();
    }


    @Test(priority = 15)
    public void TC_15_upload_maximum_capacity_1_file() throws IOException, InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Upload 1 file with maximum capacity and verify message
        attachment.upload_maximum_capacity_1_file();
    }

    @Test(priority = 16)
    public void TC_16_upload_maximum_capacity_multi_file() throws InterruptedException, IOException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Upload multi file with maximum capacity and verify message
        attachment.upload_maximum_capacity_multi_file();
    }

    @Test(priority = 17)
    public void TC_17_upload_exceed_maximum_capacity_1_file() throws IOException, InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Upload 1 file with exceed maximum capacity and verify message
        attachment.upload_exceed_maximum_capacity_1_file();
    }

    @Test(priority = 18)
    public void TC_18_upload_exceed_maximum_capacity_multi_file() throws InterruptedException, IOException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Upload multi file with exceed maximum capacity and verify message
        attachment.upload_exceed_maximum_capacity_multi_file();
    }

    @Test(priority = 19)
    public void TC_19_do_you_want_to_delete_this_delivered_email_OK_attachment() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click OK button => delivered mail should be deleted and link to url mail list page
        attachment.do_you_want_to_delete_this_delivered_email_OK();
    }

    @Test(priority = 20)
    public void TC_20_do_you_want_to_delete_this_delivered_email_Cancel_attachment() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        attachment.do_you_want_to_delete_this_delivered_email_Cancel();

        //Close browser
//        driver.close();
    }

    @Test(priority = 21)
    public void TC_21_make_a_copy_attachment() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click make attachment copy button => create attachment copy mail and link to mail list page
        attachment.make_a_copy();
    }

    @Test(priority = 22)
    public void TC_22_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_attachment() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        attachment.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();
    }

    @Test(priority = 23)
    public void TC_23_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_attachment() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        attachment.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();
    }

    @Test(priority = 24)
    public void TC_24_deliver_the_matter_do_not_select_condition() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Click search button
        destinationSearch.deliver_the_matter_do_not_select_condition();
    }

    @Test(priority = 25)
    public void TC_25_deliver_the_matter_only_select_delivery_occupation_development() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Select delivery occupation: development
        // Click search button
        destinationSearch.deliver_the_matter_only_select_delivery_occupation_development();
    }

    @Test(priority = 26)
    public void TC_26_deliver_the_matter_only_select_delivery_occupation_infrastructure() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Select delivery occupation: infrastructure
        // Click search button
        destinationSearch.deliver_the_matter_only_select_delivery_occupation_infrastructure();
    }

    @Test(priority = 27)
    public void TC_27_deliver_the_matter_only_select_delivery_occupation_others() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the matter
        // Select delivery occupation: others
        // Click search button
        destinationSearch.deliver_the_matter_only_select_delivery_occupation_others();
    }

    @Test(priority = 28)
    public void TC_28_deliver_the_personnel_do_not_select_condition() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Click search button
        destinationSearch.deliver_the_personnel_do_not_select_condition();
    }

    @Test(priority = 29)
    public void TC_29_deliver_the_personnel_only_select_delivery_occupation_development() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: development
        // Click search button
        destinationSearch.deliver_the_personnel_only_select_delivery_occupation_development();
    }

    @Test(priority = 30)
    public void TC_30_deliver_the_personnel_only_select_delivery_occupation_infrastructure() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: infrastructure
        // Click search button
        destinationSearch.deliver_the_personnel_only_select_delivery_occupation_infrastructure();
    }

    @Test(priority = 31)
    public void TC_31_deliver_the_personnel_only_select_delivery_occupation_others() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: others
        // Click search button
        destinationSearch.deliver_the_personnel_only_select_delivery_occupation_others();
    }

    @Test(priority = 32)
    public void TC_32_deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: development, infrastructure, others
        // Click search button
        destinationSearch.deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others();
    }

    @Test(priority = 33)
    public void TC_33_do_not_select_account_status() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Account status: ON
        // Do not select account status
        // Click search button
        destinationSearch.do_not_select_account_status();
    }

    @Test(priority = 34)
    public void TC_34_do_not_select_in_house_person_in_charge() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // In-house person in charge: ON
        // Do not select in-house person in charge
        // Click search button
        destinationSearch.do_not_select_in_house_person_in_charge();
    }

    @Test(priority = 35)
    public void TC_35_do_not_select_compatibility() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Compatibility: ON
        // Do not select compatibility
        // Click search button
        destinationSearch.do_not_select_compatibility();
    }

    @Test(priority = 36)
    public void TC_36_do_not_select_tag() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Tag: ON
        // Do not select tag
        // Click search button
        destinationSearch.do_not_select_tag();
    }

    @Test(priority = 37)
    public void TC_37_select_exceeds_5_tags() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Tag: ON
        // Select exceed 5 tags
        // Click search button
        destinationSearch.select_exceed_5_tags();
    }

    @Test(priority = 38)
    public void TC_38_do_you_want_to_delete_this_delivered_email_OK_destination_selection() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        destinationSearch.do_you_want_to_delete_this_delivered_email_OK();
    }

    @Test(priority = 39)
    public void TC_39_do_you_want_to_delete_this_delivered_email_Cancel_destination_selection() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        destinationSearch.do_you_want_to_delete_this_delivered_email_Cancel();
    }

    @Test(priority = 40)
    public void TC_40_make_a_copy_destination_selection() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click make attachment copy button => create attachment copy mail and link to mail list page
        destinationSearch.make_a_copy();
    }

    @Test(priority = 41)
    public void TC_41_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_destination_selection() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        destinationSearch.would_you_like_to_change_this_delivery_email_to_Draft_status_OK();
    }

    @Test(priority = 42)
    public void TC_42_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_destination_selection() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        destinationSearch.would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel();
    }

    @Test(priority = 43)
    public void TC_43_back_to_basic_information_from_destination_selection() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Back to basic information step
        destinationSearch.back_to_basic_information_step();
    }

    @Test(priority = 44)
    public void TC_44_back_to_attachment_from_destination_selection() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Back to attachment step
        destinationSearch.back_to_attachment_step();
    }

    @Test(priority = 45)
    public void TC_45_leave_search_template_name_blank() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Leave search template name blank and verify error message
        destinationSearch.leave_search_template_name_blank();
    }

    @Test(priority = 46)
    public void TC_46_search_template_name_exceed_50_half_width_characters() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Input search template name exceed 50 half width characters and verify error message
        destinationSearch.search_template_name_exceed_50_half_width_characters();
    }

    @Test(priority = 47)
    public void TC_47_search_template_name_exceed_50_full_width_characters() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Input search template name exceed 50 full width characters and verify error message
        destinationSearch.search_template_name_exceed_50_full_width_characters();
    }

    @Test(priority = 48)
    public void TC_48_search_template_name_exceed_mix_50_half_and_full_width_characters() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Input search template name exceed mix 50 half, full width characters and verify error message
        destinationSearch.search_template_name_exceed_mix_50_half_and_full_width_characters();
    }

    @Test(priority = 49)
    public void TC_49_create_search_template_name_OK() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Create search template with valid name
        destinationSearch.create_search_template_name_OK();
    }

    @Test(priority = 50)
    public void TC_50_create_search_template_name_Cancel() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Close create search template popup
        destinationSearch.create_search_template_name_Cancel();
    }

    @Test(priority = 51)
    public void TC_51_input_available_search_template_name() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Verify error message when create search template with available name.
        destinationSearch.input_available_search_template_name();
    }

    @Test(priority = 52)
    public void TC_52_set_cancel_search_template_as_default() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Verify can set and cancel template as default
        destinationSearch.set_cancel_search_template_as_default();
    }

    @Test(priority = 53)
    public void TC_53_delete_search_template_OK() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Verify that can delete template
        destinationSearch.delete_search_template_OK();
    }

    @Test(priority = 54)
    public void TC_54_delete_search_template_Cancel() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Close delete template popup
        destinationSearch.delete_search_template_Cancel();
    }

    @Test(priority = 55)
    public void TC_55_reset_search_criteria() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Verify that can reset search condition
        destinationSearch.reset_search_criteria();
    }

    @Test(priority = 56)
    public void TC_56_link_to_partner_PIC_edit_from_destination_selection() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Click first record and verify partner PIC edit page should be open
        destinationSearch.link_to_partner_PIC_edit_from_destination_selection();
    }

    @Test(priority = 57)
    public void TC_57_pagination_destination_selection() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Verify that pagination should be work normally
        destinationSearch.pagination_destination_selection();
    }

    @Test(priority = 58)
    public void TC_58_do_you_want_to_delete_this_delivered_email_OK_final_confirmation() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

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

    @Test(priority = 59)
    public void TC_59_do_you_want_to_delete_this_delivered_email_Cancel_final_confirmation() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

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

    @Test(priority = 60)
    public void TC_60_make_a_copy_final_confirmation() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Click make attachment copy button => create attachment copy mail and link to mail list page
        finalConfirmation.make_a_copy();
    }

    @Test(priority = 61)
    public void TC_61_would_you_like_to_change_this_delivery_email_to_Draft_status_OK_final_confirmation() throws InterruptedException {
        // Select format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

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

    @Test(priority = 62)
    public void TC_62_would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel_final_confirmation() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

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

    @Test(priority = 63)
    public void TC_63_back_to_destination_selection_from_final_confirmation() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Back to destination selection step
        finalConfirmation.back_to_destination_selection_step();
    }

    @Test(priority = 64)
    public void TC_64_back_to_attachment_from_final_confirmation() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

        // Search
        destinationSearch.search_contact_by_condition();

        // Select contact
        destinationSearch.select_contact();

        // Next to Final confirmation step
        destinationSearch.next_to_final_confirmation();

        // Back to attachment step
        finalConfirmation.back_to_attachment_step();
    }

    @Test(priority = 65)
    public void TC_65_back_to_basic_information_from_final_confirmation() throws InterruptedException {
        // Format
        basicInformation.format();

        // Input valid subject
        basicInformation.subject();

        // Input valid insertion
        basicInformation.insertion();

        // Next to attachment step
        basicInformation.next_to_attachment_step();

        // Next to destination selection step
        attachment.next_to_destination_selection_step();

        // Delivery information
        destinationSearch.delivery_information();

        // Commitment
        destinationSearch.commitment();

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
    public void teardown(ITestResult result) throws IOException {
        // take screenshot when test failed
        common.take_screenshot_when_test_fail(driver, result, "Create_Mail");

        // close all browsers
        driver.quit();
    }
}
