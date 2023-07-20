
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
    versionCatalogs {
        create("libs") {
            from(files("../build-system/gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "backend"

includeBuild("../build-system")
includeBuild("../platform-common")