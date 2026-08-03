package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.app.presentation.home.UserMode



@Composable
fun UserProfileCard(

    username: String = "Дмитрий",

    subscription: String = "Premium until 02.08.2027",

    mode: UserMode,

    onMenuClick: () -> Unit = {},

    onModeClick: () -> Unit = {}

) {


    Box(

        modifier = Modifier

            .fillMaxWidth()

            .height(76.dp)

            .clip(

                RoundedCornerShape(22.dp)

            )

            .background(

                Color.White

            )

            .padding(

                horizontal = 16.dp

            )

    ) {



        Row(

            modifier = Modifier.fillMaxSize(),

            verticalAlignment = Alignment.CenterVertically

        ) {



            Box(

                modifier = Modifier

                    .size(42.dp)

                    .clip(CircleShape)

                    .background(

                        Color(0xFF2563EB)

                    ),

                contentAlignment = Alignment.Center

            ) {

                Text(

                    text = username
                        .first()
                        .uppercase(),

                    color = Color.White,

                    fontSize = 17.sp

                )

            }




            Spacer(

                modifier = Modifier.width(12.dp)

            )




            Column(

                verticalArrangement = Arrangement.Center

            ) {


                Text(

                    text = username,

                    color = Color(0xFF111827),

                    fontSize = 15.sp

                )


                Text(

                    text = subscription,

                    color = Color(0xFF6B7280),

                    fontSize = 11.sp

                )

            }



        }




        Row(

            modifier = Modifier

                .align(Alignment.CenterEnd)

                .padding(end = 34.dp)


                .clip(

                    RoundedCornerShape(50)

                )

                .background(

                    modeColor(mode)

                )

                .clickable {

                    onModeClick()

                }

                .padding(

                    horizontal = 10.dp,

                    vertical = 5.dp

                ),


            verticalAlignment = Alignment.CenterVertically

        ) {


            Text(

                text = mode.name,

                color = Color.White,

                fontSize = 11.sp

            )


        }




        Text(

            text = "...",

            color = Color(0xFF111827),

            fontSize = 22.sp,

            modifier = Modifier

                .align(

                    Alignment.TopEnd

                )

                .clickable {

                    onMenuClick()

                }

        )



    }

}



private fun modeColor(

    mode: UserMode

): Color {


    return when(mode) {


        UserMode.SIMPLE ->

            Color(0xFF38BDF8)



        UserMode.ADVANCED ->

            Color(0xFF8B5CF6)



        UserMode.EXPERT ->

            Color(0xFFF59E0B)

    }

}