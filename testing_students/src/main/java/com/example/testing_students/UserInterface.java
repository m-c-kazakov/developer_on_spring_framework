package com.example.testing_students;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserInterface {

    FileReader fileReader;

    public void execute() {
        Map<String, String> read = fileReader.read();
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is your name?: ");
        String userName = scanner.nextLine();

        for (Entry<String, String> entry : read.entrySet()) {
            System.out.println("Question: " + entry.getKey());

            System.out.print("Your answer option: ");
            String answer = scanner.nextLine();
            System.out.println("Right answer: " + entry.getValue());
        }

        System.out.println("end");

    }
}
