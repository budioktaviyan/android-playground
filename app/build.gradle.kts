plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("plugin.serialization") version "1.9.10"
}

android {
  namespace = "id.android.training"
  compileSdk = 34
  buildToolsVersion = "34.0.0"

  defaultConfig {
    applicationId = "id.android.training"
    minSdk = 33
    targetSdk = 34
    versionCode = 1
    versionName = "1.0.0"

    buildFeatures.viewBinding = true
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
}

dependencies {
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.10.0")

  // ReactiveX programming
  implementation("io.reactivex.rxjava3:rxjava:3.1.8")
  implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
  implementation("io.reactivex.rxjava3:rxandroid:3.0.2")

  // Kotlin serialization
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

  // Network dependencies
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
  implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
  implementation("com.squareup.okhttp3:okhttp:4.12.0")
  implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}