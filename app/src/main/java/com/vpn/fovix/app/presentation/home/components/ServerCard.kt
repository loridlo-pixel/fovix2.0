package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun ServerCard(

    server: String,

    onClick: () -> Unit

) {


    Column(

        modifier = Modifier

            .fillMaxWidth()

            .clip(

                RoundedCornerShape(24.dp)

            )

            .background(

                Brush.linearGradient(

                    colors = listOf(

                        Color(0x33FFFFFF),

                        Color(0x22151827)

                    )

                )

            )

            .border(

                width = 1.dp,

                brush = Brush.linearGradient(

                    colors = listOf(

                        Color(0x66A855F7),

                        Color(0x4438BDF8)

                    )

                ),

                shape = RoundedCornerShape(24.dp)

            )

            .clickable {

                onClick()

            }

            .padding(20.dp)


    ) {



        Text(

            text = "CONNECTED SERVER",

            color = Color(0xFFA855F7),

            fontSize = 12.sp

        )



        Spacer(

            modifier = Modifier.height(8.dp)

        )



        Text(

            text = "🌐 $server",

            color = Color.White,

            fontSize = 19.sp

        )



        Spacer(

            modifier = Modifier.height(12.dp)

        )



        Text(

            text = "● Stable     42 ms     98 Mbps",

            color = Color(0xFF34D399),

            fontSize = 14.sp

        )



    }


}