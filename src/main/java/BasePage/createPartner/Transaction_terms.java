package BasePage.createPartner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Transaction_terms {
    public void number_of_years_required_for_transactions(WebDriver driver) {
        // Number of years required for transactions in range 1-999
        int number_of_years_required_for_transactions = (int) (Math.random() * 998 + 1);
        driver.findElement(By.cssSelector("#establishment_year"))
                .sendKeys(Integer.toString(number_of_years_required_for_transactions));
    }

    public void capital_required_for_transactions(WebDriver driver) {
        // Capital required for transactions in range 0 - 9999999999999
        long capital_required_for_transactions = (long) (Math.random() * 9999999999999L);
        driver.findElement(By.cssSelector("#capital_man_yen_required_for_transactions"))
                .sendKeys(Long.toString(capital_required_for_transactions));
    }

    public void qualifications_required_for_transactions(WebDriver driver) {
        // Qualifications required for transactions
        // 1: P mark/ISMS , 2: Invoice registration company, 3: Worker dispatch business
        int qualifications_required_for_transactions = 3;//RandomUtils.nextInt(3) + 1;
        for (int i = 0; i < qualifications_required_for_transactions; i++) {
            if (i == 0) {
                driver.findElement(By.cssSelector("#p_mark_or_isms")).click();
            } else if (i == 1) {
                driver.findElement(By.cssSelector("#invoice_system")).click();
            } else {
                driver.findElement(By.cssSelector("#haken")).click();
            }
        }
    }

    public void finish_register_partner(WebDriver driver) {
        // Click 登 録 button
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.EditForm-button-3IRWY"))
                .click();
    }
}
