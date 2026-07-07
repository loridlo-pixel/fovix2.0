package com.vpn.fovix.app.presentation.home

import androidx.compose.foundation.background
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
    connected: Boolean
) {

    val gradient = if (connected) {
        Brush.radialGradient(
            colors = listOf(
                Color.Cyan,
                Color.Blue
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
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = if (connected) "ON" else "OFF",
            color = Color.White
        )

    }
}