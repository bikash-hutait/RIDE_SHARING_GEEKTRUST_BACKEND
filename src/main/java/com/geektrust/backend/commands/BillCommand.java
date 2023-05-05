package com.geektrust.backend.commands;


import java.util.List;

import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRideService;

public class BillCommand implements ICommand {
    
    private final IRideService rideService;
   
    public BillCommand(IRideService rideService) {
        this.rideService = rideService;       
    }
    
 // Sample Input Token List:- ["BILL", "RIDE-001"]
   
 @Override
 public void execute(List<String> tokens) throws InvalidInputException {
    // System.out.println(tokens);
            
         try {
         
            if (tokens.size() < 2) {               
                throw new InvalidInputException("INPUT_DATA_ERROR\n(because of missing ride Id)");
              }              
              String rideId = tokens.get(1);
            
             
            
            String result=rideService.generateBill(rideId);        
             System.out.println(result);
         }  catch (Exception e) {
             System.out.println(e.getMessage());
         }
     }
     

}