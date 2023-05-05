package com.geektrust.backend.services;


import com.geektrust.backend.entities.Location;
import com.geektrust.backend.entities.Rider;

public interface IRiderService {
    public Rider create(String riderId, Location currentLocation); 
}