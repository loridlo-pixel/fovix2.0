package com.vpn.fovix.app.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeDashboard(

    connected: Boolean,

    server: String,

    download: Int,

    upload: Int,

    onConnectClick: () -> Unit

) {

    Box(

        modifier = Modifier.fillMaxSize(),

        contentAlignment = Alignment.Center

    ) {

        Column(

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center

        ) {

            Text(
                text = "FOVIX"
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            ConnectionOrb(

                connected = connected,

                onClick = onConnectClick

            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            ServerCard(
                server = server
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            SpeedMetrics(

                download = download,

                upload = upload

            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            PremiumBadge()

        }

    }

}