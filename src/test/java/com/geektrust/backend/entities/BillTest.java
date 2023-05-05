package com.geektrust.backend.entities;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BillTest {

    @Test
    public void testConstructor() {
        String id = "B001";
        double amount = 20.0;
        Ride ride = new Ride("R1", new Location(1.0, 1.0), new Location(2.0, 2.0));
        Bill bill = new Bill(id, amount, ride);
        assertEquals(id, bill.getId());
        assertEquals(amount, bill.getAmount());
        assertEquals(ride, bill.getRide());
    }
    
    @Test
    public void testSetAmount() {
        Bill bill = new Bill("B001", 20.0, new Ride("R001", new Location(1.0, 1.0), new Location(2.0, 2.0)));
        double newAmount = 30.0;
        bill.setAmount(newAmount);
        assertEquals(newAmount, bill.getAmount());
    }
    
    @Test
    public void testSetRide() {
        Bill bill = new Bill("B001", 20.0, new Ride("R001", new Location(1.0, 1.0), new Location(2.0, 2.0)));
        Ride newRide = new Ride("R002", new Location(3.0, 3.0), new Location(4.0, 4.0));
        bill.setRide(newRide);
        assertEquals(newRide, bill.getRide());
    }
    
  
    @Test
    public void testZeroAmount() {
        Bill bill = new Bill("B001", 0.0, new Ride("R001", new Location(1.0, 1.0), new Location(2.0, 2.0)));
        assertEquals(0.0, bill.getAmount());
    }

    @Test
    public void testNullRide() {
        Bill bill = new Bill("B001", 20.0, null);
        assertNull(bill.getRide());
    }
}
