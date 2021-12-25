import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
plugins {
    kotlin(KotlinPlugins.multiplatform)
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

    sourceSets {
        val commonMain by getting {
            // Add feature_datasource project dependencies here
            dependencies {
                implementation(project(Modules.constants))
                implementation(Ktor.core)
                implementation(Ktor.clientSerialization)
                implementation(SQLDelight.runtime)

            }
        }
        val commonTest by getting {
            // Add feature_datasource testing dependencies here
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
}
// TODO: Setup SQL Delight
// sqldelight{
//    database("Your database name"){
//        packageName="${Application.packageRoot}.datasource.cache"
//        sourceFolders=listOf("sqldelight")
//    }
//}
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
}