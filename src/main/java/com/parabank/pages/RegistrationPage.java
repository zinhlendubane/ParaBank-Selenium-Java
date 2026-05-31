package com.parabank.pages;

import com.parabank.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    WebDriver driver;

    @FindBy(xpath = "//a[text()='Register']") private WebElement registerLink;
    @FindBy(xpath = "//h1[text()='Signing up is easy!']") private WebElement registrationHeader;
    @FindBy(id = "customer.firstName") private WebElement firstNameField;
    @FindBy(id = "customer.lastName") private WebElement lastNameField;
    @FindBy(id = "customer.address.street") private WebElement addressField;
    @FindBy(id = "customer.address.city") private WebElement cityField;
    @FindBy(id = "customer.address.state") private WebElement stateField;
    @FindBy(id = "customer.address.zipCode") private WebElement zipCodeField;
    @FindBy(id = "customer.phoneNumber") private WebElement phoneNumberField;
    @FindBy(id = "customer.ssn") private WebElement ssnField;
    @FindBy(id = "customer.username") private WebElement usernameField;
    @FindBy(id = "customer.password") private WebElement passwordField;
    @FindBy(id = "repeatedPassword") private WebElement repeatedPasswordField;
    @FindBy(xpath = "//input[@value='Register']") private WebElement registerButton;
  //  @FindBy(xpath = "//div[@id='rightPanel']//h1") private WebElement welcomeMessage;
    @FindBy(id = "customer.username.errors") private WebElement usernameError;
    @FindBy(id = "repeatedPassword.errors") private WebElement passwordError;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickRegisterLink() {
        this.registerLink.click();
    }

    public RegistrationPage goTo() {
        driver.get(ConfigReader.getBaseUrl() + "/register.htm");
        return this;
    }

    public boolean isRegistrationHeaderDisplayed() {
        return this.registrationHeader.isDisplayed();
    }

    public RegistrationPage enterFirstName(String firstName) {
         this.firstNameField.sendKeys(firstName);
         return this;
    }

    public RegistrationPage enterLastName(String lastName) {
        this.lastNameField.sendKeys(lastName);
        return this;
    }

    public RegistrationPage enterAddress(String address) {
        this.addressField.sendKeys(address);
        return this;
    }

    public RegistrationPage enterCity(String city) {
        this.cityField.sendKeys(city);
        return this;
    }

    public RegistrationPage enterState(String state) {
        this.stateField.sendKeys(state);
        return this;
    }

    public RegistrationPage enterZipCode(String zipCode) {
        this.zipCodeField.sendKeys(zipCode);
        return this;
    }

    public RegistrationPage enterPhoneNumber(String phoneNumber) {
        this.phoneNumberField.sendKeys(phoneNumber);
        return this;
    }

    public RegistrationPage enterSSN(String ssn) {
        this.ssnField.sendKeys(ssn);
        return this;
    }

    public RegistrationPage enterUsername(String username) {
        this.usernameField.sendKeys(username);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        this.passwordField.sendKeys(password);
        return this;
    }

    public RegistrationPage enterRepeatedPassword(String repeatedPassword) {
        this.repeatedPasswordField.sendKeys(repeatedPassword);
        return this;
    }

    public RegistrationPage clickRegister() {
        this.registerButton.click();
        return this;
    }

    public boolean isRegistrationSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id='rightPanel']//h1")
        ));
        return driver.findElement(By.xpath("//div[@id='rightPanel']//h1")).isDisplayed();

    }

    public String getUsernameError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(usernameError));
        return usernameError.getText();
    }

    public String getPasswordError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(passwordError));
        return passwordError.getText();
    }



}
