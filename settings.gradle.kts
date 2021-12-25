pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "MultiModuleKMM"
include(":android")
include(":android:app")
include(":android:feature")

include(":kmm")
include(":kmm:core")
include(":kmm:feature")
include(":kmm:shared")
include(":kmm:constants")
include(":kmm:feature:feature-datasource")