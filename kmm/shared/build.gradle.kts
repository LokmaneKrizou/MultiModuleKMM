import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
plugins {
    kotlin(KotlinPlugins.multiplatform)
    kotlin(KotlinPlugins.cocoapods)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(Plugins.androidLibrary)
    id(Plugins.sqlDelight)
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

    cocoapods {
        summary = IOS.summary
        homepage = IOS.homepage
        ios.deploymentTarget = IOS.deploymentTarget
        frameworkName = IOS.frameworkName
        podfile = project.file(IOS.podfilePath)
    }
    sourceSets {
        val commonMain by getting {
            // Add core project dependencies here
            dependencies {
                api(project(Modules.core))
                api(project(Modules.feature_datasource))
                implementation(Kotlinx.datetime)
                implementation(Ktor.core)
                implementation(Ktor.clientSerialization)
                implementation(SQLDelight.runtime)

            }
        }
        val commonTest by getting {
            // Add core testing dependencies here
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting{
            //add android dependencies here
            dependencies{
                implementation(Ktor.android)
                implementation(SQLDelight.androidDriver)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            //add iOS dependencies here
            dependencies{
                implementation(Ktor.ios)
                implementation(SQLDelight.nativeDriver)
            }
        }
        val iosTest by getting
    }
    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            export(project(Modules.core))
            export(project(Modules.feature_datasource))
            transitiveExport = true
        }
    }
}

android {
    compileSdk = Application.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    configurations {
        create("androidTestAPi")
        create("androidTestDebugAPi")
        create("androidTestReleaseAPi")
        create("testAPi")
        create("testDebugAPi")
        create("testReleaseAPi")
    }
}