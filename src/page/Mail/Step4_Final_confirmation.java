package page.Mail;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.Common.Common;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static java.lang.Thread.sleep;
import static utilities.Link_and_Path.HBS.mail_list_path;

public class Step4_Final_confirmation extends Delivered_Mail_Page {
    public int current_id;
    @FindBy(css = "table.ant-picker-content>tbody>tr>td")
    List<WebElement> calendar;
    @FindBy(css = "span.ant-picker-next-icon")
    WebElement next_month;
    @FindBy(css = "div.ant-col>div:nth-child(1)>div>div>div:nth-child(1)>div>div>input")
    WebElement date;
    @FindBy(css = "div.ant-col.ant-col-24>div>div:nth-child(4)>div>button")
    WebElement time_selection;
    @FindBy(css = "div:nth-child(1) > div > div > div:nth-child(2) > div > div > input")
    WebElement time;
    @FindBy(css = "li.ant-picker-ok>button")
    WebElement decisive_button;
    @FindBy(css = "div.ant-modal-footer>button")
    WebElement ok_button_delivery_time_setting;
    @FindBy(css = "div.ant-modal-confirm-btns>button:nth-child(2)")
    WebElement ok_button_confirm_select_date_time;
    @FindBy(css = "div:nth-child(1)>div.ant-col.ant-col-24 > div > div:nth-child(1) > div > button")
    WebElement back_button;
    @FindBy(css = "div.ant-steps-item-icon")
    List<WebElement> step_list;
    @FindBy(css = "div:nth-child(4) > div > button")
    WebElement next_step_button;

    public Step4_Final_confirmation(WebDriver driver, String role, Common common, String domain, String Mode) {
        super(driver, role, common, domain, Mode);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public Step4_Final_confirmation(WebDriver driver, String role, Common common, String domain, String mail_id, String mail_status, String Mode) {
        super(driver, role, common, domain, mail_id, mail_status, Mode);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public Boolean check_date(int id) {
        return calendar.get(id).getAttribute("class").contains("disable");
    }

    public int date_id() {
        int id = 0;
        while (check_date(id)) {
            id++;
            // Calendar have 42 days (id: 0->41)
            if (id > 41) {
                id = 0;
                next_month.click();
            }
        }
        return id;
    }

    public int next_date_id() {
        int id;
        id = current_id + 1;
        if (id > 41) {
            id = 0;
            next_month.click();
        } else {
            while (check_date(id)) {
                id++;
                if (id > 41) {
                    id = 0;
                    next_month.click();
                }
            }
        }
        return id;
    }

    public Boolean check_time() throws InterruptedException {
        // Waiting for loading mail list page
        sleep(1000);
        return driver.getCurrentUrl().equals(domain + mail_list_path);
    }

    public String time_str(int min, int hour) {
        String min_str;
        String hour_str;
        if (min < 10) {
            min_str = "0" + min;
        } else {
            min_str = Integer.toString(min);
        }
        if (hour < 10) {
            hour_str = "0" + hour;
        } else {
            hour_str = Integer.toString(hour);
        }
        return hour_str + ":" + min_str;
    }

    public void open_delivery_time_setting_popup() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                // Click Time selection button
                sleep(3000);
                time_selection.click();
            } else {
                // Click Time selection button
                sleep(3000);
                boolean check = time_selection.isEnabled();
                soft.assertFalse(check, "[Time selection][Failed] Time selection button is not disabled.");
                soft.assertAll();
            }
        }
    }

    public void select_date() {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                // Date
                wait.until(ExpectedConditions.visibilityOf(date)).click();
                current_id = date_id();
                wait.until(ExpectedConditions.elementToBeClickable(calendar.get(current_id))).click();
            }
        }
    }

    public void select_time_and_select_again_when_time_incorrect() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (common.authorized(role, common.role_list(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                // Time
                ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly',0);", time);
                int new_min = LocalTime.now().getMinute();
                int new_hour = LocalTime.now().getHour();

                // If time incorrect, add 10 minutes and select again
                while (!check_time()) {
                    // Update time after add 10 minutes
                    if (new_min > 59) {
                        new_min = new_min - 60;
                        new_hour++;
                    }

                    // check time in range 8:00 - 19:00
                    if (new_hour < 8) {
                        new_hour = 8;
                        new_min = 0;
                    } else if (new_hour >= 19) {
                        new_hour = 8;
                        new_min = 0;
                        wait.until(ExpectedConditions.visibilityOf(date)).click();
                        current_id = next_date_id();
                        wait.until(ExpectedConditions.elementToBeClickable(calendar.get(current_id)));
                        key.moveToElement(calendar.get(current_id)).click().build().perform();
                    }
                    time.click();
                    for (int i = 0; i < 10; i++) {
                        key.sendKeys(Keys.BACK_SPACE).perform();
                    }
                    // Select time
                    time.sendKeys(time_str(new_min, new_hour));
                    // Click 決 定 button
//                key.sendKeys(Keys.ENTER).perform();
                    wait.until(ExpectedConditions.elementToBeClickable(decisive_button)).click();

                    //** 配信時刻設定 Popup **//
                    // Click OK button
                    wait.until(ExpectedConditions.elementToBeClickable(ok_button_delivery_time_setting)).click();

                    //** この配信メールを配信メール予約登録しますか？ Popup **//
                    // Click OK button
                    wait.until(ExpectedConditions.elementToBeClickable(ok_button_confirm_select_date_time)).click();

                    new_min += 10;
                }
            }
        }
    }

    public void back_to_destination_selection_step() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Back to Destination selection step
            wait.until(ExpectedConditions.elementToBeClickable(back_button)).click();

            sleep(500);

            // Check current tab
            Assert.assertTrue(step_list.get(2).isEnabled(), "[Failed] Can not back to Destination selection from Final confirmation.");

            // back to Final confirmation from Destination selection
            next_step_button.click();
            sleep(500);

            // back to Destination selection from Final confirmation
            key.moveToElement(step_list.get(2)).click().build().perform();
            sleep(500);

            // Check current tab
            Assert.assertTrue(step_list.get(2).isEnabled(), "[Failed] Can not back to Destination selection from Final confirmation.");

        }
    }

    public void back_to_attachment_step() throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        int num;
        if (Mode.equals("Edit")) {
            num = 6;
        } else {
            num = 5;
        }
        if (common.authorized(role, common.role_list(num))) {
            // Back to Attachment step
            key.moveToElement(step_list.get(1)).click().build().perform();

            sleep(500);
            // Check current tab
            Assert.assertTrue(step_list.get(1).isEnabled(), "[Failed] Can not back to Attachment from Final confirmation.");
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
            key.moveToElement(step_list.get(0)).click().build().perform();

            sleep(500);
            // Check current tab
            Assert.assertTrue(step_list.get(0).isEnabled(), "[Failed] Can not back to Basic information from Final confirmation.");
        }
    }
}
