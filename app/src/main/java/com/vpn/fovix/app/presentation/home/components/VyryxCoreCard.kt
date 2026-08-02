package com.vpn.fovix.app.presentation.home.components


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun VyryxCoreCard(


    status: ConnectionStatus = ConnectionStatus.DISCONNECTED,


    server: String = "Auto",


    connected: Boolean? = null,


    onClick: () -> Unit


) {



    val actualStatus =

        if(connected == true)

            ConnectionStatus.CONNECTED

        else

            status





    val transition = rememberInfiniteTransition(

        label = "core_animation"

    )



    val shine by transition.animateFloat(

        initialValue = -1f,

        targetValue = 2f,


        animationSpec = infiniteRepeatable(

            animation = tween(

                durationMillis = 1200,

                easing = LinearEasing

            )

        ),


        label = "shine"

    )





    val buttonColor = when(actualStatus) {


        ConnectionStatus.CONNECTED ->

            Color(0xFF22C55E)



        ConnectionStatus.CONNECTING,

        ConnectionStatus.DISCONNECTING ->

            Color(0xFF6366F1)



        ConnectionStatus.ERROR ->

            Color(0xFFEF4444)



        else ->

            Color(0xFF6366F1)


    }





    Column(

        modifier = Modifier

            .fillMaxWidth()

            .background(

                Color.White,

                RoundedCornerShape(28.dp)

            )

            .padding(22.dp)

    ) {



        Text(

            text = "VYRYX CORE",

            color = Color(0xFF111827),

            fontSize = 18.sp

        )





        Spacer(

            modifier = Modifier.height(18.dp)

        )





        Box(

            modifier = Modifier

                .fillMaxWidth()

                .height(92.dp)

                .clip(

                    RoundedCornerShape(24.dp)

                )

                .background(

                    Brush.linearGradient(

                        colors = listOf(

                            buttonColor,

                            buttonColor.copy(

                                alpha = 0.75f

                            )

                        )

                    )

                )

                .clickable {


                    onClick()


                },


            contentAlignment = Alignment.Center


        ) {



            if(

                actualStatus == ConnectionStatus.CONNECTING ||

                actualStatus == ConnectionStatus.DISCONNECTING

            ) {


                Box(

                    modifier = Modifier

                        .fillMaxSize()

                        .background(

                            Brush.linearGradient(

                                colors = listOf(

                                    Color.Transparent,

                                    Color.White.copy(

                                        alpha = 0.35f

                                    ),

                                    Color.Transparent

                                )

                            )

                        )

                )


            }






            Text(

                text = when(actualStatus) {


                    ConnectionStatus.CONNECTED ->

                        "CONNECTED"



                    ConnectionStatus.CONNECTING ->

                        "CONNECTING..."



                    ConnectionStatus.DISCONNECTING ->

                        "DISCONNECTING..."



                    ConnectionStatus.ERROR ->

                        "RETRY"



                    else ->

                        "CONNECT"


                },


                color = Color.White,


                fontSize = 18.sp


            )


        }





        Spacer(

            modifier = Modifier.height(16.dp)

        )





        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween

        ) {



            Text(

                text = "Server",

                color = Color(0xFF6B7280),

                fontSize = 13.sp

            )



            Text(

                text = server,

                color = Color(0xFF111827),

                fontSize = 13.sp

            )


        }


    }


}