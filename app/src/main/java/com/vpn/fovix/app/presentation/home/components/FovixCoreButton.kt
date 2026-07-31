package com.vpn.fovix.app.presentation.home.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
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

    val disconnecting =
        state == ConnectionStatus.DISCONNECTING


    val transition =
        rememberInfiniteTransition(
            label = "fovix_core"
        )


    val pulse by transition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.08f,
        animationSpec =
            infiniteRepeatable(
                animation =
                    tween(
                        durationMillis = 1800,
                        easing = FastOutSlowInEasing
                    ),
                repeatMode = RepeatMode.Reverse
            ),
        label = "pulse"
    )


    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
            infiniteRepeatable(
                animation =
                    tween(
                        durationMillis =
                        if(connecting) 2500 else 9000,
                        easing = LinearEasing
                    )
            ),
        label = "rotation"
    )


    val glowColor =
        when(state){

            ConnectionStatus.CONNECTED ->
                Color(0xFF00E5FF)

            ConnectionStatus.CONNECTING ->
                Color(0xFF7C4DFF)

            ConnectionStatus.DISCONNECTING ->
                Color(0xFFFF9800)

            else ->
                Color(0xFF37474F)
        }


    Box(

        modifier =
            Modifier
                .size(260.dp)
                .clickable {
                    onClick()
                },

        contentAlignment =
            Alignment.Center

    ){


        /*
            Outer orbital ring
        */

        if(
            connected ||
            connecting
        ){

            Canvas(
                modifier =
                    Modifier
                        .size(245.dp)
                        .rotate(
                            if(connecting)
                                rotation
                            else
                                rotation / 3
                        )
            ){

                drawCircle(

                    brush =
                        Brush.sweepGradient(
                            listOf(
                                Color.Transparent,
                                Color(0xFF00E5FF),
                                Color(0xFF7C4DFF),
                                Color.Transparent
                            )
                        ),

                    radius =
                        size.width / 2,

                    style =
                        androidx.compose.ui.graphics.drawscope
                            .Stroke(
                                width = 3.dp.toPx()
                            )
                )
            }
        }



        /*
            Core glow
        */

        Box(

            modifier =
                Modifier
                    .size(220.dp)
                    .scale(
                        if(connecting)
                            pulse
                        else
                            1f
                    )
                    .blur(45.dp)
                    .background(

                        Brush.radialGradient(

                            listOf(

                                glowColor.copy(
                                    alpha = 0.65f
                                ),

                                Color.Transparent

                            )
                        ),

                        CircleShape
                    )

        )



        /*
            Main Core
        */

        Box(

            modifier =
                Modifier
                    .size(170.dp)
                    .background(

                        Brush.linearGradient(

                            listOf(

                                Color(0xFF101820),
                                Color(0xFF1B2633)

                            )
                        ),

                        CircleShape

                    ),

            contentAlignment =
                Alignment.Center

        ){


            Column(

                horizontalAlignment =
                    Alignment.CenterHorizontally

            ){


                Text(

                    text =
                        "◉",

                    color =
                        if(connected)
                            Color(0xFF00E5FF)
                        else
                            Color.White,

                    fontSize =
                        56.sp

                )


                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )


                Text(

                    text =
                        when(state){

                            ConnectionStatus.CONNECTED ->
                                "PROTECTED"

                            ConnectionStatus.CONNECTING ->
                                "CONNECTING"

                            ConnectionStatus.DISCONNECTING ->
                                "STOPPING"

                            else ->
                                "CONNECT"
                        },

                    color =
                        Color.White,

                    fontSize =
                        16.sp

                )


                if(connected){

                    Spacer(
                        modifier =
                            Modifier.height(6.dp)
                    )


                    Text(

                        text =
                            server,

                        color =
                            Color(0xFF9AA7B5),

                        fontSize =
                            12.sp
                    )
                }
            }
        }
    }
}