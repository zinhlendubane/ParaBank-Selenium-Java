package com.parabank.tests;

import com.parabank.pages.LoginPage;
import com.parabank.utils.CsvReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(driver);
        loginPage.goTo();
    }

    @Test
    public void testLoginWithLastRegisteredUser() {
        String[] credentials = CsvReader.getLastRegisteredUser();
        String username = credentials[0];
        String password = credentials[1];

        loginPage
                .enterUsername(username)
                .enterPassword(password)
                .clickLogin();

        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Login failed for user: " + username);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        loginPage
                .enterUsername("invaliduser")
                .enterPassword("wrongpassword")
                .clickLogin();

        Assert.assertFalse(loginPage.getErrorMessage().isEmpty(),
                "Expected an error message but none was displayed");
    }

    @Test
    public void testLoginWithEmptyCredentials() {
        loginPage
                .enterUsername("")
                .enterPassword("")
                .clickLogin();

        Assert.assertEquals(loginPage.getErrorMessage(),
                "Please enter a username and password.");
    }


}