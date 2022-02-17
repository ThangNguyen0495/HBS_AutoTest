package BasePage.createMail;

import Common.Common;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class Attachment {
    public void generate_test_file(long capacity) throws IOException, InterruptedException {
        // Generate test file
        // capacity: MB
        new RandomAccessFile("text.txt", "rw").setLength(capacity * 1024 * 1024);
        new RandomAccessFile("excel.xlsx", "rw").setLength(capacity * 1024 * 1024);
        new RandomAccessFile("word.docx", "rw").setLength(capacity * 1024 * 1024);
        new RandomAccessFile("pdf.pdf", "rw").setLength(capacity * 1024 * 1024);
        // Wait file has been created
        sleep(1000);
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

            sleep(2000);
            // Next to 宛先選択_Step
            driver.findElement(By.cssSelector("div:nth-child(4)>div>button")).click();
        }
    }

    public void back_to_basic_information_step(WebDriver driver, String role, Common cm) throws InterruptedException {
        // Master, Administrator, Responsible person, Leader, Member
        if (cm.authorized(role, cm.role_list(5))) {

            sleep(2000);
            // Back to Basic information step
            driver.findElement(By.cssSelector("div:nth-child(1)>div.ant-col.ant-col-24 > div > div:nth-child(1) > div > button")).click();
        }
    }



    public void upload_maximum_capacity_1_file(WebDriver driver, long capacity) throws IOException, InterruptedException {
        generate_test_file(capacity);
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)"))).getText();
        Assert.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload file with capacity: " + capacity + "MB");
    }

    public void upload_maximum_capacity_multi_file(WebDriver driver, long capacity) throws IOException, InterruptedException {
        generate_test_file(capacity);
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\text.txt");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\word.docx");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\pdf.pdf");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)"))).getText();
        Assert.assertTrue(text.contains("ファイルがアップロードされました"), "[Failed] Can not upload multi file with capacity = " + capacity + "MB");
    }

    public void upload_exceed_maximum_capacity_1_file(WebDriver driver, long capacity) throws IOException, InterruptedException {
        generate_test_file(capacity + 1);
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)"))).getText();
        Assert.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match.");
    }

    public void upload_exceed_maximum_capacity_multi_file(WebDriver driver, long capacity) throws IOException, InterruptedException {
        generate_test_file(capacity + 1);
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\text.txt");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\word.docx");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\pdf.pdf");
        driver.findElement(By.cssSelector("span.ant-upload.ant-upload-btn>input")).sendKeys(System.getProperty("user.dir") + "\\excel.xlsx");
        String text = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1)>div>div.ant-message-custom-content > span:nth-child(2)"))).getText();
        Assert.assertTrue(text.contains("を超えるメールを配信することはできません"), "[Failed] Message do not match.");
    }
}
