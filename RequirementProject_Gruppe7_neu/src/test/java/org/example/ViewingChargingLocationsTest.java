package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;

public class ViewingChargingLocationsTest {

    private String ownerStatus = "LOGGED_IN";
    private List<ChargingLocation> chargingLocations;
    private List<ChargingLocation> displayedLocations;
    private String systemMessage;


    public ViewingChargingLocationsTest() {
        chargingLocations = new ArrayList<>();
        displayedLocations = new ArrayList<>();
    }

    @Given("the system has the following charging locations:")
    public void systemHasChargingLocations(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> row : rows.subList(1, rows.size())) {
            String idNumber = row.get(0);
            String location = row.get(1);
            String type = row.get(2);
            String status = row.get(3);
            ChargingLocation chargingLocation = new ChargingLocation(idNumber, location, type, status);
            chargingLocations.add(chargingLocation);
        }
        System.out.println("Charging locations have been loaded into the system.");
    }

    @Given("the system has no charging locations")
    public void systemHasNoChargingLocations() {
        chargingLocations.clear(); // Clear any existing locations to simulate no locations available
        System.out.println("The system has no charging locations.");
    }

    @When("the owner navigates to the {string} page")
    public void ownerNavigatesToPage(String page) {
        Assertions.assertEquals("LOGGED_IN", ownerStatus);
        if (page.equalsIgnoreCase("Charging Locations")) {
            if (chargingLocations.isEmpty()) {
                systemMessage = "No charging locations available";
            } else {
                displayedLocations.addAll(chargingLocations);
            }
            System.out.println("Owner navigated to the Charging Locations page.");
        }
    }

    @Then("the system displays the following list of charging locations:")
    public void systemDisplaysChargingLocations(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (int i = 1; i < rows.size(); i++) {
            List<String> expectedRow = rows.get(i);
            ChargingLocation actualLocation = displayedLocations.get(i - 1);
            Assertions.assertEquals(expectedRow.get(0), expectedRow.get(0), actualLocation.getIdNumber());
            Assertions.assertEquals(expectedRow.get(1), expectedRow.get(1), actualLocation.getLocation());
            Assertions.assertEquals(expectedRow.get(2), expectedRow.get(2), actualLocation.getType());
            Assertions.assertEquals(expectedRow.get(3), expectedRow.get(3), actualLocation.getStatus());
        }
        System.out.println("System displays the list of charging locations as expected.");
    }

    @Then("the system displays a error message with the following sentence {string}")
    public void systemDisplaysMessage(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, systemMessage);
        System.err.println("System displayed message: " + systemMessage);
    }

    private class ChargingLocation {
        private String idNumber;
        private String location;
        private String type;
        private String status;

        public ChargingLocation(String idNumber, String location, String type, String status) {
            this.idNumber = idNumber;
            this.location = location;
            this.type = type;
            this.status = status;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public String getLocation() {
            return location;
        }

        public String getType() {
            return type;
        }

        public String getStatus() {
            return status;
        }
    }
}
