import br.com.firstsoft.historiconotificacoes.buildSrc.Versions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha13")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.33-beta")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
    }
}

plugins {
    id("com.diffplug.spotless") version "5.10.0"
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint(Versions.ktlint)
//            licenseHeaderFile rootProject.file('spotless/copyright.kt')
        }
    }
    tasks
        .filterIsInstance<KotlinCompile>()
        .forEach { it ->
            it.configure<KotlinCompile> {
                kotlinOptions {
                    allWarningsAsErrors = true

                    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"

                    // Enable experimental coroutines APIs, including Flow
                    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
                    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.FlowPreview"
                    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.Experimental"

                    jvmTarget = "1.8"
                }
            }
        }
}