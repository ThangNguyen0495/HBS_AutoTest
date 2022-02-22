package hidenBasePage.editMail;

import Common.Common;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class Attachment {
    public void generate_test_file(long capacity) throws IOException {
        // Generate test file
        // capacity: MB
        new RandomAccessFile("text.txt", "rw").setLength(capacity * 1024 * 1024);
        new RandomAccessFile("excel.xlsx", "rw").setLength(capacity * 1024 * 1024);
        new RandomAccessFile("word.docx", "rw").setLength(capacity * 1024 * 1024);
        new RandomAccessFile("pdf.pdf", "rw").setLength(capacity * 1024 * 1024);
    }

    public void upload_file(WebDriver driver, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // 0: upload 1 file, 1: upload multi file
            int upload = RandomUtils.nextInt(2);
            driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\text.txt");
            if (upload != 0) {
                driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
                driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\word.docx");
                driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\pdf.pdf");
            }
            // Wait file have been uploaded
            sleep(5000);
        }
    }

    public void next_to_destination_selection_step(WebDriver driver, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            // Next to 宛先選択_Step
            driver.findElement(By.cssSelector("div:nth-child(4)>div>button")).click();

            // Check current tab
            boolean check = driver.findElement(By.cssSelector("div:nth-child(3)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check,"[Failed] Can not next to Destination selection from Attachment.");
            
            // Waiting for destination selection tab loading
            sleep(1000);
        }
    }

    public void back_to_basic_information_step(WebDriver driver, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {
            
            // Back to Basic information step
            driver.findElement(By.cssSelector("div:nth-child(1)>div.ant-col.ant-col-24 > div > div:nth-child(1) > div > button")).click();

            // Check current tab
            boolean check = driver.findElement(By.cssSelector("div:nth-child(1)>div>div.ant-steps-item-icon")).isEnabled();
            Assert.assertTrue(check,"[Failed] Can not back to Basic information from Attachment.");
            
            // Waiting for basic information tab loading
            sleep(1000);
        }
    }
    
    public void upload_maximum_capacity_1_file(WebDriver driver, long capacity) throws IOException {
        generate_test_file(capacity);
        // Waiting for hid previous message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)")));
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)"))).getText();
        Assert.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload file with capacity: " + capacity + "MB");
    }

    public void upload_maximum_capacity_multi_file(WebDriver driver, long capacity) throws IOException {
        generate_test_file(capacity);
        // Waiting for hid previous message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)")));
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\text.txt");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\word.docx");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\pdf.pdf");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)"))).getText();
        Assert.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload multi file with capacity = " + capacity + "MB");
    }

    public void upload_exceed_maximum_capacity_1_file(WebDriver driver, long capacity) throws IOException {
        generate_test_file(capacity + 1);
        // Waiting for hid previous message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)")));
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)"))).getText();
        Assert.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match.");
    }

    public void upload_exceed_maximum_capacity_multi_file(WebDriver driver, long capacity) throws IOException {
        generate_test_file(capacity + 1);
        // Waiting for hid previous message
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)")));
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\text.txt");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\word.docx");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\pdf.pdf");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)"))).getText();
        Assert.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match.");
    }

    public void do_you_want_to_delete_this_delivered_email_OK(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click delete button
        driver.findElement(By.cssSelector("button.ant-btn-danger")).click();

        // Click OK button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(2)")))
                        .click();
        
        //Waiting for mail list page loading
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), url_mail_list, "[Failed] Can not delete delivered email");
    }

    public void do_you_want_to_delete_this_delivered_email_Cancel(WebDriver driver) throws InterruptedException {
        // Click delete button
        driver.findElement(By.cssSelector("button.ant-btn-danger")).click();

        // Click Cancel button
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-modal-confirm-btns > button:nth-child(1)")))
                        .click();
        
        // Waiting for close popup
        sleep(500);

        // Check popup close
        boolean check = true;
        try {
            driver.findElement(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(1)")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }

        Assert.assertFalse(check, "[Failed] Can not close delete delivered email popup.");
    }

    public void make_a_copy(WebDriver driver, String url_mail_list) throws InterruptedException {
        // Click make a copy button
        driver.findElement(By.cssSelector("div:nth-child(1)>button.ant-btn-sm")).click();
        
        // Waiting for mail list page loading
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
        
        // Waiting for mail list page loading
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
            driver.findElement(By.cssSelector("div.ant-modal-confirm-btns>button:nth-child(1)")).click();
        } catch (NoSuchElementException ex) {
            check = false;
        }
        Assert.assertFalse(check, "[Failed] Can not close save delivered as draft popup.");
    }
}
