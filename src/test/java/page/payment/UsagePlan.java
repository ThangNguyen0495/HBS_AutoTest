package page.payment;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static utilities.links.HBS.USAGE_PLAN_PATH;
import static java.lang.Thread.sleep;

@SuppressWarnings("ALL")
public class UsagePlan extends Payment {

    @FindBy(css = "div>div.ant-col-20>button")
    List<WebElement> plan_list;

    @FindBy(css = "div.ant-col:nth-child(1)>button")
    WebElement add_upper_limit_button;

    @FindBy(css = "div.ant-col:nth-child(2)>button")
    WebElement remove_upper_limit_button;

    @FindBy(css = "div.ant-col:nth-child(2)>span>button")
    WebElement remove_upper_limit_button_disable;

    @FindBy(css = "div.ant-modal-confirm-btns > button")
    List<WebElement> select_option;

    @FindBy(css = "div.ant-message-custom-content>span:nth-child(2)")
    WebElement message;

    @FindBy(css = "span.PlanPage-ppPlanCardPriceMain-y2rKo")
    List<WebElement> plan_price_list;

    @FindBy(css = "div.PlanPage-ppPlanCardDescription-d2Zde > p:nth-child(3)")
    List<WebElement> price_upper_limit;

    @FindBy(css = "div.PlanPage-ppStatisticContent-jYsFp > span > span:nth-child(2)")
    WebElement number_of_users;

    @FindBy(css = "span.PlanPage-ppStatisticContentSubjunction-Q1HlJ")
    WebElement additional_purchase;

    public UsagePlan(WebDriver driver, String domain, String username_admin_page, String password_admin_page) {
        super(driver, domain, username_admin_page, password_admin_page);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

//    public usagePlan(WebDriver driver, Actions key, String domain, String username_admin_page, String password_admin_page) {
//        super(driver, key, domain, username_admin_page, password_admin_page);
//    }

    public boolean checkPlan() throws InterruptedException {
        driver.get(domain + USAGE_PLAN_PATH);
        sleep(1000);
        return plan_list.get(0).isEnabled();
    }

    public void buyPlan() throws InterruptedException {
        if (checkPlan()) {
            // get company_id
            System.out.println("Your company id: " + getCompanyID());

            // get old receipt_history_payment id
            String current_payment_history_receipt_id = get_payment_history_receipt().get(0);

            // login_to_Komorebi to PAY.JP and get old receipt_id
            loginToPayJP();
            String current_PayJP_receipt_id = getPayJpReceipt().get(0);

            // Back to usage plan page
            driver.get(domain + USAGE_PLAN_PATH);

            // Add 10% tax
            long price_and_tax = add_tax(plan_price_list.get(0).getText());

            // Click buy this plan button
            plan_list.get(0).click();

            // Wait and click ok button
            wait.until(ExpectedConditions.elementToBeClickable(select_option.get(1))).click();

            // Waiting for loading
            String message_text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
            wait.until(ExpectedConditions.invisibilityOf(message));

            // [page.Payment history] Get receipt id and price
            List<String> receipt_history_payment = get_payment_history_receipt();

            // [PAY.JP] Get receipt id and price
            List<String> receipt_PayJP = getPayJpReceipt();

            // Verify that usage plan should be unlocked
            // check message
            soft.assertEquals(message_text, "??????????????????????????????", "[Failed][Usage plan] Message do not match.");
            // [page.Payment history] check receipt id and price
            soft.assertNotEquals(receipt_history_payment.get(0), current_payment_history_receipt_id, "[Failed][page.Payment history] Can not find new receipt ID => New payment history has not been added yet.");
            soft.assertEquals(price_and_tax, (long) Long.parseLong(receipt_history_payment.get(1)), "[Failed][page.Payment history] Price not match.");
            // [PayJP] check receipt id and price
            soft.assertNotEquals(receipt_PayJP.get(0), current_PayJP_receipt_id, "[Failed][PayJP] Can not find new receipt ID => New payment history has not been added yet.");
            soft.assertEquals(price_and_tax, (long) Long.parseLong(receipt_PayJP.get(1)), "[Failed][PayJP] Price not match.");
            // show all assert result
            soft.assertAll();
        }
    }

    public List<Integer> getCurrentInformation() {
        // Get current number of users
        String current_number_of_users = number_of_users.getText().split("/ ")[1];
        String number_of_active_users = number_of_users.getText().split(" /")[0];

        // Get current additional purcharse
        String current_additional_purchase;
        try {
            current_additional_purchase = additional_purchase.getText().replace("?????????????????????+", "");
        } catch (NoSuchElementException ex) {
            current_additional_purchase = "0";
        }
        // 0: current upper user, 1: curent aditional purchase, 2: current active user
        return List.of(Integer.parseInt(current_number_of_users), Integer.parseInt(current_additional_purchase), Integer.parseInt(number_of_active_users));
    }

    public void addUpperLimit() throws InterruptedException {
        // get company_id
        System.out.println("Your company id: " + getCompanyID());

        // get old receipt_history_payment id
        String current_payment_history_receipt_id = get_payment_history_receipt().get(0);

        // login_to_Komorebi to PAY.JP and get old receipt_id
        loginToPayJP();
        String current_PayJP_receipt_id = getPayJpReceipt().get(0);

        // Back to usage plan page
        driver.get(domain + USAGE_PLAN_PATH);

        // Waiting for loading page
        sleep(1000);

        // Get current information (number of users and additional purcharse)
        List<Integer> current_information = getCurrentInformation();

        // Get upper limit price
        String upper_price = price_upper_limit.get(0).getText()
                .replace("???????????????????????????", "")
                .replace(",", "")
                .replace("???(??????)", "");

        // Click add upper limit button
        add_upper_limit_button.click();

        // Wait and click OK button
        wait.until(ExpectedConditions.elementToBeClickable(select_option.get(1))).click();

        // Waiting for loading
        String message_text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        wait.until(ExpectedConditions.invisibilityOf(message));

        // Get current information after add upper limit
        List<Integer> new_information = getCurrentInformation();

        // [page.Payment history] Get receipt id and price
        List<String> receipt_history_payment = get_payment_history_receipt();

        // [PAY.JP] Get receipt id and price
        List<String> receipt_PayJP = getPayJpReceipt();

        // Verify that can add upper limit
        // check message
        soft.assertEquals(message_text, "??????????????????????????????????????????", "[Failed][Usage plan] Message do not match.");
        // check number of users after add upper limit
        soft.assertEquals(new_information.get(0) - 1, (int) current_information.get(0), "[Failed][Usage plan] Can not add upper limit. ");
        // check number of additional purchase after add upper limit
        soft.assertEquals(new_information.get(1) - 1, (int) current_information.get(1), "[Failed][Usage plan] Can not add additional purchase.");
        // [page.Payment history] check receipt id and price
        soft.assertNotEquals(receipt_history_payment.get(0), current_payment_history_receipt_id, "[Failed][page.Payment history] Can not find new receipt ID => New payment history has not been added yet.");
        soft.assertEquals(add_tax(upper_price), (long) Long.parseLong(receipt_history_payment.get(1)), "[Failed][page.Payment history] Price not match.");
        // [PayJP] check receipt id and price
        soft.assertNotEquals(receipt_PayJP.get(0), current_PayJP_receipt_id, "[Failed][PayJP] Can not find new receipt ID => New payment history has not been added yet.");
        soft.assertEquals(add_tax(upper_price), (long) Long.parseLong(receipt_PayJP.get(1)), "[Failed][PayJP] Price not match.");
        // show all assert result
        soft.assertAll();
    }


    public void removeUpperLimit() throws InterruptedException {
        // get old get_payment_history_receipt id
        String current_receipt_id = get_payment_history_receipt().get(0);

        // Back to usage plan page
        driver.get(domain + USAGE_PLAN_PATH);

        // Waiting for loading page
        sleep(1000);

        // Get current information (number of users and additional purcharse)
        List<Integer> current_information = getCurrentInformation();


            // Click add upper limit button
            remove_upper_limit_button.click();

            // Wait and click OK button
            wait.until(ExpectedConditions.elementToBeClickable(select_option.get(1))).click();

            // Waiting for loading
            String message_text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
            wait.until(ExpectedConditions.invisibilityOf(message));

        if (current_information.get(1) != 0) {
            // Get current information after add upper limit
            List<Integer> new_information = getCurrentInformation();

            // Get receipt id and price
            List<String> receipt = get_payment_history_receipt();

            // Additional purchase = 0. Can removed upper limit
            // check message
            soft.assertEquals(message_text, "??????????????????????????????????????????", "[Failed][Additional purchase > 0] Message do not match.");
            // [Usage plan] check number of users and additional purcharse
            soft.assertEquals((int) new_information.get(0), current_information.get(0) - 1, "[Failed][Additional purchase > 0] Can not remove upper limit. ");
            soft.assertEquals((int) new_information.get(1), current_information.get(1) - 1, "[Failed][Additional purchase > 0] Can not remove additional purchase.");
            // [page.Payment history] check receipt id
            soft.assertEquals(receipt.get(0), current_receipt_id, "[Failed][page.Payment history] New receipt id has been found => Can not remove upper limit.");
        } else {
            // Additional purchase = 0. Can not removed upper limit
            soft.assertEquals(message_text, "????????????????????????????????????????????????????????????", "[Failed][Additional purchase = 0] Message do not match.");
        }
        // Show all assert result
        soft.assertAll();
    }
}
