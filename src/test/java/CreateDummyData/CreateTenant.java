package CreateDummyData;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static Variable.Variable.create_tenant_path;
import static Variable.Variable.url;
import static java.lang.Thread.sleep;

public class CreateTenant {
    public static String 自社プロフィール情報_Step;
    public static String 個人プロフィール情報_Step;
    public static String お支払い情報_Step;

    @Test(priority = 0)
    // Member information
    public void テナント作成_会員情報() throws InterruptedException {

        //** Generate mail **//
        // length of email address in range 11-100
        int length_of_email_address = (int) (Math.random() * (90) + 11);

        //generate email address by random text
        String email_address = RandomStringUtils.randomAlphabetic(length_of_email_address);

        //** Generate password, confirm password **//
        // length of password, confirm password in range 8-50
        // password, confirm password include both letters and numbers
        int length_of_password = (int) ((Math.random() * (43) + 8));

        // generate password, confirm password by random text
        String password = RandomStringUtils.randomAlphanumeric(length_of_password);

        //** Config Webdriver **//
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //** Link to Create tenant - Member information **//
        String baseURL = url + create_tenant_path;
        driver.get(baseURL);

        //** Fill in Member information form **//
        // Email address
        driver.findElement(By.cssSelector("#email")).sendKeys(email_address + "@bedul.net");

        // Password
        driver.findElement(By.cssSelector("#password")).sendKeys(password);

        // Confirm password
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(password);

        // Wait captcha
        sleep(3000);

        // Recaptcha checkbox
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border")))
                .click();

        // Back to main page
        driver.switchTo().defaultContent();

        // Agree term of use
        driver.findElement(By.cssSelector("input[id^='agree_to']")).click();

        // Wait and click Register member information button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .elementToBeClickable(By.cssSelector("button.ant-btn.ant-btn-primary")))
                .click();

        // Wait [コモレビ]コモレビへ新規登録 mail has been sent
        sleep(10000);

        // Link to register mail
        driver.get("https://generator.email/" + email_address + "@bedul.net");

        // Open [コモレビ]コモレビへ新規登録 mail
        WebElement mail_link = driver.findElement(By.cssSelector("div.e7m.list-group-item.list-group-item-info"));
        mail_link.click();

        // Get link in [コモレビ]コモレビへ新規登録 mail
        WebElement link = driver.findElement(By.cssSelector("div.e7m.mess_bodiyy>p>a"));
        自社プロフィール情報_Step = link.getAttribute("href");

        //Close browser
        driver.close();
    }

    @Test(priority = 1)
    // Company profile information
    public void 自社プロフィール情報() {

        //** Generate company name **//
        // Length of company name in range 1-100
        int leghth_of_company_name = RandomUtils.nextInt(100) + 1;

        // generate company name by random text
        String company_name = RandomStringUtils.randomAlphabetic(leghth_of_company_name);

        //** Generate establishment date **//
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

        //** Generate address **//
        // total length of address and bulding in range 2-100
        int leghth_of_address = RandomUtils.nextInt(99) + 1;
        int length_of_building = RandomUtils.nextInt(100 - leghth_of_address) + 1;

        // generate address and building by random text
        String address = RandomStringUtils.randomAlphabetic(leghth_of_address);
        String building = RandomStringUtils.randomAlphabetic(length_of_building);

        //** Generate url **//
        // lengh of url in range 1-50
        int leghth_of_url = RandomUtils.nextInt(38) + 1;

        // generate url by random text
        String url = "https://" + RandomStringUtils.randomAlphabetic(leghth_of_url) + ".com";

        //** Generate capital **//
        // capital in range 100-9999999999999
        // generate capital by random number
        long capital = (long) (Math.random() * (9999999999900L) + 100L);

        //** Config Webdriver **//
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //** Link to Create tenant - Company profile information tab **//
        driver.get(自社プロフィール情報_Step);

        //** Fill in Company profile information form **//
        // Company name
        driver.findElement(By.cssSelector("#name")).sendKeys(company_name);

        // Establishment date
        driver.findElement(By.cssSelector("#establishment_date")).sendKeys(establishment_date);

        // Address
        driver.findElement(By.cssSelector("#address")).sendKeys(address);
        driver.findElement(By.cssSelector("#building")).sendKeys(building);

        // Url
        driver.findElement(By.cssSelector("#domain_name")).sendKeys(url);

        // Commercial distribution
        // 1: Can't come out, 2: Exit
        int commercial_distribution = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_distribution>label:nth-child(" + Integer.toString(commercial_distribution) + ")>span>input")).click();

        // Capital
        driver.findElement(By.cssSelector("#capital_man_yen")).sendKeys(Long.toString(capital));

        // Qualifications
        // P mark / ISMS
        // 1: None, 2: Can be
        int P_mark_or_ISMS = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_p_mark_or_isms>label:nth-child(" + Integer.toString(P_mark_or_ISMS) + ")>span>input")).click();

        // Invoice registration company
        // 1: None, 2: Can be
        int invoice_registration_company = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_invoice_system>label:nth-child(" + Integer.toString(invoice_registration_company) + ")>span>input")).click();

        // Worker dispatch business
        // 1: None, 2: Can be
        int worker_dispatch_business = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#has_haken>label:nth-child(" + Integer.toString(worker_dispatch_business) + ")>span>input")).click();

        // Click Register your company profile button
        // Go to 個人プロフィール情報_Step
        driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>div>button")).click();

        // Get 個人プロフィール情報_Step url
        個人プロフィール情報_Step = driver.getCurrentUrl();

        //Close browser
        driver.close();
    }


    @Test(priority = 2)
    // Personal profile information
    public void 個人プロフィール情報() {

        //** Generate Username **//
        // total length of first name and last name in range 2-50
        int leghth_of_last_name = RandomUtils.nextInt(49) + 1;
        int length_of_firt_name = RandomUtils.nextInt(50 - leghth_of_last_name) + 1;

        // generate first name and last name by random text
        String last_name = RandomStringUtils.randomAlphabetic(leghth_of_last_name);
        String first_name = RandomStringUtils.randomAlphabetic(length_of_firt_name);

        //** Generate TEL **//
        // total length of TEL in range 3-15
        int length_of_tel1 = RandomUtils.nextInt(13) + 1;
        int length_of_tel2 = RandomUtils.nextInt(14 - length_of_tel1) + 1;
        int length_of_tel3 = RandomUtils.nextInt(15 - length_of_tel1 - length_of_tel2) + 1;

        //Generate TEL1, TEL2, TEL3 by random number
        long TEL1 = (long) (Math.random() * (Math.pow(10, length_of_tel1)));
        long TEL2 = (long) (Math.random() * (Math.pow(10, length_of_tel2)));
        long TEL3 = (long) (Math.random() * (Math.pow(10, length_of_tel3)));

        //** Generate password **//
        // length of password in range 8-50
        // password include both letters and numbers
        int length_of_password = (int) ((Math.random() * (43) + 8));

        // generate password by random text
        String password = RandomStringUtils.randomAlphanumeric(length_of_password);

        //** Generate email signature **//
        // length of email signature in range 0-1000
        int length_of_email_signature = (int) (Math.random() * 1000);

        // generate email signature by random text
        String email_signature = RandomStringUtils.randomAlphabetic(length_of_email_signature);

        //** Config Webdriver **//
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //** Link to Create tenant - Personal profile information **//
        driver.get(個人プロフィール情報_Step);

        //** Fill in Personal profile information form **//
        // Username
        driver.findElement(By.cssSelector("#last_name")).sendKeys(last_name);
        driver.findElement(By.cssSelector("#first_name")).sendKeys(first_name);

        // TEL
        driver.findElement(By.cssSelector("#tel1")).sendKeys(Long.toString(TEL1));
        driver.findElement(By.cssSelector("#tel2")).sendKeys(Long.toString(TEL2));
        driver.findElement(By.cssSelector("#tel3")).sendKeys(Long.toString(TEL3));

        // Password
        driver.findElement(By.cssSelector("#password")).sendKeys(password);

        // Email signature
        driver.findElement(By.cssSelector("#email_signature")).sendKeys(email_signature);

        // Go to お支払い情報_Step
        driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>button")).click();

        // Get お支払い情報_Step url
        お支払い情報_Step = driver.getCurrentUrl();

        //Close browser
        driver.close();
    }

    @Test(priority = 3)
    // Payment information
    public void お支払い情報_Register() throws InterruptedException {

        //** Config Webdriver **//
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //** Get card number from testcard page **//
        // Use any testcard from testcard page
        int id = RandomUtils.nextInt(12) + 1;
        driver.get("https://pay.jp/docs/testcard");

        // Get card number by id
        String card_number = driver.findElement(By.cssSelector("table:nth-child(4)>tbody>tr:nth-child(" + Integer.toString(id) + ")>td:nth-child(1)")).getText();


        //** Generate date of expiry **//
        // month in range 01-12
        int month = RandomUtils.nextInt(12) + 1;

        // year in range 23-99
        int year = (int)(Math.random()*87 + 23);

        // generate date of expiry with MM/YY format
        String date_of_expiry;
        if (month < 10){
            date_of_expiry = "0" + Integer.toString(month) + Integer.toString(year);
        }
        else {
            date_of_expiry = Integer.toString(month) + Integer.toString(year);
        }

        //** Generate CVC **//
        // CVC have only 3 numbers
        // CVC in range 000-999
        int cvc = RandomUtils.nextInt(1000);
        String CVC;
        if (cvc < 10) {
            CVC = "00" + Integer.toString(cvc);
        }
        else if (cvc < 99){
            CVC = "0" + Integer.toString(cvc);
        }
        else {
            CVC = Integer.toString(cvc);
        }

        //** Generate card name **//
        // length of card name in range 1-100
        int length_of_card_name = RandomUtils.nextInt(100) + 1;

        // generate card name by random text
        String card_name = RandomStringUtils.random(length_of_card_name);

        //** Link to Create tenant - Payment information **//
        driver.get(お支払い情報_Step);
//        driver.get("https://test.app.cmrb.jp/tenant?auth_token=ImIxNzZmOTY5LTdhMDYtNDg0Ny1iMWJhLWZlNmE5NDA0OWFhNyI:1nHczS:9tp4WTUZWtdm-EEQL1ia71FpsTQ");

        //** Open お支払い情報 popup **//
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.anticon.anticon-plus")))
                .click();

        //** Fill in Payment information form **//
        // Card Number
        driver.switchTo().frame(driver.findElement(By.cssSelector("#number-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardNumber"))).sendKeys(card_number);

        //Date of Expiry
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.cssSelector("#expiry-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardExpiry"))).sendKeys(date_of_expiry);

        //CVC
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.cssSelector("#cvc-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardCvc"))).sendKeys(CVC);

        //Card Name
        driver.switchTo().defaultContent();
        new WebDriverWait(driver,Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-col.ant-col-18.ant-form-item-control>div>div>div>div>input")))
            .sendKeys(card_name);

        //Register card
        driver.findElement(By.cssSelector("div:nth-child(3)>div>button")).click();

        //Close browser
        driver.close();
    }

    @Test(priority = 4)
    // Payment information
    public void お支払い情報_Update() throws InterruptedException {

        //** Config Webdriver **//
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //** Get card number from testcard page **//
        // Use any testcard from testcard page
        int id = RandomUtils.nextInt(12) + 1;
        driver.get("https://pay.jp/docs/testcard");

        // Get card number by id
        String card_number = driver.findElement(By.cssSelector("table:nth-child(4)>tbody>tr:nth-child(" + Integer.toString(id) + ")>td:nth-child(1)")).getText();


        //** Generate date of expiry **//
        // month in range 01-12
        int month = RandomUtils.nextInt(12) + 1;

        // year in range 23-99
        int year = (int)(Math.random()*87 + 23);

        // generate date of expiry with MM/YY format
        String date_of_expiry;
        if (month < 10){
            date_of_expiry = "0" + Integer.toString(month) + Integer.toString(year);
        }
        else {
            date_of_expiry = Integer.toString(month) + Integer.toString(year);
        }

        //** Generate CVC **//
        // CVC have only 3 numbers
        // CVC in range 000-999
        int cvc = RandomUtils.nextInt(1000);
        String CVC;
        if (cvc < 10) {
            CVC = "00" + Integer.toString(cvc);
        }
        else if (cvc < 99){
            CVC = "0" + Integer.toString(cvc);
        }
        else {
            CVC = Integer.toString(cvc);
        }

        //** Generate card name **//
        // length of card name in range 1-100
        int length_of_card_name = RandomUtils.nextInt(100) + 1;

        // generate card name by random text
        String card_name = RandomStringUtils.random(length_of_card_name);

        //** Link to Create tenant - Payment information **//
        driver.get(お支払い情報_Step);
//        driver.get("https://test.app.cmrb.jp/tenant?auth_token=ImIxNzZmOTY5LTdhMDYtNDg0Ny1iMWJhLWZlNmE5NDA0OWFhNyI:1nHczS:9tp4WTUZWtdm-EEQL1ia71FpsTQ");

        //** Open お支払い情報 popup **//
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-row.ant-row-end>button")))
                .click();

        //** Fill in Payment information form **//
        // Card Number
        driver.switchTo().frame(driver.findElement(By.cssSelector("#number-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardNumber"))).sendKeys(card_number);

        //Date of Expiry
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.cssSelector("#expiry-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardExpiry"))).sendKeys(date_of_expiry);

        //CVC
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.cssSelector("#cvc-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardCvc"))).sendKeys(CVC);

        //Card Name
        driver.switchTo().defaultContent();
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-col.ant-col-18.ant-form-item-control>div>div>div>div>input")))
                        .sendKeys(card_name);

        //Update card
        driver.findElement(By.cssSelector("div:nth-child(3)>div>button")).click();

        //** Complete tenant registration **//
        driver.findElement(By.cssSelector("div.ant-row.ant-row-center>button")).click();
    }
}
