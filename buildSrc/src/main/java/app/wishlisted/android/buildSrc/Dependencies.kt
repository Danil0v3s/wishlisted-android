package app.wishlisted.android.buildSrc

object Versions {
    const val kotlin = "1.4.31"
    const val ktlint = "0.40.0"
    const val coroutines = "1.4.2"
    const val junit = "4.13"
    const val room = "2.3.0-rc01"
    const val androidTest = "1.2.0"
    const val hilt = "2.33-beta"
    const val paging = "3.0.0-beta03"
    const val okhttp = "4.9.1"
    const val retrofit = "2.9.0"
    const val moshi = "1.11.0"
    const val coil = "1.1.1"
}

object Libs {

    object Util {
        const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.0.9"
    }

    object Retrofit {
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val codeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object ExoPlayer {
        const val core = "com.google.android.exoplayer:exoplayer:2.13.2"
    }

    object Kotlin {
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object Android {
        const val annotation = "androidx.annotation:annotation:1.2.0"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val activity = "androidx.activity:activity:1.2.2"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.2"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.3.5"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:2.3.5"
        const val palette = "androidx.palette:palette:1.0.0"
        const val coreKtx = "androidx.core:core-ktx:1.5.0-beta03"
        const val gradle = "com.android.tools.build:gradle:7.0.0-alpha11"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-beta01"
    }

    object Coil {
        const val core = "io.coil-kt:coil:${Versions.coil}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime:${Versions.paging}"
        const val tests = "androidx.paging:paging-common:${Versions.paging}"
    }

    object Testing {
        const val junit = "junit:junit:${Versions.junit}"
    }

    object AndroidTesting {
        const val core = "androidx.test:core:${Versions.androidTest}"
        const val rules = "androidx.test:rules:${Versions.androidTest}"
        const val junitKtx = "androidx.test.ext:junit-ktx:1.1.2-rc01"
        const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
        const val runner = "androidx.test:runner:1.1.0-alpha4"
    }

    object Hilt {
        const val core = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val gradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }
}
