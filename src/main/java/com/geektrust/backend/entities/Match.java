package com.geektrust.backend.entities;


import java.util.List;

public class Match {
    private final String id;
    private final String riderId;
    private final List<String> driverIds;

    public Match(String id, String riderId, List<String> driverIds) {
        this.id = id;
        this.riderId = riderId;
        this.driverIds = driverIds;
    }

    public String getId() {
        return id;
    }

    public String getRiderId() {
        return riderId;
    }

    public List<String> getDriverIds() {
        return driverIds;
    }
}
