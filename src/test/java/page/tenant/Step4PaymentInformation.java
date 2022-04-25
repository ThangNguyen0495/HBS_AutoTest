package page.tenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static java.lang.Thread.sleep;
import static page.tenant.Step1MemberInformation.url_in_mail;
import static utilities.links.PAYJP.PAYJP_CARD_NUMBER_LIST_PATH;
import static utilities.links.PAYJP.PAYJP_URL;

public class Step4PaymentInformation extends TenantPage {
    public static String card_number_register;
    public static String card_number_update;
    @FindBy(css = "table:nth-child(4)>tbody>tr>td:nth-child(1)")
    List<WebElement> list_card_number;
    @FindBy(css = "span.anticon.anticon-plus")
    WebElement open_payment_information_register;
    @FindBy(css = "div.ant-row.ant-row-end>button")
    WebElement open_payment_information_update;
    @FindBy(css = "#number-form > iframe")
    WebElement card_number_iframe;
    @FindBy(css = "#cardNumber")
    WebElement card_number;
    @FindBy(css = "#expiry-form > iframe")
    WebElement date_of_expiry_frame;
    @FindBy(css = "#cardExpiry")
    WebElement card_expiry;
    @FindBy(css = "#cvc-form > iframe")
    WebElement cvc_iframe;
    @FindBy(css = "#cardCvc")
    WebElement card_cvc;
    @FindBy(css = "div.ant-col.ant-col-18.ant-form-item-control>div>div>div>div>input")
    WebElement card_name;
    @FindBy(css = "div:nth-child(3)>div>button")
    WebElement complete_register_update_payment_information;
    @FindBy(css = "div.ant-row.ant-row-center>button")
    WebElement complete_register_tenant;

    public Step4PaymentInformation(WebDriver driver, String domain) {
        super(driver, domain);
        PageFactory.initElements(driver, this);
    }

    public void getCardNumberFromTestcardCage() {
        // Get card number from testcard page
        // Use any testcard from testcard page
        int id_register = RandomUtils.nextInt(12);
        int id_update = RandomUtils.nextInt(12);
        while (id_update == id_register) {
            id_update = RandomUtils.nextInt(12);
        }
        driver.get(PAYJP_URL + PAYJP_CARD_NUMBER_LIST_PATH);
        // Get card number by id
        card_number_register = list_card_number.get(id_register).getText();
        card_number_update = list_card_number.get(id_update).getText();
    }

    public void backToPaymentInformation() throws InterruptedException {
        driver.get(url_in_mail);
        sleep(3000);
    }

    public void registerPaymentInformation() {
        // Open お支払い情報 popup
        wait.until(ExpectedConditions.elementToBeClickable(open_payment_information_register)).click();
    }

    public void updatePaymentInformation() {
        // Open お支払い情報 popup
        wait.until(ExpectedConditions.elementToBeClickable(open_payment_information_update)).click();
    }

    public void cardNumberRegister() {
        // Card Number
        driver.switchTo().frame(card_number_iframe);
        wait.until(ExpectedConditions.visibilityOf(card_number)).sendKeys(card_number_register);
    }

    public void cardNumberUpdate() {
        // Card Number
        driver.switchTo().frame(card_number_iframe);
        wait.until(ExpectedConditions.visibilityOf(card_number)).sendKeys(card_number_update);
    }

    public void dateOfExpiry() {
        // month in range 01-12
        int month = RandomUtils.nextInt(12) + 1;
        // year in range 23-99
        int year = (int) (Math.random() * 76 + 23);
        // generate date of expiry with MM/YY format
        String date_of_expiry;
        if (month < 10) {
            date_of_expiry = "0" + month + year;
        } else {
            date_of_expiry = Integer.toString(month) + year;
        }
        //Date of Expiry
        driver.switchTo().defaultContent();
        driver.switchTo().frame(date_of_expiry_frame);
        wait.until(ExpectedConditions.visibilityOf(card_expiry)).sendKeys(date_of_expiry);
    }

    public void cvc() {
        // CVC have 3 - 4 numbers
        // CVC in range 0000-9990
        int cvc = RandomUtils.nextInt(10000);
        String CVC;
        if (cvc < 10) {
            CVC = "000" + cvc;
        } else if (cvc < 99) {
            CVC = "00" + cvc;
        } else if (cvc < 999) {
            CVC = "0" + cvc;
        } else {
            CVC = "0" + cvc;
        }
        //CVC
        driver.switchTo().defaultContent();
        driver.switchTo().frame(cvc_iframe);
        wait.until(ExpectedConditions.visibilityOf(card_cvc)).sendKeys(CVC);
    }

    public void cardName() {
        // length of card name in range 1-100
        int length_of_card_name = RandomUtils.nextInt(100) + 1;
        // generate card name by random text
        String card_name_str = RandomStringUtils.randomAlphabetic(length_of_card_name);
        //Card Name
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(card_name)).sendKeys(card_name_str);
    }

    public void completeAddPaymentInformation() throws InterruptedException {
        //Register or update card
        wait.until(ExpectedConditions.elementToBeClickable(complete_register_update_payment_information));
        complete_register_update_payment_information.click();
        // Wait page loading
        sleep(3000);
    }

    public void completeTenantRegistration() {
        // Complete tenant registration
        wait.until(ExpectedConditions.elementToBeClickable(complete_register_tenant));
        complete_register_tenant.click();
    }
}
