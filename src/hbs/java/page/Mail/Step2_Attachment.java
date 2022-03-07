package page.Mail;

import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Common.Common;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Step2_Attachment extends Delivered_Mail_Page {
    public static String current_date_time;
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
    int capacity;

    // Register Mode
    public Step2_Attachment(WebDriver driver, String role, Common common, String domain, int capacity, String Mode) {
        super(driver, role, common, domain, Mode);
        this.capacity = capacity;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    // Edit Mode
    public Step2_Attachment(WebDriver driver, String role, Common common, String domain, int capacity, String mail_id, String mail_status, String Mode) {
        super(driver, role, common, domain, mail_id, mail_status, Mode);
        this.capacity = capacity;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public void generate_test_file(int capacity, int number_of_files) throws IOException {
        // Generate test file
        // capacity: MB
        current_date_time = common.generate_date_time();
        for (int i = 0; i < number_of_files; i++) {
            new RandomAccessFile(System.getProperty("user.dir") + "\\Test_Data\\text" + i + "_" +current_date_time + ".txt", "rw")
                    .setLength((long) capacity * 1024 * 1024);
        }
    }

    public void upload_file() throws IOException {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            generate_test_file(capacity, 3);
            // Wait hide message
            wait.until(ExpectedConditions.invisibilityOf(message));

            // 0: upload 1 file, 1: upload multi file
            int upload = RandomUtils.nextInt(2);
            upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text0_" + current_date_time + ".txt");
            if (upload != 0) {
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text1_" + current_date_time + ".txt");
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text2_" + current_date_time + ".txt");
            }
            // Wait file have been uploaded
            wait.until(ExpectedConditions.visibilityOf(message));

            // Wait hide message
            wait.until(ExpectedConditions.invisibilityOf(message));
        }
    }

    public void next_to_destination_selection_step() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Next to 宛先選択_Step
            next_step_button.click();

            // Check current tab
            // 2: Destination selection step
            boolean check = step_list.get(2).isEnabled();
            soft.assertTrue(check, "[Failed] Can not next to Destination selection from Attachment.");
            soft.assertAll();

            // Waiting for destination selection tab loading
            sleep(2000);
        }
    }

    public void back_to_basic_information_step() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {

            // Back to Basic information step
            back_button.click();

            // Check current tab
            // 0: Basic information step
            boolean check = step_list.get(0).isEnabled();
            soft.assertTrue(check, "[Failed] Can not back to Basic information from Attachment.");
            soft.assertAll();

            // Waiting for basic information tab loading
            sleep(1000);
        }
    }

    public void upload_maximum_capacity_1_file() throws IOException {
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                generate_test_file(capacity, 1);
                // Waiting for hide previous message
                wait.until(ExpectedConditions.invisibilityOf(message));
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text0_" + current_date_time + ".txt");
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                soft.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload file with capacity: "
                        + capacity + "MB.\n Actual message: " + text + "\n Expect message: contains ファイルがアップロードされました");
                soft.assertAll();
            }
        }
    }

    public void upload_maximum_capacity_multi_file() throws IOException {
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                generate_test_file(capacity, 3);
                // Waiting for hide previous message
                wait.until(ExpectedConditions.invisibilityOf(message));
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text0_" + current_date_time + ".txt");
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text1_" + current_date_time + ".txt");
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text2_" + current_date_time + ".txt");
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                soft.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload multi file with capacity = "
                        + capacity + "MB.\n Actual message: " + text + "\n Expect message: contains ファイルがアップロードされました");
                soft.assertAll();
            }
        }
    }

    public void upload_exceed_maximum_capacity_1_file() throws IOException {
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                generate_test_file(capacity + 1, 1);
                // Waiting for hid previous message
                wait.until(ExpectedConditions.invisibilityOf(message));
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text0_" + current_date_time + ".txt");
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                soft.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match."
                        + "\n Actual message: " + text + "\n Expect message: contains を超えるメールを配信することはできません");
                soft.assertAll();
            }
        }
    }

    public void upload_exceed_maximum_capacity_multi_file() throws IOException {
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                generate_test_file(capacity + 1, 3);
                // Waiting for hid previous message
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text0_" + current_date_time + ".txt");
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text1_" + current_date_time + ".txt");
                upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text2_" + current_date_time + ".txt");
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                soft.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match."
                        + "\n Actual message: " + text + "\n Expect message: contains を超えるメールを配信することはできません");
                soft.assertAll();
            }
        }
    }

    public void upload_maximum_capacity_11_files() throws IOException {
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                generate_test_file(capacity, 11);
                // Waiting for hid previous message
                for (int i = 0; i < 11; i++) {
                    upload_file.sendKeys(System.getProperty("user.dir") + "\\Test_Data\\text" + i + current_date_time + ".txt");
                }
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                soft.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match."
                        + "\n Actual message: " + text + "\n Expect message: contains を超えるメールを配信することはできません");
                soft.assertAll();
            }
        }
    }
}
