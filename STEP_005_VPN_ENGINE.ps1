$root = Get-Location

Write-Host "=========================================="
Write-Host "      FOVIX STEP 005 - VPN ENGINE"
Write-Host "=========================================="


$enginePath = "$root\vpn\src\main\java\com\vpn\fovix\vpn\VpnEngine.kt"


Write-Host "[1/6] Creating VPN Engine..."


@"
package com.vpn.fovix.vpn

class VpnEngine {


    private var running = false


    fun start() {

        if(running)
            return


        running = true

        // TODO:
        // start sing-box process here

    }


    fun stop(){

        running = false

        // TODO:
        // stop sing-box process

    }


    fun isRunning(): Boolean {

        return running

    }

}
"@ | Set-Content $enginePath



Write-Host "VpnEngine created"


Write-Host "[2/6] Build check..."

./gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit 1

}


Write-Host "[3/6] Installing APK..."

./gradlew installDebug


Write-Host "[4/6] Creating lock..."

New-Item ".VPN_ENGINE_LOCK" -Force


Write-Host "[5/6] Git commit"

git add .

git commit -m "STEP 005 VPN Engine layer"


git tag -f v1.5-vpn-engine


git push origin main --tags



Write-Host "[6/6] COMPLETE"


Write-Host "=========================================="
Write-Host " FOVIX STEP 005 COMPLETED"
Write-Host " TAG: v1.5-vpn-engine"
Write-Host "=========================================="