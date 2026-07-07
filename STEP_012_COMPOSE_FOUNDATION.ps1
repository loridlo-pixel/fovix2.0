Write-Host "=========================================="
Write-Host " FOVIX STEP 012.1 - COMPOSE FOUNDATION"
Write-Host "=========================================="


$root = Get-Location


Write-Host "[1/7] Creating UI folders..."


New-Item -ItemType Directory `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\components" `
-Force | Out-Null


New-Item -ItemType Directory `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\theme" `
-Force | Out-Null



Write-Host "[2/7] Creating FovixTheme..."


@"
package com.vpn.fovix.app.presentation.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun FovixTheme(
    content: @Composable () -> Unit
){

    MaterialTheme(
        content = content
    )

}

"@ | Set-Content `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\theme\FovixTheme.kt"



Write-Host "[3/7] Creating Typography..."


@"
package com.vpn.fovix.app.presentation.theme


import androidx.compose.material3.Typography


val FovixTypography = Typography()

"@ | Set-Content `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\theme\Typography.kt"



Write-Host "[4/7] Creating GlassCard..."


@"
package com.vpn.fovix.app.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun GlassCard(
    content: @Composable () -> Unit
){

    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
    ){

        Box(
            modifier = Modifier
                .background(
                    Color(0xFF141B22)
                )
                .padding(20.dp)
        ){

            content()

        }

    }

}

"@ | Set-Content `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\components\GlassCard.kt"



Write-Host "[5/7] Creating StatusDot..."


@"
package com.vpn.fovix.app.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun StatusDot(
    active:Boolean
){

    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .size(12.dp)
            .background(
                if(active)
                    Color(0xFF00E5FF)
                else
                    Color.Gray,
                CircleShape
            )
    )

}

"@ | Set-Content `
"$root\app\src\main\java\com\vpn\fovix\app\presentation\components\StatusDot.kt"



Write-Host "[6/7] Build check..."


.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit

}



Write-Host "[7/7] Git lock..."


New-Item ".COMPOSE_FOUNDATION_LOCK" -Force | Out-Null


git add .

git commit -m "STEP 012 Compose foundation"

git tag v2.2-compose-foundation

git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " FOVIX STEP 012.1 COMPLETED"
Write-Host " TAG: v2.2-compose-foundation"
Write-Host "=========================================="