package com.geektrust.backend.services;


import java.util.List;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Location;

public interface IDriverService {
    public Driver create(String driverId, Location currentLocation); 
    public List<Driver> getAvailableDriversWithinDistance(Location riderLocation, double distanceInKms);
    public void updateDriverAvailability(String driverId, boolean isAvailable);
   
}