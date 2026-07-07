Write-Host "=========================================="
Write-Host " FOVIX STEP 023 - HOME DYNAMIC MODE"
Write-Host "=========================================="


$projectRoot = Get-Location

$homePath = "$projectRoot\app\src\main\java\com\vpn\fovix\app\presentation\home"


New-Item -ItemType Directory -Force -Path $homePath | Out-Null


Write-Host "[1/7] Creating HomeUiState..."


@"
package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.core.decision.UserMode


data class HomeUiState(

    val mode: UserMode = UserMode.SIMPLE,

    val title: String = "FOVIX",

    val showServerInfo: Boolean = false,

    val showExpertSettings: Boolean = false

)
"@ | Set-Content "$homePath\HomeUiState.kt"



Write-Host "[2/7] Creating HomeModeRenderer..."


@"
package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.core.decision.UserMode


class HomeModeRenderer {


    fun render(
        mode: UserMode
    ): HomeUiState {


        return when(mode){


            UserMode.SIMPLE ->
                HomeUiState(
                    mode = mode,
                    title = "Simple Mode",
                    showServerInfo = false,
                    showExpertSettings = false
                )


            UserMode.ADVANCED ->
                HomeUiState(
                    mode = mode,
                    title = "Advanced Mode",
                    showServerInfo = true,
                    showExpertSettings = false
                )


            UserMode.EXPERT ->
                HomeUiState(
                    mode = mode,
                    title = "Expert Mode",
                    showServerInfo = true,
                    showExpertSettings = true
                )

        }

    }

}
"@ | Set-Content "$homePath\HomeModeRenderer.kt"



Write-Host "[3/7] Creating HomeViewModel..."


@"
package com.vpn.fovix.app.presentation.home


import androidx.lifecycle.ViewModel
import com.vpn.fovix.core.decision.UserMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel : ViewModel(){


    private val renderer = HomeModeRenderer()


    private val _state =
        MutableStateFlow(
            renderer.render(UserMode.SIMPLE)
        )


    val state: StateFlow<HomeUiState> = _state



    fun changeMode(
        mode: UserMode
    ){

        _state.value =
            renderer.render(mode)

    }


}
"@ | Set-Content "$homePath\HomeViewModel.kt"



Write-Host "[4/7] Build check..."


.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

    Write-Host "BUILD FAILED"
    exit 1

}



Write-Host "[5/7] Creating lock..."


New-Item ".HOME_DYNAMIC_MODE_LOCK" -Force | Out-Null



Write-Host "[6/7] Git commit..."


git add .

git commit -m "STEP 023 dynamic home mode"


git tag v2.9-home-dynamic-mode



Write-Host "[7/7] Push..."


git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 023 COMPLETE "
Write-Host " TAG: v2.9-home-dynamic-mode "
Write-Host "=========================================="

