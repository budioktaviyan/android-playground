plugins {
  id("com.android.application")
  kotlin("android")
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
}