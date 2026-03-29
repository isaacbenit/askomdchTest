package checkout;

import Base.BaseTest;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.StorePage;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CheckOutPageTest extends BaseTest {
    @Test
    public void checkOut (){
        int productId = 6;
        int frequency = 2;
        StorePage storepage = homepage.clickOnShopNow();
        CartPage cartPage=storepage.addProductToCart(productId,frequency);
        CheckoutPage checkOutPage = cartPage.clickOnCheoutButton();
        checkOutPage.enterFirstName("John");
        checkOutPage.enterLastName("Smith");
        checkOutPage.enterCompanyName("KB21");
        checkOutPage.chooseCountry("Rwanda");
        checkOutPage.enterStreetAddress("KG Ave 12");
        checkOutPage.enterApartment("k345");
        checkOutPage.enterCity("Kigali");
        checkOutPage.enterState("california");
        checkOutPage.enterZipCode("12345");
        checkOutPage.enterPhone("0788233233");
        checkOutPage.enterEmail("isaac@gmail.com");
        checkOutPage.clickOnPlaceOrder();
        assertEquals("Thank you. Your order has been received.", checkOutPage.verificationMessage());
    }
}
