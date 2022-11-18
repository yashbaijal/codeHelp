package com.softwaretestingboard.magento.pages;

import com.aventstack.extentreports.Status;
import com.softwaretestingboard.magento.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@class='logo']")
    public static WebElement storeLogo;

    @FindBy(xpath = "//div[@class='panel wrapper']//a[contains(text(),'Sign In')]")
    public static WebElement signIn;

    @FindBy(xpath = "//div[@class='panel wrapper']//a[text()='Create an Account']")
    public static WebElement createAnAccount;

    @FindBy(xpath = "//input[@id='search']")
    public static WebElement search;

    @FindBy(xpath = "//span[contains(text(),'Women')]")
    public static WebElement women;

    @FindBy(xpath = "//span[contains(text(),'Men')]")
    public static WebElement men;

    @FindBy(xpath = "//span[contains(text(),'Gear')]")
    public static WebElement gear;

    @FindBy(xpath = "//span[contains(text(),'Training')]")
    public static WebElement training;

    @FindBy(xpath = "//span[contains(text(),'Sale')]")
    public static WebElement sale;

    @FindBy(xpath = "//a[@id='ui-id-3']//span")
    public static WebElement whatNew;

    @FindBy(xpath="//span[text()='Create New Customer Account']")
    public static WebElement createAccountText;

    @FindBy(xpath="//span[text()='Customer Login']")
    public static WebElement customerLoginText;

    public HomePage(WebDriver driver) {

        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void userSignIn()
    {
        signIn.click();
        boolean status=utility.checkElemIsPresentOnPage(customerLoginText,driver);
        if(status)
            utility.commonLogging(Status.PASS,"Clicked on Sign In Button successfully");
        else
            utility.commonLogging(Status.PASS,"Not able to click on  Sign In Button");

    }

    public void openCreateAccount()
    {
        createAnAccount.click();
        boolean status=createAccountText.isDisplayed();
        if(status)
            utility.commonLogging(Status.PASS,"User is able to click on Create Account button");
        else
            utility.commonLogging(Status.FAIL,"Not able to click on Create Account button");
    }


}
