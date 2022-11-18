
import com.RestAPI.Pojos.response.CreateUserResponse;
import com.RestAPI.Pojos.response.Results;
import com.RestAPI.helper.APIhelper;
import com.aventstack.extentreports.Status;
import com.softwaretestingboard.magento.pages.CheckOutPage;
import com.softwaretestingboard.magento.pages.CreateAccountPage;
import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.pages.LoginPage;
import com.softwaretestingboard.magento.util.Utility;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class Tests extends BaseTest {

    private Utility utility = new Utility();
    private LoginPage loginPage ;
    private CreateAccountPage createAccountPage ;
    private HomePage homePage ;
    private CheckOutPage checkOutPage;
    private String registeredUserEmail="";
    private String registereduserPassword="";
    private Results results;
    private CreateUserResponse createUser;
    @BeforeMethod
    public void testSetUp(){
        loginPage =new LoginPage(getDriver());
        createAccountPage = new CreateAccountPage(getDriver());
        homePage = new HomePage(getDriver());
        checkOutPage = new CheckOutPage(getDriver());

    }

    public void generateUser()
    {
        Response response= APIhelper.excecuteAndGetResponse();
        boolean status= response.getStatusCode()==200;
        if(status) {
            utility.commonLogging(Status.PASS, "User is created successfully");
        }else
        {
            utility.commonLogging(Status.FAIL, "User is not created");
        }
        createUser=APIhelper.getResponseObj(response);
        utility.commonLogging(Status.INFO, "Response of API "+response.asString());
    }

    @Test
    public void createAccount()
    {
        generateUser();
        results=createUser.getResults().get(0);
        createAccountPage.openBoard();
        homePage.openCreateAccount();
        createAccountPage.createAccount(createUser.getResults().get(0));
        registeredUserEmail=createAccountPage.regestriedEmail;
        registereduserPassword=createAccountPage.userPassword;
    }

    @Test(dependsOnMethods={"createAccount"})
    public void validateUserSignIn() throws InterruptedException {
        createAccountPage.openBoard();
        homePage.userSignIn();
        loginPage.loginUser(registeredUserEmail,registereduserPassword);
    }

    @Test(dependsOnMethods={"createAccount","validateUserSignIn"})
    public void validatePurchase() throws InterruptedException {
        createAccountPage.openBoard();
        homePage.userSignIn();
        loginPage.loginUser(registeredUserEmail,registereduserPassword);
        checkOutPage.checkOut(results);
    }

    @Test(dependsOnMethods={"createAccount"})
    public void verifyErrorWhileRegisteringAlreadyRegisteredUser()
    {
        createAccountPage.openBoard();
        homePage.openCreateAccount();
        createAccountPage.validateErrorForAlreadyRegisteredUser(createUser.getResults().get(0));
    }
}
