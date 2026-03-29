package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    By productName= By.cssSelector(".product-name a");
    By quantity= By.cssSelector("input[type=\"number\"]");
    private String deletedProduct;
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getProductName(){
        var productList = driver.findElements(productName);
        return productList.get(productList.size()-1).getText();
    }
    public String getQuantity(){
        var quantityList = driver.findElements(quantity);
        return quantityList.get(quantityList.size()-1).getAttribute("value");
    }
    public String checkDeletedProduct(int id){
        deletedProduct = driver.findElements(productName).get(id).getText();
       return deletedProduct;
    }
    public String removeProduct(int id){
        By remove = By.className("remove");
//        deletedProduct = driver.findElements(productName).get(id).getAttribute("value");
        driver.findElements(remove).get(id).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElements(productName).get(id)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"woocommerce-message\"]")));
        return driver.findElement(By.cssSelector("[class=\"woocommerce-message\"]")).getText();
    }
    public String undoProductDelete(){
        By undo = By.linkText("Undo?");
        By undoText = By.cssSelector("[class=\"woocommerce-message\"]");
        driver.findElement(undo).click();
        return driver.findElement(undoText).getText();
    }
    public Boolean checkProductQuantityUpdate(int id,String quantity){
        By input = By.cssSelector("[type=\"number\"]");
        var updateButton = By.cssSelector("[name=\"update_cart\"]");
        var priceOfOneProduct = By.cssSelector("[class=\"product-price\"] bdi");
        driver.findElements(input).get(id).clear();
        driver.findElements(input).get(id).sendKeys(quantity);
        driver.findElement(updateButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"woocommerce-message\"]")));
        var updatedPrice = By.cssSelector("[class=\"product-subtotal\"] bdi");
        return (int)Double.parseDouble(driver.findElements(priceOfOneProduct).get(id).getText().substring(1))* Integer.parseInt(quantity) == (int)Double.parseDouble(driver.findElements(updatedPrice).get(id).getText().substring(1));
    }
    public CheckoutPage clickOnCheoutButton(){
        driver.findElement(By.cssSelector("[class=\"wc-proceed-to-checkout\"] a")).click();
        return new CheckoutPage(driver);
    }

}
