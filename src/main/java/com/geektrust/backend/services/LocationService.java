package com.geektrust.backend.services;


import java.util.Optional;

import com.geektrust.backend.entities.Location;
import com.geektrust.backend.repositories.ILocationRepository;

public class LocationService implements ILocationService {

    private final ILocationRepository locationRepository;

    public LocationService(ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location create(double latitude, double longitude) {
       String locationId = null;
        Location location = new Location(locationId,latitude, longitude);
        return locationRepository.saveLocation(location);
    }

    @Override
    public Optional<Location> getLocationById(String locationId) {
        return locationRepository.getLocationById(locationId);
    }

    @Override
    public double getDistanceInKms(Location driverLocation, Location riderLocation) {
       
            double lat1 = driverLocation.getLatitude();
            double lon1 = driverLocation.getLongitude();
            double lat2 = riderLocation.getLatitude();
            double lon2 = riderLocation.getLongitude();
        
            double x1 = lon1;
            double y1 = lat1;
            double x2 = lon2;
            double y2 = lat2;
        
            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            return distance;
        
        
    }

    
}
