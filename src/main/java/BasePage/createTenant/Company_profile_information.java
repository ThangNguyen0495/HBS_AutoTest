package BasePage.createTenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class Company_profile_information {
    public void company_name(WebDriver driver) {
        // Length of company name in range 1-100
        int leghth_of_company_name = RandomUtils.nextInt(100) + 1;
        // generate company name by random text
        String company_name = RandomStringUtils.randomAlphabetic(leghth_of_company_name);
        // Company name
        driver.findElement(By.cssSelector("#name")).sendKeys(company_name);
    }

    public void establishment_date(WebDriver driver) {
        // month in range 1-12
        int month = RandomUtils.nextInt(12) + 1;
        // generate year from 1800-2022
        int year = 1800 + RandomUtils.nextInt(223);
        // generate establish date by YYYY-MM format
        String establishment_date;
        if (month < 10) {
            establishment_date = Integer.toString(year) + "-0" + Integer.toString(month);
        } else {
            establishment_date = Integer.toString(year) + "-" + Integer.toString(month);
        }
        // Establishment date
        driver.findElement(By.cssSelector("#establishment_date")).sendKeys(establishment_date);
    }

    public void address(WebDriver driver) {
        // total length of address and bulding in range 2-100
        int leghth_of_address = RandomUtils.nextInt(99) + 1;
        int length_of_building = RandomUtils.nextInt(100 - leghth_of_address) + 1;
        // generate address and building by random text
        String address = RandomStringUtils.randomAlphabetic(leghth_of_address);
        String building = RandomStringUtils.randomAlphabetic(length_of_building);
        // Address
        driver.findElement(By.cssSelector("#address")).sendKeys(address);
        driver.findElement(By.cssSelector("#building")).sendKeys(building);
    }

    public void url(WebDriver driver) {
        // lengh of url in range 1-50
        int leghth_of_url = RandomUtils.nextInt(38) + 1;
        // generate url by random text
        String url = "https://" + RandomStringUtils.randomAlphabetic(leghth_of_url) + ".com";
        // Url
        driver.findElement(By.cssSelector("#domain_name")).sendKeys(url);
    }

    public void commercial_distribution(WebDriver driver) {
        // Commercial distribution
        // 1: Can't come out, 2: Exit
        int commercial_distribution = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_distribution>label:nth-child(" + Integer.toString(commercial_distribution) + ")>span>input")).click();
    }

    public void capital(WebDriver driver) {
        // capital in range 100-9999999999999
        // generate capital by random number
        long capital = (long) (Math.random() * (9999999999900L) + 100L);
        // Capital
        driver.findElement(By.cssSelector("#capital_man_yen")).sendKeys(Long.toString(capital));
    }

    // Qualifications
    public void P_mark_or_ISMS(WebDriver driver) {
        // P mark / ISMS
        // 1: None, 2: Can be
        int P_mark_or_ISMS = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_p_mark_or_isms>label:nth-child(" + Integer.toString(P_mark_or_ISMS) + ")>span>input")).click();
    }

    public void invoice_registration_company(WebDriver driver) {
        // Invoice registration company
        // 1: None, 2: Can be
        int invoice_registration_company = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_invoice_system>label:nth-child(" + Integer.toString(invoice_registration_company) + ")>span>input")).click();
    }

    public void worker_dispatch_business(WebDriver driver) {
        // Worker dispatch business
        // 1: None, 2: Can be
        int worker_dispatch_business = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_haken>label:nth-child(" + Integer.toString(worker_dispatch_business) + ")>span>input")).click();
    }

    public void next_to_personal_profile_information(WebDriver driver) throws InterruptedException {
        // Click Register your company profile button
        // Go to 個人プロフィール情報_Step
        driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>div>button")).click();
        sleep(1000);
    }
}