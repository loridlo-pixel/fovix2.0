package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard
import com.vpn.fovix.app.presentation.home.components.ProtectionScoreCard
import com.vpn.fovix.app.presentation.home.components.NetworkHealthCard
import com.vpn.fovix.app.presentation.home.components.UserProfileCard


@Composable
fun HomeScreen() {


    val accent = Color(0xFF00E5FF)


    Surface(

        modifier = Modifier
            .fillMaxSize()
            .background(accent),

        color = accent

    ) {


        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 24.dp
                ),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {



            UserProfileCard(
                username = "FOVIX USER",
                plan = "PREMIUM"
            )




            VyryxCoreCard(

                connected = false,

                onClick = {

                }

            )





            ProtectionScoreCard(

                score = 98

            )





            NetworkHealthCard(

                latency = 42,

                speed = 186

            )



        }

    }

}