package com.geektrust.backend.services;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Location;
import com.geektrust.backend.repositories.IDriverRepository;

public class DriverService implements IDriverService {

    private final IDriverRepository driverRepository;
    private final ILocationService locationService;

    public DriverService(IDriverRepository driverRepository, ILocationService locationService) {
        this.driverRepository = driverRepository;
        this.locationService = locationService;
    }

    @Override
    public Driver create(String driverId, Location currentLocation) {
        Optional<Location> locationOpt = locationService.getLocationById(currentLocation.getLocationId());
        if (!locationOpt.isPresent()) {
            // handle case where location is not found
            return null;
        }
        Location location = locationOpt.get();
        Location driverLocation = new Location(location.getLocationId(),location.getLatitude(), location.getLongitude());           
        Driver driver = new Driver(driverId, driverLocation);
        return driverRepository.saveDriver(driver);
    }
    

    @Override
    public List<Driver> getAvailableDriversWithinDistance(Location riderLocation, double distanceInKms) {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : driverRepository.getAllDrivers()) {
            double distance = locationService.getDistanceInKms(driver.getCurrentLocation(), riderLocation);
            if (driver.isAvailable() && distance <= distanceInKms) {
                availableDrivers.add(driver);
            }
        }
       // Soting availableDrivers data
        availableDrivers.sort(Comparator.comparingDouble(driver -> locationService.getDistanceInKms(driver.getCurrentLocation(), riderLocation)));
        return availableDrivers;
    }

    
    @Override
    public void updateDriverAvailability(String driverId, boolean isAvailable) {
        Optional<Driver> driverOptional = driverRepository.getDriverById(driverId);
        if (driverOptional.isPresent()) {
            Driver driver = driverOptional.get();
            driver.setAvailable(isAvailable);
            driverRepository.saveDriver(driver);
        }
    }

    }
    
