package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HomePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class); // Add logger

    private static final By FILTER_SELECT = By.xpath("//select[@class='product_sort_container']");
    private static final By PAGE_HEADING = By.xpath("//span[contains(text(),'Products')]");
    private static final String PRODUCT_NAME_XPATH_START = "//div[text()='";
    private static final String PRODUCT_NAME_XPATH_END = "']";
    private static final String ADD_TO_CART_XPATH_START = "//div[@class='inventory_list']/div[";
    private static final String ADD_TO_CART_XPATH_END = "]/div[@class='inventory_item_description']/div[@class='pricebar']/button[contains(@id,'add-to-cart')]";
    private static final String REMOVE_FROM_CART_XPATH_START = "//div[@class='inventory_list']/div[";
    private static final String REMOVE_FROM_CART_XPATH_END = "]/div[@class='inventory_item_description']/div[@class='pricebar']/button[contains(@id,'remove')]";
    private static final String FILTER_APPLIED_XPATH = "//div[@class='right_component']/span/span";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void applyFilter(String value) {
        logger.info("Applying filter with value: {}", value);
        driver.findElement(FILTER_SELECT).click();
        driver.findElement(By.xpath("//select[@class='product_sort_container']/option[@value=\'" + value + "\']")).click();
    }

    public String getAppliedFilter() {
        logger.info("Getting applied filter text");
        String filterText = driver.findElement(By.xpath(FILTER_APPLIED_XPATH)).getText();
        logger.debug("Applied filter text: {}", filterText);
        return filterText;
    }

    public String getPageHeading() {
        logger.info("Getting page heading text");
        String headingText = driver.findElement(PAGE_HEADING).getText();
        logger.debug("Page heading text: {}", headingText);
        return headingText;
    }

    public String getPageHeadingLazy() {
        logger.info("Getting page heading text (lazy loading)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement headingElement = wait.until(ExpectedConditions.presenceOfElementLocated(PAGE_HEADING));
        String headingText = headingElement.getText();
        logger.debug("Page heading text: {}", headingText);
        return headingText;
    }

    public void clickOnProductName(String productName) {
        logger.info("Clicking on product name: {}", productName);
        String fullProductXpath = PRODUCT_NAME_XPATH_START + productName + PRODUCT_NAME_XPATH_END;
        driver.findElement(By.xpath(fullProductXpath)).click();
    }

    public WebElement getAddToCart(int index) {
        logger.info("Getting Add to Cart button for index: {}", index);
        String fullAddToCartXpath = ADD_TO_CART_XPATH_START + index + ADD_TO_CART_XPATH_END;
        WebElement addToCartElement = driver.findElement(By.xpath(fullAddToCartXpath));
        return addToCartElement;
    }

    public void clickOnAddToCart(int index) {
        logger.info("Clicking on Add to Cart button for index: {}", index);
        getAddToCart(index).click();
    }

    public WebElement getRemoveFromCart(int index) {
        logger.info("Getting Remove from Cart button for index: {}", index);
        String fullRemoveFromCartXpath = REMOVE_FROM_CART_XPATH_START + index + REMOVE_FROM_CART_XPATH_END;
        WebElement removeFromCartElement = driver.findElement(By.xpath(fullRemoveFromCartXpath));
        return removeFromCartElement;
    }

    public void clickOnRemoveFromCart(int index) {
        logger.info("Clicking on Remove from Cart button for index: {}", index);
        getRemoveFromCart(index).click();
    }

    public void addMultipleProductsToCart(int numberOfProducts) {
        for (int i = 1; i <= numberOfProducts; i++) {
            try {
                logger.info("Adding product {} to cart.", i);
                clickOnAddToCart(i); // Add product i to the cart
            } catch (Exception e) {
                logger.error("Error adding product {} to the cart.", i, e);
            }
        }
    }
}
