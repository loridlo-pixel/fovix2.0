package com.vpn.fovix.app.presentation.home.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Blue = Color(0xFF0066FF)
private val TextPrimary = Color(0xFF111827)
private val TextSecondary = Color(0xFF6B7280)
private val SurfaceColor = Color(0xFFFFFFFF)


@Composable
fun VyryxCoreCard(
    connected: Boolean = false,
    onClick: () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")

    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "VYRYX Core",
                color = TextPrimary,
                fontSize = 20.sp
            )


            Spacer(
                modifier = Modifier.height(24.dp)
            )


            Box(
                modifier = Modifier
                    .size(90.dp)
                    .scale(
                        if (!connected) pulse else 1f
                    )
                    .background(
                        color = if (connected)
                            Color(0xFF22C55E)
                        else
                            Blue,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = if (connected) "✓" else "◯",
                    color = Color.White,
                    fontSize = 34.sp
                )

            }


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            Text(
                text = if (connected)
                    "Защита активна"
                else
                    "Включить защиту",
                color = TextPrimary,
                fontSize = 18.sp
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Text(
                text = if (connected)
                    "Network Secure"
                else
                    "Нажмите для подключения",
                color = TextSecondary,
                fontSize = 14.sp
            )

        }
    }
}