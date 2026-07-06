Write-Host ""
Write-Host "=========== FOVIX PROJECT DIAGNOSTIC ===========" -ForegroundColor Cyan

Write-Host "`n--- AndroidManifest.xml ---" -ForegroundColor Yellow
Get-ChildItem -Recurse -Filter AndroidManifest.xml |
ForEach-Object {
    Write-Host ""
    Write-Host $_.FullName -ForegroundColor Green
    Get-Content $_.FullName
}

Write-Host "`n--- MainActivity.kt ---" -ForegroundColor Yellow
Get-ChildItem -Recurse -Filter MainActivity.kt |
ForEach-Object {
    Write-Host ""
    Write-Host $_.FullName -ForegroundColor Green
    Get-Content $_.FullName
}

Write-Host "`n--- build.gradle.kts ---" -ForegroundColor Yellow
Get-ChildItem -Recurse -Filter build.gradle.kts |
ForEach-Object {
    Write-Host ""
    Write-Host $_.FullName -ForegroundColor Green
}

Write-Host "`n=========== END ===========" -ForegroundColor Cyan