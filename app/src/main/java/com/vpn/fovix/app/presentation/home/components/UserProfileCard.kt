package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
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



@Composable
fun UserProfileCard(

    username: String = "Дмитрий",

    subscription: String = "Premium until 02.08.2027",

    plan: String = "PREMIUM",

    onMenuClick: () -> Unit = {}

) {



    Row(

        modifier = Modifier

            .fillMaxWidth()

            .clip(

                RoundedCornerShape(26.dp)

            )

            .background(

                Color(0xFF151B24)

            )

            .padding(

                horizontal = 18.dp,

                vertical = 16.dp

            ),


        verticalAlignment = Alignment.CenterVertically

    ) {



        Box(

            modifier = Modifier

                .size(52.dp)

                .clip(CircleShape)

                .background(

                    Color(0xFF2563EB)

                ),


            contentAlignment = Alignment.Center

        ){

            Text(

                text = username

                    .first()

                    .uppercase(),


                color = Color.White,

                fontSize = 22.sp

            )

        }





        Spacer(

            modifier = Modifier.width(14.dp)

        )





        Column(

            modifier = Modifier.weight(1f)

        ){



            Text(

                text = username,

                color = Color.White,

                fontSize = 17.sp

            )



            Spacer(

                modifier = Modifier.height(4.dp)

            )



            Text(

                text = subscription,

                color = Color(0xFF9CA3AF),

                fontSize = 12.sp

            )


        }





        Column(

            horizontalAlignment = Alignment.End

        ){



            Text(

                text = "...",

                color = Color.White,

                fontSize = 24.sp

            )



            Spacer(

                modifier = Modifier.height(6.dp)

            )



            Text(

                text = plan,

                color = Color(0xFF38BDF8),

                fontSize = 13.sp

            )


        }


    }


}