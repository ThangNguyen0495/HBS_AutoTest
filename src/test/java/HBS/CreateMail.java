package HBS;

import CreateDummyData.findDate;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Duration;
import java.time.LocalTime;

import static Variable.Variable.*;
import static java.lang.Thread.sleep;

public class CreateMail {
    @Test

    public void 配信メール予約() throws InterruptedException, IOException {

        //Config Webdriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        String baseURL = url + delivery_email_reservation_path;
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //Login
        driver.get(baseURL);
        driver.findElement(By.cssSelector("#username")).sendKeys(master_email);
        driver.findElement(By.cssSelector("#password")).sendKeys(master_password);
        driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.LoginForm-button-3lInS")).click();
        sleep(3000);

        //****** 基本情報 ****** //
        //** Generate subject **//
        // length of subject in range 1-100
        int length_of_subject = RandomUtils.nextInt(100) + 1;

        // generate subject by random text
        String subject = RandomStringUtils.randomAlphabetic(length_of_subject);

        //** Generate insertion **//
        // length of insertion in range 1-5000
        int length_of_insertion = RandomUtils.nextInt(5000) + 1;

        // generate insertion by random text
        String insertion = RandomStringUtils.randomAlphabetic(length_of_insertion);

        //** Fill in Basic information form **//
        // Format
        // 1: Text, 2: Html
        int format = RandomUtils.nextInt(2) + 1;
        driver.findElement(By.cssSelector("#text_format>label:nth-child(" + Integer.toString(format) + ")>span.ant-radio>input")).click();

        // Distributor
        // random distributor in range 1-20
        int distributor_id = RandomUtils.nextInt(20) + 1;
        driver.findElement(By.cssSelector("div.ant-form-item-control-input-content>div>div>div>div.ant-select-selector")).click();
        sleep(5000);
        Actions key = new Actions(driver);
        for (int i = 0; i < distributor_id; i++) {
            key.sendKeys(Keys.DOWN).perform();
        }
        sleep(5000);
        key.sendKeys(Keys.ENTER).perform();

        // Subject
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys(subject);

        // Insertion
        driver.findElement(By.cssSelector("textarea[id='text']")).sendKeys(insertion);

        // Send a copy to the distributor
        // 0: Do not sent, 1: Send copy mail
        int send_copy = RandomUtils.nextInt(2);
        if (send_copy == 1) {
            driver.findElement(By.cssSelector("#send_copy_to_sender>span")).click();
        }

        // Next to 添付ファイル_Step
        driver.findElement(By.cssSelector("div:nth-child(4)>div>button")).click();

        //****** 添付ファイル ****** //
        //** Generate test file **//
        new RandomAccessFile("text.txt", "rw").setLength(2 * 1024 * 1024);
        new RandomAccessFile("excel.xlsx", "rw").setLength(2 * 1024 * 1024);
        new RandomAccessFile("word.docx", "rw").setLength(2 * 1024 * 1024);
        new RandomAccessFile("pdf.pdf", "rw").setLength(2 * 1024 * 1024);

        // wait 添付ファイル_Step has been loaded
        sleep(1000);

        // 0: upload 1 file, 1: upload multi file
        int upload = RandomUtils.nextInt(2);
        if (upload == 0) {
            driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\text.txt");
        } else {
            driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\text.txt");
            driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
            driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\word.docx");
            driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\pdf.pdf");
        }

        // wait file have been uploaded
        sleep(5000);

        // Next to 宛先選択_Step
        driver.findElement(By.cssSelector("div:nth-child(4)>div>button")).click();


        //****** 宛先選択 ****** //
        //** Delivery information **//
        //** Delivery type **//
        // 1: Deliver the matter, 2: Deliver personnel, 3: Deliver information
        int delivery_type = 3;//RandomUtils.nextInt(3) + 1;
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(" + Integer.toString(delivery_type) + ") > span > input")).click();

        // Search destination selection by delivery type
        // Delivery type: Deliver the matter
        if (delivery_type == 1) {
            // ** Delivery area **//
            // 1: Hokaido, 2: Tohoku, 3: Kanto, 4:Chubu , 5:Tokai , 6:Kansai , 7:Shikoku , 8:China , 9:Kyushu , 10: others
            // number of delivery area have been selected in range 1-10
            int number_of_delivery_area = RandomUtils.nextInt(10) + 1;

            //select delivery area from 1 to number_of_delivery_area
            for (int i = 1; i <= number_of_delivery_area; i++) {
                driver.findElement(By.cssSelector("label:nth-child(" + Integer.toString(i) + ")>span>input[id^='wants_location']")).click();
            }

            //** Delivery occupation **//
            // 1: Development, 2:Infrastructure, 3: Others
            int delivery_occupation = RandomUtils.nextInt(3) + 1;
            driver.findElement(By.cssSelector("#jobtype > label:nth-child(" + Integer.toString(delivery_occupation) + ")>span:nth-child(1)")).click();

            // Delivery occupation: development
            if (delivery_occupation == 1) {
                //Details of delivery occupation
                // 1:Designer, 2:FE, 3:BE, 4:PM/Director, 5:Others
                int detail_of_delivery_occupation = RandomUtils.nextInt(5) + 1;
                driver.findElement(By.cssSelector("#jobtype_dev > label:nth-child(" + Integer.toString(detail_of_delivery_occupation) + ") > span > input")).click();

                // Delivery skill details
                // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Inexperienced
                // number of delivery skill details have been selected in range 1-7
                int number_of_delivery_skill_details = RandomUtils.nextInt(7) + 1;

                // select delivery skill details from 1 to number_of_delivery_skill_details
                for (int i = 1; i <= number_of_delivery_skill_details; i++) {
                    driver.findElement(By.cssSelector("label:nth-child(" + Integer.toString(i) + ")>span>input[id^='jobskill_dev']")).click();
                }
            }
            // Delivery occupation: infrastructure
            else if (delivery_occupation == 2) {
                //Details of delivery occupation
                // 1:Server, 2:Network, 3:Security, 4:Database, 5:Information system, 6:Others
                int detail_of_delivery_occupation = RandomUtils.nextInt(6) + 1;
                driver.findElement(By.cssSelector("#jobtype_infra > label:nth-child(" + Integer.toString(detail_of_delivery_occupation) + ") > span > input")).click();

                // Delivery skill details
                // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Surveillance, 8:Inexperienced
                // number of delivery skill details have been selected in range 1-8
                int number_of_delivery_skill_details = RandomUtils.nextInt(8) + 1;

                // select delivery skill details from 1 to number_of_delivery_skill_details
                for (int i = 1; i <= number_of_delivery_skill_details; i++) {
                    driver.findElement(By.cssSelector("label:nth-child(" + Integer.toString(i) + ")>span>input[id^='jobskill_infra']")).click();
                }
            }
            // Delivery occupation: others
            else {
                //Details of delivery occupation
                // 1:Sales/office work, 2:Base station, 3:Call center support desk, 4:Others
                int detail_of_delivery_occupation = RandomUtils.nextInt(4) + 1;
                driver.findElement(By.cssSelector("#jobtype_other > label:nth-child(" + Integer.toString(detail_of_delivery_occupation) + ") > span > input")).click();
            }

            //** Delivery commercial distribution **//
            // 1:End direct/main contract direct , 2:Primary contract, 3:Secondary contract, 4:Third contract/unknown
            int delivery_commercial_distribution = RandomUtils.nextInt(4) + 1;
            driver.findElement(By.cssSelector("#job_syouryu > label:nth-child(" + Integer.toString(delivery_commercial_distribution) + ") > span.ant-radio > input")).click();
        }

        // Delivery type: Deliver personnel
        else if (delivery_type == 2) {
            // ** Delivery area **//
            // 1: Hokaido, 2: Tohoku, 3: Kanto, 4:Chubu , 5:Tokai , 6:Kansai , 7:Shikoku , 8:China , 9:Kyushu , 10: others
            // number of delivery area have been selected in range 1-10
            int number_of_delivery_area = RandomUtils.nextInt(10) + 1;

            //select delivery area from 1 to number_of_delivery_area
            for (int i = 1; i <= number_of_delivery_area; i++) {
                driver.findElement(By.cssSelector("label:nth-child(" + Integer.toString(i) + ")>span>input[id^='wants_location']")).click();
            }

            //** Delivery occupation **//
            // 1: Development, 2:Infrastructure, 3: Others
            int delivery_occupation = 3;//RandomUtils.nextInt(3) + 1;

            // select delivery occupation from 1 to delivery_occupation
            for (int i = 1; i <= delivery_occupation; i++) {
                // Delivery occupation: development
                if (i == 1) {
                    // Development checkbox
                    driver.findElement(By.cssSelector("#personneltype_dev")).click();

                    //Details of delivery occupation
                    // 1:Designer, 2:FE, 3:BE, 4:PM/Director, 5:Others
                    int detail_of_delivery_occupation = RandomUtils.nextInt(5) + 1;

                    // select details of delivery occupation from 1 to detail_of_delivery_occupation
                    for (int j = 1; j <= detail_of_delivery_occupation; j++) {
                        driver.findElement(By.cssSelector("div.ant-row.ant-row-start>label:nth-child(" + Integer.toString(j) + ")>span>input[id^='personneltype_dev']")).click();
                    }

                    // Delivery skill details
                    // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Inexperienced
                    // number of delivery skill details have been selected in range 1-7
                    int number_of_delivery_skill_details = RandomUtils.nextInt(7) + 1;

                    // select delivery skill details from 1 to number_of_delivery_skill_details
                    for (int k = 1; k <= number_of_delivery_skill_details; k++) {
                        driver.findElement(By.cssSelector("label:nth-child(" + Integer.toString(k) + ")>span>input[id^='personnelskill_dev']")).click();
                    }
                }
                // Delivery occupation: infrastructure
                else if (i == 2) {
                    // Infrastructure checkbox
                    driver.findElement(By.cssSelector("#personneltype_infra")).click();

                    //Details of delivery occupation
                    // 1:Server, 2:Network, 3:Security, 4:Database, 5:Information system, 6:Others
                    int detail_of_delivery_occupation = RandomUtils.nextInt(6) + 1;

                    //select details of delivery occupation from 1 to detail_of_delivery_occupation
                    for (int j = 1; j <= detail_of_delivery_occupation; j++) {
                        driver.findElement(By.cssSelector("div.ant-form-item-control-input-content>label:nth-child(" + Integer.toString(j) + ")>span>input[id^='personneltype_infra']")).click();
                    }

                    // Delivery skill details
                    // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Surveillance, 8:Inexperienced
                    // number of delivery skill details have been selected in range 1-8
                    int number_of_delivery_skill_details = RandomUtils.nextInt(8) + 1;

                    // select delivery skill details from 1 to number_of_delivery_skill_details
                    for (int k = 1; k <= number_of_delivery_skill_details; k++) {
                        driver.findElement(By.cssSelector("label:nth-child(" + Integer.toString(k) + ")>span>input[id^='personnelskill_infra']")).click();
                    }
                }
                // Delivery occupation: others
                else {
                    // Others checkbox
                    driver.findElement(By.cssSelector("#personneltype_other")).click();

                    //Details of delivery occupation
                    // 1:Sales/office work, 2:Base station, 3:Call center support desk, 4:Others
                    int detail_of_delivery_occupation = RandomUtils.nextInt(4) + 1;
                    for (int j = 1; j <= detail_of_delivery_occupation; j++) {
                        driver.findElement(By.cssSelector("div.ant-form-item-control-input-content>label:nth-child(" + Integer.toString(j) + ")>span>input[id^='personneltype_other']")).click();
                    }

                    //** Delivery employment form **//
                    // 1:Proper , 2:Freelance
                    int delivery_employment_form = RandomUtils.nextInt(2) + 1;
                    driver.findElement(By.cssSelector("#job_koyou > label:nth-child(" + Integer.toString(delivery_employment_form) + ") > span > input")).click();

                    //** Delivery commercial distribution **//
                    // 1:Company affiliation , 2:1 company affiliation, 3:2 company affiliation, 4:Affiliation/unknown for 3 or more companies
                    int delivery_commercial_distribution = RandomUtils.nextInt(4) + 1;
                    driver.findElement(By.cssSelector("#personnel_syouryu > label:nth-child(" + Integer.toString(delivery_commercial_distribution) + ") > span > input")).click();
                }
            }
        }

        //** Commitment **//
        // Account status
        // 0: Do not search by Account status, 1: add Account status conditions
//        int account_status = RandomUtils.nextInt(2);
//        if (account_status == 1) {
//            // add Account status conditions
//            driver.findElement(By.cssSelector("div:nth-child(2) > div.ant-col.ant-col-19.ant-form-item-control > div > div > div > div > button")).click();
//            // 1: Prospect, 2:Approached, 3:Information exchanged, 4:Contract record available
//            int account_status_condition = RandomUtils.nextInt(4) + 1;
//            // select Account status conditions
//            for (int i = 1; i <= account_status_condition; i++) {
//                driver.findElement(By.cssSelector("label:nth-child(" + Integer.toString(i) + ")>span>input[id^='contact__org']")).click();
//            }
//        }
//
//        // In-house person in charge
//        // 0: Do not search by In-house person in charge, 1: add In-house person in charge condition
//        int in_house_person_in_charge = RandomUtils.nextInt(2);
//        if (in_house_person_in_charge == 1) {
//            // add In-house person in charge condition
//            driver.findElement(By.cssSelector("div:nth-child(3) > div > div > div > div > div > div > button")).click();
//            sleep(3000); // Wait dropdown has been loaded
//            driver.findElement(By.cssSelector("#contact__staff")).click(); // Open In-house person in charge dropdown
//            int id = RandomUtils.nextInt(20);
//            for (int i = 0; i <= id; i++) {
//                key.sendKeys(Keys.DOWN).perform();
//            }
//            // Wait and select In-house person in charge
//            sleep(1000);
//            key.sendKeys(Keys.ENTER).perform();
//        }
//
//        // Compatibility
//        // 0: Do not search by Compatibility, 1: add Compatibility condition
//        int compatibility = RandomUtils.nextInt(2);
//        if (compatibility == 1) {
//            // add Compatibility condition
//            driver.findElement(By.cssSelector("div:nth-child(4) > div > div > div > div > div > button")).click();
//
//            // Open Compatibility dropdown
//            driver.findElement(By.cssSelector("#contact__category")).click();
//
//            // 0: Like, 1: Dislike
//            int compatibility_condition_1 = RandomUtils.nextInt(2);
//            for (int i = 0; i <= compatibility_condition_1; i++) {
//                key.sendKeys(Keys.DOWN).perform();
//            }
//            key.sendKeys(Keys.ENTER).perform();
//
//            // 0: Matches with, 1: Do not matches with
//            int compatibility_condition_2 = RandomUtils.nextInt(2);
//            driver.findElement(By.cssSelector("div:nth-child(2) > div > span.ant-select-selection-item")).click();
//            for (int i = 0; i <= compatibility_condition_2; i++) {
//                key.sendKeys(Keys.DOWN).perform();
//            }
//            key.sendKeys(Keys.ENTER).perform();
//        }
//
//        // Tag
//        // 0: Do not search by Tag, 1: add Tag condition
//        int tag = RandomUtils.nextInt(2);
//        if (tag == 1) {
//            // add Tag condition
//            driver.findElement(By.cssSelector("div:nth-child(5) > div > div > div > div > div > div > button")).click();
//            // wait Tag dropdown has been loaded
//            sleep(3000);
//            // number of tags has been selected in range 1-5
//            int number_of_tags = RandomUtils.nextInt(5) + 1;
//            // Open Tag dropdown
//            driver.findElement(By.cssSelector("div.ant-col.ant-col-12 > div > div > div > div > div")).click();
//            for (int i = 0; i < number_of_tags; i++) {
//                key.sendKeys(Keys.DOWN).perform();
//                key.sendKeys(Keys.ENTER).perform();
//            }
//
//            // 0: AND, 1: OR
//            int tag_condition_2 = RandomUtils.nextInt(2);
//            driver.findElement(By.cssSelector("div.ant-col.ant-col-2 > div > div > span.ant-select-selection-item")).click();
//            for (int i = 0; i <= tag_condition_2; i++) {
//                key.sendKeys(Keys.DOWN).perform();
//            }
//            key.sendKeys(Keys.ENTER).perform();
//        }

        // Search
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();
        sleep(5000);

        // Select contact
        // 0: select all contact, 1: select first contact
        int contact = RandomUtils.nextInt(2);
        // select all contact
        if (contact == 0) {
            driver.findElement(By.cssSelector("div.ant-table-selection>label>span>input")).click();
        }
        // select first contact
        else {
            driver.findElement(By.cssSelector("tr:nth-child(1) > td > div > div > div > label > span > input")).click();
        }

        // Next to 最終確認_step
        driver.findElement(By.cssSelector("div:nth-child(4) > div > button")).click();

        // Wait and click Time selection button
        sleep(1000);
        driver.findElement(By.cssSelector("div.ant-col.ant-col-24>div>div:nth-child(4)>div>button")).click();

        // Select date
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-col>div:nth-child(1)>div>div>div:nth-child(1)>div>div>input")))
                .click();

        sleep(200);

        findDate findDate = new findDate();

        driver.findElement(By.cssSelector(findDate.date_element(driver))).click();
        //-------------------------------------------------------------------------------------------------------

        String text;

        WebElement time = driver.findElement(By.cssSelector("div:nth-child(1) > div > div > div:nth-child(2) > div > div > input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly',0);", time);
        int hour = LocalTime.now().getHour();
        int min = LocalTime.now().getMinute();
        int new_min = min;
        int new_hour = hour;

        // Select time
        // If time incorrect, add 10 minutes and select again
        do {

            // Update time after add 10 minutes
            if (new_min > 59) {
                new_min = min - 60;
                new_hour++;
            }

            // Verify time 8:00 - 19:00
            if (new_hour < 8) {
                new_hour = 8;
                new_min = 0;
            } else if (new_hour > 19) {
                new_hour = 8;
                new_min = 0;

                // If hour > 19:00, select next day.
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-col>div:nth-child(1)>div>div>div:nth-child(1)>div>div>input")))
                        .click();

                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector(findDate.next_date_element(driver)))).click();
            }

            String min_str;
            String hour_str;
            if (new_min < 10) {
                min_str = "0" + Integer.toString(new_min);
            } else {
                min_str = Integer.toString(new_min);
            }
            if (new_hour < 10) {
                hour_str = "0" + Integer.toString(new_hour);
            } else {
                hour_str = Integer.toString(new_hour);
            }
            time.click();
            for (int i = 0; i < 10; i++) {
                sleep(100);
                key.sendKeys(Keys.BACK_SPACE).perform();
            }

            // Select time
            time.sendKeys(hour_str + ":" + min_str);

            // Click 決 定 button
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.ant-picker-ok>button"))).click();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-footer>button"))).click();

            sleep(1000);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(2)"))).click();

            text = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-message-custom-content > span:nth-child(2)"))).getText();

            System.out.println(text);

            new_min += 10;

        } while ((text.contains("前後5分以内に配信予定のメールが登録されています。配信時刻を変更してください。"))
                || (text.contains("配信可能時刻は、08:00:00 〜 19:00:00 です。配信時刻を変更してください。"))
                || text.contains("土日祝に配信予定のメールが登録されています。配信時刻を変更してください")
                || text.contains("入力内容に誤りがあります。各フィールドに表示されたエラー内容を確認し、再入力してください。"));
    }
}
