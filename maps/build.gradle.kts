plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.gaelmarhic.quadrant")
}
android {
    namespace = "com.fadhil.storyappexpert.maps"
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
    implementation(project(":core"))

    /** jetpack */
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.activity.ktx)
    implementation(libs.constraintlayout)
    implementation(libs.cardview)
    implementation(libs.livedata)
    implementation(libs.viewmodel)
    implementation(libs.preference)
    implementation(libs.material)
    implementation(libs.androidx.activity)

    /** testing */
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    //special testing
    testImplementation(libs.androidx.core.testing) // InstantTaskExecutorRule
    testImplementation(libs.kotlinx.coroutines.test) //TestCoroutineDispatcher

    /** dependency injection */
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.gmaps)
}