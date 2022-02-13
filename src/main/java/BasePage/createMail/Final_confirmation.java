package BasePage.createMail;

import Common.Common;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public Boolean check_time(WebDriver driver) {
        return driver.getCurrentUrl().equals("https://test.app.cmrb.jp/scheduledMails");
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

    public void open_delivery_time_setting_popup(WebDriver driver, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Click Time selection button
            driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>div:nth-child(4)>div>button")).click();
            sleep(1000);
        }
    }

    public void select_date(WebDriver driver, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Date
            driver.findElement(By.cssSelector("div.ant-col>div:nth-child(1)>div>div>div:nth-child(1)>div>div>input")).click();
            sleep(200);
            driver.findElement(By.cssSelector(date_element(driver))).click();
        }
    }

    public void select_time_and_select_again_when_time_incorrect(WebDriver driver, Actions key, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Time
            WebElement time = driver.findElement(By.cssSelector("div:nth-child(1) > div > div > div:nth-child(2) > div > div > input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly',0);", time);
            int hour = LocalTime.now().getHour();
            int min = LocalTime.now().getMinute();
            int new_min = min;
            int new_hour = hour;

            // If time incorrect, add 10 minutes and select again
            while (!check_time(driver)) {
                // Update time after add 10 minutes
                if (new_min > 59) {
                    new_min = new_min - 60;
                    new_hour++;
                }

                // check time in range 8:00 - 19:00
                if (new_hour < 8) {
                    new_hour = 8;
                    new_min = 0;
                }
                else if (new_hour > 19) {
                    new_hour = 8;
                    new_min = 0;
                    sleep(1000);
                    driver.findElement(By.cssSelector("div.ant-col>div:nth-child(1)>div>div>div:nth-child(1)>div>div>input")).click();
                    new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(next_date_element(driver)))).click();
                }
                time.click();
                for (int i = 0; i < 10; i++) {
                    sleep(100);
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
                sleep(100);
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(2)"))).click();

                sleep(1000);
                new_min += 10;

            }
        }
    }
}
