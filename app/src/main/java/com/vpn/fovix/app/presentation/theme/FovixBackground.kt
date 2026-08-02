package com.vpn.fovix.app.presentation.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color



@Composable
fun FovixBackground(
    content: @Composable () -> Unit
) {


    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(

                brush = Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF071827),

                        Color(0xFF0B2236),

                        Color(0xFF004AAD)

                    )

                )

            )

    ) {


        content()

    }


}