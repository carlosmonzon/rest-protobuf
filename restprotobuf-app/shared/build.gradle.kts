import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialisation)
    alias(libs.plugins.moko.kswift)
    alias(libs.plugins.touchlab.kmmbridge)
    id("maven-publish")
    id("com.android.library")
}

val libraryName = "DataKit"

kotlin {
    androidTarget ()
    jvmToolchain(17)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = libraryName
            // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#export-dependencies-to-binaries
            // this exports apimodels module directly to swift framework. api is required in commonMain
            export(libs.cmonzon.platform.common)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.cmonzon.platform.common)
                //bundles
                implementation(libs.bundles.ktor.common)
                // extras
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.napier)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
                implementation(libs.slf4j)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.cmonzon.rest_protobuf"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}

addGithubPackagesRepository()
// https://kmmbridge.touchlab.co/docs/DEFAULT_GITHUB_FLOW/
kmmbridge {
    frameworkName.set(libraryName)
    mavenPublishArtifacts()
    githubReleaseVersions()
    spm()
    versionPrefix.set("0.1")
}

// https://github.com/icerockdev/moko-kswift
kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
    includeLibrary("shared")
    iosDeploymentTarget.set("14.1")
}

// Register a task per build type: ie: Debug, Release which copies the XCFramework
// from the shared build folder to the iOS project
NativeBuildType.DEFAULT_BUILD_TYPES.forEach { buildType ->
    val buildTypeName = buildType.name.lowercase()
    val buildTypeCapitalised = buildType.name.lowercase().capitalized()
    val iOSDir = project.projectDir.parent.plus("/iosApp/frameworks/${buildTypeName}/")
    tasks.register<Copy>("copy${buildTypeCapitalised}${libraryName}XCFramework") {
        group = "Publishing"
        description = "Copy $libraryName(${buildTypeCapitalised}) iOs framework to the iOS project"
        dependsOn("assemble${libraryName}${buildTypeCapitalised}XCFramework")
        from(layout.buildDirectory.dir("XCFrameworks/${buildTypeName}/"))
        into(iOSDir)
        doFirst {
            println("${buildTypeName}.xcframework(${buildTypeName}) successfully written out to: $iOSDir")
        }
    }
}