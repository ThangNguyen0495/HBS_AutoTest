package page.Tenant;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static java.lang.Thread.sleep;
import static utilities.Link_and_Path.HBS.auth_token_path;
import static utilities.Link_and_Path.HBS.create_tenant_path;

public class Step2_Company_profile_information extends Tenant_page {
    String register_token;
    
    @FindBy(css = "#name")
    WebElement company_name;
    
    @FindBy(css = "#establishment_date")
    WebElement establishment_date;
    
    @FindBy(css = "span.anticon-close-circle")
    WebElement clear_establishment_date;
    
    @FindBy(css = "#address")
    WebElement address;
    
    @FindBy(css = "#building")
    WebElement building;
    
    @FindBy(css = "#domain_name")
    WebElement url;
    
    @FindBy(css = "#has_distribution>label>span>input")
    List<WebElement> commercial_distribution;
    
    @FindBy(css = "#capital_man_yen")
    WebElement capital;
    
    @FindBy(css = "#has_p_mark_or_isms>label>span>input")
    List<WebElement> P_mark_or_ISMS;
    
    @FindBy(css = "#has_invoice_system>label>span>input")
    List<WebElement> invoice_registration_company;
    
    @FindBy(css = "#has_haken>label>span>input")
    List<WebElement> worker_dispatch_business;
    
    @FindBy(css = "button[type = 'submit']")
    WebElement next_to_button;
    
    @FindBy(css = "form > div:nth-child(1) > div > div> div.ant-form-item-explain-error")
    WebElement company_name_error;
    
    @FindBy(css = "form > div:nth-child(2) > div > div> div.ant-form-item-explain-error")
    WebElement establishment_date_error;
    
    @FindBy(css = "span > div:nth-child(1) > div > div.ant-form-item-explain > div")
    WebElement address_error1;
    
    @FindBy(css = "span > div:nth-child(2) > div > div.ant-form-item-explain > div")
    WebElement address_error2;
    
    @FindBy(css = "form > div:nth-child(4) > div > div.ant-form-item-explain > div")
    WebElement url_error1;
    
    @FindBy(css = "form > div:nth-child(4) > div > div.ant-form-item-explain > div:nth-child(1)")
    WebElement url_error2;
    
    @FindBy(css = "form > div:nth-child(4) > div > div.ant-form-item-explain > div:nth-child(2)")
    WebElement url_error3;
    
    @FindBy(css = "form > div:nth-child(6) > div > div.ant-form-item-explain > div")
    WebElement capital_error;
    
    

    public Step2_Company_profile_information(WebDriver driver, String domain, String register_token_cpi) {
        super(driver, domain);
        this.register_token = register_token_cpi;
        PageFactory.initElements(driver, this);
    }

    public void link_to_company_profile_information() {
        driver.get(domain + create_tenant_path + auth_token_path + register_token);
    }

    public void company_name() {
        // Length of company name in range 1-100
        int length_of_company_name = RandomUtils.nextInt(100) + 1;
        // generate company name by random text
        String company_name_str = RandomStringUtils.randomAlphabetic(length_of_company_name);
        // Company name
        company_name.sendKeys(company_name_str);
    }

    public void establishment_date() {
        clear_establishment_date.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly',0);", establishment_date);
        // month in range 1-12
        int month = RandomUtils.nextInt(12) + 1;
        // generate year from 1800-2022
        int year = 2000 + RandomUtils.nextInt(22);
        // generate establish date by YYYY-MM format
        String establishment_date_str;
        if (month < 10) {
            establishment_date_str = year + "-0" + month;
        } else {
            establishment_date_str = year + "-" + month;
        }
        // Establishment date
        establishment_date.sendKeys(establishment_date_str);
        key.sendKeys(Keys.ENTER).perform();
    }

    public void address() {
        // total length of address and building in range 2-100
        int length_of_address = RandomUtils.nextInt(99) + 1;
        int length_of_building = RandomUtils.nextInt(100 - length_of_address) + 1;
        // generate address and building by random text
        String address_str = RandomStringUtils.randomAlphabetic(length_of_address);
        String building_str = RandomStringUtils.randomAlphabetic(length_of_building);
        // Address
        address.sendKeys(address_str);
        building.sendKeys(building_str);
    }

    public void url() {
        // length of url in range 1-50
        int length_of_url = RandomUtils.nextInt(38) + 1;
        // generate url by random text
        String url_str = "https://" + RandomStringUtils.randomAlphabetic(length_of_url) + ".com";
        // Url
        url.sendKeys(url_str);
    }

    public void commercial_distribution() {
        // Commercial distribution
        // 1: Can't come out, 2: Exit
        int commercial_distribution_id = RandomUtils.nextInt(2);
        commercial_distribution.get(commercial_distribution_id).click();
    }

    public void capital() {
        // Capital
        int length_of_capital = RandomUtils.nextInt(9) + 1;
        capital.sendKeys(RandomStringUtils.random(length_of_capital, false, true));
    }

    // Qualifications
    public void P_mark_or_ISMS() {
        // P mark / ISMS
        // 1: None, 2: Can be
        int P_mark_or_ISMS_id = RandomUtils.nextInt(2);
        P_mark_or_ISMS.get(P_mark_or_ISMS_id).click();
    }

    public void invoice_registration_company() {
        // Invoice registration company
        // 1: None, 2: Can be
        int invoice_registration_company_id = RandomUtils.nextInt(2);
        invoice_registration_company.get(invoice_registration_company_id).click();
    }

    public void worker_dispatch_business() {
        // Worker dispatch business
        // 1: None, 2: Can be
        int worker_dispatch_business_id = RandomUtils.nextInt(2);
        worker_dispatch_business.get(worker_dispatch_business_id).click();
    }

    public void next_to_personal_profile_information() throws InterruptedException {
        // Click Register your company profile button
        // Go to 個人プロフィール情報_Step
        next_to_button.click();
        sleep(3000);
    }

    /**
     * Leave company name blank
     *
     */
    public void leave_company_name_blank() throws InterruptedException {
        company_name.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
        sleep(2000);
        String text = company_name_error.getText();
        Assert.assertEquals(text, "自社名を入力してください", "[Company name] Message do not match");
    }

    public void company_name_exceed_100_half_width_characters() throws InterruptedException {
        company_name.sendKeys(RandomStringUtils.randomAlphanumeric(101));
        sleep(2000);
        String text = company_name_error.getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Company name] Message do not match");
    }

    public void company_name_exceed_100_full_width_characters() throws InterruptedException {
        company_name.sendKeys(RandomStringUtils.random(101, 0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = company_name_error.getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Company name] Message do not match");
    }

    public void company_name_exceed_mix_100_half_and_full_width_characters() throws InterruptedException {
        int length_of_half_width = RandomUtils.nextInt(100) + 1;
        String company_name_str = RandomStringUtils.randomAlphabetic(length_of_half_width)
                + RandomStringUtils.random(101 - length_of_half_width, 0x4e00, 0x4f80, true, false);
        company_name.sendKeys(company_name_str);
        sleep(2000);
        String text = company_name_error.getText();
        Assert.assertEquals(text, "100文字以内で入力してください。", "[Company name] Message do not match");
    }


    // Establishment date
    public void leave_date_of_establishment_blank() throws InterruptedException {
        clear_establishment_date.click();
        sleep(2000);
        String text = establishment_date_error.getText();
        Assert.assertEquals(text, "設立年月を入力してください", "[Establishment date] Message do not match");
    }

    // Address
    public void leave_address_blank() throws InterruptedException {
        address.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
        sleep(2000);
        String text1 = address_error1.getText();
        Assert.assertEquals(text1, "市区町村・町名・番地を入力してください", "[Address] Message do not match");
    }

    public void address_exceed_100_half_width_characters() throws InterruptedException {
        // total length of address and building in range 2-101
        int length_of_address = RandomUtils.nextInt(100) + 1;
        int length_of_building = 101 - length_of_address;
        // Address
        address.sendKeys(RandomStringUtils.randomAlphanumeric(length_of_address));
        building.sendKeys(RandomStringUtils.randomAlphanumeric(length_of_building));
        sleep(2000);
        String text = address_error2.getText();
        Assert.assertEquals(text, "建物名を合わせて100文字以内で入力してください。", "[Address] Message do not match");
    }

    public void address_exceed_100_full_width_characters() throws InterruptedException {
        // total length of address and building in range 2-101
        int length_of_address = RandomUtils.nextInt(100) + 1;
        int length_of_building = 101 - length_of_address;
        // Address
        address.sendKeys(RandomStringUtils.random(length_of_address, 0x4e00, 0x4f80, true, false));
        building.sendKeys(RandomStringUtils.random(length_of_building, 0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text = address_error2.getText();
        Assert.assertEquals(text, "建物名を合わせて100文字以内で入力してください。", "[Address] Message do not match");
    }

    public void address_exceed_mix_100_half_and_and_full_width_characters() throws InterruptedException {
        // total length of address and building in range 2-101
        int length_of_address_half = RandomUtils.nextInt(98) + 1;
        int length_of_address_full = RandomUtils.nextInt(99 - length_of_address_half) + 1;
        int length_of_building_half = RandomUtils.nextInt(100 - length_of_address_half - length_of_address_full) + 1;
        int length_of_building_full = 101 - length_of_building_half - length_of_address_half - length_of_address_full;
        String address_str = RandomStringUtils.randomAlphabetic(length_of_address_half) + RandomStringUtils.random(length_of_address_full, 0x4e00, 0x4f80, true, false);
        String building_str = RandomStringUtils.randomAlphabetic(length_of_address_half) + RandomStringUtils.random(length_of_building_full, 0x4e00, 0x4f80, true, false);
        // Address
        address.sendKeys(address_str);
        building.sendKeys(building_str);
        sleep(3000);
        String text = address_error2.getText();
        Assert.assertEquals(text, "建物名を合わせて100文字以内で入力してください。", "[Address] Message do not match");
    }

    // URL
    public void leave_url_blank() throws InterruptedException {
        url.sendKeys("text", Keys.CONTROL + "a", Keys.DELETE);
        sleep(2000);
        String text = url_error1.getText();
        Assert.assertEquals(text, "URLを入力してください", "[URL] Message do not match");
    }

    public void url_exceed_50_half_width_characters() throws InterruptedException {
        url.sendKeys(RandomStringUtils.randomAlphanumeric(51));
        sleep(2000);
        String text = url_error1.getText();
        Assert.assertEquals(text, "50文字以内で入力してください。", "[URL] Message do not match");
    }

    public void url_exceed_50_full_width_characters() throws InterruptedException {
        url.sendKeys(RandomStringUtils.random(51, 0x4e00, 0x4f80, true, false));
        sleep(2000);
        String text1 = url_error2.getText();
        Assert.assertEquals(text1, "半角英数記号で入力してください。", "[URL] Message do not match");
        String text2 = url_error3.getText();
        Assert.assertEquals(text2, "50文字以内で入力してください。", "[URL] Message do not match");
    }

    public void url_exceed_mix_50_half_and_full_width_characters() throws InterruptedException {
        int length_of_half = RandomUtils.nextInt(50) + 1;
        String url_str = RandomStringUtils.randomAlphabetic(length_of_half) + RandomStringUtils.random(51 - length_of_half, 0x4e00, 0x4f80, true, false);
        url.sendKeys(url_str);
        sleep(2000);
        String text1 = url_error2.getText();
        Assert.assertEquals(text1, "半角英数記号で入力してください。", "[URL] Message do not match");
        String text2 = url_error3.getText();
        Assert.assertEquals(text2, "50文字以内で入力してください。", "[URL] Message do not match");
    }

    // Capital
    public void leave_capital_blank() throws InterruptedException {
        capital.sendKeys("123", Keys.CONTROL + "a", Keys.DELETE);
        sleep(2000);
        String text = capital_error.getText();
        Assert.assertEquals(text, "資本金を入力してください", "[Capital] Message do not match");
    }

    public void capital_exceed_13_half_width_characters() throws InterruptedException {
        capital.sendKeys(RandomStringUtils.random(14, false, true));
        sleep(2000);
        String text = capital_error.getText();
        Assert.assertEquals(text, "9桁以内で入力してください。", "[Capital] Message do not match");
    }
}