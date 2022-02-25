package BasePage.paymentProcess;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

@SuppressWarnings("ALL")
public class usage_plan extends payment {
    private static String usage_plan_path = "/plan";
    private static String user_list_path = "/users";
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
    @FindBy(css = "div.ant-btn-group > button")
    WebElement search_template_button;
    @FindBy(css = "#inactive_filter")
    WebElement inactive_filter;
    @FindBy(css = "div>a.ant-btn")
    WebElement invite_user_button;
    @FindBy(css = "div.ant-row-space-between>div>button")
    WebElement cancel_button_search_template;
    @FindBy(css = "li.ant-pagination-total-text")
    WebElement current_number_of_users;
    @FindBy(css = "#email")
    WebElement invite_user_email;

    @FindBy(css = "#role")
    WebElement invite_user_role;

    @FindBy(css = "div.ant-row-start>button:nth-child(2)")
    WebElement invite_user_invite_button;
    
    @FindBy(css = "td:nth-child(1)>div>div>div>label>span>input")
    WebElement user_list_first_check_box;
    
    @FindBy(css = ".page-tableControlButton-DRVBm:nth-child(2)")
    WebElement user_list_active_button;

    WebDriverWait wait;
    String username_admin_page;
    String password_admin_page;
    Actions key;


    public usage_plan(WebDriver driver, String domain, String username_admin_page, String password_admin_page) {
        super(driver,domain, username_admin_page, password_admin_page);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        key = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

//    public usage_plan(WebDriver driver, Actions key, String domain, String username_admin_page, String password_admin_page) {
//        super(driver, key, domain, username_admin_page, password_admin_page);
//    }

    public boolean check_plan() throws InterruptedException {
        driver.get(domain + usage_plan_path);
        sleep(1000);
        return plan_list.get(0).isEnabled();
    }

    public void buy_plan_OK() throws InterruptedException {
        if (check_plan()) {
            // get company_id
            System.out.println("Your company id: " + get_company_id());

            // get old receipt_history_payment id
            String current_payment_history_receipt_id = get_payment_history_receipt().get(0);

            // login to PAY.JP and get old receipt_id
            login_to_PayJP_page();
            String current_PayJP_receipt_id = get_PayJP_receipt().get(0);

            // Back to usage plan page
            driver.get(domain + usage_plan_path);

            // Add 10% tax
            long price_and_tax = add_tax(plan_price_list.get(0).getText());

            // Click buy this plan button
            plan_list.get(0).click();

            // Wait and click ok button
            wait.until(ExpectedConditions.elementToBeClickable(select_option.get(1))).click();

            // Waiting for loading
            String message_text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
            wait.until(ExpectedConditions.invisibilityOf(message));

            // [Payment history] Get receipt id and price
            List<String> receipt_history_payment = get_payment_history_receipt();

            // [PAY.JP] Get receipt id and price
            List<String> receipt_PayJP = get_PayJP_receipt();

            // Verify that usage plan should be unlocked
            // check message
            soft.assertEquals(message_text, "プランを購入しました", "[Failed][Usage plan] Message do not match.");
            // [Payment history] check receipt id and price
            soft.assertNotEquals(receipt_history_payment.get(0), current_payment_history_receipt_id, "[Failed][Payment history] Can not find new receipt ID => New payment history has not been added yet.");
            soft.assertEquals(price_and_tax, (long) Long.parseLong(receipt_history_payment.get(1)), "[Failed][Payment history] Price not match.");
            // [PayJP] check receipt id and price
            soft.assertNotEquals(receipt_PayJP.get(0), current_PayJP_receipt_id, "[Failed][PayJP] Can not find new receipt ID => New payment history has not been added yet.");
            soft.assertEquals(price_and_tax, (long) Long.parseLong(receipt_PayJP.get(1)), "[Failed][PayJP] Price not match.");
            // show all assert result
            soft.assertAll();
        }
    }

    public void buy_plan_Cancel() throws InterruptedException {
        if (check_plan()) {
            // Click buy this plan button
            plan_list.get(0).click();

            // Wait and click ok button
            wait.until(ExpectedConditions.elementToBeClickable(select_option.get(0))).click();

            // Waiting for closing popup
            sleep(500);

            // Verify that Buy this plan should be closed
            boolean check = true;
            try {
                select_option.get(0).click();
            } catch (IndexOutOfBoundsException ex) {
                check = false;
            }
            soft.assertFalse(check, "[Failed] Can not close buy this plan popup.");

            // Verify that plan should not be bought
            soft.assertTrue(plan_list.get(0).isEnabled(), "[Failed] Plan has been bought.");

            // show all assert result
            soft.assertAll();
        }
    }

    public List<Integer> get_current_information() {
        // Get current number of users
        String current_number_of_users = number_of_users.getText().split("/")[1].replace(" ", "");

        // Get current additional purcharse
        String current_additional_purchase;
        try {
            current_additional_purchase = additional_purchase.getText().replace("内、追加購入分+", "");
        } catch (NoSuchElementException ex) {
            current_additional_purchase = "0";
        }
        return List.of(Integer.parseInt(current_number_of_users), Integer.parseInt(current_additional_purchase));
    }

    public void add_upper_limit_OK() throws InterruptedException {
        // get company_id
        System.out.println("Your company id: " + get_company_id());

        // get old receipt_history_payment id
        String current_payment_history_receipt_id = get_payment_history_receipt().get(0);

        // login to PAY.JP and get old receipt_id
        login_to_PayJP_page();
        String current_PayJP_receipt_id = get_PayJP_receipt().get(0);

        // Back to usage plan page
        driver.get(domain + usage_plan_path);

        // Waiting for loading page
        sleep(1000);

        // Get current information (number of users and additional purcharse)
        List<Integer> current_information = get_current_information();

        // Get upper limit price
        String upper_price = price_upper_limit.get(0).getText()
                .replace("ユーザー追加：月額", "")
                .replace(",", "")
                .replace("円(税抜)", "");

        // Click add upper limit button
        add_upper_limit_button.click();

        // Wait and click OK button
        wait.until(ExpectedConditions.elementToBeClickable(select_option.get(1))).click();

        // Waiting for loading
        String message_text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        wait.until(ExpectedConditions.invisibilityOf(message));

        // Get current information after add upper limit
        List<Integer> new_information = get_current_information();

        // [Payment history] Get receipt id and price
        List<String> receipt_history_payment = get_payment_history_receipt();

        // [PAY.JP] Get receipt id and price
        List<String> receipt_PayJP = get_PayJP_receipt();

        // Verify that can add upper limit
        // check message
        soft.assertEquals(message_text, "ユーザーの追加に成功しました", "[Failed][Usage plan] Message do not match.");
        // check number of users after add upper limit
        soft.assertEquals(new_information.get(0) - 1, (int) current_information.get(0), "[Failed][Usage plan] Can not add upper limit. ");
        // check number of additional purchase after add upper limit
        soft.assertEquals(new_information.get(1) - 1, (int) current_information.get(1), "[Failed][Usage plan] Can not add additional purchase.");
        // [Payment history] check receipt id and price
        soft.assertNotEquals(receipt_history_payment.get(0), current_payment_history_receipt_id, "[Failed][Payment history] Can not find new receipt ID => New payment history has not been added yet.");
        soft.assertEquals(add_tax(upper_price), (long) Long.parseLong(receipt_history_payment.get(1)), "[Failed][Payment history] Price not match.");
        // [PayJP] check receipt id and price
        soft.assertNotEquals(receipt_PayJP.get(0), current_PayJP_receipt_id, "[Failed][PayJP] Can not find new receipt ID => New payment history has not been added yet.");
        soft.assertEquals(add_tax(upper_price), (long) Long.parseLong(receipt_PayJP.get(1)), "[Failed][PayJP] Price not match.");
        // show all assert result
        soft.assertAll();

        // Check limit of users
        // Link to user list page
        driver.get(domain + user_list_path);

        // Search condition - Inactive users: ON
        search_template_button.click();
        wait.until(ExpectedConditions.visibilityOf(inactive_filter));
        if (!inactive_filter.isEnabled()) {
            inactive_filter.click();
        }
        cancel_button_search_template.click();

        // Get current users
        String number_of_users = wait.until(ExpectedConditions.visibilityOf(current_number_of_users))
                .getText().split("件中")[0].replace("合計", "");
        for (int i = 0; i < (int) (new_information.get(1) - Long.parseLong(number_of_users)); i++) {
            // click invite user button
            wait.until(ExpectedConditions.elementToBeClickable(invite_user_button)).click();
            // input valid email
            wait.until(ExpectedConditions.visibilityOf(invite_user_email))
                    .sendKeys(RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@"
                            + RandomStringUtils.randomAlphabetic(3).toLowerCase() + "."
                            + RandomStringUtils.randomAlphabetic(2).toLowerCase());
            // open role list and select
            invite_user_role.click();
            // 1: Administrator, 2: Responsible person, 3: Leader, 4: Member, 5: Guest
            int role_id = RandomUtils.nextInt(5);
            for (int j = 0; j < role_id; j++) {
                key.sendKeys(Keys.DOWN).perform();
            }

            key.sendKeys(Keys.ENTER).perform();

            // click invite button
            invite_user_invite_button.click();

            // waiting for send invite mail
            String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();

            // verify that user should be invited
            // check message
            soft.assertEquals(text, "招待メールを送信しました", "[Failed][Invite user] Message do not match.");
            soft.assertEquals(driver.getCurrentUrl(), domain + user_list_path, "[Failed][Invite user] Url do not match.");
        }
    }

    public void add_upper_limit_Cancel() throws InterruptedException {
        // get old get_payment_history_receipt id
        String current_receipt_id = get_payment_history_receipt().get(0);

        // Back to usage plan page
        driver.get(domain + usage_plan_path);

        // Waiting for loading page
        sleep(1000);

        // Get current information (number of users and additional purcharse)
        List<Integer> current_information = get_current_information();

        // Click add upper limit button
        add_upper_limit_button.click();

        // Wait and click Cancel button
        wait.until(ExpectedConditions.elementToBeClickable(select_option.get(0))).click();

        // Waiting for Add upper limit popup close
        sleep(500);
        boolean check = true;
        try {
            select_option.get(0).click();
        } catch (IndexOutOfBoundsException ex) {
            check = false;
        }

        // Get current information after add upper limit
        List<Integer> new_information = get_current_information();

        // Get receipt id and price
        List<String> receipt = get_payment_history_receipt();

        // Verify that Add upper limit popup should be closed
        soft.assertFalse(check, "[Failed] Can not close Add upper limit popup.");
        // [Usage plan] check number of users and additional purcharse
        soft.assertEquals(new_information.get(0), current_information.get(0), "[Failed] Upper limit increase => Can not cancel upper limit.");
        soft.assertEquals(new_information.get(1), current_information.get(1), "[Failed] Additional purcharse increase => Can not cancel upper limit.");
        // [Payment history] check receipt id
        soft.assertEquals(receipt.get(0), current_receipt_id, "[Failed][Payment history] New receipt id has been found => Can not cancel add upper limit.");
        soft.assertAll();
    }


    public void remove_upper_limit_OK() throws InterruptedException {
        // get old get_payment_history_receipt id
        String current_receipt_id = get_payment_history_receipt().get(0);

        // Back to usage plan page
        driver.get(domain + usage_plan_path);

        // Waiting for loading page
        sleep(1000);

        // Get current information (number of users and additional purcharse)
        List<Integer> current_information = get_current_information();

        if (current_information.get(1) != 0) {
            // Click add upper limit button
            remove_upper_limit_button.click();

            // Wait and click OK button
            wait.until(ExpectedConditions.elementToBeClickable(select_option.get(1))).click();

            // Waiting for loading
            String message_text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
            wait.until(ExpectedConditions.invisibilityOf(message));

            // Get current information after add upper limit
            List<Integer> new_information = get_current_information();

            // Get receipt id and price
            List<String> receipt = get_payment_history_receipt();

            // Additional purchase = 0. Can removed upper limit
            // check message
            soft.assertEquals(message_text, "ユーザーの削除に成功しました", "[Failed][Additional purchase > 0] Message do not match.");
            // [Usage plan] check number of users and additional purcharse
            soft.assertEquals((int) new_information.get(0), current_information.get(0) - 1, "[Failed][Additional purchase > 0] Can not remove upper limit. ");
            soft.assertEquals((int) new_information.get(1), current_information.get(1) - 1, "[Failed][Additional purchase > 0] Can not remove additional purchase.");
            // [Payment history] check receipt id
            soft.assertEquals(receipt.get(0), current_receipt_id, "[Failed][Payment history] New receipt id has been found => Can not remove upper limit.");
        } else {
            // Additional purchase = 0. Can not removed upper limit
//            soft.assertEquals(message_text, "ユーザー登録上限数の削除に失敗しました。", "[Failed][Additional purchase = 0] Message do not match.");
            sleep(1000);
            soft.assertFalse(remove_upper_limit_button_disable.isEnabled(), "[Failed] Remove upper limit button is not getting disable.");
        }
        // Show all assert
        soft.assertAll();
    }

    public void remove_upper_limit_Cancel() throws InterruptedException {
        // Link to usage plan page
        driver.get(domain + usage_plan_path);

        // Waiting for loading page
        sleep(1000);

        // Get current information (number of users and additional purcharse)
        List<Integer> current_information = get_current_information();

        if (current_information.get(1) != 0) {
            // Click add upper limit button
            remove_upper_limit_button.click();

            // Wait and click OK button
            wait.until(ExpectedConditions.elementToBeClickable(select_option.get(0))).click();

            // Waiting for Remove upper limit popup close
            sleep(500);
            boolean check = true;
            try {
                select_option.get(0).click();
            } catch (IndexOutOfBoundsException ex) {
                check = false;
            }

            // Get current information after add upper limit
            List<Integer> new_information = get_current_information();

            // Additional purchase = 0. Can removed upper limit
            // Verify that Cancel upper limit should be closed
            soft.assertFalse(check, "[Failed] Can not close Remove upper limit popup.");
            // [Usage plan] check number of users and additional purcharse
            soft.assertEquals(new_information.get(0), current_information.get(0), "[Failed] Upper limit decrease => Can not cancel remove upper limit.");
            soft.assertEquals(new_information.get(1), current_information.get(1), "[Failed] Additional purchase decrease => Can not cancel remove upper limit.");
        } else {
            // Additional purchase = 0. Can not removed upper limit
//            soft.assertEquals(message_text, "ユーザー登録上限数の削除に失敗しました。", "[Failed][Additional purchase = 0] Message do not match.");
            sleep(1000);
            soft.assertFalse(remove_upper_limit_button_disable.isEnabled(), "[Failed] Remove upper limit button is not getting disable.");
        }
        // Show all assert
        soft.assertAll();
    }

}
