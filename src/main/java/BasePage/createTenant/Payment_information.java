package BasePage.createTenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Payment_information {
    public static String card_number_register;
    public static String card_number_update;

    public void get_card_number_from_testcard_page(WebDriver driver) {
        // Get card number from testcard page
        // Use any testcard from testcard page
        int id_register = RandomUtils.nextInt(12) + 1;
        int id_update = RandomUtils.nextInt(12) + 1;
        while (id_update == id_register) {
            id_update = RandomUtils.nextInt(12) + 1;
        }
        driver.get("https://pay.jp/docs/testcard");
        // Get card number by id
        card_number_register = driver.findElement(By.cssSelector("table:nth-child(4)>tbody>tr:nth-child(" + id_register + ")>td:nth-child(1)")).getText();
        card_number_update = driver.findElement(By.cssSelector("table:nth-child(4)>tbody>tr:nth-child(" + id_update + ")>td:nth-child(1)")).getText();
    }

    public void back_to_payment_information(WebDriver driver) throws InterruptedException {
        Member_information url = new Member_information();
        driver.get(url.url_in_New_registration_to_Komorebi_mail(driver));
        sleep(3000);
    }

    public void register_payment_information(WebDriver driver) {
        // Open お支払い情報 popup
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.anticon.anticon-plus")))
                .click();
    }

    public void update_payment_information(WebDriver driver) {
        // Open お支払い情報 popup
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-row.ant-row-end>button")))
                .click();
    }

    public void card_number_register(WebDriver driver) {
        // Card Number
        driver.switchTo().frame(driver.findElement(By.cssSelector("#number-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardNumber"))).sendKeys(card_number_register);
    }

    public void card_number_update(WebDriver driver) {
        // Card Number
        driver.switchTo().frame(driver.findElement(By.cssSelector("#number-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardNumber"))).sendKeys(card_number_update);
    }

    public void date_of_expiry(WebDriver driver) {
        // month in range 01-12
        int month = RandomUtils.nextInt(12) + 1;
        // year in range 23-99
        int year = (int) (Math.random() * 87 + 23);
        // generate date of expiry with MM/YY format
        String date_of_expiry;
        if (month < 10) {
            date_of_expiry = "0" + month + year;
        } else {
            date_of_expiry = Integer.toString(month) + year;
        }
        //Date of Expiry
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.cssSelector("#expiry-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardExpiry"))).sendKeys(date_of_expiry);
    }

    public void cvc(WebDriver driver) {
        // CVC have 3 - 4 numbers
        // CVC in range 0000-9990
        int cvc = RandomUtils.nextInt(10000);
        String CVC;
        if (cvc < 10) {
            CVC = "000" + cvc;
        } else if (cvc < 99) {
            CVC = "00" + cvc;
        } else if (cvc < 999) {
            CVC = "0" + cvc;
        } else {
            CVC = "0" + cvc;
        }
        //CVC
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.cssSelector("#cvc-form > iframe")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cardCvc"))).sendKeys(CVC);
    }

    public void card_name(WebDriver driver) {
        // length of card name in range 1-100
        int length_of_card_name = RandomUtils.nextInt(100) + 1;
        // generate card name by random text
        String card_name = RandomStringUtils.random(length_of_card_name);
        //Card Name
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-col.ant-col-18.ant-form-item-control>div>div>div>div>input")))
                .sendKeys(card_name);
    }

    public void complete_add_payment_information(WebDriver driver) throws InterruptedException {
        //Register or update card
        driver.findElement(By.cssSelector("div:nth-child(3)>div>button")).click();
        // Wait page loading
        sleep(3000);
    }

    public void complete_tenant_registration(WebDriver driver) {
        // Complete tenant registration
        driver.findElement(By.cssSelector("div.ant-row.ant-row-center>button")).click();
    }
}
