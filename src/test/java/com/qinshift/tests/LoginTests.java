package com.qinshift.tests;

import com.qinshift.entities.ErrorMessageHelper;
import com.qinshift.pages.HomePage;
import com.qinshift.pages.LoginPage;
import com.qinshift.tests.baseTest.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Feature("Login")
public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private ErrorMessageHelper errorMessageHelper;
    private static final String PRODUCTS_PAGE_TITLE = "Products";
    private static final boolean PRESS_ENTER_KEY = true;

    @DataProvider(name = "loginCredentials")
    public Object[][] loginCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    @BeforeMethod
    public void setup() {
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
    }

    private void assertSuccessfulLogin() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getPageHeadingLazy(), PRODUCTS_PAGE_TITLE, "User should be logged in and on the Products page.");
    }

    private void assertLockedOutUser(String username) {
        if (username.equalsIgnoreCase("locked_out_user")) {
            errorMessageHelper = new ErrorMessageHelper(driver);
            Assert.assertEquals(errorMessageHelper.getErrorMessageText(), prop.getProperty("locked_out_user_error"), "Locked out user error message is incorrect.");
        }
    }

    @Test(dataProvider = "loginCredentials", description = "Verify that a user can log in with valid credentials.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Successful Login")
    public void testLogin(String username, String password) {
        loginPage.doLogIn(username, password);
        assertLockedOutUser(username);
        if (!username.equalsIgnoreCase("locked_out_user")) {
            assertSuccessfulLogin();
        }
    }

    @Test(dataProvider = "loginCredentials", description = "Verify that a user can log in by pressing Enter key.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with Enter Key")
    public void testLoginWithEnter(String username, String password) {
        loginPage.doLogInEnterKey(username, password, PRESS_ENTER_KEY);
        assertLockedOutUser(username);
        if (!username.equalsIgnoreCase("locked_out_user")) {
            assertSuccessfulLogin();
        }
    }

    @Test(description = "Verify that a logged-in user can log out successfully.", priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Story("Logout")
    public void testLogout() {
        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
        loginPage.logout();
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("url"), "User should be redirected to the login page after logout.");
    }

    @Test(description = "Verify that the user cannot log in with an invalid username.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Failed Login")
    public void testLoginWithInvalidUsername() {
        loginPage.doLogIn(prop.getProperty("invalid_username"), prop.getProperty("password"));
        assertInvalidLoginError();
    }

    @Test(description = "Verify hat the user cannot log in with an invalid password.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Failed Login")
    public void testLoginWithInvalidPassword() {
        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("invalid_password"));
        assertInvalidLoginError();
    }

    private void assertInvalidLoginError() {
        errorMessageHelper = new ErrorMessageHelper(driver);
        Assert.assertTrue(Boolean.parseBoolean(String.valueOf(errorMessageHelper.getErrorMessageElement().isDisplayed())), "Error message should be displayed.");
        Assert.assertEquals(errorMessageHelper.getErrorMessageText(), prop.getProperty("invalid_login_error"), "Error message text is incorrect.");
    }
}
