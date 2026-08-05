package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun SubscriptionCard(

    vpnName: String = "VYRYX Premium",

    server: String = "Germany 🇩🇪",

    ping: Int = 42,

    speed: Int = 186,

    daysLeft: Int = 29,

    onRenewClick: () -> Unit = {}

) {



    Column(

        modifier = Modifier

            .fillMaxWidth()

            .shadow(

                elevation = 8.dp,

                shape = RoundedCornerShape(24.dp),

                ambientColor = Color.Black.copy(alpha = 0.08f),

                spotColor = Color.Black.copy(alpha = 0.10f)

            )

            .background(

                Color.White,

                RoundedCornerShape(24.dp)

            )

            .border(

                1.dp,

                Color(0xFFE6EAF0),

                RoundedCornerShape(24.dp)

            )

            .padding(20.dp)

    ) {



        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {



            Column {



                Text(

                    text = vpnName,

                    color = Color(0xFF111827),

                    fontSize = 18.sp

                )



                Spacer(

                    modifier = Modifier.height(4.dp)

                )



                Text(

                    text = "Premium Protection Plan",

                    color = Color(0xFF0284C7),

                    fontSize = 12.sp

                )

            }





            Text(

                text = "● ACTIVE",

                color = Color(0xFF16A34A),

                fontSize = 11.sp

            )

        }





        Spacer(

            modifier = Modifier.height(18.dp)

        )





        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween

        ) {



            Column {



                Text(

                    text = "Server",

                    color = Color(0xFF94A3B8),

                    fontSize = 11.sp

                )



                Text(

                    text = server,

                    color = Color(0xFF111827),

                    fontSize = 14.sp

                )

            }





            Column(

                horizontalAlignment = Alignment.End

            ) {



                Text(

                    text = "Renewal",

                    color = Color(0xFF94A3B8),

                    fontSize = 11.sp

                )



                Text(

                    text = "$daysLeft days",

                    color = Color(0xFF111827),

                    fontSize = 14.sp

                )

            }

        }





        Spacer(

            modifier = Modifier.height(16.dp)

        )





        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween

        ) {



            Text(

                text = "$ping ms",

                color = Color(0xFF64748B),

                fontSize = 13.sp

            )



            Text(

                text = "$speed Mbps",

                color = Color(0xFF64748B),

                fontSize = 13.sp

            )



            Text(

                text = "Manage →",

                color = Color(0xFF0284C7),

                fontSize = 13.sp

            )

        }

    }

}