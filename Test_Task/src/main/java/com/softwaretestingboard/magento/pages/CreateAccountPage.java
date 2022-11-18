package com.softwaretestingboard.magento.pages;

import com.RestAPI.Pojos.response.Results;
import com.aventstack.extentreports.Status;
import com.softwaretestingboard.magento.Base.BasePage;
import com.softwaretestingboard.magento.util.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CreateAccountPage extends BasePage {


    private WebDriver driver;
    public  String regestriedEmail="";
    public String  userPassword="";
    @FindBy(xpath = "//input[@id='firstname']")
    public static WebElement firstname;

    @FindBy(xpath = "//input[@id='lastname']")
    public static WebElement lastname;

    @FindBy(xpath = "//input[@id='email_address']")
    public static WebElement email;

    @FindBy(xpath = "//input[@title='Password']")
    public static WebElement password;

    @FindBy(xpath = "//input[@name='password_confirmation']")
    public static WebElement confirmPassword;

    @FindBy(xpath = "//button[@class='action submit primary']")
    public static WebElement createAccountButton;

    @FindBy(xpath = "//input[@name='is_subscribed']")
    public static WebElement newsLetterSignUpCheckBox;

    @FindBy(xpath = "//input[@id='newsletter']")
    public static WebElement emailForNewsLetter;

    @FindBy(xpath = "//button[@class='action subscribe primary']")
    public static WebElement suscribeNewLetterButton;

    @FindBy(xpath="//div[@class='page messages']//div[@data-ui-id=\"message-success\"]//div")
    public static WebElement accountCreatedMessage;

    @FindBy(xpath = "//a[@class='logo']")
    public static WebElement logo;

    @FindBy(xpath = "//div[@class='message-error error message']/div")
    public static WebElement errorMessageCreateAccountForAlreadyRegisteredUser;

    public CreateAccountPage(WebDriver driver) {

        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void openBoard()
    {
        String url= Utility.getPropertyKey("URL");
        driver.get(url);
        boolean isLogoPresent=logo.isDisplayed();
        if(isLogoPresent)
            utility.commonLogging(Status.PASS,"Logo is Present.Application is loaded successfully");
        else
            utility.commonLogging(Status.FAIL,"Not able to load the application");

    }

    public void createAccount(Results results)
    {
        try{

            firstname.sendKeys(results.getName().getFirst());
            lastname.sendKeys(results.getName().getLast());

            email.sendKeys(results.getEmail());
            regestriedEmail=results.getEmail();
            password.sendKeys(results.getLogin().getPassword());
            userPassword=results.getLogin().getPassword();
            confirmPassword.sendKeys(results.getLogin().getPassword());

            createAccountButton.click();
            Thread.sleep(6000);
            boolean status=utility.checkElemIsPresentOnPage(accountCreatedMessage,driver);
            if(status)
                utility.commonLogging(Status.PASS,"Account is created successfully for user "+results.getEmail());
            else
                utility.commonLogging(Status.FAIL,"Not able to create user account");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void validateErrorForAlreadyRegisteredUser(Results results)
    {
        try {
            firstname.sendKeys(results.getName().getFirst());
            lastname.sendKeys(results.getName().getLast());

            email.sendKeys(results.getEmail());
            regestriedEmail=results.getEmail();
            password.sendKeys(results.getLogin().getPassword());
            userPassword=results.getLogin().getPassword();
            confirmPassword.sendKeys(results.getLogin().getPassword());

            createAccountButton.click();
            Thread.sleep(5000);
            boolean status = utility.checkElemIsPresentOnPage(errorMessageCreateAccountForAlreadyRegisteredUser, driver);

            if (status)
                utility.commonLogging(Status.PASS, "User Alreday Registered");
            else
                utility.commonLogging(Status.FAIL, "First Time User");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
