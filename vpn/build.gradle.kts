plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}


android {

    namespace = "com.vpn.fovix.vpn"

    compileSdk = 34


    defaultConfig {

        minSdk = 26

    }


    sourceSets {

        getByName("main") {

            jniLibs.srcDirs(
                "src/main/jniLibs"
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

}