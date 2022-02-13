package Common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


public class Common {
    public WebDriver setupWebdriver() {
        //Config Webdriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public void login(WebDriver driver, String url, String mail, String password) throws InterruptedException {
        driver.get(url);
        driver.findElement(By.cssSelector("#username")).sendKeys(mail);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        sleep(1000);
    }

    public Boolean authorized(String role, List<String> role_list) {
        if (role_list.contains(role)) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> role_list(int number_of_role) {

        List<String> role_list = new ArrayList<String>();
        for (int i = 1; i <= number_of_role; i++) {
            // add Master role
            if (i == 1) {
                role_list.add("Master");
            }
            // add Administrator role
            else if (i == 2) {
                role_list.add("Administrator");
            }
            // add Responsible person role
            else if (i == 3) {
                role_list.add("Responsible person");
            }
            // add Leader role
            else if (i == 4) {
                role_list.add("Leader");
            }
            // add Member role
            else if (i == 5) {
                role_list.add("Member");
            }
            // add Guest role
            else {
                role_list.add("Guest");
            }
        }
        return role_list;
    }

    public void closeBrowser(WebDriver driver) {
        // Close browser
        driver.close();
    }
}
