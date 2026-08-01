package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

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

            .clickable {

                onClick()

            }

            .background(

                Color(0xFF151D26),

                RoundedCornerShape(22.dp)

            )

            .padding(20.dp)

    ) {


        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {


            Text(

                text = "🌐 $server",

                color = Color.White,

                fontSize = 18.sp

            )


            Text(

                text = "CHANGE",

                color = Color(0xFF00D9FF),

                fontSize = 12.sp

            )


        }



        Spacer(

            modifier = Modifier.height(12.dp)

        )



        Text(

            text = "VLESS • Reality",

            color = Color(0xFF8B98A8),

            fontSize = 13.sp

        )



        Spacer(

            modifier = Modifier.height(14.dp)

        )



        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween

        ) {


            Text(

                text = "Ping 42 ms",

                color = Color(0xFF9AA7B5),

                fontSize = 14.sp

            )



            Text(

                text = "↓ 98 Mbps",

                color = Color.White,

                fontSize = 14.sp

            )


        }


    }


}