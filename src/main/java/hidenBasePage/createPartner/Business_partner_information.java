package hidenBasePage.createPartner;

import Common.Common;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Business_partner_information {

    public void block_list(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person
        if (cm.authorized(role, cm.role_list(3))) {
            // Block list
            // 0: Do not block, 1: Block
            int block_list = RandomUtils.nextInt(2);
            if (block_list == 1) {
                driver.findElement(By.cssSelector("#is_blacklisted"))
                        .click();
            }
        }
    }

    public void corporate_number(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Corporate number
            // Corporate number in range 0 - 9999999999999
            long corporate_number = (long) (Math.random() * 9999999999999L);
            driver.findElement(By.cssSelector("#corporate_number"))
                    .sendKeys(Long.toString(corporate_number));
        }
    }

    public void customer_name(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Customer name
            // length of customer name in range 1-100
            int length_of_customer_name = RandomUtils.nextInt(100) + 1;
            String customer_name = RandomStringUtils.randomAlphabetic(length_of_customer_name);
            driver.findElement(By.cssSelector("#name"))
                    .sendKeys(customer_name);
        }
    }

    public void account_status(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Account status
            // 1:Prospect, 2:Approached, 3:Information exchanged, 4:Contract record available
            int account_status = RandomUtils.nextInt(4) + 1;
            driver.findElement(By.cssSelector("#category > label:nth-child(" + account_status + ") > span > input"))
                    .click();
        }
    }

    public void customer_evaluation(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Customer evaluation
            // 1: one star, 2: two stars, 3: three stars, 4: four stars, 5: five stars
            int customer_evaluation = RandomUtils.nextInt(5) + 1;
            driver.findElement(By.cssSelector("li.ant-rate-star:nth-child(" + customer_evaluation + ")")).click();
        }
    }

    public void country_of_citizenship(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Country of Citizenship
            // 1:Japan , 2:Korea, 3:China, 4:Others
            int country_of_citizenship = RandomUtils.nextInt(4) + 1;
            driver.findElement(By.cssSelector("#country > label:nth-child(" + country_of_citizenship + ") > span > input")).click();
        }
    }

    public void establishment_date(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // generate establishment date with YYYY-MM format
            int month = RandomUtils.nextInt(12) + 1; // month in range 01-12
            int year = (int) (Math.random() * 223 + 1800); // year in range 1800-2022
            String establishment_date;
            if (month < 10) {
                establishment_date = year + "-0" + month;
            } else {
                establishment_date = year + "-" + month;
            }
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#establishment_date"))).sendKeys(establishment_date);
            Actions key = new Actions(driver);
            key.sendKeys(Keys.ENTER).perform();
        }
    }

    public void fiscal_year(WebDriver driver, Actions key, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Fiscal year
            // 1: Jan, 2: Feb, 3: Mar, 4: Apr, 5: May, 6: Jun, 7: Jul, 8: Aug, 9: Sept, 10: Oct, 11: Nov, 12: Dec
            int fiscal_year = RandomUtils.nextInt(12) + 1;
            driver.findElement(By.cssSelector("#settlement_month")).click();
            for (int i = 0; i < fiscal_year; i++) {
                key.sendKeys(Keys.DOWN).perform();
            }
            key.sendKeys(Keys.ENTER).perform();
        }
    }

    public void address(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // total length of address and building in range 2-100
            int length_of_address = RandomUtils.nextInt(99) + 1;
            int length_of_building = RandomUtils.nextInt(100 - length_of_address) + 1;
            String address = RandomStringUtils.randomAlphabetic(length_of_address);
            String building = RandomStringUtils.randomAlphabetic(length_of_building);
            driver.findElement(By.cssSelector("#address")).sendKeys(address);
            driver.findElement(By.cssSelector("#building")).sendKeys(building);
        }
    }

    public void bpi_tel(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // TEL
            // total length of tel1, tel2, tel3 in range 3-15
            int length_of_tel1 = RandomUtils.nextInt(13) + 1;
            int length_of_tel2 = RandomUtils.nextInt(14 - length_of_tel1) + 1;
            int length_of_tel3 = RandomUtils.nextInt(15 - length_of_tel1 - length_of_tel2) + 1;
            long tel1 = (long) (Math.random() * (Math.pow(10, length_of_tel1)));
            long tel2 = (long) (Math.random() * (Math.pow(10, length_of_tel2)));
            long tel3 = (long) (Math.random() * (Math.pow(10, length_of_tel3)));
            driver.findElement(By.cssSelector("#tel1")).sendKeys(Long.toString(tel1));
            driver.findElement(By.cssSelector("#tel2")).sendKeys(Long.toString(tel2));
            driver.findElement(By.cssSelector("#tel3")).sendKeys(Long.toString(tel3));
        }
    }

    public void bpi_fax(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // FAX
            // total length of fax1, fax2, fax3 in range 3-15
            int length_of_fax1 = RandomUtils.nextInt(13) + 1;
            int length_of_fax2 = RandomUtils.nextInt(14 - length_of_fax1) + 1;
            int length_of_fax3 = RandomUtils.nextInt(15 - length_of_fax1 - length_of_fax2) + 1;

            long fax1 = (long) (Math.random() * (Math.pow(10, length_of_fax1)));
            long fax2 = (long) (Math.random() * (Math.pow(10, length_of_fax2)));
            long fax3 = (long) (Math.random() * (Math.pow(10, length_of_fax3)));
            driver.findElement(By.cssSelector("#fax1")).sendKeys(Long.toString(fax1));
            driver.findElement(By.cssSelector("#fax2")).sendKeys(Long.toString(fax2));
            driver.findElement(By.cssSelector("#fax3")).sendKeys(Long.toString(fax3));
        }
    }

    public void url(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            //URL
            // lengh of url in range 1-50
            int leghth_of_url = RandomUtils.nextInt(38) + 1;
            String url = "https://" + RandomStringUtils.randomAlphabetic(leghth_of_url) + ".com";
            driver.findElement(By.cssSelector("#domain_name")).sendKeys(url);
        }
    }

    public void number_of_employees(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Number of employees
            // 1:~ 10 people, 2:11 ~ 30 people, 3:31 ~ 50 people, 4:51 ~ 100 people, 5:101 ~ 300 people, 6:301 people ~
            int number_of_employees = RandomUtils.nextInt(6) + 1;
            driver.findElement(By.cssSelector("#employee_number > label:nth-child(" + number_of_employees + ") > span > input")).click();

        }
    }

    public void commercial_distribution(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Commercial distribution
            // 1: Can't come out, 2: Exit
            int commercial_distribution = RandomUtils.nextInt(2) + 1;
            driver.findElement(By.cssSelector("#has_distribution > label:nth-child(" + commercial_distribution + ") > span > input")).click();
        }
    }

    public void contract(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            //Contract
            // 1: None, 2: Can be
            int contract = RandomUtils.nextInt(2) + 1;
            driver.findElement(By.cssSelector("#contract > label:nth-child(" + contract + ") > span > input")).click();
        }
    }

    public void capital(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Capital
            // capital in range 100-9999999999999
            long capital = (long) (Math.random() * (9999999999900L) + 100L);
            driver.findElement(By.cssSelector("#capital_man_yen")).sendKeys(Long.toString(capital));
        }
    }

    // Qualifications
    public void P_mark_or_ISMS(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // P mark / ISMS
            // 1: None, 2: Can be
            int P_mark_or_ISMS = RandomUtils.nextInt(2) + 1;
            driver.findElement(By.cssSelector("#has_p_mark_or_isms > label:nth-child(" + P_mark_or_ISMS + ") > span > input")).click();
        }
    }

    public void invoice_registration_company(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Invoice registration company
            // 1: None, 2: Can be
            int invoice_registration_company = RandomUtils.nextInt(2) + 1;
            driver.findElement(By.cssSelector("#has_invoice_system > label:nth-child(" + invoice_registration_company + ") > span > input")).click();
        }
    }

    public void worker_dispatch_business(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Worker dispatch business
            // 1: None, 2: Can be
            int worker_dispatch_business = RandomUtils.nextInt(2) + 1;
            driver.findElement(By.cssSelector("#has_haken > label:nth-child(" + worker_dispatch_business + ") > span > input")).click();
        }
    }

    public void comment(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Comment
            // lenght of comment in range 0-1000
            int lenght_of_comment = RandomUtils.nextInt(1001);
            String comment = RandomStringUtils.randomAlphabetic(lenght_of_comment);
            driver.findElement(By.cssSelector("textarea[id='comment.content']")).sendKeys(comment);
        }
    }

    public void fixed_comment(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Fixed comment
            // 0: Do not fixed comment, 1: Fixed comment
            int fixed_comment = RandomUtils.nextInt(2);
            if (fixed_comment == 1) {
                driver.findElement(By.cssSelector("div:nth-child(3) > div > div > div > button:nth-child(1)")).click();
            }
        }
    }

    public void scroll_up_and_switch_to_Business_partner_branch_information_tab(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Scroll up
            ((JavascriptExecutor) driver).executeScript("scroll(0, -250);");
            // Switch to Business partner branch information tab
            driver.findElement(By.cssSelector("#rc-tabs-0-tab-2")).click();
        }
    }
}
