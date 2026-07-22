package com.vpn.fovix.app.presentation.home


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun ConnectionOrb(

    connected: Boolean,

    onClick: () -> Unit

) {


    val infiniteTransition =
        rememberInfiniteTransition(
            label = "connection"
        )


    val pulse by infiniteTransition.animateFloat(

        initialValue = 0.95f,

        targetValue = 1.08f,

        animationSpec =
            infiniteRepeatable(

                animation =
                    tween(

                        durationMillis = 1800,

                        easing = LinearEasing

                    ),

                repeatMode =
                    RepeatMode.Reverse

            ),

        label = "pulse"

    )





    Box(

        modifier =
            Modifier
                .size(220.dp)
                .clickable {

                    onClick()

                },

        contentAlignment =
            Alignment.Center

    ) {



        /*
            Outer reactor glow
        */

        Box(

            modifier =
                Modifier
                    .size(210.dp)
                    .scale(

                        if(connected)

                            pulse

                        else

                            1f

                    )
                    .background(

                        brush =
                            Brush.radialGradient(

                                colors =

                                    if(connected)

                                        listOf(

                                            Color(0xFF00E5FF),

                                            Color(0xFF7C4DFF),

                                            Color.Transparent

                                        )

                                    else

                                        listOf(

                                            Color(0xFF39414D),

                                            Color.Transparent

                                        )

                            ),

                        shape =
                            CircleShape

                    )

        )





        /*
            Main core
        */

        Box(

            modifier =
                Modifier
                    .size(160.dp)
                    .shadow(

                        elevation = 20.dp,

                        shape = CircleShape

                    )
                    .background(

                        brush =
                            Brush.linearGradient(

                                colors = listOf(

                                    Color(0xFF151B22),

                                    Color(0xFF27313D)

                                )

                            ),

                        shape =
                            CircleShape

                    ),

            contentAlignment =
                Alignment.Center

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


                    fontSize = 52.sp

                )



                Spacer(

                    modifier =
                        Modifier.height(8.dp)

                )



                Text(

                    text =

                        if(connected)

                            "CONNECTED"

                        else

                            "CONNECT",


                    color = Color.White,

                    fontSize = 15.sp,

                    fontWeight = FontWeight.Bold

                )



                Spacer(

                    modifier =
                        Modifier.height(4.dp)

                )



                Text(

                    text =

                        if(connected)

                            "Secure"

                        else

                            "Tap to protect",


                    color =
                        Color.Gray,

                    fontSize = 11.sp

                )


            }


        }


    }


}