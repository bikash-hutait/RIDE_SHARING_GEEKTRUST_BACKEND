package com.geektrust.backend.commands;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRideService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class StopRideCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private StopRideCommand stopRideCommand;
    private IRideService mockRideService;

    @BeforeEach
    public void setUp() {
        mockRideService = Mockito.mock(IRideService.class);
        stopRideCommand = new StopRideCommand(mockRideService);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_withValidInput_StopRideMethod() throws InvalidInputException {
        // Arrange
        List<String> tokens = Arrays.asList("STOP_RIDE", "RIDE-001", "4", "5", "32");
        String expectedOutput = "RIDE_COMPLETED RIDE-001";

        Mockito.when(mockRideService.stopRide("RIDE-001", 4, 5, 32)).thenReturn(expectedOutput);

        // Act
        stopRideCommand.execute(tokens);

        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        Mockito.verify(mockRideService, Mockito.times(1)).stopRide("RIDE-001", 4, 5, 32);
    }

    @Test
    public void execute_withMissingInput() {
        // Arrange
        List<String> tokens = Arrays.asList("STOP_RIDE");
        String expectedOutput = "INPUT_DATA_ERROR (not enough input values)";
        
        // Act and Assert
        stopRideCommand.execute(tokens);
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_withMissingRideId() {
        // Arrange
        List<String> tokens = Arrays.asList("STOP_RIDE", "", "4", "5", "32");
        String expectedOutput = "INPUT_DATA_ERROR\n(because of missing ride Id)";
        // Act and Assert
        stopRideCommand.execute(tokens);
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }


  
}