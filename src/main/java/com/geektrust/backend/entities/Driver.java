package com.geektrust.backend.entities;


public class Driver {
    private final String id;
    private Location currentLocation;
    private boolean isAvailable;
    private Ride ride;
    
    public Driver(String id, Location currentLocation) {
        this.id = id;
        this.currentLocation = currentLocation;     
    }

    public String getId() {
        return id;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    @Override
    public String toString() {
        return "Driver [id=" + id + ", currentLocation=" + currentLocation + ", isAvailable=" + isAvailable + ", ride="
                + ride + "]";
    }
   
    
}