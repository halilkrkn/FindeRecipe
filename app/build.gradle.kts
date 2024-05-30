plugins {
    kotlin("kapt")
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id ("com.google.devtools.ksp")
//    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.halilkrkn.finderecipe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.halilkrkn.finderecipe"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Compose dependencies
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coil Compose
    implementation(libs.coil.compose)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //DataStore
    implementation (libs.androidx.datastore.preferences)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.adapter.rxjava2)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Lottie
    implementation(libs.lottie.compose)

    // Firebase
//    implementation(libs.firebase.auth)
//    implementation(libs.firebase.firestore.ktx)

    // Swipe Refresh
    implementation(libs.accompanist.swiperefresh)

    // Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation (libs.androidx.room.runtime)
    implementation(libs.androidx.room.paging)


    // Paging
    implementation (libs.androidx.paging.runtime.ktx)
    implementation (libs.androidx.paging.compose)

    // Splash API
    implementation (libs.androidx.core.splashscreen)

    // ONE TAP GOOGLE SIGN IN WITH FIREBASE
//    implementation ("com.google.firebase:firebase-auth:22.2.0")
//    implementation platform("com.google.firebase:firebase-bom:32.4.0")
//    implementation("com.google.firebase:firebase-auth-ktx")
//    implementation("com.google.android.gms:play-services-auth:20.4.0")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Local unit tests
    testImplementation (libs.androidx.core)
    testImplementation (libs.junit)
    testImplementation (libs.androidx.core.testing)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.truth)
    testImplementation (libs.mockwebserver)
    testImplementation (libs.mockk)
    debugImplementation (libs.ui.test.manifest)

    // Instrumentation tests
    androidTestImplementation (libs.hilt.android.testing)
    kaptAndroidTest (libs.hilt.android.compiler.v237)
    androidTestImplementation (libs.junit)
    androidTestImplementation (libs.dexmaker.mockito)
    androidTestImplementation (libs.kotlinx.coroutines.test)
    androidTestImplementation (libs.androidx.core.testing.v210)
    androidTestImplementation (libs.truth)
    androidTestImplementation (libs.androidx.junit.v113)
    androidTestImplementation (libs.core.ktx)
    androidTestImplementation (libs.mockwebserver.v491)
    androidTestImplementation (libs.mockk.android)
    androidTestImplementation (libs.androidx.runner)
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}