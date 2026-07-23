package com.vpn.fovix.app.presentation.subscription.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp




@Composable
fun SubscriptionInputCard(


    value: String,


    onValueChange: (String) -> Unit


) {



    OutlinedTextField(


        value = value,


        onValueChange = onValueChange,



        modifier = Modifier

            .fillMaxWidth()

            .background(

                Color(0xFF111820),

                RoundedCornerShape(18.dp)

            ),



        placeholder = {


            androidx.compose.material3.Text(


                text = "Paste URL / Config",


                color = Color(0xFF7D8895)


            )


        },



        colors = OutlinedTextFieldDefaults.colors(


            focusedBorderColor = Color(0xFF00E5FF),


            unfocusedBorderColor = Color(0xFF263342),


            focusedTextColor = Color.White,


            unfocusedTextColor = Color.White,


            cursorColor = Color(0xFF00E5FF)


        ),



        shape = RoundedCornerShape(18.dp),



        maxLines = 4


    )


}