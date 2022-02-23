package BasePage.editMail;

import Common.Common;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Attachment {
    @FindBy(css = "span.ant-upload.ant-upload-btn>input")
    WebElement upload_file;

    @FindBy(css = "div:nth-child(4)>div>button")
    WebElement next_step_button;

    @FindBy(css = "div.ant-steps-item-icon")
    List<WebElement> step_list;

    @FindBy(css = "div:nth-child(1)>div.ant-col.ant-col-24 > div > div:nth-child(1) > div > button")
    WebElement back_button;

    @FindBy(css = "div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)")
    WebElement message;

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

    WebDriver driver;
    WebDriverWait wait;
    String role;
    Common cm;
    int capacity;

    String url_mail_list;

    public Attachment(WebDriver driver, String role, Common cm, int capacity, String url_mail_list) {
        this.driver = driver;
        this.capacity = capacity;
        this.role = role;
        this.cm = cm;
        this.url_mail_list = url_mail_list;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public void generate_test_file() throws IOException {
        // Generate test file
        // capacity: MB
        new RandomAccessFile("text.txt", "rw").setLength((long) capacity * 1024 * 1024);
        new RandomAccessFile("excel.xlsx", "rw").setLength((long) capacity * 1024 * 1024);
        new RandomAccessFile("word.docx", "rw").setLength((long) capacity * 1024 * 1024);
        new RandomAccessFile("pdf.pdf", "rw").setLength((long) capacity * 1024 * 1024);
    }

    public void upload_file() {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Wait hide message
            wait.until(ExpectedConditions.invisibilityOf(message));

            // 0: upload 1 file, 1: upload multi file
            int upload = RandomUtils.nextInt(2);
            upload_file.sendKeys(System.getProperty("user.dir") + "\\text.txt");
            if (upload != 0) {
                upload_file.sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
                upload_file.sendKeys(System.getProperty("user.dir") + "\\word.docx");
                upload_file.sendKeys(System.getProperty("user.dir") + "\\pdf.pdf");
            }
            // Wait file have been uploaded
            wait.until(ExpectedConditions.visibilityOf(message));

            // Wait hide message
            wait.until(ExpectedConditions.invisibilityOf(message));
        }
    }

    public void next_to_destination_selection_step() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Next to 宛先選択_Step
            next_step_button.click();

            // Check current tab
            // 2: Destination selection step
            boolean check = step_list.get(2).isEnabled();
            Assert.assertTrue(check, "[Failed] Can not next to Destination selection from Attachment.");

            // Waiting for destination selection tab loading
            sleep(1000);
        }
    }

    public void back_to_basic_information_step() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {

            // Back to Basic information step
            back_button.click();

            // Check current tab
            // 0: Basic information step
            boolean check = step_list.get(0).isEnabled();
            Assert.assertTrue(check, "[Failed] Can not back to Basic information from Attachment.");

            // Waiting for basic information tab loading
            sleep(1000);
        }
    }

    // Update button
    public void update_delivery_with_valid_data() throws InterruptedException {
        update_button.click();
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Update] Can not update delivered email.");
    }

    public void upload_maximum_capacity_1_file() throws IOException {
        generate_test_file();
        // Waiting for hide previous message
        wait.until(ExpectedConditions.invisibilityOf(message));
        upload_file.sendKeys(System.getProperty("user.dir") + "\\text.txt");
        String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        Assert.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload file with capacity: "
                + capacity + "MB.\n Actual message: " + text + "\n Expect message: contains ファイルがアップロードされました");
    }

    public void upload_maximum_capacity_multi_file() throws IOException {
        generate_test_file();
        // Waiting for hid previous message
        wait.until(ExpectedConditions.invisibilityOf(message));
        upload_file.sendKeys(System.getProperty("user.dir") + "\\text.txt");
        upload_file.sendKeys(System.getProperty("user.dir") + "\\word.docx");
        upload_file.sendKeys(System.getProperty("user.dir") + "\\pdf.pdf");
        upload_file.sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        Assert.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload multi file with capacity = "
                + capacity + "MB.\n Actual message: " + text + "\n Expect message: contains ファイルがアップロードされました");
    }

    public void upload_exceed_maximum_capacity_1_file() throws IOException {
        generate_test_file();
        // Waiting for hid previous message
        wait.until(ExpectedConditions.invisibilityOf(message));
        upload_file.sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        Assert.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match."
                + "\n Actual message: " + text + "\n Expect message: contains を超えるメールを配信することはできません");
    }

    public void upload_exceed_maximum_capacity_multi_file() throws IOException {
        generate_test_file();
        // Waiting for hid previous message
        wait.until(ExpectedConditions.invisibilityOf(message));
        upload_file.sendKeys(System.getProperty("user.dir") + "\\text.txt");
        upload_file.sendKeys(System.getProperty("user.dir") + "\\word.docx");
        upload_file.sendKeys(System.getProperty("user.dir") + "\\pdf.pdf");
        upload_file.sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
        Assert.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match."
                + "\n Actual message: " + text + "\n Expect message: contains を超えるメールを配信することはできません");
    }

    public void do_you_want_to_delete_this_delivered_email_OK() throws InterruptedException {
        // Click delete button
        delete_button.click();

        // Click OK button
        wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

        //Waiting for mail list page loading
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not delete delivered email");
    }

    public void do_you_want_to_delete_this_delivered_email_Cancel() throws InterruptedException {
        // Click delete button
        delete_button.click();

        // Click Cancel button
        wait.until(ExpectedConditions.elementToBeClickable(cancel_button)).click();

        // Waiting for close popup
        sleep(500);

        // Check popup close
        boolean check = true;
        try {
            cancel_button.click();
        } catch (NoSuchElementException ex) {
            check = false;
        }

        Assert.assertFalse(check, "[Failed] Can not close delete delivered email popup.");
    }

    public void make_a_copy() throws InterruptedException {
        // Click make a copy button
        make_a_copy_button.click();

        // Waiting for mail list page loading
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not make a copy of delivered email.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_OK() throws InterruptedException {
        // Click save as draft button
        save_as_draft_button.click();

        // Click OK button
        wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

        // Waiting for mail list page loading
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not save delivered as draft.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel() throws InterruptedException {
        // Click save as draft button
        save_as_draft_button.click();

        // Click cancel button
        wait.until(ExpectedConditions.elementToBeClickable(cancel_button)).click();

        // Waiting for close popup
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
