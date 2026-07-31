package com.vpn.fovix.app.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FovixHeader(
    modifier: Modifier = Modifier
){

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.SpaceBetween,
        verticalAlignment =
            Alignment.CenterVertically
    ){

        Text(
            text = "FOVIX",
            color = Color.White,
            fontSize = 28.sp
        )


        NetworkIndicator(
            level = 4
        )

    }
}