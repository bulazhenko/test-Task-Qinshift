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

    private static final int EXPECTED_CART_COUNT_1 = 1;
    private static final int EXPECTED_CART_COUNT_0 = 0;

    @BeforeMethod
    public void setup() {
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"), false);
        homePage = new HomePage(driver);
        homePage.clickOnProductName(prop.getProperty("product_name"));
        productPage = new ProductPage(driver);
    }

    @Test(description = "Verify that a product can be added to the cart from the product details page.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Add to Cart")
    public void testAddToCartProductPage() {
        productPage.getAddToCartButton().click();
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_1, "Cart count should be 1 after adding product from product page.");
        productPage.resetAppState();
    }

    @Test(description = "Verify that a product can be removed from the cart from the product details page.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Remove from Cart")
    public void testRemoveFromCartProductPage() {
        productPage.getAddToCartButton().click();
        Assert.assertEquals(homePage.getCartCount(), EXPECTED_CART_COUNT_1, "Cart count should be 1 after adding product.");
        productPage.getRemoveFromCartButton().click();
        Assert.assertEquals(productPage.getCartCountList().size(), EXPECTED_CART_COUNT_0, "Cart count should be 0 after removing product from product page.");
    }
}
