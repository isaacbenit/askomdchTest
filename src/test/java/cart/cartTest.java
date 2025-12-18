package cart;

import Base.BaseTest;
import Pages.CartPage;
import Pages.StorePage;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class cartTest extends BaseTest {
    @Test
    public void addToCart(){
        int productId = 6;
        int frequency = 2;
        StorePage storepage = homepage.clickOnShopNow();
        var expected = storepage.getProductName(productId);
        CartPage cartPage=storepage.addProductToCart(productId,frequency);
        assertEquals(cartPage.getProductName(),expected);
        assertEquals(cartPage.getQuantity(),String.valueOf(frequency));
    }
    @Test
    public void deleteProductTest(){
        int productId = 6;
        int frequency = 2;
        StorePage storepage = homepage.clickOnShopNow();
        CartPage cartPage=storepage.addProductToCart(productId,frequency);
        var productName = cartPage.checkDeletedProduct(0);
        String deletedResult = cartPage.removeProduct(0);
        assertEquals(deletedResult.substring(deletedResult.indexOf("“")+1,deletedResult.indexOf("”")),productName);
        String undoText = cartPage.undoProductDelete();
        assertEquals(undoText.substring(undoText.indexOf("“")+1,undoText.indexOf("”")),productName);
    }
    @Test
    public void checkingProductUpdate(){
        int productId = 6;
        int frequency = 2;
        StorePage storepage = homepage.clickOnShopNow();
        CartPage cartPage=storepage.addProductToCart(productId,frequency);
        assertEquals(cartPage.checkProductQuantityUpdate(0,"3")+"","true");
    }
    @Test
    public void checkOut (){
        int productId = 6;
        int frequency = 2;
        StorePage storepage = homepage.clickOnShopNow();
        CartPage cartPage=storepage.addProductToCart(productId,frequency);
        cartPage.clickOnCheoutButton();
    }
}
