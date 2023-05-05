package com.geektrust.backend.commands;


import java.util.List;

import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRideService;

public class StartRideCommand implements ICommand {

    private final IRideService rideService;
   
    public StartRideCommand(IRideService rideService) {
        this.rideService = rideService;       
    }
    
 // Sample Input Token List:- ["START_RIDE", "RIDE-001", "2", "R1"]
   
 @Override
 public void execute(List<String> tokens) throws InvalidInputException {
    // System.out.println(tokens);
            
         try {
         
             if (tokens.size() < 4) {
                 throw new InvalidInputException("INPUT_DATA_ERROR (not enough input values)");
             }
             
             String rideId = tokens.get(1);
             int driverIndex = Integer.parseInt(tokens.get(2));
             String riderId = tokens.get(3);
             
             
             if (riderId == null || riderId.isEmpty()) {
                 throw new InvalidInputException("INPUT_DATA_ERROR\n(because of missing rider Id)\n");
             }
 
            
            String result=rideService.startRide(rideId, driverIndex,riderId);        
             System.out.println(result);
         }  catch (Exception e) {
             System.out.println(e.getMessage());
         }
     }
     

}