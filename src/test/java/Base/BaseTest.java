package Base;

import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homepage;

    @BeforeClass
    public void setUP(){
        driver = new ChromeDriver();
    }
    @BeforeMethod
    public void beforeMethod(){
        homepage = new HomePage(driver);
        driver.get("https://askomdch.com/");
    }
}
