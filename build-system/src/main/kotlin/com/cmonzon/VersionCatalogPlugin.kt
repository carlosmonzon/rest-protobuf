package com.cmonzon

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.configurationcache.extensions.fileSystemEntryType
import org.gradle.kotlin.dsl.*
import org.gradle.kotlin.dsl.support.expectedKotlinDslPluginsVersion
import kotlin.io.path.fileVisitor

open class VersionCatalogPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {


        settings.dependencyResolutionManagement.apply {
            repositories {
                google()
                mavenCentral()
            }
            versionCatalogs {
                create("libs") {
                    from(expectedKotlinDslPluginsVersion.delegateClosureOf<KotlinScript> {
                        files("../gradle/libs.versions.toml")
                    })
                }
            }
        }
        println("MY HELLO WORLD")
    }
}