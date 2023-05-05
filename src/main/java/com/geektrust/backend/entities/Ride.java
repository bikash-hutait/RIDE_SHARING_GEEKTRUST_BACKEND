package com.geektrust.backend.entities;


public class Ride {
    private String rideId;
    private String driverId;
    private int driverIndex;
    private String riderId;
    private boolean isStarted;
    private boolean isStopped;
    private double destinationX;
    private double destinationY;
    private int timeTakenInMin;

    // Constructor
  

    public Ride(String rideId, String driverId, int driverIndex, String riderId, boolean isStarted, boolean isStopped,
            double destinationX, double destinationY, int timeTakenInMin) {
        this.rideId = rideId;
        this.driverId = driverId;
        this.driverIndex = driverIndex;
        this.riderId = riderId;
        this.isStarted = isStarted;
        this.isStopped = isStopped;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.timeTakenInMin = timeTakenInMin;
    }

    public Ride(String string, Location location, Location location2) {
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public int getDriverIndex() {
        return driverIndex;
    }

    public void setDriverIndex(int driverIndex) {
        this.driverIndex = driverIndex;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean isStopped) {
        this.isStopped = isStopped;
    }

    public double getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(double destinationX) {
        this.destinationX = destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(double destinationY) {
        this.destinationY = destinationY;
    }

    public int getTimeTakenInMin() {
        return timeTakenInMin;
    }

    public void setTimeTakenInMin(int timeTakenInMin) {
        this.timeTakenInMin = timeTakenInMin;
    }

    @Override
    public String toString() {
        return "Ride [rideId=" + rideId + ", driverId=" + driverId + ", driverIndex=" + driverIndex + ", riderId="
                + riderId + ", isStarted=" + isStarted + ", isStopped=" + isStopped + ", destinationX=" + destinationX
                + ", destinationY=" + destinationY + ", timeTakenInMin=" + timeTakenInMin + "]";
    }

   
  
      
    
    
}