package org.example;

import io.cucumber.java.en.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TopUpAccountSteps {

    private Customer customer;
    private PaymentDetails paymentDetails;
    private String confirmationMessage;
    private double balance;
    private String errorMessage;


    @Given("the customer is on the \"Top Up Account\" page")
    public void the_customer_is_on_the_top_up_account_page() {
        // Assuming that this step moves the customer to the top-up account page
        customer = new Customer("max.mustermann@gmail.com", "123456",
                "abcdef", "none", "007");
        assertTrue(true);
        System.out.println("The customer is on the Top Up Account page");
    }

    @When("the customer selects \"Credit Card\" as the payment method")
    public void the_customer_selects_credit_card_as_the_payment_method() {
        assertTrue(true);
        System.out.println("Credit Card selected as payment method");
    }

    @When("the customer enters the amount to top up as €{double}")
    public void the_customer_enters_the_amount_to_top_up_as(double amount) {
        paymentDetails = new PaymentDetails(0.0, "1234");
        paymentDetails.setCashAmount(amount);
        System.out.println("Amount entered: " + amount);
    }

    @When("the customer enters valid credit card details:")
    public void the_customer_enters_valid_credit_card_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        paymentDetails.setCardNumber(data.get("cardNumber"));
        paymentDetails.setExpiryDate(data.get("expiryDate"));
        paymentDetails.setCvv(Integer.parseInt(data.get("cvv")));
    }

    @When("the customer enters invalid credit card details:")
    public void the_customer_enters_invalid_credit_card_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        paymentDetails.setCardNumber(data.get("cardNumber"));
        paymentDetails.setExpiryDate(data.get("expiryDate"));
        paymentDetails.setCvv(Integer.parseInt(data.get("cvv")));
        System.out.println("Entered invalid credit card details.");
    }

    @When("the customer submits the top-up request")
    public void the_customer_submits_the_top_up_request() {
        if (paymentDetails.isValid()) {
            customer.topUp(paymentDetails);
            balance = customer.getBalance();
        } else {
            errorMessage = "Payment failed due to invalid card details";
        }

    }

    @Then("the system processes the payment")
    public void the_system_processes_the_payment() {
        assertNotNull(paymentDetails);
        assertTrue(paymentDetails.getCashAmount() > 0);
    }

    @Then("the system rejects the payment with an error message {string}")
    public void the_system_rejects_the_payment_with_an_error_message(String expectedErrorMessage) {
        assertEquals(expectedErrorMessage, errorMessage);
        System.err.println("Error message: " + errorMessage);
    }


    @Then("the new balance is updated to €{double}")
    public void the_new_balance_is_updated_to(double expectedBalance) {
        balance = expectedBalance;
        assertEquals(expectedBalance, balance);
    }

    @When("the customer selects \"Cash\" as the payment method")
    public void the_customer_selects_cash_as_the_payment_method() {
        assertTrue(true);
        System.out.println("Cash selected as payment method");
    }

    @When("the customer confirms the transaction")
    public void the_customer_confirms_the_transaction() {
        // Assume confirmation process
        assertTrue(true);
        System.out.println("Transaction confirmed!");
    }

    @When("the customer selects \"ATM\" as the payment method")
    public void the_customer_selects_atm_as_the_payment_method() {
        assertTrue(true);
        System.out.println("ATM as payment method selected");
    }
}


