package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetail {
    WebDriver driver;
    public ProductDetail(WebDriver driver) {
        this.driver = driver;
    }
    public String verfyingProductDetail(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"product_title entry-title\"]")));
        return driver.findElement(By.cssSelector("[class=\"product_title entry-title\"]")).getText();
    }
    public String verfyAddToCartOnProductDetailPage(){
        driver.findElement(By.cssSelector("[name=\"add-to-cart\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"woocommerce-message\"]")));
        return driver.findElement(By.cssSelector("[class=\"woocommerce-message\"]")).getText();
    }


}
