Write-Host "=========================================="
Write-Host " FOVIX STEP 010 FIX - APP DATA DEPENDENCY"
Write-Host "=========================================="


$appGradle = ".\app\build.gradle.kts"


Write-Host "[1/5] Updating app dependencies..."


$content = Get-Content $appGradle -Raw


if($content -notmatch 'project\(":data"\)') {


$content += @"


dependencies {

    implementation(project(":data"))
    implementation(project(":core"))

}

"@


Set-Content $appGradle $content


}


Write-Host "[2/5] Updating core dependency..."


$coreGradle = ".\core\build.gradle.kts"

$core = Get-Content $coreGradle -Raw


if($core -notmatch 'project\(":domain"\)') {


$core += @"


dependencies {

    implementation(project(":domain"))

}

"@


Set-Content $coreGradle $core

}



Write-Host "[3/5] Build..."


.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit 1

}



Write-Host "[4/5] Install..."

.\gradlew installDebug



Write-Host "[5/5] Git lock..."


New-Item ".STATE_FLOW_FIX_LOCK" -Force | Out-Null


git add .

git commit -m "STEP 010 fix app data dependency"

git tag v2.0-state-flow-fixed

git push origin main --tags


Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 010 FIX COMPLETE "
Write-Host " TAG: v2.0-state-flow-fixed "
Write-Host "=========================================="

