package com.geektrust.backend.entities;


public class Bill {
    private final String id;
    private double amount;
    private Ride ride;
    
    public Bill(String id, double amount, Ride ride) {
        this.id = id;
        this.amount = amount;
        this.ride = ride;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    } 


    
}