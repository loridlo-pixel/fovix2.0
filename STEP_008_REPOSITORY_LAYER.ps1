$root="C:\Users\Patriot\Projects\Fovix"

$file="$root\data\src\main\java\com\vpn\fovix\data\repository\VpnRepository.kt"

Write-Host "=========================================="
Write-Host " FOVIX STEP 008 - REPOSITORY LAYER"
Write-Host "=========================================="


New-Item -ItemType Directory `
-Force `
-Path (Split-Path $file) | Out-Null


@"
package com.vpn.fovix.data.repository

import com.vpn.fovix.vpn.VpnEngine
import com.vpn.fovix.domain.vpnstate.VPNState


class VpnRepository(
    private val engine: VpnEngine
) {

    fun connect() {
        engine.connect()
    }


    fun disconnect() {
        engine.disconnect()
    }


    fun getState(): VPNState {

        return VPNState()

    }

}
"@ | Set-Content $file


Write-Host "[1/6] Repository created"


cd $root


Write-Host "[2/6] Building..."

.\gradlew assembleDebug


if ($LASTEXITCODE -eq 0) {

Write-Host "BUILD SUCCESS"


Write-Host "[3/6] Installing APK..."

.\gradlew installDebug


Write-Host "[4/6] Creating lock..."

New-Item "$root\.REPOSITORY_LAYER_LOCK" -Force | Out-Null


git add .

git commit -m "STEP 008 Repository layer"

git tag v1.8-repository-layer

git push origin main --tags


Write-Host ""
Write-Host "=========================================="
Write-Host " FOVIX STEP 008 COMPLETE "
Write-Host " TAG: v1.8-repository-layer "
Write-Host "=========================================="

}
else {

Write-Host "BUILD FAILED"

}