package br.com.firstsoft.historiconotificacoes.buildSrc

object Versions {
    const val kotlin = "1.4.31"
    const val ktlint = "0.40.0"
    const val appCompanist = "0.7.0"
    const val coroutines = "1.4.2"
    const val junit = "4.13"
    const val compose = "1.0.0-beta03"
    const val room = "2.3.0-rc01"
    const val androidTest = "1.2.0"
    const val hilt = "2.33-beta"
    const val paging = "3.0.0-beta02"
}

object Libs {

    object Util {
        const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.0.9"
        const val gson = "com.google.code.gson:gson:2.8.5"
    }

    object AppCompanist {
        const val coil = "com.google.accompanist:accompanist-coil:${Versions.appCompanist}"
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.appCompanist}"
        const val pager = "com.google.accompanist:accompanist-pager:${Versions.appCompanist}"
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
        const val activity = "androidx.activity:activity-compose:1.3.0-alpha05"
        const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha03"
        const val navigation = "androidx.navigation:navigation-compose:1.0.0-alpha09"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    }

    object Lifecycle {
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha03"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-beta01"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime:${Versions.paging}"
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha08"
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
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha01"
    }
}
