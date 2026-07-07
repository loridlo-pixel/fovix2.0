Write-Host "=========================================="
Write-Host " FOVIX STEP 013 - MODE TRANSFORMATION ENGINE"
Write-Host "=========================================="


$root = Get-Location

$uiPath="$root\app\src\main\java\com\vpn\fovix\app\presentation\home"



Write-Host "[1/7] Creating mode components..."


New-Item -ItemType Directory $uiPath -Force | Out-Null



Write-Host "[2/7] Creating SimpleView..."


@"
package com.vpn.fovix.app.presentation.home


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun SimpleView(){


Text(
text="Simple Protection"
)


}

"@ | Set-Content "$uiPath\SimpleView.kt"



Write-Host "[3/7] Creating AdvancedView..."


@"
package com.vpn.fovix.app.presentation.home


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun AdvancedView(){


Text(
text="Advanced Settings"
)


}

"@ | Set-Content "$uiPath\AdvancedView.kt"



Write-Host "[4/7] Creating ExpertView..."


@"
package com.vpn.fovix.app.presentation.home


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ExpertView(){


Text(
text="Expert Controls"
)


}

"@ | Set-Content "$uiPath\ExpertView.kt"



Write-Host "[5/7] Creating Mode Renderer..."


@"
package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable
import com.vpn.fovix.domain.usermode.UserMode



@Composable
fun ModeRenderer(
mode: UserMode
){


when(mode){


UserMode.SIMPLE -> {

SimpleView()

}


UserMode.ADVANCED -> {

AdvancedView()

}


UserMode.EXPERT -> {

ExpertView()

}


}


}

"@ | Set-Content "$uiPath\ModeRenderer.kt"



Write-Host "[6/7] Build check..."


.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit

}



Write-Host "[7/7] Git lock..."


New-Item ".MODE_ENGINE_LOCK" -Force | Out-Null


git add .

git commit -m "STEP 013 mode transformation engine"

git tag v2.4-mode-engine

git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " FOVIX STEP 013 COMPLETED"
Write-Host " TAG: v2.4-mode-engine"
Write-Host "=========================================="