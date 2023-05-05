package com.geektrust.backend.commands;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRideService;

public class MatchCommandTest {

    @Mock
    private IRideService mockRideService;

    private MatchCommand matchCommand;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        matchCommand = new MatchCommand(mockRideService);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_withValidInput_shouldMatchRider() throws InvalidInputException {
        // Arrange
        String riderId = "R1";
        List<String> tokens = Arrays.asList("MATCH", riderId);
        String expectedOutput = "D1\nD2";

        Mockito.when(mockRideService.matchRider(riderId)).thenReturn(expectedOutput);

        // Act
        matchCommand.execute(tokens);

        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        Mockito.verify(mockRideService, Mockito.times(1)).matchRider(riderId);
    }

    @Test
    public void execute_withMatchMissingInput() {
        // Arrange
        List<String> tokens = Arrays.asList("MATCH");
        String expectedOutput = "INPUT_DATA_ERROR (not enough input values)";

        // Act
        matchCommand.execute(tokens);

        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_withMissingRiderId_matchCommand() {
        // Arrange
        List<String> tokens = Arrays.asList("MATCH", "");
        String expectedOutput = "INPUT_DATA_ERROR\n(because of missing rider Id)";

        // Act
        matchCommand.execute(tokens);

        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

}