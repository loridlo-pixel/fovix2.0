package com.vpn.fovix.app.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard


@Composable
fun HomeScreen(){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF7F8FA)
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ){

            Text(
                text = "VYRYX",
                color = Color(0xFF111827),
                style = MaterialTheme.typography.headlineMedium
            )


            Spacer(
                modifier = Modifier.height(32.dp)
            )


            VyryxCoreCard(
                connected = false,
                onClick = {
                    // позже подключим VPN start
                }
            )

        }

    }

}