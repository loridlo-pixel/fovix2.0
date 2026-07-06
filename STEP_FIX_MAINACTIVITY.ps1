Write-Host "FOVIX FIX: aligning MainActivity package..."

# 1. Найти MainActivity
$activity = Get-ChildItem -Recurse -Filter MainActivity.kt | Select-Object -First 1

if ($activity -eq $null) {
    Write-Host "MainActivity NOT FOUND -> creating default one..."

    New-Item -ItemType Directory -Force -Path "app\src\main\java\com\vpn\fovix\app"

    @"
package com.vpn.fovix.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("FOVIX VPN")
        }
    }
}
"@ | Out-File "app\src\main\java\com\vpn\fovix\app\MainActivity.kt" -Encoding UTF8
}
else {
    Write-Host "Found: $($activity.FullName)"

    # 2. переместить в правильный пакет
    New-Item -ItemType Directory -Force -Path "app\src\main\java\com\vpn\fovix\app"

    Move-Item $activity.FullName "app\src\main\java\com\vpn\fovix\app\MainActivity.kt" -Force
}

# 3. пересборка
./gradlew clean assembleDebug