package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage extends BasePage {

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    By userNameTextbox = By.id("id_username");
    By passwordTextbox = By.id("id_password");
    By submitButton = By.className("btnSubmit");

    public void adminLogin (String userName, String password) {

        driver.findElement(userNameTextbox).sendKeys(userName);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(submitButton).click();
    }
}
