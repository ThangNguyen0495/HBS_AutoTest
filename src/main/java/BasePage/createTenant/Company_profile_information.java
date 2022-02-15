package BasePage.createTenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

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
            establishment_date = year + "-0" + month;
        } else {
            establishment_date = year + "-" + month;
        }
        // Establishment date
        driver.findElement(By.cssSelector("#establishment_date")).sendKeys(establishment_date);
    }

    public void address(WebDriver driver) {
        // total length of address and building in range 2-100
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
        // length of url in range 1-50
        int length_of_url = RandomUtils.nextInt(38) + 1;
        // generate url by random text
        String url = "https://" + RandomStringUtils.randomAlphabetic(length_of_url) + ".com";
        // Url
        driver.findElement(By.cssSelector("#domain_name")).sendKeys(url);
    }

    public void commercial_distribution(WebDriver driver) {
        // Commercial distribution
        // 1: Can't come out, 2: Exit
        int commercial_distribution = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_distribution>label:nth-child(" + commercial_distribution + ")>span>input")).click();
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
        driver.findElement(By.cssSelector("#has_p_mark_or_isms>label:nth-child(" + P_mark_or_ISMS + ")>span>input")).click();
    }

    public void invoice_registration_company(WebDriver driver) {
        // Invoice registration company
        // 1: None, 2: Can be
        int invoice_registration_company = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_invoice_system>label:nth-child(" + invoice_registration_company + ")>span>input")).click();
    }

    public void worker_dispatch_business(WebDriver driver) {
        // Worker dispatch business
        // 1: None, 2: Can be
        int worker_dispatch_business = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_haken>label:nth-child(" + worker_dispatch_business + ")>span>input")).click();
    }

    public void next_to_personal_profile_information(WebDriver driver) throws InterruptedException {
        // Click Register your company profile button
        // Go to 個人プロフィール情報_Step
        driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>div>button")).click();
        sleep(3000);
    }

    /**
     * Leave company name blank
     *
     * @param driver Webdriver
     * @param key  Actions
     */
    public void leave_company_name_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#name")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(1000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(1) > div > div> div.ant-form-item-explain-error")).getText();
        Assert.assertEquals(text, "自社名を入力してください", "[Company name] Message do not match");
    }

    public void company_name_exceed_100_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#name")).sendKeys(RandomStringUtils.randomAlphanumeric(101));
        sleep(1000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(1) > div > div> div.ant-form-item-explain-error")).getText();
        Assert.assertEquals(text, "自社名は100文字以内で入力してください。", "[Company name] Message do not match");
    }


    // Establishment date
    public void leave_date_of_establishment_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#establishment_date")).sendKeys("2022-01");
        key.sendKeys(Keys.ENTER).perform();
        driver.findElement(By.cssSelector("span.anticon-close-circle")).click();
        sleep(1000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(2) > div > div> div.ant-form-item-explain-error")).getText();
        Assert.assertEquals(text, "設立年月を入力してください", "[Establishment date] Message do not match");
    }

    // Address
    public void leave_address_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#address")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(1000);
        String text1 = driver.findElement(By.cssSelector("span > div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text1, "市区町村・町名・番地を入力してください", "[Address] Message do not match");

        driver.findElement(By.cssSelector("#building")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(1000);
        String text2 = driver.findElement(By.cssSelector("span > div:nth-child(2) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text2, "建物名を入力してください", "[Building] Message do not match");

    }

    public void address_exceed_100_characters(WebDriver driver) throws InterruptedException {
        // total length of address and building in range 2-101
        int leghth_of_address = RandomUtils.nextInt(100) + 1;
        int length_of_building = 101 - leghth_of_address;
        // Address
        driver.findElement(By.cssSelector("#address")).sendKeys(RandomStringUtils.randomAlphanumeric(leghth_of_address));
        driver.findElement(By.cssSelector("#building")).sendKeys(RandomStringUtils.randomAlphanumeric(length_of_building));
        sleep(1000);
        String text = driver.findElement(By.cssSelector("span > div:nth-child(2) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "住所は100文字以内で入力してください。", "[Address] Message do not match");
    }

    // URL
    public void leave_url_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#domain_name")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(1000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "URLを入力してください", "[URL] Message do not match");
    }

    public void url_exceed_50_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#domain_name")).sendKeys(RandomStringUtils.randomAlphanumeric(51));
        sleep(1000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "URLは50文字以内で入力してください。", "[URL] Message do not match");
    }

    // Capital
    public void leave_capital_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#capital_man_yen")).sendKeys("0");
        key.sendKeys(Keys.BACK_SPACE).perform();
        sleep(1000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(6) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "資本金を入力してください", "[Capital] Message do not match");
    }

    public void capital_exceed_13_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#capital_man_yen")).sendKeys(RandomStringUtils.random(14, false, true));
        sleep(1000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(6) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "資本金は13桁以内で入力してください。", "[Capital] Message do not match");
    }
}