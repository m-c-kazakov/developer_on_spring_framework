package com.example.testing_students.controller;

import com.example.testing_students.service.UserInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ShellController {

    UserInterface userInterface;

    @ShellMethod(key = {"start", "s"}, value = "start testing")
    public void start() {
        userInterface.execute();
    }
}
