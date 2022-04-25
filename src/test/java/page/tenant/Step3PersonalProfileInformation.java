package page.tenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static java.lang.Thread.sleep;
import static utilities.links.HBS.AUTHOR_TOKEN_PATH;
import static utilities.links.HBS.CREATE_TENANT_PATH;

public class Step3PersonalProfileInformation extends TenantPage {
    @FindBy(css = "#last_name")
    WebElement user_name_lastname;

    @FindBy(css = "#first_name")
    WebElement user_name_firstname;

    @FindBy(css = "#tel1")
    WebElement tel1;

    @FindBy(css = "#tel2")
    WebElement tel2;

    @FindBy(css = "#tel3")
    WebElement tel3;

    @FindBy(css = "#password")
    WebElement password;

    @FindBy(css = "#email_signature")
    WebElement mail_signature;

    @FindBy(css = "button[type = 'submit']")
    WebElement next_to_button;

    @FindBy(css = "form > div:nth-child(1) > div > div.ant-form-item-explain > div")
    WebElement username_error;

    @FindBy(css = "form > div:nth-child(4) > div > div.ant-form-item-explain > div")
    WebElement tel_error;

    @FindBy(css = "form > div:nth-child(5) > div > div.ant-form-item-explain > div")
    WebElement password_error;

    @FindBy(css = "form > div:nth-child(6) > div > div.ant-form-item-explain > div")
    WebElement mail_signature_error;


    String register_token_ppi;

    public Step3PersonalProfileInformation(WebDriver driver, String domain, String register_token_ppi) {
        super(driver, domain);
        this.register_token_ppi = register_token_ppi;
        PageFactory.initElements(driver, this);
    }

    public void linkToPersonalProfileInformation() {
        driver.get(domain + CREATE_TENANT_PATH + AUTHOR_TOKEN_PATH + register_token_ppi);
    }

    public void inputValidUsername() {
        // total length of first name and last name in range 2-50
        int length_of_last_name = RandomUtils.nextInt(19) + 1;
        int length_of_first_name = RandomUtils.nextInt(20 - length_of_last_name) + 1;
        // generate first name and last name by random text
        String last_name = RandomStringUtils.randomAlphabetic(length_of_last_name);
        String first_name = RandomStringUtils.randomAlphabetic(length_of_first_name);
        // Username
        user_name_lastname.sendKeys(last_name);
        user_name_firstname.sendKeys(first_name);
    }

    public void tel() {
        // total length of TEL in range 3-15
        int length_of_tel1 = RandomUtils.nextInt(13) + 1;
        int length_of_tel2 = RandomUtils.nextInt(14 - length_of_tel1) + 1;
        int length_of_tel3 = RandomUtils.nextInt(15 - length_of_tel1 - length_of_tel2) + 1;
        //Generate TEL1, TEL2, TEL3 by random number
        tel1.sendKeys(RandomStringUtils.random(length_of_tel1, false, true));
        tel2.sendKeys(RandomStringUtils.random(length_of_tel2, false, true));
        tel3.sendKeys(RandomStringUtils.random(length_of_tel3, false, true));
    }

    public void inputValidPassword() {
        // length of password in range 8-50
        // password include both letters and numbers
        int length_of_password = (int) ((Math.random() * (42) + 8));
        // generate password by random text
        String password_text = RandomStringUtils.randomAlphanumeric(length_of_password) + "@";
        // Password
        password.sendKeys(password_text);
    }

    public void inputValidEmailSignature() {
        // length of email signature in range 0-1000
        int length_of_email_signature = (int) (Math.random() * 1000);
        // generate email signature by random text
        String email_signature = RandomStringUtils.randomAlphabetic(length_of_email_signature);
        // Email signature
        mail_signature.sendKeys(email_signature);
    }

    public void nextToPaymentInformation() throws InterruptedException {
        // Go to お支払い情報_Step
        next_to_button.click();
        sleep(2000);
    }

    // Username
    public void verifyErrorMessageWhenLeaveUsernameBlank() throws InterruptedException {
        user_name_lastname.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
        sleep(2000);
        String text = username_error.getText();
        Assert.assertEquals(text, "必須項目です", "[Username] Message do not match");
    }

    public void verifyErrorMessageWhenInputUsernameExceed50HalfWidthCharacters() throws InterruptedException {
        int length_of_lastname = RandomUtils.nextInt(50) + 1;
        int length_of_firstname = 51 - length_of_lastname;
        user_name_lastname.sendKeys(RandomStringUtils.randomAlphabetic(length_of_lastname));
        user_name_firstname.sendKeys(RandomStringUtils.randomAlphabetic(length_of_firstname));
        sleep(2000);
        String text = username_error.getText();
        Assert.assertEquals(text, "50文字以内で入力してください。", "[Username] Message do not match");
    }

    public void verifyErrorMessageWhenInputUsernameExceed50FullWidthCharacters() throws InterruptedException {
        int length_of_lastname = RandomUtils.nextInt(50) + 1;
        int length_of_firstname = 51 - length_of_lastname;
        user_name_lastname
                .sendKeys(RandomStringUtils.random(length_of_lastname, 0x4e00, 0x4f80, true, false));
        user_name_firstname
                .sendKeys(RandomStringUtils.random(length_of_firstname, 0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = username_error.getText();
        Assert.assertEquals(text, "50文字以内で入力してください。", "[Username] Message do not match");
    }

    public void verifyErrorMessageWhenInputUsernameExceed50CharactersMixHalfAndFullWidth() throws InterruptedException {
        int length_of_lastname = RandomUtils.nextInt(50) + 1;
        int length_of_firstname = 51 - length_of_lastname;
        user_name_lastname.sendKeys(RandomStringUtils.randomAlphabetic(length_of_lastname));
        user_name_firstname.sendKeys(RandomStringUtils.random(length_of_firstname, 0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = username_error.getText();
        Assert.assertEquals(text, "50文字以内で入力してください。", "[Username] Message do not match");
    }

    public void verifyErrorMessageWhenInputUsernameContainsSpaceCharacters() throws InterruptedException {
        user_name_lastname.sendKeys(" ");
        sleep(2000);
        String text = username_error.getText();
        Assert.assertEquals(text, "スペースを入力することはできません。", "[Username] Message do not match");
    }

    // TEL
    public void verifyErrorMessageWhenDoNotFillInAllTelFields() throws InterruptedException {
        int number_of_tel1 = RandomUtils.nextInt(15) + 1;
        tel1.sendKeys(RandomStringUtils.random(number_of_tel1, false, true));
        sleep(2000);
        String text = tel_error.getText();
        Assert.assertEquals(text, "TELの欄は全て入力してください", "[TEL] Message do not match");
    }

    public void verifyErrorMessageWhenInputTelExceed15HalfWidthCharacters() throws InterruptedException {
        int number_of_tel1 = RandomUtils.nextInt(14) + 1;
        int number_of_tel2 = RandomUtils.nextInt(15 - number_of_tel1) + 1;
        int number_of_tel3 = 16 - number_of_tel1 - number_of_tel2;
        tel1.sendKeys(RandomStringUtils.random(number_of_tel1, false, true));
        tel2.sendKeys(RandomStringUtils.random(number_of_tel2, false, true));
        tel3.sendKeys(RandomStringUtils.random(number_of_tel3, false, true));
        sleep(2000);
        String text = tel_error.getText();
        Assert.assertEquals(text, "3つの入力欄を合わせて15桁以内で入力してください。", "[TEL] Message do not match");
    }

    public void verifyErrorMessageWhenInputTelContainsFullWidthCharacters() throws InterruptedException {
        tel1.sendKeys("１１１");
        sleep(2000);
        String text = tel_error.getText();
        Assert.assertEquals(text, "半角数字でご入力ください", "[TEL] Message do not match");
    }

    public void verifyErrorMessageWhenInputTelContainsLetterCharacters() throws InterruptedException {
        tel1.sendKeys(RandomStringUtils.randomAlphabetic(3));
        sleep(2000);
        String text = tel_error.getText();
        Assert.assertEquals(text, "半角数字でご入力ください", "[TEL] Message do not match");
    }

    // Password
    public void verifyErrorMessageWhenInputPasswordLessThan10HalfWidthCharacters() throws InterruptedException {
        password.sendKeys(RandomStringUtils.randomAlphabetic(9));
        sleep(2000);
        String text = password_error.getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    public void verifyErrorMessageWhenInputPasswordExceed50HalfWidthCharacters() throws InterruptedException {
        password.sendKeys(RandomStringUtils.randomAlphabetic(51));
        sleep(2000);
        String text = password_error.getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    public void verifyErrorMessageWhenInputPasswordContainsFullWidthCharacters() throws InterruptedException {
        password.sendKeys(RandomStringUtils.random(10, 0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = password_error.getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    public void verifyErrorMessageWhenInputPasswordDoNotMixAlphanumericalCharacters() throws InterruptedException {
        password.sendKeys(RandomStringUtils.random(10, true, false));
        sleep(2000);
        String text = password_error.getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    // Email signature
    public void verifyErrorMessageWhenInputEmailSignatureExceed1000HalfWidthCharacters() throws InterruptedException {
        mail_signature.sendKeys(RandomStringUtils.randomAlphabetic(1001));
        sleep(2000);
        String text = mail_signature_error.getText();
        Assert.assertEquals(text, "1000文字以内で入力してください。", "[Email signature] Message do not match");
    }

    public void verifyErrorMessageWhenInputEmailSignatureExceed1000FullWidthCharacters() throws InterruptedException {
        mail_signature
                .sendKeys(RandomStringUtils.random(1001, 0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = mail_signature_error.getText();
        Assert.assertEquals(text, "1000文字以内で入力してください。", "[Email signature] Message do not match");
    }

    public void verifyErrorMessageWhenInputEmailSignatureExceed1000CharactersMixHalfAndFullWidth() throws InterruptedException {
        int length_of_half = RandomUtils.nextInt(1000) + 1;
        String email_signature = RandomStringUtils.randomAlphabetic(length_of_half)
                + RandomStringUtils.random(1001 - length_of_half, 0x4e00, 0x4f80, true, false);
        mail_signature.sendKeys(email_signature);
        sleep(2000);
        String text = mail_signature_error.getText();
        Assert.assertEquals(text, "1000文字以内で入力してください。", "[Email signature] Message do not match");
    }
}
