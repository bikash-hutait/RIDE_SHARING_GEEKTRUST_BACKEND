package com.geektrust.backend.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.geektrust.backend.entities.Bill;

public class BillRepository implements IBillRepository {
    
    private final Map<String,Bill> billMap;

    public BillRepository(Map<String, Bill> billMap) {
        this.billMap = billMap;
    }

    public BillRepository() {
        this.billMap = new HashMap<>();
    }

    @Override
    public Bill saveBill(Bill entity) {
       
        if (entity.getId() == null) {
            String id = UUID.randomUUID().toString(); 
            Bill bill = new Bill(id,entity.getAmount(), entity.getRide());
            billMap.put(id, bill);
            return bill;          
        }
        billMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<Bill> getAllBills() {
        return billMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Bill> getBillById(String billId) {
        return Optional.ofNullable(billMap.get(billId));
    }

   


    

}
