package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ManageChargingPointStatusTest {

    private Map<String, String> chargingPoints;
    private String selectedChargingPoint;
    private String selectedStatus;
    private boolean ownerLoggedIn = false;
    private String errorMessage;


    @Given("the owner has an existing charging point at {string}")
    public void ownerHasExistingChargingPoint(String location) {

        if (chargingPoints == null) {
            chargingPoints = new HashMap<>();  // Initialisiere die Map, falls sie noch nicht initialisiert wurde
        }

        chargingPoints.put(location, "Available");
        System.out.println("Charging point added at location: " + location);
    }

    @Given("the owner is not logged into their account")
    public void ownerIsNotLoggedIn() {
        ownerLoggedIn = false; // Simulating the owner not being logged in
        System.err.println("Owner is not logged into their account.");
    }

    @When("the owner selects {string}")
    public void ownerSelectsSetStatus(String action) {
        if (!ownerLoggedIn) {
            errorMessage = "Owner must be logged in";
            System.out.println("Owner attempted to select action without logging in.");
        } else {
            Assertions.assertEquals("Set Status", action);
            System.out.println("Owner selected action: " + action);
        }
    }

    @And("the owner sets the status to {string}")
    public void ownerSetsTheStatus(String status) {
        if (ownerLoggedIn) {
            selectedStatus = status;
            System.out.println("Owner sets status to: " + status);
        }
    }

    @Then("the system updates the status of {string} to {string}")
    public void systemUpdatesChargingPointStatus(String location, String updatedStatus) {
        if (ownerLoggedIn) {
            Assertions.assertTrue(chargingPoints.containsKey(location),
                    "Charging point does not exist: " + location);
            chargingPoints.put(location, updatedStatus);

            Assertions.assertEquals(updatedStatus, chargingPoints.get(location));
            System.out.println("System updated the status of charging point at " + location + " to " + updatedStatus);
        }
    }

    @Then("the system rejects the action with an error message {string}")
    public void systemRejectsActionWithErrorMessage(String expectedErrorMessage) {
        // Check if the system rejected the action and provided the correct error message
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
        System.err.println("Error message: " + errorMessage);
    }
}
