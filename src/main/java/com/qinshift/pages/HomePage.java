package com.qinshift.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

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
        driver.findElement(FILTER_SELECT).click();
        driver.findElement(By.xpath("//select[@class='product_sort_container']/option[@value='" + value + "']")).click();
    }

    public String getAppliedFilter() {
        return driver.findElement(By.xpath(FILTER_APPLIED_XPATH)).getText();
    }

    public String getPageHeading() {
        return driver.findElement(PAGE_HEADING).getText();
    }

    public String getPageHeadingLazy() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement headingElement = wait.until(ExpectedConditions.presenceOfElementLocated(PAGE_HEADING));
        return headingElement.getText();
    }

    public void clickOnProductName(String productName) {
        String fullProductXpath = PRODUCT_NAME_XPATH_START + productName + PRODUCT_NAME_XPATH_END;
        driver.findElement(By.xpath(fullProductXpath)).click();
    }

    public WebElement getAddToCart(int index) {
        String fullAddToCartXpath = ADD_TO_CART_XPATH_START + index + ADD_TO_CART_XPATH_END;
        return driver.findElement(By.xpath(fullAddToCartXpath));
    }

    public void clickOnAddToCart(int index) {
        getAddToCart(index).click();
    }

    public WebElement getRemoveFromCart(int index) {
        String fullRemoveFromCartXpath = REMOVE_FROM_CART_XPATH_START + index + REMOVE_FROM_CART_XPATH_END;
        return driver.findElement(By.xpath(fullRemoveFromCartXpath));
    }

    public void clickOnRemoveFromCart(int index) {
        getRemoveFromCart(index).click();
    }
}