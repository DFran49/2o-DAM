plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0" // plugin de javafx para Gradle
}

application {
    mainClass="fcm.Main" // aquí ponemos la clase (con su paquete) que tiene el main
}

javafx {
    modules("javafx.controls", "javafx.fxml") // módulos que usaremos
    version="23" // versión de JavaFX a utilizar
}

group = "fcm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}