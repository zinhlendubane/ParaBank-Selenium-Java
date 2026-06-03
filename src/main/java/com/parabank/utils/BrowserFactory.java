package com.parabank.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            String browser = ConfigReader.getBrowser();

            switch (browser.toLowerCase()) {
                case "chrome" -> driver = createChrome(headless);
                case "firefox" -> driver = createFirefox(headless);
                case "edge" -> driver = createEdge(headless);
                default -> throw new IllegalArgumentException(
                        "Browser not supported: " + browser
                );
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static WebDriver createChrome(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-debugging-port=9222");

        }
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefox(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdge(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        return new EdgeDriver(options);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}