package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class CustomerRegistrationStep {

    private String registrationPage;
    private String emailInput;
    private String phoneInput;
    private String passwordInput;
    private String paymentMethodInput;
    private String paymentDetailsInput;
    private boolean isRegistered;
    private String confirmationMessage;
    private String errorMessage;

    @Given("the customer is on the registration page")
    public void customerOnRegistrationPage() {
        // Simulate customer opening the registration page
        registrationPage = "Registration Page";
        System.out.println("Customer is on the registration page.");
    }

    @When("the customer enters the following details:")
    public void customerEntersDetails(io.cucumber.datatable.DataTable dataTable) {
        // Extracting data from the data table provided in the feature file
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
                case "paymentMethod":
                    paymentMethodInput = value;
                    break;
                case "paymentDetails":
                    paymentDetailsInput = value;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
        System.out.println("Customer has entered registration details.");
    }

    @And("the customer submits the registration form")
    public void customerSubmitsRegistrationForm() {
        // Validate the email format and other required information
        if (!isValidEmail(emailInput)) {
            isRegistered = false;
            errorMessage = "Invalid email format! Must contain an @";
        } else if (emailInput != null && phoneInput != null && passwordInput != null &&
                paymentMethodInput != null && paymentDetailsInput != null && isValidEmail(emailInput)) {
            isRegistered = true;
            confirmationMessage = "Registration successful!";
        } else {
            isRegistered = false;
            errorMessage = "Payment details are required";
        }
        System.out.println("Customer submitted the registration form.");
    }

    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        // Basic email pattern check: checks for the presence of "@" and a domain.
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }


    @Then("the system creates a new account for the customer")
    public void systemCreatesNewAccount() {
        Assertions.assertTrue(isRegistered, "Account creation failed.");
        System.out.println("System created a new account for the customer.");
    }

    @And("the customer receives a confirmation message {string}")
    public void customerReceivesConfirmationMessage(String expectedMessage) {
        if (isRegistered) {
            confirmationMessage = "Registration successful!";
        }
        Assertions.assertEquals(expectedMessage, confirmationMessage);
        System.out.println("System displays the confirmation message: " + confirmationMessage);
    }

    @Then("the system rejects the registration with an error message {string}")
    public void systemRejectsRegistration(String expectedErrorMessage) {
        // Check if the error message matches the expected message
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
        System.err.println("Error message: " + errorMessage);
    }
}
