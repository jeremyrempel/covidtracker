object Versions {
    const val min_sdk = 24
    const val target_sdk = 30
    const val compile_sdk = 30

    val kotlin = "1.4.0"
    const val compose = "0.1.0-dev16"
    val androidx_test = "1.2.0"
    val androidx_test_ext = "1.1.1"
    val android_gradle_plugin = "4.2.0-alpha07"
    val junit = "4.13"
    val sqlDelight = "1.4.0"
    val ktor = "1.3.2-1.4.0-rc"
    val coroutines = "1.3.5-native-mt"
    const val serialization = "1.0-M1-1.4.0-rc"
    val lifecycle = "2.1.0"
    val ktlint_gradle_plugin = "9.2.1"
    val robolectric = "4.3.1"
}

object Deps {
    val app_compat_x = "androidx.appcompat:appcompat:1.1.0"
    val material_x = "com.google.android.material:material:1.1.0"
    val core_ktx = "androidx.core:core-ktx:1.2.0"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    val junit = "junit:junit:${Versions.junit}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    object AndroidXTest {
        val core = "androidx.test:core:${Versions.androidx_test}"
        val junit = "androidx.test.ext:junit:${Versions.androidx_test_ext}"
        val runner = "androidx.test:runner:${Versions.androidx_test}"
        val rules = "androidx.test:rules:${Versions.androidx_test}"
    }

    object KotlinTest {
        val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }

    object Coroutines {
        val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
        val jdk = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object SqlDelight {
        val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:${Versions.sqlDelight}"
        val driverIos = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object Ktor {
        val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        val commonJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        val jvmCore = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
        val androidCore = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        val jvmJson = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
        val jvmLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
        val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        val iosCore = "io.ktor:ktor-client-core-native:${Versions.ktor}"
        val iosJson = "io.ktor:ktor-client-json-native:${Versions.ktor}"
        val iosLogging = "io.ktor:ktor-client-logging-native:${Versions.ktor}"
        val commonSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        val androidSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
        val iosSerialization = "io.ktor:ktor-client-serialization-native:${Versions.ktor}"
    }
}