package com.geektrust.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Location;
import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repositories.IDriverRepository;
import com.geektrust.backend.repositories.IRideRepository;
import com.geektrust.backend.repositories.IRiderRepository;

public class RideService implements IRideService {
    
    private final IRiderRepository riderRepository;
    private final IDriverRepository driverRepository;
    private final ILocationService locationService;
    private final IRideRepository rideRepository;

    public RideService(IRiderRepository riderRepository, IDriverRepository driverRepository,ILocationService locationService,IRideRepository rideRepository) {
        this.riderRepository = riderRepository;
        this.driverRepository = driverRepository;
        this.locationService = locationService;
        this.rideRepository = rideRepository;
    }

    @Override
    public String matchRider(String riderId) {
        Optional<Rider> optionalRider = riderRepository.getRiderById(riderId);
        //System.out.println("optionalRider:"+optionalRider);
        
        if (optionalRider.isEmpty()) {
            return "INVALID_RIDER_ID";
           
        }
    
        Rider rider = optionalRider.get();
        if (rider.getRide() != null) {
            return "RIDE_IN_PROGRESS";         
        }
    
        Location riderLocation = rider.getCurrentLocation();
        List<Driver> availableDrivers = driverRepository.getAvailableDriversWithinDistance(riderLocation, 5);
        if (availableDrivers.isEmpty()) {
            return "NO_DRIVERS_AVAILABLE"; 
           
        }
           
        List<String> driverIds = availableDrivers.stream().map(Driver::getId).collect(Collectors.toList());
        return "DRIVERS_MATCHED " + String.join(" ", driverIds);
    }

    @Override
    public String startRide(String rideId, int driverIndex, String riderId) {
        Optional<Ride> existingRide = rideRepository.getRideById(rideId);
        
            if (existingRide == null) {
                return "INVALID_RIDE";                
            }
            
            Optional<Rider> existingRider = riderRepository.getRiderById(riderId);
            if (existingRider == null) {
                return "INVALID_RIDE";  
            }
            
            Optional<Rider> riderDetails = riderRepository.getRiderById(riderId);
            Rider rider = riderDetails.get();
            Location riderLocation = rider.getCurrentLocation();

            List<Driver> availableDrivers = driverRepository.getAvailableDriversWithinDistance(riderLocation, 5);
            if (availableDrivers.size() < driverIndex) {
                return "INVALID_RIDE";  
            }
            
            Driver driver = availableDrivers.get(driverIndex - 1);
           
            if (!driver.isAvailable()) {
                return "INVALID_RIDE";  
            }
            String driverId=driver.getId();
            Ride newRide = new Ride(rideId,driverId, driverIndex, riderId, true,false,0,0,0);
            rideRepository.saveRide(newRide);
            return "RIDE_STARTED " + rideId; 
           
        }
        

        @Override
        public String stopRide(String rideId, double destinationX, double destinationY, int timeTaken) {
            Optional<Ride> ride = rideRepository.getRideById(rideId);
            if (ride.isPresent()) {        
                Ride rideObj = ride.get();

                // UPDATE RIDE INFORMATION
                Ride newRide = new Ride(rideId,rideObj.getDriverId(), rideObj.getDriverIndex(), rideObj.getRiderId(), true, true, destinationX, destinationY, timeTaken);
                rideRepository.saveRide(newRide);                   
                return "RIDE_STOPPED " + rideId;
            } else {
                return "INVALID_RIDE";
            }
        }
        

        @Override
        public String generateBill(String rideId) {
            Optional<Ride> ride = rideRepository.getRideById(rideId);        
            if (ride == null) {
                return "INVALID_RIDE";
            }
        
            if (!ride.get().isStopped()) {
                return "RIDE_NOT_COMPLETED";
            }
        
            // GET RIDER STARTING AND DESTINATION LOCATIONS
            Optional<Rider> riderInfo = riderRepository.getRiderById(ride.get().getRiderId());
            Rider rider = riderInfo.get();
            Location startLocation = rider.getCurrentLocation();
            Location destination = new Location(ride.get().getDestinationX(), ride.get().getDestinationY());
        
            double distanceKm = locationService.getDistanceInKms(startLocation, destination);
            double distanceInKm = Double.parseDouble(String.format("%.2f", distanceKm));

            int timeTaken = ride.get().getTimeTakenInMin();
            double timeTakenInMin = (double) timeTaken;
            double fare = 50.00 + (6.50 * distanceInKm) + (2.00 * timeTakenInMin);
            double serviceTax = 0.20 * fare;
            double totalFare = fare + serviceTax;       
            return String.format("BILL %s %s %.2f", rideId, ride.get().getDriverId(), totalFare);
        }
                
        
}