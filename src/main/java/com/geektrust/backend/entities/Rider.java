package com.geektrust.backend.entities;


public class Rider {

    private final String id;
    private Location currentLocation;
    private Ride ride;
    
    public Rider(String id, Location currentLocation) {
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

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    @Override
    public String toString() {
        return "Rider [id=" + id + ", currentLocation=" + currentLocation + ", ride=" + ride + "]";
    }

      
    
    
}