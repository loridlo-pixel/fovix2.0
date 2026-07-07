Write-Host "=========================================="
Write-Host " FOVIX STEP 014 - CONNECT STATE BINDING"
Write-Host "=========================================="


$root = Get-Location


$componentPath="$root\app\src\main\java\com\vpn\fovix\app\presentation\components"



Write-Host "[1/7] Creating ConnectButton..."


New-Item -ItemType Directory $componentPath -Force | Out-Null


@"
package com.vpn.fovix.app.presentation.components


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ConnectButton(
    connected:Boolean,
    onClick:()->Unit
){


Button(
onClick = onClick
){

Text(
if(connected)
"DISCONNECT"
else
"CONNECT"
)


}


}

"@ | Set-Content `
"$componentPath\ConnectButton.kt"



Write-Host "[2/7] Creating VPN action bridge..."


@"
package com.vpn.fovix.app.viewmodel


import com.vpn.fovix.domain.vpnstate.VPNAction


fun VPNViewModel.dispatch(
action: VPNAction
){

when(action){


VPNAction.Connect -> {

connect()

}


VPNAction.Disconnect -> {

disconnect()

}


}

}

"@ | Set-Content `
"$root\app\src\main\java\com\vpn\fovix\app\viewmodel\VPNActionBridge.kt"



Write-Host "[3/7] Updating Home connection component..."


@"
package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable
import com.vpn.fovix.app.presentation.components.ConnectButton


@Composable
fun ConnectionControl(
connected:Boolean,
onClick:()->Unit
){


ConnectButton(
connected = connected,
onClick = onClick
)


}

"@ | Set-Content `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\home\ConnectionControl.kt"



Write-Host "[4/7] Build check..."


.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit

}



Write-Host "[5/7] Installing APK..."


.\gradlew installDebug



Write-Host "[6/7] Creating lock..."


New-Item ".CONNECT_STATE_BINDING_LOCK" -Force | Out-Null



Write-Host "[7/7] Git..."


git add .

git commit -m "STEP 014 connect state binding"

git tag v2.5-connect-state-binding

git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " FOVIX STEP 014 COMPLETED"
Write-Host " TAG: v2.5-connect-state-binding"
Write-Host "=========================================="