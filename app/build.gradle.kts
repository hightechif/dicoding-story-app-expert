import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.gaelmarhic.quadrant")
}

android {
    namespace = "com.fadhil.storyappexpert"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fadhil.storyappexpert"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Load credentials.
        val p = Properties()
        p.load(project.rootProject.file("local.properties").reader())

        // Share the key with your `AndroidManifest.xml`
        manifestPlaceholders.put("googleMapsApiKey", p.getProperty("GOOGLE_MAPS_API_KEY") ?: "null")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    dynamicFeatures += setOf(":favorite")
}

dependencies {

    /** jetpack */
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.activity.ktx)
    implementation(libs.constraintlayout)
    implementation(libs.cardview)
    implementation(libs.livedata)
    implementation(libs.viewmodel)
    implementation(libs.preference)
    implementation(libs.feature.delivery)
    implementation(libs.feature.delivery.ktx)

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

    /** material design */
    implementation(libs.legacy)
    implementation(libs.recyclerview)
    implementation(libs.material)

    /** navigation */
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.gmaps)

    /** coroutines */
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    /** local data persistence */
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    implementation(libs.datastore.preferences)

    /** networking */
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp3.logging.interceptor)

    /** dependency injection */
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    /** supporting lib */
    implementation(libs.timber)
    implementation(libs.circleimageview)
    implementation(libs.glide)
    implementation(libs.compressor)
    implementation(libs.androidx.paging.runtime.ktx)

    implementation(project(":core"))

}
