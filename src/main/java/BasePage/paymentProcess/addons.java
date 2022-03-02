package BasePage.paymentProcess;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static BasePage.Link_and_Path.HBS.addons_path;
import static java.lang.Thread.sleep;


public class addons extends payment {
    @FindBy(css = "span.ant-tag-green")
    List<WebElement> list_addons_price;
    @FindBy(css = "div.ant-row-center>div>span.ant-tag")
    List<WebElement> list_addons_information;
    @FindBy(css = "li:nth-child(2) > span > div > div:nth-child(1) > button")
    List<WebElement> list_add_button;
    @FindBy(css = "li:nth-child(2) > span > div > div:nth-child(2) > button")
    List<WebElement> list_remove_button;
    @FindBy(css = "div.ant-modal-confirm-btns > button")
    List<WebElement> select_option_add;
    @FindBy(css = "div.ant-col.ant-col-24 > div > div > button")
    List<WebElement> select_option_remove;
    @FindBy(css = "div.ant-message-custom-content>span:nth-child(2)")
    WebElement message;
    @FindBy(css = "span.ant-checkbox>input")
    List<WebElement> agree_to_immediate_cancellation;
    WebDriverWait wait;

    public static List<String> addons_name = List.of(
            "Recommendation - Acquisition of delivery opening information",
            "Recommendation - Maximum number of deliveries",
            "Recommendation - Shortening the delivery interval",
            "Account management - Number of search condition template registrations",
            "Account management - Number of comment template registrations",
            "Delivery mail management - Number of search condition template registrations",
            "Delivery mail management - Acquisition of delivery opening information",
            "Delivery mail management - Extension of acquisition period of delivery opening information",
            "Delivery mail management - Delivery attachment capacity",
            "Delivery mail management - Advertising display during HTML distribution",
            "Delivery mail management - Maximum number of deliveries",
            "Delivery mail management - Shortening the delivery interval");

    public addons(WebDriver driver, String domain, String username_admin_page, String password_admin_page) {
        super(driver, domain, username_admin_page, password_admin_page);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * @param element: addons price element
     * @return price of Addons
     */
    public String get_add_ons_price(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText().replace("¥", "").replace(" (税抜) /月", "");
    }

    /**
     * @param element: (number of available/ number of purchases) element
     * @return 0: number_of_available_for_purchase, 1: number_of_purchases
     */
    public List<Integer> get_addons_information(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String number_of_available_for_purchase = element.getText().split(" /")[0];
        String number_of_purchases = element.getText().split("/ ")[1];
        return List.of(Integer.parseInt(number_of_available_for_purchase), Integer.parseInt(number_of_purchases));
    }

    /**
     * @param element: button element
     * @return true: button is not getting disable
     */
    public Boolean button_should_be_disable(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isEnabled();
    }

    /**
     * @param addons_id value in range 0-11
     * <p0: Recommendation - Acquisition of delivery opening information
     * <p1: Recommendation - Maximum number of deliveries
     * <p2: Recommendation - Shortening the delivery interval
     * <p3: Account management - Number of search condition template registrations
     * <p4: Account management - Number of comment template registrations
     * <p5: Delivery mail management - Number of search condition template registrations
     * <p6: Delivery mail management - Acquisition of delivery opening information
     * <p7: Delivery mail management - Extension of acquisition period of delivery opening information
     * <p8: Delivery mail management - Delivery attachment capacity
     * <p9: Delivery mail management - Advertising display during HTML distribution
     * <p10: Delivery mail management - Maximum number of deliveries
     * <p11: Delivery mail management - Shortening the delivery interval
     * <pRelated: {0,6}, {1,10}, {2,11}, {3,5}
     * <pRelated parent-child: {0,7}, {6,7}
     **/
    public void Add(int addons_id) throws InterruptedException {
        // Waiting for loading page
        sleep(2000);

        // get Addons information
        List<Integer> addons_info = get_addons_information(list_addons_information.get(addons_id));

        int addons_add_remove_id = get_add_remove_id(addons_id);
        System.out.println("addons_add_remove_id: " + addons_add_remove_id);

        boolean check;
        List<Integer> add_on_info;
        if ((addons_id == 0) || (addons_id == 6)) {
            add_on_info = get_addons_information(list_addons_information.get(7));
            // addons no.7 has not been added => can add/remove addons no.0 and no.6
            check = add_on_info.get(0).equals(add_on_info.get(1));
        } else if (addons_id == 7) {
            add_on_info = get_addons_information(list_addons_information.get(0));
            // addons no.0 or no.6 has been added => can add/remove addons no.7
            check = !add_on_info.get(0).equals(add_on_info.get(1));
        } else {
            // remain addons => can add/remove
            check = true;
        }

        if (check) {
            // number of available purchase = 0 => disable Add button
            if (addons_info.get(0) == 0) {
                soft.assertFalse(button_should_be_disable(list_add_button.get(addons_add_remove_id)),
                        "[" + addons_name.get(addons_id) + "]" + "[Failed] Add button is not getting disable.");
            } else {
                // number of available purchase = number of purchases => disable Remove button
                if (addons_info.get(0).equals(addons_info.get(1))) {
                    soft.assertFalse(button_should_be_disable(list_remove_button.get(addons_add_remove_id)),
                            "[" + addons_name.get(addons_id) + "]" + "[Failed] Remove button is not getting disable.");
                }

//            // get company_id
//            System.out.println("Your company id: " + get_company_id());

                // get old receipt id
                String current_payment_history_receipt_id = get_payment_history_receipt().get(0);

//            // login to PAY.JP and get old receipt_id
//            login_to_PayJP_page();
//            String current_PayJP_receipt_id = get_PayJP_receipt().get(0);

                // Back to Addons page
                driver.get(domain + addons_path);

                sleep(2000);

                // get Addons price
                int price_id = get_price_id(addons_id);
                System.out.println("price_id: " + price_id);
                long price = add_tax(get_add_ons_price(list_addons_price.get(price_id)));
                System.out.println(price);

//            sleep(2000);

                // wait and click add button
                wait.until(ExpectedConditions.visibilityOf(list_add_button.get(addons_add_remove_id)));
                list_add_button.get(addons_add_remove_id).click();

                // wait and click OK button
                wait.until(ExpectedConditions.visibilityOf(select_option_add.get(1)));
                select_option_add.get(1).click();

                // wait message
                wait.until(ExpectedConditions.visibilityOf(message));

                // get message
                String text = message.getText();
                wait.until(ExpectedConditions.invisibilityOf(message));

                // reload page
                driver.navigate().refresh();
                sleep(2000);

                // get current number of available for purchase
                int current_number_of_available_for_purchase = get_addons_information(list_addons_information.get(addons_id)).get(0);

                // [Payment history] Get receipt id and price
                List<String> receipt_history_payment = get_payment_history_receipt();
                System.out.println(receipt_history_payment);

//            // [PAY.JP] Get receipt id and price
//            List<String> receipt_PayJP = get_PayJP_receipt();

                // check message
                soft.assertEquals(text, "アドオンの購入に成功しました",
                        "[" + addons_name.get(addons_id) + "]" + "[Failed] Message do not match.");

                // check number of available for purchase
                soft.assertEquals(current_number_of_available_for_purchase, addons_info.get(0) - 1,
                        "[" + addons_name.get(addons_id) + "]" + "[Failed] Current number of available for purchase incorrect.");

                // [Payment history] check receipt id and price
                soft.assertNotEquals(receipt_history_payment.get(0), current_payment_history_receipt_id, "[" + addons_name.get(addons_id) + "]" + "[Failed][Payment history] Can not find new receipt ID => New payment history has not been added yet.");
                soft.assertEquals(Long.parseLong(receipt_history_payment.get(1)), price, "[" + addons_name.get(addons_id) + "]" + "[Failed][Payment history] Price not match.");
                // [PayJP] check receipt id and price
//            soft.assertNotEquals(receipt_PayJP.get(0), current_PayJP_receipt_id, "[Failed][PayJP] Can not find new receipt ID => New payment history has not been added yet.");
//            soft.assertEquals(Long.parseLong(receipt_PayJP.get(1)), price,  "[Failed][PayJP] Price not match.");
            }
        }
        // show all assert result
        soft.assertAll();
    }

    /**
     * @param addons_id value in range 0-11
     * <p0: Recommendation - Acquisition of delivery opening information
     * <p1: Recommendation - Maximum number of deliveries
     * <p2: Recommendation - Shortening the delivery interval
     * <p3: Account management - Number of search condition template registrations
     * <p4: Account management - Number of comment template registrations
     * <p5: Delivery mail management - Number of search condition template registrations
     * <p6: Delivery mail management - Acquisition of delivery opening information
     * <p7: Delivery mail management - Extension of acquisition period of delivery opening information
     * <p8: Delivery mail management - Delivery attachment capacity
     * <p9: Delivery mail management - Advertising display during HTML distribution
     * <p10: Delivery mail management - Maximum number of deliveries
     * <p11: Delivery mail management - Shortening the delivery interval
     * <pRelated: {0,6}, {1,10}, {2,11}, {3,5}
     * <pRelated parent-child: {0,7}, {6,7}
     **/
    public void Remove(int addons_id) throws InterruptedException {
        // Waiting for loading page
        sleep(2000);

        // get Addons information
        List<Integer> addons_info = get_addons_information(list_addons_information.get(addons_id));

        int addons_add_remove_id = get_add_remove_id(addons_id);
        System.out.println("addons_add_remove_id :" + addons_add_remove_id);

        boolean check;
        List<Integer> add_on_info;
        if ((addons_id == 0) || (addons_id == 6)) {
            add_on_info = get_addons_information(list_addons_information.get(7));
            // addons no.7 has not been added => can add/remove addons no.0 and no.6
            check = add_on_info.get(0).equals(add_on_info.get(1));
        } else if (addons_id == 7) {
            add_on_info = get_addons_information(list_addons_information.get(0));
            // addons no.0 or no.6 has been added => can add/remove addons no.7
            check = !add_on_info.get(0).equals(add_on_info.get(1));
        } else {
            // remain addons => can add/remove
            check = true;
        }

        if (check) {
            // number of available purchase = number of purchases => disable Remove button
            if (addons_info.get(0).equals(addons_info.get(1))) {
                soft.assertFalse(button_should_be_disable(list_remove_button.get(addons_add_remove_id)),
                        "[" + addons_name.get(addons_id) + "]" + "[Failed] Remove button is not getting disable.");
            } else {
                // number of available purchase = 0 => disable Add button
                if (addons_info.get(0) == 0) {
                    soft.assertFalse(button_should_be_disable(list_add_button.get(addons_add_remove_id)),
                            "[" + addons_name.get(addons_id) + "]" + "[Failed] Add button is not getting disable.");
                }

                // get old receipt id
                String current_payment_history_receipt_id = get_payment_history_receipt().get(0);

                // Back to Addons page
                driver.get(domain + addons_path);
                sleep(2000);


                // wait and click remove button
                wait.until(ExpectedConditions.visibilityOf(list_remove_button.get(addons_add_remove_id)));
                list_remove_button.get(addons_add_remove_id).click();

                wait.until(ExpectedConditions.visibilityOf(select_option_remove.get(1)));

                // Agree to immediate cancellation
                for (WebElement agree : agree_to_immediate_cancellation) {
                    try {
                        agree.click();
                    } catch (ElementNotInteractableException ex) {
                        // Skip and click next Element
                    }
                }

                // wait and click OK button
                select_option_remove.get(1).click();

                // wait message
                wait.until(ExpectedConditions.visibilityOf(message));

                // get message
                String text = message.getText();
                wait.until(ExpectedConditions.invisibilityOf(message));

                // reload page
                driver.navigate().refresh();
                sleep(2000);

                // get current number of available for purchase
                int current_number_of_available_for_purchase = get_addons_information(list_addons_information.get(addons_id)).get(0);

                // [Payment history] Get receipt id and price
                String receipt_history_payment = get_payment_history_receipt().get(0);

                // check message
                soft.assertEquals(text, "アドオンの解約に成功しました", "[" + addons_name.get(addons_id) + "]" + "[Failed] Message do not match.");

                // check number of available for purchase
                soft.assertEquals(current_number_of_available_for_purchase, addons_info.get(0) + 1,
                        "[" + addons_name.get(addons_id) + "]" + "[Failed] Current number of available for purchase incorrect.");

                // [Payment history] check receipt id
                soft.assertEquals(receipt_history_payment, current_payment_history_receipt_id, "[" + addons_name.get(addons_id) + "]" + "[Failed][Payment history] New receipt id has been found. Remove Addons is no fee.");
            }
        }
        // show all assert result
        soft.assertAll();
    }

    /**
     * <p>Related: {0,6}, {1,10}, {2,11}, {3,5}
     */
    public void check_addons_related(int addons_id1, int addons_id2) throws InterruptedException {
        // Back to Addons page
        driver.get(domain + addons_path);
        sleep(2000);


        // get number of purchases/available purchase
        List<Integer> addons1_information = get_addons_information(list_addons_information.get(addons_id1));
        List<Integer> addons2_information = get_addons_information(list_addons_information.get(addons_id2));

        // Compare
        // number of purchases/available purchase
        soft.assertEquals(addons1_information, addons2_information, addons_name.get(addons_id1) + "[Failed][Number of purchases/available purchase] Number incorrect.");

        boolean check;
        List<Integer> add_on_info;
        if ((addons_id1 == 0) || (addons_id1 == 6)) {
            add_on_info = get_addons_information(list_addons_information.get(7));
            // addons no.7 has not been added => can add/remove addons no.0 and no.6
            check = add_on_info.get(0).equals(add_on_info.get(1));
        } else if (addons_id1 == 7) {
            add_on_info = get_addons_information(list_addons_information.get(0));
            // addons no.0 or no.6 has been added => can add/remove addons no.7
            check = !add_on_info.get(0).equals(add_on_info.get(1));
        } else {
            // remain addons => can add/remove
            check = true;
        }

        if (check) {
            int id1 = get_add_remove_id(addons_id1);
            int id2 = get_add_remove_id(addons_id2);

            // get button status (enable/disable)
            boolean addons1_add_status = list_add_button.get(id1).isEnabled();
            boolean addons1_remove_status = list_remove_button.get(id1).isEnabled();
            boolean addons2_add_status = list_add_button.get(id2).isEnabled();
            boolean addons2_remove_status = list_remove_button.get(id2).isEnabled();

            // button status
            soft.assertEquals(addons1_add_status, addons2_add_status, addons_name.get(addons_id1) + "[Failed][Add button] Button status incorrect.");
            soft.assertEquals(addons1_remove_status, addons2_remove_status, addons_name.get(addons_id1) + "[Failed][Remove button] Button status incorrect.");
        }
        // show all assert result
        soft.assertAll();
    }

    public int get_add_remove_id(int addons_id) {
        int addons_add_remove_id = addons_id;
        List<Integer> addon_info_0 = get_addons_information(list_addons_information.get(0));
        List<Integer> addon_info_7 = get_addons_information(list_addons_information.get(7));
        // if addons no.7 has been added, addons no.0 and no.6 should be disabled
        // addons_id < 6, remove/add id - 1 (addons no.0 has been removed from list)
        // addons_id > 6, remove/add id - 2 (addons no.0 and no.6 have been removed from list)
        if (!addon_info_7.get(0).equals(addon_info_7.get(1))) {
            if ((addons_id > 0) && (addons_id < 6)) {
                addons_add_remove_id = addons_id - 1;
            } else {
                addons_add_remove_id = addons_id - 2;
            }
        }
        // in case, addons no.0 or no.5 have not been added, addons no.7 should be disabled
        // addons_id > 7, remove/add id - 1 (addons no.7 has been removed from list)
        else if (addon_info_0.get(0).equals(addon_info_0.get(1))) {
            if (addons_id > 7) {
                addons_add_remove_id = addons_id - 1;
            }
        }
        return addons_add_remove_id;
    }

    public int get_price_id(int addons_id) {
        int id = addons_id;
        for (int i = 0; i < addons_id; i++) {
            if (get_addons_information(list_addons_information.get(i)).get(0) == 0) {
                id = id - 1;
            }
        }
        return id;
    }
}
