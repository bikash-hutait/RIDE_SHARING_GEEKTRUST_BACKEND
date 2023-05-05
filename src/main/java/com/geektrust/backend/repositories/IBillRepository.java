package com.geektrust.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.geektrust.backend.entities.Bill;

public interface IBillRepository {
    public Bill saveBill(Bill bill);
    public Optional<Bill> getBillById(String billId);   
    public List<Bill> getAllBills(); 
}