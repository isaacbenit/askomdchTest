package Account;

import Base.BaseTest;
import Pages.AccountPage;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AccountTest extends BaseTest {
    @Test
    public void register(){
        String name="isaac";
        AccountPage accountPage = homepage.clickAccountPage();
        accountPage.enterRegisterEmail("isaac@gmail.com");
        accountPage.enterRegisterPassword("isaac");
        accountPage.enterRegisterUsername(name);
        String message = accountPage.verfyRegister();

//        verfying login using correct name and password

        assertEquals("Hello "+name,message.substring(0,message.indexOf("(")).trim());

//        verfying login using incorrect name or password

//        assertEquals("Error: An account is already registered with your email address. Please log in.",accountPage.verfyRegister());
    }
    @Test
    public void login(){
        String name="fjkq2910";
        AccountPage accountPage = homepage.clickAccountPage();
        accountPage.enterLoginUsername(name);
        accountPage.enterLoginPassword("isaac");
        String message = accountPage.verfyingLogIn();

//        verfying login using correct name and password

//        assertEquals("Hello "+name,message.substring(0,message.indexOf("(")).trim());
//        System.out.println(message.substring(0,message.indexOf("(")).trim());

//        verfying login using incorrect name or password

        assertTrue(accountPage.verfyingLogIn().startsWith("Error:"));
    }
    @Test
    public void LogOut(){
        String name="isaac";
        AccountPage accountPage = homepage.clickAccountPage();
        accountPage.enterLoginUsername(name);
        accountPage.enterLoginPassword("isaac");
        accountPage.verfyingLogIn();
        assertEquals("Login",accountPage.verfyLogout());
    }
}
