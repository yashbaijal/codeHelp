package com.softwaretestingboard.magento.pages;

import com.aventstack.extentreports.Status;
import com.softwaretestingboard.magento.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private WebDriver driver;
    @FindBy(xpath = "//input[@id='email']")
    public static WebElement email;

    @FindBy(xpath = "//input[@name='login[password]']")
    public static WebElement password;

    @FindBy(xpath = "//a[@class='action remind']")
    public static WebElement forgetPassword;

    @FindBy(xpath = "//a[@class='action create primary']")
    public static WebElement createAccount;

    @FindBy(xpath = "//span[text()='Sign In']")
    public static WebElement signInButton;

    @FindBy(xpath = "//li[@class='greet welcome']//span[contains(text(),'Welcome')]")
    public static WebElement welcomeText;

    public LoginPage(WebDriver driver) {

        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void loginUser(String usremail, String usrpassword)
    {
        email.sendKeys(usremail);
        password.sendKeys(usrpassword);
        signInButton.click();
        boolean status=utility.checkElemIsPresentOnPage(welcomeText,driver);
        if(status)
        {
            utility.commonLogging(Status.PASS,usremail+" User is able to sign in successfully");
        }else
        {
            utility.commonLogging(Status.FAIL,usremail+" User is not able to Sign In");
        }

    }
}
