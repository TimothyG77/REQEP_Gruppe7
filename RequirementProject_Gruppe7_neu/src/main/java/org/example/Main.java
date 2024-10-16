package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        PaymentDetails paymentDetails = new PaymentDetails("1234567890123456", "12/25",
                123, 100.0, "ATM-12345");


        Customer customer = new Customer("customer@example.com", "0123456789",
                "password", "VISA", "CUST001");


        ChargingStation station1 = new ChargingStation("LOC001", TypeOfCharging.DC,
                StationStatus.AVAILABLE, 0.50, 5.0);
        ChargingStation station2 = new ChargingStation("LOC002", TypeOfCharging.AC,
                StationStatus.AVAILABLE, 0.25, 3.0);
        List<ChargingStation> chargingStations = new ArrayList<>();
        chargingStations.add(station1);
        chargingStations.add(station2);

        Location location = new Location("LOC001", "Berlin Central",
                "Berlin, Germany", chargingStations);


        Owner owner = new Owner("owner@example.com", "0987654321",
                "ownerPass", "MASTERCARD");


        ChargingStation station3 = new ChargingStation("LOC003", TypeOfCharging.DC,
                StationStatus.AVAILABLE, 0.60, 6.0);
        owner.addStation(location, station3);


        customer.selectChargingStation(station1);
        customer.startChargingSession();
        station1.startChargingSession();


        station1.stopChargingSession();
        customer.stopChargingSession();


        InvoiceManagement invoice = new InvoiceManagement("INV001", "Berlin Central",
                0.50, 5.0, 1, new Date());
        customer.viewInvoice(invoice);


        owner.setPriceperKWh(station2, 0.30);
        owner.setPricePerLocation(station2, 0.50);

        // Location-Daten
        List<Location> locations = customer.viewAllLocations();
        if (locations != null) {
            for (Location loc : locations) {
                System.out.println("Location ID: " + loc.getIDLocation() + ", Name: " + loc.getName());
            }
        }

        // Invoice-Daten
        List<InvoiceManagement> invoices = owner.invoiceInformations();
        if (invoices != null) {
            for (InvoiceManagement inv : invoices) {
                inv.printInvoicePerKWh();
            }
        }
    }
}
