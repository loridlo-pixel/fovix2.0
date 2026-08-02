package com.vpn.fovix.app.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard
import com.vpn.fovix.app.presentation.home.components.ProtectionScoreCard
import com.vpn.fovix.app.presentation.home.components.NetworkHealthCard


@Composable
fun HomeScreen() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF7F8FA)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                )
        ) {

            Text(
                text = "VYRYX",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF111827)
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Text(
                text = "Добрый день",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF6B7280)
            )


            Spacer(
                modifier = Modifier.height(28.dp)
            )


            VyryxCoreCard(
                connected = false,
                onClick = {
                    // позже подключим vpn start
                }
            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            ProtectionScoreCard(
                score = 98
            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            NetworkHealthCard(
                latency = 42,
                speed = 186
            )

        }
    }
}