package com.geektrust.backend.commands;


import java.util.List;

import com.geektrust.backend.entities.Location;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.ILocationService;
import com.geektrust.backend.services.IRiderService;

public class AddRiderCommand implements ICommand{
    

    private final IRiderService riderService;
    private ILocationService locationService;
    
    public AddRiderCommand(IRiderService riderService, ILocationService locationService) {
        this.riderService = riderService;
        this.locationService=locationService;
    }

    
    // Sample Input Token List:- ["ADD_RIDER" "R1" "0" "0"]
   
    @Override
    public void execute(List<String> tokens) throws InvalidInputException {
       // System.out.println(tokens);
               
            try {
            
                if (tokens.size() < 4) {
                    throw new InvalidInputException("INPUT_DATA_ERROR (not enough input values)");
                }
                
                String riderId = tokens.get(1);
                String coordinateXStr=tokens.get(2);
                String coordinateYStr = tokens.get(3);
               
                if (riderId == null || riderId.isEmpty() || coordinateXStr == null || coordinateXStr.isEmpty() || coordinateYStr == null || coordinateYStr.isEmpty()) {
                    throw new InvalidInputException("INPUT_DATA_ERROR\n(because of missing rider Id and coordinates)\n");
                }
    
                double coordinateX = Double.parseDouble(coordinateXStr);
                double coordinateY = Double.parseDouble(coordinateYStr);
               
                Location location=locationService.create(coordinateX, coordinateY);
                
                riderService.create(riderId, location);           
                
            }  catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        

    
    }
    