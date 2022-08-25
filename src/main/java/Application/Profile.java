package Application;

import View.MainFrame;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Profile {
    private WebDriver driver;

    public Profile() {
        driver = loadProfile();
        login();
    }

    private WebDriver loadProfile() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-gpu");
        options.addExtensions(new File("jpakadanffilpnjijlmmkljogkfaognd.crx"));
        try {
            WebDriver driver = new ChromeDriver(options);
            return driver;
        } catch (SessionNotCreatedException e) {
            JOptionPane.showMessageDialog(null, "Profile is not loaded\nPlease check chrome version");
        } catch (WebDriverException e) {
            JOptionPane.showMessageDialog(null, "Profile is not loaded\nPlease turn off all other chrome of this profile processes");
        }
        return null;
    }

    private void login() {
        driver.get("https://code.ptit.edu.vn/");
        driver.findElement(By.id("login__user")).sendKeys(MainFrame.jTextFieldUser.getText());
        driver.findElement(By.id("login__pw")).sendKeys(MainFrame.jTextFieldPassWord.getText());
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/form/button")).sendKeys(Keys.ENTER);
    }

    public void submit() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            try {
                driver.findElement(By.className("submit__pad__btn")).sendKeys(Keys.ENTER);
            } catch (Exception e) {

            }
        }
    }
}
