package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    FileInputStream fis;
    Properties config;

    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    @BeforeSuite
    public void config() throws IOException {
        fis = new FileInputStream("src/main/resources/config/config.properties");
        config = new Properties();
        config.load(fis);

        if (extentReports == null) {
            extentSparkReporter = new ExtentSparkReporter("Reports/AutomationReport.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);

            extentReports.setSystemInfo("OS", "Windows 10");
            extentReports.setSystemInfo("Environment", "QA");
            extentReports.setSystemInfo("Browser", "Chrome");
            extentReports.setSystemInfo("Tester", "Krishna");

            extentSparkReporter.config().setReportName("Hospital Management Application Report");
            extentSparkReporter.config().setDocumentTitle("HMA Report");
            extentSparkReporter.config().setTheme(Theme.DARK);
        }
    }

    @BeforeMethod
    public void beforeMethod() {

        // launch browser
        if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();

        // navigate to ur applicatio url
        driver.get(config.getProperty("applicationUrl"));
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws Exception {

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Test Failed...");
            extentTest.fail(result.getThrowable());
            String path = captureScreenshot(driver, result.getMethod().getMethodName());
            System.out.println("Report Path: " + path);
            extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test Passed...");
        } else {
            extentTest.skip("Test Skipped...");
        }

        Thread.sleep(3000);
        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        extentReports.flush(); // flush all the captured information to the report.
    }

    public String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // D:\eclipse-workspace\SitaAutomationFramework\Screenshots
        //String dest = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png";
        String dest = "D:/eclipse-workspace/SitaAutomationFramework/Screenshots/" + screenshotName + ".png";
        File destination = new File(dest);
        FileHandler.copy(source, destination);
        return dest;
    }
}