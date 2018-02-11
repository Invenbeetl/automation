package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Demo;

public class ProductListTest extends Fixtures {

    private Demo demo = new Demo(driver);

    @Test
    public void isProductPresentInTheProductList() {
        demo.homePage.clickMenuCategotyLink();
        demo.productCategoryPage.clickProductCategoryLink();
        Assert.assertFalse(demo.productListPage.isProductListEmpty(),"The product list is empty! Test failed!");
    }

    @Test
    public void productListFilterPriceIncludesValidProductTest() {
        demo.homePage.clickMenuCategotyLink();
        demo.productCategoryPage.clickProductCategoryLink();
        demo.productListPage.clickRandomPriceRangeFilter();
        Assert.assertFalse(demo.productListPage.isProductListEmpty(), "Product list is empty!" );
        Assert.assertTrue(demo.productListPage.isProductInTheFilterPriceRange(), "No valid product in the product list after apply price filter");
    }

}
