Write-Host "=========================================="
Write-Host " FOVIX STEP 009 - DI CONTAINER"
Write-Host "=========================================="

$root = Get-Location


Write-Host "[1/8] Creating core module..."

$corePath = "$root\core\src\main\java\com\vpn\fovix\core\di"

New-Item -ItemType Directory -Force -Path $corePath | Out-Null


Write-Host "[2/8] Creating AppContainer..."


@"
package com.vpn.fovix.core.di

import com.vpn.fovix.vpn.VpnEngine
import com.vpn.fovix.data.repository.VpnRepository


class AppContainer {


    private val vpnEngine = VpnEngine()


    val vpnRepository =
        VpnRepository(vpnEngine)

}
"@ | Set-Content "$corePath\AppContainer.kt"



Write-Host "[3/8] Creating core build file..."


@"
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}


android {

    namespace = "com.vpn.fovix.core"

    compileSdk = 34


    defaultConfig {
        minSdk = 26
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

    implementation(project(":vpn"))
    implementation(project(":data"))

}
"@ | Set-Content "$root\core\build.gradle.kts"



Write-Host "[4/8] Updating settings.gradle..."


$settings = Get-Content "$root\settings.gradle.kts"

if ($settings -notmatch 'include\(":core"\)') {

    Add-Content "$root\settings.gradle.kts" "`ninclude(`":core`")"

}



Write-Host "[5/8] Updating data dependencies..."


$dataGradle = "$root\data\build.gradle.kts"

$content = Get-Content $dataGradle -Raw


if ($content -notmatch 'project\(":vpn"\)') {

$content += @"


dependencies {

    implementation(project(":vpn"))
    implementation(project(":domain"))

}

"@

Set-Content $dataGradle $content

}



Write-Host "[6/8] Build check..."

.\gradlew assembleDebug


if ($LASTEXITCODE -ne 0) {

Write-Host "BUILD FAILED"
exit 1

}



Write-Host "[7/8] Installing APK..."

.\gradlew installDebug



New-Item ".DI_CONTAINER_LOCK" -Force | Out-Null



Write-Host "[8/8] Git commit..."


git add .

git commit -m "STEP 009 dependency injection container"

git tag v1.9-di-container

git push origin main --tags


Write-Host ""
Write-Host "=========================================="
Write-Host " FOVIX STEP 009 COMPLETED "
Write-Host " TAG: v1.9-di-container "
Write-Host "=========================================="
