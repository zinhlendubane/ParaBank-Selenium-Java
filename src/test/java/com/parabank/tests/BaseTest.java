package com.parabank.tests;

import com.parabank.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = BrowserFactory.getDriver();
    }

    @AfterClass
    public void tearDown(){
        BrowserFactory.quitDriver();
    }
}
