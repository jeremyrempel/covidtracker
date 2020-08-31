object Versions {
    const val min_sdk = 24
    const val target_sdk = 30
    const val compile_sdk = 30

    const val kotlin = "1.4.0"
    const val compose = "0.1.0-dev17"
    const val androidx_test = "1.2.0"
    const val androidx_test_ext = "1.1.1"
    const val android_gradle_plugin = "4.2.0-alpha08"
    const val espresso = "3.2.0"
    const val junit = "4.13"
    const val sqlDelight = "1.4.0"
    const val ktor = "1.3.2-1.4.0-rc"
    const val coroutines = "1.3.5-native-mt"
    const val serialization = "1.0-rc"
    const val datetime = "0.1.0"
    const val lifecycle = "2.1.0"
    const val ktlint_gradle_plugin = "9.3.0"
    const val ktlint = "0.38.1"
    const val robolectric = "4.3.1"
    const val jdkDesugar = "1.0.10"
}

object Deps {
    const val app_compat = "androidx.appcompat:appcompat:1.2.0"
    const val material = "com.google.android.material:material:1.2.0"
    const val core_ktx = "androidx.core:core-ktx:1.3.1"
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val junit = "junit:junit:${Versions.junit}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val kotlindate = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.datetime}"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:${Versions.jdkDesugar}"

    object AndroidXTest {
        val core = "androidx.test:core:${Versions.androidx_test}"
        val junit = "androidx.test.ext:junit:${Versions.androidx_test_ext}"
        val runner = "androidx.test:runner:${Versions.androidx_test}"
        val rules = "androidx.test:rules:${Versions.androidx_test}"
        val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object KotlinTest {
        val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }

    object Coroutines {
        const val common =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
        const val jdk = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val native =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object SqlDelight {
        val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:${Versions.sqlDelight}"
        val driverIos = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object Ktor {
        const val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val commonJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val androidCore = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val iosCore = "io.ktor:ktor-client-core-native:${Versions.ktor}"
        const val commonSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    }
}
