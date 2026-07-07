Write-Host "=========================================="
Write-Host " FOVIX STEP 015 - USER MODE ARCHITECTURE"
Write-Host "=========================================="


$root = Get-Location


$path = "$root\domain\src\main\java\com\vpn\fovix\domain\usermode"

New-Item $path -ItemType Directory -Force | Out-Null



@"
package com.vpn.fovix.domain.usermode


enum class UserMode {

    SIMPLE,

    ADVANCED,

    EXPERT

}
"@ | Set-Content "$path\UserMode.kt"



@"
package com.vpn.fovix.domain.usermode


data class UserModeState(

    val mode: UserMode = UserMode.SIMPLE

)
"@ | Set-Content "$path\UserModeState.kt"



Write-Host "[1/5] UserMode created"



.\gradlew assembleDebug


if($LASTEXITCODE -eq 0){

    Write-Host "BUILD SUCCESS"


    New-Item ".USER_MODE_ARCHITECTURE_LOCK" -Force | Out-Null


    git add .

    git commit -m "STEP 015 user mode architecture"


    git tag v2.5-user-mode-architecture


    git push origin main --tags



    Write-Host ""
    Write-Host "=========================================="
    Write-Host " STEP 015 COMPLETE "
    Write-Host " TAG: v2.5-user-mode-architecture "
    Write-Host "=========================================="

}
else {

    Write-Host "BUILD FAILED"

}