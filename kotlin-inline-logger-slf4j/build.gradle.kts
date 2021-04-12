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
    api(project(":kotlin-inline-logger-api"))
    api("org.slf4j:slf4j-api:1.7.30")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.30")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("io.mockk:mockk:1.11.0")
    testImplementation("junit:junit:4.13")
    testImplementation("com.google.testparameterinjector:test-parameter-injector:1.1")
}

tasks.test {
    testLogging {
        events("passed", "skipped", "failed")
    }
}
