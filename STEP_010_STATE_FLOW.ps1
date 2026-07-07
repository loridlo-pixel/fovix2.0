Write-Host "=========================================="
Write-Host " FOVIX STEP 010 - STATE FLOW"
Write-Host "=========================================="


$root = Get-Location


Write-Host "[1/8] Creating VPN Actions..."


$path = "$root\domain\src\main\java\com\vpn\fovix\domain\vpnstate"

New-Item -ItemType Directory -Force -Path $path | Out-Null


@"
package com.vpn.fovix.domain.vpnstate


sealed class VPNAction {

    object Connect : VPNAction()

    object Disconnect : VPNAction()

}

"@ | Set-Content "$path\VPNAction.kt"



Write-Host "[2/8] Updating Repository with StateFlow..."


$repoPath = "$root\data\src\main\java\com\vpn\fovix\data\repository\VpnRepository.kt"


@"
package com.vpn.fovix.data.repository


import com.vpn.fovix.vpn.VpnEngine
import com.vpn.fovix.domain.vpnstate.VPNState
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class VpnRepository(
    private val engine: VpnEngine
){


private val _state =
    MutableStateFlow(
        VPNState(
            status = ConnectionStatus.DISCONNECTED
        )
    )


val state: StateFlow<VPNState>
    get() = _state



fun connect(){

    _state.value =
        VPNState(
            status = ConnectionStatus.CONNECTING
        )


    engine.start()


    _state.value =
        VPNState(
            status = ConnectionStatus.CONNECTED
        )

}



fun disconnect(){

    engine.stop()


    _state.value =
        VPNState(
            status = ConnectionStatus.DISCONNECTED
        )

}


}

"@ | Set-Content $repoPath



Write-Host "[3/8] Updating ViewModel..."


$vmPath="$root\app\src\main\java\com\vpn\fovix\app\viewmodel\VPNViewModel.kt"


@"
package com.vpn.fovix.app.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.domain.vpnstate.VPNAction
import kotlinx.coroutines.flow.StateFlow


class VPNViewModel(
    private val repository: VpnRepository
): ViewModel(){


val state: StateFlow<com.vpn.fovix.domain.vpnstate.VPNState>
    = repository.state



fun action(action: VPNAction){

    when(action){

        VPNAction.Connect ->
            repository.connect()


        VPNAction.Disconnect ->
            repository.disconnect()

    }

}


}

"@ | Set-Content $vmPath



Write-Host "[4/8] Adding coroutines dependency..."


$data="$root\data\build.gradle.kts"


$content=Get-Content $data -Raw


if($content -notmatch "kotlinx-coroutines"){

$content += @"


dependencies {

implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

}

"@

Set-Content $data $content

}



Write-Host "[5/8] Build check..."


.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit 1

}



Write-Host "[6/8] Installing APK..."


.\gradlew installDebug



Write-Host "[7/8] Creating lock..."


New-Item ".STATE_FLOW_LOCK" -Force | Out-Null



Write-Host "[8/8] Git..."


git add .

git commit -m "STEP 010 VPN StateFlow architecture"

git tag v2.0-state-flow

git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " FOVIX STEP 010 COMPLETED "
Write-Host " TAG: v2.0-state-flow "
Write-Host "=========================================="

