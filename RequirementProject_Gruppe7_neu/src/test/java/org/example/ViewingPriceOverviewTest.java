package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ViewingPriceOverviewTest {

    private Map<String, Map<String, String>> pricingInformation;
    private Map<String, String> selectedLocationDetails;
    private boolean ownerLoggedIn;
    private boolean overviewPageOpened;

    @Given("the owner is logged into their account")
    public void ownerIsLoggedIn() {
        // Simulation of the owner logging in
        ownerLoggedIn = true;
        pricingInformation = new HashMap<>();
        System.out.println("Owner logged in successfully.");
    }

    @When("the system has the following pricing information:")
    public void systemHasPricingInformation(io.cucumber.datatable.DataTable dataTable) {
        for (Map<String, String > row : dataTable.asMaps(String.class, String.class)) {
            String location = row.get("location");
            Map<String, String> pricingDetails = new HashMap<>();
            pricingDetails.put("pricePerKWh", row.get("pricePerKWh"));
            pricingDetails.put("pricePerMinute", row.get("pricePerMinute"));

            pricingInformation.put(location, pricingDetails);
            System.out.println("Stored pricing details for location: " + location + " -> " + pricingDetails);

        }
        System.out.println("Pricing information stored in the system: " + pricingInformation);
    }


    @Then("the system displays the following price overview:")
    public void systemDisplaysPriceOverview(io.cucumber.datatable.DataTable dataTable) {
        if (overviewPageOpened) {
            for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
                String expectedLocation = row.get("location");
                String expectedPricePerKWh = row.get("pricePerKWh");
                String expectedPricePerMinute = row.get("pricePerMinute");

                //Retrieve actual pricing details
                Map<String, String> actualPricingDetails = pricingInformation.get(expectedLocation);

                Assertions.assertNotNull(actualPricingDetails, "Pricing information for location not found: " + expectedLocation);
                Assertions.assertEquals(expectedPricePerKWh, actualPricingDetails.get("pricePerKWh"),
                        "Mismatch in price per kWh for location: " + expectedLocation);
                Assertions.assertEquals(expectedPricePerMinute, actualPricingDetails.get("pricePerMinute"),
                        "Mismatch in price per minute for location: " + expectedLocation);
            }
            System.out.println("All pricing information matches the expected values.");
        }
    }

    @When("the owner opens the price overview page")
    public void ownerOpensPriceOverviewPage() {
        overviewPageOpened = true;  // Sicherstellen, dass die Übersicht geöffnet ist
        System.out.println("Price overview page opened.");
    }


    @And("the owner selects {string} from the price overview")
    public void ownerSelectsLocationFromOverview(String location) {
        // Simulate selecting a specific location from the overview
        if (overviewPageOpened) {
            selectedLocationDetails = pricingInformation.get(location);
            Assertions.assertNotNull(selectedLocationDetails, "Selected location not found in the system: " + location);
            System.out.println("Owner selected location: " + location);
        } else {
            System.out.println("Overview page not opened.");
            Assertions.fail("Price overview page must be opened before selecting a location.");

        }
    }

    @Then("the system displays the price details for {string}:")
    public void systemDisplaysPriceDetailsForLocation(String location, io.cucumber.datatable.DataTable dataTable) {
        Assertions.assertNotNull(selectedLocationDetails, "No location details selected.");
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String expectedField = row.get("field");
            String expectedValue = row.get("value");

            String actualValue = selectedLocationDetails.getOrDefault(expectedField, "");
            Assertions.assertEquals(expectedValue, actualValue, "Mismatch in pricing detail for field: " + expectedField);

        }
        System.out.println("All displayed price details match the expected values for location: " + location);
    }
}
