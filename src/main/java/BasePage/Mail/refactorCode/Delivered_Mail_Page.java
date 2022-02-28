package BasePage.Mail.refactorCode;

import Common.Common;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static BasePage.Link_and_Path.HBS.mail_list_path;
import static java.lang.Thread.sleep;

public class Delivered_Mail_Page {
    @FindBy(css = "button.ant-btn-danger")
    WebElement delete_button;

    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(2)")
    WebElement ok_button;

    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(1)")
    WebElement cancel_button;

    @FindBy(css = "span>div>div>div:nth-child(1)>button")
    WebElement make_a_copy_button;

    @FindBy(css = "div.ant-col:nth-child(2)>button.ant-btn-sm")
    WebElement save_as_draft_button;

    @FindBy(css = "div.ant-message-custom-content>span:nth-child(2)")
    WebElement message;

    @FindBy(css = "tr[data-row-key]:nth-child(2)")
    WebElement first_mail;

    @FindBy(css = "div.ant-row> div:nth-child(2) > button.ant-btn-primary")
    WebElement update_button;


    WebDriver driver;
    WebDriverWait wait;
    String role;
    Common cm;
    Actions key;
    SoftAssert soft;
    String domain;
    String Mode;
    public static String mail_id_for_delete_test;
    public static String mail_id_for_save_as_draft_test;

    public Delivered_Mail_Page(WebDriver driver, String role, Common cm, String domain, String Mode) {
        this.driver = driver;
        this.role = role;
        this.cm = cm;
        this.domain = domain;
        this.Mode = Mode;
        soft = new SoftAssert();
        key = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String get_mail_id() {
        return first_mail.getAttribute("data-row-key");
    }

    public void wait_for_loading_element(WebElement element) {
        boolean check = true;
        do {
            try {
                element.click();
            } catch (ElementClickInterceptedException ex) {
                check = false;
            }
        }
        while (check);
    }

    public void do_you_want_to_delete_this_delivered_email_OK() throws InterruptedException {
        // Click delete button
        delete_button.click();

        // Click OK button
        wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

        // Waiting for loading mail list page
        String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        wait.until(ExpectedConditions.invisibilityOf(message));

        // check message
        soft.assertEquals(text, "アイテムが削除されました", "[Failed][Delete Delivered Mail] Message do not match.");

        // check current url
        soft.assertEquals(driver.getCurrentUrl(), domain + mail_list_path, "[Failed] Can not delete delivered email");

        // show all assert result
        soft.assertAll();
    }

    public void do_you_want_to_delete_this_delivered_email_Cancel() throws InterruptedException {
        // Click delete button
        delete_button.click();

        // Click cancel button
        wait.until(ExpectedConditions.elementToBeClickable(cancel_button)).click();

        // Wait popup close
        sleep(500);

        // Check popup close
        boolean check = true;
        try {
            cancel_button.click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        soft.assertFalse(check, "[Failed] Can not close delete delivered email popup");
        soft.assertAll();
    }

    public void make_a_copy() throws InterruptedException {
        if (Mode.equals("Edit")) {
            try {
                sleep(3000);
            }
            catch (ElementNotInteractableException ex)
            {
                //Skip
            }
        }

        // Click make a copy button
//        key.moveToElement(make_a_copy_button).click();
        // scroll to top
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        wait.until(ExpectedConditions.elementToBeClickable(make_a_copy_button));
        key.moveToElement(make_a_copy_button).click().build().perform();

        // Waiting for loading mail list page
        String text;
        try {
            text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        }
        catch (TimeoutException ex) {
            key.moveToElement(make_a_copy_button).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(message));
            text = message.getText();
        }

        sleep(2000);
//        wait.until(ExpectedConditions.invisibilityOf(message));

        // check message
        soft.assertEquals(text, "アイテムがコピーされました", "[Failed][Make a copy] Message do not match.");

        // check current url
        soft.assertEquals(driver.getCurrentUrl(), domain + mail_list_path, "[Failed][Make a copy] Can not make a copy of delivered email.");

        // Because when you delete or save as draft, your mail will be changed.
        // So, I make two copy for this test.
        // get mail id for delete and save as draft testcase
        if (Mode.equals("Edit")) {
            // get mail id for delete testcase
            mail_id_for_delete_test = get_mail_id();

            // make a copy mail again for save as draft testcase
            driver.get(domain + mail_list_path + "/" + mail_id_for_delete_test);

            // wait for loading distributor list
            sleep(3000);

            // click make a copy button to make a copy mail
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
            wait.until(ExpectedConditions.elementToBeClickable(make_a_copy_button));
            make_a_copy_button.click();
//            make_a_copy_button.click();

            // Waiting for loading mail list page
            wait.until(ExpectedConditions.visibilityOf(message));
            String text1 = message.getText();
            sleep(2000);
//            wait.until(ExpectedConditions.invisibilityOf(message));

            // check message
            soft.assertEquals(text1, "アイテムがコピーされました", "[Failed][Make a copy mail from another copy] Message do not match.");

            // check current url
            soft.assertEquals(driver.getCurrentUrl(), domain + mail_list_path, "[Failed][Make a copy from another copy] Can not make a copy of delivered email from another copy.");

            // get mail id for save as draft testcase
            mail_id_for_save_as_draft_test = get_mail_id();
        }
        // show all assert result
        soft.assertAll();
    }

    public void switch_url_before_run_test(String test_item) throws InterruptedException {
        if (test_item.equals("delete")) {
            driver.get(domain + mail_list_path + "/" + mail_id_for_delete_test);
        } else if (test_item.equals("save as draft")) {
            driver.get(domain + mail_list_path + "/" + mail_id_for_save_as_draft_test);
        }
        // wait for distributor list loading
        sleep(3000);
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_OK() throws InterruptedException {
        // scroll to top page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

        // Click save as draft button
        key.moveToElement(save_as_draft_button).click().build().perform();
//        save_as_draft_button.click();

        // Click OK button
        wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

        // Waiting for loading mail list page
        String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        wait.until(ExpectedConditions.invisibilityOf(message));

        // check message
        soft.assertEquals(text, "アイテムが更新されました", "[Failed][Save as Draft] Message do not match.");
        soft.assertEquals(driver.getCurrentUrl(), domain + mail_list_path, "[Failed][Save as Draft] Can not change status mail to draft.");
        soft.assertAll();
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel() throws InterruptedException {
        // scroll to top page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

        // Click save as draft button
        key.moveToElement(save_as_draft_button).click().build().perform();
//        save_as_draft_button.click();

        // Click cancel button
        wait.until(ExpectedConditions.elementToBeClickable(cancel_button)).click();

        // Wait popup close
        sleep(500);

        // Check popup close
        boolean check = true;
        try {
            cancel_button.click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        soft.assertFalse(check, "[Failed] Can not close save delivered as draft popup.");
        soft.assertAll();
    }

    // Update button
    public void update_delivery_with_valid_data() {
        update_button.click();
        String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        wait.until(ExpectedConditions.invisibilityOf(message));

        // check message
        soft.assertEquals(text, "アイテムが更新されました", "[Update mail][Basic information] Message do not match.");

        // check current url
        soft.assertEquals(driver.getCurrentUrl(), domain + mail_list_path, "[Failed][Update] Can not update delivered email.");
        soft.assertAll();
    }
}
