package com.vpn.fovix.app.presentation.navigation


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun FovixBottomBar(

    selected: FovixTab,

    connectionStatus: ConnectionStatus,

    onTabSelected: (FovixTab) -> Unit

) {


    val transition = rememberInfiniteTransition(
        label = "core_pulse"
    )


    val pulse by transition.animateFloat(

        initialValue = 1f,

        targetValue = 1.12f,

        animationSpec = infiniteRepeatable(

            animation = tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing
            ),

            repeatMode = RepeatMode.Reverse

        ),

        label = "pulse"

    )



    val coreColor = when(connectionStatus) {


        ConnectionStatus.CONNECTED ->

            Color(0xFF22C55E)



        ConnectionStatus.CONNECTING ->

            Color(0xFFF59E0B)



        else ->

            Color(0xFF2563EB)

    }





    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )

    ) {


        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .clip(
                    RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 8.dp),

            contentAlignment = Alignment.Center

        ) {



            Row(

                modifier = Modifier
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceAround,

                verticalAlignment = Alignment.CenterVertically

            ) {



                BottomItem(
                    text = "Home",
                    active = selected == FovixTab.HOME
                ) {

                    onTabSelected(
                        FovixTab.HOME
                    )

                }




                BottomItem(
                    text = "Servers",
                    active = selected == FovixTab.SERVERS
                ) {

                    onTabSelected(
                        FovixTab.SERVERS
                    )

                }




                Box(

                    modifier = Modifier

                        .size(

                            if(
                                connectionStatus ==
                                ConnectionStatus.CONNECTING
                            )

                                (58 * pulse).dp

                            else

                                58.dp

                        )

                        .clip(
                            CircleShape
                        )

                        .clickable {

                            onTabSelected(
                                FovixTab.HOME
                            )

                        },

                    contentAlignment = Alignment.Center

                ) {


                    Box(

                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape)

                    ) {


                        Text(

                            text = "CORE",

                            color = Color.White,

                            fontSize = 12.sp,

                            modifier = Modifier
                                .align(
                                    Alignment.Center
                                )

                        )


                    }


                }





                BottomItem(
                    text = "Doctor",
                    active = selected == FovixTab.DOCTOR
                ) {

                    onTabSelected(
                        FovixTab.DOCTOR
                    )

                }





                BottomItem(
                    text = "Settings",
                    active = selected == FovixTab.SETTINGS
                ) {

                    onTabSelected(
                        FovixTab.SETTINGS
                    )

                }



            }


        }


    }


}





@Composable
private fun BottomItem(

    text: String,

    active: Boolean,

    onClick: () -> Unit

) {


    Text(

        text = text,

        color = if(active)

            Color(0xFF111827)

        else

            Color(0xFF64748B),


        fontSize = 12.sp,


        modifier = Modifier

            .clickable {

                onClick()

            }

            .padding(8.dp)

    )


}