package com.example.testing_students;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.DEFAULT)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserInterfaceImpl implements UserInterface {

    MessageSender messageSender;
    
    MessageSource messageSource;

    Locale locale;
    Map<String, String> questionAndAnswerMap;

    MessageGetter messageGetter;

    @Override
    public void execute() {

        messageSender.sendMessage(messageSource.getMessage("whatIsYourName", new String[]{}, locale));
        String userName = messageGetter.getMessage();
        for (Entry<String, String> entry : questionAndAnswerMap.entrySet()) {
            messageSender.sendMessage(messageSource.getMessage("question", new String[]{}, locale)+ entry.getKey());
            messageSender.sendMessage(messageSource.getMessage("yourAnswerOption", new String[]{}, locale));
            String answer = messageGetter.getMessage();
            messageSender.sendMessage(messageSource.getMessage("rightAnswer", new String[]{}, locale)+ entry.getValue());
        }
        messageSender.sendMessage(messageSource.getMessage("end", new String[]{}, locale));


    }
}
