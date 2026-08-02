package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun VyryxUserHeader(

    userName: String = "Dmitry",

    plan: String = "Premium Protection"

) {


    Row(

        modifier = Modifier

            .fillMaxWidth()

            .background(

                brush = Brush.linearGradient(

                    colors = listOf(

                        Color(0xFF4338CA),

                        Color(0xFF6366F1)

                    )

                ),

                shape = RoundedCornerShape(24.dp)

            )

            .padding(16.dp),


        verticalAlignment = Alignment.CenterVertically

    ) {



        androidx.compose.foundation.layout.Box(

            modifier = Modifier

                .background(

                    Color.White.copy(alpha = 0.2f),

                    RoundedCornerShape(50)

                )

                .padding(12.dp)

        ) {


            Text(

                text = userName.first().uppercase(),

                color = Color.White,

                fontSize = 20.sp

            )


        }





        Spacer(

            modifier = Modifier.width(12.dp)

        )





        Column {


            Text(

                text = userName,

                color = Color.White,

                fontSize = 17.sp

            )



            Text(

                text = plan,

                color = Color.White.copy(alpha = 0.8f),

                fontSize = 13.sp

            )


        }


    }


}