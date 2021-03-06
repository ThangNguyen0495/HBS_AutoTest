package page.mail;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.common.Common;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
import static utilities.links.HBS.MAIL_LIST_PATH;

public class DeliveredMailPage {
    public static String mail_id_for_another_test;
    @FindBy(css = "button.ant-btn-danger")
    WebElement delete_button;
    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(2)")
    WebElement ok_button;
    @FindBy(css = "div.ant-modal-confirm-btns > button:nth-child(1)")
    WebElement cancel_button;
    @FindBy(css = "span>div>div>div:nth-child(1)>button")
    WebElement make_a_copy_button;
    @FindBy(css = "div.ant-col:nth-child(2)>button.ant-btn-sm")
    WebElement save_as_draft_button;
    @FindBy(css = "div.ant-message-custom-content>span:nth-child(2)")
    WebElement message;
    @FindBy(css = "tr[data-row-key]:nth-child(2)")
    WebElement first_mail;
    @FindBy(css = "div.ant-row> div:nth-child(2) > button.ant-btn-primary")
    WebElement update_button;
    WebDriver driver;
    WebDriverWait wait;
    String role;
    Common common;
    Actions key;
    SoftAssert soft;
    String domain;
    String Mode;
    String mail_status;
    String mail_id;
    List<String> list_mail_status = List.of("During delivery", "Delivered", "Error");

    // Create Mode
    public DeliveredMailPage(WebDriver driver, String role, Common common, String domain, String Mode) {
        this.driver = driver;
        this.role = role;
        this.common = common;
        this.domain = domain;
        this.Mode = Mode;
        soft = new SoftAssert();
        key = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Edit Mode
    public DeliveredMailPage(WebDriver driver, String role, Common common, String domain, String mail_id, String mail_status, String Mode) {
        this.driver = driver;
        this.role = role;
        this.common = common;
        this.domain = domain;
        this.Mode = Mode;
        this.mail_status = mail_status;
        this.mail_id = mail_id;
        soft = new SoftAssert();
        key = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getMailID() {
        return first_mail.getAttribute("data-row-key");
    }

    public void wait_for_loading_element(WebElement element) {
        boolean check = true;
        do {
            try {
                element.click();
            } catch (ElementClickInterceptedException ex) {
                check = false;
            }
        }
        while (check);
    }

    public void verifyThatDeliveredEmailShouldBeDeletedAfterClickingOKButton() {
        if (common.authorized(role, common.roleList(5))) {
            // Click delete button
            delete_button.click();

            // Click OK button
            wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

            // Waiting for loading mail list page
            String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
            wait.until(ExpectedConditions.invisibilityOf(message));

            // check message
            soft.assertEquals(text, "????????????????????????????????????", "[Failed][Delete Delivered page.Mail] Message do not match.");

            // check current inputValidUrl
            soft.assertEquals(driver.getCurrentUrl(), domain + MAIL_LIST_PATH, "[Failed] Can not delete delivered email");

            // show all assert result
            soft.assertAll();
        }
    }

    public void verifyThatDeleteThisDeliveredEmailPopupShouldBeClosedAfterClickCancelButton() throws InterruptedException {
        if (common.authorized(role, common.roleList(5))) {
            // Click delete button
            delete_button.click();

            // Click cancel button
            wait.until(ExpectedConditions.elementToBeClickable(cancel_button)).click();

            // Wait popup close
            sleep(500);

            // Check popup close
            boolean check = true;
            try {
                cancel_button.click();
            } catch (NoSuchElementException ex) {
                check = false;
            }
            soft.assertFalse(check, "[Failed] Can not close delete delivered email popup");
            soft.assertAll();
        }
    }

    public void verifyThatCanMakeDeliveredEmailCopy() throws InterruptedException {
        if (common.authorized(role, common.roleList(5))) {
            if (Mode.equals("Edit")) {
                sleep(5000);
            }

            // scroll to top
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

            // Click make a copy button
            wait.until(ExpectedConditions.elementToBeClickable(make_a_copy_button));
            key.moveToElement(make_a_copy_button).click().build().perform();

            // Waiting for loading mail list page
            String text;
            try {
                text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
            } catch (TimeoutException ex) {
                key.moveToElement(make_a_copy_button).click().build().perform();
                wait.until(ExpectedConditions.visibilityOf(message));
                text = message.getText();
            }

            sleep(2000);

            // check message
            soft.assertEquals(text, "???????????????????????????????????????", "[Failed][Make a copy] Message do not match.");

            // check current inputValidUrl
            soft.assertEquals(driver.getCurrentUrl(), domain + MAIL_LIST_PATH, "[Failed][Make a copy] Can not make a copy of delivered email.");

            // Because when you delete or save as draft, your mail will be changed.
            // So, I make two copy for this test.
            // get mail id for delete and save as draft testcase
            if (Mode.equals("Edit")) {
                // get mail id for delete testcase
                mail_id_for_another_test = getMailID();
            }
            // show all assert result
            soft.assertAll();
        }
    }

    // Update button
    public void verifyThatCanUpdateDeliveredEmailWithValidData() {
        if (common.authorized(role, common.roleList(5))) {
            if ((!list_mail_status.contains(mail_status)) && (Mode.equals("Edit"))) {
                update_button.click();
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                wait.until(ExpectedConditions.invisibilityOf(message));

                // check message
                soft.assertEquals(text, "????????????????????????????????????", "[Update mail][Basic information] Message do not match.");

                // check current inputValidUrl
                soft.assertEquals(driver.getCurrentUrl(), domain + MAIL_LIST_PATH, "[Update mail][Failed] Can not update delivered email.");
            } else {
                boolean check = false;
                try {
                    update_button.click();
                } catch (NoSuchElementException ex) {
                    check = true;
                }
                soft.assertTrue(check, "[Update mail][Failed] Update button is not disabled.");
            }
            soft.assertAll();
        }
    }

    public void switchUrlBeforeRunDeleteOrSaveAsDraftTest(String test_item) throws InterruptedException {
        if (common.authorized(role, common.roleList(5))) {
            if ((test_item.equals("delete")) || (test_item.equals("save as draft") && !list_mail_status.contains(mail_status))) {
                driver.get(domain + MAIL_LIST_PATH + "/" + mail_id_for_another_test);
            } else {
                driver.get(domain + MAIL_LIST_PATH + "/" + mail_id);
            }

            // wait for distributor list loading
            sleep(3000);
        }
    }

    public void verifyThatCanChangeDeliveredEmailStatusToDraft() {
        if (common.authorized(role, common.roleList(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                // scroll to top page
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

                // Click save as draft button
                key.moveToElement(save_as_draft_button).click().build().perform();

                // Click OK button
                wait.until(ExpectedConditions.elementToBeClickable(ok_button)).click();

                // Waiting for loading mail list page
                String text = wait.until(ExpectedConditions.visibilityOf(message)).getText();
                wait.until(ExpectedConditions.invisibilityOf(message));

                // check message
                soft.assertEquals(text, "????????????????????????????????????", "[Failed][Save as Draft] Message do not match.");
                soft.assertEquals(driver.getCurrentUrl(), domain + MAIL_LIST_PATH, "[Failed][Save as Draft] Can not change status mail to draft.");
            } else {
                boolean check = false;
                try {
                    save_as_draft_button.click();
                } catch (NoSuchElementException ex) {
                    check = true;
                }
                soft.assertTrue(check, "[Save as Draft][Failed] Save as Draft button is not disabled.");
            }
            soft.assertAll();
        }
    }

    public void verifyThatChangeDeliveredEmailStatusPopupShouldBeClosedAfterClickingCancelButton() throws InterruptedException {
        if (common.authorized(role, common.roleList(5))) {
            if ((Mode.equals("Create")) || (!list_mail_status.contains(mail_status) && (Mode.equals("Edit")))) {
                // scroll to top page
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

                sleep(2000);
                // Click save as draft button
                key.moveToElement(save_as_draft_button).click().build().perform();

                // Click cancel button
                wait.until(ExpectedConditions.elementToBeClickable(cancel_button)).click();

                // Wait popup close
                sleep(500);

                // Check popup close
                boolean check = true;
                try {
                    cancel_button.click();
                } catch (NoSuchElementException ex) {
                    check = false;
                }
                soft.assertFalse(check, "[Failed] Can not close save delivered as draft popup.");
            } else {
                boolean check = false;
                try {
                    save_as_draft_button.click();
                } catch (NoSuchElementException ex) {
                    check = true;
                }
                soft.assertTrue(check, "[Save as Draft][Failed] Save as Draft button is not disabled.");
            }
            soft.assertAll();
        }
    }
}
