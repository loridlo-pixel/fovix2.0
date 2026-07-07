Write-Host "=========================================="
Write-Host " FOVIX STEP 016 - MODE CONTROLLER"
Write-Host "=========================================="


$root = Get-Location


$path="$root\app\src\main\java\com\vpn\fovix\app\presentation\mode"


New-Item $path -ItemType Directory -Force | Out-Null



@"
package com.vpn.fovix.app.presentation.mode

import com.vpn.fovix.domain.usermode.UserMode


class ModeController {


    fun showServerInfo(mode: UserMode): Boolean {

        return when(mode){

            UserMode.SIMPLE ->
                false


            UserMode.ADVANCED ->
                true


            UserMode.EXPERT ->
                true

        }

    }



    fun showAdvancedMetrics(mode: UserMode): Boolean {

        return when(mode){

            UserMode.SIMPLE ->
                false


            UserMode.ADVANCED ->
                true


            UserMode.EXPERT ->
                true

        }

    }



    fun showExpertTools(mode: UserMode): Boolean {

        return mode == UserMode.EXPERT

    }


}
"@ | Set-Content "$path\ModeController.kt"



Write-Host "[1/4] ModeController created"


.\gradlew assembleDebug


if($LASTEXITCODE -eq 0){

Write-Host "BUILD SUCCESS"


New-Item ".MODE_CONTROLLER_LOCK" -Force | Out-Null


git add .

git commit -m "STEP 016 mode controller"


git tag v2.6-mode-controller


git push origin main --tags


Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 016 COMPLETE "
Write-Host " TAG: v2.6-mode-controller "
Write-Host "=========================================="

}
else {

Write-Host "BUILD FAILED"

}