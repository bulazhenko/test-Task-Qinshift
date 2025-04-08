package com.qinshift.tests;

import com.qinshift.pages.HomePage;
import com.qinshift.pages.LoginPage;
import com.qinshift.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    private static final int DEFAULT_PRODUCT_INDEX_1 = 3; // Use constants for product indexes
    private static final int DEFAULT_PRODUCT_INDEX_2 = 2;
    private static final int DEFAULT_PRODUCT_INDEX_3 = 4;
    private static final int EXPECTED_CART_COUNT_1 = 1;
    private static final int EXPECTED_CART_COUNT_2 = 2;

    @BeforeMethod
    public void setup() {
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"), false);
        homePage = new HomePage(driver);
    }

    @Test(description = "Verify filter is applied successfully")
    public void testApplyFilter() {
        homePage.applyFilter(prop.getProperty("sort_filter"));
        Assert.assertEquals(homePage.getAppliedFilter(), prop.getProperty("sort_filter_text"), "Filter was not applied correctly.");
    }

    @Test(description = "Verify product page opens successfully")
    public void testGoToProductPage() {
        homePage.clickOnProductName(prop.getProperty("product_name"));
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductName(), prop.getProperty("product_name"), "Product page did not open with the correct product name.");
    }

    @Test(description = "Verify product is added to cart successfully")
    public void testAddToCart() {
        Assert.assertTrue(homePage.getAddToCart(DEFAULT_PRODUCT_INDEX_1).isDisplayed(), "Add to cart button should be displayed.");
        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_1);
        Assert.assertTrue(homePage.getRemoveFromCart(DEFAULT_PRODUCT_INDEX_1).isDisplayed(), "Remove from cart button should be displayed after adding to cart.");
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_1, "Cart count should be 1 after adding one product.");

        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_3);
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_2, "Cart count should be 2 after adding two products.");
        homePage.resetAppState(); // Consider if this is the best place for reset.  See note below.
    }

    @Test(description = "Verify product is removed from cart successfully")
    public void testRemoveFromCart() {
        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_1);
        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_2);
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_2, "Cart count should be 2 before removing a product.");
        homePage.clickOnRemoveFromCart(DEFAULT_PRODUCT_INDEX_2);
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_1, "Cart count should be 1 after removing one product.");
        homePage.resetAppState();  // Consider if this is the best place for reset. See note below.
    }
}
