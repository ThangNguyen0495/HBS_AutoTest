package page.mail;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.common.Common;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Step1BasicInformation extends DeliveredMailPage {
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

    // Register Mode
    public Step1BasicInformation(WebDriver driver, String role, Common common, String domain, String Mode) {
        super(driver, role, common, domain, Mode);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Edit Mode
    public Step1BasicInformation(WebDriver driver, String role, Common common, String domain, String mail_id, String mail_status, String Mode) {
        super(driver, role, common, domain, mail_id, mail_status, Mode);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void format() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
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
        if (common.authorized(role, common.roleList(4))) {
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
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // Delete old subject
            subject.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
            // length of subject in range 1-100
            int length_of_subject = RandomUtils.nextInt(100) + 1;
            subject.sendKeys(RandomStringUtils.randomAlphabetic(length_of_subject));
        }
    }

    public void insertion() {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // Delete old insertion
            insertion.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
            // length of insertion in range 1-10000
            int length_of_insertion = RandomUtils.nextInt(50) + 1;
            insertion.sendKeys(RandomStringUtils.randomAlphabetic(length_of_insertion));
        }
    }

    public void sendACopyToTheDistributor() {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // Send a copy to the distributor
            // 0: not change, 1: change
            int send_copy = RandomUtils.nextInt(2);
            if (send_copy == 1) {
                send_a_copy_to_the_distributor.click();
            }
        }
    }

    public void nextToAttachmentStep() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // Next to 添付ファイル_Step
            next_step_button.click();

            // check message in case register mail
            sleep(5000);

            // Check current tab
            // 0: Basic information, 1: attachmentStep, 2: Destination selection, 3: Final confirmation
            boolean check = wait.until(ExpectedConditions.visibilityOf(step_list.get(1))).isEnabled();
            soft.assertTrue(check, "[Failed] Can not next to attachmentStep from Basic information.");

            // show all assert result
            soft.assertAll();
        }
    }

    /*
        Validation function
    */

    // Distributor
    public void verifyErrorMessageWhenDistributorBlank() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.roleList(4))) {
            // waiting for loading distributor list
            sleep(3000);

            // click "x" button to clear current distributor
            clear_distributor.click();

            // check error message
            String text = wait.until(ExpectedConditions.visibilityOf(distributor_error)).getText();
            soft.assertEquals(text, "必須項目です。", "[Distributor] Message do not match");
            soft.assertAll();
        }
    }

    // Subject
    public void verifyErrorMessageWhenSubjectBlank() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            if (role.equals("Member")) {
                sleep(3000);
            }
            // leave subject blank
            wait.until(ExpectedConditions.elementToBeClickable(subject)).sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);

            // check error message
            String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
            soft.assertEquals(text, "必須項目です。", "[Failed][Subject][Blank] Message do not match");
            soft.assertAll();
        }
    }

    public void verifyErrorMessageWhenSubjectExceed100HalfWidthCharacters() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // wait and delete old subject
            sleep(3000);
            subject.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

            // input subject exceed 100 half width characters
            subject.sendKeys(RandomStringUtils.randomAlphabetic(101));

            // waiting for loading new message
            sleep(500);
            String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
            soft.assertEquals(text, "100文字以内で入力してください。", "[Failed][Subject][Exceed 100 half width] Message do not match");
            soft.assertAll();
        }
    }

    public void verifyErrorMessageWhenSubjectExceed100FullWidthCharacters() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // wait and delete old subject
            sleep(3000);
            subject.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

            // input subject exceed 100 full width characters
            subject.sendKeys(RandomStringUtils.random(101, 0x4e00, 0x4f80, true, false));

            // waiting for loading new message
            sleep(500);
            String text = wait.until(ExpectedConditions.visibilityOf(subject_error)).getText();
            soft.assertEquals(text, "100文字以内で入力してください。", "[Failed][Subject][Exceed 100 full width] Message do not match");
            soft.assertAll();
        }
    }

    public void verifyErrorMessageWhenSubjectExceed100CharactersMixHalfAndFullWidth() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // wait and delete old subject
            sleep(3000);
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
    }

    // Insertion
    public void verifyErrorMessageWhenInsertionBlank() {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // leave insertion blank
            insertion.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);

            // waiting for loading new message
            String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
            soft.assertEquals(text, "必須項目です。", "[Failed][Insertion][Blank] Message do not match");
            soft.assertAll();
        }
    }

    public void verifyErrorMessageWhenInsertionExceed10000HalfWidthCharacters() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // wait and delete old insertion
            sleep(3000);
            insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

            // input insertion exceed 10000 half width characters
            String insertion_text = RandomStringUtils.random(2000, true, false);
            insertion.sendKeys(insertion_text); //2000
            sleep(500);
            insertion.sendKeys(insertion_text); //4000
            sleep(500);
            insertion.sendKeys(insertion_text); //6000
            sleep(500);
            insertion.sendKeys(insertion_text); //8000
            sleep(500);
            insertion.sendKeys(insertion_text); //10000
            sleep(3000);
            insertion.sendKeys("@");
            // Scroll down
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            // waiting for loading new message
            sleep(1000);
            String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
            soft.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Failed][Insertion][Exceed 10000 half width] Message do not match");
            soft.assertAll();
        }
    }

    public void verifyErrorMessageWhenInsertionExceed5000FullWidthCharacters() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // minJpnCharCode: 0x4e00
            // maxJpnCharCode: 0x4f80
            // wait and delete old insertion
            sleep(3000);
            insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

            // input insertion exceed 5000 full width characters
            String insert_text = RandomStringUtils.random(2500, 0x4e00, 0x4f80, true, false);
            insertion.sendKeys(insert_text);
            insertion.sendKeys(insert_text);
            sleep(3000);
            insertion.sendKeys("@");

            // Scroll down
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            // waiting for loading new message
            sleep(500);
            String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
            soft.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Failed][Insertion][Exceed 5000 full width] Message do not match");
            soft.assertAll();
        }
    }

    public void verifyErrorMessageWhenInsertionExceed5000CharactersMixHalfAndFullWidth() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.roleList(num))) {
            // wait and delete old insertion
            sleep(3000);
            insertion.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

            // input insertion exceed 5000 characters (mix full and half width)
            String insertion_half = RandomStringUtils.randomAlphabetic(2500);
            String insertion_full = RandomStringUtils.random(2500, 0x4e00, 0x4f80, true, false);

            // minJpnCharCode: 0x4e00
            // maxJpnCharCode: 0x4f80
            insertion.sendKeys(insertion_half);
            insertion.sendKeys(insertion_full);
            sleep(3000);
            insertion.sendKeys("@");

            // Scroll down
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            // waiting for loading new message
            sleep(500);
            String text = wait.until(ExpectedConditions.visibilityOf(insertion_error)).getText();
            soft.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Failed][Insertion][Exceed 5000 mix] Message do not match");
            soft.assertAll();
        }
    }

    public void verifyDeleteButtonShouldBeEnable() {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.roleList(5))) {
            soft.assertTrue(delete_button.isEnabled(), "[Failed] Delete button is getting disable.");
            soft.assertAll();
        }
    }
}
