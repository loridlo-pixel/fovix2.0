package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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


    Card(

        modifier = Modifier

            .fillMaxWidth(),


        colors = CardDefaults.cardColors(

            containerColor = Color.White

        ),


        elevation = CardDefaults.cardElevation(

            defaultElevation = 3.dp

        )


    ) {



        Column(

            modifier = Modifier

                .padding(20.dp)

        ) {



            Row(

                modifier = Modifier

                    .fillMaxWidth(),


                horizontalArrangement = Arrangement.SpaceBetween,


                verticalAlignment = Alignment.CenterVertically

            ) {



                Column {


                    Text(

                        text = vpnName,


                        fontSize = 18.sp,


                        color = Color(0xFF111827)

                    )



                    Spacer(

                        modifier = Modifier.height(4.dp)

                    )



                    Text(

                        text = "Premium VPN Plan",


                        fontSize = 13.sp,


                        color = Color(0xFF6366F1)

                    )


                }





                Text(

                    text = "ACTIVE",


                    fontSize = 12.sp,


                    color = Color(0xFF16A34A)

                )


            }





            Spacer(

                modifier = Modifier.height(16.dp)

            )





            Text(

                text = "🌐 $server",


                fontSize = 16.sp,


                color = Color(0xFF111827)

            )





            Spacer(

                modifier = Modifier.height(12.dp)

            )





            Row(

                modifier = Modifier

                    .fillMaxWidth(),


                horizontalArrangement = Arrangement.SpaceBetween

            ) {



                Text(

                    text = "$ping ms",


                    color = Color(0xFF6B7280),


                    fontSize = 14.sp

                )



                Text(

                    text = "$speed Mbps",


                    color = Color(0xFF6B7280),


                    fontSize = 14.sp

                )



                Text(

                    text = "$daysLeft days",


                    color = Color(0xFF6B7280),


                    fontSize = 14.sp

                )


            }


        }


    }


}