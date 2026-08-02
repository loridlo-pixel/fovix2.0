package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun ProtectionScoreCard(

    score: Int = 98

) {


    Card(

        modifier = Modifier

            .fillMaxWidth(),


        colors = CardDefaults.cardColors(

            containerColor = Color.White

        ),


        elevation = CardDefaults.cardElevation(

            defaultElevation = 2.dp

        )

    ) {



        Row(

            modifier = Modifier

                .fillMaxWidth()

                .padding(18.dp)

        ) {



            Column(

                modifier = Modifier.weight(1f)

            ) {



                Text(

                    text = "Protection Score",

                    fontSize = 13.sp,

                    color = Color(0xFF6B7280)

                )



                Text(

                    text = "$score%",

                    fontSize = 30.sp,

                    color = Color(0xFF111827)

                )



            }





            Text(

                text = when {

                    score >= 90 -> "Excellent"

                    score >= 70 -> "Good"

                    else -> "Attention"

                },


                color = when {

                    score >= 90 -> Color(0xFF16A34A)

                    score >= 70 -> Color(0xFFF59E0B)

                    else -> Color(0xFFEF4444)

                },


                fontSize = 14.sp

            )


        }


    }


}