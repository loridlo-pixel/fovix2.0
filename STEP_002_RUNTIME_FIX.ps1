Write-Host "FOVIX STEP 2 - RUNTIME FIX" -ForegroundColor Cyan

# 1. clean
./gradlew clean

# 2. найти MainActivity
$activity = Get-ChildItem -Recurse -Filter MainActivity.kt | Select-Object -First 1

if ($activity -ne $null) {
    Write-Host "Found MainActivity: $($activity.FullName)" -ForegroundColor Green

    # читаем package
    $content = Get-Content $activity.FullName -Raw

    if ($content -notmatch "package com.vpn.fovix.app") {
        Write-Host "Fixing package..." -ForegroundColor Yellow

        $content = $content -replace "package .+", "package com.vpn.fovix.app"
        Set-Content $activity.FullName $content
    }

    # правильная папка
    New-Item -ItemType Directory -Force -Path "app\src\main\java\com\vpn\fovix\app"

    Move-Item $activity.FullName "app\src\main\java\com\vpn\fovix\app\MainActivity.kt" -Force
}
else {
    Write-Host "Creating MainActivity..." -ForegroundColor Yellow

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
            Text("FOVIX VPN STARTED")
        }
    }
}
"@ | Out-File "app\src\main\java\com\vpn\fovix\app\MainActivity.kt" -Encoding UTF8
}

# 3. build
./gradlew assembleDebug

# 4. install
./gradlew installDebug