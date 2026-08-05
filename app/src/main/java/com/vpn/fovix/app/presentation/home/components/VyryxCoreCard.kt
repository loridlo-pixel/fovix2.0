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

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Brush
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


    val pulse =
        transition.animateFloat(

            initialValue = 1f,

            targetValue = 1.08f,

            animationSpec =
                infiniteRepeatable(

                    animation =
                        tween(
                            1000,
                            easing = FastOutSlowInEasing
                        ),

                    repeatMode =
                        RepeatMode.Reverse

                ),

            label = "pulse"

        )



    val stateText =
        when(status) {

            ConnectionStatus.CONNECTED ->
                "Protected"


            ConnectionStatus.CONNECTING ->
                "Connecting..."


            ConnectionStatus.ERROR ->
                "Attention"


            else ->
                "Ready"

        }



    val subtitle =
        when(status) {

            ConnectionStatus.CONNECTED ->
                "Secure connection active"


            ConnectionStatus.CONNECTING ->
                "Building secure tunnel"


            ConnectionStatus.ERROR ->
                "Connection failed"


            else ->
                "Tap to protect your network"

        }



    val powerColor =
        when(status) {

            ConnectionStatus.CONNECTED ->
                Color(0xFF22C55E)


            ConnectionStatus.CONNECTING ->
                Color(0xFFFBBF24)


            ConnectionStatus.ERROR ->
                Color(0xFFEF4444)


            else ->
                Color.White

        }





    Box(

        modifier =
            Modifier

                .fillMaxWidth()

                .height(180.dp)

                .shadow(

                    elevation = 12.dp,

                    shape = RoundedCornerShape(28.dp),

                    ambientColor = Color(0x33000000),

                    spotColor = Color(0x33000000)

                )

                .background(

                    brush =
                        Brush.linearGradient(

                            colors =
                                listOf(

                                    Color(0xFF38BDF8),

                                    Color(0xFF0284C7)

                                )

                        ),

                    shape =
                        RoundedCornerShape(28.dp)

                )

                .clickable {

                    onClick()

                }

                .padding(22.dp)

    ) {



        Column(

            modifier =
                Modifier.fillMaxWidth()

        ) {



            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically

            ) {



                Column {


                    Text(

                        text = "VYRYX CORE",

                        color = Color.White.copy(alpha = 0.75f),

                        fontSize = 12.sp

                    )


                    Spacer(

                        modifier =
                            Modifier.height(5.dp)

                    )


                    Text(

                        text = stateText,

                        color = Color.White,

                        fontSize = 24.sp

                    )


                }





                Box(

                    modifier =
                        Modifier

                            .size(54.dp)

                            .scale(

                                if(status == ConnectionStatus.CONNECTING)

                                    pulse.value

                                else

                                    1f

                            )

                            .background(

                                Color.White.copy(alpha = 0.18f),

                                CircleShape

                            ),

                    contentAlignment =
                        Alignment.Center

                ) {


                    Text(

                        text = "⏻",

                        color = powerColor,

                        fontSize = 30.sp

                    )


                }



            }







            Spacer(

                modifier =
                    Modifier.height(18.dp)

            )





            Text(

                text = subtitle,

                color =
                    Color.White.copy(alpha = 0.85f),

                fontSize = 13.sp

            )







            Spacer(

                modifier =
                    Modifier.weight(1f)

            )







            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween

            ) {



                Column {


                    Text(

                        text = "SERVER",

                        color =
                            Color.White.copy(alpha = 0.65f),

                        fontSize = 10.sp

                    )


                    Text(

                        text = server,

                        color = Color.White,

                        fontSize = 14.sp

                    )


                }






                if(mode != UserMode.SIMPLE) {


                    Column(

                        horizontalAlignment =
                            Alignment.End

                    ) {


                        Text(

                            text = "PING",

                            color =
                                Color.White.copy(alpha = 0.65f),

                            fontSize = 10.sp

                        )


                        Text(

                            text = "42 ms",

                            color = Color.White,

                            fontSize = 14.sp

                        )


                    }


                }



            }



        }


    }


}