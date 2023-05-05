package com.geektrust.backend.commands;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRideService;

public class StartRideCommandTest {

private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

private IRideService mockRideService;
private StartRideCommand startRideCommand;



@BeforeEach
public void setUp() {
    MockitoAnnotations.openMocks(this);
    startRideCommand = new StartRideCommand(mockRideService);
    System.setOut(new PrintStream(outputStreamCaptor));
}

@Test
public void execute_withValidInput_shouldCallRideServiceStartRideMethod() throws InvalidInputException {
    
    // Arrange
    IRideService mockRideService = Mockito.mock(IRideService.class);
    StartRideCommand startRideCommand = new StartRideCommand(mockRideService);
    String rideId = "RIDE-001";
    String riderId = "R1";
    int driverIndex = 2;

    List<String> tokens = Arrays.asList("START_RIDE", "RIDE-001", "2", "R1");
    String expectedOutput = "RIDE_STARTED RIDE-001";
    
    Mockito.when(mockRideService.startRide(rideId,driverIndex,riderId)).thenReturn(expectedOutput);

    // Act
    startRideCommand.execute(tokens);

    // Assert
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    Mockito.verify(mockRideService, Mockito.times(1)).startRide(rideId,driverIndex,riderId);

}


@Test
public void execute_withMissingRiderId_startRideCommand() throws InvalidInputException {
    // Arrange
    List<String> tokens = Arrays.asList("START_RIDE", "RIDE-001", "2","");
    String expectedOutput = "INPUT_DATA_ERROR\n(because of missing rider Id)";

    // Act
    startRideCommand.execute(tokens);

    // Assert
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

   
}

@Test
public void execute_withNotEnoughInputValues_startRideCommand() throws InvalidInputException {
    // Arrange
    List<String> tokens = Arrays.asList("START_RIDE", "RIDE-001");

    String expectedOutput = "INPUT_DATA_ERROR (not enough input values)";

    // Act
    startRideCommand.execute(tokens);

    // Assert
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

}

}