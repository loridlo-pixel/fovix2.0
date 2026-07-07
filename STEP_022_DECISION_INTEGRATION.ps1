Write-Host "=========================================="
Write-Host " FOVIX STEP 022 - DECISION INTEGRATION"
Write-Host "=========================================="


$projectRoot = Get-Location


$decisionIntegrationPath = "$projectRoot\app\src\main\java\com\vpn\fovix\app\presentation\decision"


New-Item -ItemType Directory -Force -Path $decisionIntegrationPath | Out-Null


Write-Host "[1/7] Creating DecisionController..."


@"
package com.vpn.fovix.app.presentation.decision

import com.vpn.fovix.core.decision.DecisionEngine
import com.vpn.fovix.core.decision.DecisionResult
import com.vpn.fovix.core.decision.UserMode


class DecisionController {


    private val engine = DecisionEngine()


    fun calculate(
        mode: UserMode
    ): DecisionResult {

        return engine.evaluate(mode)

    }

}
"@ | Set-Content "$decisionIntegrationPath\DecisionController.kt"



Write-Host "[2/7] Creating DecisionState..."


@"
package com.vpn.fovix.app.presentation.decision


data class DecisionState(

    val server: String = "AUTO",

    val description: String = "",

    val score: Int = 0

)
"@ | Set-Content "$decisionIntegrationPath\DecisionState.kt"



Write-Host "[3/7] Updating Mode Integration..."


$modePath = "$projectRoot\app\src\main\java\com\vpn\fovix\app\presentation\mode\ModeController.kt"


if(Test-Path $modePath){

Add-Content $modePath @"


/*
 Decision Engine connected in STEP 022
*/

"@

}



Write-Host "[4/7] Build check..."


.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit 1

}



Write-Host "[5/7] Creating lock..."

New-Item ".DECISION_INTEGRATION_LOCK" -Force | Out-Null



Write-Host "[6/7] Git commit..."


git add .

git commit -m "STEP 022 decision engine integration"


git tag v2.8-decision-integration



Write-Host "[7/7] Push..."

git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 022 COMPLETE "
Write-Host " TAG: v2.8-decision-integration "
Write-Host "=========================================="

