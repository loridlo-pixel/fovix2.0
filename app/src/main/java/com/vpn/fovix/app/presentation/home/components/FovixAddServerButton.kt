package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

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
fun FovixAddServerButton(

    onClick: () -> Unit

) {



    Row(


        modifier = Modifier

            .background(

                brush = Brush.linearGradient(

                    colors = listOf(

                        Color(0xFF111820),

                        Color(0xFF182532)

                    )

                ),

                shape = RoundedCornerShape(18.dp)

            )

            .clickable {

                onClick()

            }

            .padding(

                horizontal = 28.dp,

                vertical = 16.dp

            ),



        verticalAlignment = Alignment.CenterVertically,


        horizontalArrangement = Arrangement.Center


    ) {



        Text(


            text = "+",


            color = Color(0xFF00E5FF),


            fontSize = 30.sp



        )



        Spacer(

            modifier = Modifier.size(12.dp)

        )




        Text(


            text = "ADD SERVER",


            color = Color.White,


            fontSize = 15.sp



        )



    }


}