package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

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

            .height(62.dp)

            .shadow(

                elevation = 8.dp,

                shape = RoundedCornerShape(22.dp),

                ambientColor = Color.Black.copy(alpha = 0.08f),

                spotColor = Color.Black.copy(alpha = 0.10f)

            )

            .background(

                Color.White,

                RoundedCornerShape(22.dp)

            )

            .border(

                width = 1.dp,

                color = Color(0xFFE6EAF0),

                shape = RoundedCornerShape(22.dp)

            )

            .padding(

                horizontal = 14.dp

            ),


        verticalAlignment = Alignment.CenterVertically

    ) {



        Box(

            modifier = Modifier

                .size(34.dp)

                .background(

                    Color(0xFF2563EB),

                    CircleShape

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

                fontSize = 14.sp

            )

        }





        Spacer(

            modifier = Modifier.width(10.dp)

        )







        Row(

            modifier = Modifier

                .weight(1f)

                .clickable {

                    onProfileClick()

                },


            verticalAlignment = Alignment.CenterVertically

        ) {



            Column(

                verticalArrangement = Arrangement.Center

            ) {



                Text(

                    text = username,

                    color = Color(0xFF111827),

                    fontSize = 14.sp,

                    lineHeight = 16.sp

                )





                Text(

                    text = subscription,

                    color = Color(0xFF94A3B8),

                    fontSize = 10.sp,

                    lineHeight = 12.sp

                )

            }

        }







        Box(

            modifier = Modifier

                .background(

                    when(mode) {


                        UserMode.SIMPLE ->

                            Color(0xFFE0F2FE)


                        UserMode.ADVANCED ->

                            Color(0xFFDCFCE7)


                        UserMode.EXPERT ->

                            Color(0xFFF3E8FF)

                    },

                    RoundedCornerShape(12.dp)

                )

                .clickable {

                    onModeClick()

                }

                .padding(

                    horizontal = 10.dp,

                    vertical = 6.dp

                ),


            contentAlignment = Alignment.Center

        ) {



            Text(

                text = when(mode) {


                    UserMode.SIMPLE ->

                        "SIMPLE"


                    UserMode.ADVANCED ->

                        "ADVANCED"


                    UserMode.EXPERT ->

                        "EXPERT"

                },


                color = when(mode) {


                    UserMode.SIMPLE ->

                        Color(0xFF0284C7)


                    UserMode.ADVANCED ->

                        Color(0xFF16A34A)


                    UserMode.EXPERT ->

                        Color(0xFF7C3AED)

                },


                fontSize = 10.sp

            )

        }

    }

}