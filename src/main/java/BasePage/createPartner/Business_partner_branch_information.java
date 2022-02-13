package BasePage.createPartner;

import Common.Common;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Business_partner_branch_information {

    public void register_a_branch(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Click 支店を登録する button
            driver.findElement(By.cssSelector("div.ant-result-extra > button")).click();
        }
    }

    public void branch_name(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Branch name
            // length of branch name in range 1-100
            int length_of_branch_name = RandomUtils.nextInt(100) + 1;
            String branch_name = RandomStringUtils.randomAlphabetic(length_of_branch_name);
            driver.findElement(By.cssSelector("#branches_0_name")).sendKeys(branch_name);
        }
    }

    public void branch_address(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Address
            // total length of branch address and branch building in range 2-100
            int length_of_branch_address = RandomUtils.nextInt(99) + 1;
            int length_of_branch_building = RandomUtils.nextInt(100 - length_of_branch_address) + 1;
            String branch_address = RandomStringUtils.randomAlphabetic(length_of_branch_address);
            String branch_building = RandomStringUtils.randomAlphabetic(length_of_branch_building);
            driver.findElement(By.cssSelector("#branches_0_address")).sendKeys(branch_address);
            driver.findElement(By.cssSelector("#branches_0_building")).sendKeys(branch_building);
        }
    }

    public void branch_tel(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // TEL
            // total length of branch tel1, branch tel2, branch tel3 in range 3-15
            int length_of_branch_tel1 = RandomUtils.nextInt(13) + 1;
            int length_of_branch_tel2 = RandomUtils.nextInt(14 - length_of_branch_tel1) + 1;
            int length_of_branch_tel3 = RandomUtils.nextInt(15 - length_of_branch_tel1 - length_of_branch_tel2) + 1;
            long branch_tel1 = (long) (Math.random() * (Math.pow(10, length_of_branch_tel1)));
            long branch_tel2 = (long) (Math.random() * (Math.pow(10, length_of_branch_tel2)));
            long branch_tel3 = (long) (Math.random() * (Math.pow(10, length_of_branch_tel3)));
            driver.findElement(By.cssSelector("#branches_0_tel1")).sendKeys(Long.toString(branch_tel1));
            driver.findElement(By.cssSelector("#branches_0_tel2")).sendKeys(Long.toString(branch_tel2));
            driver.findElement(By.cssSelector("#branches_0_tel3")).sendKeys(Long.toString(branch_tel3));
        }
    }

    public void branch_fax(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // FAX
            // total length of branch fax1, branch fax2, branch fax3 in range 3-15
            int length_of_branch_fax1 = RandomUtils.nextInt(13) + 1;
            int length_of_branch_fax2 = RandomUtils.nextInt(14 - length_of_branch_fax1) + 1;
            int length_of_branch_fax3 = RandomUtils.nextInt(15 - length_of_branch_fax1 - length_of_branch_fax2) + 1;
            long branch_fax1 = (long) (Math.random() * (Math.pow(10, length_of_branch_fax1)));
            long branch_fax2 = (long) (Math.random() * (Math.pow(10, length_of_branch_fax2)));
            long branch_fax3 = (long) (Math.random() * (Math.pow(10, length_of_branch_fax3)));
            driver.findElement(By.cssSelector("#branches_0_fax1")).sendKeys(Long.toString(branch_fax1));
            driver.findElement(By.cssSelector("#branches_0_fax2")).sendKeys(Long.toString(branch_fax2));
            driver.findElement(By.cssSelector("#branches_0_fax3")).sendKeys(Long.toString(branch_fax3));
        }
    }

    public void switch_to_transaction_terms(WebDriver driver, String role, Common cm) {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Switch to Transaction terms tab
            driver.findElement(By.cssSelector("#rc-tabs-0-tab-3")).click();
        }
    }
}
