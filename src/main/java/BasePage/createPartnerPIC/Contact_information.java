package BasePage.createPartnerPIC;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;

import static java.lang.Thread.sleep;

public class Contact_information {
    public void contact_name(WebDriver driver) throws InterruptedException {
        // total length of first name and last name in range 2-50
        int leghth_of_last_name = RandomUtils.nextInt(19) + 1;
        int length_of_firt_name = RandomUtils.nextInt(20 - leghth_of_last_name) + 1;
        // generate first name and last name by random text
        String last_name = RandomStringUtils.randomAlphabetic(leghth_of_last_name);
        String first_name = RandomStringUtils.randomAlphabetic(length_of_firt_name);
        // Username
        driver.findElement(By.cssSelector("#last_name")).sendKeys(last_name);
        driver.findElement(By.cssSelector("#first_name")).sendKeys(first_name);
        sleep(3000);
    }

    public void affiliated_business_partner(WebDriver driver, Actions key) throws InterruptedException {
        // Affiliated business partner
        driver.findElement(By.cssSelector("#organization")).click();
        // random affiliated business partner in range 1-20
        int distributor_id = RandomUtils.nextInt(20) + 1;
        for (int i = 0; i < distributor_id; i++) {
            key.sendKeys(Keys.DOWN).perform();
        }
        sleep(1000);
        key.sendKeys(Keys.ENTER).perform();
    }

    public void TO(WebDriver driver) {
        // TO
        // length of TO in range 12-100
        int length_of_to = RandomUtils.nextInt(88) + 1;
        String TO = RandomStringUtils.randomAlphabetic(length_of_to);
        driver.findElement(By.cssSelector("#email")).sendKeys(TO + "@gmail.com");
    }

    public void CC(WebDriver driver) {
        // CC
        // length of CC in range 12-100
        int length_of_cc = RandomUtils.nextInt(88) + 1;
        String CC = RandomStringUtils.randomAlphabetic(length_of_cc);
        driver.findElement(By.cssSelector("#ccEmails")).sendKeys(CC + "@gmail.com");
    }

    public void tel(WebDriver driver) {
        // TEL
        // total length of tel1, tel2, tel3 in range 3-15
        int length_of_tel1 = RandomUtils.nextInt(13);
        int length_of_tel2 = RandomUtils.nextInt(14 - length_of_tel1);
        int length_of_tel3 = RandomUtils.nextInt(15 - length_of_tel1 - length_of_tel2);
        long tel1 = (long) (Math.random() * (Math.pow(10, length_of_tel1)));
        long tel2 = (long) (Math.random() * (Math.pow(10, length_of_tel2)));
        long tel3 = (long) (Math.random() * (Math.pow(10, length_of_tel3)));
        driver.findElement(By.cssSelector("#tel1")).sendKeys(Long.toString(tel1));
        driver.findElement(By.cssSelector("#tel2")).sendKeys(Long.toString(tel2));
        driver.findElement(By.cssSelector("#tel3")).sendKeys(Long.toString(tel3));
    }

    public void position(WebDriver driver) {
        // Position
        // length of position in range 1-50
        int length_of_position = RandomUtils.nextInt(50);
        String position = RandomStringUtils.randomAlphabetic(length_of_position);
        driver.findElement(By.cssSelector("#position")).sendKeys(position);
    }

    public void department(WebDriver driver) throws InterruptedException {
        // Department
        // length of department in range 1-50
        int length_of_department = RandomUtils.nextInt(50);
        String department = RandomStringUtils.randomAlphabetic(length_of_department);
        driver.findElement(By.cssSelector("#department")).sendKeys(department);
        sleep(1000);
    }

    public void in_house_person_in_charge(WebDriver driver, Actions key) throws InterruptedException {
        // In-house person in charge
        driver.findElement(By.cssSelector("#staff")).click();
        int in_house_person_in_charge = RandomUtils.nextInt(20);
        for (int i = 0; i <= in_house_person_in_charge; i++) {
            key.sendKeys(Keys.DOWN).perform();
        }
        // Wait and select In-house person in charge
        sleep(1000);
        key.sendKeys(Keys.ENTER).perform();
    }

    public void last_visit_date(WebDriver driver, Actions key) throws InterruptedException {
        // Last visit date
        LocalDate date_now = LocalDate.now();
        // (sub_date = date_now - last_visit_date) in range (year_now - 1900)*365 days/year
        int sub_date = RandomUtils.nextInt((date_now.getYear() - 1900) * 365) + 1;
        LocalDate last_visit_date = date_now.minusDays(sub_date);
        driver.findElement(By.cssSelector("#last_visit")).sendKeys(last_visit_date.toString());
        key.sendKeys(Keys.ENTER).perform();
        sleep(1000);
    }

    public void tag(WebDriver driver, Actions key) throws InterruptedException {
        // Tag
        // number of tags has been selected in range 1-10
        int number_of_tags = RandomUtils.nextInt(10) + 1;
        // Open Tag dropdown
        driver.findElement(By.cssSelector("div.ant-select-selection-overflow")).click();
        for (int i = 0; i < number_of_tags; i++) {
            key.sendKeys(Keys.DOWN).perform();
            key.sendKeys(Keys.ENTER).perform();
        }
    }

    public void compatibility(WebDriver driver) {
        // Compatibility
        // 1: Good, 2: Bad
        int compatibility = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-link.ant-btn-sm.ant-btn-icon-only.ContactForm-button-1pNJJ:nth-child(" + Integer.toString(compatibility) + ")"))
                .click();
    }

    public void comment(WebDriver driver) {
        // Comment
        // lenght of comment in range 0-1000
        int lenght_of_comment = RandomUtils.nextInt(1001);
        String comment = RandomStringUtils.randomAlphabetic(lenght_of_comment);
        driver.findElement(By.cssSelector("textarea[id='comment.content']")).sendKeys(comment);
    }

    public void fixed_comment(WebDriver driver) {
        // Fixed comment
        // 0: Do not fixed comment, 1: Fixed comment
        int fixed_comment = RandomUtils.nextInt(2);
        if (fixed_comment == 1) {
            driver.findElement(By.cssSelector("div:nth-child(3) > div > div > div > button:nth-child(1)")).click();
        }
    }

    public void switch_to_delivery_conditions_tab(WebDriver driver) {
        // Switch to Delivery conditions tab
        driver.findElement(By.cssSelector("#rc-tabs-0-tab-2")).click();
    }

}
