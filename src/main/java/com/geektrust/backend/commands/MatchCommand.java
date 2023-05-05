package com.geektrust.backend.commands;


import java.util.List;

import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRideService;

public class MatchCommand implements ICommand {
   
    
    private final IRideService rideService;
   
    public MatchCommand(IRideService rideService) {
        this.rideService = rideService;       
    }

    
    // Sample Input Token List:- ["MATCH" ,"R1"]
   
    @Override
    public void execute(List<String> tokens) throws InvalidInputException {
       // System.out.println(tokens);
               
            try {
            
                if (tokens.size() < 2) {
                    throw new InvalidInputException("INPUT_DATA_ERROR (not enough input values)");
                }
                
                String riderId = tokens.get(1);
                
                if (riderId == null || riderId.isEmpty()) {
                    throw new InvalidInputException("INPUT_DATA_ERROR\n(because of missing rider Id)");
                }
    
               
                
               String availableDrivers=rideService.matchRider(riderId);        
                System.out.println(availableDrivers);
            }  catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        

}