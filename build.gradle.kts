plugins {
    id("java")
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    val junitVersion = "5.9.1"
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    val assertjVersion = "3.24.2"
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

tasks.test {
    useJUnitPlatform()
}