package com.geektrust.backend.services;


import java.util.Optional;
import com.geektrust.backend.entities.Location;

public interface ILocationService {
    public Location create(double latitude, double longitude);
    public Optional<Location> getLocationById(String locationId);
    public double getDistanceInKms(Location driverLocation, Location riderLocation); 
}