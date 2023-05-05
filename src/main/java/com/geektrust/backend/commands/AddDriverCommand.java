package com.geektrust.backend.commands;



import java.util.List;

import com.geektrust.backend.entities.Location;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IDriverService;
import com.geektrust.backend.services.ILocationService;


public class AddDriverCommand implements ICommand{

    private final IDriverService driverService;
    private ILocationService locationService;
    
    public AddDriverCommand(IDriverService driverService, ILocationService locationService) {
        this.driverService = driverService;
        this.locationService=locationService;
    }

    
    // Sample Input Token List:- ["ADD_DRIVER" "D1" "1" "1"]

    @Override
    public void execute(List<String> tokens) throws InvalidInputException {
       // System.out.println(tokens);
               
            try {
            
                if (tokens.size() < 4) { // Check that the tokens list has at least 4 elements
                    throw new InvalidInputException("INPUT_DATA_ERROR (not enough input values)");
                }
                
                String driverId = tokens.get(1);
                String coordinateXStr=tokens.get(2);
                String coordinateYStr = tokens.get(3);
               
                if (driverId == null || driverId.isEmpty() || coordinateXStr == null || coordinateXStr.isEmpty() || coordinateYStr == null || coordinateYStr.isEmpty()) {
                    throw new InvalidInputException("INPUT_DATA_ERROR\n(because of missing driver Id and coordinates)\n");
                }
    
                double coordinateX = Double.parseDouble(coordinateXStr);
                double coordinateY = Double.parseDouble(coordinateYStr);
               
                Location location=locationService.create(coordinateX, coordinateY);
                
                driverService.create(driverId, location);           
               
            }  catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        

    
    }
    