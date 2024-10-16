package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindingChargingLocationTest {

    private List<Map<String, String>> availableChargingLocations;
    private List<Map<String, String>> displayedChargingLocations;
    private Map<String, String> selectedLocation;
    private boolean customerLoggedIn;
    private String selectedChargingType;

    @Given("the customer is logged into their account")
    public void customerIsLoggedIn() {
        // Simulate the customer logging in
        customerLoggedIn = true;
        availableChargingLocations = new ArrayList<>();
        System.out.println("Customer logged in successfully.");
    }

    @And("the system has the following available charging locations:")
    public void systemHasAvailableChargingLocations(io.cucumber.datatable.DataTable dataTable) {
        for (Map<String,String> row : dataTable.asMaps(String.class, String.class)) {
            availableChargingLocations.add(row);
        }
        System.out.println("Available charging locations in the system: " + availableChargingLocations);
    }

    @When("the customer searches for charging locations near {string}")
    public void customerSearchesForChargingLocations(String searchLocation) {
        displayedChargingLocations = new ArrayList<>();
        for (Map<String, String> location : availableChargingLocations) {
            if (location.get("location").contains(searchLocation) && location.get("status").equals("Available")) {
                displayedChargingLocations.add(location);
            }
        }
        System.out.println("Charging locations displayed after search: " + displayedChargingLocations);
    }

    @Then("the system displays the following available charging locations:")
    public void systemDisplaysAvailableChargingLocations(io.cucumber.datatable.DataTable dataTable) {
        // Verify that displayed locations match expected values from the DataTable
        List<Map<String, String>> expectedLocations = dataTable.asMaps(String.class, String.class);

        Assertions.assertEquals(expectedLocations, displayedChargingLocations,
                "Displayed charging locations do not match expected locations.");
        System.out.println("Displayed charging locations match the expected values.");
    }

    @Given("the customer is on the charging location selection page")
    public void customerIsOnChargingLocationSelectionPage() {
        Assertions.assertTrue(customerLoggedIn, "Customer must be logged in to navigate to the selection page.");
        System.out.println("Customer is on the charging location selection page.");
    }

    @And("the following charging locations are displayed:")
    public void followingChargingLocationsAreDisplayed(io.cucumber.datatable.DataTable dataTable) {
        displayedChargingLocations = dataTable.asMaps(String.class, String.class);
        System.out.println("Charging locations displayed on the selection page: " + displayedChargingLocations);
    }

    @When("the customer selects the charging location {string}")
    public void customerSelectsChargingLocation(String locationName) {
        for (Map<String, String> location : displayedChargingLocations) {
            if (location.get("location").equals(locationName) && location.get("status").equals("Available")) {
                selectedLocation = location;
                break;
            }
        }
        //Assertions.assertNotNull("Charging location not found or is not available: " + locationName, selectedLocation);
        System.out.println("Customer selected charging location: " + locationName);
    }

    @Then("the system confirms the selection of {string}")
    public void systemConfirmsSelection(String locationName) {
        Assertions.assertEquals("Selected location mismatch.", locationName, selectedLocation.get("location"));
        System.out.println("System confirmed the selection of location: " + locationName);
    }

    @Given("the customer has selected the charging location {string}")
    public void customerHasSelectedChargingLocation(String locationName) {
        customerSelectsChargingLocation(locationName);
    }

    @When("the customer chooses the charging type {string}")
    public void customerChoosesChargingType(String chargingType) {
        selectedChargingType = chargingType;
        System.out.println("Customer chose the charging type: " + chargingType);
    }

    @Then("the system confirms the selection of charging type {string}")
    public void systemConfirmsChargingTypeSelection(String chargingType) {
        Assertions.assertEquals("Charging type selection mismatch.", chargingType, selectedChargingType);
        System.out.println("System confirmed the selection of charging type: " + chargingType);
    }

    @And("the customer receives a message {string}")
    public void customerReceivesMessage(String expectedMessage) {
        String actualMessage = "You have selected " + selectedChargingType + " charging at " + selectedLocation.get("location") + ".";
        Assertions.assertEquals("Confirmation message mismatch.", expectedMessage, actualMessage);
        System.out.println("Customer received the message: " + actualMessage);
    }
}
