import br.com.firstsoft.historiconotificacoes.buildSrc.Libs

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

        javaCompileOptions {
            annotationProcessorOptions {
                arguments.put("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Libs.Kotlin.stdLib)

    implementation(Libs.Room.runtime)
    implementation(Libs.Room.ktx)
    kapt(Libs.Room.compiler)

    implementation(Libs.Paging.runtime)

    implementation(Libs.Hilt.core)
    kapt(Libs.Hilt.compiler)

    implementation(Libs.Android.annotation)

    implementation(Libs.Util.gson)
}