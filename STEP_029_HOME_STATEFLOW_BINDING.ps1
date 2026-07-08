Write-Host "=========================================="
Write-Host " FOVIX STEP 029 - HOME STATEFLOW BINDING"
Write-Host "=========================================="


$root = Get-Location

$path = "$root\app\src\main\java\com\vpn\fovix\app\presentation\home"


New-Item -ItemType Directory -Force -Path $path | Out-Null



Write-Host "[1/5] Creating HomeStateMapper..."


@'
package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.vpnstate.VPNState



fun VPNState.toHomeConnectionState() : HomeConnectionState {


    return HomeConnectionState(

        status = status,

        server = connectionInfo?.serverName ?: "AUTO",

        ping = connectionInfo?.ping ?: 0

    )

}
'@ | Set-Content "$path\HomeStateMapper.kt"



Write-Host "[2/5] Creating HomeViewState..."


@'
package com.vpn.fovix.app.presentation.home


data class HomeViewState(

    val connection: HomeConnectionState = HomeConnectionState(),

    val download:Int = 0,

    val upload:Int = 0

)
'@ | Set-Content "$path\HomeViewState.kt"



Write-Host "[3/5] Build..."

.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit 1

}



Write-Host "[4/5] Creating lock..."

New-Item ".HOME_STATEFLOW_BINDING_LOCK" -Force | Out-Null



Write-Host "[5/5] Git..."

git add .

git commit -m "STEP 029 home stateflow binding"

git tag v3.5-home-stateflow

git push origin main --tags


Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 029 COMPLETE "
Write-Host " TAG: v3.5-home-stateflow "
Write-Host "=========================================="