package com.geektrust.backend.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.entities.Location;
import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repositories.IDriverRepository;
import com.geektrust.backend.repositories.IRideRepository;
import com.geektrust.backend.repositories.IRiderRepository;

public class RideServiceTest {

    private IRiderRepository riderRepository;
    private IDriverRepository driverRepository;
    private ILocationService locationService;
    private IRideRepository rideRepository;
    private IRideService rideService;

    @BeforeEach
    public void setup() {
        riderRepository = mock(IRiderRepository.class);
        driverRepository = mock(IDriverRepository.class);
        locationService = mock(ILocationService.class);
        rideRepository = mock(IRideRepository.class);
        rideService = new RideService(riderRepository, driverRepository, locationService, rideRepository);
    }

    @Test
    public void testMatchRiderWithInvalidRiderId() {
        when(riderRepository.getRiderById("123")).thenReturn(Optional.empty());
        String result = rideService.matchRider("123");
        assertEquals("INVALID_RIDER_ID", result);
    }

    @Test
    public void testMatchRiderWithRideInProgress() {
        Rider rider = new Rider("R1", new Location(10, 20));
        Ride ride = new Ride("RIDE-001", "D1", 1, "R1", true, false, 0, 0, 0);
        rider.setRide(ride);
    
        riderRepository.saveRider(rider);
        when(riderRepository.getRiderById("R1")).thenReturn(Optional.of(rider));
        String result = rideService.matchRider("R1");
        assertEquals("RIDE_IN_PROGRESS", result);
    }
    
    @Test
    public void testMatchRiderWithNoDriversAvailable() {
        Rider rider = new Rider("R1", new Location(10, 20));
        when(riderRepository.getRiderById("R1")).thenReturn(Optional.of(rider));
        when(driverRepository.getAvailableDriversWithinDistance(eq(rider.getCurrentLocation()), eq(5))).thenReturn(Collections.emptyList());
        String result = rideService.matchRider("R1");
        assertEquals("NO_DRIVERS_AVAILABLE", result);
    }

   
   

  }