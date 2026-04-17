plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.foodkart"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.foodkart"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("com.github.bumptech.glide:glide:4.16.0")
    
    // Razorpay
    implementation(libs.razorpay.checkout)

    // Room components
    implementation(libs.room.runtime)
    // annotationProcessor(libs.room.compiler) // For Java
    // If using Kotlin (which you seem to have in dependencies), use ksp or kapt
    // but since this is a Java project mostly, annotationProcessor is fine.
    annotationProcessor(libs.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}