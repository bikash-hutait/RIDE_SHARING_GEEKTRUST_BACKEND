package com.geektrust.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.geektrust.backend.entities.Rider;

public interface IRiderRepository {    
    public Rider saveRider(Rider rider);
    public Optional<Rider> getRiderById(String riderId);   
    public List<Rider> getAllRiders();
}