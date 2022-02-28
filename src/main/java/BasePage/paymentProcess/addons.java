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

    public addons(WebDriver driver, String domain, String username_admin_page, String password_admin_page) {
        super(driver, domain, username_admin_page, password_admin_page);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * @param element: WebElement
     * @return price of Addons
     */
    public String get_add_ons_price(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText().replace("¥", "").replace(" (税抜) /月", "");
    }

    /**
     * @param element: WebElement
     * @return 0: number_of_available_for_purchase, 1: total_purchase_times
     */
    public List<Integer> get_addons_information(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String number_of_available_for_purchase = element.getText().split(" /")[0];
        String total_purchase_times = element.getText().split("/ ")[1];
        return List.of(Integer.parseInt(number_of_available_for_purchase), Integer.parseInt(total_purchase_times));
    }

    /**
     * @param element: WebElement
     * @return true: button is not getting disable
     */
    public Boolean button_should_be_disable(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isEnabled();
    }

    public void acquisition_of_delivery_opening_information_Add() throws InterruptedException {
        // get company_id
        System.out.println("Your company id: " + get_company_id());

        // get old receipt id
        String current_payment_history_receipt_id = get_payment_history_receipt().get(0);
        System.out.println("History payment receipt id: " + current_payment_history_receipt_id);

        // login to PAY.JP and get old receipt_id
        login_to_PayJP_page();
        String current_PayJP_receipt_id = get_PayJP_receipt().get(0);
        System.out.println("History PAYJP receipt id: " + current_PayJP_receipt_id);

        // Back to Addons page
        driver.get(domain + addons_path);

        sleep(2000);

        // get Addons information
        List<Integer> addons_info = get_addons_information(list_addons_information.get(0));
        System.out.println("Number of available for purchase: " + addons_info.get(0));
        System.out.println("Total purchase times: " + addons_info.get(1));

        // number of available purchase = 0 => disable Add button
        if (addons_info.get(0) == 0) {
            soft.assertFalse(button_should_be_disable(list_add_button.get(0)),
                    "[Acquisition of delivery opening information][Failed] Add button is not getting disable.");
        }
        else {
            // number of available purchase = total purchase times => disable Remove button
            if (addons_info.get(0).equals(addons_info.get(1))) {
                soft.assertFalse(button_should_be_disable(list_remove_button.get(0)),
                        "[Acquisition of delivery opening information][Failed] Remove button is not getting disable.");
            }

            // get Addons price
            long price = add_tax(get_add_ons_price(list_addons_price.get(0)));

//            sleep(2000);

            // wait and click add button
            wait.until(ExpectedConditions.visibilityOf(list_add_button.get(0)));
            list_add_button.get(0).click();

            // wait and click OK button
            wait.until(ExpectedConditions.visibilityOf(select_option_add.get(1)));
            select_option_add.get(1).click();

            // wait message
            wait.until(ExpectedConditions.visibilityOf(message));

            // get message
            String text = message.getText();
            wait.until(ExpectedConditions.invisibilityOf(message));

            // get current number of available for purchase
            int current_number_of_available_for_purchase = get_addons_information(list_addons_information.get(0)).get(0);
            System.out.println("New number of available for purchase: " + current_number_of_available_for_purchase);

            // [Payment history] Get receipt id and price
            List<String> receipt_history_payment = get_payment_history_receipt();
            System.out.println("New history payment receipt id: " + receipt_history_payment.get(0));
            System.out.println("New history payment price: " + receipt_history_payment.get(1));

            // [PAY.JP] Get receipt id and price
            List<String> receipt_PayJP = get_PayJP_receipt();
            System.out.println("New PAYJP receipt id: " + receipt_PayJP.get(0));
            System.out.println("New PAYJP price: " + receipt_PayJP.get(1));

            // check message
            soft.assertEquals(text, "アドオンの購入に成功しました",
                    "[Acquisition of delivery opening information][Failed] Message do not match.");

            // check number of available for purchase
            soft.assertEquals(current_number_of_available_for_purchase, addons_info.get(0) - 1,
                    "[Acquisition of delivery opening information][Failed] Current number of available for purchase incorrect.");

            // [Payment history] check receipt id and price
            soft.assertEquals(receipt_history_payment.get(0), current_payment_history_receipt_id, "[Failed][Payment history] Can not find new receipt ID => New payment history has not been added yet.");
            soft.assertEquals(price, Long.parseLong(receipt_history_payment.get(1)), "[Failed][Payment history] Price not match.");
            // [PayJP] check receipt id and price
            soft.assertNotEquals(receipt_PayJP.get(0), current_PayJP_receipt_id, "[Failed][PayJP] Can not find new receipt ID => New payment history has not been added yet.");
            soft.assertEquals(price, Long.parseLong(receipt_PayJP.get(1)), "[Failed][PayJP] Price not match.");
        }
        // show all assert result
        soft.assertAll();
    }

    public void acquisition_of_delivery_opening_information_Remove() throws InterruptedException {
        // get old receipt id
        String current_payment_history_receipt_id = get_payment_history_receipt().get(0);
        System.out.println("History payment receipt id: " + current_payment_history_receipt_id);

        // Back to Addons page
        driver.get(domain + addons_path);

        sleep(2000);

        // get Addons information
        List<Integer> addons_info = get_addons_information(list_addons_information.get(0));
        System.out.println("Number of available for purchase: " + addons_info.get(0));
        System.out.println("Total purchase times: " + addons_info.get(1));
        // number of available purchase = total purchase times => disable Remove button
        if (addons_info.get(0).equals(addons_info.get(1))) {
            soft.assertFalse(button_should_be_disable(list_remove_button.get(0)),
                    "[Acquisition of delivery opening information][Failed] Remove button is not getting disable.");
        }
        else {
            // number of available purchase = 0 => disable Add button
            if (addons_info.get(0) == 0) {
                soft.assertFalse(button_should_be_disable(list_add_button.get(0)),
                        "[Acquisition of delivery opening information][Failed] Add button is not getting disable.");
            }

            // wait and click remove button
            wait.until(ExpectedConditions.visibilityOf(list_remove_button.get(0)));
            list_remove_button.get(0).click();

            // Agree to immediate cancellation
            for (WebElement agree:agree_to_immediate_cancellation) {
                try {
                    agree.click();
                }
                catch (ElementNotInteractableException ex){
                    // Nothing
                }
            }

            // wait and click OK button
            wait.until(ExpectedConditions.visibilityOf(select_option_remove.get(1)));
            select_option_remove.get(1).click();

            // wait message
            wait.until(ExpectedConditions.visibilityOf(message));

            // get message
            String text = message.getText();
            wait.until(ExpectedConditions.invisibilityOf(message));

            // get current number of available for purchase
            int current_number_of_available_for_purchase = get_addons_information(list_addons_information.get(0)).get(0);
            System.out.println("New number of available for purchase: " + current_number_of_available_for_purchase);

            // [Payment history] Get receipt id and price
            String receipt_history_payment = get_payment_history_receipt().get(0);
            System.out.println("New history payment receipt id: " + receipt_history_payment);

            // check message
            soft.assertEquals(text, "アドオンの解約に成功しました",
                    "[Acquisition of delivery opening information][Failed] Message do not match.");

            // check number of available for purchase
            soft.assertEquals(current_number_of_available_for_purchase, addons_info.get(0) + 1,
                    "[Acquisition of delivery opening information][Failed] Current number of available for purchase incorrect.");

            // [Payment history] check receipt id
            soft.assertEquals(receipt_history_payment, current_payment_history_receipt_id, "[Failed][Payment history] New receipt id has been found. Remove Addons is no fee.");
        }
        // show all assert result
        soft.assertAll();
    }
}
