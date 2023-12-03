import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.TestExecutable

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqlite)
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sqlite.android.driver)
        }

        desktopMain.dependencies {
            implementation(libs.sqlite.jvm.driver)
            implementation(libs.ktor.client.apache5)

        }

        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.sqlite.common.runtime)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqlite.ios.driver)
        }
    }
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.withType<TestExecutable>().configureEach {
            linkerOpts += "-lsqlite3"
        }
    }

}




android {
    namespace = "com.honya.sss.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}


sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.cache")

        }
        linkSqlite.set(true)
    }
    linkSqlite.set(true)
}