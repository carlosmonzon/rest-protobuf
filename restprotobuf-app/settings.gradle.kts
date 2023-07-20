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
    versionCatalogs {
        create("libs") {
            from(files("../build-system/gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "rest-protobuf-app"
includeBuild("../build-system")
includeBuild("../platform-common")
include(":androidApp")
include(":shared")
