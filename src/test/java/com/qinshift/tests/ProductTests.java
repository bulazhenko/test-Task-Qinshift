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

@Feature("Product Page")
public class ProductTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private ProductPage productPage;

    private static final int CART_COUNT_TWO = 2;
    private static final int CART_COUNT_ONE = 1;
    private static final int CART_COUNT_ZERO = 0;

    @BeforeMethod
    public void setup() {
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"), false);
        homePage = new HomePage(driver);
        homePage.clickOnProductName(prop.getProperty("product_name"));
        productPage = new ProductPage(driver);
    }

    private void addProductToCart() {
        productPage.getAddToCartButton().click();
        Assert.assertEquals(homePage.getCartCount(), CART_COUNT_ONE, "Cart count should be 1 after adding product.");
    }

    private void removeProductFromCart() {
        productPage.getRemoveFromCartButton().click();
        Assert.assertEquals(productPage.getCartCountList().size(), CART_COUNT_ZERO, "Cart count should be 0 after removing product.");
    }

    @Test(description = "Verify that a product can be added to the cart from the product details page.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Add to Cart")
    public void testAddToCartProductPage() {
        addProductToCart();
        productPage.resetAppState();
    }

    @Test(description = "Verify that multiple products can be added to the cart from the product details page.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Add to Cart")
    public void testAddMultipleProductsToCart() {
        productPage.getBackToProductsButton().click();
        homePage.clickOnAddToCart(2);
        homePage.clickOnAddToCart(3);
        Assert.assertEquals(homePage.getCartCount(), CART_COUNT_TWO, "Cart count should be 2 after adding multiple products.");
        productPage.resetAppState();
    }

    @Test(description = "Verify that a product can be removed from the cart from the product details page.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Remove from Cart")
    public void testRemoveFromCartProductPage() {
        addProductToCart();
        removeProductFromCart();
    }
}
