plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {

    namespace = "com.vpn.fovix.app"

    compileSdk = 34


    defaultConfig {

        applicationId = "com.vpn.fovix"

        minSdk = 26

        targetSdk = 34

        versionCode = 1

        versionName = "1.0"

    }


    buildFeatures {

        compose = true

    }


    composeOptions {

        kotlinCompilerExtensionVersion = "1.5.10"

    }


    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_17

        targetCompatibility = JavaVersion.VERSION_17

    }


    kotlinOptions {

        jvmTarget = "17"

    }


    /*
       libbox.so и libsingbox.so
       находятся здесь:

       app/src/main/jniLibs/arm64-v8a/

    */

    sourceSets {

        getByName("main") {

            jniLibs.srcDirs(

                "src/main/jniLibs"

            )

        }

    }


    packaging {

        jniLibs {

            useLegacyPackaging = true

        }

    }

}



dependencies {


    implementation(project(":domain"))

    implementation(project(":vpn"))

    implementation(project(":data"))

    implementation(project(":core"))



    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.8.1"
    )



    implementation(
        platform(
            "androidx.compose:compose-bom:2024.06.00"
        )
    )



    implementation(
        "androidx.compose.ui:ui"
    )


    implementation(
        "androidx.compose.ui:ui-graphics"
    )


    implementation(
        "androidx.compose.ui:ui-tooling-preview"
    )



    implementation(
        "androidx.compose.material3:material3"
    )



    implementation(
        "androidx.activity:activity-compose:1.9.0"
    )



    debugImplementation(
        "androidx.compose.ui:ui-tooling"
    )


    debugImplementation(
        "androidx.compose.ui:ui-test-manifest"
    )

}