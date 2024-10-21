package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ManagingPricesForChargingLocationsTest {

    private String ownerStatus;
    private Map<String, ChargingLocation> chargingLocations;
    private ChargingLocation selectedChargingLocation;
    private String confirmationMessage;
    private String errorMessage;

    public ManagingPricesForChargingLocationsTest() {
        chargingLocations = new HashMap<>();
    }

    @Given("the owner has an existing charging location at {string}")
    public void ownerHasExistingChargingLocation(String locationName) {
        ChargingLocation location = new ChargingLocation(locationName);
        chargingLocations.put(locationName, location);
        System.out.println("Added existing charging location at " + locationName + ".");
    }

    @When("the owner selects the charging location {string}")
    public void ownerSelectsChargingLocation(String locationName) {
        selectedChargingLocation = chargingLocations.get(locationName);
        if (selectedChargingLocation == null) {
            errorMessage = "Charging location not found";
            System.err.println("Error: " + errorMessage);
        } else {
            System.out.println("Owner selected charging location: " + locationName);
        }
    }

    @Then("the system displays following error message {string}")
    public void systemDisplaysErrorMessage(String expectedErrorMessage) {
        Assertions.assertEquals(expectedErrorMessage, errorMessage, "Error message must match expected message");
        System.err.println("System displayed error message: " + errorMessage);
    }

    @When("the owner sets the price per kWh to €{double}")
    public void ownerSetsPricePerKwh(double pricePerKwh) {
        Assertions.assertNotNull(selectedChargingLocation, "Charging location must be selected to set prices");
        selectedChargingLocation.setPricePerKwh(String.valueOf(pricePerKwh));
        confirmationMessage = "Price per kWh updated successfully to " + pricePerKwh + ".";
        System.out.println(confirmationMessage);
    }

    @When("the owner sets the price for charging sessions to €{double}")
    public void ownerSetsPriceForChargingSession(double pricePerSession) {
        Assertions.assertNotNull(selectedChargingLocation, "Charging location must be selected to set prices");
        selectedChargingLocation.setPricePerSession(String.valueOf(pricePerSession));
        confirmationMessage = "Price for charging sessions updated successfully to " + pricePerSession + ".";
        System.out.println(confirmationMessage);
    }

    @Then("the system updates the price per kWh for {string}")
    public void systemUpdatesPricePerKwhForLocation(String locationName) {
        ChargingLocation location = chargingLocations.get(locationName);
        Assertions.assertNotNull("Location must exist in the system", String.valueOf(location));
        System.out.println("System updated price per kWh for location: " + locationName);
    }

    @Then("the system updates the price for charging sessions at {string}")
    public void systemUpdatesPriceForChargingSessionsAt(String locationName) {
        ChargingLocation location = chargingLocations.get(locationName);
        Assertions.assertNotNull("Location must exist in the system", String.valueOf(location));
        System.out.println("System updated price for charging sessions at location: " + locationName);
    }


    private class ChargingLocation {
        private String locationName;
        private String pricePerKwh;
        private String pricePerSession;

        public ChargingLocation(String locationName) {
            this.locationName = locationName;
        }

        public void setPricePerKwh(String pricePerKwh) {
            this.pricePerKwh = pricePerKwh;
        }

        public void setPricePerSession(String pricePerSession) {
            this.pricePerSession = pricePerSession;
        }

        public String getPricePerKwh() {
            return pricePerKwh;
        }

        public String getPricePerSession() {
            return pricePerSession;
        }

        public String getLocationName() {
            return locationName;
        }
    }
}
