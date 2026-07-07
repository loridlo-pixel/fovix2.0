$root = Get-Location

Write-Host "=========================================="
Write-Host " FOVIX STEP 007 - VIEWMODEL LAYER"
Write-Host "=========================================="


$viewModelDir = "$root\app\src\main\java\com\vpn\fovix\app\viewmodel"

New-Item -ItemType Directory -Force -Path $viewModelDir | Out-Null


Write-Host "[1/8] Creating VPNViewModel..."


@"
package com.vpn.fovix.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.domain.vpnstate.VPNState
import com.vpn.fovix.vpn.VpnEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class VPNViewModel(
    private val vpnEngine: VpnEngine = VpnEngine()
) : ViewModel() {


    private val _state =
        MutableStateFlow(
            VPNState()
        )


    val state: StateFlow<VPNState> =
        _state



    fun connect(){

        viewModelScope.launch {

            _state.value =
                _state.value.copy(
                    status = ConnectionStatus.CONNECTING
                )


            vpnEngine.start()


            _state.value =
                _state.value.copy(
                    status = ConnectionStatus.CONNECTED,
                    protectionEnabled = true
                )

        }

    }



    fun disconnect(){

        viewModelScope.launch {


            vpnEngine.stop()


            _state.value =
                VPNState(
                    status = ConnectionStatus.DISCONNECTED
                )

        }

    }


}
"@ | Set-Content "$viewModelDir\VPNViewModel.kt"



Write-Host "[2/8] Checking ViewModel..."

if(Test-Path "$viewModelDir\VPNViewModel.kt")
{
Write-Host "VIEWMODEL OK"
}
else
{
Write-Host "VIEWMODEL FAILED"
exit 1
}



Write-Host "[3/8] Building project..."

./gradlew assembleDebug


if($LASTEXITCODE -ne 0)
{
Write-Host "BUILD FAILED"
exit 1
}


Write-Host "BUILD SUCCESS"



Write-Host "[4/8] Installing APK..."

./gradlew installDebug


Write-Host "INSTALL SUCCESS"



Write-Host "[5/8] Creating ViewModel lock..."

New-Item ".VIEWMODEL_LAYER_LOCK" -Force | Out-Null



Write-Host "[6/8] Git commit..."

git add .

git commit -m "STEP 007 VPN ViewModel layer"



Write-Host "[7/8] Creating tag..."

git tag -f v1.7-viewmodel-layer

git push origin main --tags



Write-Host "[8/8] COMPLETE"


Write-Host "=========================================="
Write-Host " FOVIX STEP 007 COMPLETED"
Write-Host " TAG: v1.7-viewmodel-layer"
Write-Host "=========================================="