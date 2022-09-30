plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version 1.7.20
    id("com.android.library")
    id("com.squareup.sqldelight")
}

android {
    compileSdkVersion(Versions.compile_sdk)
    defaultConfig {
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

kotlin {
    android()

    sourceSets["commonMain"].dependencies {
        implementation(Deps.Ktor.commonCore)
        implementation(Deps.Ktor.commonJson)
        implementation(Deps.Ktor.commonLogging)
        implementation(Deps.Coroutines.common)
        implementation(Deps.Ktor.commonSerialization)
        implementation(Deps.kotlindate)
    }

    sourceSets["androidMain"].dependencies {
    }
}

sqldelight {
    database("CovidTrackerDb") {
        packageName = "com.jeremyrempel.covidtracker"
    }
}
