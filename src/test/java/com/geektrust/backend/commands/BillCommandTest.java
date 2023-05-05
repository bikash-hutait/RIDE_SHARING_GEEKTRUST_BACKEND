package com.geektrust.backend.commands;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRideService;

public class BillCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private BillCommand billCommand;
    private IRideService mockRideService;

    @BeforeEach
    public void setUp() {
        mockRideService = Mockito.mock(IRideService.class);
        billCommand = new BillCommand(mockRideService);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_withValidInput_shouldCallRideServiceGenerateBillMethod() throws InvalidInputException {
    
        // Arrange
        String rideId = "RIDE-001";

        List<String> tokens = Arrays.asList("BILL", "RIDE-001");
        String expectedOutput = "BILL RIDE-001 D3 186.72";
    
        Mockito.when(mockRideService.generateBill(rideId)).thenReturn(expectedOutput);

        // Act
        billCommand.execute(tokens);

        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        Mockito.verify(mockRideService, Mockito.times(1)).generateBill(rideId);

    }

    @Test
    public void execute_withInvalidInput_missingRideId_billCommand() {
    
        // Arrange
        List<String> tokens = Arrays.asList("BILL");
        String expectedOutput = "INPUT_DATA_ERROR\n(because of missing ride Id)";

        // Act & Assert
        billCommand.execute(tokens);
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

    }

}