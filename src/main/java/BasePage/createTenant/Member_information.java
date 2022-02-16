package BasePage.createTenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Member_information {
    public static String email_address;
    public static String url_in_mail;

    /*** Process function
     *
     * @param driver: Webdriver
     * @param url: domain
     */
    public void link_to_member_information(WebDriver driver, String url) {
        driver.get(url);
    }

    public void email_address(WebDriver driver) {
        int length_of_email_address = RandomUtils.nextInt(90) + 1;
        //generate email address by random text
        email_address = RandomStringUtils.randomAlphabetic(length_of_email_address);
        // Email address
        driver.findElement(By.cssSelector("#email")).sendKeys(email_address + "@bedul.net");
    }

    public void password_and_confirm_password(WebDriver driver) throws InterruptedException {
        // length of password, confirm password in range 10-50
        // password, confirm password include both letters and numbers
        int length_of_password = (int) ((Math.random() * (39) + 10));
        // generate password, confirm password by random text
        String password = RandomStringUtils.randomAlphanumeric(length_of_password);
        // Password
        driver.findElement(By.cssSelector("#password")).sendKeys(password + "@");
        // Confirm password
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(password + "@");
        // Wait captcha
        sleep(2000);
    }

    public void recaptcha_checkbox(WebDriver driver) throws InterruptedException {
        // Recaptcha checkbox
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border")))
                .click();

        sleep(10000);
        // Back to main page
        driver.switchTo().defaultContent();
    }

    public void agree_term_of_use(WebDriver driver) {
        // Agree term of use
        driver.findElement(By.cssSelector("input[id^='agree_to']")).click();
    }

    public void click_register_member_information_button(WebDriver driver) throws InterruptedException {
        // Wait and click Register member information button
        sleep(2000);
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary")).click();
        // Wait [コモレビ]コモレビへ新規登録 mail has been sent
        sleep(10000);
    }

    public void url_in_New_registration_to_Komorebi_mail(WebDriver driver) {
        // Link to register mail
        driver.get("https://generator.email/" + email_address + "@bedul.net");

        // Open [コモレビ]コモレビへ新規登録 mail
        WebElement mail_link = driver.findElement(By.cssSelector("div.e7m.list-group-item.list-group-item-info"));
        mail_link.click();

        // Get url in [コモレビ]コモレビへ新規登録 mail
        WebElement url = driver.findElement(By.cssSelector("div.e7m.mess_bodiyy>p>a"));

        // Url in [コモレビ]コモレビへ新規登録 mail
        url_in_mail = url.getAttribute("href");
    }

    public void next_to_company_profile_information(WebDriver driver) throws InterruptedException {
        // get url in [コモレビ]コモレビへ新規登録 mail
        url_in_New_registration_to_Komorebi_mail(driver);
        // Next to Company profile information step
        driver.get(url_in_mail);
        sleep(2000);
    }

    /*** Validation function
     *
     * @param driver: Webdriver
     * @param key: Action
     */

    // Email
    public void leave_email_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#email")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "メールアドレスを入力してください", "[Email] Message do not match");
    }

    public void email_exceed_100_half_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#email")).sendKeys(RandomStringUtils.randomAlphabetic(101));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Email] Message do not match");
    }

    public void email_contains_full_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#email")).sendKeys(RandomStringUtils.random(10,0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "半角英数記号で入力してください。", "[Email] Message do not match");
    }

    public void email_contains_space_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#email")).sendKeys("1 1");
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "スペースを入力することはできません。", "[Email] Message do not match");
    }

    // Password
    public void leave_password_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#password")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(2) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "パスワードを入力してください", "[Password] Message do not match");
    }

    public void password_less_than_10_half_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#password")).sendKeys(RandomStringUtils.randomAlphabetic(9));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(2) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    /**
     * Input: password exceed 50 characters
     * Output: show error message "大小英数字記号混在で10-50桁で入力してください。"
     **/
    public void password_exceed_50_half_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#password")).sendKeys(RandomStringUtils.randomAlphabetic(51));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(2) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    public void password_contains_full_width_characters(WebDriver driver) {
        driver.findElement(By.cssSelector("#password")).sendKeys(RandomStringUtils.random(10,0x4e00, 0x4f80, true, false));
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("form> div:nth-child(2) > div > div.ant-form-item-explain > div"))).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    /**
     * Input: password and confirm password does not match
     * Output: show error message "パスワードが一致しません"
     **/
    public void password_and_confirm_password_does_not_match(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#password")).sendKeys(RandomStringUtils.randomAlphabetic(8));
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(RandomStringUtils.randomAlphabetic(9));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(3) > div > div.ant-form-item-explain > div:nth-child(2)")).getText();
        Assert.assertEquals(text, "パスワードが一致しません", "Message do not match");
    }

    /**
     * Input: password and confirm password does not mix alphanumerical character.
     * Output: show error message "大小英数字記号混在で10-50桁で入力してください。" at each form
     **/
    public void password_and_confirm_password_does_not_mix_alphanumerical_characters(WebDriver driver) throws InterruptedException {
        String password = RandomStringUtils.random(10,true,false);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(password);
        sleep(2000);
        String text1 = driver.findElement(By.cssSelector("form> div:nth-child(2) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text1, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
        String text2 = driver.findElement(By.cssSelector("form> div:nth-child(3) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text2, "大小英数字記号混在で10-50桁で入力してください。", "[Confirm password] Message do not match");
    }

    /**
     * Input: leave confirm password blank
     * Output: show error message "パスワード確認を入力してください"
     **/
    public void leave_confirm_password_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(2000);
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("form> div:nth-child(3) > div > div.ant-form-item-explain > div:nth-child(1)"))).getText();
        Assert.assertEquals(text, "パスワード確認を入力してください", "[Confirm password] Message do not match");
    }

    public void confirm_password_less_than_10_half_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(RandomStringUtils.randomAlphabetic(9));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(3) > div > div.ant-form-item-explain > div:nth-child(1)")).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Confirm password] Message do not match");
    }

    public void confirm_password_exceed_50_half_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(RandomStringUtils.randomAlphabetic(51));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form> div:nth-child(3) > div > div.ant-form-item-explain > div:nth-child(1)")).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Confirm password] Message do not match");
    }

    public void confirm_password_contains_full_width_characters(WebDriver driver) {
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys(RandomStringUtils.random(10,0x4e00, 0x4f80, true, false));
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("form> div:nth-child(3) > div > div.ant-form-item-explain > div:nth-child(1)"))).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Confirm Password] Message do not match");
    }

    //Register member information
    public void register_member_information_should_be_disable(WebDriver driver) throws InterruptedException {
        sleep(2000);
        boolean button_is_enable = driver.findElement(By.cssSelector("button[type='submit']")).isEnabled();
        Assert.assertFalse(button_is_enable, "[Register member information] Button is not getting disable.");
    }
}
