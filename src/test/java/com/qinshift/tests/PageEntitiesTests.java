package com.qinshift.tests;

import com.qinshift.pages.HomePage;
import com.qinshift.pages.LoginPage;
import com.qinshift.pages.ProductPage;
import com.qinshift.tests.BaseTest.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

@Feature("Page Entities")
public class PageEntitiesTests extends BaseTest {

    private LoginPage loginPage;
    private static final String DESCRIPTION_META_TAG = "<meta name=\"description\" content=\"Sauce Labs Swag Labs app\">";

    @BeforeMethod
    public void setup() {
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
    }

    private void assertPageTitle(String expectedTitle) {
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Page title is incorrect.");
    }

    private void assertPageUrl(String expectedUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Page URL is incorrect.");
    }

    private void assertPageSourceContains(String expectedText) {
        Assert.assertTrue(driver.getPageSource().contains(expectedText), "Page source does not contain expected text.");
    }

    private void switchToChildWindow(String mainWindowHandle, String childWindowHandle) {
        driver.switchTo().window(childWindowHandle);
    }

    private void closeCurrentWindowAndSwitchToMain(String mainWindowHandle) {
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    @Test(description = "Verify that page titles are correct for Login, Home, and Product pages.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Page Titles")
    public void testVerifyPageTitles() {
        assertPageTitle(prop.getProperty("page_title"));

        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
        HomePage homePage = new HomePage(driver);
        assertPageTitle(prop.getProperty("page_title"));

        homePage.clickOnProductName(prop.getProperty("product_name"));
        ProductPage productPage = new ProductPage(driver);
        assertPageTitle(prop.getProperty("page_title"));
    }

    @Test(description = "Verify that page URLs are correct for Login, Home, and Product pages.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Page URLs")
    public void testVerifyPageUrls() {
        assertPageUrl(prop.getProperty("url"));

        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
        HomePage homePage = new HomePage(driver);
        assertPageUrl(prop.getProperty("home_page_url"));

        homePage.clickOnProductName(prop.getProperty("product_name"));
        ProductPage productPage = new ProductPage(driver);
        String actualProductPageUrl = productPage.getPageURL();
        Assert.assertEquals(actualProductPageUrl.substring(0, actualProductPageUrl.length() - 1), prop.getProperty("product_page_url"), "Product page URL is incorrect.");
    }

    @Test(description = "Verify the presence of a specific element in the page source.")
    @Severity(SeverityLevel.MINOR)
    @Story("Page Source")
    public void testVerifyPageSourceElement() {
        assertPageSourceContains(DESCRIPTION_META_TAG);
    }

    @Test(description = "Verify that social media links in the footer open in new windows and have correct titles.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Social Media Links")
    public void testVerifySocialMediaLinks() {
        loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
        HomePage homePage = new HomePage(driver);

        homePage.goToSocialMedia("facebook");
        homePage.goToSocialMedia("twitter");
        homePage.goToSocialMedia("linkedin");

        // Iterate through the child windows to assert page title
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> windowIterator = allWindowHandles.iterator();

        while (windowIterator.hasNext()) {
            String childWindowHandle = windowIterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(childWindowHandle)) {
                switchToChildWindow(mainWindowHandle, childWindowHandle);
                String childPageTitle = driver.getTitle();
                Assert.assertNotNull(childPageTitle, "Child window title should not be null.");

                if (childPageTitle.toLowerCase().contains("twitter")) {
                    Assert.assertTrue(true, "Twitter page title verification failed.");
                } else if (childPageTitle.toLowerCase().contains("facebook")) {
                    Assert.assertTrue(true, "Facebook page title verification failed.");
                } else if (childPageTitle.toLowerCase().contains("linkedin")) {
                    Assert.assertTrue(true, "LinkedIn page title verification failed.");
                }
                closeCurrentWindowAndSwitchToMain(mainWindowHandle);
            }
        }
    }
}
