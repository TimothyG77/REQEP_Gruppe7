package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ManageChargingPointStatusTest {

    private Map<String, String> chargingPoints;
    private String selectedChargingPoint;
    private String selectedStatus;
    private boolean ownerLoggedIn;


    @Given("the owner has an existing charging point at {string}")
    public void ownerHasExistingChargingPoint(String location) {

        chargingPoints.put(location, "Available");
        System.out.println("Charging point added at location: " + location);
    }

    @When("the owner selects {string}")
    public void ownerSelectsSetStatus(String action) {

        Assertions.assertEquals("Set Status", action);
        System.out.println("Owner selected action: " + action);
    }

    @And("the owner sets the status to {string}")
    public void ownerSetsTheStatus(String status) {
        selectedStatus = status;
        System.out.println("Owner sets status to: " + status);
    }

    @Then("the system updates the status of {string} to {string}")
    public void systemUpdatesChargingPointStatus(String location, String updatedStatus) {
        Assertions.assertTrue(chargingPoints.containsKey(location),
                "Charging point does not exist: " + location);
        chargingPoints.put(location, updatedStatus);


        Assertions.assertEquals("Status update failed for location: " +
                location, updatedStatus, chargingPoints.get(location));
        System.out.println("System updated the status of charging point at " + location + " to " + updatedStatus);
    }
}
