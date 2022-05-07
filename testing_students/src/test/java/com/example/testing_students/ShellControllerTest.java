package com.example.testing_students;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ShellControllerTest {
    @Autowired
    ShellController shellController;
    @MockBean
    UserInterface userInterface;

    @Test
    void start() {

        shellController.start();
        Mockito.verify(userInterface, Mockito.times(1)).execute();
    }
}