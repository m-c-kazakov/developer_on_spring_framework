package com.example.testing_students;

import com.example.testing_students.service.MessageGetter;
import com.example.testing_students.service.MessageSender;
import com.example.testing_students.service.UserInterfaceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserInterfaceImplTest {

    @MockBean
    MessageSender messageSender;

    @MockBean
    MessageSource messageSource;
    @MockBean
    MessageGetter messageGetter;


    @Test
    void execute() {
        Map<String, String> questionAndAnswerMap = new HashMap<>();
        String answer = "Ответ";
        questionAndAnswerMap.put("Вопрос", answer);
        when(messageGetter.getMessage()).thenReturn("Имя").thenReturn(answer);
        UserInterfaceImpl userInterface = new UserInterfaceImpl(messageSender, messageSource, Locale.US, questionAndAnswerMap, messageGetter);

        assertThat(userInterface.execute()).isEqualTo(1);
    }
}

