Write-Host "=========================================="
Write-Host " FOVIX STEP 028 - CONNECTION STATE ANIMATION"
Write-Host "=========================================="


$root = Get-Location

$path = "$root\app\src\main\java\com\vpn\fovix\app\presentation\home"


New-Item -ItemType Directory -Force -Path $path | Out-Null


Write-Host "[1/4] Creating HomeConnectionState..."


@'
package com.vpn.fovix.app.presentation.home

import com.vpn.fovix.domain.vpnstate.ConnectionStatus


data class HomeConnectionState(

    val status: ConnectionStatus = ConnectionStatus.DISCONNECTED,

    val server: String = "AUTO",

    val ping: Int = 0

)
'@ | Set-Content "$path\HomeConnectionState.kt"



Write-Host "[2/4] Creating ConnectionStateMapper..."


@'
package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.vpnstate.ConnectionStatus


data class ConnectionUiState(

    val text:String,

    val active:Boolean,

    val loading:Boolean

)


fun mapConnectionState(
    status: ConnectionStatus
): ConnectionUiState {


    return when(status){


        ConnectionStatus.DISCONNECTED ->
            ConnectionUiState(
                text = "CONNECT",
                active = false,
                loading = false
            )


        ConnectionStatus.CONNECTING ->
            ConnectionUiState(
                text = "CONNECTING...",
                active = false,
                loading = true
            )


        ConnectionStatus.CONNECTED ->
            ConnectionUiState(
                text = "CONNECTED",
                active = true,
                loading = false
            )


        ConnectionStatus.ERROR ->
            ConnectionUiState(
                text = "ERROR",
                active = false,
                loading = false
            )

    }

}
'@ | Set-Content "$path\ConnectionStateMapper.kt"



Write-Host "[3/4] Build..."

.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit 1

}


Write-Host "[4/4] Git..."


New-Item ".CONNECTION_STATE_ANIMATION_LOCK" -Force | Out-Null


git add .

git commit -m "STEP 028 connection state animation"

git tag v3.4-connection-animation

git push origin main --tags


Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 028 COMPLETE "
Write-Host " TAG: v3.4-connection-animation "
Write-Host "=========================================="