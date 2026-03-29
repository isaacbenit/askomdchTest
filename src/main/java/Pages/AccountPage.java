package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    WebDriver driver;
    AccountPage(WebDriver driver){
        this.driver = driver;
    }
    public void enterLoginUsername(String username){
        driver.findElement(By.id("username")).sendKeys(username);
    }
    public void enterLoginPassword(String password){
        driver.findElement(By.id("password")).sendKeys(password);
    }
    public String verfyingLogIn(){
        driver.findElement(By.name("login")).click();
        if(!driver.findElements(By.cssSelector("[class=\"woocommerce-MyAccount-content\"] p")).isEmpty()){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"woocommerce-MyAccount-content\"] p")));
            By welcomeMessage = By.cssSelector("[class=\"woocommerce-MyAccount-content\"] p");
            return driver.findElement(welcomeMessage).getText();
        }
        else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"woocommerce-error\"] li")));
            return driver.findElement(By.cssSelector("[class=\"woocommerce-error\"] li")).getText();
        }
    }
    public void enterRegisterUsername(String username){
        driver.findElement(By.id("reg_username")).sendKeys(username);
    }
    public void enterRegisterEmail(String email){
        driver.findElement(By.id("reg_email")).sendKeys(email);
    }
    public void enterRegisterPassword(String password){
        driver.findElement(By.id("reg_password")).sendKeys(password);
    }
    public String verfyRegister(){
        driver.findElement(By.name("register")).click();
        if(!driver.findElements(By.cssSelector("[class=\"woocommerce-MyAccount-content\"] p")).isEmpty()){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"woocommerce-MyAccount-content\"] p")));
            By welcomeMessage = By.cssSelector("[class=\"woocommerce-MyAccount-content\"] p");
            return driver.findElement(welcomeMessage).getText();
        }
        else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"woocommerce-error\"] li")));
            return driver.findElement(By.cssSelector("[class=\"woocommerce-error\"] li")).getText();
        }
    }
    public String verfyLogout(){
        driver.findElement(By.cssSelector("[class=\"woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--customer-logout\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id=\"customer_login\"] h2")));
        return driver.findElements(By.cssSelector("[id=\"customer_login\"] h2")).get(0).getText();
    }
}
