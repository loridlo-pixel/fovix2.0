Write-Host "=========================================="
Write-Host " FOVIX STEP 027 - HOME SCREEN ASSEMBLY"
Write-Host "=========================================="


$root = Get-Location

$path = "$root\app\src\main\java\com\vpn\fovix\app\presentation\home"


New-Item -ItemType Directory -Force -Path $path | Out-Null


Write-Host "[1/5] Creating HomeDashboard..."


@'
package com.vpn.fovix.app.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vpn.fovix.app.presentation.home.dashboard.ConnectionOrb


@Composable
fun HomeDashboard(

    connected: Boolean,

    server: String,

    download: Int,

    upload: Int

){

    Column {


        Text(
            text = "FOVIX"
        )


        Spacer(
            modifier = androidx.compose.ui.Modifier.height(32.dp)
        )


        ConnectionOrb(
            connected = connected
        )


        Spacer(
            modifier = androidx.compose.ui.Modifier.height(24.dp)
        )


        ServerCard(
            server = server
        )


        SpeedMetrics(
            download = download,
            upload = upload
        )


        PremiumBadge()

    }

}
'@ | Set-Content "$path\HomeDashboard.kt"



Write-Host "[2/5] Build..."

.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit 1

}



Write-Host "[3/5] Creating lock..."

New-Item ".HOME_SCREEN_ASSEMBLY_LOCK" -Force | Out-Null



Write-Host "[4/5] Git..."

git add .

git commit -m "STEP 027 home screen assembly"

git tag v3.3-home-screen-assembly

git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 027 COMPLETE "
Write-Host " TAG: v3.3-home-screen-assembly "
Write-Host "=========================================="