package BasePage.createMail;

import Common.Common;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Destination_selection {

    //** Delivery information **//
    public void delivery_information(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            //** Delivery information **//
            //** Delivery type **//
            // 1: Deliver the matter, 2: Deliver personnel, 3: Deliver information
            int delivery_type = RandomUtils.nextInt(3) + 1;
            driver.findElement(By.cssSelector("#searchtype > label:nth-child(" + delivery_type + ") > span > input")).click();

            // Search destination selection by delivery type
            // Delivery type: Deliver the matter
            if (delivery_type == 1) {
                // ** Delivery area **//
                // 1: Hokaido, 2: Tohoku, 3: Kanto, 4:Chubu , 5:Tokai , 6:Kansai , 7:Shikoku , 8:China , 9:Kyushu , 10: others
                // number of delivery area have been selected in range 1-10
                int number_of_delivery_area = RandomUtils.nextInt(10) + 1;

                //select delivery area from 1 to number_of_delivery_area
                for (int i = 1; i <= number_of_delivery_area; i++) {
                    driver.findElement(By.cssSelector("label:nth-child(" + i + ")>span>input[id^='wants_location']")).click();
                }

                //** Delivery occupation **//
                // 1: Development, 2:Infrastructure, 3: Others
                int delivery_occupation = RandomUtils.nextInt(3) + 1;
                driver.findElement(By.cssSelector("#jobtype > label:nth-child(" + delivery_occupation + ")>span:nth-child(1)")).click();

                // Delivery occupation: development
                if (delivery_occupation == 1) {
                    //Details of delivery occupation
                    // 1:Designer, 2:FE, 3:BE, 4:PM/Director, 5:Others
                    int detail_of_delivery_occupation = RandomUtils.nextInt(5) + 1;
                    driver.findElement(By.cssSelector("#jobtype_dev > label:nth-child(" + detail_of_delivery_occupation + ") > span > input")).click();

                    // Delivery skill details
                    // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Inexperienced
                    // number of delivery skill details have been selected in range 1-7
                    int number_of_delivery_skill_details = RandomUtils.nextInt(7) + 1;

                    // select delivery skill details from 1 to number_of_delivery_skill_details
                    for (int i = 1; i <= number_of_delivery_skill_details; i++) {
                        driver.findElement(By.cssSelector("label:nth-child(" + i + ")>span>input[id^='jobskill_dev']")).click();
                    }
                }
                // Delivery occupation: infrastructure
                else if (delivery_occupation == 2) {
                    //Details of delivery occupation
                    // 1:Server, 2:Network, 3:Security, 4:Database, 5:Information system, 6:Others
                    int detail_of_delivery_occupation = RandomUtils.nextInt(6) + 1;
                    driver.findElement(By.cssSelector("#jobtype_infra > label:nth-child(" + detail_of_delivery_occupation + ") > span > input")).click();

                    // Delivery skill details
                    // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Surveillance, 8:Inexperienced
                    // number of delivery skill details have been selected in range 1-8
                    int number_of_delivery_skill_details = RandomUtils.nextInt(8) + 1;

                    // select delivery skill details from 1 to number_of_delivery_skill_details
                    for (int i = 1; i <= number_of_delivery_skill_details; i++) {
                        driver.findElement(By.cssSelector("label:nth-child(" + i + ")>span>input[id^='jobskill_infra']")).click();
                    }
                }
                // Delivery occupation: others
                else {
                    //Details of delivery occupation
                    // 1:Sales/office work, 2:Base station, 3:Call center support desk, 4:Others
                    int detail_of_delivery_occupation = RandomUtils.nextInt(4) + 1;
                    driver.findElement(By.cssSelector("#jobtype_other > label:nth-child(" + detail_of_delivery_occupation + ") > span > input")).click();
                }

                //** Delivery commercial distribution **//
                // 1:End direct/main contract direct , 2:Primary contract, 3:Secondary contract, 4:Third contract/unknown
                int delivery_commercial_distribution = RandomUtils.nextInt(4) + 1;
                driver.findElement(By.cssSelector("#job_syouryu > label:nth-child(" + delivery_commercial_distribution + ") > span.ant-radio > input")).click();
            }

            // Delivery type: Deliver personnel
            else if (delivery_type == 2) {
                // ** Delivery area **//
                // 1: Hokaido, 2: Tohoku, 3: Kanto, 4:Chubu , 5:Tokai , 6:Kansai , 7:Shikoku , 8:China , 9:Kyushu , 10: others
                // number of delivery area have been selected in range 1-10
                int number_of_delivery_area = RandomUtils.nextInt(10) + 1;

                //select delivery area from 1 to number_of_delivery_area
                for (int i = 1; i <= number_of_delivery_area; i++) {
                    driver.findElement(By.cssSelector("label:nth-child(" + i + ")>span>input[id^='wants_location']")).click();
                }

                //** Delivery occupation **//
                // 1: Development, 2:Infrastructure, 3: Others
                int delivery_occupation = RandomUtils.nextInt(3) + 1;

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
                            driver.findElement(By.cssSelector("div.ant-row.ant-row-start>label:nth-child(" + j + ")>span>input[id^='personneltype_dev']")).click();
                        }

                        // Delivery skill details
                        // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Inexperienced
                        // number of delivery skill details have been selected in range 1-7
                        int number_of_delivery_skill_details = RandomUtils.nextInt(7) + 1;

                        // select delivery skill details from 1 to number_of_delivery_skill_details
                        for (int k = 1; k <= number_of_delivery_skill_details; k++) {
                            driver.findElement(By.cssSelector("label:nth-child(" + k + ")>span>input[id^='personnelskill_dev']")).click();
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
                            driver.findElement(By.cssSelector("div.ant-form-item-control-input-content>label:nth-child(" + j + ")>span>input[id^='personneltype_infra']")).click();
                        }

                        // Delivery skill details
                        // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Surveillance, 8:Inexperienced
                        // number of delivery skill details have been selected in range 1-8
                        int number_of_delivery_skill_details = RandomUtils.nextInt(8) + 1;

                        // select delivery skill details from 1 to number_of_delivery_skill_details
                        for (int k = 1; k <= number_of_delivery_skill_details; k++) {
                            driver.findElement(By.cssSelector("label:nth-child(" + k + ")>span>input[id^='personnelskill_infra']")).click();
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
                            driver.findElement(By.cssSelector("div.ant-form-item-control-input-content>label:nth-child(" + j + ")>span>input[id^='personneltype_other']")).click();
                        }
                    }

                    //** Delivery employment form **//
                    // 1:Proper , 2:Freelance
                    int delivery_employment_form = RandomUtils.nextInt(2) + 1;
                    driver.findElement(By.cssSelector("#job_koyou > label:nth-child(" + delivery_employment_form + ") > span > input")).click();

                    //** Delivery commercial distribution **//
                    // 1:Company affiliation , 2:1 company affiliation, 3:2 company affiliation, 4:Affiliation/unknown for 3 or more companies
                    int delivery_commercial_distribution = RandomUtils.nextInt(4) + 1;
                    driver.findElement(By.cssSelector("#personnel_syouryu > label:nth-child(" + delivery_commercial_distribution + ") > span > input")).click();
                }
            }
        }
    }

    //** Commitment **//
    public void account_status(WebDriver driver) {
        // Account status
        // 0: Do not search by Account status, 1: add Account status conditions
        int account_status = RandomUtils.nextInt(2);
        if (account_status == 1) {
            // add Account status conditions
            driver.findElement(By.cssSelector("div:nth-child(2) > div.ant-col.ant-col-19.ant-form-item-control > div > div > div > div > button")).click();
            // 1: Prospect, 2:Approached, 3:Information exchanged, 4:Contract record available
            int account_status_condition = RandomUtils.nextInt(4) + 1;
            // select Account status conditions
            for (int i = 1; i <= account_status_condition; i++) {
                driver.findElement(By.cssSelector("label:nth-child(" + i + ")>span>input[id^='contact__org']")).click();
            }
        }
    }

    public void in_house_person_in_charge(WebDriver driver, Actions key) throws InterruptedException {
        // In-house person in charge
        // 0: Do not search by In-house person in charge, 1: add In-house person in charge condition
        int in_house_person_in_charge = RandomUtils.nextInt(2);
        if (in_house_person_in_charge == 1) {
            // add In-house person in charge condition
            driver.findElement(By.cssSelector("div:nth-child(3) > div > div > div > div > div > div > button")).click();
            sleep(3000); // Wait dropdown has been loaded
            driver.findElement(By.cssSelector("#contact__staff")).click(); // Open In-house person in charge dropdown
            int id = RandomUtils.nextInt(20);
            for (int i = 0; i <= id; i++) {
                key.sendKeys(Keys.DOWN).perform();
            }
            // Wait and select In-house person in charge
            sleep(1000);
            key.sendKeys(Keys.ENTER).perform();
        }
    }

    public void compatibility(WebDriver driver, Actions key) {
        // Compatibility
        // 0: Do not search by Compatibility, 1: add Compatibility condition
        int compatibility = RandomUtils.nextInt(2);
        if (compatibility == 1) {
            // add Compatibility condition
            driver.findElement(By.cssSelector("div:nth-child(4) > div > div > div > div > div > button")).click();

            // Open Compatibility dropdown
            driver.findElement(By.cssSelector("#contact__category")).click();

            // 0: Like, 1: Dislike
            int compatibility_condition_1 = RandomUtils.nextInt(2);
            for (int i = 0; i <= compatibility_condition_1; i++) {
                key.sendKeys(Keys.DOWN).perform();
            }
            key.sendKeys(Keys.ENTER).perform();

            // 0: Matches with, 1: Do not match with
            int compatibility_condition_2 = RandomUtils.nextInt(2);
            driver.findElement(By.cssSelector("div:nth-child(2) > div > span.ant-select-selection-item")).click();
            for (int i = 0; i <= compatibility_condition_2; i++) {
                key.sendKeys(Keys.DOWN).perform();
            }
            key.sendKeys(Keys.ENTER).perform();
        }
    }

    public void tag(WebDriver driver, Actions key) throws InterruptedException {
        // Tag
        // 0: Do not search by Tag, 1: add Tag condition
        int tag = RandomUtils.nextInt(2);
        if (tag == 1) {
            // add Tag condition
            driver.findElement(By.cssSelector("div:nth-child(5) > div > div > div > div > div > div > button")).click();
            // wait Tag dropdown has been loaded
            sleep(3000);
            // number of tags has been selected in range 1-5
            int number_of_tags = RandomUtils.nextInt(5) + 1;
            // Open Tag dropdown
            driver.findElement(By.cssSelector("div.ant-col.ant-col-12 > div > div > div > div > div")).click();
            for (int i = 0; i < number_of_tags; i++) {
                key.sendKeys(Keys.DOWN).perform();
                key.sendKeys(Keys.ENTER).perform();
            }

            // 0: AND, 1: OR
            int tag_condition_2 = RandomUtils.nextInt(2);
            driver.findElement(By.cssSelector("div.ant-col.ant-col-2 > div > div > span.ant-select-selection-item")).click();
            for (int i = 0; i <= tag_condition_2; i++) {
                key.sendKeys(Keys.DOWN).perform();
            }
            key.sendKeys(Keys.ENTER).perform();
        }
    }

    public void search_contact_by_condition(WebDriver driver, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Search
            driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

            // Waiting for loading result
            sleep(1000);
        }
    }

    public void select_contact(WebDriver driver, String role, Common cm) {
        // check null = "True" => No partner PIC
        boolean check_null = false;
        try {
            driver.findElement(By.cssSelector("li.ant-pagination-item-active")).click();
        } catch (NoSuchElementException ex) {
            check_null = true;
        }

        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5)) && (!check_null)) {
            // Select contact
            // 0: select all contact, 1: select first contact
            int contact = RandomUtils.nextInt(2);
            // Scroll down
            ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
            // select all contact
            if (contact == 0) {
                driver.findElement(By.cssSelector("div.ant-table-selection>label>span>input")).click();
            }
            // select first contact
            else {
                driver.findElement(By.cssSelector("tr:nth-child(1) > td > div > div > div > label > span > input")).click();
            }
        } else {
            System.out.println("Can not find partner PIC with this condition.");
            System.out.println("Please add more data or change your search condition.");
        }
    }

    public void commitment(WebDriver driver, Actions key, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            account_status(driver);
            in_house_person_in_charge(driver, key);
            compatibility(driver, key);
            tag(driver, key);
        }
    }

    public void next_to_final_confirmation(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Next to 最終確認_step
            driver.findElement(By.cssSelector("div:nth-child(4) > div > button")).click();

            // Check current tab
            String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-message-custom-content>span:nth-child(2)"))).getText();
            Assert.assertEquals(text, "アイテムが更新されました", "[Failed] Can not next to Final confirmation from Destination selection.");
        }
    }

    public void deliver_the_matter_do_not_select_condition(WebDriver driver) {
        // Delivery type: Deliver the matter
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(1) > span > input")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        //Delivery area
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")))
                .getText();
        Assert.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

        // Delivery occupation
        String text2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(1)> div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation] Message do not match");

        // Delivery commercial distribution
        String text3 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(5)> div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text3, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");
    }

    public void deliver_the_matter_only_select_delivery_occupation_development(WebDriver driver) {
        // Delivery type: Deliver the matter
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(1) > span > input")).click();

        // Delivery occupation: Development
        driver.findElement(By.cssSelector("#jobtype > label:nth-child(1)>span:nth-child(1)")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        //Delivery area
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

        // Delivery occupation/Development/Delivery job details
        String text2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery job details] Message do not match");

        // Delivery occupation/Development/Delivery skill details
        String text3 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(2) > div:nth-child(2) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery skill details] Message do not match");

        // Delivery commercial distribution
        String text4 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(5)> div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text4, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");
    }

    public void deliver_the_matter_only_select_delivery_occupation_infrastructure(WebDriver driver) {
        // Delivery type: Deliver the matter
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(1) > span > input")).click();

        // Delivery occupation: Infrastructure
        driver.findElement(By.cssSelector("#jobtype > label:nth-child(2)>span:nth-child(1)")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        //Delivery area
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

        // Delivery occupation/Infrastructure/Delivery job details
        String text2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(3) > div:nth-child(1) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery job details] Message do not match");

        // Delivery occupation/Infrastructure/Delivery skill details
        String text3 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(3) > div:nth-child(2) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery skill details] Message do not match");

        // Delivery commercial distribution
        String text4 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(5)> div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text4, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");
    }

    public void deliver_the_matter_only_select_delivery_occupation_others(WebDriver driver) {
        // Delivery type: Deliver the matter
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(1) > span > input")).click();

        // Delivery occupation: Others
        driver.findElement(By.cssSelector("#jobtype > label:nth-child(3)>span:nth-child(1)")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        //Delivery area
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

        // Delivery occupation/Others/Delivery job details
        String text2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(4) > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Others/Delivery job details] Message do not match");

        // Delivery commercial distribution
        String text3 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(4) > div:nth-child(5)> div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text3, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");
    }

    /**
     *
     */
    public void deliver_the_personnel_do_not_select_condition(WebDriver driver) {
        // Delivery type: Deliver the personnel
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(2) > span > input")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        //Delivery area
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

        // Delivery occupation
        String text2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(1) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation] Message do not match");

        // Delivery employment form
        String text3 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(2) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text3, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

        // Delivery commercial distribution
        String text4 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(3) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text4, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");
    }

    public void deliver_the_personnel_only_select_delivery_occupation_development(WebDriver driver) {
        // Delivery type: Deliver the personnel
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(2) > span > input")).click();

        // Delivery occupation: Development
        driver.findElement(By.cssSelector("#personneltype_dev")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        //Delivery area
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

        // Delivery occupation/Development/Delivery job details
        String text2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div:nth-child(1) > div> div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery job details] Message do not match");

        // Delivery occupation/Development/Delivery skill details
        String text3 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div:nth-child(2) > div> div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery skill details] Message do not match");

        // Delivery employment form
        String text4 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(2) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text4, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

        // Delivery commercial distribution
        String text5 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(3) > div > div.ant-form-item-explain> div")))
                        .getText();
        Assert.assertEquals(text5, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");
    }

    public void deliver_the_personnel_only_select_delivery_occupation_infrastructure(WebDriver driver) {
        // Delivery type: Deliver the personnel
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(2) > span > input")).click();

        // Delivery occupation: Infrastructure
        driver.findElement(By.cssSelector("#personneltype_infra")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        //Delivery area
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

        // Delivery occupation/Infrastructure/Delivery job details
        String text2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div:nth-child(1) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery job details] Message do not match");

        // Delivery occupation/Infrastructure/Delivery skill details
        String text3 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div:nth-child(2) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery skill details] Message do not match");

        // Delivery employment form
        String text4 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(2) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text4, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

        // Delivery commercial distribution
        String text5 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(3) > div > div.ant-form-item-explain> div")))
                        .getText();
        Assert.assertEquals(text5, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");
    }

    public void deliver_the_personnel_only_select_delivery_occupation_others(WebDriver driver) {
        // Delivery type: Deliver the personnel
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(2) > span > input")).click();

        // Delivery occupation: Others
        driver.findElement(By.cssSelector("#personneltype_other")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        //Delivery area
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

        // Delivery occupation/Others/Delivery job details
        String text2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Others/Delivery job details] Message do not match");

        // Delivery employment form
        String text3 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(2) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text3, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

        // Delivery commercial distribution
        String text4 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(3) > div > div.ant-form-item-explain> div")))
                        .getText();
        Assert.assertEquals(text4, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");
    }

    public void deliver_the_personnel_select_delivery_occupation_development_infrastructure_and_others(WebDriver driver) {
        // Delivery type: Deliver the personnel
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(2) > span > input")).click();

        // Delivery occupation: Development
        driver.findElement(By.cssSelector("#personneltype_dev")).click();

        // Delivery occupation: Infrastructure
        driver.findElement(By.cssSelector("#personneltype_infra")).click();

        // Delivery occupation: Others
        driver.findElement(By.cssSelector("#personneltype_other")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        //Delivery area
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(3) > div > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text1, "必ず1つ選択してください", "[Delivery area] Message do not match");

        // Delivery occupation/Development/Delivery job details
        String text2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div:nth-child(1) > div> div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text2, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery job details] Message do not match");

        // Delivery occupation/Development/Delivery skill details
        String text3 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div:nth-child(2) > div> div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text3, "必ず1つ選択してください", "[Delivery occupation/Development/Delivery skill details] Message do not match");

        // Delivery occupation/Infrastructure/Delivery job details
        String text4 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div:nth-child(1) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text4, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery job details] Message do not match");

        // Delivery occupation/Infrastructure/Delivery skill details
        String text5 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div:nth-child(2) > div > div.ant-form-item-explain > div")))
                        .getText();
        Assert.assertEquals(text5, "必ず1つ選択してください", "[Delivery occupation/Infrastructure/Delivery skill details] Message do not match");

        // Delivery occupation/Others/Delivery job details
        String text6 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div > div > div > div > div > div > div > div.ant-form-item-explain > div")))
                .getText();
        Assert.assertEquals(text6, "必ず1つ選択してください", "[Delivery occupation/Others/Delivery job details] Message do not match");

        // Delivery employment form
        String text7 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(2) > div > div.ant-form-item-explain > div")))
                .getText();
        Assert.assertEquals(text7, "必ず1つ選択してください", "[Delivery employment form] Message do not match");

        // Delivery commercial distribution
        String text8 =
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(5) > div:nth-child(3) > div > div.ant-form-item-explain> div")))
                        .getText();
        Assert.assertEquals(text8, "必ず1つ選択してください", "[Delivery commercial distribution] Message do not match");

    }

    public void do_not_select_account_status(WebDriver driver, String role, Common cm) {
        // Select delivery information
        delivery_information(driver, role, cm);

        // Account status: ON
        driver.findElement(By.cssSelector("div:nth-child(2) > div.ant-col.ant-col-19.ant-form-item-control > div > div > div > div > button")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        // Account status
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(6) > div:nth-child(2) > div > div.ant-form-item-explain > div > div")))
                        .getText();
        Assert.assertEquals(text, "必ず1つ選択してください", "[Account status] Message do not match");
    }

    public void do_not_select_in_house_person_in_charge(WebDriver driver, String role, Common cm) {
        // Select delivery information
        delivery_information(driver, role, cm);

        // In-house person in charge: ON
        driver.findElement(By.cssSelector("div:nth-child(3) > div > div > div > div > div > div > button")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        // In-house person in charge
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(6) > div:nth-child(3) > div > div.ant-form-item-explain > div > div")))
                .getText();
        Assert.assertEquals(text, "必ず選択してください", "[In-house person in charge] Message do not match");
    }

    public void do_not_select_compatibility(WebDriver driver, String role, Common cm) {
        // Select delivery information
        delivery_information(driver, role, cm);

        // Compatibility: ON
        driver.findElement(By.cssSelector("div:nth-child(4) > div > div > div > div > div > button")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        // Compatibility
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(6) > div:nth-child(4) > div > div.ant-form-item-explain > div > div")))
                .getText();
        Assert.assertEquals(text, "必ず選択してください", "[Compatibility] Message do not match");
    }

    public void do_not_select_tag(WebDriver driver, String role, Common cm) {
        // Select delivery information
        delivery_information(driver, role, cm);

        // Tag: ON
        driver.findElement(By.cssSelector("div:nth-child(5) > div > div > div > div > div > div > button")).click();

        // Click Search button
        driver.findElement(By.cssSelector("div:nth-child(6) > div > div > div:nth-child(1) > div > button")).click();

        // Tag
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(6) > div:nth-child(5) > div > div.ant-form-item-explain > div > div")))
                .getText();
        Assert.assertEquals(text, "必ず1つ選択してください", "[Tag] Message do not match");
    }

    public void select_exceeds_5_tags(WebDriver driver, String role, Common cm, Actions key) throws InterruptedException {
        // Select delivery information
        delivery_information(driver, role, cm);

        // Tag: ON
        driver.findElement(By.cssSelector("div:nth-child(5) > div > div > div > div > div > div > button")).click();

        // Waiting for loading tag list
        sleep(3000);

        // Select 6 tags
        driver.findElement(By.cssSelector("div.ant-col.ant-col-12 > div > div > div > div > div")).click();
        for (int i = 0; i < 6; i++) {
            key.sendKeys(Keys.DOWN).perform();
            key.sendKeys(Keys.ENTER).perform();
        }

        // Tag
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form > div:nth-child(6) > div.ant-form-item-has-error > div > div.ant-form-item-explain > div")))
                .getText();
        Assert.assertEquals(text, "これ以上選択できません", "[Tag] Message do not match");
    }

    public void do_you_want_to_delete_this_delivered_email_OK(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click delete button
        driver.findElement(By.cssSelector("button.ant-btn-danger")).click();

        // Click OK button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(2)")))
                .click();

        // Waiting for loading mail list page
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not delete delivered email");
    }

    public void do_you_want_to_delete_this_delivered_email_Cancel(WebDriver driver) throws InterruptedException {
        // Click delete button
        driver.findElement(By.cssSelector("button.ant-btn-danger")).click();

        // Click delete button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(1)")))
                .click();
        sleep(1000);

        // Check popup close
        boolean check = true;
        try {
            driver.findElement(By.cssSelector("div:nth-child(3) > div > div:nth-child(1) > button")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        Assert.assertFalse(check, "[Failed] Can not close delete delivered email popup");
    }

    public void make_a_copy(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click make a copy button
        driver.findElement(By.cssSelector("div:nth-child(1)>button.ant-btn-sm")).click();

        // Waiting for loading mail list page
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not make a copy of delivered email.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_OK(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click save as draft button
        driver.findElement(By.cssSelector("div.ant-col:nth-child(2)>button.ant-btn-sm")).click();

        // Click OK button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(2)")))
                .click();

        // Waiting for loading mail list page
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not save delivered as draft.");
    }

    public void would_you_like_to_change_this_delivery_email_to_Draft_status_Cancel(WebDriver driver) throws InterruptedException {
        // Click save as draft button
        driver.findElement(By.cssSelector("div.ant-col:nth-child(2)>button.ant-btn-sm")).click();

        // Click cancel button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(1)")))
                .click();

        // Waiting for close popup
        sleep(500);

        // Check popup close
        boolean check = true;
        try {
            driver.findElement(By.cssSelector("div:nth-child(3) > div > div:nth-child(1) > button")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        Assert.assertFalse(check, "[Failed] Can not close save delivered as draft popup.");
    }

    public void back_to_attachment_step(WebDriver driver, Actions key, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Back to Attachment step
            driver.findElement(By.cssSelector("div:nth-child(1)>div.ant-col.ant-col-24 > div > div:nth-child(1) > div > button")).click();

            // Waiting for loading attachment tab
            sleep(500);

            // Check current tab
            boolean check = driver.findElement(By.cssSelector("div:nth-child(2)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check, "[Failed] Can not back to Attachment from Destination selection.");

            // Back to Destination selection step
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(4)>div>button")))
                    .click();

            // Back to Attachment step
            WebElement back_to_attachment = driver.findElement(By.cssSelector("div:nth-child(2)>div>div.ant-steps-item-icon"));
            key.moveToElement(back_to_attachment).click().build().perform();

            // Check current tab
            boolean check1 = driver.findElement(By.cssSelector("div:nth-child(2)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check1, "[Failed] Can not back to Attachment from Destination selection.");
        }
    }

    public void back_to_basic_information_step(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Back to Basic information step
            driver.findElement(By.cssSelector("div:nth-child(1)>div>div.ant-steps-item-icon")).click();

            // Check current tab
            boolean check = driver.findElement(By.cssSelector("div:nth-child(1)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check, "[Failed] Can not back to Basic information from Destination selection.");
        }
    }

    public void leave_search_template_name_blank(WebDriver driver, Actions key) {
        // Click Save template button
        driver.findElement(By.cssSelector("div>button[type]:nth-child(4)")).click();

        // Leave search template name blank and verify error message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#newTemplateName")))
                .sendKeys("leave_blank");
        for (int i = 0; i < 11; i++) {
            key.sendKeys(Keys.BACK_SPACE).perform();
        }
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-form-item-explain-error")))
                .getText();
        Assert.assertEquals(text, "テンプレート名を入力してください", "[Template name] Message do not match");
    }

    public void search_template_name_exceed_50_half_width_characters(WebDriver driver) throws InterruptedException {
        // Click Save template button
        driver.findElement(By.cssSelector("div>button[type]:nth-child(4)")).click();

        // Input search template name exceed 50 half width characters and verify error message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#newTemplateName")))
                .sendKeys(RandomStringUtils.randomAlphabetic(51));

        sleep(2000);
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-form-item-explain-error")))
                .getText();
        Assert.assertEquals(text, "テンプレート名は50文字以内で入力してください。", "[Template name] Message do not match");
    }

    public void search_template_name_exceed_50_full_width_characters(WebDriver driver) throws InterruptedException {
        // Click Save template button
        driver.findElement(By.cssSelector("div>button[type]:nth-child(4)")).click();

        // Input search template name exceed 50 full width characters and verify error message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#newTemplateName")))
                .sendKeys(RandomStringUtils.random(51, 0x4e00, 0x4f80, true, false));

        sleep(2000);
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-form-item-explain-error")))
                .getText();
        Assert.assertEquals(text, "テンプレート名は50文字以内で入力してください。", "[Template name] Message do not match");
    }

    public void search_template_name_exceed_mix_50_half_and_full_width_characters(WebDriver driver) {
        // Click Save template button
        driver.findElement(By.cssSelector("div>button[type]:nth-child(4)")).click();

        int length_of_half = RandomUtils.nextInt(50) + 1;
        String template_name = RandomStringUtils.randomAlphabetic(length_of_half) + RandomStringUtils.random(51 - length_of_half, 0x4e00, 0x4f80, true, false);
        // Input search template name exceed mix 50 half and full width characters and verify error message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#newTemplateName")))
                .sendKeys(template_name);

        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-form-item-explain-error")))
                .getText();
        Assert.assertEquals(text, "テンプレート名は50文字以内で入力してください。", "[Template name] Message do not match");
    }


    public void create_search_template_name_OK(WebDriver driver) throws InterruptedException {
        // Click Save template button
        driver.findElement(By.cssSelector("div>button[type]:nth-child(4)")).click();

        // Input search template name with 50 half width characters and verify message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#newTemplateName")))
                .sendKeys(RandomStringUtils.randomAlphabetic(50));

        // Click OK button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > div > div:nth-child(2) > button")))
                .click();

        // Waiting for show message
        sleep(1000);
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-message-custom-content>span:nth-child(2)")))
                .getText();
        Assert.assertEquals(text, "テンプレートを作成しました。", "[Template name] Message do not match");
    }

    public void create_search_template_name_Cancel(WebDriver driver) throws InterruptedException {
        // Click Save template button
        driver.findElement(By.cssSelector("div>button[type]:nth-child(4)")).click();

        // Click Cancel button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > div > div:nth-child(1) > button")))
                .click();

        // Waiting for close popup
        sleep(500);

        // Check popup close
        boolean check = true;
        try {
            driver.findElement(By.cssSelector("div:nth-child(3) > div > div:nth-child(1) > button")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        Assert.assertFalse(check, "[Failed] Can not close create search template popup.");
    }

    public void input_available_search_template_name(WebDriver driver) throws InterruptedException {
        // Click Save template button
        driver.findElement(By.cssSelector("div>button[type]:nth-child(4)")).click();

        String template_name = RandomStringUtils.randomAlphabetic(50);

        // Input search template name with 50 half width characters and verify message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#newTemplateName")))
                .sendKeys(template_name);

        // Click OK button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > div > div:nth-child(2) > button")))
                .click();

        // Waiting for show message
        sleep(1000);
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-message-custom-content>span:nth-child(2)")))
                .getText();
        Assert.assertEquals(text, "テンプレートを作成しました。", "[Template name] Message do not match");

        // Click Save template button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div>button[type]:nth-child(4)")))
                .click();

        // Input available template name
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#newTemplateName")))
                .sendKeys(template_name);

        // Click OK button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > div > div:nth-child(2) > button")))
                .click();

        // Verify error message
        String text1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-message-error>span:nth-child(2)")))
                .getText();
        System.out.println(text1);
        Assert.assertTrue(text1.contains("同一名称のテンプレートが既に存在します"), "[Failed] Can create search template with available template name.");
    }

    public void set_cancel_search_template_as_default(WebDriver driver) throws InterruptedException {
        // Create search template
        create_search_template_name_OK(driver);

        // Set template as default
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div > button[type]:nth-child(3)")))
                .click();

        String title1 = driver.findElement(By.cssSelector("div:nth-child(2) > div > div > div > div > span.ant-select-selection-item")).getAttribute("title");
        Assert.assertTrue(title1.contains("☆ :"), "[Failed] Can not set search template as default.");

        // Cancel template as default
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div > button[type]:nth-child(3)")))
                .click();

        // waiting for cancel set default
        sleep(1000);
        String title2 = driver.findElement(By.cssSelector("div:nth-child(2) > div > div > div > div > span.ant-select-selection-item")).getAttribute("title");
        Assert.assertFalse(title2.contains("☆ :"), "[Failed] Can not cancel search template as default.");
    }

    public void delete_search_template_OK(WebDriver driver) throws InterruptedException {
        // Create search template
        create_search_template_name_OK(driver);

        // Click delete icon
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div > button[type]:nth-child(2)")))
                .click();

        //Click OK button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(2)")))
                .click();

        // Verify message
        String text = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-message-custom-content>span:nth-child(2)")))
                .getText();
        Assert.assertEquals(text, "テンプレートを削除しました", "[Failed] Can not delete search template.");
    }

    public void delete_search_template_Cancel(WebDriver driver) throws InterruptedException {
        // Create search template
        create_search_template_name_OK(driver);

        // Click delete icon
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div > button[type]:nth-child(2)")))
                .click();

        //Click cancel button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(1)")))
                .click();

        // Waiting for close popup
        sleep(500);

        // Check popup close
        boolean check = true;
        try {
            driver.findElement(By.cssSelector("div:nth-child(3) > div > div:nth-child(1) > button")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        Assert.assertFalse(check, "[Failed] Can not close delete search template popup.");
    }

    public void reset_search_criteria(WebDriver driver) {
        /*
        ** Delivery information ** //
        ** Delivery type ** //
         1: Deliver the matter, 2: Deliver personnel, 3: Deliver information
        */
        int delivery_type = RandomUtils.nextInt(3) + 1;
        driver.findElement(By.cssSelector("#searchtype > label:nth-child(" + delivery_type + ") > span > input")).click();

        // Click reset search criteria button
        driver.findElement(By.cssSelector("div:nth-child(2) > div.ant-row-start > button")).click();

        // Verify search condition have been reset
        boolean check = driver.findElement(By.cssSelector("#searchtype > label:nth-child(" + delivery_type + ") > span > input"))
                .isSelected();
        Assert.assertFalse(check, "[Failed] Can not reset search condition.");
    }

    public void link_to_partner_PIC_edit_from_destination_selection(WebDriver driver, String partnerPIC_url) throws InterruptedException {
        // Scroll down
        ((JavascriptExecutor) driver).executeScript("scroll(0, 550);");
        sleep(1000);

        // check null = "True" => No partner PIC => Can not link to partner PIC.
        boolean check_null = false;
        try {
            driver.findElement(By.cssSelector(" tr.ant-table-row:nth-child(1) > td:nth-child(2)")).click();
        } catch (NoSuchElementException ex) {
            check_null = true;
        }

        if (!check_null) {
            sleep(1000);
            // If we can link to partner PIC, verify current url
            Assert.assertTrue(driver.getCurrentUrl().contains(partnerPIC_url), "[Failed] Can not link to partner PIC from Destination selection");
        } else {
            System.out.println("No result.");
        }
    }

    public void pagination_destination_selection(WebDriver driver) throws InterruptedException {
        // check null = "True" => No partner PIC
        boolean check_null = false;
        try {
            driver.findElement(By.cssSelector("li.ant-pagination-item-active")).click();
        } catch (NoSuchElementException ex) {
            check_null = true;
        }

        if (!check_null) {
            sleep(1000);
            // Scroll down
            ((JavascriptExecutor) driver).executeScript("scroll(0, 1000);");
            sleep(1000);

            String next_page = driver.findElement(By.cssSelector("li.ant-pagination-next")).getAttribute("class");
            if (!next_page.contains("disabled")) {
                String current_page;
                // if we can go to next page, verify current page
                driver.findElement(By.cssSelector("li.ant-pagination-next")).click();
                current_page = driver.findElement(By.cssSelector("li.ant-pagination-item-active")).getAttribute("title");
                Assert.assertEquals(current_page, "2", "[Failed] Can not go to next page.");

                // back to previous page
                driver.findElement(By.cssSelector("li.ant-pagination-prev")).click();
                current_page = driver.findElement(By.cssSelector("li.ant-pagination-item-active")).getAttribute("title");
                Assert.assertEquals(current_page, "1", "[Failed] Can not back to previous page.");
            }
        }
    }
}
