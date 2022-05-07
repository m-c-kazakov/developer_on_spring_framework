package com.example.testing_students.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
public class MessageGetterImpl implements MessageGetter {

    Scanner scanner = new Scanner(System.in);
    @Override
    public String getMessage() {
        return scanner.nextLine();
    }
}
