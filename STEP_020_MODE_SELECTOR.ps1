Write-Host "=========================================="
Write-Host " FOVIX STEP 020 - MODE SELECTOR"
Write-Host "=========================================="


$root = Get-Location


$path="$root\app\src\main\java\com\vpn\fovix\app\presentation\mode"


New-Item $path -ItemType Directory -Force | Out-Null



@"
package com.vpn.fovix.app.presentation.mode


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vpn.fovix.domain.usermode.UserMode



@Composable
fun ModeSelector(

    current: UserMode,

    onChange:(UserMode)->Unit

){


    Column {


        Button(
            onClick = {

                onChange(UserMode.SIMPLE)

            }
        ){

            Text("Simple")

        }



        Button(
            onClick = {

                onChange(UserMode.ADVANCED)

            }
        ){

            Text("Advanced")

        }



        Button(
            onClick = {

                onChange(UserMode.EXPERT)

            }
        ){

            Text("Expert")

        }


    }


}
"@ | Set-Content "$path\ModeSelector.kt"




.\gradlew assembleDebug


if($LASTEXITCODE -eq 0){


Write-Host "BUILD SUCCESS"


New-Item ".MODE_SELECTOR_LOCK" -Force | Out-Null


git add .

git commit -m "STEP 020 mode selector"


git tag v3.0-mode-selector


git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 020 COMPLETE "
Write-Host " TAG: v3.0-mode-selector "
Write-Host "=========================================="


}