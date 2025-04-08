package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class ProductPage extends BasePage {

    private static final By PRODUCT_NAME_LABEL = By.xpath("//div[@class='inventory_details_name large_size']");
    private static final By ADD_TO_CART_BUTTON = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']");
    private static final By REMOVE_FROM_CART_BUTTON = By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory']");
    private static final By CART_COUNT = By.xpath("//div[@id='shopping_cart_container']/a/span");
    private static final By PRODUCT_PRICE_LABEL = By.className("inventory_details_price");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return driver.findElement(PRODUCT_NAME_LABEL).getText();
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(ADD_TO_CART_BUTTON);
    }

    public WebElement getRemoveFromCartButton() {
        return driver.findElement(REMOVE_FROM_CART_BUTTON);
    }

    public String getProductPrice() {
        String priceText = driver.findElement(PRODUCT_PRICE_LABEL).getText();
        return priceText.substring(1); // Remove the currency symbol
    }

    public List<WebElement> getCartCountList() {
        return driver.findElements(CART_COUNT); // Assuming CART_COUNT is defined in BasePage
    }
}
