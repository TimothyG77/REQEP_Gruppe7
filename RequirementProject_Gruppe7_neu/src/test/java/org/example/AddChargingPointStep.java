package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class AddChargingPointStep {

    private String ownerStatus = "LOGGED_IN";
    private Map<String, ChargingPoint> chargingPoints;
    private String errorMessage;


    public AddChargingPointStep() {
        chargingPoints = new HashMap<>();
    }

    @Given("the owner is on the {string} page")
    public void ownerIsOnPage(String pageName) {
        Assertions.assertEquals("Add Charging Point", pageName);
        System.out.println("Owner is on the " + pageName + " page.");
    }

    @When("the owner enters the following details:")
    public void ownerEntersChargingPointDetails(Map<String, String> details) {
        String location = details.get("location");
        String type = details.get("type");
        String power = details.get("power");
        String status = details.get("status");

        // Check if location or power is missing or empty
        if (location == null || location.trim().isEmpty()) {
            errorMessage = "Location is required";
            System.err.println("Error: " + errorMessage);
            return;
        }
        if (power == null || power.trim().isEmpty()) {
            errorMessage = "Power is required! Cannot be empty!";
            System.err.println("Error: " + errorMessage);
            return;
        }

        ChargingPoint chargingPoint = new ChargingPoint(location, type, power, status);
        chargingPoints.put(location, chargingPoint);

        System.out.println("Owner enters the charging point details: " + details);
    }

    @And("the owner submits the form")
    public void ownerSubmitsTheForm() {
        Assertions.assertEquals(ownerStatus, "LOGGED_IN");
        System.out.println("Owner submits the form to add a new charging point.");
    }

    @Then("the system adds the new {string} charging point to {string}")
    public void systemAddsChargingPoint(String type, String location) {
        Assertions.assertTrue(chargingPoints.containsKey(location), "Charging point at location must exist.");
        ChargingPoint chargingPoint = chargingPoints.get(location);
        Assertions.assertEquals(type, chargingPoint.getType());
        System.out.println("System successfully added the new " + type + " charging point to " + location);
    }

    @And("the owner receives a confirmation message {string}")
    public void ownerReceivesConfirmationMessageAfterAdding(String expectedMessage) {
        String lastAddedLocation = null;
        for (String location : chargingPoints.keySet()) {
            lastAddedLocation = location;
        }
        String chargingPointType = chargingPoints.get(lastAddedLocation).getType();
        String actualMessage = chargingPointType + " Charging Point added successfully.";

        Assertions.assertEquals(expectedMessage, actualMessage, "Confirmation message mismatch.");
        System.out.println("Owner received confirmation message: " + actualMessage);
    }

    @Then("the system rejects the submission with an error message {string}")
    public void systemRejectsSubmission(String expectedErrorMessage) {
        Assertions.assertEquals(expectedErrorMessage, errorMessage, "Error message must match expected message");
        System.err.println("Error message: " + errorMessage);
    }

    // Helper class  to represent the details of the charging point
    private class ChargingPoint {
        private String location;
        private String type;
        private String power;
        private String status;

        public ChargingPoint(String location, String type, String power, String status) {
            this.location = location;
            this.type = type;
            this.power = power;
            this.status = status;
        }

        public String getLocation() {
            return location;
        }

        public String getType() {
            return type;
        }

        public String getPower() {
            return power;
        }

        public String getStatus() {
            return status;
        }
    }
}
