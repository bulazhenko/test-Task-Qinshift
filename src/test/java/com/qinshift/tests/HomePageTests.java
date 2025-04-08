package com.qinshift.tests;

import com.qinshift.pages.HomePage;
import com.qinshift.pages.LoginPage;
import com.qinshift.pages.ProductPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Home Page")
public class HomePageTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    private static final int DEFAULT_PRODUCT_INDEX_1 = 3;
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

    @Test(description = "Verify that the filter is applied correctly on the Home Page.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter")
    public void testApplyFilter() {
        homePage.applyFilter(prop.getProperty("sort_filter"));
        Assert.assertEquals(homePage.getAppliedFilter(), prop.getProperty("sort_filter_text"), "Filter was not applied correctly.");
    }

    @Test(description = "Verify that clicking on a product name navigates to the correct product page.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Page Navigation")
    public void testGoToProductPage() {
        homePage.clickOnProductName(prop.getProperty("product_name"));
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductName(), prop.getProperty("product_name"), "Product page did not open with the correct product name.");
    }

    @Test(description = "Verify that a product can be added to the cart from the Home Page.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add to Cart")
    public void testAddToCart() {
        Assert.assertTrue(homePage.getAddToCart(DEFAULT_PRODUCT_INDEX_1).isDisplayed(), "Add to cart button should be displayed.");
        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_1);
        Assert.assertTrue(homePage.getRemoveFromCart(DEFAULT_PRODUCT_INDEX_1).isDisplayed(), "Remove from cart button should be displayed after adding to cart.");
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_1, "Cart count should be 1 after adding one product.");

        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_3);
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_2, "Cart count should be 2 after adding two products.");
        homePage.resetAppState();
    }

    @Test(description = "Verify that a product can be removed from the cart from the Home Page.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Remove from Cart")
    public void testRemoveFromCart() {
        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_1);
        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_2);
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_2, "Cart count should be 2 before removing a product.");
        homePage.clickOnRemoveFromCart(DEFAULT_PRODUCT_INDEX_2);
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_1, "Cart count should be 1 after removing one product.");
        homePage.resetAppState();
    }

    @Test(description = "Verify that reset app state functionality works correctly.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Reset App State")
    public void testResetAppState() {
        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_1);
        homePage.clickOnAddToCart(DEFAULT_PRODUCT_INDEX_2);
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_2,
                "Cart count should be 2 items before reset");

        homePage.resetAppState();
        Assert.assertEquals(homePage.getCartCount(), 0,
                "Cart count should be zero after reset");
    }
}

