package com.geektrust.backend.repositories;


import java.util.List;
import java.util.Optional;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Location;

public interface IDriverRepository {    
    public Driver saveDriver(Driver driver);
    public Optional<Driver> getDriverById(String driverId);   
    public List<Driver> getAllDrivers();
    public List<Driver> getAvailableDriversWithinDistance(Location location, double maxDistance);
   
}