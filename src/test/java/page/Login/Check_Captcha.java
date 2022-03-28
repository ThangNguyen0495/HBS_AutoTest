package page.Login;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.Common.Common;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
import static utilities.Link_and_Path.HBS.login_path;

public class Check_Captcha {
    @FindBy(xpath = "//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")
    WebElement iframe_captcha;

    @FindBy(css = "div.recaptcha-checkbox-border")
    WebElement captcha_checkbox;

    WebDriver driver;
    Common common;
    String domain;
    WebDriverWait wait;

    public Check_Captcha(WebDriver driver, Common common, String domain) {
        this.driver = driver;
        this.common = common;
        this.domain = domain;
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        PageFactory.initElements(driver,this);
    }
    public List<String> generate_mail_and_password() {
        String mail = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
        String password = RandomStringUtils.randomAlphabetic(10) + "@";
        return List.of(mail,password);
    }

    public void Login_fail_3_times() throws InterruptedException {
        List<String> login_account = generate_mail_and_password();
        common.login(driver, domain + login_path, login_account.get(0), login_account.get(1));
        common.login(driver, domain + login_path, login_account.get(0), login_account.get(1));
        common.login(driver, domain + login_path, login_account.get(0), login_account.get(1));
        sleep(3000);
    }

    public void Captcha_should_be_displayed() {
        boolean check;
        try {
            check = true;
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe_captcha));
            wait.until(ExpectedConditions.elementToBeClickable(captcha_checkbox)).click();
        }
        catch (TimeoutException ex) {
            check = false;
        }
        Assert.assertTrue(check, "[Captcha][Failed] Captcha is not displayed after login fail 3 times.");
    }
}
