package com.geektrust.backend.commands;


import java.util.List;

import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRideService;

public class StopRideCommand implements ICommand {

    private final IRideService rideService;
   
    public StopRideCommand(IRideService rideService) {
        this.rideService = rideService;       
    }
    
 // Sample Input Token List:- ["STOP_RIDE", "RIDE-001", "4","5", "32"]
   
 @Override
 public void execute(List<String> tokens) throws InvalidInputException {
    // System.out.println(tokens);
            
         try {
         
             if (tokens.size() < 2) {
                 throw new InvalidInputException("INPUT_DATA_ERROR (not enough input values)");
             }
             
             String locationX=tokens.get(2);
             String locationY=tokens.get(3);
             String time=tokens.get(4);
                
             String rideId = tokens.get(1);
             double destinationX = Double.parseDouble(locationX);
             double destinationY = Double.parseDouble(locationY);
             int timeTaken = Integer.parseInt(time);
            
             
             
             if (rideId == null || rideId.isEmpty()) {
                 throw new InvalidInputException("INPUT_DATA_ERROR\n(because of missing ride Id)\n");
             }

             if (locationX == null || locationX.isEmpty() || locationY == null || locationY.isEmpty()) {
                throw new InvalidInputException("INPUT_DATA_ERROR\n(because of missing DESTINATION COORDINATES)\n");
            }
            if (time == null || time.isEmpty()) {
                throw new InvalidInputException("INPUT_DATA_ERROR\n(because of missing timeTaken)\n");
            }
            
            String result=rideService.stopRide(rideId, destinationX,destinationY,timeTaken);        
             System.out.println(result);
         }  catch (Exception e) {
             System.out.println(e.getMessage());
         }
     }
     

}