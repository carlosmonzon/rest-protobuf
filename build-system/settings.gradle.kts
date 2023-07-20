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
        create("platform") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-system"
