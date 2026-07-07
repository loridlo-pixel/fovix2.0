package com.vpn.fovix.app.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun HomeDashboard(

    connected: Boolean,

    server: String,

    download: Int,

    upload: Int

) {

    Column {


        Text(
            text = "FOVIX"
        )


        Spacer(
            modifier = Modifier.height(32.dp)
        )


        ConnectionOrb(
            connected = connected
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        ServerCard(
            server = server
        )


        SpeedMetrics(
            download = download,
            upload = upload
        )


        PremiumBadge()

    }

}