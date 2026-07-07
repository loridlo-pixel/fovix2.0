Write-Host "=========================================="
Write-Host " FOVIX STEP 011 - DESIGN SYSTEM"
Write-Host "=========================================="


$root = Get-Location


Write-Host "[1/8] Creating UI folders..."

New-Item -ItemType Directory `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\theme" `
-Force | Out-Null

New-Item -ItemType Directory `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\home" `
-Force | Out-Null


Write-Host "[2/8] Creating Fovix Colors..."


@"
package com.vpn.fovix.app.presentation.theme

import androidx.compose.ui.graphics.Color


val FovixBackground = Color(0xFF0B1015)

val FovixCyan = Color(0xFF00E5FF)

val FovixViolet = Color(0xFF8A2BE2)

val FovixCard = Color(0xFF141B22)

"@ | Set-Content `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\theme\FovixColors.kt"



Write-Host "[3/8] Creating User Mode..."


New-Item -ItemType Directory `
"$root\domain\src\main\java\com\vpn\fovix\domain\usermode" `
-Force | Out-Null


@"
package com.vpn.fovix.domain.usermode

enum class UserMode {

    SIMPLE,

    ADVANCED,

    EXPERT

}

"@ | Set-Content `
"$root\domain\src\main\java\com\vpn\fovix\domain\usermode\UserMode.kt"



Write-Host "[4/8] Creating Home Screen Skeleton..."


@"
package com.vpn.fovix.app.presentation.home


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vpn.fovix.app.presentation.theme.FovixBackground


@Composable
fun HomeScreen(){


    Surface(
        color = FovixBackground
    ){

        Text(
            text = "FOVIX",
            color = Color.White
        )

    }


}

"@ | Set-Content `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\home\HomeScreen.kt"



Write-Host "[5/8] Adding UI state foundation..."


$stateFile="$root\domain\src\main\java\com\vpn\fovix\domain\vpnstate\VPNState.kt"


if(Test-Path $stateFile){

(Get-Content $stateFile) `
-replace "import","import com.vpn.fovix.domain.usermode.UserMode`r`n`r`nimport" |
Set-Content $stateFile


}



Write-Host "[6/8] Build check..."

.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit

}


Write-Host "[7/8] Creating DESIGN SYSTEM LOCK..."


New-Item ".DESIGN_SYSTEM_LOCK" -Force | Out-Null



Write-Host "[8/8] Git commit..."


git add .

git commit -m "STEP 011 FOVIX design system"

git tag v2.1-design-system

git push origin main --tags


Write-Host ""
Write-Host "=========================================="
Write-Host " FOVIX STEP 011 COMPLETED"
Write-Host " TAG: v2.1-design-system"
Write-Host "=========================================="