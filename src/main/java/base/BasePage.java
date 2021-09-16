package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click() {
        driver.findElement(By.xpath("")).click();
    }
}
