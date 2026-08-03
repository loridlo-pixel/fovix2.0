package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import com.vpn.fovix.app.presentation.home.components.UserProfileCard
import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard
import com.vpn.fovix.app.presentation.home.components.ProtectionScoreCard
import com.vpn.fovix.app.presentation.home.components.NetworkHealthCard



@Composable
fun HomeScreen() {


    Column(

        modifier = Modifier

            .fillMaxSize()

            .padding(

                horizontal = 20.dp,

                vertical = 24.dp

            )

    ) {



        UserProfileCard()



        Spacer(

            modifier = Modifier.height(24.dp)

        )



        Text(

            text = "FOVIX CORE",

            color = Color.White,

            fontSize = 24.sp

        )



        Spacer(

            modifier = Modifier.height(16.dp)

        )



        VyryxCoreCard(

            connected = false,

            onClick = {

                // TODO start vpn

            }

        )



        Spacer(

            modifier = Modifier.height(16.dp)

        )



        ProtectionScoreCard(

            score = 98

        )



        Spacer(

            modifier = Modifier.height(16.dp)

        )



        NetworkHealthCard(

            latency = 42,

            speed = 186

        )


    }

}