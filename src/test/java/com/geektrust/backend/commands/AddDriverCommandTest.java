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
import com.geektrust.backend.services.IDriverService;
import com.geektrust.backend.services.ILocationService;

public class AddDriverCommandTest {
    
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private IDriverService mockDriverService;

    @Mock
    private ILocationService mockLocationService;

    private AddDriverCommand addDriverCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
        addDriverCommand = new AddDriverCommand(mockDriverService, mockLocationService);
    }

    @Test
    public void execute_withValidInput_addDriverCommand() throws InvalidInputException {
        // Arrange
        List<String> tokens = Arrays.asList("ADD_DRIVER", "D1", "1", "1");
        Location expectedLocation = new Location(1, 1);
        when(mockLocationService.create(1, 1)).thenReturn(expectedLocation);
        
        // Act
        addDriverCommand.execute(tokens);
    
        // Assert
        verify(mockDriverService).create(eq("D1"), any(Location.class));
    }
    

    @Test
    public void execute_withMissingInput_addDriverCommand() {
        // Arrange
        List<String> tokens = Arrays.asList("ADD_DRIVER", "D1", "1");
        String expectedOutput = "INPUT_DATA_ERROR (not enough input values)";

        // Act
        addDriverCommand.execute(tokens);

        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
