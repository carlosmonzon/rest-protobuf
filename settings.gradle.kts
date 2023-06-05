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

rootProject.name = "rest-protobuf"
include(":androidApp")
include(":shared")
include(":backend")
include(":apimodels")
