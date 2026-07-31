package com.vpn.fovix.app.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun NetworkIndicator(
    level: Int
){

    Row(
        verticalAlignment =
            androidx.compose.ui.Alignment.Bottom
    ){

        repeat(4){ index ->

            Box(
                modifier =
                    Modifier
                        .padding(horizontal = 2.dp)
                        .width(5.dp)
                        .height(
                            (8 + index * 6).dp
                        )
                        .background(
                            if(index < level)
                                Color(0xFF00E5FF)
                            else
                                Color.DarkGray,
                            RoundedCornerShape(3.dp)
                        )
            )

        }

    }
}