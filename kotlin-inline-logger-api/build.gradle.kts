plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.30")
}

config {
    coverage {
        jacoco {
            excludes = setOf("**/LoggerKt.*")
        }
    }
}
