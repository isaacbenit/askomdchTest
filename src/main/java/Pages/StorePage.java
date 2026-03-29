package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StorePage {
    WebDriver driver;
    By Heading = By.cssSelector("header h1");
    By search = By.cssSelector("input[class=\"search-field\"]");
    By category = By.id("product_cat");
    By sort = By.cssSelector("select[name=\"orderby\"]");
    By minPrice = By.cssSelector("span[class=\"from\"]");
    By maxPrice = By.cssSelector("span[class=\"to\"]");
    By inputSpan = By.cssSelector("span[tabindex=\"0\"]");
    By filterButton = By.cssSelector("form[action=\"https://askomdch.com/store/\"] button");
    By productName = By.cssSelector("[class=\"astra-shop-summary-wrap\"] h2");
    public StorePage(WebDriver driver){
        this.driver = driver;
    }
    public String getHeadingText(){
        return driver.findElement(Heading).getText();
    }
    public void enterInputsInSearch(String input){
        driver.findElement(search).sendKeys(input, Keys.ENTER);
    }
    public String verfySearch(String input){
//        System.out.println(driver.findElement(By.cssSelector("div[class=\"astra-shop-summary-wrap\"] h2")).getText());
        if(!driver.findElements(By.cssSelector("div[class=\"astra-shop-summary-wrap\"] h2")).isEmpty()){
            if(driver.findElement(By.cssSelector("div[class=\"astra-shop-summary-wrap\"] h2")).getText().toLowerCase().contains(input.toLowerCase())) return "true";
        }
        if(!driver.findElements(By.cssSelector("div[class=\"summary entry-summary\"] h1")).isEmpty()){
            if(driver.findElement(By.cssSelector("div[class=\"summary entry-summary\"] h1")).getText().toLowerCase().contains(input.toLowerCase())) return "true";
        }

        return driver.findElement(By.cssSelector("p[class=\"woocommerce-info woocommerce-no-products-found\"]")).getText();

    }
    public void filterByCategories(String option){
        driver.findElement(category).sendKeys(Keys.ENTER);
        Select dropDownElement = new Select(driver.findElement(category));
        dropDownElement.selectByVisibleText(option);
//        dropDownElement.deselectAll();
    }
    public String verfyingCategoriesName(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("[class=\"woocommerce-products-header__title page-title\"]"))));
        By heading = By.cssSelector("[class=\"woocommerce-products-header__title page-title\"]");
        return driver.findElement(heading).getText();
    }
    public int verfyingCategoryNumber(){
        return driver.findElements(By.cssSelector("[class=\"astra-shop-summary-wrap\"]")).size();
    }
    public void sortProducts(String option){
        Select dropDownElement = new Select(driver.findElement(sort));
        dropDownElement.selectByVisibleText(option);
        driver.findElement(sort).sendKeys(Keys.ENTER);
    }
    public void filterByPriceRange(String min, String max){
        while(!driver.findElement(minPrice).getText().equals(min)){
            driver.findElements(inputSpan).get(0).sendKeys(Keys.ARROW_RIGHT);
        }
        while(!driver.findElement(maxPrice).getText().equals(max)){
            driver.findElements(inputSpan).get(1).sendKeys(Keys.ARROW_LEFT);
        }
        driver.findElement(filterButton).click();
    }
    public String verfyingFilterByPrice(String min, String max){
        if(!driver.findElements(By.cssSelector("[class=\"price\"] bdi")).isEmpty()){
            List<WebElement> allPrices = driver.findElements(By.cssSelector("[class=\"price\"] bdi"));
            List<WebElement> deltedPrices = driver.findElements(By.cssSelector("[class=\"price\"] del"));
            List<WebElement> realPrices = allPrices.stream().filter(val-> !deltedPrices.contains(val)).toList();
            if(realPrices.stream().allMatch(val-> (int) Double.parseDouble((val.getText().substring(1))) >= Integer.parseInt(min) && (int) Double.parseDouble((val.getText().substring(1))) <= Integer.parseInt(max))){
                return "true";
            }
            else return "Some prices are out of range";
        }
        return driver.findElement(By.cssSelector("p[class=\"woocommerce-info woocommerce-no-products-found\"]")).getText();
    }
    public String getProductName(int id){
//        System.out.println(driver.findElements(productName).get(id).getText());
        return driver.findElements(productName).get(id).getText();
    }
    public CartPage addProductToCart(int id,int frequency){
        while(frequency > 0){
            By productAddToCartButton = By.cssSelector("[class=\"astra-shop-summary-wrap\"] a[aria-label^=\"Add \"]");
            driver.findElements(productAddToCartButton).get(id).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(productAddToCartButton).get(id)));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"View cart\"]")));
            frequency--;
        }
        driver.findElement(By.cssSelector("a[title=\"View cart\"]")).click();
        return new CartPage(driver);
    }
    public ProductDetail clickOnProductName(int id){
        driver.findElements(productName).get(id).click();
        return new ProductDetail(driver);
    }

}
