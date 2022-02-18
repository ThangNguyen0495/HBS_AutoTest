package BasePage.createMail;

import Common.Common;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static java.lang.Thread.sleep;

public class Basic_information {
    /*
        Process function
    */
    public void format(WebDriver driver, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Format
            // 1: Text, 2: Html
            int format = RandomUtils.nextInt(2) + 1;
            driver.findElement(By.cssSelector("#text_format>label:nth-child(" + format + ")>span.ant-radio>input")).click();
            sleep(3000);
        }
    }

    public void distributor(WebDriver driver, Actions key, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Distributor
            // random distributor in range 1-20
            int distributor_id = RandomUtils.nextInt(20) + 1;
            driver.findElement(By.cssSelector("div.ant-form-item-control-input-content>div>div>div>div.ant-select-selector")).click();
            for (int i = 0; i < distributor_id; i++) {
                key.sendKeys(Keys.DOWN).perform();
            }
            sleep(1000);
            key.sendKeys(Keys.ENTER).perform();
        }
    }

    public void subject(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // length of subject in range 1-100
            int length_of_subject = RandomUtils.nextInt(100) + 1;
            String subject = RandomStringUtils.randomAlphabetic(length_of_subject);
            driver.findElement(By.cssSelector("input[type='text']")).sendKeys(subject);
        }
    }

    public void insertion(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // length of insertion in range 1-5000
            int length_of_insertion = RandomUtils.nextInt(50) + 1;
            String insertion = RandomStringUtils.randomAlphabetic(length_of_insertion);
            driver.findElement(By.cssSelector("textarea[id='text']")).sendKeys(insertion);
        }
    }

    public void send_a_copy_to_the_distributor(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Send a copy to the distributor
            // 0: Do not sent, 1: Send copy mail
            int send_copy = RandomUtils.nextInt(2);
            if (send_copy == 1) {
                driver.findElement(By.cssSelector("#send_copy_to_sender>span")).click();
            }
        }
    }

    public void next_to_attachment_step(WebDriver driver, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Next to 添付ファイル_Step
            driver.findElement(By.cssSelector("div:nth-child(4)>div>button")).click();

            sleep(1000);

            // Check current tab
            boolean check = driver.findElement(By.cssSelector("div:nth-child(2)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check,"[Failed] Can not next to Attachment from Basic information.");
        }
    }

    /*
        Validation function
    */

    // Distributor
    public void leave_distributor_blank(WebDriver driver) throws InterruptedException {
        sleep(3000);
        driver.findElement(By.cssSelector("div.ant-col-9> div > div > div > div > div > span.ant-select-clear")).click();
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(2) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "必須項目です。", "[Distributor] Message do not match");
    }

    // Subject
    public void leave_subject_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#subject")).sendKeys("leave_blank");
        sleep(100);
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(3) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "必須項目です。", "[Subject] Message do not match");
    }

    public void subject_exceed_100_half_width_characters(WebDriver driver) throws InterruptedException {
        sleep(3000);
        driver.findElement(By.cssSelector("#subject")).sendKeys(RandomStringUtils.randomAlphabetic(101));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(3) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Subject] Message do not match");
    }

    public void subject_exceed_100_full_width_characters(WebDriver driver) throws InterruptedException {
        sleep(3000);
        driver.findElement(By.cssSelector("#subject")).sendKeys(RandomStringUtils.random(101, 0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(3) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Subject] Message do not match");
    }

    public void subject_exceed_100_mix_half_and_full_width_characters(WebDriver driver) throws InterruptedException {
        sleep(3000);
        int length_of_half_width = RandomUtils.nextInt(100) + 1;
        String subject = RandomStringUtils.randomAlphabetic(length_of_half_width)
                + RandomStringUtils.random(101 - length_of_half_width, 0x4e00, 0x4f80, true, false);
        driver.findElement(By.cssSelector("#subject")).sendKeys(subject);
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(3) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Subject] Message do not match");
    }

    // Insertion
    public void leave_insertion_blank(WebDriver driver, Actions key) throws InterruptedException {
        driver.findElement(By.cssSelector("#text")).sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "必須項目です。", "[Insertion] Message do not match");
    }

    public void insertion_exceed_10000_half_width_characters(WebDriver driver) throws InterruptedException {
        sleep(3000);
        driver.findElement(By.cssSelector("#text")).sendKeys(RandomStringUtils.randomAlphabetic(10001));
        // Scroll down
        ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Insertion] Message do not match");
    }

    public void insertion_exceed_5000_full_width_characters(WebDriver driver) throws InterruptedException {
        sleep(3000);
        // minJpnCharCode: 0x4e00
        // maxJpnCharCode: 0x4f80
        driver.findElement(By.cssSelector("#text")).sendKeys(RandomStringUtils.random(5001, 0x4e00, 0x4f80, true, false));
        // Scroll down
        ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Insertion] Message do not match");
    }

    public void insertion_exceed_5000_mix_half_and_full_width_characters(WebDriver driver) throws InterruptedException {
        sleep(3000);
        int length_of_half_width = RandomUtils.nextInt(5000) + 1;
        String insertion = RandomStringUtils.randomAlphabetic(length_of_half_width)
                + RandomStringUtils.random(5001 - length_of_half_width, 0x4e00, 0x4f80, true, false);
        // minJpnCharCode: 0x4e00
        // maxJpnCharCode: 0x4f80
        driver.findElement(By.cssSelector("#text")).sendKeys(insertion);
        // Scroll down
        ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
        sleep(2000);
        String text = driver.findElement(By.cssSelector("form > div:nth-child(4) > div > div.ant-form-item-explain > div")).getText();
        Assert.assertEquals(text, "全角5000文字または半角10000文字以内で入力してください。", "[Insertion] Message do not match");
    }

    // Delete button
    public void delete_button_should_be_disable(WebDriver driver) {
        Assert.assertFalse(driver.findElement(By.cssSelector("button.ant-btn-danger")).isEnabled(), "[Delete] button is not getting dissable.");
    }

    public void do_you_want_to_delete_this_delivered_email_OK(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click delete button
        driver.findElement(By.cssSelector("button.ant-btn-danger")).click();
        sleep(1000);

        // Click OK button
        driver.findElement(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(2)")).click();
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not delete delivered email");
    }

    public void do_you_want_to_delete_this_delivered_email_Cancel(WebDriver driver) throws InterruptedException {
        // Click delete button
        driver.findElement(By.cssSelector("button.ant-btn-danger")).click();
        sleep(1000);

        // Click delete button
        driver.findElement(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(1)")).click();
        sleep(1000);

        boolean check = true;
        try {
            driver.findElement(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(1)")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        Assert.assertFalse(check, "[Failed] Can not close delete delivered email popup");
    }

    public void make_a_copy(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click make a copy button
        driver.findElement(By.cssSelector("div:nth-child(1)>button.ant-btn-sm")).click();
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(),url_mail_list, "[Failed] Can not make a copy of delivered email.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_OK(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click save as draft button
        driver.findElement(By.cssSelector("div:nth-child(2)>button.ant-btn-sm")).click();
        sleep(1000);
        driver.findElement(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(2)")).click();
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(),url_mail_list, "[Failed] Can not save delivered as draft.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel(WebDriver driver) throws InterruptedException {
        // Click save as draft button
        driver.findElement(By.cssSelector("div:nth-child(2)>button.ant-btn-sm")).click();
        sleep(1000);

        // Click cancel button
        driver.findElement(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(1)")).click();
        sleep(1000);

        // Check popup close
        boolean check = true;
        try {
            driver.findElement(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(1)")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        Assert.assertFalse(check, "[Failed] Can not close save delivered as draft popup.");
    }
}


