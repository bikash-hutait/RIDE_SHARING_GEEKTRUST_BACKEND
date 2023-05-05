package com.geektrust.backend.entities;


public class Location {
    private final String locationId;
    private double latitude;
    private double longitude;
    
    public Location(String locationId, double latitude, double longitude) {
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(double latitude, double longitude) {
        this.locationId = null;
        this.latitude = latitude;
        this.longitude = longitude;
    }

 

    public String getLocationId() {
        return locationId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCurrentLocation() {
        String loc=Double.toString(latitude)+" "+Double.toString(longitude);
        return loc;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location [locationId=" + locationId + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }
    
   


    
}