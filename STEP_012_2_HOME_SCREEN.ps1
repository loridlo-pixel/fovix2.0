Write-Host "=========================================="
Write-Host " FOVIX STEP 012.2 - HOME SCREEN CORE"
Write-Host "=========================================="


$root = Get-Location


$home="$root\app\src\main\java\com\vpn\fovix\app\presentation\home"

New-Item -ItemType Directory $home -Force | Out-Null


Write-Host "[1/5] Creating HomeScreen..."


@"
package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vpn.fovix.app.presentation.components.GlassCard
import com.vpn.fovix.app.presentation.components.StatusDot


@Composable
fun HomeScreen(){


Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),

    horizontalAlignment = Alignment.CenterHorizontally

){


Text(
    text="FOVIX",
    style=MaterialTheme.typography.headlineLarge
)



Spacer(
    modifier=Modifier.height(40.dp)
)



GlassCard {


Column {


Row(
verticalAlignment = Alignment.CenterVertically
){

StatusDot(
active=true
)


Spacer(
Modifier.width(12.dp)
)


Text(
"Protected"
)

}


Spacer(
Modifier.height(20.dp)
)


Button(
onClick={}
){

Text(
"CONNECT"
)

}



}


}



Spacer(
Modifier.height(20.dp)
)



GlassCard {


Text(
"USA • New York"
)


Text(
"24 ms"
)


}



}


}

"@ | Set-Content `
"$home\HomeScreen.kt"



Write-Host "[2/5] Creating Home components..."


@"
package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable
import androidx.compose.material3.Text


@Composable
fun FovixHeader(){

Text(
text="FOVIX"
)

}

"@ | Set-Content `
"$home\FovixHeader.kt"



Write-Host "[3/5] Build..."


.\gradlew assembleDebug


if($LASTEXITCODE -ne 0){

Write-Host "BUILD FAILED"
exit

}



Write-Host "[4/5] Creating HOME LOCK..."


New-Item ".HOME_SCREEN_CORE_LOCK" -Force | Out-Null



Write-Host "[5/5] Git..."


git add .

git commit -m "STEP 012.2 FOVIX home screen core"

git tag v2.3-home-screen-core

git push origin main --tags



Write-Host ""
Write-Host "=========================================="
Write-Host " STEP 012.2 COMPLETED"
Write-Host " TAG: v2.3-home-screen-core"
Write-Host "=========================================="