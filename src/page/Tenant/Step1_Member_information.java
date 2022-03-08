package page.Tenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static java.lang.Thread.sleep;
import static utilities.Link_and_Path.HBS.create_tenant_path;

public class Step1_Member_information extends Tenant_page {
    public static String email_address;
    public static String url_in_mail;

    @FindBy(css = "#email")
    WebElement email;

    @FindBy(css = "#password")
    WebElement password;

    @FindBy(css = "#password_confirm")
    WebElement confirm_password;

    @FindBy(xpath = "//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")
    WebElement iframe_captcha;

    @FindBy(css = "div.recaptcha-checkbox-border")
    WebElement captcha_checkbox;

    @FindBy(css = "input[id^='agree_to']")
    WebElement term_of_use;

    @FindBy(css = "button.ant-btn-primary")
    WebElement next_to_button;

    @FindBy(css = "div.e7m.list-group-item.list-group-item-info")
    WebElement open_mail;

    @FindBy(css = "div.e7m.mess_bodiyy>p>a")
    WebElement get_url_in_mail;

    @FindBy(css = "form> div:nth-child(1) > div > div.ant-form-item-explain > div")
    WebElement email_error;

    @FindBy(css = "form> div:nth-child(2) > div > div.ant-form-item-explain > div")
    WebElement password_error;

    @FindBy(css = "form> div:nth-child(3) > div > div.ant-form-item-explain > div:nth-child(2)")
    WebElement password_and_confirm_password_not_match_error;

    @FindBy(css = "form> div:nth-child(3) > div > div.ant-form-item-explain > div:nth-child(1)")
    WebElement confirm_password_error;

    public Step1_Member_information(WebDriver driver, String domain) {
        super(driver, domain);
        PageFactory.initElements(driver, this);
    }

    /*** Process function
     *
     */
    public void link_to_member_information() {
        driver.get(domain + create_tenant_path);
    }

    public void email_address() {
        int length_of_email_address = RandomUtils.nextInt(90) + 1;
        //generate email address by random text
        email_address = RandomStringUtils.randomAlphabetic(length_of_email_address).toLowerCase();
        // Email address
        email.sendKeys(email_address + "@bedul.net");
        System.out.println("Your mail: " + email_address + "@bedul.net");
    }

    public void password_and_confirm_password() throws InterruptedException {
        // length of password, confirm password in range 10-50
        // password, confirm password include both letters and numbers
        int length_of_password = (int) ((Math.random() * (39) + 10));
        // generate password, confirm password by random text
        String password_str = RandomStringUtils.randomAlphanumeric(length_of_password);
        // Password
        password.sendKeys(password_str + "@");
        // Confirm password
        confirm_password.sendKeys(password_str + "@");
        // Wait captcha
        sleep(2000);
        System.out.println("Your password: " + password_str + "@");
    }

    public void recaptcha_checkbox() throws InterruptedException {
        // Recaptcha checkbox
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe_captcha));
        wait.until(ExpectedConditions.elementToBeClickable(captcha_checkbox)).click();
        sleep(10000);

        // Back to main page
        driver.switchTo().defaultContent();
    }

    public void agree_term_of_use() {
        // Agree term of use
        term_of_use.click();
    }

    public void click_register_member_information_button() throws InterruptedException {
        // Wait and click Register member information button
        sleep(2000);
        next_to_button.click();
        // Wait [コモレビ]コモレビへ新規登録 mail has been sent
        sleep(10000);
    }

    public void url_in_New_registration_to_Komorebi_mail() {
        // Link to register mail
        driver.get("https://generator.email/" + email_address + "@bedul.net");

        // Open [コモレビ]コモレビへ新規登録 mail
        open_mail.click();

        // Url in [コモレビ]コモレビへ新規登録 mail
        url_in_mail = get_url_in_mail.getAttribute("href");
    }

    public void next_to_company_profile_information() throws InterruptedException {
        // get url in [コモレビ]コモレビへ新規登録 mail
        url_in_New_registration_to_Komorebi_mail();
        // Next to Company profile information step
        driver.get(url_in_mail);
        sleep(2000);
    }

    // Email
    public void leave_email_blank() throws InterruptedException {
        email.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
        sleep(2000);
        String text = email_error.getText();
        Assert.assertEquals(text, "メールアドレスを入力してください", "[Email] Message do not match");
    }

    public void email_exceed_100_half_width_characters() throws InterruptedException {
        email.sendKeys(RandomStringUtils.randomAlphabetic(101));
        sleep(2000);
        String text = email_error.getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Email] Message do not match");
    }

    public void email_contains_full_width_characters() throws InterruptedException {
        email.sendKeys(RandomStringUtils.random(10, 0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = email_error.getText();
        Assert.assertEquals(text, "半角英数記号で入力してください。", "[Email] Message do not match");
    }

    public void email_contains_space_characters() throws InterruptedException {
        email.sendKeys("1 1");
        sleep(2000);
        String text = email_error.getText();
        Assert.assertEquals(text, "スペースを入力することはできません。", "[Email] Message do not match");
    }

    // Password
    public void leave_password_blank() throws InterruptedException {
        password.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
        sleep(2000);
        String text = password_error.getText();
        Assert.assertEquals(text, "パスワードを入力してください", "[Password] Message do not match");
    }

    public void password_less_than_10_half_width_characters() throws InterruptedException {
        password.sendKeys(RandomStringUtils.randomAlphabetic(9));
        sleep(2000);
        String text = password_error.getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    /**
     * Input: password exceed 50 characters
     * Output: show error message "大小英数字記号混在で10-50桁で入力してください。"
     **/
    public void password_exceed_50_half_width_characters() throws InterruptedException {
        password.sendKeys(RandomStringUtils.randomAlphabetic(51));
        sleep(2000);
        String text = password_error.getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    public void password_contains_full_width_characters() {
        password.sendKeys(RandomStringUtils.random(10, 0x4e00, 0x4f80, true, false));
        String text = wait.until(ExpectedConditions.visibilityOf(password_error)).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    /**
     * Input: password and confirm password does not match
     * Output: show error message "パスワードが一致しません"
     **/
    public void password_and_confirm_password_does_not_match() throws InterruptedException {
        password.sendKeys(RandomStringUtils.randomAlphabetic(8));
        confirm_password.sendKeys(RandomStringUtils.randomAlphabetic(9));
        sleep(2000);
        String text = password_and_confirm_password_not_match_error.getText();
        Assert.assertEquals(text, "パスワードが一致しません", "Message do not match");
    }

    /**
     * Input: password and confirm password does not mix alphanumerical character.
     * Output: show error message "大小英数字記号混在で10-50桁で入力してください。" at each form
     **/
    public void password_and_confirm_password_does_not_mix_alphanumerical_characters() throws InterruptedException {
        String password_str = RandomStringUtils.random(10, true, false);
        password.sendKeys(password_str);
        confirm_password.sendKeys(password_str);
        sleep(2000);
        String text1 = password_error.getText();
        Assert.assertEquals(text1, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
        String text2 = confirm_password_error.getText();
        Assert.assertEquals(text2, "大小英数字記号混在で10-50桁で入力してください。", "[Confirm password] Message do not match");
    }

    /**
     * Input: leave confirm password blank
     * Output: show error message "パスワード確認を入力してください"
     **/
    public void leave_confirm_password_blank() throws InterruptedException {
        confirm_password.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
        sleep(2000);
        String text = wait.until(ExpectedConditions.visibilityOf(confirm_password_error)).getText();
        Assert.assertEquals(text, "パスワード確認を入力してください", "[Confirm password] Message do not match");
    }

    public void confirm_password_less_than_10_half_width_characters() throws InterruptedException {
        confirm_password.sendKeys(RandomStringUtils.randomAlphabetic(9));
        sleep(2000);
        String text = confirm_password_error.getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Confirm password] Message do not match");
    }

    public void confirm_password_exceed_50_half_width_characters() throws InterruptedException {
        confirm_password.sendKeys(RandomStringUtils.randomAlphabetic(51));
        sleep(2000);
        String text = confirm_password_error.getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Confirm password] Message do not match");
    }

    public void confirm_password_contains_full_width_characters() {
        confirm_password.sendKeys(RandomStringUtils.random(10, 0x4e00, 0x4f80, true, false));
        String text = wait.until(ExpectedConditions.visibilityOf(confirm_password_error)).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Confirm Password] Message do not match");
    }
}
