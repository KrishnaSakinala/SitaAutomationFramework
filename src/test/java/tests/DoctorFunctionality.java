package tests;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DoctorFunctionality extends BaseTest {

    @Test(description = "Create Doctor and verify login.")
    public void verifyDoctorCreation() throws InterruptedException {

        // click on doctor link on home page
        driver.findElement(By.linkText("Doctor")).click();

        // click on apply button on doctor home page
        driver.findElement(By.linkText("Apply")).click();

        // doctor signup
        driver.findElement(By.id("id_first_name")).sendKeys("John");
        driver.findElement(By.id("id_last_name")).sendKeys("Paul");
        driver.findElement(By.id("id_username")).sendKeys("John");
        driver.findElement(By.id("id_password")).sendKeys("password");
        Select department = new Select(driver.findElement(By.id("id_department")));
        department.selectByIndex(1);
        driver.findElement(By.id("id_mobile")).sendKeys("9872541256");
        driver.findElement(By.id("id_address")).sendKeys("India");
        driver.findElement(By.id("id_profile_pic")).sendKeys("D:\\eclipse-workspace\\SampleDummy1\\pictures\\Doctor.png");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(5000);

        // doctor login
        driver.findElement(By.id("id_username")).sendKeys("John");
        driver.findElement(By.id("id_password")).sendKeys("password");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // greeting verification
        String doctorApplyGreetingMessage = driver.findElement(By.tagName("h1")).getText();

        Assert.assertTrue(doctorApplyGreetingMessage.contains("John"));
    }
}