package com.qinshift.tests.baseTest;

import com.qinshift.utils.Configuration;
import com.qinshift.webDriver.SingletonWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties prop;
    protected SoftAssert softAssert;

    @BeforeClass
    public void setupBeforeTest() {
        this.driver = SingletonWebDriver.getDriver();
        this.prop = Configuration.getProperties("common");
        this.softAssert = new SoftAssert();
    }

    @AfterClass
    public void setupAfterSuite() {
        SingletonWebDriver.quitDriver();
    }
}
