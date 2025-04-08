package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    private static final By PRODUCT_NAME_LABEL = By.xpath("//div[@class='inventory_details_name large_size']");
    private static final By ADD_TO_CART_BUTTON = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']");
    private static final By REMOVE_FROM_CART_BUTTON = By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory']");
    private static final By CART_COUNT = By.xpath("//div[@id='shopping_cart_container']/a/span");
    private static final By PRODUCT_PRICE_LABEL = By.className("inventory_details_price");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        logger.info("Getting product name");
        String productName = driver.findElement(PRODUCT_NAME_LABEL).getText();
        logger.debug("Product name: {}", productName);
        return productName;
    }

    public WebElement getAddToCartButton() {
        logger.info("Getting Add to Cart button");
        WebElement addToCartButtonElement = driver.findElement(ADD_TO_CART_BUTTON);
        return addToCartButtonElement;
    }

    public WebElement getRemoveFromCartButton() {
        logger.info("Getting Remove from Cart button");
        WebElement removeFromCartButtonElement = driver.findElement(REMOVE_FROM_CART_BUTTON);
        return removeFromCartButtonElement;
    }

    public String getProductPrice() {
        logger.info("Getting product price");
        String priceText = driver.findElement(PRODUCT_PRICE_LABEL).getText();
        String price = priceText.substring(1);
        logger.debug("Product price: {}", price);
        return price;
    }

    public List<WebElement> getCartCountList() {
        logger.info("Getting cart count list");
        List<WebElement> cartCountList = driver.findElements(CART_COUNT);
        logger.debug("Cart count list size: {}", cartCountList.size());
        return cartCountList;
    }
}

