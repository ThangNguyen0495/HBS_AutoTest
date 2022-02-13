package BasePage.createPartnerPIC;

import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Delivery_conditions {

    public static int proposal_delivery_check;
    public static int personnel_delivery_check;

    public void proposal_delivery(WebDriver driver) {
        // Proposal delivery
        // 0: OFF, 1: ON
        int proposal_delivery = RandomUtils.nextInt(2);
        proposal_delivery_check = proposal_delivery;
        if (proposal_delivery == 1) {
            // Proposal delivery: ON
            driver.findElement(By.cssSelector("#jobvisible")).click();

            // Desired occupation: Development
            // Development: Job description details
            // 1:Designer, 2:FE, 3:BE, 4:PM / Director, 5:Others
            int development_job_description_details = RandomUtils.nextInt(5) + 1;
            // select job description details from 1 to development_job_description_details
            for (int i = 1; i <= development_job_description_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + ")>span>input[id^='jobtype_dev']")).click();
            }
            // Development: Skill details
            // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Inexperienced
            int development_job_skill_details = RandomUtils.nextInt(7) + 1;
            // select skill details from 1 to development_job_skill_details
            for (int i = 1; i <= development_job_skill_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + ")>span>input[id^='jobskill_dev']")).click();
            }

            // Desired occupation: Infrastructure
            // Infrastructure: Job description details
            // 1:Server, 2:Network, 3:Security, 4:Database, 5:Information system, 6:Others
            int infrastructure_job_description_details = RandomUtils.nextInt(6) + 1;
            //select job description details from 1 to infrastructure_delivery_occupation
            for (int i = 1; i <= infrastructure_job_description_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + ")>span>input[id^='jobtype_infra']")).click();
            }
            // Infrastructure: Skill details
            // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Surveillance, 8:Inexperienced
            int infrastructure_skill_details = RandomUtils.nextInt(8) + 1;
            // select skill details from 1 to infrastructure_skill_details
            for (int i = 1; i <= infrastructure_skill_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + " )>span>input[id^='jobskill_infra']")).click();
            }

            // Desired occupation: Others
            // Others: Job description details
            // 1:Sales/office work, 2:Base station, 3:Call center support desk, 4:Others
            int others_job_description_details = RandomUtils.nextInt(4) + 1;
            // select job description details from 1 to others_job_description_details
            for (int i = 1; i <= others_job_description_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + ")>span>input[id^='jobtype_other']")).click();
            }

            // Commercial distribution restrictions
            // 1:None , 2:To the end direct / main contract direct, 3:Until the first contract, 4:Until the second contract
            int commercial_distribution = RandomUtils.nextInt(4) + 1;
            driver.findElement(By.cssSelector("#job_syouryu>label:nth-child(" + Integer.toString(commercial_distribution) + ")>span:nth-child(1)>input")).click();
        }
    }

    public void personnel_delivery(WebDriver driver) {
        // Personnel delivery
        // 0: OFF, 1: ON
        int personnel_delivery = RandomUtils.nextInt(2);
        personnel_delivery_check = personnel_delivery;
        if (personnel_delivery == 1) {
            // Personnel delivery: ON
            driver.findElement(By.cssSelector("div:nth-child(4)>div>div>div>button[role='switch']")).click();

            // Desired occupation: Development
            // Development: Job description details
            // 1:Designer, 2:FE, 3:BE, 4:PM / Director, 5:Others
            int development_job_description_details = RandomUtils.nextInt(5) + 1;
            // select job description details from 1 to development_job_description_details
            for (int i = 1; i <= development_job_description_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + ")>span>input[id^='personneltype_dev']")).click();
            }
            // Development: Skill details
            // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Inexperienced
            int development_job_skill_details = RandomUtils.nextInt(7) + 1;
            // select skill details from 1 to development_job_skill_details
            for (int i = 1; i <= development_job_skill_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + ")>span>input[id^='personnelskill_dev']")).click();
            }

            // Desired occupation: Infrastructure
            // Infrastructure: Job description details
            // 1:Server, 2:Network, 3:Security, 4:Database, 5:Information system, 6:Others
            int infrastructure_job_description_details = RandomUtils.nextInt(6) + 1;
            //select job description details from 1 to infrastructure_delivery_occupation
            for (int i = 1; i <= infrastructure_job_description_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + ")>span>input[id^='personneltype_infra']")).click();
            }
            // Infrastructure: Skill details
            // 1:Requirement definition, 2:Basic design, 3:Detailed design, 4:Manufacturing, 5:Testing/verification, 6:Maintenance and operation, 7:Surveillance, 8:Inexperienced
            int infrastructure_skill_details = RandomUtils.nextInt(8) + 1;
            // select skill details from 1 to infrastructure_skill_details
            for (int i = 1; i <= infrastructure_skill_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + " )>span>input[id^='personnelskill_infra']")).click();
            }

            // Desired occupation: Others
            // Others: Job description details
            // 1:Sales/office work, 2:Base station, 3:Call center support desk, 4:Others
            int others_job_description_details = RandomUtils.nextInt(4) + 1;
            // select job description details from 1 to others_job_description_details
            for (int i = 1; i <= others_job_description_details; i++) {
                driver.findElement(By.cssSelector("label.ContactForm-horizontalCheckboxStyle-1I13p:nth-child(" + Integer.toString(i) + ")>span>input[id^='personneltype_other']")).click();
            }

            // Delivery employment form
            // 1:Proper , 2:Freelance
            int delivery_employment_form = RandomUtils.nextInt(2) + 1;

            // select delivery employment form
            for (int i = 1; i<= delivery_employment_form; i++) {
                driver.findElement(By.cssSelector("label:nth-child(" + Integer.toString(i) + ")>span>input[id^='job_koyou']")).click();
            }

            // Commercial distribution restrictions
            // 1:None , 2:To the end direct / main contract direct, 3:Until the first contract, 4:Until the second contract
            int commercial_distribution = RandomUtils.nextInt(4) + 1;
            driver.findElement(By.cssSelector("#personnel_syouryu>label:nth-child(" + Integer.toString(commercial_distribution) + ")>span:nth-child(1)>input")).click();
        }
    }

    public void desired_area(WebDriver driver) {
        // Verify Proposal delivery or Personnel delivery is enable
        if ((personnel_delivery_check == 1) || (proposal_delivery_check == 1)) {
            // Scroll up
            ((JavascriptExecutor) driver).executeScript("scroll(0, -250);");
            // Desired area
            // 1: Hokaido, 2: Tohoku, 3: Kanto, 4:Chubu , 5:Tokai , 6:Kansai , 7:Shikoku , 8:China , 9:Kyushu , 10: others
            // number of delivery area have been selected in range 1-10
            int number_of_delivery_area = RandomUtils.nextInt(10) + 1;

            //select delivery area from 1 to number_of_delivery_area
            for (int i = 1; i <= number_of_delivery_area; i++) {
                driver.findElement(By.cssSelector("label:nth-child(" + Integer.toString(i) + ")>span>input[id^='wants_location']")).click();
            }
        }
    }

    public void complete_create_partner_pic(WebDriver driver) {
        // Click Register button
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }
}
