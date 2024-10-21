package org.example;

import java.util.ArrayList;
import java.util.List;

public class Owner extends AccountManagement {
    private List<ChargingStation> stations = new ArrayList<>();

    public Owner(String email, String phonenumber, String password, String paymentdetails) {
        super(email, phonenumber, password, paymentdetails);
    }

    public void setPriceperKWh(ChargingStation station, double setPriceKWH) {
       station.setPricePerKWH(setPriceKWH);
       System.out.println("Price per kWh set to " + setPriceKWH + " for charging station at " + station.getIdLocation());
    }



    public void setPricePerLocation(ChargingStation station, double setPricePerLocation) {
        station.setPricePerChargingLocation(setPricePerLocation);
        System.out.println("Price per charging location set to " + setPricePerLocation +
                " for charging station at " + station.getIdLocation());
    }

    public void adjustPriceperKWh(ChargingStation station, double setPriceKWh) {
        station.setPricePerKWH(setPriceKWh);
    }

    public void adjustPriceperChargingLocation(ChargingStation station, double setPriceChargingLocation) {
        station.setPricePerChargingLocation(setPriceChargingLocation);
    }

    public void addStation(Location location, ChargingStation station) {
        stations.add(station);
    }

    public void removeStation(ChargingStation station) {
        stations.remove(station);
    }

    @Override
    public List<Location> viewAllLocations() {
        return new ArrayList<>();
    }

    @Override
    public List<InvoiceManagement> invoiceInformations() {
        return new ArrayList<>();
    }

    @Override
    public double getBalance() {
        return 0;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails() {
        this.paymentDetails = paymentDetails;
    }


}

