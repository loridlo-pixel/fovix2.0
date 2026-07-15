package com.vpn.fovix.app.presentation.home


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ConnectionOrb(

    connected: Boolean,

    onClick: () -> Unit

) {


    val gradient =

        Brush.radialGradient(

            colors = listOf(

                Color.Cyan,

                Color.Blue

            )

        )



    Box(

        modifier = Modifier

            .size(180.dp)

            .background(

                brush = gradient,

                shape = CircleShape

            )

            .clickable {


                Log.e(
                    "FOVIX_TEST",
                    "1 ORB CLICK"
                )


                onClick()


                Log.e(
                    "FOVIX_TEST",
                    "2 CALLBACK FINISHED"
                )


            },


        contentAlignment = Alignment.Center

    ) {


        Text(

            text = if (connected)
                "ON"
            else
                "OFF",

            color = Color.White

        )

    }

}
