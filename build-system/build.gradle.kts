import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.dsl)
    alias(libs.plugins.kotlin.multiplatform) apply false
}

// any plugin required
//gradlePlugin {
//    plugins.register("example-plugin") {
//        id = "example-plugin"
//        implementationClass = "org.example.ExamplePlugin"
//    }
//}

group = "com.monzon"
version = "1.0-SNAPSHOT"