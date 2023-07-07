plugins {
    alias(libs.plugins.ktor.plugin)
    alias(libs.plugins.kotlin.serialisation)
    alias(libs.plugins.kotlin.jvm)
}

group = "com.cmonzon"
version = "0.0.1"
application {
    mainClass.set("com.cmonzon.ApplicationKt")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.cmonzon.platform.common)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.content.negociation)
    implementation(libs.ktor.serialisation.json)
    implementation(libs.ktor.serialisation.protobuf)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}