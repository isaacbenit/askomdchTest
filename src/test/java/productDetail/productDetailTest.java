package productDetail;

import Base.BaseTest;
import Pages.ProductDetail;
import Pages.StorePage;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class productDetailTest extends BaseTest {
    @Test
    public void productDetailTest(){
        int id = 0;
        StorePage storePage = homepage.clickOnShopNow();
        String productName = storePage.getProductName(id);
        ProductDetail productDetail = storePage.clickOnProductName(id);
        assertEquals(productName,productDetail.verfyingProductDetail());
    }
    @Test
    public void addToCartOnProductDetailTest(){
        int id = 0;
        StorePage storePage = homepage.clickOnShopNow();
        String productName = storePage.getProductName(id);
        ProductDetail productDetail = storePage.clickOnProductName(id);
        productDetail.verfyingProductDetail();
        String confrimationMessage = productDetail.verfyAddToCartOnProductDetailPage();
        assertEquals(productName,confrimationMessage.substring(confrimationMessage.indexOf("“")+1,confrimationMessage.indexOf("”")));
    }
}
