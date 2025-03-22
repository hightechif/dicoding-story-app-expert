plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.kotlin.android)
    id("com.gaelmarhic.quadrant")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("com.google.dagger.hilt.android")
}
android {
    namespace = "com.fadhil.storyappexpert.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":app"))

    /** jetpack */
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.activity.ktx)
    implementation(libs.livedata)
    implementation(libs.viewmodel)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /** navigation */
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    /** dependency injection */
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    /** networking */
    implementation(libs.gson)

    /** supporting lib */
    implementation(libs.timber)
    implementation(libs.circleimageview)
    implementation(libs.glide)
    implementation(libs.compressor)
    implementation(libs.androidx.paging.runtime.ktx)

    implementation(project(":core"))
}