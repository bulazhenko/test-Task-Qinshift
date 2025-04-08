package com.qinshift.tests;

import com.qinshift.pages.HomePage;
import com.qinshift.pages.LoginPage;
import com.qinshift.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private ProductPage productPage;

    private static final int EXPECTED_CART_COUNT_1 = 1;
    private static final int EXPECTED_CART_COUNT_0 = 0; // Added for clarity

    @BeforeMethod
    public void setup() {
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"), false);
        homePage = new HomePage(driver);
        homePage.clickOnProductName(prop.getProperty("product_name"));
        productPage = new ProductPage(driver);
    }

    @Test(description = "Verify product can be added to cart from product page")
    public void testAddToCartProductPage() {
        productPage.getAddToCartButton().click();
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_1, "Cart count should be 1 after adding product from product page.");
        productPage.resetAppState();
    }

    @Test(description = "Verify product can be removed from cart from product page")
    public void testRemoveFromCartProductPage() {
        productPage.getAddToCartButton().click();
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_1, "Cart count should be 1 after adding product.");
        productPage.getRemoveFromCartButton().click();
        Assert.assertEquals(productPage.getCartCountList().size(), EXPECTED_CART_COUNT_0, "Cart count should be 0 after removing product from product page.");
    }
}
