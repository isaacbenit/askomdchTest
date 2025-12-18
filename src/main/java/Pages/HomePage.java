package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public By Store = By.cssSelector("a[href=\"/store\"]");
public HomePage(WebDriver driver) {
    this.driver = driver;
}

    public StorePage clickOnShopNow(){
    driver.findElement(Store).click();
    return new StorePage(driver);
}
    public AccountPage clickAccountPage(){
        driver.findElement(By.cssSelector(".menu-item-1237 a")).click();
        return new AccountPage(driver);
    }
}
