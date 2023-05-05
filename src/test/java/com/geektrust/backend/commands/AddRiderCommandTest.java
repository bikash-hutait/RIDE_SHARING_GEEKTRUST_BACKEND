package com.geektrust.backend.commands;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.geektrust.backend.entities.Location;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.ILocationService;
import com.geektrust.backend.services.IRiderService;

public class AddRiderCommandTest {
    
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private IRiderService mockRiderService;

    @Mock
    private ILocationService mockLocationService;

    private AddRiderCommand addRiderCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
        addRiderCommand = new AddRiderCommand(mockRiderService, mockLocationService);
    }

    @Test
    public void execute_withValidInput_addRiderCommand() throws InvalidInputException {
        // Arrange
        List<String> tokens = Arrays.asList("ADD_RIDER", "R1", "0", "0");
        Location expectedLocation = new Location(0, 0);
        when(mockLocationService.create(0, 0)).thenReturn(expectedLocation);
        
        // Act
        addRiderCommand.execute(tokens);
    
        // Assert
        verify(mockRiderService).create(eq("R1"), any(Location.class));
    }
    

    @Test
    public void execute_withMissingInput_addRiderCommand() {
        // Arrange
        List<String> tokens = Arrays.asList("ADD_RIDER", "R1", "1");
        String expectedOutput = "INPUT_DATA_ERROR (not enough input values)";

        // Act
        addRiderCommand.execute(tokens);

        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}