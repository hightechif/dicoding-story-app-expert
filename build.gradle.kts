// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.navigation.safeargs)
        classpath(libs.navigation.quadrant)
    }
}

plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.mapsplatform) apply false
    alias(libs.plugins.android.dynamic.feature) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}