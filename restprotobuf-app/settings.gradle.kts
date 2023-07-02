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
    }
}

rootProject.name = "rest-protobuf-app"
includeBuild("../build-system")
includeBuild("../platform-common")
include(":androidApp")
include(":shared")
