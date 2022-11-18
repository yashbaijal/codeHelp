package com.softwaretestingboard.magento.util;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

public class Utility {

    private static File filepath=new File(System.getProperty("user.dir").concat(File.separator).concat("/src/main/resources").concat(File.separator).concat("config.properties"));


    public static String getPropertyKey(String key) {
        String keyValue="";
        try{
            Properties properties= new Properties();
            properties.load(new FileInputStream(filepath));
            keyValue=properties.getProperty(key);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return keyValue;
    }

    public void commonLogging(Status status, String message) {
        switch (status) {
            case PASS:
                ExtentHtmlReporterManager.getTest().log(Status.PASS, message);
                break;
            case INFO:
                ExtentHtmlReporterManager.getTest().log(Status.INFO, message);
                break;
            case FAIL:
                ExtentHtmlReporterManager.getTest().log(Status.FAIL, message);
                Assert.fail(message);
                break;
            case DEBUG:
                ExtentHtmlReporterManager.getTest().log(Status.DEBUG, message);
                break;
            case ERROR:
                ExtentHtmlReporterManager.getTest().log(Status.ERROR, message);
                break;
            case FATAL:
                ExtentHtmlReporterManager.getTest().log(Status.FATAL, message);
                break;
            case SKIP:
                ExtentHtmlReporterManager.getTest().log(Status.SKIP, message);
                break;
            case WARNING:
                ExtentHtmlReporterManager.getTest().log(Status.WARNING, message);
                break;
        }
    }
        public  boolean checkElemIsPresentOnPage(WebElement elementName,WebDriver driver)
        {
            try {
                FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(45))
                        .pollingEvery(Duration.ofMillis(5)).ignoring(NoSuchElementException.class);
                fWait.until(ExpectedConditions.visibilityOf(elementName));
                if (elementName.isDisplayed() && elementName.isEnabled()) {
                    return true;
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
            return false;
        }

    public void click(WebElement element, String label,WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {

            wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
            element.click();
            commonLogging(Status.INFO,label);
        } catch (Exception ex1) {
            try {
                executeJS(element, "arguments[0].click();",driver);
                commonLogging(Status.INFO,label);
            } catch (Exception ex2) {
                try {
                    executeJS(element, "arguments[0].scrollIntoView(true);",driver);
                    element.click();
                    commonLogging(Status.INFO,label);
                } catch (Exception ex3) {
                    try {
                        Actions build = new Actions(driver);
                        build.moveToElement(element, 0, 0).click().build().perform();
                        commonLogging(Status.INFO,label);
                    } catch (Exception ex4) {
                            new SoftAssert().fail(String.format("Element '%s' not found", label));
                            commonLogging(Status.FAIL,label);
                    }
                }
            }
        }
    }


    public void executeJS(WebElement element, final String script,WebDriver driver) {
        final String name = (String) ((JavascriptExecutor) driver).executeScript(script, element);
    }


}
