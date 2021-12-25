import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(KotlinPlugins.multiplatform)
    id(Plugins.androidLibrary)
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}
}
android {
    compileSdk = Application.compileSdk
    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
    }
}