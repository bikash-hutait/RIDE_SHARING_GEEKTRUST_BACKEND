package com.geektrust.backend.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.geektrust.backend.entities.Location;

public class LocationRepository implements ILocationRepository{
    private final Map<String,Location> locationMap;

    public LocationRepository(Map<String, Location> locationMap) {
        this.locationMap = locationMap;
    }

    public LocationRepository() {
        this.locationMap = new HashMap<>();
    }

    @Override
    public Location saveLocation(Location entity) {
      
    if (entity.getLocationId() == null) {
        String locationId = UUID.randomUUID().toString(); 
        Location location = new Location(locationId, entity.getLatitude(), entity.getLongitude());
        locationMap.put(locationId, location);
        return location;
    }
   
        locationMap.put(entity.getLocationId(),entity);
        return entity;

    }


    @Override
    public List<Location> getAllLocations() {
        return locationMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Location> getLocationById(String locationId) {
        return Optional.ofNullable(locationMap.get(locationId));

    }

    

    
}