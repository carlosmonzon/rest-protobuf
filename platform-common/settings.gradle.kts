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

rootProject.name = "platform-common"

includeBuild("../build-system")
