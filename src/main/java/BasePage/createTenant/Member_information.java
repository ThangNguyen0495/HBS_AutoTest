package BasePage.createTenant;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Member_information {
    public static String email_address;

    public void link_to_member_information(WebDriver driver, String url) {
        driver.get(url);
    }

    public void email_address(WebDriver driver) {
        // length of email address in range 11-100
        int length_of_email_address = (int) (Math.random() * (90) + 11);
        //generate email address by random text
        email_address = RandomStringUtils.randomAlphabetic(length_of_email_address);
        // Email address
        driver.findElement(By.cssSelector("#email")).sendKeys(email_address + "@bedul.net");
    }

    public void password_and_confirm_password(WebDriver driver) throws InterruptedException {
        // length of password, confirm password in range 8-50
        // password, confirm password include both letters and numbers
        int length_of_password = (int) ((Math.random() * (43) + 8));
        // generate password, confirm password by random text
        String password = RandomStringUtils.randomAlphanumeric(length_of_password);
        // Password
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        // Confirm password
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(password);
        // Wait captcha
        sleep(3000);
    }

    public void recaptcha_checkbox(WebDriver driver) {
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
    }

    public void agree_term_of_use(WebDriver driver) {
        // Agree term of use
        driver.findElement(By.cssSelector("input[id^='agree_to']")).click();
    }

    public void click_register_member_information_button(WebDriver driver) throws InterruptedException {
        // Wait and click Register member information button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .elementToBeClickable(By.cssSelector("button.ant-btn.ant-btn-primary")))
                .click();
        // Wait [コモレビ]コモレビへ新規登録 mail has been sent
        sleep(10000);
    }

    public String url_in_コモレビへ新規登録_mail(WebDriver driver) {
        // Link to register mail
        driver.get("https://generator.email/" + email_address + "@bedul.net");

        // Open [コモレビ]コモレビへ新規登録 mail
        WebElement mail_link = driver.findElement(By.cssSelector("div.e7m.list-group-item.list-group-item-info"));
        mail_link.click();

        // Get url in [コモレビ]コモレビへ新規登録 mail
        WebElement url = driver.findElement(By.cssSelector("div.e7m.mess_bodiyy>p>a"));

        // Url in [コモレビ]コモレビへ新規登録 mail
        return url.getAttribute("href");
    }

    public void next_to_company_profile_information(WebDriver driver) throws InterruptedException {
        // Next to Company profile information step
        driver.get(url_in_コモレビへ新規登録_mail(driver));
        sleep(1000);
    }
}
