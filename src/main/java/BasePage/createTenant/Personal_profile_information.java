package BasePage.createTenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class Personal_profile_information {
    public void username(WebDriver driver) {
        // total length of first name and last name in range 2-50
        int leghth_of_last_name = RandomUtils.nextInt(19) + 1;
        int length_of_firt_name = RandomUtils.nextInt(20 - leghth_of_last_name) + 1;
        // generate first name and last name by random text
        String last_name = RandomStringUtils.randomAlphabetic(leghth_of_last_name);
        String first_name = RandomStringUtils.randomAlphabetic(length_of_firt_name);
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
        long TEL1 = (long) (Math.random() * (Math.pow(10, length_of_tel1)));
        long TEL2 = (long) (Math.random() * (Math.pow(10, length_of_tel2)));
        long TEL3 = (long) (Math.random() * (Math.pow(10, length_of_tel3)));
        driver.findElement(By.cssSelector("#tel1")).sendKeys(Long.toString(TEL1));
        driver.findElement(By.cssSelector("#tel2")).sendKeys(Long.toString(TEL2));
        driver.findElement(By.cssSelector("#tel3")).sendKeys(Long.toString(TEL3));
    }

    public void password(WebDriver driver) {
        // length of password in range 8-50
        // password include both letters and numbers
        int length_of_password = (int) ((Math.random() * (43) + 8));
        // generate password by random text
        String password = RandomStringUtils.randomAlphanumeric(length_of_password);
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
        driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>button")).click();
        sleep(1000);
    }
}
