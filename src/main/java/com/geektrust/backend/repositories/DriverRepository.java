package com.geektrust.backend.repositories;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Location;

public class DriverRepository implements IDriverRepository {
    private final Map<String,Driver> driverMap;

    public DriverRepository(Map<String, Driver> driverMap) {
        this.driverMap = driverMap;
    }

    public DriverRepository() {
        this.driverMap = new HashMap<>();
    }

    @Override
    public Driver saveDriver(Driver entity) {
       
        if (entity.getId() == null) {
            String id = UUID.randomUUID().toString();  
            Driver driver = new Driver(id, entity.getCurrentLocation());
            driverMap.put(id, driver);
            return driver;         
        }
       
        driverMap.put(entity.getId(),entity);
        return entity;

    }

    

    @Override
    public List<Driver> getAllDrivers() {       
        return driverMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Driver> getDriverById(String driverId) {
        return Optional.ofNullable(driverMap.get(driverId));
    }
    
    @Override
    public List<Driver> getAvailableDriversWithinDistance(Location location, double maxDistance) {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : driverMap.values()) {
            if (driver.getRide() == null && driver.getCurrentLocation() != null) {
                double distance = calculateDistance(driver.getCurrentLocation(), location);
                if (distance <= maxDistance) {
                    driver.setAvailable(true);
                    availableDrivers.add(driver);
                }
            }
        }
        // Sort the available drivers by their distance to the rider's location
        Collections.sort(availableDrivers, (d1, d2) -> {
            double distance1 = calculateDistance(d1.getCurrentLocation(), location);
            double distance2 = calculateDistance(d2.getCurrentLocation(), location);
            return Double.compare(distance1, distance2);
        });
        return availableDrivers;
    }
    
    
     private double calculateDistance(Location location1, Location location2) {
        double lat1 = location1.getLatitude();
        double lon1 = location1.getLongitude();
        double lat2 = location2.getLatitude();
        double lon2 = location2.getLongitude();
    
        double x1 = lon1;
        double y1 = lat1;
        double x2 = lon2;
        double y2 = lat2;
    
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distance;
    }
    
    
}