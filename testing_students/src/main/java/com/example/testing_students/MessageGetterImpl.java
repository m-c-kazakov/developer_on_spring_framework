package com.example.testing_students;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MessageGetterImpl implements MessageGetter {

    Scanner scanner = new Scanner(System.in);
    @Override
    public String getMessage() {
        return scanner.nextLine();
    }
}
