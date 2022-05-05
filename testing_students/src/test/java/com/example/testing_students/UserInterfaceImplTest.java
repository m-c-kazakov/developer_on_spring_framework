package com.example.testing_students;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.MessageSource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserInterfaceImplTest {

    @MockBean
    MessageSender messageSender;

    @MockBean
    MessageSource messageSource;

    Map<String, String> questionAndAnswerMap;
    @MockBean
    MessageGetter messageGetter;

    @BeforeEach
    void setUp() {
        questionAndAnswerMap = new HashMap<>();
        questionAndAnswerMap.put("Вопрос", "Ответ");
    }

    @Test
    void execute() {
        UserInterfaceImpl userInterface = new UserInterfaceImpl(messageSender, messageSource, Locale.US, questionAndAnswerMap, messageGetter);
        userInterface.execute();

//        verify(messageSender, times(5)).sendMessage(any());
//        verify(messageGetter, times(2)).getMessage();
    }
}

