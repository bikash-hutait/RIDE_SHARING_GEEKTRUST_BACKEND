package com.geektrust.backend.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.entities.Location;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repositories.IRiderRepository;

public class RiderServiceTest {
    private IRiderRepository riderRepositoryMock;
    private ILocationService locationServiceMock;
    private IRiderService riderService;

    @BeforeEach
    public void setUp() {
        riderRepositoryMock = mock(IRiderRepository.class);
        locationServiceMock = mock(ILocationService.class);
        riderService = new RiderService(riderRepositoryMock, locationServiceMock);
    }

    @Test
    public void testCreateRider() {
        // Create mock data
        String riderId = "R1";
        Location location = new Location("loc1", 1.0, 1.0);

        // Set up mock behavior
        when(locationServiceMock.getLocationById(location.getLocationId())).thenReturn(Optional.of(location));
        when(riderRepositoryMock.saveRider(any(Rider.class))).thenReturn(new Rider(riderId, location));

        // Call the method
        Rider result = riderService.create(riderId, location);

        // Check the result
        assertNotNull(result);
        assertEquals(riderId, result.getId());
        assertEquals(location, result.getCurrentLocation());

        // Verify mock behavior
        verify(locationServiceMock).getLocationById(location.getLocationId());
        verify(riderRepositoryMock).saveRider(any(Rider.class));
    }

    @Test
    public void testCreateRiderWithInvalidLocation() {
        // Create mock data
        String riderId = "R1";
        Location location = new Location("loc1", 1.0, 1.0);

        // Set up mock behavior
        when(locationServiceMock.getLocationById(location.getLocationId())).thenReturn(Optional.empty());

        // Call the method
        Rider result = riderService.create(riderId, location);

        // Check the result
        assertNull(result);

        // Verify mock behavior
        verify(locationServiceMock).getLocationById(location.getLocationId());
        verify(riderRepositoryMock, never()).saveRider(any(Rider.class));
    }
}