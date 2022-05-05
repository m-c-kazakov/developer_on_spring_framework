package com.example.testing_students;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestingStudentsApplication {

	@SneakyThrows
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TestingStudentsApplication.class);
		UserInterfaceImpl bean = context.getBean(UserInterfaceImpl.class);
		bean.execute();
	}

}
