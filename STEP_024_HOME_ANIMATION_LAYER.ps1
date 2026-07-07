Write-Host "=========================================="
Write-Host " FOVIX STEP 024 - HOME ANIMATION LAYER"
Write-Host "=========================================="


$root = Get-Location


$dir = "$root\app\src\main\java\com\vpn\fovix\app\presentation\home"


New-Item -ItemType Directory -Force -Path $dir | Out-Null


Write-Host "[1/5] Creating HomeAnimation..."


@"
package com.vpn.fovix.app.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable


@Composable
fun HomeAnimatedSection(
    visible: Boolean,
    content: @Composable () -> Unit
) {

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut()
    ) {

        content()

    }

}
"@ | Set-Content "$dir\HomeAnimation.kt"



Write-Host "[2/5] Checking file..."

if (!(Test-Path "$dir\HomeAnimation.kt")) {

    Write-Host "FILE ERROR"
    exit 1

}


Write-Host "[3/5] Build..."

.\gradlew assembleDebug


if ($LASTEXITCODE -ne 0) {

    Write-Host "BUILD FAILED"
    exit 1

}


Write-Host "[4/5] Creating lock..."

New-Item ".HOME_ANIMATION_LAYER_LOCK" -Force | Out-Null



Write-Host "[5/5] Git..."

git add .

git commit -m "STEP 024 home animation layer"

git tag v3.0-home-animation

git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 024 COMPLETE "
Write-Host " TAG: v3.0-home-animation "
Write-Host "=========================================="