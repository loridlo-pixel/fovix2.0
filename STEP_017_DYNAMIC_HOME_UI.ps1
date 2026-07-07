Write-Host "=========================================="
Write-Host " FOVIX STEP 017 - DYNAMIC HOME UI"
Write-Host "=========================================="


$root = Get-Location


$path="$root\app\src\main\java\com\vpn\fovix\app\presentation\home\components"

New-Item $path -ItemType Directory -Force | Out-Null


@"
package com.vpn.fovix.app.presentation.home.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun StatusCard(
    status:String
){

    Card {

        Text(
            text = status
        )

    }

}
"@ | Set-Content "$path\StatusCard.kt"



@"
package com.vpn.fovix.app.presentation.home.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ServerCard(
    server:String
){

    Card {

        Text(
            text = server
        )

    }

}
"@ | Set-Content "$path\ServerCard.kt"



@"
package com.vpn.fovix.app.presentation.home.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun MetricsCard(
    ping:String
){

    Card {

        Text(
            text = ping
        )

    }

}
"@ | Set-Content "$path\MetricsCard.kt"



.\gradlew assembleDebug


if($LASTEXITCODE -eq 0){

Write-Host "BUILD SUCCESS"

New-Item ".DYNAMIC_HOME_UI_LOCK" -Force | Out-Null

git add .

git commit -m "STEP 017 dynamic home UI components"

git tag v2.7-dynamic-home-ui

git push origin main --tags


Write-Host "=========================================="
Write-Host " STEP 017 COMPLETE "
Write-Host " TAG: v2.7-dynamic-home-ui "
Write-Host "=========================================="

}