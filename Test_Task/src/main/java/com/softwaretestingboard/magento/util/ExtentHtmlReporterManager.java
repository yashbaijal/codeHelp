package com.softwaretestingboard.magento.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class ExtentHtmlReporterManager extends Driver implements ITestListener {

    private static ExtentReports extentReports = null;
    private static Utility utility = new Utility();
    private static ExtentTest extentTest;
    private final static String FILENAME = System.getProperty("user.dir") + File.separator;

    public  static ExtentTest getTest() {
        return extentTest;
    }

    public synchronized static void setTest(ExtentTest test) {
        ExtentTest test1=getTest();
        test1=test;
    }


    public synchronized ExtentReports getInstance() {
        if (extentReports == null)
            createInstance();
        return extentReports;
    }

    public synchronized ExtentReports createInstance() {
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(FILENAME + Utility.getPropertyKey("REPORT_FILE_PATH"));
        extentHtmlReporter.config().setDocumentTitle("Automation Report");
        extentHtmlReporter.config().setReportName("AutomationReports");
        extentHtmlReporter.config().setTheme(Theme.STANDARD);
        extentHtmlReporter.config().setEncoding("utf-8");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);

        extentReports.setSystemInfo("App Build Stamp", "");
        extentReports.setSystemInfo("Environment", "GDC");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));

        extentReports.setSystemInfo("Browser Name", Utility.getPropertyKey("BROWSER_TYPE"));

        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        Reporter.log(context.getName().trim() + " = [Context Name : onStart]<br>");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        Reporter.log(context.getName().trim() + " = [Context Name : onFinish]<br>");

        ITestNGMethod[] totalTestCasesCount = context.getAllTestMethods();
        //ConfigPropertiesReader.totalTestCasesCount = String.valueOf(totalTestCasesCount.length);

        Set<ITestResult> passedTestCasesCount = context.getPassedTests().getAllResults();
       // ConfigPropertiesReader.passedTestCasesCount = String.valueOf(passedTestCasesCount.size());

        Set<ITestResult> failedTestCasesCount = context.getFailedTests().getAllResults();
       // ConfigPropertiesReader.failedTestCasesCount = String.valueOf(failedTestCasesCount.size());

        Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();
       // ConfigPropertiesReader.skippedTestCasesCount = String.valueOf(skippedTests.size());

        for (ITestResult temp : skippedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getFailedTests().getResults(method).size() > 0) {
                skippedTests.remove(temp);
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    skippedTests.remove(temp);
                }
            }
        }

        for (String s : Reporter.getOutput()) {
            extentReports.setTestRunnerOutput(s);
        }
        try {
           // extentReports.flush();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {

        System.out.println(result.getName());
        Reporter.log(result.getName() + " = [OnTestStart]<br>");
        String methodName = getMethodNamewithParams(result);
        extentTest = getInstance().createTest(result.getMethod().getMethodName());
        ExtentHtmlReporterManager.setTest(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        Reporter.log(result.getName() + " = [Pass]<br>");
        String className = result.getMethod().getRealClass().getSimpleName();
        extentTest.assignCategory(className);

        extentTest.pass(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN));

        getInstance().flush();
    }

    public void onTestFailure(ITestResult result) {
        Reporter.log(result.getName() + " = [Fail]<br>");
        String className = result.getMethod().getRealClass().getSimpleName();
        System.out.println("***Inside onTestFailure printing className**"+className);
        String methodName = result.getName();
        try {
            ExtentHtmlReporterManager.getTest().assignCategory(className);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

         /*   try {
                ExtentHtmlReporterManager.getTest().get().addScreenCaptureFromPath(takeScreenshot(DriverThreadManager.getDriver(), methodName));
                utility.makeFile(DriverThreadManager.getDriver().getPageSource());
            } catch (Exception e) {
                e.printStackTrace();
            }
    */
        getInstance().flush();
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {

        if (result.getThrowable().toString().contains("exception.SkipTestException")) {
            Reporter.log(result.getName() + " = [Skip]<br>");
            String methodName = getMethodNamewithParams(result);
            String className = result.getMethod().getRealClass().getSimpleName();
            ExtentHtmlReporterManager.getTest().assignCategory(className);

            ExtentHtmlReporterManager.getTest().skip(result.getThrowable());
            try {
                ExtentHtmlReporterManager.getTest().addScreenCaptureFromPath(takeScreenshot(getDriver()));
                        //,methodName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ExtentHtmlReporterManager.getTest().skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
        }
        getInstance().flush();
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public synchronized void logStatusInfo(String details) {
        ExtentHtmlReporterManager.getTest().log(Status.INFO, details);
    }

    public String getMethodNamewithParams(ITestResult result) {
        String methodName = result.getName();
        String nextLineCharacter = "<br>";
        if (result.getParameters().length > 0 && result.getParameters()[0] instanceof HashMap) {
            methodName = methodName + nextLineCharacter + result.getParameters()[0].toString();
        }

        return methodName;
    }


    public synchronized String takeScreenshot(WebDriver driver) {
        System.out.println("Inside Take ScreenShot Method");
        String fileName = "screenShot" + System.currentTimeMillis() + ".jpg";

        File srcFile = ((TakesScreenshot)driver ).getScreenshotAs(OutputType.FILE);
        // String targetFilePath = "extentreport/Screenshots/" + fileName;
        String targetDir = System.getProperty("user.dir")+File.separator+"extentreport"+File.separator+"Screenshots";
        System.out.println("** Target dir **"+targetDir);
        File directory = new File(targetDir);
        if (! directory.exists()){
            directory.mkdirs();
        }
        String targetFilePath = targetDir+File.separator+ fileName;
        String filepath = "Screenshots" + File.separator+fileName;
        File targetFile = new File(targetFilePath);
        String base64 = null;
        try {
            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            extentTest.log(Status.PASS, "", MediaEntityBuilder.createScreenCaptureFromPath(filepath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFilePath;
    }

    public synchronized String takeScreenshotInPNG(WebDriver driver) {
        String fileName = "screenShot" + LocalDate.now() + "-" + System.currentTimeMillis() + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targetFilePath = "extentreport/Screenshots/" + fileName;
        String filepath = "Screenshots/" + fileName;
        File targetFile = new File(targetFilePath);
        String base64 = null;
        try {
            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            extentTest.log(Status.PASS, "", MediaEntityBuilder.createScreenCaptureFromPath(filepath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFilePath;
    }


}
