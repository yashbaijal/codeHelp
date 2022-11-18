package com.softwaretestingboard.magento.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class Driver {

    private  WebDriver driver ;
    private int timeOut=10;

    public  void initialize() {
        WebDriver driverTemp = null;
        String browser = Utility.getPropertyKey("BROWSER_TYPE");
        System.out.println("***Inside Base Test Before Method***  "+browser);
        if (browser.contentEquals("firefox")) {

            try {
                WebDriverManager.firefoxdriver().setup();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FirefoxOptions fo = new FirefoxOptions();
            if (Utility.getPropertyKey("HEADLESS_SUPPORT").equalsIgnoreCase("Y")) {
                fo.addArguments("--headless",
                        "--window-size=1980,1080", "--disable-dev-shm-usage",
                        "--disable-gpu");
            }
            fo.setAcceptInsecureCerts(true);
            fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

            driverTemp = new FirefoxDriver();
            driver=driverTemp;
            //driver.set(driverTemp);
        }
        else if (browser.contentEquals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            try {
                System.out.println("***Using WebDriver Manager To Setup Chrome**");
                //WebDriverManager.chromedriver().clearCache();
                Thread.sleep(3000);
                WebDriverManager.chromedriver().setup();
               // WebDriverManager.chromedriver().version("90.0.4430.24").setup();
            } catch (Exception e) {
                System.out.println("Not able to setup Chrome browser");
               // System.setProperty("webdriver.chrome.driver", wdPath);
                e.printStackTrace();
            }
            //Map<String, Object> prefs = new HashMap<String, Object>();
            if (Utility.getPropertyKey("HEADLESS_SUPPORT").equalsIgnoreCase("Y")) {
                System.out.println("***Inside Set Headless Support**");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("–-disable-notifications");
                chromeOptions.addArguments("--window-size=1980,1080");
                chromeOptions.addArguments("--allow-insecure-localhost");
                chromeOptions.addArguments("--disable-gpu");

            }
            chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,
                    true);

            try {
                System.out.println("**Set ChromeOptions**");

                Thread.sleep(5000);
                System.out.println("Driver Temp "+driverTemp);
                driverTemp = new ChromeDriver(chromeOptions);
                System.out.println("Driver Temp "+driverTemp);
                //driver.set(new ChromeDriver(chromeOptions));
                Thread.sleep(9000);
                System.out.println("***Verfiy Driver Inside Try***");
                driver=driverTemp;

            }catch (Exception e){
                System.out.println("***Verfiy Driver Inside Catch***");
                e.printStackTrace();
            }
            System.out.println("***Verfiy Driver***"+driver);
            getDriver().manage().window().maximize();
            //getDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT,
                 //   TimeUnit.SECONDS);

        }

    }

    /**
     * Closes the current driver.
     */
    public void close() {
        this.driver.close();
    }

    /**
     * Closes the all the active driver.
     */
    public void quit() {
        this.driver.quit();
    }

    public FluentWait<WebDriver> Wait() {
        return new
                FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOut))

                .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
    }

    public  WebDriver getDriver() {
        return this.driver;
    }
}
