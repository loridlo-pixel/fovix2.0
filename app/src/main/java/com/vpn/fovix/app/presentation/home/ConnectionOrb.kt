package com.vpn.fovix.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ConnectionOrb(

    connected: Boolean,

    onClick: () -> Unit

) {

    val gradient =
        if (connected) {

            Brush.radialGradient(

                colors = listOf(

                    Color(0xFF00E5FF),

                    Color(0xFF2979FF)

                )

            )

        } else {

            Brush.radialGradient(

                colors = listOf(

                    Color.DarkGray,

                    Color.Black

                )

            )

        }

    Box(

        modifier = Modifier

            .size(180.dp)

            .background(

                brush = gradient,

                shape = CircleShape

            )

            .clickable {

                onClick()

            },

        contentAlignment = Alignment.Center

    ) {

        Text(

            text = if (connected) "CONNECTED" else "CONNECT",

            color = Color.White

        )

    }

}