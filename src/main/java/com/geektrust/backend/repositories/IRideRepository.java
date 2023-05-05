package com.geektrust.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.geektrust.backend.entities.Ride;

public interface IRideRepository {
    public Ride saveRide(Ride ride);
    public Optional<Ride> getRideById(String rideId);   
    public List<Ride> getAllRides();
}