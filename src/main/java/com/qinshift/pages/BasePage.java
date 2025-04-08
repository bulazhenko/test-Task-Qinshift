package com.qinshift.pages;

import com.qinshift.utils.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Properties properties;
    protected final Properties xPathProperties;
    protected final Actions actions;

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    private static final By HAMBURGER_MENU = By.id("react-burger-menu-btn");
    private static final By CART_COUNT = By.xpath("//div[@id='shopping_cart_container']/a/span");
    private static final By LOGOUT_BUTTON = By.id("logout_sidebar_link");
    private static final By RESET_APP_BUTTON = By.id("reset_sidebar_link");
    private static final By CLOSE_MENU_BUTTON = By.id("react-burger-cross-btn");
    private static final By FACEBOOK_LINK = By.xpath("//a[contains(text(),'Facebook')]");
    private static final By LINKEDIN_LINK = By.xpath("//a[contains(text(),'LinkedIn')]");
    private static final By TWITTER_LINK = By.xpath("//a[contains(text(),'Twitter')]");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.properties = Configuration.getProperties("SwagLabs");
        this.xPathProperties = Configuration.getProperties("xpath");
        this.actions = new Actions(driver);
    }

    public String getPageURL() {
        logger.info("Getting current page URL");
        String url = driver.getCurrentUrl();
        logger.debug("Current URL: {}", url);
        return url;
    }

    public String getPageTitle() {
        logger.info("Getting current page title");
        String title = driver.getTitle();
        logger.debug("Page title: {}", title);
        return title;
    }

    public String getPageSource() {
        logger.info("Getting page source");
        String source = driver.getPageSource();
        logger.debug("Page source (length): {}", source.length());
        return source;
    }

    public void logout() {
        logger.info("Logging out");
        driver.findElement(HAMBURGER_MENU).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON)).click();
        logger.info("Logged out successfully");
    }

    public int getCartCount() {
        logger.info("Getting cart count");
        try {
            WebElement cartCountElement = driver.findElement(CART_COUNT);
            String cartCountText = cartCountElement.getText();
            if (cartCountText.isEmpty()) {
                logger.warn("Cart count text is null or empty. Returning 0.");
                return 0;
            }
            int count = Integer.parseInt(cartCountText);
            logger.debug("Cart count: {}", count);
            return count;
        } catch (NumberFormatException e) {
            logger.warn("Cart count is not a valid number. Returning 0.", e);
            return 0;
        } catch (Exception e) {
            logger.warn("Error getting cart count. Returning 0.", e);
            return 0;
        }
    }

    public void resetAppState() {
        logger.info("Resetting application state");
        driver.findElement(HAMBURGER_MENU).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(RESET_APP_BUTTON)).click();
        driver.findElement(CLOSE_MENU_BUTTON).click();
        logger.info("Application state reset successfully");
    }

    private WebElement getFacebookLinkElement() {
        logger.info("Getting Facebook link element");
        WebElement facebookLink =  driver.findElement(FACEBOOK_LINK);
        return facebookLink;
    }

    private WebElement getTwitterLinkElement() {
        logger.info("Getting Twitter link element");
        WebElement twitterLink = driver.findElement(TWITTER_LINK);
        return twitterLink;
    }

    private WebElement getLinkedinLinkElement() {
        logger.info("Getting LinkedIn link element");
        WebElement linkedInLink = driver.findElement(LINKEDIN_LINK);
        return linkedInLink;
    }

    public void goToSocialMedia(String name) {
        logger.info("Navigating to social media: {}", name);
        WebElement linkToClick = null;
        switch (name.toLowerCase()) {
            case "twitter":
                linkToClick = getTwitterLinkElement();
                break;
            case "facebook":
                linkToClick = getFacebookLinkElement();
                break;
            case "linkedin":
                linkToClick = getLinkedinLinkElement();
                break;
            default:
                logger.warn("Incorrect Social media platform: {}", name);
                System.out.println("Incorrect Social media platform: " + name); // Keep for backwards compatibility
                return;
        }
        actions.keyDown(Keys.SHIFT).click(linkToClick).keyUp(Keys.SHIFT).perform();
        logger.info("Navigated to {} successfully", name);
    }

    public boolean verifyPageTitle(String title) {
        logger.info("Verifying page title: {}", title);
        boolean isTitleCorrect = driver.getTitle().equalsIgnoreCase(title);
        logger.debug("Page title verification result: {}", isTitleCorrect);
        return isTitleCorrect;
    }
}

