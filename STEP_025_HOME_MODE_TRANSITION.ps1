Write-Host "=========================================="
Write-Host " FOVIX STEP 025 - HOME MODE TRANSITION"
Write-Host "=========================================="


$root = Get-Location

$dir = "$root\app\src\main\java\com\vpn\fovix\app\presentation\home"


New-Item -ItemType Directory -Force -Path $dir | Out-Null


Write-Host "[1/6] Creating HomeModeTransition..."


@"
package com.vpn.fovix.app.presentation.home

import com.vpn.fovix.core.decision.UserMode


data class HomeModeTransition(

    val mode: UserMode,

    val showServer: Boolean,

    val showMetrics: Boolean,

    val showExpert: Boolean

)


fun resolveHomeMode(mode: UserMode): HomeModeTransition {

    return when(mode) {


        UserMode.SIMPLE -> {

            HomeModeTransition(

                mode = mode,

                showServer = false,

                showMetrics = false,

                showExpert = false

            )

        }



        UserMode.ADVANCED -> {

            HomeModeTransition(

                mode = mode,

                showServer = true,

                showMetrics = true,

                showExpert = false

            )

        }



        UserMode.EXPERT -> {

            HomeModeTransition(

                mode = mode,

                showServer = true,

                showMetrics = true,

                showExpert = true

            )

        }

    }

}
"@ | Set-Content "$dir\HomeModeTransition.kt"



Write-Host "[2/6] Checking file..."


if (!(Test-Path "$dir\HomeModeTransition.kt")) {

    Write-Host "FILE ERROR"
    exit 1

}



Write-Host "[3/6] Build..."

.\gradlew assembleDebug



if ($LASTEXITCODE -ne 0) {

    Write-Host "BUILD FAILED"
    exit 1

}



Write-Host "[4/6] Creating lock..."

New-Item ".HOME_MODE_TRANSITION_LOCK" -Force | Out-Null



Write-Host "[5/6] Git..."

git add .

git commit -m "STEP 025 home mode transition"

git tag v3.1-home-mode-transition

git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 025 COMPLETE "
Write-Host " TAG: v3.1-home-mode-transition "
Write-Host "=========================================="