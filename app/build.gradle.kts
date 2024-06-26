plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.organization.directory"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.organization.directory"
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
            var debuggable = false
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
    kotlinOptions {
        jvmTarget = "1.8"
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
    hilt {
        enableAggregatingTask = true
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Dependency Injection packages
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    // Data binding
    implementation("androidx.databinding:databinding-runtime:8.2.2")
    // Navigation
    //implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Swipe to refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    // Recyclerview
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    // Cardview
    implementation("androidx.cardview:cardview:1.0.0")

    // Network packages
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Image library
    implementation("com.github.bumptech.glide:glide:4.8.0") {
        exclude (group= "com.android.support")
    }

    // Import the Compose BOM
    implementation (platform("androidx.compose:compose-bom:2023.08.00"))

    // For telephony utils
    implementation ("com.googlecode.libphonenumber:libphonenumber:7.2.2")

    implementation("androidx.room:room-ktx:2.6.1")
    // KSP (Kotlin Symbol Processing)
    // s a Kotlin-first alternative to kapt. KSP analyzes Kotlin code directly, which is up to 2x faster.
    // It also has a better understanding of Kotlin's language constructs.
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")

    // relays
    implementation("com.jakewharton.rxrelay3:rxrelay:3.0.1")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")

    // tests
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.10.0")
    testImplementation("app.cash.turbine:turbine:0.11.0")
    testImplementation("org.mockito:mockito-core:3.4.6")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:runner:1.6.0-beta01")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
}