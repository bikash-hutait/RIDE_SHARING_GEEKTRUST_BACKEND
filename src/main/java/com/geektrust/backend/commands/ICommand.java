package com.geektrust.backend.commands;


import java.util.List;

import com.geektrust.backend.exceptions.InvalidInputException;

public interface ICommand {
    void execute(List<String> tokens) throws InvalidInputException;
}