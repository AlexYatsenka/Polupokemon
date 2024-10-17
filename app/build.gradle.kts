plugins {
    id("com.apollographql.apollo")
    kotlin("kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.alexyatsenka.polupokemon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.alexyatsenka.polupokemon"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    buildFeatures {
        viewBinding = true
    }
    apollo {
        service("service") {
            packageName.set("com.alexyatsenka.polupokemon")
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Dagger 2
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    androidTestImplementation(libs.androidx.navigation.testing)

    // Apollo
    implementation(libs.apollo.runtime)
    implementation(libs.logging.interceptor) // Okhttp logging interceptor

    // Glide
    implementation(libs.glide)
    implementation(libs.glide.recyclerview) // Glide recycler preloader

    // Palette
    implementation(libs.androidx.palette)

    // Base
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    kapt(libs.androidx.lifecycle.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}