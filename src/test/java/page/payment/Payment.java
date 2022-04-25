package page.payment;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static utilities.links.HBS.*;
import static utilities.links.PAYJP.*;
import static java.lang.Thread.sleep;

public class Payment {
    public static String company_id_text;
    @FindBy(css = "tr:nth-child(1)>td.ant-table-cell:nth-child(7)")
    WebElement newest_receipt_id;
    @FindBy(css = "tr:nth-child(1)>td.ant-table-cell:nth-child(6)>p")
    WebElement newest_price;
    @FindBy(css = "#name")
    WebElement company_name;
    @FindBy(css = "#id_username")
    WebElement username_admin;
    @FindBy(css = "#id_password")
    WebElement password_admin;
    @FindBy(css = "input[type='submit']")
    WebElement login_button;
    @FindBy(css = "tr.row1>th>a")
    WebElement company_id;
    @FindBy(css = "#email")
    WebElement PayJP_email_WebElement;
    @FindBy(css = "#login_form-password")
    WebElement PayJP_password_WebElement;
    @FindBy(css = "tr:nth-child(1) > td:nth-child(2)")
    WebElement PayJP_receipt_id;
    @FindBy(css = " tr:nth-child(1) > td:nth-child(1)")
    WebElement PayJP_price;
    WebDriver driver;
    Actions key;
    String domain;
    WebDriverWait wait;

    String username_admin_page;
    String password_admin_page;


    SoftAssert soft;

    public Payment(WebDriver driver, String domain, String username_admin_page, String password_admin_page) {
        this.driver = driver;
        this.domain = domain;
        this.username_admin_page = username_admin_page;
        this.password_admin_page = password_admin_page;
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        soft = new SoftAssert();
        key = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     *
     * @return 0: receipt id, 1: price
     */
    public List<String> get_payment_history_receipt() {
        driver.get(domain + HISTORY_PAYMENT_PATH);
        String receipt_id;
        try {
            receipt_id = wait.until(ExpectedConditions.visibilityOf(newest_receipt_id)).getText();
        } catch (TimeoutException ex) {
            receipt_id = "nothing";
        }
        // get price in history payment
        String price;
        if (receipt_id.equals("nothing")) {
            price = "0";
        } else {
            price = newest_price.getText().replace(",", "").replace(" 円", "");
        }
        return List.of(receipt_id, price);
    }

    public long add_tax(String price) {
        return (long) (1.1 * Long.parseLong(price.replace(",", "")));
    }

    public String getCompanyName() throws InterruptedException {
        driver.get(domain + COMPANY_PROFILE_PATH);
        sleep(1000);
        return company_name.getAttribute("value").replace(" ", "+");
    }

    public String getCompanyID() throws InterruptedException {
        // Step1: login_to_Komorebi and get company name
        // Step2: login_to_Komorebi admin page and get company id
        // Link to search company page
        driver.get(domain + GET_COMPANY_ID_PATH + getCompanyName());

        // Login to admin page
        username_admin.sendKeys(username_admin_page);
        password_admin.sendKeys(password_admin_page);
        login_button.click();

        // Get company_id
        String get_company_id_text = wait.until(ExpectedConditions.visibilityOf(company_id)).getAttribute("href");
        company_id_text = get_company_id_text.split("/", 8)[6];
        return company_id_text;
    }

    public void loginToPayJP() {
        driver.get(PAYJP_URL + PAYJP_LOGIN_PATH);
        PayJP_email_WebElement.sendKeys(PAYJP_LOGIN_EMAIL);
        PayJP_password_WebElement.sendKeys(PAYJP_LOGIN_PASSWORD);
        login_button.click();
    }

    public List<String> getPayJpReceipt() {
        String receipt_id;
        String price;
        driver.get(PAYJP_URL + PAYJP_SEARCH_RECEIPT_PATH + company_id_text);
        try {
            receipt_id = wait.until(ExpectedConditions.visibilityOf(PayJP_receipt_id)).getText();
        } catch (TimeoutException ex) {
            receipt_id = "nothing";
        }

        if (!receipt_id.equals("nothing")) {
            price = PayJP_price.getText().replace("¥", "").replace(",", "");
        } else {
            price = "0";
        }
        return List.of(receipt_id, price);
    }
}
