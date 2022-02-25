package BasePage.paymentProcess;

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

import static java.lang.Thread.sleep;

public class payment {
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
    String role;
    Actions key;
    String domain;
    WebDriverWait wait;
    String history_payment_path = "/purchaseHistory";
    String company_profile_path = "/myCompany";
    String search_company_id_path = "/admin/app_staffing/company/?q=";
    String username_admin_page;
    String password_admin_page;
    String PayJP_email = "okuaki@h-basis.co.jp";
    String PayJP_password = "iwnctndoTK40";
    String PayJP_url = "https://pay.jp";
    String PayJP_search_path = "/d/charges?keyword=";
    String PayJP_login_path = "/login";

    SoftAssert soft;

    public payment(WebDriver driver, String domain, String username_admin_page, String password_admin_page) {
        this.driver = driver;
        this.domain = domain;
        this.username_admin_page = username_admin_page;
        this.password_admin_page = password_admin_page;
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        soft = new SoftAssert();
        PageFactory.initElements(driver, this);
    }

    public List<String> get_payment_history_receipt() {
        driver.get(domain + history_payment_path);
        String receipt_id;
        try {
            receipt_id = wait.until(ExpectedConditions.visibilityOf(newest_receipt_id)).getText();
        } catch (TimeoutException ex) {
            receipt_id = "nothing";
        }
        // get price in history payment
        String price;
        if (receipt_id.equals("nothing")) {
            price = "nothing";
        } else {
            price = newest_price.getText().replace(",", "").replace(" 円", "");
        }
        return List.of(receipt_id, price);
    }

    public long add_tax(String price) {
        return (long) (1.1 * Long.parseLong(price.replace(",", "")));
    }

    public String get_company_name() throws InterruptedException {
        driver.get(domain + company_profile_path);
        sleep(1000);
        return company_name.getAttribute("value").replace(" ", "+");
    }

    public String get_company_id() throws InterruptedException {
        // Step1: login and get company name
        // Step2: login admin page and get company id
        // Link to search company page
        driver.get(domain + search_company_id_path + get_company_name());

        // Login to admin page
        username_admin.sendKeys(username_admin_page);
        password_admin.sendKeys(password_admin_page);
        login_button.click();

        // Get company_id
        String get_company_id_text = wait.until(ExpectedConditions.visibilityOf(company_id)).getAttribute("href");
        company_id_text = get_company_id_text.split("/", 8)[6];
        return company_id_text;
    }

    public void login_to_PayJP_page() {
        driver.get(PayJP_url + PayJP_login_path);
        PayJP_email_WebElement.sendKeys(PayJP_email);
        PayJP_password_WebElement.sendKeys(PayJP_password);
        login_button.click();
    }

    public List<String> get_PayJP_receipt() {
        String receipt_id;
        String price;
        driver.get(PayJP_url + PayJP_search_path + company_id_text);
        try {
            receipt_id = wait.until(ExpectedConditions.visibilityOf(PayJP_receipt_id)).getText();
        } catch (TimeoutException ex) {
            receipt_id = "nothing";
        }

        if (!receipt_id.equals("nothing")) {
            price = PayJP_price.getText().replace("¥", "").replace(",", "");
        } else {
            price = "nothing";
        }
        return List.of(receipt_id, price);
    }
}
