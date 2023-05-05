package com.geektrust.backend.entities;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class MatchTest {

    // Test the Match constructor
    @Test
    public void testMatchConstructor() {
        List<String> driverIds = Arrays.asList("D1", "D2", "D3");
        Match match = new Match("match1", "R1", driverIds);
        assertEquals("match1", match.getId());
        assertEquals("R1", match.getRiderId());
        assertEquals(driverIds, match.getDriverIds());
    }

    // Test the getDriverIds method
    @Test
    public void testGetDriverIds() {
        List<String> driverIds = Arrays.asList("D1", "D2", "D3");
        Match match = new Match("match1", "R1", driverIds);
        assertEquals(driverIds, match.getDriverIds());
    }

    // Test the getRiderId method
    @Test
    public void testGetRiderId() {
        List<String> driverIds = Arrays.asList("D1", "D2", "D3");
        Match match = new Match("match1", "R1", driverIds);
        assertEquals("R1", match.getRiderId());
    }

    // Test the getId method
    @Test
    public void testGetId() {
        List<String> driverIds = Arrays.asList("D1", "D2", "D3");
        Match match = new Match("match1", "R1", driverIds);
        assertEquals("match1", match.getId());
    }
}