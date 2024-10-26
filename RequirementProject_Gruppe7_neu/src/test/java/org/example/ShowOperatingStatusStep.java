package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ShowOperatingStatusStep {

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

    @Given("the owner is logged on the account")
    public void ownerIsLoggedIn() {
        ownerLoggedIn = true;  // Setzt den Besitzer als eingeloggt
        System.out.println("Owner is logged in.");
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
            // Überprüfe, ob der Status gültig ist
            if (!status.equals("Available") && !status.equals("Out of Service") && !status.equals("Occupied")) {
                errorMessage = "Invalid status value";
                System.err.println("Invalid status value set: " + status);
            } else {
                selectedStatus = status;
                errorMessage = null;  // Lösche die Fehlermeldung, wenn der Status gültig ist
                System.out.println("Owner sets status to: " + status);
            }
        } else {
            errorMessage = "Invalid status value";  // Fehlermeldung setzen, falls nicht eingeloggt
        }
    }

    @Then("the system updates the status of {string} to {string}")
    public void systemUpdatesChargingPointStatus(String location, String updatedStatus) {
        if (ownerLoggedIn && errorMessage == null) {
            Assertions.assertTrue(chargingPoints.containsKey(location),
                    "Charging point does not exist: " + location);
            chargingPoints.put(location, updatedStatus);

            Assertions.assertEquals(updatedStatus, chargingPoints.get(location));
            System.out.println("System updated the status of charging point at " + location + " to " + updatedStatus);
        }
    }

    @Then("the system rejects the new status update with an error message {string}")
    public void systemRejectsActionWithErrorMessage(String expectedErrorMessage) {
        // Check if the system rejected the action and provided the correct error message
        Assertions.assertNotNull(errorMessage, "No error message was set.");
        Assertions.assertEquals(expectedErrorMessage, errorMessage,
                "Expected error message does not match the actual error message.");
        System.err.println("Error message: " + errorMessage);
    }
}
