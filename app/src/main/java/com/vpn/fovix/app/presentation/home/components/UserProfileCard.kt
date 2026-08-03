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


    mode: UserMode = UserMode.SIMPLE,


    onModeClick: () -> Unit = {},


    onProfileClick: () -> Unit = {}


) {



    Row(


        modifier = Modifier

            .fillMaxWidth()

            .height(46.dp)

            .clip(

                RoundedCornerShape(16.dp)

            )

            .background(

                Color.White

            )

            .padding(

                horizontal = 12.dp

            ),



        verticalAlignment = Alignment.CenterVertically


    ) {



        Box(


            modifier = Modifier

                .size(29.dp)

                .clip(

                    CircleShape

                )

                .background(

                    Color(0xFF2563EB)

                )

                .clickable {

                    onProfileClick()

                },



            contentAlignment = Alignment.Center


        ) {



            Text(


                text = username

                    .first()

                    .uppercase(),



                color = Color.White,


                fontSize = 13.sp


            )


        }






        Spacer(


            modifier = Modifier.width(10.dp)


        )







        Column(


            modifier = Modifier

                .weight(1f)

                .clickable {

                    onProfileClick()

                },



            verticalArrangement = Arrangement.Center


        ) {



            Text(


                text = username,


                color = Color(0xFF111827),


                fontSize = 13.sp,


                lineHeight = 14.sp


            )






            Text(


                text = subscription,


                color = Color(0xFF6B7280),


                fontSize = 9.sp,


                lineHeight = 11.sp


            )


        }








        Text(


            text = when(mode) {


                UserMode.SIMPLE -> "SIMPLE"


                UserMode.ADVANCED -> "ADVANCED"


                UserMode.EXPERT -> "EXPERT • PRO"


            },



            color = when(mode) {


                UserMode.EXPERT -> Color(0xFF7C3AED)


                else -> Color(0xFF0284C7)


            },



            fontSize = 10.sp,



            modifier = Modifier

                .clickable {

                    onModeClick()

                }


        )


    }


}