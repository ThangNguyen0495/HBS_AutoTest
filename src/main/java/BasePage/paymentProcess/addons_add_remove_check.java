package BasePage.paymentProcess;

import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static BasePage.Link_and_Path.HBS.mail_list_path;
import static BasePage.Link_and_Path.HBS.register_path;
import static java.lang.Thread.sleep;

public class addons_add_remove_check extends payment {
    @FindBy(css = "div:nth-child(2) > div > div > button")
    WebElement refined_search_button;

    @FindBy(css = "button.TemplateControlButton-container-QGDeF:nth-child(3)")
    WebElement save_button_enable;

    @FindBy(css = "div.ant-message-custom-content>span:nth-child(2)")
    WebElement message;

    @FindBy(css = "span.TemplateControlButton-container-QGDeF:nth-child(3)")
    WebElement save_button_disable;

    @FindBy(css = "div.SearchTemplateSelectFormItem-selectContainer-OnKbn>div>span:nth-child(1)")
    WebElement search_template_dropdown;

    @FindBy(css = "div> button.ant-btn-dangerous")
    WebElement delete_template_button;

    @FindBy(css = "div.ant-modal-confirm-btns > button.ant-btn-primary")
    WebElement ok_button;

    @FindBy(css = "div.ant-col-24>div>span.ant-divider-inner-text")
    WebElement search_template_information;

    @FindBy(css = "span.ant-divider-inner-text")
    WebElement deliveries_information;

    @FindBy(css = "input[type='text']")
    WebElement subject;

    @FindBy(css = "textarea[id='text']")
    WebElement insertion;

    @FindBy(css = "div:nth-child(4)>div>button")
    WebElement next_step_button;

    @FindBy(css = "span:nth-child(4)>button")
    WebElement destination_search_save_button_disable;

    @FindBy(css = "div:nth-child(2) > div > div > div > div > span.ant-select-selection-item")
    WebElement destination_search_template_dropdown;

    @FindBy(css = "div > button[type]:nth-child(2)")
    WebElement destination_search_delete_template_button;

    @FindBy(css = "div>button[type]:nth-child(4)")
    WebElement destination_search_save_button_enable;

    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(2)")
    WebElement destination_search_ok_button;

    @FindBy(css = "div.ant-upload.ant-upload-drag > span > div > p:nth-child(5)")
    WebElement upload_descriptions;

    @FindBy(css = "button.ant-btn-danger")
    WebElement delete_mail_button;

    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(2)")
    WebElement delete_mail_ok_button;

    @FindBy(css = "div:nth-child(3) > div > div:nth-child(1) > button")
    WebElement destination_search_cancel_button;

    @FindBy(css = "div:nth-child(3) > div > div > div > button:nth-child(2)")
    WebElement comment_template_button;

    @FindBy(css = "span.ant-divider-inner-text")
    WebElement comment_template_information;

    // Delivery information
    // Matter
    @FindBy(css = "#searchtype > label > span > input")
    List<WebElement> delivery_type;

    @FindBy(css = "label>span>input[id^='wants_location']")
    List<WebElement> delivery_area;

    @FindBy(css = "#jobtype > label > span:nth-child(1)")
    List<WebElement> matter_delivery_occupation;

    @FindBy(css = "#jobtype_dev > label > span > input")
    List<WebElement> matter_detail_of_delivery_occupation_dev;

    @FindBy(css = "label>span>input[id^='jobskill_dev']")
    List<WebElement> matter_delivery_skill_details_dev;

    @FindBy(css = "#jobtype_infra > label > span > input")
    List<WebElement> matter_detail_of_delivery_occupation_infra;

    @FindBy(css = "label>span>input[id^='jobskill_infra']")
    List<WebElement> matter_delivery_skill_details_infra;

    @FindBy(css = "#jobtype_other > label > span > input")
    List<WebElement> matter_detail_of_delivery_occupation_others;

    @FindBy(css = "#job_syouryu > label > span.ant-radio > input")
    List<WebElement> matter_delivery_commercial_distribution;

    // Personnel
    @FindBy(css = "#personneltype_dev")
    WebElement personnel_delivery_occupation_dev;

    @FindBy(css = "div.ant-row.ant-row-start>label>span>input[id^='personneltype_dev']")
    List<WebElement> personnel_detail_of_delivery_occupation_dev;

    @FindBy(css = "label>span>input[id^='personnelskill_dev']")
    List<WebElement> personnel_delivery_skill_details_dev;

    @FindBy(css = "#personneltype_infra")
    WebElement personnel_delivery_occupation_infra;

    @FindBy(css = "div.ant-form-item-control-input-content>label>span>input[id^='personneltype_infra']")
    List<WebElement> personnel_detail_of_delivery_occupation_infra;

    @FindBy(css = "label>span>input[id^='personnelskill_infra']")
    List<WebElement> personnel_delivery_skill_details_infra;

    @FindBy(css = "#personneltype_other")
    WebElement personnel_delivery_occupation_others;

    @FindBy(css = "div.ant-form-item-control-input-content>label>span>input[id^='personneltype_other']")
    List<WebElement> personnel_detail_of_delivery_occupation_others;

    @FindBy(css = "#job_koyou > label > span > input")
    List<WebElement> personnel_delivery_employment_form;

    @FindBy(css = "#personnel_syouryu > label > span > input")
    List<WebElement> personnel_delivery_commercial_distribution;

    @FindBy(css = "div:nth-child(2) > div.ant-row-start > button")
    WebElement reset_search_criteria_button;

    @FindBy(css = "div:nth-child(6) > div > div > div:nth-child(1) > div > button")
    WebElement search_button;

    @FindBy(css = "li.ant-pagination-item-active")
    WebElement current_page;

    @FindBy(css = "div.ant-table-selection>label>span>input")
    WebElement select_all_contact;

    @FindBy(css = "table.ant-picker-content>tbody>tr>td")
    List<WebElement> calendar;

    @FindBy(css = "span.ant-picker-next-icon")
    WebElement next_month;

    @FindBy(css = "div.ant-col.ant-col-24>div>div:nth-child(4)>div>button")
    WebElement time_selection;
    
    @FindBy(css = "span.ant-modal-close-x")
    WebElement close_time_selection;

    @FindBy(css = "div:nth-child(1) > div > div > div:nth-child(2) > div > div > input")
    WebElement time;

    @FindBy(css = "div.ant-col>div:nth-child(1)>div>div>div:nth-child(1)>div>div>input")
    WebElement date;

    @FindBy(css = "ul.ant-picker-time-panel-column:nth-child(2)>li")
    List<WebElement> minute_list;

    public int current_id;

    public addons_add_remove_check(WebDriver driver, String domain, String username_admin_page, String password_admin_page) {
        super(driver, domain, username_admin_page, password_admin_page);
        PageFactory.initElements(driver, this);
    }

    public Boolean check_template_in_stock(WebElement save_button_disable) {
        // Check save button is enable
        // true: in stock
        // false: out of stock
        boolean check = false;
        try {
            save_button_disable.getAttribute("class");
        } catch (NoSuchElementException ex) {
            check = true;
        }
        return check;
    }

    public void delete_search_template(WebElement template_dropdown, WebElement delete_template_button, WebElement ok_button) {
        // Select template dropdown list
        wait.until(ExpectedConditions.elementToBeClickable(template_dropdown)).click();

        // Select template
        key.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();

        // Click delete icon
        wait.until(ExpectedConditions.elementToBeClickable(delete_template_button)).click();

        //Click OK button
        wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

        wait.until(ExpectedConditions.visibilityOf(message));
    }

    /**
     * @param path: partner/contact/mail list path
     * @return
     * <p> 0: number_of_registered
     * <p> 1: number_of_search_template
     */
    public List<Integer> get_number_of_search_template_list_page(String path) throws InterruptedException {
        // link to list page
        driver.get(domain + path);

        // wait and click refined search button
        wait.until(ExpectedConditions.visibilityOf(refined_search_button));
        refined_search_button.click();
        sleep(2000);

        if (!check_template_in_stock(save_button_disable)) {
            delete_search_template(search_template_dropdown, delete_template_button, ok_button);
        }

        // get information
        sleep(2000);
        save_button_enable.click();
        wait.until(ExpectedConditions.visibilityOf(search_template_information));
        System.out.println(search_template_information.getText());
        int number_of_registered = Integer.parseInt(search_template_information.getText().split("件 ／")[0].replace("現在登録数: ", ""));
        int number_of_search_template = Integer.parseInt(search_template_information.getText().split("件 ／")[1].replace("件 ", ""));
        return List.of(number_of_registered, number_of_search_template);
    }

    public void link_to_attachment_step() throws InterruptedException {
        // link to create delivered mail page
        driver.get(domain + mail_list_path + register_path);

        // wait distributor loading
        sleep(3000);

        // input subject
        subject.sendKeys("addons test");

        // input insertion
        insertion.sendKeys("addons test");

        // next to attachment step
        next_step_button.click();

        // wait attachment step loading
        sleep(2000);
    }

    public void link_to_destination_search() throws InterruptedException {
        // link to attachment step
        link_to_attachment_step();

        // next to destination selection step
        next_step_button.click();

        // wait destination selection step
        sleep(2000);
    }

    /**
     *
     * @return
     * <p>Destination search</p>
     * <p>0: number of registered.</p>
     * <p>1: number of search template</p>
     */
    public List<Integer> get_number_of_search_template_destination_search() throws InterruptedException {
        link_to_destination_search();
        if (!check_template_in_stock(destination_search_save_button_disable)) {
            delete_search_template(destination_search_template_dropdown, destination_search_delete_template_button, destination_search_ok_button);
        }

        // click save button
        destination_search_save_button_enable.click();
        wait.until(ExpectedConditions.visibilityOf(search_template_information));
        System.out.println(search_template_information.getText());
        int number_of_registered = Integer.parseInt(search_template_information.getText().split("件 ／")[0].replace("現在登録数: ", ""));
        int number_of_search_template = Integer.parseInt(search_template_information.getText().split("件 ／")[1].replace("件 ", ""));
        destination_search_cancel_button.click();
        delete_mail_after_get_information();
        return List.of(number_of_registered, number_of_search_template);
    }

    /**
     *
     * @return
     * <p>Destination search</p>
     * <p>0: number of used deliveries.</p>
     * <p>1: number of deliveries.</p>
     */
    public List<Integer> get_number_of_deliveries_destination_search() throws InterruptedException {
        link_to_destination_search();

        // click save button
        wait.until(ExpectedConditions.visibilityOf(deliveries_information));
        System.out.println(deliveries_information.getText());

        int number_of_used_deliveries = Integer.parseInt(deliveries_information.getText().split("件 ／")[0].replace("配信数: ", ""));
        int number_of_deliveries = Integer.parseInt(deliveries_information.getText().split("件 ／")[1].replace("件 ", "").replace(",", ""));
        delete_mail_after_get_information();
        return List.of(number_of_used_deliveries, number_of_deliveries);
    }

    /**
     *
     * @param path: register partner/contact path
     * @return
     * <p>0: number of registered.</p>
     * <p>1: number of comment template.</p>
     */
    public List<Integer> get_number_of_comment_template(String path) throws InterruptedException {
        driver.get(domain + path);
        sleep(2000);
        comment_template_button.click();
        wait.until(ExpectedConditions.visibilityOf(comment_template_information));
        System.out.println(comment_template_information.getText());

        int number_of_registered = Integer.parseInt(comment_template_information.getText().split("件 ／")[0].replace(" ", ""));
        int number_of_comment_template = Integer.parseInt(comment_template_information.getText().split("件 ／")[1].replace("件", "").replace(" ", ""));
        return List.of(number_of_registered, number_of_comment_template);
    }

    /**
     *
     * @return
     * <p>true: addons has been added.</p>
     * <p>false: addons has been removed.</p>
     */
    public Boolean check_delivery_attachment_capacity() throws InterruptedException {
        link_to_attachment_step();
        return upload_descriptions.getText().equals("※1通のメールに添付できる容量の上限はヘッダー情報を含め10MBです。");
    }

    public void delete_mail_after_get_information() {
        // scroll to bottom
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Click delete button
        delete_mail_button.click();

        // Click OK button
        wait.until(ExpectedConditions.elementToBeClickable(delete_mail_ok_button)).click();
    }

    //** Delivery information **//
    public void delivery_information() {
        // Reset search criteria
        if ((delivery_type.get(0).isSelected()) || (delivery_type.get(1).isSelected()) || (delivery_type.get(2).isSelected())) {
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

    public void search_contact_by_condition() throws InterruptedException {
        // Search
        key.moveToElement(search_button).click().build().perform();

        // Waiting for loading result
        sleep(1000);
    }

    public Boolean check_result_is_null() {
        // check null = "True" => No partner PIC
        boolean check_null = false;
        try {
            current_page.click();
        } catch (NoSuchElementException ex) {
            check_null = true;
        }
        return check_null;
    }

    public void select_contact() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        while (check_result_is_null()) {
            System.out.println("Can not find partner PIC with this condition.");
            System.out.println("Please add more data or change your search condition.");
            System.out.println("Search condition have been changed and try again, please wait ...");
            System.out.println("-----------------------------------------------------------------");
            delivery_information();
            search_contact_by_condition();
        }

        // Select contact
        // Scroll to bottom
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // select all contact
        select_all_contact.click();
    }

    public Boolean check_date(int id) {
        return calendar.get(id).getAttribute("class").contains("disable");
    }

    public int date_id() {
        int id = 0;
        while (check_date(id)) {
            id++;
            // Calendar have 42 days (id: 0->41)
            if (id > 41) {
                id = 0;
                next_month.click();
            }
        }
        return id;
    }

    public void open_delivery_time_setting_popup() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        // Click Time selection button
        sleep(3000);
        time_selection.click();
    }

    public void select_date() {
        // Date
        wait.until(ExpectedConditions.visibilityOf(date)).click();
        current_id = date_id();
        wait.until(ExpectedConditions.elementToBeClickable(calendar.get(current_id))).click();
    }

    /**
     *
     * @return
     * <p>true: addons has been added.</>
     * <p>false: addons has ben removed.</p>
     */
    public Boolean check_shortening_the_delivery_interval() throws InterruptedException {
        // link to destination search
        link_to_destination_search();

        // search contact condition
        delivery_information();

        // search contact by condition
        search_contact_by_condition();

        // select contact
        select_contact();

        // next to final confirmation step
        next_step_button.click();

        // wait final confirmation step loading
        sleep(2000);

        // open delivery time setting popup
        open_delivery_time_setting_popup();

        // select date
        select_date();

        // open time
        sleep(1000);
        time.click();

        // get status time (true: item enable, false: item disable)
        boolean check = !minute_list.get(5).getAttribute("class").contains("disabled");

        // close time selection popup
        close_time_selection.click();

        // wait popup close
        sleep(1000);

        // delete mail after get information
        delete_mail_after_get_information();

        return check;
    }
}