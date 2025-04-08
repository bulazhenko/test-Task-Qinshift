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

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Properties properties;
    protected final Properties xPathProperties;
    protected final Actions actions;

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
        this.xPathProperties = Configuration.getProperties("xpath"); // Corrected variable name
        this.actions = new Actions(driver);
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void logout() {
        driver.findElement(HAMBURGER_MENU).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON)).click();
    }

    public int getCartCount() {
        String cartCountText = driver.findElement(CART_COUNT).getText();
        try {
            return Integer.parseInt(cartCountText);
        } catch (NumberFormatException e) {
            // Handle the case where the cart count is not a valid number (empty cart)
            return 0; // Or throw an exception, log an error, or return a default value as appropriate
        }
    }

    public void resetAppState() {
        driver.findElement(HAMBURGER_MENU).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(RESET_APP_BUTTON)).click();
        driver.findElement(CLOSE_MENU_BUTTON).click();
    }

    private WebElement getFacebookLinkElement() {
        return driver.findElement(FACEBOOK_LINK);
    }

    private WebElement getTwitterLinkElement() {
        return driver.findElement(TWITTER_LINK);
    }

    private WebElement getLinkedinLinkElement() {
        return driver.findElement(LINKEDIN_LINK);
    }

    public void goToSocialMedia(String name) {
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
                System.out.println("Incorrect Social media platform: " + name);
                return;
        }
        actions.keyDown(Keys.SHIFT).click(linkToClick).keyUp(Keys.SHIFT).perform();
    }

    public boolean verifyPageTitle(String title) {
        return driver.getTitle().equalsIgnoreCase(title);
    }
}
