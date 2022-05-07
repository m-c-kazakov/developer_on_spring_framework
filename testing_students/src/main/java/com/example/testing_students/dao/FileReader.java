package com.example.testing_students.dao;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FileReader {

    @SneakyThrows
    public Map<String, String> read(String fileName) {
        Map<String, String> questionsAndAnswers = new HashMap<>();

        try (Scanner scanner = new Scanner(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName))))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(";");
                questionsAndAnswers.put(split[0], split[1]);
            }
        }

        return questionsAndAnswers;
    }

}
