package com.vpn.fovix.app.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ServerCard(

    server: String,

    onClick: () -> Unit

) {


    Card(

        modifier = Modifier

            .fillMaxWidth()

            .shadow(

                elevation = 6.dp,

                shape = RoundedCornerShape(26.dp)

            )

            .clickable {

                onClick()

            },

        shape = RoundedCornerShape(26.dp),

        colors = CardDefaults.cardColors(

            containerColor = Color.White

        )

    ) {


        Column(

            modifier = Modifier

                .padding(22.dp)

        ) {



            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically

            ) {


                Column {


                    Text(

                        text = "VYRYX Premium",

                        fontSize = 19.sp,

                        color = Color(0xFF111827)

                    )


                    Text(

                        text = "Personal Security Plan",

                        fontSize = 13.sp,

                        color = Color(0xFF64748B)

                    )

                }



                Box(

                    modifier = Modifier

                        .background(

                            Color(0xFFEDE9FE),

                            RoundedCornerShape(20.dp)

                        )

                        .padding(

                            horizontal = 12.dp,

                            vertical = 6.dp

                        )

                ) {


                    Text(

                        text = "ACTIVE",

                        fontSize = 11.sp,

                        color = Color(0xFF6D28D9)

                    )

                }

            }



            Spacer(

                modifier = Modifier.height(18.dp)

            )



            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween

            ) {


                InfoItem(

                    title = "Server",

                    value = server

                )


                InfoItem(

                    title = "Ping",

                    value = "24 ms"

                )


                InfoItem(

                    title = "Valid",

                    value = "12 Aug 27"

                )

            }



            Spacer(

                modifier = Modifier.height(16.dp)

            )



            Box(

                modifier = Modifier

                    .fillMaxWidth()

                    .height(42.dp)

                    .background(

                        Color(0xFFF5F3FF),

                        RoundedCornerShape(14.dp)

                    ),

                contentAlignment = Alignment.Center

            ) {


                Text(

                    text = "Update Subscription",

                    color = Color(0xFF6D28D9),

                    fontSize = 14.sp

                )

            }

        }

    }

}



@Composable
private fun InfoItem(

    title: String,

    value: String

) {


    Column {


        Text(

            text = title,

            fontSize = 11.sp,

            color = Color(0xFF94A3B8)

        )


        Spacer(

            modifier = Modifier.height(3.dp)

        )


        Text(

            text = value,

            fontSize = 14.sp,

            color = Color(0xFF111827)

        )

    }

}