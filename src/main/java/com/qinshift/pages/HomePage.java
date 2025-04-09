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

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    private static final By FILTER_SELECT = By.xpath("//select[@class='product_sort_container']");
    private static final By PAGE_HEADING = By.xpath("//span[contains(text(),'Products')]");
    private static final By FILTER_APPLIED = By.xpath("//div[@class='right_component']/span/span");

    private static final String PRODUCT_NAME_TEMPLATE = "//div[text()='%s']";
    private static final String ADD_TO_CART_TEMPLATE = "//div[@class='inventory_list']/div[%d]//button[contains(@id,'add-to-cart')]";
    private static final String REMOVE_FROM_CART_TEMPLATE = "//div[@class='inventory_list']/div[%d]//button[contains(@id,'remove')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void applyFilter(String value) {
        logger.info("Applying filter with value: {}", value);
        driver.findElement(FILTER_SELECT).click();
        By filterOption = By.xpath(String.format("//select[@class='product_sort_container']/option[@value='%s']", value));
        driver.findElement(filterOption).click();
    }

    public String getAppliedFilter() {
        logger.info("Getting applied filter text");
        return driver.findElement(FILTER_APPLIED).getText();
    }

    public String getPageHeading() {
        logger.info("Getting page heading text");
        return driver.findElement(PAGE_HEADING).getText();
    }

    public String getPageHeadingLazy() {
        logger.info("Getting page heading text (lazy loading)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_HEADING)).getText();
    }

    public void clickOnProductName(String productName) {
        logger.info("Clicking on product name: {}", productName);
        By productLocator = By.xpath(String.format(PRODUCT_NAME_TEMPLATE, productName));
        driver.findElement(productLocator).click();
    }

    public WebElement getAddToCart(int index) {
        By addToCartLocator = By.xpath(String.format(ADD_TO_CART_TEMPLATE, index));
        return driver.findElement(addToCartLocator);
    }

    public void clickOnAddToCart(int index) {
        logger.info("Clicking on Add to Cart button for index: {}", index);
        getAddToCart(index).click();
    }

    public WebElement getRemoveFromCart(int index) {
        By removeFromCartLocator = By.xpath(String.format(REMOVE_FROM_CART_TEMPLATE, index));
        return driver.findElement(removeFromCartLocator);
    }

    public void clickOnRemoveFromCart(int index) {
        logger.info("Clicking on Remove from Cart button for index: {}", index);
        getRemoveFromCart(index).click();
    }

    public void addMultipleProductsToCart(int numberOfProducts) {
        for (int i = 1; i <= numberOfProducts; i++) {
            try {
                logger.info("Adding product {} to cart.", i);
                clickOnAddToCart(i);
            } catch (Exception e) {
                logger.error("Error adding product {} to the cart.", i, e);
            }
        }
    }
}
