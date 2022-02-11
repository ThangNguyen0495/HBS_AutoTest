package CreateDummyData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class findDate {
    public static int col1;
    public static int row1;

    public Boolean check_date(int col, int row,WebDriver driver) {
        String date_check = driver.findElement(By.cssSelector("table.ant-picker-content>tbody>tr:nth-child(" + Integer.toString(row) + ")>td:nth-child("+ Integer.toString(col) + ")"))
                .getAttribute("class");
        if (date_check.contains("disable")) {
            return true;
        }
        else {
            return false;
        }
    }

    public String date_element(WebDriver driver) {
        int col = 0;
        int row = 1;

        do {
            col ++;
            if (col > 7) {
                row ++;
                col = 1;
            }
        } while (check_date(col,row, driver));
        col1 = col;
        row1 = row;
        return "table.ant-picker-content>tbody>tr:nth-child(" + Integer.toString(row) + ")>td:nth-child("+ Integer.toString(col) + ")";
    }

    public String next_date_element(WebDriver driver){
        int col = col1;
        int row = row1;

        do {
            col ++;
            if (col > 7) {
                row ++;
                col = 1;
            }
        } while (check_date(col,row, driver));
        col1 = col;
        row1 = row;
        return "table.ant-picker-content>tbody>tr:nth-child(" + Integer.toString(row) + ")>td:nth-child("+ Integer.toString(col) + ")";
    }
}
