import app.wishlisted.android.buildSrc.Libs

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Libs.Kotlin.stdLib)

    implementation(Libs.Coroutines.core)

    implementation(Libs.Paging.runtime)

    implementation(Libs.Hilt.core)
    kapt(Libs.Hilt.compiler)

    testImplementation(Libs.Testing.junit)
}