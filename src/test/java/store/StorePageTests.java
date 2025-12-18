package store;

import Base.BaseTest;
import Pages.CartPage;
import Pages.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class StorePageTests extends BaseTest {
    @Test
    public void  StorePageTest(){
        StorePage storepage = homepage.clickOnShopNow();
        assertEquals("Store", storepage.getHeadingText());
    }
    @Test
    public void searchProduct(){
        String searchword = "Dark Brown";
        StorePage storepage = homepage.clickOnShopNow();
        storepage.enterInputsInSearch(searchword);
//        assertEquals(storepage.verfySearch(searchword), "true");
        assertEquals(storepage.verfySearch(searchword),"true");
//        assertEquals(storepage.verfySearch(searchword),"No products were found matching your selection.");
    }
    @Test
    public void filterProduct(){
        String category = "Accessories  (3)";
        StorePage storepage = homepage.clickOnShopNow();
        storepage.filterByCategories(category);
        String result = getResult(storepage);
//        System.out.println(category.substring(0, category.indexOf("(")).trim().equals(result));
        assertTrue(category.startsWith("Accessories"));
        String numberOfElements = category.substring(category.indexOf("(")+1,  category.indexOf(")"));
        assertEquals(storepage.verfyingCategoryNumber(),Integer.parseInt(numberOfElements));
    }

    private static String getResult(StorePage storepage) {
        return storepage.verfyingCategoriesName();
    }
//    @Test
//    public void sortProduct(){
//        StorePage storepage = homepage.clickOnShopNow();
//        storepage.sortProducts("Sort by latest");
//    }
    @Test
    public void filterByPrice(){
        String min = "$60";
        String max = "$60";
        StorePage storepage = homepage.clickOnShopNow();
        storepage.filterByPriceRange(min,max);
        assertEquals(storepage.verfyingFilterByPrice(min.substring(min.indexOf("$")),max.substring(max.indexOf("$"))),"true");
        assertEquals(storepage.verfyingFilterByPrice(min.substring(min.indexOf("$")),max.substring(max.indexOf("$"))),"No products were found matching your selection.");
    }



}
