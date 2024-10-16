package org.example;

public class ChargingStation {
    private String idLocation;
    private TypeOfCharging type;
    private StationStatus status;
    private double pricePerKWH;
    private double pricePerChargingLocation;

    public ChargingStation(String idLocation, TypeOfCharging type, StationStatus status,
                           double pricePerKWH, double pricePerChargingLocation) {
        this.idLocation = idLocation;
        this.type = type;
        this.status = status;
        this.pricePerKWH = pricePerKWH;
        this.pricePerChargingLocation = pricePerChargingLocation;
    }

    public void startChargingSession() {
        if (status == StationStatus.AVAILABLE) {
            status = StationStatus.OCCUPIED;
            System.out.println("Charging session started at station: " + idLocation);
        } else {
            System.out.println("Charging station not available.");
        }
    }

    public void stopChargingSession() {
        if (status == StationStatus.OCCUPIED) {
            status = StationStatus.AVAILABLE;
            System.out.println("Charging session stopped at station: " + idLocation);
        }
    }

    public void checkStatus() {
        System.out.println("The current status of the station is: " + status);
    }


    public void setPricePerKWH(double pricePerKWH) {
        this.pricePerKWH = pricePerKWH;
    }

    public void setPricePerChargingLocation(double pricePerChargingLocation) {
        this.pricePerChargingLocation = pricePerChargingLocation;
    }

    public String getIdLocation() {
        return idLocation;
    }
}

