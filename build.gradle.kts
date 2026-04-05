import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("com.android.compose.screenshot") version "0.0.1-alpha13"
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "io.github.magisk317.uikit"
    compileSdk = 37
    experimentalProperties["android.experimental.enableScreenshotTest"] = true

    defaultConfig {
        minSdk = 23
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
        }
    }

    testOptions {
        screenshotTests {
            imageDifferenceThreshold = 0.0001f
        }
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2026.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3:1.5.0-alpha15")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.core:core-ktx:1.18.0")
    implementation("dev.chrisbanes.haze:haze-android:1.7.2")
    implementation("top.yukonga.miuix.kmp:miuix:0.8.8")

    debugImplementation("androidx.compose.ui:ui-tooling")
    screenshotTestImplementation("com.android.tools.screenshot:screenshot-validation-api:0.0.1-alpha13")
    screenshotTestImplementation("androidx.compose.ui:ui-tooling")
}
