package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    CheckoutPage(WebDriver driver){
        this.driver=driver;
    }
    public void enterFirstName(String firstName){
        driver.findElement(By.name("billing_first_name")).sendKeys(firstName);
    }
    public void enterLastName(String lastName){
        driver.findElement(By.name("billing_last_name")).sendKeys(lastName);
    }
    public void enterCompanyName(String companyName){
        driver.findElement(By.name("billing_company")).sendKeys(companyName);
    }
    public void chooseCountry(String country){
        Select countrys = new Select(driver.findElement(By.cssSelector("[name=\"billing_country\"]")));
        countrys.selectByVisibleText(country);
    }
    public void enterStreetAddress(String streetAddress){
        driver.findElement(By.name("billing_address_1")).sendKeys(streetAddress);
    }
    public void enterApartment(String  apartment){
        driver.findElement(By.name("billing_address_2")).sendKeys(apartment);
    }
    public void enterCity(String  city){
        driver.findElement(By.name("billing_city")).sendKeys(city);
    }
    public void enterState(String state){
        driver.findElement(By.name("billing_state")).sendKeys(state);
    }
    public void enterZipCode(String  zipCode){
        driver.findElement(By.name("billing_postcode")).sendKeys(zipCode);
    }
    public void enterPhone(String phone){
        driver.findElement(By.name("billing_phone")).sendKeys(phone);
    }
    public void enterEmail(String email){
        driver.findElement(By.name("billing_email")).sendKeys(email);
    }
    public void clickOnPlaceOrder(){
        driver.findElement(By.cssSelector("[name=\"woocommerce_checkout_place_order\"]")).click();
    }
    public String verificationMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"woocommerce-order\"] p")));
        return driver.findElement(By.cssSelector("[class=\"woocommerce-order\"] p")).getText();
    }
}
