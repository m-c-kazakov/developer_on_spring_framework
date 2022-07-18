package com.otus.homework.book_catalog_with_mongodb.controller.integration.annotation

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("test")
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class IT()
