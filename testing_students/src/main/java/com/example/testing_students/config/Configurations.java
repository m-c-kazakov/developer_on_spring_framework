package com.example.testing_students.config;

import com.example.testing_students.FileReader;
import com.example.testing_students.MessageGetter;
import com.example.testing_students.MessageSender;
import com.example.testing_students.UserInterfaceImpl;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Map;

@Configuration
public class Configurations {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public UserInterfaceImpl userInterface(@Value("${fileName}") String fileName,
                                           @Value("${language}") String tag,
                                           MessageSender messageSender,
                                           MessageGetter messageGetter,
                                           MessageSource messageSource,
                                           FileReader fileReader) {
        Map<String, String> questionAndAnswerMap = fileReader.read(fileName+"_"+tag);
        return new UserInterfaceImpl(messageSender, messageSource, LocaleUtils.toLocale(tag) , questionAndAnswerMap, messageGetter);
    }
}
