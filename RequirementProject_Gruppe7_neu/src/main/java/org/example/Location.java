package org.example;

import java.util.List;

public class Location {
    private String IDLocation;
    private String name;
    private String address;
    private List<ChargingStation> chargingStations;

    public Location(String IDLocation, String name, String address, List<ChargingStation> chargingStations) {
        this.IDLocation = IDLocation;
        this.name = name;
        this.address = address;
        this.chargingStations = chargingStations;
    }


    public String getIDLocation() {
        return IDLocation;
    }

    public void setIDLocation(String IDLocation) {
        this.IDLocation = IDLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ChargingStation> getChargingStations() {
        return chargingStations;
    }

    public void setChargingStations(List<ChargingStation> chargingStations) {
        this.chargingStations = chargingStations;
    }

    public void addChargingStation(ChargingStation station) {
        chargingStations.add(station);
        System.out.println("Charging station added to location: " + name);
    }

    public void removeChargingStation(ChargingStation station) {
        chargingStations.remove(station);
        System.out.println("Charging station removed from location: " + name);
    }
}
