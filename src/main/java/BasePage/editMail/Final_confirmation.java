package BasePage.editMail;

import Common.Common;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalTime;

import static java.lang.Thread.sleep;

public class Final_confirmation {
    public static int col1;
    public static int row1;

    public Boolean check_date(int col, int row, WebDriver driver) {
        String date_check = driver.findElement(By.cssSelector("table.ant-picker-content>tbody>tr:nth-child(" + row + ")>td:nth-child(" + col + ")"))
                .getAttribute("class");
        return date_check.contains("disable");
    }

    public String date_element(WebDriver driver) {
        int col = 0;
        int row = 1;

        do {
            col++;
            if (col > 7) {
                row++;
                col = 1;
            }
        } while (check_date(col, row, driver));
        col1 = col;
        row1 = row;
        return "table.ant-picker-content>tbody>tr:nth-child(" + row + ")>td:nth-child(" + col + ")";
    }

    public String next_date_element(WebDriver driver) {
        int col = col1;
        int row = row1;

        do {
            col++;
            if (col > 7) {
                row++;
                col = 1;
            }
        } while (check_date(col, row, driver));
        col1 = col;
        row1 = row;
        return "table.ant-picker-content>tbody>tr:nth-child(" + row + ")>td:nth-child(" + col + ")";
    }

    public Boolean check_time(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Waiting for loading mail list page
        sleep(1000);
        return driver.getCurrentUrl().equals(url_mail_list);
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

    public void open_delivery_time_setting_popup(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Click Time selection button
            driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>div:nth-child(4)>div>button")).click();
        }
    }

    public void select_date(WebDriver driver, String role, Common cm, String element) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Date
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-col>div:nth-child(1)>div>div>div:nth-child(1)>div>div>input")))
                            .click();

            if (element.equals("")) {
                driver.findElement(By.cssSelector(date_element(driver))).click();
            } else {
                driver.findElement(By.cssSelector(element)).click();
            }
        }
    }

    public void select_time_and_select_again_when_time_incorrect(WebDriver driver, Actions key, String role, Common cm, String url_mail_list) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Time
            WebElement time = driver.findElement(By.cssSelector("div:nth-child(1) > div > div > div:nth-child(2) > div > div > input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly',0);", time);
            int new_min = LocalTime.now().getMinute();
            int new_hour = LocalTime.now().getHour();

            // If time incorrect, add 10 minutes and select again
            while (!check_time(driver, url_mail_list)) {
                // Update time after add 10 minutes
                if (new_min > 59) {
                    new_min = new_min - 60;
                    new_hour++;
                }

                // check time in range 8:00 - 19:00
                if (new_hour < 8) {
                    new_hour = 8;
                    new_min = 0;
                } else if (new_hour > 19) {
                    new_hour = 8;
                    new_min = 0;
                    select_date(driver, role, cm, next_date_element(driver));
                }
                time.click();
                for (int i = 0; i < 10; i++) {
                    key.sendKeys(Keys.BACK_SPACE).perform();
                }
                // Select time
                time.sendKeys(time_str(new_min, new_hour));
                // Click 決 定 button
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.ant-picker-ok>button"))).click();

                //** 配信時刻設定 Popup **//
                // Click OK button
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-footer>button"))).click();

                //** この配信メールを配信メール予約登録しますか？ Popup **//
                // Click OK button
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(2)"))).click();

                new_min += 10;

            }
        }
    }

    public void do_you_want_to_delete_this_delivered_email_OK(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click delete button
        driver.findElement(By.cssSelector("button.ant-btn-danger")).click();

        // Click OK button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(2)")))
                        .click();

        // Waiting for loading mail list page
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not delete delivered email");
    }

    public void do_you_want_to_delete_this_delivered_email_Cancel(WebDriver driver) throws InterruptedException {
        // Click delete button
        driver.findElement(By.cssSelector("button.ant-btn-danger")).click();

        // Click delete button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(1)")))
                        .click();

        // Waiting for close popup
        sleep(500);

        // Check popup close
        boolean check = true;
        try {
            driver.findElement(By.cssSelector("div:nth-child(3) > div > div:nth-child(1) > button")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        Assert.assertFalse(check, "[Failed] Can not close delete delivered email popup");
    }

    public void make_a_copy(WebDriver driver, Actions key, String url_mail_list) throws InterruptedException {
        // Click make a copy button
        WebElement make_a_copy = driver.findElement(By.cssSelector("div:nth-child(1)>button.ant-btn-sm"));
        key.moveToElement(make_a_copy).click().build().perform();

        // Waiting for loading mail list page
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not make a copy of delivered email.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_OK(WebDriver driver, Actions key, String url_mail_list) throws InterruptedException {
        // Click save as draft button
        WebElement save_as_draft = driver.findElement(By.cssSelector("div.ant-col:nth-child(2)>button.ant-btn-sm"));
        key.moveToElement(save_as_draft).click().build().perform();

        // click OK button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(2)"))).click();

        // Waiting for loading mail list page
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not save delivered as draft.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel(WebDriver driver, Actions key) throws InterruptedException {
        // Click save as draft button
        WebElement save_as_draft = driver.findElement(By.cssSelector("div.ant-col:nth-child(2)>button.ant-btn-sm"));
        key.moveToElement(save_as_draft).click().build().perform();

        // Click cancel button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(1)")))
                        .click();

        // Waiting for close popup
        sleep(500);

        // Check popup close
        boolean check = true;
        try {
            driver.findElement(By.cssSelector("div:nth-child(3) > div > div:nth-child(1) > button")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        Assert.assertFalse(check, "[Failed] Can not close save delivered as draft popup.");
    }

    public void back_to_destination_selection_step(WebDriver driver, Actions key, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Back to Destination selection step
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1)>div.ant-col.ant-col-24 > div > div:nth-child(1) > div > button")))
                    .click();

            sleep(500);

            // Check current tab
            boolean check = driver.findElement(By.cssSelector("div:nth-child(3)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check,"[Failed] Can not back to Destination selection from Final confirmation.");

            // back to Final confirmation from Destination selection
            driver.findElement(By.cssSelector("div:nth-child(4) > div > button")).click();
            sleep(500);

            // back to Destination selection from Final confirmation
            WebElement back_to_destination_selection = driver.findElement(By.cssSelector("div:nth-child(3)>div>div.ant-steps-item-icon"));
            key.moveToElement(back_to_destination_selection).click().build().perform();
            sleep(500);

            // Check current tab
            boolean check1 = driver.findElement(By.cssSelector("div:nth-child(3)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check1,"[Failed] Can not back to Destination selection from Final confirmation.");

        }
    }

    public void back_to_attachment_step(WebDriver driver, Actions key, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Back to Attachment step
            WebElement back_to_attachment = driver.findElement(By.cssSelector("div:nth-child(2)>div>div.ant-steps-item-icon"));
            key.moveToElement(back_to_attachment).click().build().perform();

            sleep(500);
            // Check current tab
            boolean check = driver.findElement(By.cssSelector("div:nth-child(2)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check,"[Failed] Can not back to Attachment from Final confirmation.");
        }
    }

    public void back_to_basic_information_step(WebDriver driver, Actions key, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Back to Basic information step
            WebElement back_to_basic_information = driver.findElement(By.cssSelector("div:nth-child(1)>div>div.ant-steps-item-icon"));
            key.moveToElement(back_to_basic_information).click().build().perform();

            sleep(500);
            // Check current tab
            boolean check = driver.findElement(By.cssSelector("div:nth-child(1)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check,"[Failed] Can not back to Basic information from Final confirmation.");
        }
    }
}
