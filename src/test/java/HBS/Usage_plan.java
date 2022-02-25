package HBS;

import BasePage.paymentProcess.usage_plan;
import Common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Usage_plan {
    Common cm;
    Actions key;
    WebDriver driver;
    usage_plan up;

    @BeforeMethod
    @Parameters({"headless", "domain", "email", "password","username_admin_page","password_admin_page"})
    public void init(boolean headless, String domain, String email, String password, String username_admin_page, String password_admin_page) throws InterruptedException {
        // Init Common function
        cm = new Common();

        // Config Webdriver
        driver = cm.setupWebdriver(headless);

        // Init Actions
        key = new Actions(driver);

        //Login
        cm.login(driver, domain, email, password);

        // Init Usage plan function
        up = new usage_plan(driver, domain, username_admin_page, password_admin_page);
    }

    @Test(priority = 1)
    public void TC_01_buy_usage_plan_Cancel() throws InterruptedException {
        up.buy_plan_Cancel();

        driver.close();
    }

    @Test(priority = 2)
    public void TC_02_buy_usage_plan_OK() throws InterruptedException {
        up.buy_plan_OK();

        driver.close();
    }

    @Test(priority = 3)
    public void TC_03_add_upper_limit_OK() throws InterruptedException {
        up.add_upper_limit_OK();

        driver.close();
    }

    @Test(priority = 4)
    public void TC_04_add_upper_limit_Cancel() throws InterruptedException {
        up.add_upper_limit_Cancel();

        driver.close();
    }

    @Test(priority = 5)
    public void TC_05_remove_upper_limit_OK() throws InterruptedException {
        up.remove_upper_limit_OK();

        driver.close();
    }

    @Test(priority = 6)
    public void TC_06_remove_upper_limit_Cancel() throws InterruptedException {
        up.remove_upper_limit_Cancel();

        driver.close();
    }

    @Test(priority = 7)
    public void TC_07_remove_upper_limit_Cancel() throws InterruptedException {
        System.out.println(up.get_company_id());

        driver.close();
    }
}
