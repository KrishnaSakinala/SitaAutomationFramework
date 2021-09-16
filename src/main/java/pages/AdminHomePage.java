package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminHomePage extends BasePage {

    public AdminHomePage(WebDriver driver) {
        super(driver);
    }

    // locators

    By signUpButton = By.linkText("SignUp");
    By loginButton = By.linkText("Login");

    //actions

    public AdminSignupPage navigateToAdminSignupPage() {
        driver.findElement(signUpButton).click();
        return new AdminSignupPage(driver);
    }

    public void navigateToAdminLoginPage () {
        driver.findElement(loginButton).click();
    }


}
