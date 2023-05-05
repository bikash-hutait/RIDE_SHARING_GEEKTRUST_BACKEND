package com.geektrust.backend.appConfig;


import com.geektrust.backend.commands.*;
import com.geektrust.backend.repositories.*;
import com.geektrust.backend.services.*;

public class ApplicationConfig {
   
    private final ILocationRepository locationRepository = new LocationRepository();
    private final IDriverRepository driverRepository = new DriverRepository();
    private final IRideRepository rideRepository = new RideRepository();
    private final IRiderRepository riderRepository = new RiderRepository();


    private final ILocationService locationService = new LocationService(locationRepository);   
    private final IDriverService driverService = new DriverService(driverRepository, locationService);
    private final IRiderService riderService = new RiderService(riderRepository, locationService);
    private final IRideService rideService = new RideService(riderRepository, driverRepository, locationService,rideRepository);

   
    private final AddDriverCommand addDriverCommand = new AddDriverCommand(driverService,locationService);
    private final AddRiderCommand addRiderCommand = new AddRiderCommand(riderService,locationService);
    private final MatchCommand matchCommand = new MatchCommand(rideService);
    private final StartRideCommand startRideCommand = new StartRideCommand(rideService);
    private final StopRideCommand stopRideCommand = new StopRideCommand(rideService);
    private final BillCommand billCommand = new BillCommand(rideService);
    
    
    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("ADD_DRIVER",addDriverCommand);
        commandInvoker.register("ADD_RIDER",addRiderCommand);
        commandInvoker.register("MATCH",matchCommand);
        commandInvoker.register("START_RIDE",startRideCommand);
        commandInvoker.register("STOP_RIDE",stopRideCommand);
        commandInvoker.register("BILL",billCommand);
       
        
       
        return commandInvoker;
    }



    
}
