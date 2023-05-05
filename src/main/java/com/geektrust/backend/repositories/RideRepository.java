package com.geektrust.backend.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.geektrust.backend.entities.Ride;

public class RideRepository implements IRideRepository {
    private final Map<String,Ride> rideMap;

    public RideRepository(Map<String, Ride> rideMap) {
        this.rideMap = rideMap;
    }

    public RideRepository() {
        this.rideMap = new HashMap<>();
    }

    @Override
    public Ride saveRide(Ride entity) {
      
    if (entity.getRideId() == null) {
        String rideId = UUID.randomUUID().toString(); 
        Ride ride = new Ride(rideId,entity.getDriverId(), entity.getDriverIndex(), entity.getRiderId(), false,false,0,0,0);
        rideMap.put(rideId, ride);
        return ride;
       }       
        rideMap.put(entity.getRideId(),entity);
        return entity;

    }


    @Override
    public List<Ride> getAllRides() {
        return rideMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Ride> getRideById(String rideId) {      
        return Optional.ofNullable(rideMap.get(rideId));
    }

   

    
}