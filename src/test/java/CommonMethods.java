import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommonMethods {

    private WebDriver driverFirefox;
    private ExtentReports extentReports;
    private final String SCREEN_SHOT_PATH = "/Users/igor_shved/Desktop/shots/";

    public void setWebDriver(){
        System.setProperty("webdriver.gecko.driver", "/Users/igor_shved/Documents/Java/libraries/webDrivers/geckodriver");
        driverFirefox = new FirefoxDriver();
        driverFirefox.manage().window().maximize();
        driverFirefox.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driverFirefox.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    public void setExtentReports(){
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("/Users/igor_shved/Desktop/reports/gatechub_facebook_report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Environment", "Production");
        extentReports.setSystemInfo("Developer", "Mayer Shved");
    }

    public WebDriver getDriverFirefox(){
        return driverFirefox;
    }

    public ExtentReports getExtentReports() {
        return extentReports;
    }

    public String takeScreenShot(String pahtToScreenShot, WebDriver driver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(pahtToScreenShot+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
        return pahtToScreenShot+".png";
    }

    public void addScreenShotToExtentTest(String details, ExtentTest extentTest, WebDriver driver) throws IOException {
        String currentTime = String.valueOf(System.currentTimeMillis());
        extentTest.pass(details, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(SCREEN_SHOT_PATH + currentTime,driver)).build());
    }
}
