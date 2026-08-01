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

                        Color(0xFF102A43),

                        Color(0xFF0B1B2B),

                        Color(0xFF071018)

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

                            Color(0x557B4DFF),

                            Color.Transparent

                        ),

                        radius = 900f

                    )

                )

        )


        content()


    }

}