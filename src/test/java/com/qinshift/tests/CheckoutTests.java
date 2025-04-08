package com.qinshift.tests;

import com.qinshift.entities.ErrorMessageHelper;
import com.qinshift.pages.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

@Feature("Checkout")
public class CheckoutTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private YourCartPage yourCartPage;
    private CheckoutPage checkoutPage;
    private ProductPage productPage;
    private ErrorMessageHelper errorMessageHelper;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;

    private static final String firstName = "Sonic";
    private static final String lastName = "Blue";
    private static final String postalCode = "123245";

    @BeforeMethod
    public void setup() {
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"), false);

        homePage = new HomePage(driver);
        homePage.clickOnProductName(prop.getProperty("product_name"));

        productPage = new ProductPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddToCartButton())).click();

        productPage.getShoppingCartBucket().click();

        yourCartPage = new YourCartPage(driver);
        yourCartPage.getCheckoutButton().click();

        checkoutPage = new CheckoutPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        productPage.resetAppState();
    }

    @Test(description = "Verify that user can cancel checkout.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Checkout")
    public void testCancelCheckoutProductPage() {
        checkoutPage.getCancelButton().click();
        Assert.assertTrue(yourCartPage.getPageURL().contains("cart.html"), "Your Cart page is presented.");
    }

    @Test(description = "Verify that user can cancel checkout from overview page.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Checkout")
    public void testCancelOverviewCheckoutProductPage() {
        checkoutPage.setUserData(firstName, lastName, postalCode);
        checkoutPage.getContinueButton().click();

        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.getCancelButton().click();
        Assert.assertTrue(homePage.getPageURL().contains("inventory.html"), "Checkout canceled and user redirected to home page");
    }

    @Test(description = "Verify that user can do a valid checkout.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Checkout")
    public void testCheckoutProductPage() {
        checkoutPage.setUserData(firstName, lastName, postalCode);
        checkoutPage.getContinueButton().click();

        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.getFinishButton().click();

        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertEquals(checkoutCompletePage.getOrderCompleted(), "Thank you for your order!");
    }

    @Test(description = "Verify that user can checkout with multiple products.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Checkout")
    public void testCheckoutWithMultipleProducts() {
        homePage.addMultipleProductsToCart(3);
        productPage.getShoppingCartBucket().click();

        yourCartPage.getCheckoutButton().click();
        checkoutPage.setUserData(firstName, lastName, postalCode);
        checkoutPage.getContinueButton().click();

        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.getFinishButton().click();

        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertEquals(checkoutCompletePage.getOrderCompleted(), "Thank you for your order!");
    }

    @Test(description = "Verify that user cannot do checkout with empty firstName field.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Checkout")
    public void testInvalidUserFirstNameCheckoutProductPage() {
        checkoutPage.setUserData("", lastName, postalCode);
        checkoutPage.getContinueButton().click();

        errorMessageHelper = new ErrorMessageHelper(driver);
        Assert.assertTrue(errorMessageHelper.getErrorMessageElement().isDisplayed(), "Error message should be displayed.");
        Assert.assertEquals(errorMessageHelper.getErrorMessageText(), prop.getProperty("invalid_first_name_checkout_error"), "Error message text is correct.");
    }

    @Test(description = "Verify that user cannot do checkout with empty lastName field.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Checkout")
    public void testInvalidUserLastNameCheckoutProductPage() {
        checkoutPage.setUserData(firstName, "", postalCode);
        checkoutPage.getContinueButton().click();

        errorMessageHelper = new ErrorMessageHelper(driver);
        Assert.assertTrue(errorMessageHelper.getErrorMessageElement().isDisplayed(), "Error message should be displayed.");
        Assert.assertEquals(errorMessageHelper.getErrorMessageText(), prop.getProperty("invalid_last_name_checkout_error"), "Error message text is correct.");
    }

    @Test(description = "Verify that user cannot do checkout with empty postalCode field.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Checkout")
    public void testInvalidUserPostalCodeCheckoutProductPage() {
        checkoutPage.setUserData(firstName, lastName, "");
        checkoutPage.getContinueButton().click();

        errorMessageHelper = new ErrorMessageHelper(driver);
        Assert.assertTrue(errorMessageHelper.getErrorMessageElement().isDisplayed(), "Error message should be displayed.");
        Assert.assertEquals(errorMessageHelper.getErrorMessageText(), prop.getProperty("invalid_postal_code_checkout_error"), "Error message text is correct.");
    }

    //TODO - INVESTIGATE This is bug or feature? :D
    @Test(description = "Verify that user can checkout with long input values.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Checkout")
    public void testLongInputFieldsCheckout() {
        String longString = "A".repeat(256); // 256 characters
        checkoutPage.setUserData(longString, longString, longString);
        checkoutPage.getContinueButton().click();

        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.getFinishButton().click();

        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertEquals(checkoutCompletePage.getOrderCompleted(), "Thank you for your order!");
         }
}
