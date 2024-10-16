package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class CustomerLoginStep {

    private String loginPage;
    private String emailInput;
    private String phoneInput;
    private String passwordInput;
    private boolean isAuthenticated;
    private String welcomeMessage;

    @Given("the customer is on the login page")
    public void customerOnLoginPage() {
        // Simulating the customer opening the login page
        loginPage = "Login Page";
        System.out.println("Customer is on the login page.");
    }

    @When("the customer enters the following login details:")
    public void customerEntersLoginDetails(io.cucumber.datatable.DataTable dataTable) {
        // Extracting the data from the data table provided in the feature file
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String field = row.get("field");
            String value = row.get("value");

            switch (field) {
                case "email":
                    emailInput = value;
                    break;
                case "phone":
                    phoneInput = value;
                    break;
                case "password":
                    passwordInput = value;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
        System.out.println("Customer has entered login details.");
    }

    @And("the customer submits the login form")
    public void customerSubmitsLoginForm() {
        // Simulating form submission
        if ((emailInput != null && emailInput.equals("customer@gmail.com") && passwordInput.equals("Password!123")) ||
                (phoneInput != null && phoneInput.equals("+491234567890") && passwordInput.equals("Password!123"))) {
            isAuthenticated = true;
        } else {
            isAuthenticated = false;
        }
        System.out.println("Customer submitted the login form.");
    }

    @Then("the system authenticates the customer")
    public void systemAuthenticatesCustomer() {
        Assertions.assertTrue(isAuthenticated, "Customer authentication failed.");
        System.out.println("System authenticated the customer successfully.");
    }

    @And("the customer is redirected to their account dashboard")
    public void customerRedirectedToDashboard() {
        // Simulating a redirected successful login
        if (isAuthenticated) {
            System.out.println("Customer is redirected to the account dashboard.");
        } else {
            throw new IllegalStateException("Authentication failed, customer cannot be redirected.");
        }
    }

    @And("the system displays a welcome message {string}")
    public void systemDisplaysWelcomeMessage(String expectedMessage) {
        if (isAuthenticated) {
            welcomeMessage = "Welcome back, Customer!";
        }
        Assertions.assertEquals("The welcome message does not match.", expectedMessage, welcomeMessage);
        System.out.println("System displays the welcome message: " + welcomeMessage);
    }
}
