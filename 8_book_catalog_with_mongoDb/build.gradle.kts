import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.otus.homework"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.shell:spring-shell-starter:2.0.1.RELEASE")
    implementation("org.jline:jline-reader:3.21.0")

    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("com.github.cloudyrock.mongock:mongock-spring-v5:4.3.8")
    implementation("com.github.cloudyrock.mongock:mongodb-springdata-v3-driver:4.3.8")

    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.apache.logging.log4j:log4j-core:2.18.0")





    testImplementation("org.testcontainers:mongodb:1.17.3")


//    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:3.4.6")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

//tasks.bootRun {
//    standardInput = System.`in`
//}

tasks.withType<Test> {
    useJUnitPlatform()
}
