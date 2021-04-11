import app.wishlisted.android.buildSrc.Libs
import app.wishlisted.android.buildSrc.Versions

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

hilt {
    enableExperimentalClasspathAggregation = true
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "app.wishlisted.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storeFile = file("$rootDir/debug.keystore")
            storePassword = "android"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Libs.Kotlin.stdLib)

    implementation(Libs.Android.coreKtx)
    implementation(Libs.Android.palette)
    implementation(Libs.Android.activity)
    implementation(Libs.Android.fragment)
    implementation(Libs.Android.appCompat)
    implementation(Libs.Android.constraintLayout)
    implementation(Libs.Android.navigationFragment)
    implementation(Libs.Android.navigationUi)

    implementation(Libs.Coil.core)

    implementation(Libs.Lifecycle.viewModel)

    implementation(Libs.Paging.runtime)

    implementation(Libs.ExoPlayer.core)

    implementation(Libs.Coroutines.android)
    implementation(Libs.Coroutines.core)

    implementation(Libs.Hilt.core)
    kapt(Libs.Hilt.compiler)

    coreLibraryDesugaring(Libs.Util.jdkDesugar)

    testImplementation(Libs.Testing.junit)
    androidTestImplementation(Libs.AndroidTesting.espresso)
    androidTestImplementation(Libs.AndroidTesting.runner)
}