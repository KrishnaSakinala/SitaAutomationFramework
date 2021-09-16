package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.TestData;

public class AdminSignupPage extends BasePage {

    public AdminSignupPage(WebDriver driver) {
        super(driver);
    }

    By firstNameTextbox = By.id("id_first_name");
    By lastNameTextbox = By.id("id_last_name");
    By userNameTextbox = By.id("id_username");
    By passwordTextbox = By.id("id_password");
    By submitButton = By.className("btnSubmit");

    public AdminLoginPage createNewAdmin (String userName, String password) {

        String firstName = TestData.firstName();
        String lastName = TestData.lastName();

        driver.findElement(firstNameTextbox).sendKeys(firstName);
        driver.findElement(lastNameTextbox).sendKeys(lastName);
        driver.findElement(userNameTextbox).sendKeys(userName);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(submitButton).click();
        return new AdminLoginPage(driver);
    }

}
