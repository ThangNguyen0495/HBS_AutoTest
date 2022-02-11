package model.deliveryMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class findDate {
    public static int col1;
    public static int row1;

    public Boolean check_date(int col, int row, WebDriver driver) {
        String date_check = driver.findElement(By.cssSelector("table.ant-picker-content>tbody>tr:nth-child(" + Integer.toString(row) + ")>td:nth-child(" + Integer.toString(col) + ")"))
                .getAttribute("class");
        if (date_check.contains("disable")) {
            return true;
        } else {
            return false;
        }
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
        return "table.ant-picker-content>tbody>tr:nth-child(" + Integer.toString(row) + ")>td:nth-child(" + Integer.toString(col) + ")";
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
        return "table.ant-picker-content>tbody>tr:nth-child(" + Integer.toString(row) + ")>td:nth-child(" + Integer.toString(col) + ")";
    }

    public Boolean check_time(WebDriver driver) {
        if (driver.getCurrentUrl().equals("https://test.app.cmrb.jp/scheduledMails")) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean add_day(int min, int hour) {

        if (hour < 8) {
            min = 0;
            hour = 8;
            return false;
        } else if (hour > 18) {
            min = 0;
            hour = 8;
            return true;
        } else {
            return false;
        }
    }

    public String time_str(int min, int hour){
        String min_str;
        String hour_str;
        if (min < 10) {
            min_str = "0" + Integer.toString(min);
        } else {
            min_str = Integer.toString(min);
        }
        if (hour < 10) {
            hour_str = "0" + Integer.toString(hour);
        } else {
            hour_str = Integer.toString(hour);
        }
        return hour_str + ":" + min_str;
    }
}
