package com.parabank.tests;

import com.parabank.pages.RegistrationPage;
import com.parabank.utils.UsernameGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest{

        private static final String PASSWORD = "Test@12345";
        private RegistrationPage registrationPage;

        @BeforeMethod
        public void setUpPage() {
            registrationPage = new RegistrationPage(driver);
            registrationPage.goTo();
        }

        private RegistrationPage fillCommonFields(String username) {
            return registrationPage
                    .enterFirstName("Brandon")
                    .enterLastName("Baker")
                    .enterAddress("123 Main St")
                    .enterCity("Johannesburg")
                    .enterState("Thembisa")
                    .enterZipCode("1632")
                    .enterPhoneNumber("555-123-4567")
                    .enterSSN("123-45-6789")
                    .enterUsername(username);
        }

        @Test
        public void testSuccessfulRegistration() {
            fillCommonFields(UsernameGenerator.generateUniqueUsername())
                    .enterPassword(PASSWORD)
                    .enterRepeatedPassword(PASSWORD)
                    .clickRegister();

           Assert.assertTrue(registrationPage.isRegistrationSuccessful());
        }

        @Test
        public void testDuplicateUserRegistration() {
            fillCommonFields("qwerty1")
                    .enterPassword(PASSWORD)
                    .enterRepeatedPassword(PASSWORD)
                    .clickRegister();

            Assert.assertEquals(registrationPage.getUsernameError(),
                    "This username already exists.");
        }

        @Test
        public void testPasswordValidation() {
            fillCommonFields(UsernameGenerator.generateUniqueUsername())
                    .enterPassword(PASSWORD)
                    .enterRepeatedPassword("WrongPassword@99")
                    .clickRegister();

            Assert.assertEquals(registrationPage.getPasswordError(),  "Passwords did not match.");

        }
    }