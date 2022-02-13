package BasePage.createMail;

import Common.Common;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Thread.sleep;

public class Basic_information {
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
            int length_of_insertion = RandomUtils.nextInt(5000) + 1;
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

        public void next_to_attachment_step (WebDriver driver, String role, Common cm){
            // Master, Administrator, Responsible person, Leader, Member
            if (cm.authorized(role, cm.role_list(5))) {
            // Next to 添付ファイル_Step
            driver.findElement(By.cssSelector("div:nth-child(4)>div>button")).click();
        }
    }
}
