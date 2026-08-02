package com.vpn.fovix.app.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NetworkHealthCard(

    latency: Int = 24,

    speed: Int = 98,

modifier: Modifier = Modifier

) {


    Card(

        modifier = Modifier

            

            .height(110.dp),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(

            containerColor = Color.White

        ),

        elevation = CardDefaults.cardElevation(

            defaultElevation = 3.dp

        )

    ) {


        Column(

            modifier = Modifier.padding(18.dp)

        ) {


            Text(

                text = "Network",

                fontSize = 12.sp,

                color = Color(0xFF64748B)

            )


            Spacer(

                modifier = Modifier.height(8.dp)

            )


            Text(

                text = "Excellent",

                fontSize = 22.sp,

                color = Color(0xFF111827)

            )


            Text(

                text = "$latency ms  •  ${speed}Mbps",

                fontSize = 12.sp,

                color = Color(0xFF16A34A)

            )

        }

    }

}