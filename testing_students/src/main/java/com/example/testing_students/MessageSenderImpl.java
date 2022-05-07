package com.example.testing_students;

import org.springframework.stereotype.Component;

@Component
public class MessageSenderImpl implements MessageSender {


    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
