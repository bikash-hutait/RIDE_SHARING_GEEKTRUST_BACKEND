package com.geektrust.backend.entities;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RideTest {
    
    // test constructor
    @Test
    public void testConstructor() {
        Location loc2 = new Location("loc2", 37.3382, -121.8863);
        Ride ride = new Ride("ride1", "driver1", 0, "rider1", false, false, loc2.getLatitude(), loc2.getLongitude(), 10);
        assertEquals("ride1", ride.getRideId());
        assertEquals("driver1", ride.getDriverId());
        assertEquals(0, ride.getDriverIndex());
        assertEquals("rider1", ride.getRiderId());
        assertFalse(ride.isStarted());
        assertFalse(ride.isStopped());
        assertEquals(loc2.getLatitude(), ride.getDestinationX());
        assertEquals(loc2.getLongitude(), ride.getDestinationY());
        assertEquals(10, ride.getTimeTakenInMin());
    }
    
    // test setters and getters
    @Test
    public void testSettersAndGetters() {
        Location loc1 = new Location("loc1", 37.7749, -122.4194);
        Location loc2 = new Location("loc2", 37.3382, -121.8863);
        Ride ride = new Ride("ride1", "driver1", 0, "rider1", false, false, loc2.getLatitude(), loc2.getLongitude(), 10);
        ride.setRideId("ride2");
        assertEquals("ride2", ride.getRideId());
        ride.setDriverId("driver2");
        assertEquals("driver2", ride.getDriverId());
        ride.setDriverIndex(1);
        assertEquals(1, ride.getDriverIndex());
        ride.setRiderId("rider2");
        assertEquals("rider2", ride.getRiderId());
        ride.setStarted(true);
        assertTrue(ride.isStarted());
        ride.setStopped(true);
        assertTrue(ride.isStopped());
        ride.setDestinationX(loc1.getLatitude());
        assertEquals(loc1.getLatitude(), ride.getDestinationX());
        ride.setDestinationY(loc1.getLongitude());
        assertEquals(loc1.getLongitude(), ride.getDestinationY());
        ride.setTimeTakenInMin(20);
        assertEquals(20, ride.getTimeTakenInMin());
    }
}