package com.vpn.fovix.app.presentation.home.components


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.vpn.fovix.domain.subscription.VpnSubscription
import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun VyryxCoreCard(

    mode: UserMode,

    status: ConnectionStatus,

    server: String,

    subscription: VpnSubscription?,

    onClick: () -> Unit

) {


    val hasSubscription =
        subscription != null



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

        if (!hasSubscription) {

            "Setup required"

        } else {

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

        }




    val subtitle =

        if (!hasSubscription) {

            "Add VPN provider subscription"

        } else {

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

        }





    val cardBrush =

        if(hasSubscription)

            Brush.linearGradient(

                colors = listOf(

                    Color(0xFF38BDF8),

                    Color(0xFF0284C7)

                )

            )

        else

            Brush.linearGradient(

                colors = listOf(

                    Color(0xFFE5E7EB),

                    Color(0xFFD1D5DB)

                )

            )





    val primaryText =

        if(hasSubscription)

            Color.White

        else

            Color(0xFF334155)



    val secondaryText =

        if(hasSubscription)

            Color.White.copy(alpha = 0.75f)

        else

            Color(0xFF64748B)





    val powerColor =

        if(!hasSubscription)

            Color(0xFF94A3B8)

        else

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

                    shape = RoundedCornerShape(28.dp)

                )

                .background(

                    brush = cardBrush,

                    shape = RoundedCornerShape(28.dp)

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

                        color = secondaryText,

                        fontSize = 12.sp

                    )


                    Spacer(
                        Modifier.height(5.dp)
                    )


                    Text(

                        text = stateText,

                        color = primaryText,

                        fontSize = 24.sp

                    )


                }




                Box(

                    modifier =
                        Modifier

                            .size(54.dp)

                            .scale(

                                if(
                                    status ==
                                    ConnectionStatus.CONNECTING
                                )

                                    pulse.value

                                else

                                    1f

                            )

                            .background(

                                if(hasSubscription)

                                    Color.White.copy(
                                        alpha = 0.18f
                                    )

                                else

                                    Color.White.copy(
                                        alpha = 0.55f
                                    ),

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

                Modifier.height(18.dp)

            )




            Text(

                text = subtitle,

                color = secondaryText,

                fontSize = 13.sp

            )





            Spacer(

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

                        color = secondaryText,

                        fontSize = 10.sp

                    )


                    Text(

                        text =

                            if(hasSubscription)

                                server

                            else

                                "No server",

                        color = primaryText,

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

                            color = secondaryText,

                            fontSize = 10.sp

                        )


                        Text(

                            text =

                                if(hasSubscription)

                                    "42 ms"

                                else

                                    "--",

                            color = primaryText,

                            fontSize = 14.sp

                        )


                    }

                }


            }


        }


    }


}