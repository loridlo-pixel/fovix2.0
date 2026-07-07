$root = Get-Location

Write-Host "=========================================="
Write-Host " FOVIX STEP 006 - STATE ARCHITECTURE"
Write-Host "=========================================="


$stateDir = "$root\domain\src\main\java\com\vpn\fovix\domain\vpnstate"

New-Item -ItemType Directory -Force -Path $stateDir | Out-Null


Write-Host "[1/8] Creating ConnectionStatus..."


@"
package com.vpn.fovix.domain.vpnstate

enum class ConnectionStatus {

    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    ERROR

}
"@ | Set-Content "$stateDir\ConnectionStatus.kt"



Write-Host "[2/8] Creating VPNState..."


@"
package com.vpn.fovix.domain.vpnstate


data class VPNState(

    val status: ConnectionStatus = ConnectionStatus.DISCONNECTED,

    val server: String? = null,

    val ip: String? = null,

    val latency: Long? = null,

    val protectionEnabled: Boolean = false

)
"@ | Set-Content "$stateDir\VPNState.kt"



Write-Host "[3/8] Creating ConnectionInfo..."


@"
package com.vpn.fovix.domain.vpnstate


data class ConnectionInfo(

    val serverName: String,

    val country: String,

    val ping: Long

)
"@ | Set-Content "$stateDir\ConnectionInfo.kt"



Write-Host "[4/8] Checking files..."


if(
(Test-Path "$stateDir\VPNState.kt") -and
(Test-Path "$stateDir\ConnectionStatus.kt") -and
(Test-Path "$stateDir\ConnectionInfo.kt")
)
{
Write-Host "STATE FILES OK"
}
else
{
Write-Host "STATE CREATION FAILED"
exit 1
}



Write-Host "[5/8] Building project..."

./gradlew assembleDebug


if($LASTEXITCODE -ne 0)
{
Write-Host "BUILD FAILED"
exit 1
}


Write-Host "BUILD SUCCESS"



Write-Host "[6/8] Installing APK..."

./gradlew installDebug


Write-Host "INSTALL SUCCESS"



Write-Host "[7/8] Creating state lock..."


New-Item ".STATE_ARCH_LOCK" -Force | Out-Null



git add .

git commit -m "STEP 006 VPN State Architecture"

git tag -f v1.6-state-architecture

git push origin main --tags



Write-Host "[8/8] COMPLETE"


Write-Host "=========================================="
Write-Host " FOVIX STEP 006 COMPLETED"
Write-Host " TAG: v1.6-state-architecture"
Write-Host "=========================================="