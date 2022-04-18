package page.Mail;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Common.Common;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Step2_Attachment extends Delivered_Mail_Page {
    public static String current_date_time;
    @FindBy(css = "span > input[type='file']")
    WebElement upload_file;
    @FindBy(css = "div:nth-child(4)>div>button")
    WebElement next_step_button;
    @FindBy(css = "div.ant-steps-item-icon")
    List<WebElement> step_list;
    @FindBy(css = "div:nth-child(1)>div.ant-col.ant-col-24 > div > div:nth-child(1) > div > button")
    WebElement back_button;
    @FindBy(css = "div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)")
    WebElement message;
    @FindBy(css = "div.ant-upload-list-item-done")
    List<WebElement> number_of_file;
    @FindBy(css = "button[title='Remove file']")
    List<WebElement> delete_upload_file;
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
            new RandomAccessFile(System.getProperty("user.dir") + "/Test_Data/text" + i + "_" + current_date_time + ".txt", "rw")
                    .setLength(((long) capacity * 1024 * 1024 - number_of_files) / number_of_files);
        }
    }

    public void upload_file() throws IOException {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {

//                generate_test_file(capacity - 1, 1);
                // Waiting for hide previous message
                if (Mode.equals("Edit")) {
                    while (number_of_file.size() != 0) {
                        key.moveToElement(delete_upload_file.get(0)).click().build().perform();
                        wait.until(ExpectedConditions.visibilityOf(message));
                        wait.until(ExpectedConditions.invisibilityOf(message));
                    }
                }
                if (capacity <= 2) {
                    upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/2MB_upload_process.txt");
                } else {
                    upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/10MB_upload_process.txt");
                }

                // Wait file have been uploaded
                wait.until(ExpectedConditions.visibilityOf(message));

                // Wait hide message
                wait.until(ExpectedConditions.invisibilityOf(message));
            }
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
//                generate_test_file(capacity, 1);
                // Waiting for hide previous message
                if (Mode.equals("Edit")) {
                    while (number_of_file.size() != 0) {
                        key.moveToElement(delete_upload_file.get(0)).click().build().perform();
                        wait.until(ExpectedConditions.visibilityOf(message));
                        wait.until(ExpectedConditions.invisibilityOf(message));
                    }
                }
                if (capacity <= 2) {
                    String path = Paths.get(System.getProperty("user.dir") + "/Test_Data/2MB_upload_maximum_1_file.txt").toString();
                    System.out.println(path);
                    upload_file.sendKeys(path);
                } else {
                    upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/10MB_upload_maximum_1_file.txt");
                }
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                soft.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload file with capacity: "
                        + capacity + "MB.\n Actual message: " + text + "\n Expect message: contains ファイルがアップロードされました");
                soft.assertAll();
            }
        }
    }

    public void upload_maximum_capacity_multi_file() {
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
//                generate_test_file(capacity, 3);
                // Waiting for hide previous message
                if (Mode.equals("Edit")) {
                    while (number_of_file.size() != 0) {
                        key.moveToElement(delete_upload_file.get(0)).click().build().perform();
                        wait.until(ExpectedConditions.visibilityOf(message));
                        wait.until(ExpectedConditions.invisibilityOf(message));
                    }
                }
                String text = "";
                for (int i = 1; i <= 3; i++) {
                    if (capacity <= 2) {
                        ((RemoteWebElement) upload_file).setFileDetector(new LocalFileDetector());
                        upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/2MB_upload_maximum_multi_file_0" + i + ".txt");
                    } else {
                        upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/10MB_upload_maximum_multi_file_0" + i + ".txt");
                    }
                    wait.until(ExpectedConditions.visibilityOf(message));
                    if (i == 2) {
                        text = message.getText();
                    }
                    wait.until(ExpectedConditions.invisibilityOf(message));
                }
                soft.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload multi file with capacity = "
                        + capacity + "MB.\n Actual message: " + text + "\n Expect message: contains ファイルがアップロードされました");
                soft.assertAll();
            }
        }
    }

    public void upload_exceed_maximum_capacity_1_file() throws IOException {
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
//                generate_test_file(capacity + 1, 1);
                if (Mode.equals("Edit")) {
                    while (number_of_file.size() != 0) {
                        key.moveToElement(delete_upload_file.get(0)).click().build().perform();
                        wait.until(ExpectedConditions.visibilityOf(message));
                        wait.until(ExpectedConditions.invisibilityOf(message));
                    }
                }
                // Waiting for hid previous message
                wait.until(ExpectedConditions.invisibilityOf(message));
                if (capacity <= 2) {
                    upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/2MB_upload_exceed_maximum_1_file.txt");
                } else {
                    upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/10MB_upload_exceed_maximum_1_file.txt");
                }
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
//                generate_test_file(capacity + 1, 3);
                if (Mode.equals("Edit")) {
                    while (number_of_file.size() != 0) {
                        key.moveToElement(delete_upload_file.get(0)).click().build().perform();
                        wait.until(ExpectedConditions.visibilityOf(message));
                        wait.until(ExpectedConditions.invisibilityOf(message));
                    }
                }
                String text = "";
                for (int i = 1; i <= 3; i++) {
                    if (capacity <= 2) {
                        upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/2MB_upload_exceed_maximum_multi_file_0" + i + ".txt");
                    } else {
                        upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/10MB_upload_exceed_maximum_multi_file_0" + i + ".txt");
                    }
                    wait.until(ExpectedConditions.visibilityOf(message));
                    if (i == 2) {
                        text = message.getText();
                    }
                    wait.until(ExpectedConditions.invisibilityOf(message));
                }
                soft.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match."
                        + "\n Actual message: " + text + "\n Expect message: contains を超えるメールを配信することはできません");
                soft.assertAll();
            }
        }
    }

    public void upload_exceed_11_files() throws IOException {
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                // generate test data
//                generate_test_file(capacity, 11);

                if (Mode.equals("Edit")) {
                    while (number_of_file.size() != 0) {
                        key.moveToElement(delete_upload_file.get(0)).click().build().perform();
                        wait.until(ExpectedConditions.visibilityOf(message));
                        wait.until(ExpectedConditions.invisibilityOf(message));
                    }
                }

                // Waiting for hid previous message
                String text = "";
                for (int i = 1; i <= 11; i++) {
                    if (capacity <= 2) {
                        upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/2MB_upload_11_file_0" + i + ".txt");
                    } else {
                        upload_file.sendKeys(System.getProperty("user.dir") + "/Test_Data/10MB_upload_11_file_0" + i + ".txt");
                    }
                    wait.until(ExpectedConditions.visibilityOf(message));
                    if (i == 11) {
                        text = message.getText();
                    }
                    wait.until(ExpectedConditions.invisibilityOf(message));
                }
                soft.assertEquals(text, "添付可能なファイル数は10件以下です。", "[Failed] Message do not match.");
                soft.assertAll();
            }
        }
    }
}
