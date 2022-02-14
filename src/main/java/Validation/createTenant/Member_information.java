package Validation.createTenant;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static java.lang.Thread.sleep;

public class Member_information {
    // Email
    public void leave_email_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#email")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(1000);
        String text = driver.findElement(By.cssSelector("div:nth-child(1)>div>div>div[role='alert']")).getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Email] Message is not match");
    }

    public void email_exceed_100_characters(WebDriver driver) {
        driver.findElement(By.cssSelector("#email")).sendKeys(RandomStringUtils.randomAlphabetic(101));
        String text = driver.findElement(By.cssSelector("div:nth-child(1)>div>div>div[role='alert']")).getText();
        Assert.assertEquals(text, "メールアドレスを入力してください", "[Email] Message is not match");
    }

    // Password
    public void leave_password_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#password")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(1000);
        String text = driver.findElement(By.cssSelector("div:nth-child(2)>div>div>div[role='alert']")).getText();
        Assert.assertEquals(text, "パスワードを入力してください", "[Password] Message is not match");
    }

    public void password_less_than_8_characters(WebDriver driver) {
        driver.findElement(By.cssSelector("")).sendKeys(RandomStringUtils.randomAlphabetic(7));
        String text = driver.findElement(By.cssSelector("div:nth-child(2)>div>div>div[role='alert']")).getText();
        Assert.assertEquals(text, "大小英数字記号混在で8-50桁で入力してください。", "[Password] Message is not match");
    }

    // Confirm password
    public void leave_confirm_password_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#password_confirm")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(1000);
        String text = driver.findElement(By.cssSelector("div:nth-child(3)>div>div>div[role='alert']")).getText();
        Assert.assertEquals(text, "パスワード確認を入力してください", "[Confirm password] Message is not match");
    }

    //Register member information
    public void register_member_information_should_be_disable(WebDriver driver) throws InterruptedException {
        sleep(1000);
        boolean button_is_enable = driver.findElement(By.cssSelector("button[type='submit']")).isEnabled();
        Assert.assertFalse(button_is_enable, "[Register member information] Button is not getting disable.");
    }
}
