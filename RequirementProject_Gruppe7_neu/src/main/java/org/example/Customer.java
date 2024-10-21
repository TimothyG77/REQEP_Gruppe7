package org.example;

import java.util.List;

public class Customer extends AccountManagement {
    private String customerId;

    public Customer(String email, String phoneNumber, String password, String paymentDetails, String customerId) {
        super(email, phoneNumber, password, paymentDetails);
        this.customerId = customerId;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails() {
        this.paymentDetails = paymentDetails;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public void topUp(PaymentDetails details) {
        System.out.println("Account topped up successfully using card number: " + details.getCardNumber());
    }

    public Location findChargingPoint(String location) {
        System.out.println("Finding charging point at location: " + location);
        return null;
    }

    public void startChargingSession() {
        System.out.println("Charging session started for customer: " + customerId);
    }

    public void selectChargingStation(ChargingStation station) {
        System.out.println("Charging station selected: " + station.getIdLocation());
    }

    public void stopChargingSession() {
        System.out.println("Charging session stopped for customer: " + customerId);
    }

    public void viewInvoice(InvoiceManagement invoice) {
        invoice.printInvoicePerKWh();
    }

    @Override
    public List<Location> viewAllLocations() {
        System.out.println("Viewing all locations available for charging.");
        return null;
    }

    @Override
    public List<InvoiceManagement> invoiceInformations() {
        System.out.println("Viewing all invoice informations.");
        return null;
    }

    @Override
    public double getBalance() {
        return 0;
    }
}
