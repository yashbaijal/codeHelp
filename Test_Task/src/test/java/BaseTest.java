import com.aventstack.extentreports.Status;
import com.softwaretestingboard.magento.util.Driver;
import com.softwaretestingboard.magento.util.ExtentHtmlReporterManager;
import org.testng.IMethodInstance;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Listeners({ExtentHtmlReporterManager.class})
public class BaseTest extends Driver {


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

        initialize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            ExtentHtmlReporterManager extentHtmlReporterManager = new
                    ExtentHtmlReporterManager();
            String className =
                    result.getMethod().getRealClass().getSimpleName();
            String methodName = result.getName();
            ExtentHtmlReporterManager.getTest().assignCategory(className);
        }
        System.gc();
        /*try {
            if (getDriver() != null) {
                close();
                quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}
