package com.vpn.fovix.app.presentation.navigation


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

import androidx.compose.ui.graphics.Brush
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
        label = "core_bottom"
    )


    val pulse by transition.animateFloat(

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





    val coreColor = when(connectionStatus) {


        ConnectionStatus.CONNECTED ->

            Color(0xFF22C55E)



        ConnectionStatus.CONNECTING ->

            Color(0xFFF59E0B)



        else ->

            Color(0xFF6366F1)


    }





    Column(

        modifier = Modifier

            .fillMaxWidth()

            .background(

                Color.Black

            )

    ) {



        Box(

            modifier = Modifier

                .fillMaxWidth()

                .padding(

                    horizontal = 12.dp,

                    vertical = 12.dp

                )

                .height(74.dp)

                .background(

                    brush = Brush.linearGradient(

                        colors = listOf(

                            Color(0xFF111827),

                            Color(0xFF1F2937)

                        )

                    ),

                    shape = RoundedCornerShape(28.dp)

                ),



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

                            if(connectionStatus == ConnectionStatus.CONNECTING)

                                (56 * pulse).dp

                            else

                                56.dp

                        )

                        .background(

                            coreColor,

                            RoundedCornerShape(20.dp)

                        )

                        .clickable {


                            onTabSelected(

                                FovixTab.HOME

                            )


                        },

                    contentAlignment = Alignment.Center


                ) {



                    Text(

                        text = "CORE",


                        color = Color.White,


                        fontSize = 13.sp


                    )


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

            Color.White

        else

            Color.White.copy(

                alpha = 0.55f

            ),


        fontSize = 12.sp,


        modifier = Modifier

            .clickable {

                onClick()

            }

            .padding(8.dp)


    )


}