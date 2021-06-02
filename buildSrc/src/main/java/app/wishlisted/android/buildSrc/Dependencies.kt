package app.wishlisted.android.buildSrc

object Versions {
    const val kotlin = "1.4.32"
    const val ktlint = "0.41.0"
    const val accompanist = "0.10.0"
    const val coroutines = "1.4.2"
    const val junit = "4.13"
    const val compose = "1.0.0-beta08"
    const val room = "2.3.0"
    const val androidTest = "1.2.0"
    const val hilt = "2.35.1"
    const val paging = "3.0.0-beta03"
    const val okhttp = "4.9.1"
    const val retrofit = "2.9.0"
    const val moshi = "1.11.0"
}

object Libs {

    object Util {
        const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.0.9"
    }

    object Accompanist {
        const val coil = "com.google.accompanist:accompanist-coil:${Versions.accompanist}"
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
        const val pager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
        const val flow = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
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
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object Android {
        const val annotation = "androidx.annotation:annotation:1.2.0"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val palette = "androidx.palette:palette:1.0.0"
        const val coreKtx = "androidx.core:core-ktx:1.5.0-beta03"
        const val gradle = "com.android.tools.build:gradle:7.0.0-alpha11"
    }

    object Compose {
        const val activity = "androidx.activity:activity-compose:1.3.0-alpha08"
        const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha07"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha01"

        const val animation = "androidx.compose.animation:animation:${Versions.compose}"
        const val animationCore = "androidx.compose.animation:animation-core:${Versions.compose}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val materialIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    }

    object Lifecycle {
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha05"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime:${Versions.paging}"
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha09"
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
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha02"
    }
}
