package com.vpn.fovix.app.presentation.subscription.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.Box



@Composable
fun SubscriptionImportButton(


    enabled: Boolean,


    onClick: () -> Unit


) {



    Box(


        modifier = Modifier

            .fillMaxWidth()

            .background(


                brush = Brush.linearGradient(


                    colors = if(enabled)


                        listOf(

                            Color(0xFF00E5FF),

                            Color(0xFF7C4DFF)

                        )


                    else


                        listOf(

                            Color(0xFF263342),

                            Color(0xFF263342)

                        )


                ),


                shape = RoundedCornerShape(20.dp)


            )


            .clickable(

                enabled = enabled

            ) {


                onClick()


            }


            .padding(18.dp),



        contentAlignment = Alignment.Center


    ) {



        Text(


            text = "IMPORT",


            color = if(enabled)


                Color.Black


            else


                Color(0xFF7D8895),



            fontSize = 16.sp



        )



    }



}