plugins {
    kotlin("jvm") version "1.9.20"
}

tasks.withType(Wrapper::class) {
    gradleVersion = "8.4"
}

group = "com.example.testng"
version = "1.0-SNAPSHOT"

val allureVersion = "2.24.0"
val aspectJVersion = "1.9.20.1"
val kotlinVersion = "1.9.20"

kotlin {
    jvmToolchain(21)
}

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

tasks.test {
    useTestNG()
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
}

dependencies {
    agent("org.aspectj:aspectjweaver:$aspectJVersion")
    
    testImplementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("org.jetbrains.kotlin:kotlin-test-testng")

    testImplementation("org.testng:testng:7.8.0")
    
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-testng")

    testImplementation("org.slf4j:slf4j-simple:2.0.9")
}

repositories {
    mavenCentral()
}
