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

