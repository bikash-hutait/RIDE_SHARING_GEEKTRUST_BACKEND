package com.geektrust.backend.entities;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    // Test the Location constructor with a location ID
    @Test
    public void testLocationConstructorWithId() {
        Location location = new Location("loc1", 40.7128, -74.0060);
        assertEquals("loc1", location.getLocationId());
        assertEquals(40.7128, location.getLatitude());
        assertEquals(-74.0060, location.getLongitude());
    }

    // Test the Location constructor without a location ID
    @Test
    public void testLocationConstructorWithoutId() {
        Location location = new Location(40.7128, -74.0060);
        assertNull(location.getLocationId());
        assertEquals(40.7128, location.getLatitude());
        assertEquals(-74.0060, location.getLongitude());
    }

    // Test the setLatitude method
    @Test
    public void testSetLatitude() {
        Location location = new Location("loc1", 40.7128, -74.0060);
        location.setLatitude(41.8781);
        assertEquals(41.8781, location.getLatitude());
    }

    // Test the setLongitude method
    @Test
    public void testSetLongitude() {
        Location location = new Location("loc1", 40.7128, -74.0060);
        location.setLongitude(-87.6298);
        assertEquals(-87.6298, location.getLongitude());
    }

    // Test the getCurrentLocation method
    @Test
    public void testGetCurrentLocation() {
        Location location = new Location("loc1", 40.7128, -87.6298);
        assertEquals("40.7128 -87.6298", location.getCurrentLocation());
    }
}
