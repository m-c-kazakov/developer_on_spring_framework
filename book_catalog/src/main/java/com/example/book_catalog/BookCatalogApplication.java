package com.example.book_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BookCatalogApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(BookCatalogApplication.class, args);
    }

}
