// Used to add repositories to both the plugin and dependency management configurations
fun RepositoryHandler.configureCommonRepositories() {
    google()
    mavenCentral()
}

pluginManagement.repositories {
    gradlePluginPortal()
    configureCommonRepositories()
}

dependencyResolutionManagement {
    repositories {
        configureCommonRepositories()
    }
    versionCatalogs {
        create("libs") {
            from(files("../build-system/gradle/libs.versions.toml"))
        }
    }
}

includeBuild("../build-system")
includeBuild("../platform-common")
includeBuild("../restprotobuf-app")
includeBuild("../backend")