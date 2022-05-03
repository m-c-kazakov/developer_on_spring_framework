package com.example.testing_students;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserInterface {

    MessageSender messageSender;
    
    MessageSource messageSource;

    Locale locale;
    Map<String, String> questionAndAnswerMap;

    public void execute() {
        try (Scanner scanner = new Scanner(System.in);) {
            messageSender.sendMessage(messageSource.getMessage("whatIsYourName", new String[]{}, locale));
            String userName = scanner.nextLine();

            for (Entry<String, String> entry : questionAndAnswerMap.entrySet()) {
                messageSender.sendMessage(messageSource.getMessage("question", new String[]{}, locale)+ entry.getKey());
                messageSender.sendMessage(messageSource.getMessage("yourAnswerOption", new String[]{}, locale));
                String answer = scanner.nextLine();
                messageSender.sendMessage(messageSource.getMessage("rightAnswer", new String[]{}, locale)+ entry.getValue());
            }
            messageSender.sendMessage(messageSource.getMessage("end", new String[]{}, locale));
        }


    }
}
