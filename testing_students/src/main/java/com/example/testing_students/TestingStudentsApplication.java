package com.example.testing_students;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.File;
import java.util.Scanner;
@SpringBootApplication
public class TestingStudentsApplication {

	@SneakyThrows
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TestingStudentsApplication.class);
		UserInterface bean = context.getBean(UserInterface.class);
		bean.execute();
	}

}
