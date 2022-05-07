package com.example.testing_students;

import com.example.testing_students.controller.ShellController;
import com.example.testing_students.service.UserInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


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