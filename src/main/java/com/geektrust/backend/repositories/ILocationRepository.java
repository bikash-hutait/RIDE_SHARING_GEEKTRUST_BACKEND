package com.geektrust.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.geektrust.backend.entities.Location;

public interface ILocationRepository {    
    public Location saveLocation(Location location);
    public Optional<Location> getLocationById(String driverId);   
    public List<Location> getAllLocations();
}