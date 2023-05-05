package com.geektrust.backend.entities;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DriverTest {

    @Test
    public void testConstructor() {
        String id = "D1";
        Location location = new Location(0.0, 0.0);
        Driver driver = new Driver(id, location);
        assertEquals(id, driver.getId());
        assertEquals(location, driver.getCurrentLocation());
    }
    
    @Test
    public void testSetCurrentLocation() {
        Driver driver = new Driver("D1", new Location(0.0, 0.0));
        Location newLocation = new Location(1.0, 1.0);
        driver.setCurrentLocation(newLocation);
        assertEquals(newLocation, driver.getCurrentLocation());
    }
    
    @Test
    public void testSetAvailable() {
        Driver driver = new Driver("D1", new Location(0.0, 0.0));
        driver.setAvailable(true);
        assertTrue(driver.isAvailable());
        driver.setAvailable(false);
        assertFalse(driver.isAvailable());
    }
    
    
    @Test
    public void testNoRide() {
        Driver driver = new Driver("D1", new Location(0.0, 0.0));
        assertNull(driver.getRide());
    }
    
    @Test
    public void testNegativeLocation() {
        Driver driver = new Driver("D1", new Location(-1.0, -1.0));
        Location newLocation = new Location(-2.0, -2.0);
        driver.setCurrentLocation(newLocation);
        assertEquals(newLocation, driver.getCurrentLocation());
    }
}