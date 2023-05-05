package com.geektrust.backend.services;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Location;
import com.geektrust.backend.repositories.IDriverRepository;

public class DriverServiceTest {

    private DriverService driverService;
    private IDriverRepository driverRepositoryMock;
    private ILocationService locationServiceMock;

    @BeforeEach
    public void setup() {
        driverRepositoryMock = mock(IDriverRepository.class);
        //mock(IRiderRepository.class);
        locationServiceMock = mock(ILocationService.class);
        driverService = new DriverService(driverRepositoryMock, locationServiceMock);
    }

 
    @Test
    public void testCreateLocationNotFound() {
        Location location = new Location("loc1", 10.0, 20.0);

        when(locationServiceMock.getLocationById(location.getLocationId())).thenReturn(Optional.empty());

        Driver createdDriver = driverService.create("D5", location);

        assertNull(createdDriver);
        verify(driverRepositoryMock, never()).saveDriver(any(Driver.class));
        verify(locationServiceMock).getLocationById(location.getLocationId());
    }

   
    @Test
    public void testUpdateDriverAvailability() {
        Driver driver = new Driver("D1", new Location("loc1", 10.0, 20.0));

        when(driverRepositoryMock.getDriverById("D1")).thenReturn(Optional.of(driver));
        when(driverRepositoryMock.saveDriver(driver)).thenReturn(driver);

        driverService.updateDriverAvailability("D1", false);

        assertFalse(driver.isAvailable());
        verify(driverRepositoryMock).getDriverById("D1");
        verify(driverRepositoryMock).saveDriver(driver);
    }

}