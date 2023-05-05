package com.geektrust.backend.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.geektrust.backend.entities.Rider;

public class RiderRepository implements IRiderRepository {
    private final Map<String,Rider> riderMap;

    public RiderRepository(){
        riderMap = new HashMap<String,Rider>();
    }

    public RiderRepository(Map<String, Rider> riderMap) {
        this.riderMap = riderMap;
    }
    @Override
    public Rider saveRider(Rider entity) {
       
        if (entity.getId() == null) {
            int count=riderMap.size();
            String id = "R"+count+1;            
            Rider rider = new Rider(id, entity.getCurrentLocation());
            riderMap.put(rider.getId(),rider);
            return rider;

        }
        riderMap.put(entity.getId(),entity);
        return entity;
    }
    

    @Override
    public List<Rider> getAllRiders() {
        return riderMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Rider> getRiderById(String riderId) {
       return Optional.ofNullable(riderMap.get(riderId));
       
    }
    
    

   
   
}