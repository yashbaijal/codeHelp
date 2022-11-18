package com.softwaretestingboard.magento.pages;

import com.RestAPI.Pojos.response.Results;
import com.aventstack.extentreports.Status;
import com.softwaretestingboard.magento.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage extends BasePage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@name='street[0]']")
    public static WebElement streetAddress1;

    @FindBy(xpath = "//input[@name='street[1]']")
    public static WebElement streetAddress2;

    @FindBy(xpath = "//input[@name='street[2]']")
    public static WebElement streetAddress3;

    @FindBy(xpath = "//input[@name='city']")
    public static WebElement city;

    @FindBy(xpath = "//select[@name='region_id']")
    public static WebElement stateOrProvince;

    @FindBy(xpath = "//input[@name='postcode']")
    public static WebElement zipCode;

    @FindBy(xpath = "//select[@name='country_id']")
    public static WebElement country;

    @FindBy(xpath = "//input[@name='telephone']")
    public static WebElement telephhone;

    @FindBy(xpath = "//input[@name='ko_unique_1']")
    public static WebElement bestWay;

    @FindBy(xpath = "//input[@name='ko_unique_2']")
    public static WebElement flatRate;

    @FindBy(xpath = "//div[@id='shipping-method-buttons-container']//button[@class='button action continue primary']")
    public static WebElement next;

    @FindBy(xpath = "//button[@class='action primary checkout']")
    public static WebElement placeOrder;

    @FindBy(xpath="//a[@role='menuitem'][@id='ui-id-3']")
    public static WebElement whatNewLink;

    @FindBy(xpath ="//span[text()=\"New in women's\"]/parent::strong//following-sibling::ul//descendant::a[contains(@href,'women/tops-women/jackets-women.html')]")
    public static WebElement womenJacketsLink;

    @FindBy(xpath="//ol[@class='products list items product-items']")
    public static WebElement productList;

    @FindBy(xpath="//button[@title='Add to Cart']")
    public static WebElement addToCart;

    @FindBy(xpath = "//div[@option-label='XS']")
    public static WebElement selectSize;

    @FindBy(xpath="//div[@option-label='Black']")
    public static WebElement blackColour;

    @FindBy(xpath="//a[@class='action showcart']")
    public static WebElement cartOption;

    @FindBy(xpath = "//button[@id='top-cart-btn-checkout']")
    public static WebElement checkOut;

    @FindBy(xpath = "//input[@name='company']")
    public static WebElement company;

    @FindBy(xpath = "//span[text()='Ship here']/parent::button")
    public static WebElement shipHere;

    @FindBy(xpath = "//span[@class='base']")
    public static WebElement purchasedText;

    public CheckOutPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void checkOut(Results results) throws InterruptedException {
        Thread.sleep(5000);
        utility.click(whatNewLink,"Click on What's New Link Button",driver);
        //whatNewLink.click();
        utility.click(womenJacketsLink,"Click on Women's Jacket Link Button",driver);
        //womenJacketsLink.click();
        utility.click(productList.findElements(By.tagName("li")).get(0),"Click on First product from product list",driver);
        //productList.findElements(By.tagName("li")).get(0).click();
        //selectSize.click();
        utility.click(selectSize,"Click on Product Size from product list",driver);
       // blackColour.click();
        utility.click(blackColour,"Click on Product colour from product list",driver);
        Thread.sleep(3000);
       // addToCart.click();
        utility.click(addToCart,"Click on Add product to cart",driver);
        Thread.sleep(3000);
      //  cartOption.click();
        utility.click(cartOption,"Click on Cart Button",driver);
        Thread.sleep(3000);
       // checkOut.click();
        utility.click(checkOut,"Click on Proceed to Checkout Button",driver);
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(streetAddress1));
        Thread.sleep(7000);
       // company.sendKeys(results.getLocation().);
        streetAddress1.sendKeys("Flat 45 A Metro Apartments");
        city.sendKeys(results.getLocation().getCity());
        Thread.sleep(2000);
        stateOrProvince.sendKeys(results.getLocation().getState());
        zipCode.sendKeys(results.getLocation().getPostcode());
        //stateOrProvince.sendKeys(results.getLocation().getState());
        country.sendKeys(results.getLocation().getCountry());
        telephhone.sendKeys(results.getPhone());
       // shipHere.click();
       // flatRate.click();
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);",next);
        flatRate.click();
        next.click();
        Thread.sleep(6000);
        utility.click(placeOrder,"Click on Place Order Button",driver);
        //placeOrder.click();
        Thread.sleep(6000);
        boolean status=utility.checkElemIsPresentOnPage(purchasedText,driver);
        if(status)
        {
            utility.commonLogging(Status.PASS," User is able to make the purchase successfully");
        }else
        {
            utility.commonLogging(Status.FAIL," User is not able to make the purchase ");
        }



    }



}
