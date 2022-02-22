package hidenBasePage.editMail;

import Common.Common;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Basic_information {
    /*
        Process function
    */
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "#text_format>label:nth-child(1)>span.ant-radio>input")
    WebElement text;

    @FindBy(css = "#text_format>label:nth-child(2)>span.ant-radio>input")
    WebElement html;

    @FindBy(css = "div.ant-form-item-control-input-content>div>div>div>div.ant-select-selector")
    WebElement distributor;

    @FindBy(css = "div.ant-col-9> div > div > div > div > div > span.ant-select-clear")
    WebElement clear_distributor;

    @FindBy(css = "input[type='text']")
    WebElement subject;

    @FindBy(css = "textarea[id='text']")
    WebElement insertion;

    @FindBy(css = "#send_copy_to_sender>span")
    WebElement send_a_copy_to_the_distributor;

    @FindBy(css = "div:nth-child(4)>div>button")
    @CacheLookup
    WebElement next_step_button;

    @FindBy(css = "div:nth-child(2)>div>div.ant-steps-item-icon")
    WebElement attachment_step;

    @FindBy(css = "form > div:nth-child(2) > div > div.ant-form-item-explain > div")
    WebElement distributor_error;

    @FindBy(css = "form > div:nth-child(3) > div > div.ant-form-item-explain > div")
    WebElement subject_error;

    @FindBy(css = "form > div:nth-child(4) > div > div.ant-form-item-explain > div")
    WebElement insertion_error;

    @FindBy(css = "button.ant-btn-danger")
    WebElement delete_button;

    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(2)")
    WebElement ok_button;

    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(1)")
    WebElement cancel_button;

    @FindBy(css = "div:nth-child(1)>button.ant-btn-sm")
    WebElement make_a_copy_button;

    @FindBy(css = "div.ant-col:nth-child(2)>button.ant-btn-sm")
    WebElement save_as_draft_button;

    @FindBy(css = "div.ant-row> div:nth-child(2) > button.ant-btn-primary")
    WebElement update_button;

    public Basic_information(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void format(String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Format
            // 1: Text, 2: Html
            try {
                text.click();
            } catch (ElementClickInterceptedException ex) {
                html.click();
            }
        }
    }

    public void distributor(Actions key, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // waiting for loading distributor list
            sleep(3000);

            // Distributor
            // random distributor in range 1-20
            int distributor_id = RandomUtils.nextInt(20) + 1;
            distributor.click();
            for (int i = 0; i < distributor_id; i++) {
                key.sendKeys(Keys.DOWN).perform();
            }
            sleep(1000);
            key.sendKeys(Keys.ENTER).perform();
        }
    }

    public void subject(String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Delete old subject
            wait.until(ExpectedConditions.elementToBeClickable(distributor));
            wait.until(ExpectedConditions.elementToBeClickable(subject)).sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            // length of subject in range 1-100
            int length_of_subject = RandomUtils.nextInt(100) + 1;
            subject.sendKeys(RandomStringUtils.randomAlphabetic(length_of_subject));
        }
    }

    public void insertion(String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Delete old insertion
            insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            // length of insertion in range 1-10000
            int length_of_insertion = RandomUtils.nextInt(50) + 1;
            insertion.sendKeys(RandomStringUtils.randomAlphabetic(length_of_insertion));
        }
    }

    public void send_a_copy_to_the_distributor(String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Send a copy to the distributor
            // 0: not change, 1: change
            int send_copy = RandomUtils.nextInt(2);
            if (send_copy == 1) {
                send_a_copy_to_the_distributor.click();
            }
        }
    }

    public void next_to_attachment_step(String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Next to 添付ファイル_Step
            next_step_button.click();
            // Check current tab
            boolean check = wait.until(ExpectedConditions.visibilityOf(attachment_step)).isEnabled();
            Assert.assertTrue(check, "[Failed] Can not next to Attachment from Basic information.");
            // Waiting for attachment tab loading
            sleep(1000);
        }
    }

    /*
        Validation function
    */

    // Distributor
    public void leave_distributor_blank() throws InterruptedException {
        // waiting for loading distributor list
        sleep(3000);
        // click "x" button to delete distributor
        clear_distributor.click();
        String text = wait.until(ExpectedConditions.visibilityOf(distributor_error)).getText();
        Assert.assertEquals(text, "必須項目です。", "[Distributor] Message do not match");
    }

    // Subject
    public void leave_subject_blank() {
        wait.until(ExpectedConditions.elementToBeClickable(subject)).sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
        Assert.assertEquals(text, "必須項目です。", "[Subject] Message do not match");
    }

    public void subject_exceed_100_half_width_characters() throws InterruptedException {
        // wait and delete old subject
        sleep(3000);
        subject.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        subject.sendKeys(RandomStringUtils.randomAlphabetic(101));

        // waiting for loading new message
        sleep(500);
        String text = subject_error.getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Subject] Message do not match");
        sleep(10000);
    }

    public void subject_exceed_100_full_width_characters() throws InterruptedException {
        // wait and delete old subject
        sleep(3000);
        subject.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        subject.sendKeys(RandomStringUtils.random(101, 0x4e00, 0x4f80, true, false));

        //waiting for loading new message
        sleep(500);
        String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Subject] Message do not match");
    }

    public void subject_exceed_100_mix_half_and_full_width_characters() throws InterruptedException {
        // wait and delete old subject
        sleep(3000);
        subject.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        int length_of_half_width = RandomUtils.nextInt(100) + 1;
        String subject_text = RandomStringUtils.randomAlphabetic(length_of_half_width)
                + RandomStringUtils.random(101 - length_of_half_width, 0x4e00, 0x4f80, true, false);
        subject.sendKeys(subject_text);

        //waiting for loading new message
        sleep(500);
        String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Subject] Message do not match");
    }

    // Insertion
    public void leave_insertion_blank() {
        insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
        Assert.assertEquals(text, "必須項目です。", "[Insertion] Message do not match");
    }

    public void insertion_exceed_10000_half_width_characters() throws InterruptedException {
        // wait and delete old insertion
        sleep(3000);
        insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        String insertion_text = RandomStringUtils.randomAlphabetic(5000);
        insertion.sendKeys(insertion_text);
        insertion.sendKeys(insertion_text + " ");

        //waiting for loading new message
        String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
        Assert.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Insertion] Message do not match");
    }

    public void insertion_exceed_5000_full_width_characters() throws InterruptedException {
        // minJpnCharCode: 0x4e00
        // maxJpnCharCode: 0x4f80
        // wait and delete old insertion
        sleep(3000);
        insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        String insert_text = RandomStringUtils.random(2500, 0x4e00, 0x4f80, true, false);
        insertion.sendKeys(insert_text);
        insertion.sendKeys(insert_text + " ");


        //waiting for loading new message
        String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
        Assert.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Insertion] Message do not match");
    }

    public void insertion_exceed_5000_mix_half_and_full_width_characters() throws InterruptedException {
        // wait and delete old insertion
        sleep(3000);
        insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        String insertion_half = RandomStringUtils.randomAlphabetic(2500);
        String insertion_full = RandomStringUtils.random(2501, 0x4e00, 0x4f80, true, false);

        // minJpnCharCode: 0x4e00
        // maxJpnCharCode: 0x4f80
        insertion.sendKeys(insertion_half);
        insertion.sendKeys(insertion_full);

        //waiting for loading new message
        sleep(500);
        String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
        Assert.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Insertion] Message do not match");
    }

    // Delete button
    public void update_delivery_with_valid_data(String url_mail_list) throws InterruptedException {
        sleep(3000);
        subject.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        subject.sendKeys(RandomStringUtils.randomAlphabetic(100));
        insertion.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        insertion.sendKeys(RandomStringUtils.random(5000, 0x4e00, 0x5f80, true, false));
        update_button.click();
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Update] Can not update delivered email.");
    }

    public void do_you_want_to_delete_this_delivered_email_OK(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click delete button
        delete_button.click();

        // Click OK button
        wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

        // Waiting for loading mail list page
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not delete delivered email");
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
        Assert.assertFalse(check, "[Failed] Can not close delete delivered email popup");
    }

    public void make_a_copy(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click make a copy button
        make_a_copy_button.click();

        // Waiting for loading mail list page
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not make a copy of delivered email.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_OK(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click save as draft button
        save_as_draft_button.click();

        // Click OK button
        wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

        // Waiting for loading mail list page
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not save delivered as draft.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel() throws InterruptedException {
        // Click save as draft button
        save_as_draft_button.click();

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
        Assert.assertFalse(check, "[Failed] Can not close save delivered as draft popup.");
    }
}


