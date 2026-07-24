package com.vpn.fovix.app.presentation.core


import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.scale

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun FovixCore(


    status: ConnectionStatus,


    compact: Boolean,


    onClick: () -> Unit


) {



    val size = if (compact) {

        54.dp

    } else {

        220.dp

    }






    val transition = rememberInfiniteTransition()



    val pulse = transition.animateFloat(


        initialValue = 1f,


        targetValue = 1.06f,


        animationSpec = infiniteRepeatable(


            animation = tween(1200),


            repeatMode = RepeatMode.Reverse


        )


    )







    val brush = when(status) {



        ConnectionStatus.CONNECTED ->

            Brush.radialGradient(

                listOf(

                    Color(0xFF00E5FF),

                    Color(0xFF0055FF)

                )

            )



        ConnectionStatus.CONNECTING ->

            Brush.radialGradient(

                listOf(

                    Color(0xFFFFC107),

                    Color(0xFFFF5722)

                )

            )



        ConnectionStatus.DISCONNECTED ->

            Brush.radialGradient(

                listOf(

                    Color(0xFF37474F),

                    Color(0xFF101820)

                )

            )



        else ->

            Brush.radialGradient(

                listOf(

                    Color.Red,

                    Color(0xFF8B0000)

                )

            )


    }







    Box(


        modifier = Modifier

            .size(size)

            .scale(pulse.value)

            .background(

                brush,

                CircleShape

            )

            .clickable {


                onClick()


            },


        contentAlignment = Alignment.Center


    ) {



        if (!compact) {



            Text(


                text = when(status) {



                    ConnectionStatus.CONNECTED -> "CONNECTED"



                    ConnectionStatus.CONNECTING -> "CONNECTING"



                    ConnectionStatus.DISCONNECTED -> "OFF"



                    else -> "ERROR"


                },


                color = Color.White,


                fontSize = 20.sp


            )


        }



    }



}