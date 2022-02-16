package BasePage.createTenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static java.lang.Thread.sleep;

public class Personal_profile_information {
    public void username(WebDriver driver) {
        // total length of first name and last name in range 2-50
        int length_of_last_name = RandomUtils.nextInt(19) + 1;
        int length_of_first_name = RandomUtils.nextInt(20 - length_of_last_name) + 1;
        // generate first name and last name by random text
        String last_name = RandomStringUtils.randomAlphabetic(length_of_last_name);
        String first_name = RandomStringUtils.randomAlphabetic(length_of_first_name);
        // Username
        driver.findElement(By.cssSelector("#last_name")).sendKeys(last_name);
        driver.findElement(By.cssSelector("#first_name")).sendKeys(first_name);
    }

    public void tel(WebDriver driver) {
        // total length of TEL in range 3-15
        int length_of_tel1 = RandomUtils.nextInt(13) + 1;
        int length_of_tel2 = RandomUtils.nextInt(14 - length_of_tel1) + 1;
        int length_of_tel3 = RandomUtils.nextInt(15 - length_of_tel1 - length_of_tel2) + 1;
        //Generate TEL1, TEL2, TEL3 by random number
        driver.findElement(By.cssSelector("#tel1")).sendKeys(RandomStringUtils.random(length_of_tel1, false, true));
        driver.findElement(By.cssSelector("#tel2")).sendKeys(RandomStringUtils.random(length_of_tel2,false,true));
        driver.findElement(By.cssSelector("#tel3")).sendKeys(RandomStringUtils.random(length_of_tel3, false, true));
    }

    public void password(WebDriver driver) {
        // length of password in range 8-50
        // password include both letters and numbers
        int length_of_password = (int) ((Math.random() * (42) + 8));
        // generate password by random text
        String password = RandomStringUtils.randomAlphanumeric(length_of_password) + "@";
        // Password
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
    }

    public void email_signature(WebDriver driver) {
        // length of email signature in range 0-1000
        int length_of_email_signature = (int) (Math.random() * 1000);
        // generate email signature by random text
        String email_signature = RandomStringUtils.randomAlphabetic(length_of_email_signature);
        // Email signature
        driver.findElement(By.cssSelector("#email_signature")).sendKeys(email_signature);
    }

    public void next_to_payment_information(WebDriver driver) throws InterruptedException {
        // Go to お支払い情報_Step
        driver.findElement(By.cssSelector("button[type = 'submit']")).click();
        sleep(2000);
    }

    // Username
    public void leave_username_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#last_name")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "必須項目です", "[Username] Message do not match");
    }

    public void username_exceed_50_half_width_characters(WebDriver driver) throws InterruptedException {
        int length_of_lastname = RandomUtils.nextInt(50) + 1;
        int length_of_firstname = 51 - length_of_lastname;
        driver.findElement(By.cssSelector("#last_name")).sendKeys(RandomStringUtils.randomAlphabetic(length_of_lastname));
        driver.findElement(By.cssSelector("#first_name")).sendKeys(RandomStringUtils.randomAlphabetic(length_of_firstname));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "50文字以内で入力してください。", "[Username] Message do not match");
    }

    public void username_exceed_50_full_width_characters(WebDriver driver) throws InterruptedException {
        int length_of_lastname = RandomUtils.nextInt(50) + 1;
        int length_of_firstname = 51 - length_of_lastname;
        driver.findElement(By.cssSelector("#last_name"))
                .sendKeys(RandomStringUtils.random(length_of_lastname,0x4e00, 0x4f80, true, false));
        driver.findElement(By.cssSelector("#first_name"))
                .sendKeys(RandomStringUtils.random(length_of_firstname,0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "50文字以内で入力してください。", "[Username] Message do not match");
    }

    public void username_exceed_mix_50_half_and_full_width_characters(WebDriver driver) throws InterruptedException {
        int length_of_lastname = RandomUtils.nextInt(50) + 1;
        int length_of_firstname = 51 - length_of_lastname;
        driver.findElement(By.cssSelector("#last_name")).sendKeys(RandomStringUtils.randomAlphabetic(length_of_lastname));
        driver.findElement(By.cssSelector("#first_name")).sendKeys(RandomStringUtils.random(length_of_firstname,0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "50文字以内で入力してください。", "[Username] Message do not match");
    }

    public void username_contains_space_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#last_name")).sendKeys(" ");
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(1) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "スペースを入力することはできません。", "[Username] Message do not match");
    }

    // TEL
    public void do_not_fill_in_all_tel_fields(WebDriver driver) throws InterruptedException {
        int number_of_tel1 = RandomUtils.nextInt(15) + 1;
        driver.findElement(By.cssSelector("#tel1")).sendKeys(RandomStringUtils.random(number_of_tel1, false, true));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "TELの欄は全て入力してください", "[TEL] Message do not match");
    }

    public void tel_exceed_15_half_width_characters(WebDriver driver) throws InterruptedException {
        int number_of_tel1 = RandomUtils.nextInt(14) + 1;
        int number_of_tel2 = RandomUtils.nextInt(15 - number_of_tel1) + 1;
        int number_of_tel3 = 16 - number_of_tel1 - number_of_tel2;
        driver.findElement(By.cssSelector("#tel1")).sendKeys(RandomStringUtils.random(number_of_tel1, false, true));
        driver.findElement(By.cssSelector("#tel2")).sendKeys(RandomStringUtils.random(number_of_tel2, false, true));
        driver.findElement(By.cssSelector("#tel3")).sendKeys(RandomStringUtils.random(number_of_tel3, false, true));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "3つの入力欄を合わせて15桁以内で入力してください。", "[TEL] Message do not match");
    }

    public void tel_contains_full_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#tel1")).sendKeys("１１１");
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "半角数字でご入力ください", "[TEL] Message do not match");
    }

    public void tel_contains_letter_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#tel1")).sendKeys(RandomStringUtils.randomAlphabetic(3));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "半角数字でご入力ください", "[TEL] Message do not match");
    }

    // Password
    public void password_less_than_10_half_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#password")).sendKeys(RandomStringUtils.randomAlphabetic(9));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(5) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    public void password_exceed_50_half_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#password")).sendKeys(RandomStringUtils.randomAlphabetic(51));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(5) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    public void password_contains_full_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#password")).sendKeys(RandomStringUtils.random(10,0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(5) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    public void password_do_not_mix_alphanumerical_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#password")).sendKeys(RandomStringUtils.random(10,true,false));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(5) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "大小英数字記号混在で10-50桁で入力してください。", "[Password] Message do not match");
    }

    // Email signature
    public void email_signature_exceed_1000_half_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#email_signature")).sendKeys(RandomStringUtils.randomAlphabetic(1001));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(6) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "1000文字以内で入力してください。", "[Email signature] Message do not match");
    }

    public void email_signature_exceed_1000_full_width_characters(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector("#email_signature"))
                .sendKeys(RandomStringUtils.random(1001,0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(6) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "1000文字以内で入力してください。", "[Email signature] Message do not match");
    }

    public void email_signature_exceed_mix_1000_half_and_full_width_characters(WebDriver driver) throws InterruptedException {
        int length_of_half = RandomUtils.nextInt(1000) + 1;
        String email_signature = RandomStringUtils.randomAlphabetic(length_of_half)
                + RandomStringUtils.random(1001 - length_of_half,0x4e00, 0x4f80, true, false);
        driver.findElement(By.cssSelector("#email_signature")).sendKeys(email_signature);
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(6) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "1000文字以内で入力してください。", "[Email signature] Message do not match");
    }
}
