import com.aventstack.extentreports.Status;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTest extends ProjectTestBase {

    private final String GATECHUB_URL = "http://qatechhub.com";
    private final String FACEBOOK_URL = "http://facebook.com";
    private final String EXPECTED_TITLE = "QA Automation Tools Trainings and Tutorials | QA Tech Hub";

    @Test
    public void test_1_openGatechubLandingPage() throws IOException {
        try {
            driverFirefox.get(GATECHUB_URL);
            passed = true;
        } catch (Exception e) {
            extentTest.log(Status.FATAL,"Error on Gatechub page load");
            extentTest.log(Status.INFO, e.getMessage());
        }finally {
            if(passed)
                extentTest.log(Status.PASS, "Gatechub page loaded successfully");
               commonMethods.addScreenShotToExtentTest("GatechubLandingPage", extentTest,driverFirefox);
        }
    }

    @Test
    public void test_2_assertTitle(){
        Assert.assertEquals(EXPECTED_TITLE,driverFirefox.getTitle());
    }

    @Test
    public void test_3_openFacebookPage() throws IOException {
        try{
        driverFirefox.navigate().to(FACEBOOK_URL);
        passed = true;
    }catch (Exception e){
            extentTest.log(Status.FATAL, "Error on Facebook page load");
            extentTest.log(Status.INFO, e.getMessage());
        }finally {
            if(passed)
                extentTest.log(Status.PASS, "Facebook page loaded successfully");
                commonMethods.addScreenShotToExtentTest("Facebook page" ,extentTest, driverFirefox);
        }
    }

    @Test
    public void test_4_backToGatechubLandingPage() throws IOException {
        try{
        driverFirefox.navigate().back();
        passed = true;
    }catch (Exception e){
            extentTest.log(Status.FATAL, "error on returning back to Gatechub");
            extentTest.log(Status.INFO, e.getMessage());
        }finally {
            if(passed)
                extentTest.log(Status.PASS, "successfully returned to Gatechub");
                commonMethods.addScreenShotToExtentTest("Gatechub page" ,extentTest, driverFirefox);
        }
    }

    @Test
    public void test_5_PrintCurrentUrl(){
        System.out.println(driverFirefox.getCurrentUrl());
    }

    @Test
    public void test_6_navigateForward() throws IOException {
        try{
        driverFirefox.navigate().forward();
        passed = true;
        }catch (Exception e){
            extentTest.log(Status.FATAL, "error on forward to facebook");
            extentTest.log(Status.INFO, e.getMessage());
        }finally {
            if(passed)
                extentTest.log(Status.PASS, "forwarded to Facebook");
                commonMethods.addScreenShotToExtentTest("facebook" ,extentTest, driverFirefox);

        }
    }

    @Test
    public void test_7_ReloadPage(){
        driverFirefox.navigate().refresh();
    }
}