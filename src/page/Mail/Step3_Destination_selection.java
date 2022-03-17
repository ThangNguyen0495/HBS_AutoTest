package page.Mail;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Common.Common;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
import static utilities.Link_and_Path.HBS.contact_list_path;

public class Step3_Destination_selection extends Delivered_Mail_Page {
    // Delivery information
    // Matter
    @FindBy(css = "#searchtype > label > span > input")
    List<WebElement> delivery_type;

    @FindBy(css = "label>span>input[id^='wants_location']")
    List<WebElement> delivery_area;

    @FindBy(css = "form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")
    WebElement delivery_area_error;

    @FindBy(css = "#jobtype > label > span:nth-child(1)")
    List<WebElement> matter_delivery_occupation;

    @FindBy(css = "form > div:nth-child(4) > div:nth-child(1)> div > div.ant-form-item-explain > div")
    WebElement matter_delivery_occupation_error;

    @FindBy(css = "#jobtype_dev > label > span > input")
    List<WebElement> matter_detail_of_delivery_occupation_dev;

    @FindBy(css = "form > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div > div.ant-form-item-explain > div")
    WebElement matter_detail_of_delivery_occupation_dev_error;

    @FindBy(css = "label>span>input[id^='jobskill_dev']")
    List<WebElement> matter_delivery_skill_details_dev;

    @FindBy(css = "form > div:nth-child(4) > div:nth-child(2) > div:nth-child(2) > div > div.ant-form-item-explain > div")
    WebElement matter_delivery_skill_details_dev_error;

    @FindBy(css = "#jobtype_infra > label > span > input")
    List<WebElement> matter_detail_of_delivery_occupation_infra;

    @FindBy(css = "form > div:nth-child(4) > div:nth-child(3) > div:nth-child(1) > div > div.ant-form-item-explain > div")
    WebElement matter_detail_of_delivery_occupation_infra_error;

    @FindBy(css = "label>span>input[id^='jobskill_infra']")
    List<WebElement> matter_delivery_skill_details_infra;

    @FindBy(css = "form > div:nth-child(4) > div:nth-child(3) > div:nth-child(2) > div > div.ant-form-item-explain > div")
    WebElement matter_delivery_skill_details_infra_error;

    @FindBy(css = "#jobtype_other > label > span > input")
    List<WebElement> matter_detail_of_delivery_occupation_others;

    @FindBy(css = "form > div:nth-child(4) > div:nth-child(4) > div > div > div.ant-form-item-explain > div")
    WebElement matter_detail_of_delivery_occupation_others_error;

    @FindBy(css = "#job_syouryu > label > span.ant-radio > input")
    List<WebElement> matter_delivery_commercial_distribution;

    @FindBy(css = "form > div:nth-child(4) > div:nth-child(5)> div > div.ant-form-item-explain > div")
    WebElement matter_delivery_commercial_distribution_error;

    // Personnel
    @FindBy(css = "form > div:nth-child(5) > div:nth-child(1) > div > div.ant-form-item-explain > div")
    WebElement personnel_delivery_occupation_error;

    @FindBy(css = "#personneltype_dev")
    WebElement personnel_delivery_occupation_dev;

    @FindBy(css = "div.ant-row.ant-row-start>label>span>input[id^='personneltype_dev']")
    List<WebElement> personnel_detail_of_delivery_occupation_dev;

    @FindBy(css = "form > div:nth-child(5) > div > div > div > div > div > div:nth-child(1) > div> div.ant-form-item-explain > div")
    WebElement personnel_detail_of_delivery_occupation_dev_error;

    @FindBy(css = "label>span>input[id^='personnelskill_dev']")
    List<WebElement> personnel_delivery_skill_details_dev;

    @FindBy(css = "form > div:nth-child(5) > div > div > div > div > div > div:nth-child(2) > div> div.ant-form-item-explain > div")
    WebElement personnel_delivery_skill_details_dev_error;

    @FindBy(css = "#personneltype_infra")
    WebElement personnel_delivery_occupation_infra;

    @FindBy(css = "div.ant-form-item-control-input-content>label>span>input[id^='personneltype_infra']")
    List<WebElement> personnel_detail_of_delivery_occupation_infra;

    @FindBy(css = "form > div:nth-child(5) > div > div > div > div > div > div:nth-child(1) > div > div.ant-form-item-explain > div")
    WebElement personnel_detail_of_delivery_occupation_infra_error;

    @FindBy(css = "label>span>input[id^='personnelskill_infra']")
    List<WebElement> personnel_delivery_skill_details_infra;

    @FindBy(css = "form > div:nth-child(5) > div > div > div > div > div > div:nth-child(2) > div > div.ant-form-item-explain > div")
    WebElement personnel_delivery_skill_details_infra_error;

    @FindBy(css = "#personneltype_other")
    WebElement personnel_delivery_occupation_others;

    @FindBy(css = "div.ant-form-item-control-input-content>label>span>input[id^='personneltype_other']")
    List<WebElement> personnel_detail_of_delivery_occupation_others;

    @FindBy(css = "form > div:nth-child(5) > div > div > div > div > div > div > div > div.ant-form-item-explain > div")
    WebElement personnel_detail_of_delivery_occupation_others_error;

    @FindBy(css = "#job_koyou > label > span > input")
    List<WebElement> personnel_delivery_employment_form;

    @FindBy(css = "form > div:nth-child(5) > div:nth-child(2) > div > div.ant-form-item-explain > div")
    WebElement personnel_delivery_employment_form_error;

    @FindBy(css = "#personnel_syouryu > label > span > input")
    List<WebElement> personnel_delivery_commercial_distribution;

    @FindBy(css = "form > div:nth-child(5) > div:nth-child(3) > div > div.ant-form-item-explain > div")
    WebElement personnel_delivery_commercial_distribution_error;

    //Commitment
    @FindBy(css = "div:nth-child(2) > div.ant-form-item-control > div > div > div > div > button")
    WebElement account_status;

    @FindBy(css = "label>span>input[id^='contact__org']")
    List<WebElement> account_status_list;

    @FindBy(css = "form > div:nth-child(6) > div:nth-child(2) > div > div.ant-form-item-explain > div > div")
    WebElement account_status_error;

    @FindBy(css = "div:nth-child(3) > div > div > div > div > div > div > button")
    WebElement in_house_person_in_charge;

    @FindBy(css = "#contact__staff")
    WebElement in_house_person_in_charge_dropdown;

    @FindBy(css = "form > div:nth-child(6) > div:nth-child(3) > div > div.ant-form-item-explain > div > div")
    WebElement in_house_person_in_charge_error;

    @FindBy(css = "div:nth-child(4) > div > div > div > div > div > button")
    WebElement compatibility;

    @FindBy(css = "#contact__category")
    WebElement compatibility_dropdown_1;

    @FindBy(css = "div:nth-child(2) > div > span.ant-select-selection-item")
    WebElement compatibility_dropdown_2;

    @FindBy(css = "form > div:nth-child(6) > div:nth-child(4) > div > div.ant-form-item-explain > div > div")
    WebElement compatibility_error;

    @FindBy(css = "div:nth-child(5) > div > div > div > div > div > div > button")
    WebElement tag;

    @FindBy(css = "div.ant-col.ant-col-12 > div > div > div > div > div")
    WebElement tag_dropdown_1;

    @FindBy(css = "div.ant-col.ant-col-2 > div > div > span.ant-select-selection-item")
    WebElement tag_dropdown_2;

    @FindBy(css = "form > div:nth-child(6) > div:nth-child(5) > div > div.ant-form-item-explain > div > div")
    WebElement tag_error_1;

    @FindBy(css = "form > div:nth-child(6) > div.ant-form-item-has-error > div > div.ant-form-item-explain > div")
    WebElement tag_error_2;

    @FindBy(css = "div:nth-child(6) > div > div > div:nth-child(1) > div > button")
    WebElement search_button;

    @FindBy(css = "li.ant-pagination-item-active")
    WebElement current_page;

    @FindBy(css = "div.ant-table-selection>label>span>input")
    WebElement select_all_contact;

    @FindBy(css = "tr:nth-child(1) > td > div > div > div > label > span > input")
    WebElement select_first_contact;

    @FindBy(css = "div:nth-child(4) > div > button")
    WebElement next_step_button;

    @FindBy(css = "div.ant-message-custom-content>span:nth-child(2)")
    WebElement message;

    @FindBy(css = "div:nth-child(2) > div.ant-row-start > button")
    WebElement reset_search_criteria_button;

    @FindBy(css = "div.ant-steps-item-icon")
    List<WebElement> step_list;

    @FindBy(css = "div:nth-child(1)>div.ant-col.ant-col-24 > div > div:nth-child(1) > div > button")
    WebElement back_button;

    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(2)")
    WebElement ok_button;

    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(1)")
    WebElement cancel_button;

    @FindBy(css = "#newTemplateName")
    WebElement template_name;

    @FindBy(css = "div.ant-form-item-explain-error")
    WebElement template_name_error;

    @FindBy(css = "div.ant-message-error>span:nth-child(2)")
    WebElement message_error;

    @FindBy(css = "span:nth-child(4)>button")
    WebElement save_button_disable;

    @FindBy(css = "div:nth-child(2) > div > div > div > div > span.ant-select-selection-item")
    WebElement template_dropdown;

    @FindBy(css = "div > button[type]:nth-child(2)")
    WebElement delete_template_button;

    @FindBy(css = "div>button[type]:nth-child(4)")
    WebElement save_button;

    @FindBy(css = "div:nth-child(3) > div > div:nth-child(2) > button")
    WebElement ok_button_template;

    @FindBy(css = "div:nth-child(3) > div > div:nth-child(1) > button")
    WebElement cancel_button_template;

    @FindBy(css = "div > button[type]:nth-child(3)")
    WebElement set_template_default;

    @FindBy(css = "tr.ant-table-row:nth-child(1) > td:nth-child(2)")
    WebElement first_partner_PIC;

    @FindBy(css = "li.ant-pagination-next")
    WebElement next_page;

    @FindBy(css = "li.ant-pagination-prev")
    WebElement previous_page;

    @FindBy(css = "div.rc-virtual-list-holder-inner>div")
    List<WebElement> tags_list;

    // Register Mode
    public Step3_Destination_selection(WebDriver driver, String role, Common common, String domain, String Mode) {
        super(driver, role, common, domain, Mode);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Edit Mode
    public Step3_Destination_selection(WebDriver driver, String role, Common common, String domain, String mail_id, String mail_status, String Mode) {
        super(driver, role, common, domain, mail_id, mail_status, Mode);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //** Delivery information **//
    public void delivery_information() {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
                reset_search_criteria_button.click();
            }

            //** Delivery information **//
            //** Delivery type **//
            // 0: Deliver the matter, 1: Deliver personnel, 2: Deliver information
            int delivery_type_id = 2;//RandomUtils.nextInt(3);
            key.moveToElement(delivery_type.get(delivery_type_id)).click().build().perform();

            // Search destination selection by delivery type
            // Delivery type: Deliver the matter
            if (delivery_type_id == 0) {
                // ** Delivery area **//
                // 0: Hokaido, 1: Tohoku, 2: Kanto, 3:Chubu , 4:Tokai , 5:Kansai , 6:Shikoku , 7:China , 8:Kyushu , 9: others
                int number_of_delivery_area = RandomUtils.nextInt(10);

                //select delivery area from 1 to number_of_delivery_area
                for (int i = 0; i <= number_of_delivery_area; i++) {
                    delivery_area.get(i).click();
                }

                //** Delivery occupation **//
                // 0: Development, 1:Infrastructure, 2: Others
                int delivery_occupation = RandomUtils.nextInt(3);
                matter_delivery_occupation.get(delivery_occupation).click();

                // Delivery occupation: development
                if (delivery_occupation == 0) {
                    //Details of delivery occupation
                    // 0:Designer, 1:FE, 2:BE, 3:PM/Director, 4:Others
                    int detail_of_delivery_occupation = RandomUtils.nextInt(5);
                    matter_detail_of_delivery_occupation_dev.get(detail_of_delivery_occupation).click();

                    // Delivery skill details
                    // 0:Requirement definition, 1:Basic design, 2:Detailed design, 3:Manufacturing, 4:Testing/verification, 5:Maintenance and operation, 6:Inexperienced
                    int number_of_delivery_skill_details = RandomUtils.nextInt(7);

                    // select delivery skill details from 1 to number_of_delivery_skill_details
                    for (int i = 0; i <= number_of_delivery_skill_details; i++) {
                        matter_delivery_skill_details_dev.get(i).click();
                    }
                }

                // Delivery occupation: infrastructure
                else if (delivery_occupation == 1) {
                    //Details of delivery occupation
                    // 0:Server, 1:Network, 2:Security, 3:Database, 4:Information system, 5:Others
                    int detail_of_delivery_occupation = RandomUtils.nextInt(6);
                    matter_detail_of_delivery_occupation_infra.get(detail_of_delivery_occupation).click();

                    // Delivery skill details
                    // 0:Requirement definition, 1:Basic design, 2:Detailed design, 3:Manufacturing, 4:Testing/verification
                    // 5:Maintenance and operation, 6:Surveillance, 7:Inexperienced
                    int number_of_delivery_skill_details = RandomUtils.nextInt(8);

                    // select delivery skill details from 1 to number_of_delivery_skill_details
                    for (int i = 0; i <= number_of_delivery_skill_details; i++) {
                        matter_delivery_skill_details_infra.get(i).click();
                    }
                }
                // Delivery occupation: others
                else {
                    //Details of delivery occupation
                    // 0:Sales/office work, 1:Base station, 2:Call center support desk, 3:Others
                    int detail_of_delivery_occupation = RandomUtils.nextInt(4);
                    matter_detail_of_delivery_occupation_others.get(detail_of_delivery_occupation).click();
                }

                //** Delivery commercial distribution **//
                // 0:End direct/main contract direct , 1:Primary contract, 2:Secondary contract, 3:Third contract/unknown
                int delivery_commercial_distribution = RandomUtils.nextInt(4);
                matter_delivery_commercial_distribution.get(delivery_commercial_distribution).click();
            }

            // Delivery type: Deliver personnel
            else if (delivery_type_id == 1) {
                // ** Delivery area **//
                // 0: Hokaido, 1: Tohoku, 2: Kanto, 3:Chubu , 4:Tokai , 5:Kansai , 6:Shikoku , 7:China , 8:Kyushu , 9: others
                int number_of_delivery_area = RandomUtils.nextInt(10);

                //select delivery area from 1 to number_of_delivery_area
                for (int i = 0; i <= number_of_delivery_area; i++) {
                    delivery_area.get(i).click();
                }

                //** Delivery occupation **//
                // 0: Development, 1:Infrastructure, 2: Others
                int delivery_occupation = RandomUtils.nextInt(3);

                // select delivery occupation from 1 to delivery_occupation
                for (int i = 0; i <= delivery_occupation; i++) {
                    // Delivery occupation: development
                    if (i == 0) {
                        // Development checkbox
                        personnel_delivery_occupation_dev.click();

                        //Details of delivery occupation
                        // 0:Designer, 1:FE, 2:BE, 3:PM/Director, 4:Others
                        int detail_of_delivery_occupation = RandomUtils.nextInt(5);

                        // select details of delivery occupation from 1 to detail_of_delivery_occupation
                        for (int j = 0; j <= detail_of_delivery_occupation; j++) {
                            personnel_detail_of_delivery_occupation_dev.get(j).click();
                        }

                        // Delivery skill details
                        // 0:Requirement definition, 1:Basic design, 2:Detailed design, 3:Manufacturing
                        // 4:Testing/verification, 5:Maintenance and operation, 6:Inexperienced
                        int number_of_delivery_skill_details = RandomUtils.nextInt(7);

                        // select delivery skill details from 1 to number_of_delivery_skill_details
                        for (int k = 0; k <= number_of_delivery_skill_details; k++) {
                            personnel_delivery_skill_details_dev.get(k).click();
                        }
                    }

                    // Delivery occupation: infrastructure
                    else if (i == 1) {
                        // Infrastructure checkbox
                        personnel_delivery_occupation_infra.click();

                        //Details of delivery occupation
                        // 0:Server, 1:Network, 2:Security, 3:Database, 4:Information system, 5:Others
                        int detail_of_delivery_occupation = RandomUtils.nextInt(6);

                        //select details of delivery occupation from 1 to detail_of_delivery_occupation
                        for (int j = 0; j <= detail_of_delivery_occupation; j++) {
                            personnel_detail_of_delivery_occupation_infra.get(j).click();
                        }

                        // Delivery skill details
                        // 0:Requirement definition, 1:Basic design, 2:Detailed design, 3:Manufacturing, 4:Testing/verification
                        // 5:Maintenance and operation, 6:Surveillance, 7:Inexperienced
                        int number_of_delivery_skill_details = RandomUtils.nextInt(8);

                        // select delivery skill details from 1 to number_of_delivery_skill_details
                        for (int k = 0; k <= number_of_delivery_skill_details; k++) {
                            personnel_delivery_skill_details_infra.get(k).click();
                        }
                    }
                    // Delivery occupation: others
                    else {
                        // Others checkbox
                        personnel_delivery_occupation_others.click();

                        //Details of delivery occupation
                        // 0:Sales/office work, 1:Base station, 2:Call center support desk, 3:Others
                        int detail_of_delivery_occupation = RandomUtils.nextInt(4);
                        for (int j = 0; j <= detail_of_delivery_occupation; j++) {
                            personnel_detail_of_delivery_occupation_others.get(j).click();
                        }
                    }

                    //** Delivery employment form **//
                    // 0:Proper , 1:Freelance
                    int delivery_employment_form = RandomUtils.nextInt(2);
                    personnel_delivery_employment_form.get(delivery_employment_form).click();

                    //** Delivery commercial distribution **//
                    // 0:Company affiliation , 1:1 company affiliation, 2:2 company affiliation, 3:Affiliation/unknown for 3 or more companies
                    int delivery_commercial_distribution = RandomUtils.nextInt(4);
                    personnel_delivery_commercial_distribution.get(delivery_commercial_distribution).click();
                }
            }
        }
    }

    //** Commitment **//
    public void account_status() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Account status
            // 0: Do not search by Account status, 1: add Account status conditions
            int search_by_account_status = RandomUtils.nextInt(2);
            if (search_by_account_status == 1) {
                // add Account status conditions
                account_status.click();
                // 1: Prospect, 2:Approached, 3:Information exchanged, 4:Contract record available
                int account_status_condition = RandomUtils.nextInt(4);
                // select Account status conditions
                for (int i = 0; i <= account_status_condition; i++) {
                    account_status_list.get(i).click();
                }
            }
        }
    }

    public void in_house_person_in_charge() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // In-house person in charge
            // 0: Do not search by In-house person in charge, 1: add In-house person in charge condition
            int search_by_in_house_person_in_charge = RandomUtils.nextInt(2);
            if (search_by_in_house_person_in_charge == 1) {
                // add In-house person in charge condition
                in_house_person_in_charge.click();
                wait_for_loading_element(in_house_person_in_charge_dropdown);
//            sleep(3000); // Wait dropdown has been loaded
                in_house_person_in_charge_dropdown.click(); // Open In-house person in charge dropdown
                int id = RandomUtils.nextInt(20);
                for (int i = 0; i <= id; i++) {
                    key.sendKeys(Keys.DOWN).perform();
                }
                // Wait and select In-house person in charge
                sleep(1000);
                key.sendKeys(Keys.ENTER).perform();
            }
        }
    }

    public void compatibility() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Compatibility
            // 0: Do not search by Compatibility, 1: add Compatibility condition
            int search_by_compatibility = RandomUtils.nextInt(2);
            if (search_by_compatibility == 1) {
                // add Compatibility condition
                compatibility.click();

                // Open Compatibility dropdown
                compatibility_dropdown_1.click();

                // 0: Like, 1: Dislike
                int compatibility_condition_1 = RandomUtils.nextInt(2);
                for (int i = 0; i <= compatibility_condition_1; i++) {
                    key.sendKeys(Keys.DOWN).perform();
                }
                key.sendKeys(Keys.ENTER).perform();

                // 0: Matches with, 1: Do not match with
                int compatibility_condition_2 = RandomUtils.nextInt(2);
                compatibility_dropdown_2.click();
                for (int i = 0; i <= compatibility_condition_2; i++) {
                    key.sendKeys(Keys.DOWN).perform();
                }
                key.sendKeys(Keys.ENTER).perform();
            }
        }
    }

    public void tag() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Tag
            // 0: Do not search by Tag, 1: add Tag condition
            int search_by_tag = RandomUtils.nextInt(2);
            if (search_by_tag == 1) {
                // add Tag condition
                tag.click();
                // wait Tag dropdown has been loaded
                wait_for_loading_element(tag_dropdown_1);
//            sleep(3000);
                // number of tags has been selected in range 1-5
                int number_of_tags = RandomUtils.nextInt(5) + 1;
                // Open Tag dropdown
                tag_dropdown_1.click();
                for (int i = 0; i < number_of_tags; i++) {
                    key.sendKeys(Keys.DOWN).perform();
                    key.sendKeys(Keys.ENTER).perform();
                }

                // 0: AND, 1: OR
                int tag_condition_2 = RandomUtils.nextInt(2);
                tag_dropdown_2.click();
                for (int i = 0; i <= tag_condition_2; i++) {
                    key.sendKeys(Keys.DOWN).perform();
                }
                key.sendKeys(Keys.ENTER).perform();
            }
        }
    }

    public void search_contact_by_condition() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Master, Administrator, Responsible person, Leader, Member
            if (common.authorized(role, common.role_list(5))) {
                // Search
                key.moveToElement(search_button).click().build().perform();

                // Waiting for loading result
                sleep(1000);
            }
        }
    }

    public Boolean check_result_is_null() throws InterruptedException {
        // check null = "True" => No partner PIC
        boolean check_null = false;
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            sleep(3000);
            current_page.click();
        } catch (NoSuchElementException ex) {
            check_null = true;
        }
        return check_null;
    }

    public void select_contact() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            while (check_result_is_null()) {
                System.out.println("Can not find partner PIC with this condition.");
                System.out.println("Please add more data or change your search condition.");
                System.out.println("Search condition have been changed and try again, please wait ...");
                System.out.println("-----------------------------------------------------------------");
                delivery_information();
//                commitment();
                search_contact_by_condition();
            }
            // Select contact
            // 0: select all contact, 1: select first contact
            int contact = RandomUtils.nextInt(2);
            // Scroll down
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            // select all contact
            if (contact == 0) {
                select_all_contact.click();
            }
            // select first contact
            else {
                select_first_contact.click();
            }
        }
    }

    public void commitment() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            account_status();
            in_house_person_in_charge();
            compatibility();
            tag();
        }
    }

    public void next_to_final_confirmation() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Next to 最終確認_step
            next_step_button.click();

            if (Mode.equals("Create")) {

                // wait for message
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                wait.until(ExpectedConditions.invisibilityOf(message));

                // check message
                soft.assertEquals(text, "アイテムが更新されました", "[Failed][Destination selection] Message do not match.");
            } else {
                // waiting for final confirmation step loading
                sleep(5000);
            }

            // Check current tab
            soft.assertTrue(step_list.get(3).isEnabled(), "[Failed][Destination selection] Can not next to Final confirmation from Destination selection.");
            soft.assertAll();
        }
    }

    public void deliver_the_matter_do_not_select_condition() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                reset_search_criteria_button.click();
            }

            // Delivery type: Deliver the matter
            delivery_type.get(0).click();

            // Click Search button
            search_button.click();

            //Delivery area
            String text1 = wait.until(ExpectedConditions.visibilityOf(delivery_area_error)).getText();
            soft.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

            // Delivery occupation
            String text2 = wait.until(ExpectedConditions.visibilityOf(matter_delivery_occupation_error)).getText();
            soft.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation] Message do not match");

            // Delivery commercial distribution
            String text3 = wait.until(ExpectedConditions.visibilityOf(matter_delivery_commercial_distribution_error)).getText();
            soft.assertEquals(text3, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

            soft.assertAll();
        }
    }

    public void deliver_the_matter_only_select_delivery_occupation_development() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                reset_search_criteria_button.click();
            }

            // Delivery type: Deliver the matter
            delivery_type.get(0).click();

            // Delivery occupation: Development
            matter_delivery_occupation.get(0).click();

            // Click Search button
            search_button.click();

            //Delivery area
            String text1 = wait.until(ExpectedConditions.visibilityOf(delivery_area_error)).getText();
            soft.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

            // Delivery occupation/Development/Delivery job details
            String text2 = wait.until(ExpectedConditions.visibilityOf(matter_detail_of_delivery_occupation_dev_error)).getText();
            soft.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery job details] Message do not match");

            // Delivery occupation/Development/Delivery skill details
            String text3 = wait.until(ExpectedConditions.visibilityOf(matter_delivery_skill_details_dev_error)).getText();
            soft.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery skill details] Message do not match");

            // Delivery commercial distribution
            String text4 = wait.until(ExpectedConditions.visibilityOf(matter_delivery_commercial_distribution_error)).getText();
            soft.assertEquals(text4, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

            soft.assertAll();
        }
    }

    public void deliver_the_matter_only_select_delivery_occupation_infrastructure() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                reset_search_criteria_button.click();
            }

            // Delivery type: Deliver the matter
            delivery_type.get(0).click();

            // Delivery occupation: Infrastructure
            matter_delivery_occupation.get(1).click();

            // Click Search button
            search_button.click();

            //Delivery area
            String text1 = wait.until(ExpectedConditions.visibilityOf(delivery_area_error)).getText();
            soft.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

            // Delivery occupation/Infrastructure/Delivery job details
            String text2 = wait.until(ExpectedConditions.visibilityOf(matter_detail_of_delivery_occupation_infra_error)).getText();
            soft.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery job details] Message do not match");

            // Delivery occupation/Infrastructure/Delivery skill details
            String text3 = wait.until(ExpectedConditions.visibilityOf(matter_delivery_skill_details_infra_error)).getText();
            soft.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery skill details] Message do not match");

            // Delivery commercial distribution
            String text4 = wait.until(ExpectedConditions.visibilityOf(matter_delivery_commercial_distribution_error)).getText();
            soft.assertEquals(text4, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

            soft.assertAll();
        }
    }

    public void deliver_the_matter_only_select_delivery_occupation_others() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                reset_search_criteria_button.click();
            }

            // Delivery type: Deliver the matter
            delivery_type.get(0).click();

            // Delivery occupation: Others
            matter_delivery_occupation.get(2).click();

            // Click Search button
            search_button.click();

            //Delivery area
            String text1 = wait.until(ExpectedConditions.visibilityOf(delivery_area_error)).getText();
            soft.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

            // Delivery occupation/Others/Delivery job details
            String text2 = wait.until(ExpectedConditions.visibilityOf(matter_detail_of_delivery_occupation_others_error)).getText();
            soft.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Others/Delivery job details] Message do not match");

            // Delivery commercial distribution
            String text3 = wait.until(ExpectedConditions.visibilityOf(matter_delivery_commercial_distribution_error)).getText();
            soft.assertEquals(text3, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

            soft.assertAll();
        }
    }

    /**
     *
     */
    public void deliver_the_personnel_do_not_select_condition() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                reset_search_criteria_button.click();
            }

            // Delivery type: Deliver the personnel
            delivery_type.get(1).click();

            // Click Search button
            search_button.click();

            //Delivery area
            String text1 = wait.until(ExpectedConditions.visibilityOf(delivery_area_error)).getText();
            soft.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

            // Delivery occupation
            String text2 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_occupation_error)).getText();
            soft.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation] Message do not match");

            // Delivery employment form
            String text3 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_employment_form_error)).getText();
            soft.assertEquals(text3, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

            // Delivery commercial distribution
            String text4 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_commercial_distribution_error)).getText();
            soft.assertEquals(text4, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

            soft.assertAll();
        }
    }

    public void deliver_the_personnel_only_select_delivery_occupation_development() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                reset_search_criteria_button.click();
            }

            // Delivery type: Deliver the personnel
            delivery_type.get(1).click();

            // Delivery occupation: Development
            personnel_delivery_occupation_dev.click();

            // Click Search button
            search_button.click();

            //Delivery area
            String text1 = wait.until(ExpectedConditions.visibilityOf(delivery_area_error)).getText();
            soft.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

            // Delivery occupation/Development/Delivery job details
            String text2 = wait.until(ExpectedConditions.visibilityOf(personnel_detail_of_delivery_occupation_dev_error)).getText();
            soft.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery job details] Message do not match");

            // Delivery occupation/Development/Delivery skill details
            String text3 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_skill_details_dev_error)).getText();
            soft.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery skill details] Message do not match");

            // Delivery employment form
            String text4 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_employment_form_error)).getText();
            soft.assertEquals(text4, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

            // Delivery commercial distribution
            String text5 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_commercial_distribution_error)).getText();
            soft.assertEquals(text5, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

            soft.assertAll();
        }
    }

    public void deliver_the_personnel_only_select_delivery_occupation_infrastructure() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                reset_search_criteria_button.click();
            }

            // Delivery type: Deliver the personnel
            delivery_type.get(1).click();

            // Delivery occupation: Infrastructure
            personnel_delivery_occupation_infra.click();

            // Click Search button
            search_button.click();

            //Delivery area
            String text1 = wait.until(ExpectedConditions.visibilityOf(delivery_area_error)).getText();
            soft.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

            // Delivery occupation/Infrastructure/Delivery job details
            String text2 = wait.until(ExpectedConditions.visibilityOf(personnel_detail_of_delivery_occupation_infra_error)).getText();
            soft.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery job details] Message do not match");

            // Delivery occupation/Infrastructure/Delivery skill details
            String text3 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_skill_details_infra_error)).getText();
            soft.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery skill details] Message do not match");

            // Delivery employment form
            String text4 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_employment_form_error)).getText();
            soft.assertEquals(text4, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

            // Delivery commercial distribution
            String text5 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_commercial_distribution_error)).getText();
            soft.assertEquals(text5, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

            soft.assertAll();
        }
    }

    public void deliver_the_personnel_only_select_delivery_occupation_others() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                reset_search_criteria_button.click();
            }

            // Delivery type: Deliver the personnel
            delivery_type.get(1).click();

            // Delivery occupation: Others
            personnel_delivery_occupation_others.click();

            // Click Search button
            search_button.click();

            //Delivery area
            String text1 = wait.until(ExpectedConditions.visibilityOf(delivery_area_error)).getText();
            soft.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

            // Delivery occupation/Others/Delivery job details
            String text2 = wait.until(ExpectedConditions.visibilityOf(personnel_detail_of_delivery_occupation_others_error)).getText();
            soft.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Others/Delivery job details] Message do not match");

            // Delivery employment form
            String text3 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_employment_form_error)).getText();
            soft.assertEquals(text3, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

            // Delivery commercial distribution
            String text4 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_commercial_distribution_error)).getText();
            soft.assertEquals(text4, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

            soft.assertAll();
        }
    }

    public void deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Reset search criteria
            if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
                reset_search_criteria_button.click();
            }

            // Delivery type: Deliver the personnel
            delivery_type.get(1).click();

            // Delivery occupation: Development
            personnel_delivery_occupation_dev.click();

            // Delivery occupation: Infrastructure
            personnel_delivery_occupation_infra.click();

            // Delivery occupation: Others
            personnel_delivery_occupation_others.click();

            // Click Search button
            search_button.click();

            //Delivery area
            String text1 = wait.until(ExpectedConditions.visibilityOf(delivery_area_error)).getText();
            soft.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

            // Delivery occupation/Development/Delivery job details
            String text2 = wait.until(ExpectedConditions.visibilityOf(personnel_detail_of_delivery_occupation_dev_error)).getText();
            soft.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery job details] Message do not match");

            // Delivery occupation/Development/Delivery skill details
            String text3 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_skill_details_dev_error)).getText();
            soft.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery skill details] Message do not match");

            // Delivery occupation/Infrastructure/Delivery job details
            String text4 = wait.until(ExpectedConditions.visibilityOf(personnel_detail_of_delivery_occupation_infra_error)).getText();
            soft.assertEquals(text4, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery job details] Message do not match");

            // Delivery occupation/Infrastructure/Delivery skill details
            String text5 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_skill_details_infra_error)).getText();
            soft.assertEquals(text5, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery skill details] Message do not match");

            // Delivery occupation/Others/Delivery job details
            String text6 = wait.until(ExpectedConditions.visibilityOf(personnel_detail_of_delivery_occupation_others_error)).getText();
            soft.assertEquals(text6, "必ず1つ選択してください", "[Delivery occupation/Others/Delivery job details] Message do not match");

            // Delivery employment form
            String text7 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_employment_form_error)).getText();
            soft.assertEquals(text7, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

            // Delivery commercial distribution
            String text8 = wait.until(ExpectedConditions.visibilityOf(personnel_delivery_commercial_distribution_error)).getText();
            soft.assertEquals(text8, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

            soft.assertAll();
        }
    }

    public void do_not_select_account_status() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Select delivery information
            delivery_information();

            // Account status: ON
            account_status.click();

            // Click Search button
            search_button.click();

            // Account status
            String text = wait.until(ExpectedConditions.visibilityOf(account_status_error)).getText();
            soft.assertEquals(text, "必ず1つ選択してください", "[Account status] Message do not match");

            soft.assertAll();
        }
    }

    public void do_not_select_in_house_person_in_charge() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Select delivery information
            delivery_information();

            // In-house person in charge: ON
            in_house_person_in_charge.click();

            // Click Search button
            search_button.click();

            // In-house person in charge
            String text = wait.until(ExpectedConditions.visibilityOf(in_house_person_in_charge_error)).getText();
            soft.assertEquals(text, "必ず選択してください", "[In-house person in charge] Message do not match");

            soft.assertAll();
        }
    }

    public void do_not_select_compatibility() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Select delivery information
            delivery_information();

            // Compatibility: ON
            compatibility.click();

            // Click Search button
            search_button.click();

            // Compatibility
            String text = wait.until(ExpectedConditions.visibilityOf(compatibility_error)).getText();
            soft.assertEquals(text, "必ず選択してください", "[Compatibility] Message do not match");

            soft.assertAll();
        }
    }

    public void do_not_select_tag() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Select delivery information
            delivery_information();

            // Tag: ON
            tag.click();

            // Click Search button
            search_button.click();

            // Tag
            String text = wait.until(ExpectedConditions.visibilityOf(tag_error_1)).getText();
            soft.assertEquals(text, "必ず1つ選択してください", "[Tag] Message do not match");

            soft.assertAll();
        }
    }

    public void select_exceed_5_tags() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Select delivery information
            delivery_information();

            // Tag: ON
            tag.click();

            // Waiting for loading tag list
            wait_for_loading_element(tag_dropdown_1);

            if (tags_list.size() < 6) {
                System.out.println("Number of tags < 6, so can not verify that case.\nPlease add more tags and try again.");
            } else {
                // Select 6 tags
                tag_dropdown_1.click();
                for (int i = 0; i < 6; i++) {
                    key.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
                }

                // Tag
                String text = wait.until(ExpectedConditions.visibilityOf(tag_error_2)).getText();
                soft.assertEquals(text, "これ以上選択できません", "[Tag] Message do not match");

                soft.assertAll();
            }
        }
    }

    public void back_to_attachment_step() {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Back to Attachment step
            back_button.click();

            // Check current tab
            soft.assertTrue(step_list.get(1).isEnabled(), "[Failed] Can not back to Attachment from Destination selection.");

            // Back to Destination selection step
            wait.until(ExpectedConditions.elementToBeClickable(next_step_button)).click();

            // Back to Attachment step
            key.moveToElement(step_list.get(1)).click().build().perform();

            // Check current tab
            soft.assertTrue(step_list.get(1).isEnabled(), "[Failed] Can not back to Attachment from Destination selection.");

            soft.assertAll();
        }
    }

    public void back_to_basic_information_step() {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Back to Basic information step
            step_list.get(0).click();

            // Check current tab
            soft.assertTrue(step_list.get(0).isEnabled(), "[Failed] Can not back to Basic information from Destination selection.");

            soft.assertAll();
        }
    }

    public Boolean check_template_in_stock() {
        // Check save button is enable
        // true: in stock
        // false: out of stock
        boolean check = false;
        try {
            save_button_disable.getAttribute("type");
        } catch (NoSuchElementException ex) {
            check = true;
        }
        return check;
    }

    public void delete_search_template() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Wait message hide
            wait.until(ExpectedConditions.invisibilityOf(message));

            // Select template dropdown list
            wait.until(ExpectedConditions.elementToBeClickable(template_dropdown)).click();

            // Select template
            key.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();

            // Click delete icon
            wait.until(ExpectedConditions.elementToBeClickable(delete_template_button)).click();

            //Click OK button
            wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

            sleep(3000);
        }
    }


    public void leave_search_template_name_blank() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Check save button is enable
            if (!check_template_in_stock()) {
                delete_search_template();
            }
            // Click Save template button
            wait.until(ExpectedConditions.elementToBeClickable(save_button)).click();

            // Leave search template name blank and verify error message
            wait.until(ExpectedConditions.elementToBeClickable(template_name))
                    .sendKeys("leave_blank");
            for (int i = 0; i < 11; i++) {
                key.sendKeys(Keys.BACK_SPACE).perform();
            }
            String text = wait.until(ExpectedConditions.visibilityOf(template_name_error)).getText();
            soft.assertEquals(text, "テンプレート名を入力してください", "[Template name] Message do not match");

            soft.assertAll();
        }
    }

    public void search_template_name_exceed_50_half_width_characters() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Check save button is enable
            if (!check_template_in_stock()) {
                delete_search_template();
            }
            // Click Save template button
            wait.until(ExpectedConditions.elementToBeClickable(save_button)).click();

            // Input search template name exceed 50 half width characters and verify error message
            wait.until(ExpectedConditions.elementToBeClickable(template_name))
                    .sendKeys(RandomStringUtils.randomAlphabetic(51));

            sleep(2000);
            String text = wait.until(ExpectedConditions.visibilityOf(template_name_error)).getText();
            soft.assertEquals(text, "テンプレート名は50文字以内で入力してください。", "[Template name] Message do not match");

            soft.assertAll();
        }
    }

    public void search_template_name_exceed_50_full_width_characters() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Check save button is enable
            if (!check_template_in_stock()) {
                delete_search_template();
            }
            // Click Save template button
            wait.until(ExpectedConditions.elementToBeClickable(save_button)).click();

            // Input search template name exceed 50 full width characters and verify error message
            wait.until(ExpectedConditions.elementToBeClickable(template_name))
                    .sendKeys(RandomStringUtils.random(51, 0x4e00, 0x4f80, true, false));

            sleep(2000);
            String text = wait.until(ExpectedConditions.visibilityOf(template_name_error)).getText();
            soft.assertEquals(text, "テンプレート名は50文字以内で入力してください。", "[Template name] Message do not match");

            soft.assertAll();
        }
    }

    public void search_template_name_exceed_mix_50_half_and_full_width_characters() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Check save button is enable
            if (!check_template_in_stock()) {
                delete_search_template();
            }
            // Click Save template button
            wait.until(ExpectedConditions.elementToBeClickable(save_button)).click();

            int length_of_half = RandomUtils.nextInt(50) + 1;
            String template_name_text = RandomStringUtils.randomAlphabetic(length_of_half)
                    + RandomStringUtils.random(51 - length_of_half, 0x4e00, 0x4f80, true, false);
            // Input search template name exceed mix 50 half and full width characters and verify error message
            wait.until(ExpectedConditions.elementToBeClickable(template_name))
                    .sendKeys(template_name_text);

            String text = wait.until(ExpectedConditions.visibilityOf(template_name_error)).getText();
            soft.assertEquals(text, "テンプレート名は50文字以内で入力してください。", "[Template name] Message do not match");

            soft.assertAll();
        }
    }


    public void create_search_template_name_OK() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Check save button is enable
            if (!check_template_in_stock()) {
                delete_search_template();
            } else {
                wait.until(ExpectedConditions.invisibilityOf(message));
            }

            // Click Save template button
            wait.until(ExpectedConditions.elementToBeClickable(save_button)).click();

            String template_name_text = RandomStringUtils.randomAlphabetic(50);

            // Input search template name with 50 half width characters
            wait.until(ExpectedConditions.elementToBeClickable(template_name))
                    .sendKeys(template_name_text);

            // Click OK button
            wait.until(ExpectedConditions.elementToBeClickable(ok_button_template)).click();

            String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
            soft.assertEquals(text, "テンプレートを作成しました。", "[Template name] Message do not match");

            soft.assertAll();
        }
    }

    public void create_search_template_name_Cancel() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Check save button is enable
            if (!check_template_in_stock()) {
                delete_search_template();
            }
            // Click Save template button
            wait.until(ExpectedConditions.elementToBeClickable(save_button)).click();

            // Click Cancel button
            wait.until(ExpectedConditions.elementToBeClickable(cancel_button_template)).click();

            // Waiting for close popup
            sleep(500);

            // Check popup close
            boolean check = true;
            try {
                cancel_button_template.click();
            } catch (NoSuchElementException ex) {
                check = false;
            }
            soft.assertFalse(check, "[Failed] Can not close create search template popup.");

            soft.assertAll();
        }
    }

    public void input_available_search_template_name() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Check save button is enable
            if (!check_template_in_stock()) {
                delete_search_template();
            } else {
                wait
                        .until(ExpectedConditions.invisibilityOf(message));
            }
            // Click Save template button
            wait.until(ExpectedConditions.elementToBeClickable(save_button)).click();

            String template_name_text = RandomStringUtils.randomAlphabetic(50);

            // Input search template name with 50 half width characters and verify message
            wait.until(ExpectedConditions.elementToBeClickable(template_name))
                    .sendKeys(template_name_text);


            // Click OK button
            wait.until(ExpectedConditions.elementToBeClickable(ok_button_template)).click();

            // Waiting for show message
            String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
            soft.assertEquals(text, "テンプレートを作成しました。", "[Template name] Message do not match");

            // Check save button is enable
            if (!check_template_in_stock()) {
                delete_search_template();
            }

            // Click Save template button
            wait.until(ExpectedConditions.elementToBeClickable(save_button)).click();

            // Input available template name
            wait.until(ExpectedConditions.elementToBeClickable(template_name))
                    .sendKeys(template_name_text);

            // Click OK button
            wait.until(ExpectedConditions.elementToBeClickable(ok_button_template)).click();

            // Verify error message
            String text1 = wait.until(ExpectedConditions.visibilityOf(message_error)).getText();
            soft.assertTrue(text1.contains("同一名称のテンプレートが既に存在します"), "[Failed] Can create search template with available template name.");

            soft.assertAll();
        }
    }

    public void set_cancel_search_template_as_default() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Create search template
            create_search_template_name_OK();

            // Set template as default
            wait.until(ExpectedConditions.elementToBeClickable(set_template_default)).click();

            // waiting for set as default
            sleep(1000);
            String title1 = template_dropdown.getAttribute("title");
            soft.assertTrue(title1.contains("☆ :"), "[Failed] Can not set search template as default.");

            // Cancel template as default
            wait.until(ExpectedConditions.elementToBeClickable(set_template_default)).click();

            // waiting for cancel set default
            sleep(1000);
            String title2 = template_dropdown.getAttribute("title");
            soft.assertFalse(title2.contains("☆ :"), "[Failed] Can not cancel search template as default.");

            soft.assertAll();
        }
    }

    public void delete_search_template_OK() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Create search template
            create_search_template_name_OK();

            // Wait message hide
            wait.until(ExpectedConditions.invisibilityOf(message));

            // Click delete icon
            wait.until(ExpectedConditions.elementToBeClickable(delete_template_button)).click();

            //Click OK button
            wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

            // Verify message
            String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
            soft.assertEquals(text, "テンプレートを削除しました", "[Failed] Can not delete search template.");

            soft.assertAll();
        }
    }

    public void delete_search_template_Cancel() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Create search template
            create_search_template_name_OK();

            // Click delete icon
            wait.until(ExpectedConditions.elementToBeClickable(delete_template_button)).click();

            //Click cancel button
            wait.until(ExpectedConditions.elementToBeClickable(cancel_button)).click();

            // Waiting for close popup
            sleep(500);

            // Check popup close
            boolean check = true;
            try {
                cancel_button_template.click();
            } catch (NoSuchElementException ex) {
                check = false;
            }
            soft.assertFalse(check, "[Failed] Can not close delete search template popup.");

            soft.assertAll();
        }
    }

    public void reset_search_criteria() {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
        /*
        ** Delivery information ** //
        ** Delivery type ** //
         1: Deliver the matter, 2: Deliver personnel, 3: Deliver information
        */
            int delivery_type_id = RandomUtils.nextInt(3);
            delivery_type.get(delivery_type_id).click();

            // Click reset search criteria button
            reset_search_criteria_button.click();

            // Verify search condition have been reset
            soft.assertFalse(delivery_type.get(delivery_type_id).isSelected(), "[Failed] Can not reset search condition.");

            soft.assertAll();
        }
    }

    public void link_to_partner_PIC_edit_from_destination_selection() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Scroll down
            ((JavascriptExecutor) driver).executeScript("scroll(0, 550);");
            sleep(1000);

            // check null = "True" => No partner PIC => Can not link to partner PIC.
            boolean check_null = false;
            try {
                first_partner_PIC.click();
            } catch (NoSuchElementException ex) {
                check_null = true;
            }

            if (!check_null) {
                sleep(1000);
                // If we can link to partner PIC, verify current url
                soft.assertTrue(driver.getCurrentUrl().contains(domain + contact_list_path), "[Failed] Can not link to partner PIC from Destination selection");
            } else {
                System.out.println("No result.");
            }

            soft.assertAll();
        }
    }

    public void pagination_destination_selection() throws InterruptedException {
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // check null = "True" => No partner PIC
            boolean check_null = false;
            try {
                current_page.click();
            } catch (NoSuchElementException ex) {
                check_null = true;
            }

            if (!check_null) {
                sleep(1000);
                // Scroll down
                ((JavascriptExecutor) driver).executeScript("scroll(0, 1000);");
                sleep(1000);

                if (!next_page.getAttribute("class").contains("disabled")) {
                    // if we can go to next page, verify current page
                    next_page.click();
                    soft.assertEquals(current_page.getAttribute("title"), "2", "[Failed] Can not go to next page.");

                    // back to previous page
                    previous_page.click();
                    soft.assertEquals(current_page.getAttribute("title"), "1", "[Failed] Can not back to previous page.");
                }

                soft.assertAll();
            }
        }
    }
}
