package com.example.testing_students;

import com.example.testing_students.dao.FileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class FileReaderTest {

    public static final String FILE_NAME = "questions";

    @Test
    @DisplayName("Проверка вопросов-ответов на английском")
    void read$EN() {
        String tag = "en_US";

        FileReader fileReader = new FileReader();
        Map<String, String> read = fileReader.read(FILE_NAME+"_"+tag);


        assertThat(read)
                .isNotEmpty()
                .contains(
                        entry("Question_1", "Answer_1"),
                        entry("Question_2", "Answer_2"),
                        entry("Question_3", "Answer_3"),
                        entry("Question_4", "Answer_4"),
                        entry("Question_5", "Answer_5")
                );
    }

    @Test
    @DisplayName("Проверка вопросов-ответов на русском")
    void read$RUS() {
        String tag = "ru_RU";

        FileReader fileReader = new FileReader();
        Map<String, String> read = fileReader.read(FILE_NAME+"_"+tag);

        assertThat(read)
                .isNotEmpty()
                .contains(
                        entry("Вопрос_1", "Ответ_1"),
                        entry("Вопрос_2", "Ответ_2"),
                        entry("Вопрос_3", "Ответ_3"),
                        entry("Вопрос_4", "Ответ_4"),
                        entry("Вопрос_5", "Ответ_5")
                );
    }
}