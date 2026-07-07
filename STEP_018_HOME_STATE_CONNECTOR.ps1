Write-Host "=========================================="
Write-Host " FOVIX STEP 018 - HOME STATE CONNECTOR"
Write-Host "=========================================="


$root = Get-Location


$path="$root\app\src\main\java\com\vpn\fovix\app\presentation\home"


New-Item $path -ItemType Directory -Force | Out-Null



@"
package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.usermode.UserMode
import com.vpn.fovix.domain.vpnstate.ConnectionStatus



data class HomeUiState(

    val status: ConnectionStatus,

    val mode: UserMode,

    val showServer:Boolean,

    val showMetrics:Boolean,

    val showExpert:Boolean

)
"@ | Set-Content "$path\HomeUiState.kt"




@"
package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.usermode.UserMode



object HomeStateMapper {


    fun mapMode(
        mode: UserMode
    ): HomeVisibility {


        return when(mode){


            UserMode.SIMPLE ->
                HomeVisibility(
                    server=false,
                    metrics=false,
                    expert=false
                )


            UserMode.ADVANCED ->
                HomeVisibility(
                    server=true,
                    metrics=true,
                    expert=false
                )


            UserMode.EXPERT ->
                HomeVisibility(
                    server=true,
                    metrics=true,
                    expert=true
                )

        }


    }


}



data class HomeVisibility(

    val server:Boolean,

    val metrics:Boolean,

    val expert:Boolean

)
"@ | Set-Content "$path\HomeStateMapper.kt"




.\gradlew assembleDebug


if($LASTEXITCODE -eq 0){


Write-Host "BUILD SUCCESS"


New-Item ".HOME_STATE_CONNECTOR_LOCK" -Force | Out-Null


git add .

git commit -m "STEP 018 home state connector"


git tag v2.8-home-state-connector


git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 018 COMPLETE "
Write-Host " TAG: v2.8-home-state-connector "
Write-Host "=========================================="


}