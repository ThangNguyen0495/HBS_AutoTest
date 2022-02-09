package CreateDummyData;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static Variable.Variable.*;

public class RegisterPartner {
    @Test
    public void main() throws InterruptedException {

        //Config Webdriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //Login
        driver.get(url + register_partner_path);
        driver.findElement(By.cssSelector("#username")).sendKeys(master_email);
        driver.findElement(By.cssSelector("#password")).sendKeys(master_password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //*** 取引先情報 ***//
        // Business partner information

        //** Fill in Business partner information form **//
        // Block list
        // 0: Do not block, 1: Block
        int block_list = RandomUtils.nextInt(2);
        if (block_list == 1){
            driver.findElement(By.cssSelector("#is_blacklisted")).click();
        }

        // Corporate number
        // Corporate number in range 0 - 9999999999999
        long corporate_number = (long) (Math.random()*9999999999999L);
        driver.findElement(By.cssSelector("#corporate_number")).sendKeys(Long.toString(corporate_number));

        // Customer name
        // length of customer name in range 1-100
        int length_of_customer_name = RandomUtils.nextInt(100) + 1;
        String customer_name = RandomStringUtils.randomAlphabetic(length_of_customer_name);
        driver.findElement(By.cssSelector("#name")).sendKeys(customer_name);

        // Account status
        // 1:Prospect, 2:Approached, 3:Information exchanged, 4:Contract record available
        int account_status = RandomUtils.nextInt(4) + 1;
        driver.findElement(By.cssSelector("#category > label:nth-child(" + Integer.toString(account_status) + ") > span > input"));

        // Customer evaluation
        // 1: one star, 2: two stars, 3: three stars, 4: four stars, 5: five stars
        int customer_evaluation = RandomUtils.nextInt(5) + 1;
        driver.findElement(By.cssSelector("li.ant-rate-star:nth-child(" + Integer.toString(customer_evaluation) + ")")).click();

        // Country of Citizenship
        // 1:Japan , 2:Korea, 3:China, 4:Others
        int country_of_citizenship = RandomUtils.nextInt(4) + 1;
        driver.findElement(By.cssSelector("#country > label:nth-child(" + Integer.toString(country_of_citizenship) + ") > span > input")).click();

        // Establishment date
        // generate establishment date with YYYY-MM format
        int month = RandomUtils.nextInt(12) + 1; // month in range 01-12
        int year = (int)(Math.random()*223 + 1800); // year in range 1800-2022
        String establishment_date;
        if (month < 10){
            establishment_date = Integer.toString(year) + "-0" + Integer.toString(month);
        }
        else {
            establishment_date = Integer.toString(year) + "-" + Integer.toString(month);
        }
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#establishment_date"))).sendKeys(establishment_date);
        Actions key = new Actions(driver);
        key.sendKeys(Keys.ENTER).perform();

        // Fiscal year
        // 1: Jan, 2: Feb, 3: Mar, 4: Apr, 5: May, 6: Jun, 7: Jul, 8: Aug, 9: Sept, 10: Oct, 11: Nov, 12: Dec
        int fiscal_year = RandomUtils.nextInt(12) + 1;
        driver.findElement(By.cssSelector("#settlement_month")).click();
        for (int i = 0; i < fiscal_year ; i++){
            key.sendKeys(Keys.DOWN).perform();
        }
        key.sendKeys(Keys.ENTER).perform();

        // Address
        // total length of address and building in range 2-100
        int length_of_address = RandomUtils.nextInt(99) + 1;
        int length_of_building = RandomUtils.nextInt(100 - length_of_address) + 1;
        String address = RandomStringUtils.randomAlphabetic(length_of_address);
        String building = RandomStringUtils.randomAlphabetic(length_of_building);
        driver.findElement(By.cssSelector("#address")).sendKeys(address);
        driver.findElement(By.cssSelector("#building")).sendKeys(building);

        // TEL
        // total length of tel1, tel2, tel3 in range 3-15
        int length_of_tel1 = RandomUtils.nextInt(13);
        int length_of_tel2 = RandomUtils.nextInt(14 - length_of_tel1);
        int length_of_tel3 = RandomUtils.nextInt(15 - length_of_tel1 - length_of_tel2);

        long tel1 = (long) (Math.random() * (Math.pow(10, length_of_tel1)));
        long tel2 = (long) (Math.random() * (Math.pow(10, length_of_tel2)));
        long tel3 = (long) (Math.random() * (Math.pow(10, length_of_tel3)));

        driver.findElement(By.cssSelector("#tel1")).sendKeys(Long.toString(tel1));
        driver.findElement(By.cssSelector("#tel2")).sendKeys(Long.toString(tel2));
        driver.findElement(By.cssSelector("#tel3")).sendKeys(Long.toString(tel3));

        // FAX
        // total length of fax1, fax2, fax3 in range 3-15
        int length_of_fax1 = RandomUtils.nextInt(13);
        int length_of_fax2 = RandomUtils.nextInt(14 - length_of_fax1);
        int length_of_fax3 = RandomUtils.nextInt(15 - length_of_fax1 - length_of_fax2);

        long fax1 = (long) (Math.random() * (Math.pow(10, length_of_fax1)));
        long fax2 = (long) (Math.random() * (Math.pow(10, length_of_fax2)));
        long fax3 = (long) (Math.random() * (Math.pow(10, length_of_fax3)));
        driver.findElement(By.cssSelector("#fax1")).sendKeys(Long.toString(fax1));
        driver.findElement(By.cssSelector("#fax2")).sendKeys(Long.toString(fax2));
        driver.findElement(By.cssSelector("#fax3")).sendKeys(Long.toString(fax3));

        //URL
        // lengh of url in range 1-50
        int leghth_of_url = RandomUtils.nextInt(38) + 1;
        String url = "https://" + RandomStringUtils.randomAlphabetic(leghth_of_url) + ".com";
        driver.findElement(By.cssSelector("#domain_name")).sendKeys(url);

        // Number of employees
        // 1:~ 10 people, 2:11 ~ 30 people, 3:31 ~ 50 people, 4:51 ~ 100 people, 5:101 ~ 300 people, 6:301 people ~
        int number_of_employees = RandomUtils.nextInt(6) + 1;
        driver.findElement(By.cssSelector("#employee_number > label:nth-child(" + Integer.toString(number_of_employees) + ") > span > input")).click();

        // Commercial distribution
        // 1: Can't come out, 2: Exit
        int commercial_distribution = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_distribution > label:nth-child(" + Integer.toString(commercial_distribution) + ") > span > input")).click();

        //Contract
        // 1: None, 2: Can be
        int contract = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#contract > label:nth-child(" + Integer.toString(contract) + ") > span > input")).click();

        // Capital
        // capital in range 100-9999999999999
        long capital = (long) (Math.random() * (9999999999900L) + 100L);
        driver.findElement(By.cssSelector("#capital_man_yen")).sendKeys(Long.toString(capital));

        // Qualifications
        // P mark / ISMS
        // 1: None, 2: Can be
        int P_mark_or_ISMS = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_p_mark_or_isms > label:nth-child(" + Integer.toString(P_mark_or_ISMS) + ") > span > input")).click();

        // Invoice registration company
        // 1: None, 2: Can be
        int invoice_registration_company = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_invoice_system > label:nth-child(" + Integer.toString(invoice_registration_company) + ") > span > input")).click();

        // Worker dispatch business
        // 1: None, 2: Can be
        int worker_dispatch_business = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_haken > label:nth-child(" + Integer.toString(worker_dispatch_business) + ") > span > input")).click();
    }
}
