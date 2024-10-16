package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ViewingInvoiceTest {

    private Map<String, String> chargingSessionDetails;
    private Map<String, String> displayedInvoiceDetails;
    private boolean sessionCompleted;
    private String invoicePage;

    @Given("the customer has completed a charging session at {string}")
    public void customerHasCompletedChargingSession(String location) {
        // Simulate the customer has completed a charging session
        sessionCompleted = true;
        chargingSessionDetails = new HashMap<>();
        chargingSessionDetails.put("chargingPoint", location);
        System.out.println("Charging session completed at: " + location);
    }

    @And("the charging session details are as follows:")
    public void chargingSessionDetails(io.cucumber.datatable.DataTable dataTable) {
        // Extracting data from the data table provided in the feature file
        for (Map<String, String > row : dataTable.asMaps(String.class, String.class)) {
            String field = row.get("field");
            String value = row.get("value");
            chargingSessionDetails.put(field, value);
        }
        System.out.println("Charging session details recorded: " + chargingSessionDetails);
    }

    @When("the customer navigates to the {string} page")
    public void customerNavigatesToPage(String page) {
        // Simulate navigating to the invoice page
        if (sessionCompleted) {
            invoicePage = page;
            displayedInvoiceDetails = new HashMap<>(chargingSessionDetails); // Assume details are displayed correctly
        }
        System.out.println("Customer navigated to the " + page + " page.");
    }

    @Then("the system displays the following invoice details:")
    public void systemDisplaysInvoiceDetails(io.cucumber.datatable.DataTable dataTable) {
        // Check that all invoice details match the expected values
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String expectedField = row.get("field");
            String expectedValue = row.get("value");

            String actualValue = displayedInvoiceDetails.getOrDefault(expectedField, "");
            Assertions.assertEquals("Invoice detail mismatch for field: " + expectedField, expectedValue, actualValue);
        }
        System.out.println("All invoice details match the expected values.");
    }
}