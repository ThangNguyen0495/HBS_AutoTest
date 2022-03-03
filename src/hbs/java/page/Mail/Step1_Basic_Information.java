package page.Mail;

import utilities.Common.Common;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Step1_Basic_Information extends Delivered_Mail_Page {
    @FindBy(css = "#text_format>label>span.ant-radio>input")
    List<WebElement> format;

    @FindBy(css = "div.ant-form-item-control-input-content>div>div>div>div.ant-select-selector>span:nth-child(2)")
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
    WebElement next_step_button;

    @FindBy(css = "div.ant-steps-item-icon")
    List<WebElement> step_list;

    @FindBy(css = "form > div:nth-child(2) > div > div.ant-form-item-explain > div")
    WebElement distributor_error;

    @FindBy(css = "form > div:nth-child(3) > div > div.ant-form-item-explain > div")
    WebElement subject_error;

    @FindBy(css = "form > div:nth-child(4) > div > div.ant-form-item-explain > div")
    WebElement insertion_error;

    @FindBy(css = "div.ant-message-custom-content>span:nth-child(2)")
    WebElement message;

    public Step1_Basic_Information(WebDriver driver, String role, Common common, String domain, String Mode) {
        super(driver, role, common, domain, Mode);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void format() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            // waiting for loading distributor list
            sleep(3000);

            // Format
            // 0: Text, 1: Html
            int id = RandomUtils.nextInt(2);
            format.get(id).click();
        }
    }

    public void distributor() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
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

    public void subject() {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            // Delete old subject
            subject.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
            // length of subject in range 1-100
            int length_of_subject = RandomUtils.nextInt(100) + 1;
            subject.sendKeys(RandomStringUtils.randomAlphabetic(length_of_subject));
        }
    }

    public void insertion() {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            // Delete old insertion
            insertion.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
            // length of insertion in range 1-10000
            int length_of_insertion = RandomUtils.nextInt(50) + 1;
            insertion.sendKeys(RandomStringUtils.randomAlphabetic(length_of_insertion));
        }
    }

    public void send_a_copy_to_the_distributor() {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            // Send a copy to the distributor
            // 0: not change, 1: change
            int send_copy = RandomUtils.nextInt(2);
            if (send_copy == 1) {
                send_a_copy_to_the_distributor.click();
            }
        }
    }

    public void next_to_attachment_step() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            // Next to 添付ファイル_Step
            next_step_button.click();

            // check message in case register mail
            if (Mode.equals("Register")) {
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                wait.until(ExpectedConditions.invisibilityOf(message));
                soft.assertEquals(text, "アイテムが作成されました", "[Failed][Basic Information] Message do not match.");
            }
            else {
                sleep(2000);
            }

            // Check current tab
            // 0: Basic information, 1: Attachment, 2: Destination selection, 3: Final confirmation
            boolean check = wait.until(ExpectedConditions.visibilityOf(step_list.get(1))).isEnabled();
            soft.assertTrue(check, "[Failed] Can not next to Attachment from Basic information.");

            // show all assert result
            soft.assertAll();
        }
    }

    /*
        Validation function
    */

    // Distributor
    public void leave_distributor_blank() {
        // waiting for loading distributor list
        wait_for_loading_element(distributor);

        // click "x" button to clear current distributor
        clear_distributor.click();

        // check error message
        String text = wait.until(ExpectedConditions.visibilityOf(distributor_error)).getText();
        soft.assertEquals(text, "必須項目です。", "[Distributor] Message do not match");
        soft.assertAll();
    }

    // Subject
    public void leave_subject_blank() {
        // leave subject blank
        wait.until(ExpectedConditions.elementToBeClickable(subject)).sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);

        // check error message
        String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
        soft.assertEquals(text, "必須項目です。", "[Failed][Subject][Blank] Message do not match");
        soft.assertAll();
    }

    public void subject_exceed_100_half_width_characters() throws InterruptedException {
        // wait and delete old subject
        wait_for_loading_element(distributor);
        subject.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

        // input subject exceed 100 half width characters
        subject.sendKeys(RandomStringUtils.randomAlphabetic(101));

        // waiting for loading new message
        sleep(500);
        String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
        soft.assertEquals(text, "100文字以内で入力してください。", "[Failed][Subject][Exceed 100 half width] Message do not match");
        soft.assertAll();
    }

    public void subject_exceed_100_full_width_characters() throws InterruptedException {
        // wait and delete old subject
        wait_for_loading_element(distributor);
        subject.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

        // input subject exceed 100 full width characters
        subject.sendKeys(RandomStringUtils.random(101, 0x4e00, 0x4f80, true, false));

        // waiting for loading new message
        sleep(500);
        String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
        soft.assertEquals(text, "100文字以内で入力してください。", "[Failed][Subject][Exceed 100 full width] Message do not match");
        soft.assertAll();
    }

    public void subject_exceed_100_mix_half_and_full_width_characters() throws InterruptedException {
        // wait and delete old subject
        wait_for_loading_element(distributor);
        subject.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

        // input subject exceed 100 mix half and full width characters
        int length_of_half_width = RandomUtils.nextInt(100) + 1;
        String subject_text = RandomStringUtils.randomAlphabetic(length_of_half_width)
                + RandomStringUtils.random(101 - length_of_half_width, 0x4e00, 0x4f80, true, false);
        subject.sendKeys(subject_text);

        // waiting for loading new message
        sleep(500);
        String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
        soft.assertEquals(text, "100文字以内で入力してください。", "[Failed][Subject][Exceed 100 mix] Message do not match");
        soft.assertAll();
    }

    // Insertion
    public void leave_insertion_blank() {
        // leave insertion blank
        insertion.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);

        // waiting for loading new message
        String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
        soft.assertEquals(text, "必須項目です。", "[Failed][Insertion][Blank] Message do not match");
        soft.assertAll();
    }

    public void insertion_exceed_10000_half_width_characters() throws InterruptedException {
        // wait and delete old insertion
        wait_for_loading_element(distributor);
        insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

        // input insertion exceed 10000 half width characters
        String insertion_text = RandomStringUtils.randomAlphabetic(5000);
        insertion.sendKeys(insertion_text);
        insertion.sendKeys(insertion_text + " ");

        // Scroll down
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // waiting for loading new message
        sleep(1000);
        String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
        soft.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Failed][Insertion][Exceed 10000 half width] Message do not match");
        soft.assertAll();
    }

    public void insertion_exceed_5000_full_width_characters() throws InterruptedException {
        // minJpnCharCode: 0x4e00
        // maxJpnCharCode: 0x4f80
        // wait and delete old insertion
        wait_for_loading_element(distributor);
        insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

        // input insertion exceed 5000 full width characters
        String insert_text = RandomStringUtils.random(2500, 0x4e00, 0x4f80, true, false);
        insertion.sendKeys(insert_text + insert_text + " ");

        // Scroll down
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // waiting for loading new message
        sleep(500);
        String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
        soft.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Failed][Insertion][Exceed 5000 full width] Message do not match");
        soft.assertAll();
    }

    public void insertion_exceed_5000_mix_half_and_full_width_characters() throws InterruptedException {
        // wait and delete old insertion
        wait_for_loading_element(distributor);
        insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

        // input insertion exceed 5000 characters (mix full and half width)
        String insertion_half = RandomStringUtils.randomAlphabetic(2500);
        String insertion_full = RandomStringUtils.random(2501, 0x4e00, 0x4f80, true, false);

        // minJpnCharCode: 0x4e00
        // maxJpnCharCode: 0x4f80
        insertion.sendKeys(insertion_half + insertion_full);

        // Scroll down
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // waiting for loading new message
        sleep(500);
        String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
        soft.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Failed][Insertion][Exceed 5000 mix] Message do not match");
        soft.assertAll();
    }

    public void delete_button_should_be_disable() {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            soft.assertFalse(driver.findElement(By.cssSelector("button.ant-btn-danger")).isEnabled(), "[Delete] button is not getting disable.");
            soft.assertAll();
        }
    }
}
