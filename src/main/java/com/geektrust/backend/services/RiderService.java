package com.geektrust.backend.services;

import java.util.Optional;

import com.geektrust.backend.entities.Location;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repositories.IRiderRepository;

public class RiderService implements IRiderService {
    private final IRiderRepository riderRepository;
    private final ILocationService locationService;

    public RiderService(IRiderRepository riderRepository, ILocationService locationService) {
        this.riderRepository = riderRepository;
        this.locationService = locationService;
    }



    @Override
    public Rider create(String riderId, Location currentLocation) {
       
        Optional<Location> locationOpt = locationService.getLocationById(currentLocation.getLocationId());
       
        if (!locationOpt.isPresent()) {
            // handle case where location is not found
            return null;
        }
        Location location = locationOpt.get();
        Location riderLocation = new Location(location.getLocationId(),location.getLatitude(), location.getLongitude());           
        Rider rider = new Rider(riderId, riderLocation);
        return riderRepository.saveRider(rider);
    }
    
}