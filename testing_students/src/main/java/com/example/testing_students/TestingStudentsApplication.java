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
@ComponentScan
@Configuration
@PropertySource("classpath:application.yml")
public class TestingStudentsApplication {

	@SneakyThrows
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestingStudentsApplication.class);
		UserInterface bean = context.getBean(UserInterface.class);
		bean.execute();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
