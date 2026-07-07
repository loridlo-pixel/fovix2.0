Clear-Host

Write-Host ""
Write-Host "==========================================" -ForegroundColor Cyan
Write-Host "      FOVIX STEP 004 - VPN SERVICE"
Write-Host "==========================================" -ForegroundColor Cyan
Write-Host ""

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root


Write-Host "[1/8] Creating VPN Service module..." -ForegroundColor Yellow


$vpnPath = ".\vpn\src\main\java\com\vpn\fovix\vpn"

if (!(Test-Path $vpnPath)) {
    New-Item -ItemType Directory -Force -Path $vpnPath | Out-Null
}


$serviceFile = "$vpnPath\VpnServiceImpl.kt"


if (!(Test-Path $serviceFile)) {

@"
package com.vpn.fovix.vpn

import android.content.Intent
import android.net.VpnService
import android.os.IBinder


class VpnServiceImpl : VpnService() {


    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }


    override fun onCreate() {
        super.onCreate()

        // STEP 004
        // VPN service layer created
        // Sing-box integration comes later
    }


    override fun onDestroy() {
        super.onDestroy()

        // stop VPN engine here later
    }
}
"@ | Set-Content $serviceFile -Encoding UTF8


Write-Host "Created VpnServiceImpl.kt" -ForegroundColor Green

}
else {

Write-Host "VpnServiceImpl.kt already exists" -ForegroundColor DarkYellow

}



Write-Host ""
Write-Host "[2/8] Creating VPN permission manager..." -ForegroundColor Yellow


$managerFile = "$vpnPath\VpnPermissionManager.kt"


if (!(Test-Path $managerFile)) {


@"
package com.vpn.fovix.vpn

import android.content.Context
import android.content.Intent
import android.net.VpnService


object VpnPermissionManager {


    fun getPermissionIntent(context: Context): Intent? {

        return VpnService.prepare(context)

    }

}
"@ | Set-Content $managerFile -Encoding UTF8


Write-Host "Created VpnPermissionManager.kt" -ForegroundColor Green

}
else {

Write-Host "VpnPermissionManager already exists" -ForegroundColor DarkYellow

}



Write-Host ""
Write-Host "[3/8] Checking files..." -ForegroundColor Yellow


if (!(Test-Path $serviceFile)) {

Write-Host "VpnServiceImpl missing" -ForegroundColor Red
exit

}


if (!(Test-Path $managerFile)) {

Write-Host "VpnPermissionManager missing" -ForegroundColor Red
exit

}


Write-Host "OK"



Write-Host ""
Write-Host "[4/8] Building project..." -ForegroundColor Yellow


./gradlew assembleDebug


if ($LASTEXITCODE -ne 0) {

Write-Host "BUILD FAILED" -ForegroundColor Red
exit

}


Write-Host "BUILD SUCCESS"



Write-Host ""
Write-Host "[5/8] Installing APK..." -ForegroundColor Yellow


./gradlew installDebug


if ($LASTEXITCODE -ne 0) {

Write-Host "INSTALL FAILED" -ForegroundColor Red
exit

}


Write-Host "INSTALL SUCCESS"



Write-Host ""
Write-Host "[6/8] Creating VPN SERVICE LOCK..." -ForegroundColor Yellow


@"
FOVIX VPN SERVICE LOCK

Version: 1.4

Created:
- VpnServiceImpl
- VpnPermissionManager

Status:
OK VPN layer skeleton
OK Build
OK Install

Date:
$(Get-Date)
"@ | Set-Content ".VPN_SERVICE_LOCK"



Write-Host "VPN SERVICE LOCK CREATED"



Write-Host ""
Write-Host "[7/8] Git commit + tag..." -ForegroundColor Yellow


git add .

git commit -m "STEP 004 VPN Service layer"

git tag -f v1.4-vpn-service



Write-Host ""
Write-Host "[8/8] COMPLETE" -ForegroundColor Green


Write-Host ""
Write-Host "==========================================" -ForegroundColor Green
Write-Host " FOVIX STEP 004 COMPLETED"
Write-Host " TAG: v1.4-vpn-service"
Write-Host "==========================================" -ForegroundColor Green

