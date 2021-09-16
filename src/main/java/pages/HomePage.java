package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    // capture the required elements
    By adminLink = By.linkText("Admin");
    By doctorLink = By.linkText("Doctor");

    public HomePage(WebDriver driver) {
        super(driver); // calling parent class constructor.
    }

    // actions to be performed on the page

    public AdminHomePage navigateToAdminHomePage() {
        driver.findElement(adminLink).click();
        return new AdminHomePage(driver);
    }

    public void navigateToDoctorHomePage() {
        driver.findElement(doctorLink).click();
    }

    public String method() {
        return driver.getTitle();
    }
}

