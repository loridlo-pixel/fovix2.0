package com.vpn.fovix.app.presentation.home.components


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun FovixCoreButton(

    state: ConnectionStatus,

    server: String,

    onClick: () -> Unit

) {


    val connected =
        state == ConnectionStatus.CONNECTED


    val connecting =
        state == ConnectionStatus.CONNECTING



    val infinite =
        rememberInfiniteTransition(
            label = "core"
        )



    val pulse by infinite.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation =
                tween(
                    1800,
                    easing = LinearEasing
                ),
            repeatMode =
                RepeatMode.Reverse
        ),
        label = "pulse"
    )



    Box(

        modifier = Modifier
            .size(230.dp)
            .clickable {
                onClick()
            },

        contentAlignment = Alignment.Center

    ) {



        if(
            connected || connecting
        ) {


            Box(

                modifier = Modifier
                    .size(210.dp)
                    .scale(
                        if(connecting)
                            pulse
                        else
                            1f
                    )
                    .blur(35.dp)
                    .background(

                        Brush.radialGradient(

                            listOf(

                                Color(0xFF00E5FF),
                                Color(0xFF7C4DFF),
                                Color.Transparent

                            )

                        ),

                        CircleShape

                    )

            )

        }




        Box(

            modifier = Modifier
                .size(170.dp)
                .background(

                    Brush.linearGradient(

                        listOf(

                            Color(0xFF111820),
                            Color(0xFF1B2633)

                        )

                    ),

                    CircleShape

                ),

            contentAlignment = Alignment.Center

        ) {



            Column(

                horizontalAlignment =
                    Alignment.CenterHorizontally

            ) {



                Text(

                    text = "◉",

                    color =
                        if(connected)
                            Color(0xFF00E5FF)
                        else
                            Color.White,

                    fontSize = 54.sp

                )



                Spacer(
                    Modifier.height(8.dp)
                )



                Text(

                    text =
                        when {

                            connected ->
                                "PROTECTED"


                            connecting ->
                                "CONNECTING"


                            else ->
                                "CONNECT"

                        },

                    color = Color.White,

                    fontSize = 16.sp

                )



                if(
                    connected
                ) {


                    Spacer(
                        Modifier.height(6.dp)
                    )


                    Text(

                        text = server,

                        color =
                            Color(0xFF9AA7B5),

                        fontSize = 12.sp

                    )

                }

            }

        }

    }

}