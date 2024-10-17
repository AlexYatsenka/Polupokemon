plugins {
    id("com.apollographql.apollo") version "4.0.1" apply false
    kotlin("kapt") version "1.9.0" apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}