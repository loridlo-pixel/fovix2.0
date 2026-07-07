Clear-Host

Write-Host ""
Write-Host "==========================================" -ForegroundColor Cyan
Write-Host "      FOVIX STEP 003 - ARCH LOCK"
Write-Host "==========================================" -ForegroundColor Cyan
Write-Host ""

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root

Write-Host "[1/8] Checking MainActivity..." -ForegroundColor Yellow

$main = Get-ChildItem -Recurse -Filter MainActivity.kt

if ($main.Count -eq 0)
{
    Write-Host "ERROR: MainActivity not found." -ForegroundColor Red
    exit
}

Write-Host "OK"

Write-Host ""
Write-Host "[2/8] Checking AndroidManifest..." -ForegroundColor Yellow

$manifest = Get-Content ".\app\src\main\AndroidManifest.xml" -Raw

if ($manifest -notmatch "MainActivity")
{
    Write-Host "ERROR: AndroidManifest doesn't reference MainActivity." -ForegroundColor Red
    exit
}

Write-Host "OK"

Write-Host ""
Write-Host "[3/8] Checking VPN Core..." -ForegroundColor Yellow

$core = Get-ChildItem -Recurse -Filter VpnCore.kt

if ($core.Count -eq 0)
{
    Write-Host "ERROR: VpnCore.kt missing." -ForegroundColor Red
    exit
}

Write-Host "OK"

Write-Host ""
Write-Host "[4/8] Building project..." -ForegroundColor Yellow

./gradlew assembleDebug

if ($LASTEXITCODE -ne 0)
{
    Write-Host "BUILD FAILED." -ForegroundColor Red
    exit
}

Write-Host "BUILD SUCCESS"

Write-Host ""
Write-Host "[5/8] Installing APK..." -ForegroundColor Yellow

./gradlew installDebug

if ($LASTEXITCODE -ne 0)
{
    Write-Host "INSTALL FAILED." -ForegroundColor Red
    exit
}

Write-Host "INSTALL SUCCESS"

Write-Host ""
Write-Host "[6/8] Creating ARCH_LOCK marker..." -ForegroundColor Yellow

@"
FOVIX Architecture Lock

Version : 1.3

Status:
OK MainActivity
OK Manifest
OK VpnCore
OK Build
OK Install

Locked: $(Get-Date)
"@ | Set-Content ".ARCH_LOCK"

Write-Host "ARCH_LOCK created."

Write-Host ""
Write-Host "[7/8] Creating Git tag..." -ForegroundColor Yellow

git tag -f v1.3-arch-lock

Write-Host "Git tag updated."

Write-Host ""
Write-Host "[8/8] Finished." -ForegroundColor Green

Write-Host ""
Write-Host "==========================================" -ForegroundColor Green
Write-Host "     FOVIX ARCHITECTURE LOCK COMPLETE"
Write-Host "==========================================" -ForegroundColor Green
