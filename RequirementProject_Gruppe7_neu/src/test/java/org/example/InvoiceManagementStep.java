package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class InvoiceManagementStep {

    private String ownerStatus;
    private Map<String, Invoice> invoices;
    private Invoice selectedInvoice;
    private String systemMessage;

    public InvoiceManagementStep() {
        invoices = new HashMap<>();
    }

    @Given("the system has the following invoices:")
    public void systemHasFollowingInvoices(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String invoiceNumber = row.get("invoiceNumber");
            String location = row.get("location");
            String chargingPoint = row.get("chargingPoint");
            String chargingMode = row.get("chargingMode");
            String duration = row.get("duration");
            String chargedEnergy = row.get("chargedEnergy");
            String price = row.get("price");

            invoices.put(invoiceNumber, new Invoice(invoiceNumber, location, chargingPoint, chargingMode, duration, chargedEnergy, price));
            System.out.println("Added invoice " + invoiceNumber + " for " + location + ".");
        });
    }

    @Given("the system has no invoices")
    public void systemHasNoInvoices() {
        invoices.clear();  // Ensure no invoices exist in the system
        System.err.println("The system has no invoices.");
    }

    @Given("the owner is logged in")
    public void ownerIsLoggedIn() {
        ownerStatus = "logged_in";  // Setzt den Status auf "logged_in"
        System.out.println("Owner is logged in.");
    }

    @When("the owner in the system navigates to the {string} page")
    public void ownerNavigatesToInvoiceListPage(String page) {
        Assertions.assertEquals("Invoice List", page, "The page must be 'Invoice List'.");
        if (invoices.isEmpty()) {
            systemMessage = "No invoices available";
        }
        System.out.println("Owner navigated to the Invoice List page.");
    }



    @Then("the system displays a message {string}")
    public void systemDisplaysMessage(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, systemMessage);
        System.err.println("System displayed message: " + systemMessage);
    }


    @When("the owner selects invoice number {string}")
    public void ownerSelectsInvoiceNumber(String invoiceNumber) {
        Assertions.assertEquals("logged_in", ownerStatus, "Owner must be logged in to select an invoice.");
        selectedInvoice = invoices.get(invoiceNumber);
        Assertions.assertNotNull(selectedInvoice, "Invoice should exist in the system");
        System.out.println("Owner selected invoice number: " + invoiceNumber);
    }

    @Then("the system displays the details for invoice {string}:")
    public void systemDisplaysDetailsForInvoice(String invoiceNumber, io.cucumber.datatable.DataTable expectedDetails) {
        Assertions.assertNotNull(selectedInvoice, "Invoice must be selected to view details");
        Assertions.assertEquals(invoiceNumber, selectedInvoice.getInvoiceNumber(), "Invoice number must match");

        expectedDetails.asMaps().forEach(expectedRow -> {
            String field = expectedRow.get("field");
            String expectedValue = expectedRow.get("value");
            String actualValue = selectedInvoice.getFieldValue(field);

            Assertions.assertEquals(expectedValue, actualValue, "Field " + field + " must match");
            System.out.println(field + ": " + actualValue);
        });
    }

    @Then("the system displays the following invoice list:")
    public void systemDisplaysFollowingInvoiceList(io.cucumber.datatable.DataTable expectedInvoiceList) {
        expectedInvoiceList.asMaps().forEach(expectedRow -> {
            String invoiceNumber = expectedRow.get("invoiceNumber");
            Invoice invoice = invoices.get(invoiceNumber);
            Assertions.assertNotNull(invoice, "Invoice must exist in the system");

            String location = expectedRow.get("location");
            String chargingPoint = expectedRow.get("chargingPoint");
            String chargingMode = expectedRow.get("chargingMode");
            String duration = expectedRow.get("duration");
            String chargedEnergy = expectedRow.get("chargedEnergy");
            String price = expectedRow.get("price");

            Assertions.assertEquals(location, invoice.getLocation(), "Location must match");
            Assertions.assertEquals(chargingPoint, invoice.getChargingPoint(), "Charging Point must match");
            Assertions.assertEquals(chargingMode, invoice.getChargingMode(), "Charging Mode must match");
            Assertions.assertEquals(duration, invoice.getDuration(), "Duration must match");
            Assertions.assertEquals(chargedEnergy, invoice.getChargedEnergy(), "Charged Energy must match");
            Assertions.assertEquals(price, invoice.getPrice(), "Price must match");

            System.out.println("Invoice " + invoiceNumber + ": Location - " + location + ", Charging Point - " + chargingPoint +
                    ", Charging Mode - " + chargingMode + ", Duration - " + duration + ", Charged Energy - " + chargedEnergy +
                    ", Price - " + price);
        });
    }


    private class Invoice {
        private String invoiceNumber;
        private String location;
        private String chargingPoint;
        private String chargingMode;
        private String duration;
        private String chargedEnergy;
        private String price;

        public Invoice(String invoiceNumber, String location, String chargingPoint,
                       String chargingMode, String duration, String chargedEnergy, String price) {
            this.invoiceNumber = invoiceNumber;
            this.location = location;
            this.chargingPoint = chargingPoint;
            this.chargingMode = chargingMode;
            this.duration = duration;
            this.chargedEnergy = chargedEnergy;
            this.price = price;
        }

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public String getLocation() {
            return location;
        }

        public String getChargingPoint() {
            return chargingPoint;
        }

        public String getChargingMode() {
            return chargingMode;
        }

        public String getDuration() {
            return duration;
        }

        public String getChargedEnergy() {
            return chargedEnergy;
        }

        public String getPrice() {
            return price;
        }

        public String getFieldValue(String field) {
            switch (field) {
                case "location":
                    return location;
                case "chargingPoint":
                    return chargingPoint;
                case "chargingMode":
                    return chargingMode;
                case "duration":
                    return duration;
                case "chargedEnergy":
                    return chargedEnergy;
                case "price":
                    return price;
                default:
                    throw new IllegalArgumentException("Unknown field: " + field);
            }
        }
    }
}
