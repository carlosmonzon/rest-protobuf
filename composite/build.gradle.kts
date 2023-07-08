buildscript {
    dependencies {
        classpath(libs.build.androidgradleplugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "org.cmonzon.composite"
version = "1.0-SNAPSHOT"

// https://docs.gradle.org/current/userguide/composite_builds.html#separate_composite
tasks.register("compositeChecks") {
    dependsOn(
        gradle.includedBuild("platform-common").task(":build"),
        gradle.includedBuild("backend").task(":build"),
        gradle.includedBuild("restprotobuf-app").task(":shared:build"),
        gradle.includedBuild("restprotobuf-app").task(":androidApp:assembleDebug"),
    )
}