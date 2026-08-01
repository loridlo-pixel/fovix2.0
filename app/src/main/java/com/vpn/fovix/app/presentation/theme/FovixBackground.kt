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

                        Color(0xFF171126),

                        Color(0xFF111522),

                        Color(0xFF0F111A)

                    )

                )

            )

    ) {


        // Aurora glow

        Box(

            modifier = Modifier

                .fillMaxSize()

                .background(

                    Brush.radialGradient(

                        colors = listOf(

                            Color(0x55A855F7),

                            Color.Transparent

                        ),

                        radius = 850f

                    )

                )

        )



        // Ice blue secondary glow

        Box(

            modifier = Modifier

                .fillMaxSize()

                .background(

                    Brush.radialGradient(

                        colors = listOf(

                            Color(0x2238BDF8),

                            Color.Transparent

                        ),

                        radius = 1200f

                    )

                )

        )



        content()


    }


}