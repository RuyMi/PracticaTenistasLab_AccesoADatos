import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
    kotlin("plugin.serialization") version "1.7.10"
}

group = "es.ar"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Para serializar JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    // Para trabajar con Exposed
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    // Para base de datos H2
    implementation("com.h2database:h2:2.1.214")
    //Para hacer el logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
    implementation("ch.qos.logback:logback-classic:1.4.4")
    // Para manejar las fechas
    implementation("org.jetbrains.exposed:exposed-java-time:0.40.1")
    // Para manejar un pool de conexions mega rápido con HikariCP
    implementation("com.zaxxer:HikariCP:5.0.1")

    implementation("com.google.guava:guava:31.1-jre")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}