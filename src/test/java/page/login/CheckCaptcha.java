package page.login;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.common.Common;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
import static utilities.links.HBS.LOGIN_PATH;

public class CheckCaptcha {
    @FindBy(xpath = "//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")
    WebElement iframe_captcha;

    @FindBy(css = "div.recaptcha-checkbox-border")
    WebElement captcha_checkbox;

    WebDriver driver;
    Common common;
    String domain;
    WebDriverWait wait;

    public CheckCaptcha(WebDriver driver, Common common, String domain) {
        this.driver = driver;
        this.common = common;
        this.domain = domain;
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        PageFactory.initElements(driver,this);
    }
    public List<String> generateMailAndPassword() {
        String mail = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
        String password = RandomStringUtils.randomAlphabetic(10) + "@";
        return List.of(mail,password);
    }

    public void loginFail3times() throws InterruptedException {
        List<String> login_account = generateMailAndPassword();
        common.login(driver, domain + LOGIN_PATH, login_account.get(0), login_account.get(1));
        common.login(driver, domain + LOGIN_PATH, login_account.get(0), login_account.get(1));
        common.login(driver, domain + LOGIN_PATH, login_account.get(0), login_account.get(1));
        sleep(3000);
    }

    public void verifyThatAfterLoginFail3TimesCaptchaShouldBeDisplayed() {
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
