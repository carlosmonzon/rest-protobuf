import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.dsl)
    alias(libs.plugins.kotlin.multiplatform) apply false
}

// any plugin required
//gradlePlugin {
//    plugins.register("versionCatalogPlugin") {
//        id = "version-catalog-plugin"
//        implementationClass = "com.cmonzon.VersionCatalogPlugin"
//    }
//}

group = "com.cmonzon"
version = "1.0-SNAPSHOT"