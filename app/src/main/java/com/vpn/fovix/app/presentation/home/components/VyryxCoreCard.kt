package com.vpn.fovix.app.presentation.home.components


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.scale

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.app.presentation.home.UserMode
import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun VyryxCoreCard(

    mode: UserMode,

    status: ConnectionStatus,

    server: String,

    onClick: () -> Unit

) {


    val transition =
        rememberInfiniteTransition(
            label = "core"
        )


    val pulse = transition.animateFloat(

        initialValue = 1f,

        targetValue = 1.08f,

        animationSpec = infiniteRepeatable(

            animation = tween(

                900,

                easing = FastOutSlowInEasing

            ),

            repeatMode = RepeatMode.Reverse

        ),

        label = "pulse"

    )



    val color = when(status) {


        ConnectionStatus.CONNECTED ->
            Color(0xFF22C55E)


        ConnectionStatus.CONNECTING ->
            Color(0xFFF59E0B)


        ConnectionStatus.ERROR ->
            Color(0xFFEF4444)


        else ->
            Color(0xFF38BDF8)

    }




    val buttonText = when(status) {


        ConnectionStatus.CONNECTED ->
            "PROTECTED"


        ConnectionStatus.CONNECTING ->
            "CONNECTING"


        ConnectionStatus.ERROR ->
            "RETRY"


        else ->
            "CONNECT"

    }





    Box(

        modifier = Modifier

            .fillMaxWidth()

            .height(170.dp)

            .background(

                Color.White,

                RoundedCornerShape(24.dp)

            )

            .padding(20.dp)

    ) {



        Column(

            modifier = Modifier.fillMaxWidth()

        ) {



            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically

            ) {



                Column {


                    Text(

                        text = "Protection",

                        color = Color(0xFF64748B),

                        fontSize = 12.sp

                    )


                    Text(

                        text =
                        if(status == ConnectionStatus.CONNECTED)
                            "Protected"
                        else
                            "Not protected",

                        color = Color(0xFF111827),

                        fontSize = 18.sp

                    )


                }




                Box(

                    modifier = Modifier

                        .scale(

                            if(status == ConnectionStatus.CONNECTING)

                                pulse.value

                            else

                                1f

                        )

                        .background(

                            color,

                            RoundedCornerShape(18.dp)

                        )

                        .clickable {

                            onClick()

                        }

                        .padding(

                            horizontal = 22.dp,

                            vertical = 11.dp

                        ),

                    contentAlignment = Alignment.Center

                ) {



                    Text(

                        text = buttonText,

                        color = Color.White,

                        fontSize = 12.sp

                    )


                }


            }





            Spacer(

                modifier = Modifier.height(20.dp)

            )





            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween

            ) {



                Column {


                    Text(

                        text = "Server",

                        color = Color(0xFF64748B),

                        fontSize = 11.sp

                    )


                    Text(

                        text = server,

                        color = Color(0xFF111827),

                        fontSize = 14.sp

                    )


                }





                if(mode != UserMode.SIMPLE) {


                    Column(

                        horizontalAlignment = Alignment.End

                    ) {


                        Text(

                            text = "Ping",

                            color = Color(0xFF64748B),

                            fontSize = 11.sp

                        )


                        Text(

                            text = "42 ms",

                            color = Color(0xFF111827),

                            fontSize = 14.sp

                        )


                    }


                }


            }





            if(mode == UserMode.EXPERT) {


                Spacer(

                    modifier = Modifier.height(10.dp)

                )


                Text(

                    text = "DNS • Tunnel • Route",

                    color = Color(0xFF64748B),

                    fontSize = 11.sp

                )


            }


        }


    }


}