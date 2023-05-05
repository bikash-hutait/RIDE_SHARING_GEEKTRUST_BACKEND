package com.geektrust.backend.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RiderTest {

    // Test the Rider constructor
    @Test
    public void testRiderConstructor() {
        Location location = new Location(40.7128, -74.0060);
        Rider rider = new Rider("rider1", location);
        assertEquals("rider1", rider.getId());
        assertEquals(location, rider.getCurrentLocation());
    }

    // Test the setCurrentLocation method
    @Test
    public void testSetCurrentLocation() {
        Location location1 = new Location(40.7128, -74.0060);
        Location location2 = new Location(41.8781, -87.6298);
        Rider rider = new Rider("rider1", location1);
        rider.setCurrentLocation(location2);
        assertEquals(location2, rider.getCurrentLocation());
    }

    // Test the setRide method
    @Test
    public void testSetRide() {
        Location location = new Location(40.7128, -74.0060);
        //Ride ride = new Ride("RIDE-001", new Driver("D1", location), new Rider("rider1", location), location, location);
        Ride ride=new Ride("RIDE-001", "D3", 2, "R1", true, false, 50, 80, 50);
        
        Rider rider = new Rider("R1", location);
        rider.setRide(ride);
        assertEquals(ride, rider.getRide());
    }
}