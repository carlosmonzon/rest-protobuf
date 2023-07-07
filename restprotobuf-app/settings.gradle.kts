pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://androidx.dev/storage/compose-compiler/repository")
    }
}

rootProject.name = "rest-protobuf-app"
includeBuild("../build-system")
includeBuild("../platform-common")
include(":androidApp")
include(":shared")
