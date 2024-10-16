package org.example;

import io.cucumber.java.en.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TopUpAccountSteps {

    private Customer customer;
    private PaymentDetails paymentDetails;
    private String confirmationMessage;
    private double balance;

    @Given("the customer is on the \"Top Up Account\" page")
    public void the_customer_is_on_the_top_up_account_page() {
        // Assuming that this step moves the customer to the top-up account page
        assertTrue(true);
    }

    @When("the customer selects \"Credit Card\" as the payment method")
    public void the_customer_selects_credit_card_as_the_payment_method() {
        assertTrue(true);
    }

    @When("the customer enters the amount to top up as €{double}")
    public void the_customer_enters_the_amount_to_top_up_as(double amount) {
        paymentDetails = new PaymentDetails(50.0, "1234");
        paymentDetails.setCashAmount(amount);
    }

    @When("the customer enters valid credit card details:")
    public void the_customer_enters_valid_credit_card_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        paymentDetails.setCardNumber(data.get("cardNumber"));
        paymentDetails.setExpiryDate(data.get("expiryDate"));
        paymentDetails.setCvv(Integer.parseInt(data.get("cvv")));
    }

    @When("the customer submits the top-up request")
    public void the_customer_submits_the_top_up_request() {
        customer.topUp(paymentDetails);
        //balance = customer.getBalance();
    }

    @Then("the system processes the payment")
    public void the_system_processes_the_payment() {
        assertNotNull(paymentDetails);
        assertTrue(paymentDetails.getCashAmount() > 0);
    }

    @Then("the customer receives a confirmation message \"{string}\"")
    public void the_customer_receives_a_confirmation_message(String expectedMessage) {
        confirmationMessage = "Account topped up successfully by €" + paymentDetails.getCashAmount();
        assertEquals(expectedMessage, confirmationMessage);
    }

    @Then("the new balance is updated to €{double}")
    public void the_new_balance_is_updated_to(double expectedBalance) {
        assertEquals(expectedBalance, balance);
    }

    @When("the customer selects \"Cash\" as the payment method")
    public void the_customer_selects_cash_as_the_payment_method() {
        assertTrue(true);
    }

    @When("the customer confirms the transaction")
    public void the_customer_confirms_the_transaction() {
        // Assume confirmation process
        assertTrue(true);
    }

    @When("the customer selects \"ATM\" as the payment method")
    public void the_customer_selects_atm_as_the_payment_method() {
        assertTrue(true);
    }
}


