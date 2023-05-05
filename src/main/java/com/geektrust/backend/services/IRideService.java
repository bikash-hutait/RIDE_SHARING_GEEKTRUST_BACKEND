package com.geektrust.backend.services;


public interface IRideService {
    public String matchRider(String riderId);
    public String startRide(String rideId, int driverIndex, String riderId);
    public String stopRide(String rideId, double destinationX, double destinationY, int timeTaken);
    public String generateBill(String rideId);

}