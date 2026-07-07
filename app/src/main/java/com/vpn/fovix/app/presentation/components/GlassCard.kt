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

