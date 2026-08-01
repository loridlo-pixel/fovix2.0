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

                Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF101923),

                        Color(0xFF0D1520),

                        Color(0xFF0A1018)

                    )

                )

            )

    ) {



        Box(

            modifier = Modifier

                .fillMaxSize()

                .background(

                    Brush.radialGradient(

                        colors = listOf(

                            Color(0x5533D9FF),

                            Color(0x227B4DFF),

                            Color.Transparent

                        ),

                        radius = 1200f

                    )

                )

        )



        content()

    }

}