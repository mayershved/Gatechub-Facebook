import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

public class ProjectTestBase {

    protected static WebDriver driverFirefox;

    protected static ExtentReports extentReports;

    protected static ExtentTest extentTest;

    protected static CommonMethods commonMethods = new CommonMethods();

    protected static boolean passed = false;

    @BeforeClass
    public static void projectTestBase(){
        // webDriver for the ptoject
        commonMethods.setWebDriver();
        driverFirefox = commonMethods.getDriverFirefox();
        // extentReport for the project
        commonMethods.setExtentReports();
        extentReports = commonMethods.getExtentReports();
        // ExtentTest
        extentTest = extentReports.createTest("Gatechub-Facebook","open, navigate, refresh");


    }

    @After
    public void afterEachTest(){
        passed = false;
    }

    @AfterClass
    public static void tearsDown() throws InterruptedException {
        Thread.sleep(1000);
        extentReports.flush();
        driverFirefox.quit();
    }
}
