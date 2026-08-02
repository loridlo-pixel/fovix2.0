package com.vpn.fovix.app.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun VyryxHeader() {


    Column(

        modifier = Modifier
            .padding(
                top = 24.dp
            )

    ) {


        Text(

            text = "VYRYX",

            color = Color(0xFF111827),

            fontSize = 34.sp

        )


        Text(

            text = "Personal Security",

            color = Color(0xFF64748B),

            fontSize = 14.sp

        )


    }


}