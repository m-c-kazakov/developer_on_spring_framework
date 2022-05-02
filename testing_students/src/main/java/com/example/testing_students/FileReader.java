package com.example.testing_students;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FileReader {
    // questions
    String fileName;

    public FileReader(@Value("${fileName}") String fileName) {
        this.fileName = fileName;
    }

    @SneakyThrows
    public Map<String, String> read() {
        Map<String, String> questionsAndAnswers = new HashMap<>();

        try (Scanner scanner = new Scanner(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(";");
                questionsAndAnswers.put(split[0], split[1]);
            }
        }

        return questionsAndAnswers;
    }

}
