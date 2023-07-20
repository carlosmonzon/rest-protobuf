package com.cmonzon

import org.gradle.api.Plugin
import org.gradle.api.Project

class AnyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.create("SampleTask") {
            println("Hello there!")
        }
    }
}