Write-Host "=========================================="
Write-Host " FOVIX STEP 019 - DYNAMIC HOME SCREEN"
Write-Host "=========================================="


$root = Get-Location


$path="$root\app\src\main\java\com\vpn\fovix\app\presentation\home"


New-Item $path -ItemType Directory -Force | Out-Null



@"
package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vpn.fovix.app.presentation.home.components.MetricsCard
import com.vpn.fovix.app.presentation.home.components.ServerCard
import com.vpn.fovix.app.presentation.home.components.StatusCard



@Composable
fun HomeScreenDynamic(

    state: HomeUiState

){


    Column {


        StatusCard(
            status = state.status.name
        )



        if(state.showServer){

            ServerCard(
                server = "Auto Server"
            )

        }



        if(state.showMetrics){

            MetricsCard(
                ping = "Ping 40 ms"
            )

        }



        if(state.showExpert){

            Text(
                text = "Expert tools enabled"
            )

        }


    }


}
"@ | Set-Content "$path\HomeScreenDynamic.kt"




.\gradlew assembleDebug


if($LASTEXITCODE -eq 0){


Write-Host "BUILD SUCCESS"


New-Item ".DYNAMIC_HOME_SCREEN_LOCK" -Force | Out-Null


git add .

git commit -m "STEP 019 dynamic home screen"


git tag v2.9-dynamic-home-screen


git push origin main --tags


Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 019 COMPLETE "
Write-Host " TAG: v2.9-dynamic-home-screen "
Write-Host "=========================================="


}