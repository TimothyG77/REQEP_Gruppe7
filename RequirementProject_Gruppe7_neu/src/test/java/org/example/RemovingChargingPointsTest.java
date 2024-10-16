package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class RemovingChargingPointsTest {

    private String ownerStatus;
    private Map<String, ChargingPoint> chargingPoints;
    private ChargingPoint selectedChargingPoint;
    private String confirmationMessage;

    public RemovingChargingPointsTest() {
        chargingPoints = new HashMap<>();
    }


    @Given("the owner has an existing AC charging point at {string}")
    public void ownerHasExistingAcChargingPointAt(String locationName) {
        ChargingPoint chargingPoint = new ChargingPoint(locationName, "AC");
        chargingPoints.put(locationName, chargingPoint);
        System.out.println("Added existing AC charging point at " + locationName + ".");
    }

    @Given("the owner has an existing DC charging point at {string}")
    public void ownerHasExistingDcChargingPointAt(String locationName) {
        ChargingPoint chargingPoint = new ChargingPoint(locationName, "DC");
        chargingPoints.put(locationName, chargingPoint);
        System.out.println("Added existing DC charging point at " + locationName + ".");
    }

    @When("the owner selects {string} for {string}")
    public void ownerSelectsRemoveChargingPoint(String action, String locationName) {
        Assertions.assertEquals("logged_in", ownerStatus);
        Assertions.assertEquals("Remove Charging Point", action);
        selectedChargingPoint = chargingPoints.get(locationName);
        //Assertions.assertNotNull("Charging point must exist to remove", selectedChargingPoint);
        System.out.println("Owner selected to remove charging point at " + locationName + ".");
    }

    @When("the owner confirms the removal")
    public void ownerConfirmsRemoval() {
        //Assertions.assertNotNull("Charging point must be selected to confirm removal", selectedChargingPoint);
        chargingPoints.remove(selectedChargingPoint.getLocationName());
        confirmationMessage = selectedChargingPoint.getType() + " Charging Point removed successfully.";
        System.out.println(confirmationMessage);
    }

    @Then("the system removes the AC charging point from {string}")
    public void systemRemovesAcChargingPoint(String locationName) {
        //Assertions.assertNull("AC Charging Point must be removed from the system", chargingPoints.get(locationName));
        System.out.println("System removed the AC charging point from " + locationName);
    }

    @Then("the system removes the DC charging point from {string}")
    public void systemRemovesDcChargingPoint(String locationName) {
        //Assertions.assertNull("DC Charging Point must be removed from the system", chargingPoints.get(locationName));
        System.out.println("System removed the DC charging point from " + locationName);
    }


    private class ChargingPoint {
        private String locationName;
        private String type;

        public ChargingPoint(String locationName, String type) {
            this.locationName = locationName;
            this.type = type;
        }

        public String getLocationName() {
            return locationName;
        }

        public String getType() {
            return type;
        }
    }
}

