package com.vpn.fovix.app.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ServerCard(
    server: String
){

    Column(

        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFF141B22),
                    RoundedCornerShape(20.dp)
                )
                .padding(18.dp)

    ){

        Text(
            text = server,
            color = Color.White,
            fontSize = 17.sp
        )


        Spacer(
            modifier =
                Modifier.height(6.dp)
        )


        Text(
            text = "42 ms • 98 Mbps",
            color = Color(0xFF9AA7B5),
            fontSize = 13.sp
        )

    }

}