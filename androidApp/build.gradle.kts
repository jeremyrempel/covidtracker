plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("plugin.serialization") version Versions.kotlin
}

android {
    compileSdkVersion(Versions.compile_sdk)

    defaultConfig {
        applicationId("com.jeremyrempel.covidtracker.android")
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerVersion = Versions.kotlin
        kotlinCompilerExtensionVersion = Versions.compose
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check")
    }
}

dependencies {
    implementation(project(":shared"))

    // jetpack
    implementation("androidx.compose.runtime:runtime:${Versions.compose}")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.foundation:foundation:${Versions.compose}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.ui:ui-tooling:${Versions.compose}")

    // coroutines
    implementation(Deps.Coroutines.jdk)
    implementation(Deps.Coroutines.android)

    // google
    implementation(Deps.core_ktx)
    implementation(Deps.app_compat)
    implementation(Deps.material)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.AndroidXTest.junit)
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
