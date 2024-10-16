package org.example;

import java.util.Date;

public class InvoiceManagement {
    private String invoiceNumber;
    private String locationAddress;
    private double pricePerKWh;
    private double pricePerChargingLocation;
    private int chargingPoint;
    private Date durationCharging;

    public InvoiceManagement(String invoiceNumber, String locationAddress, double pricePerKWh,
                             double pricePerChargingLocation, int chargingPoint, Date durationCharging) {
        this.invoiceNumber = invoiceNumber;
        this.locationAddress = locationAddress;
        this.pricePerKWh = pricePerKWh;
        this.pricePerChargingLocation = pricePerChargingLocation;
        this.chargingPoint = chargingPoint;
        this.durationCharging = durationCharging;
    }


    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public double getPricePerKWh() {
        return pricePerKWh;
    }

    public void setPricePerKWh(double pricePerKWh) {
        this.pricePerKWh = pricePerKWh;
    }

    public double getPricePerChargingLocation() {
        return pricePerChargingLocation;
    }

    public void setPricePerChargingLocation(double pricePerChargingLocation) {
        this.pricePerChargingLocation = pricePerChargingLocation;
    }

    public int getChargingPoint() {
        return chargingPoint;
    }

    public void setChargingPoint(int chargingPoint) {
        this.chargingPoint = chargingPoint;
    }

    public Date getDurationCharging() {
        return durationCharging;
    }

    public void setDurationCharging(Date durationCharging) {
        this.durationCharging = durationCharging;
    }

    public void printInvoicePerKWh() {
        System.out.println("Invoice Number: " + invoiceNumber);
        System.out.println("Location Address: " + locationAddress);
        System.out.println("Price per kWh: €" + pricePerKWh);
        System.out.println("Charging Point: " + chargingPoint);
        System.out.println("Duration: " + durationCharging);
    }

    public void printInvoicePerLocation() {
        System.out.println("Invoice Number: " + invoiceNumber);
        System.out.println("Location Address: " + locationAddress);
        System.out.println("Price per Charging Location: €" + pricePerChargingLocation);
        System.out.println("Charging Point: " + chargingPoint);
        System.out.println("Duration: " + durationCharging);
    }
}
