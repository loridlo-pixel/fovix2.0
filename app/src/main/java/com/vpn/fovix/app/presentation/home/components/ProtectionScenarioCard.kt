package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class ProtectionScenario {

    EVERYDAY,
    TRAVEL,
    STREAMING,
    GAMING,
    MAX_SECURITY

}



@Composable
fun ProtectionScenarioCard(

    scenario: ProtectionScenario,

    onClick: () -> Unit

) {


    val title = when(scenario) {


        ProtectionScenario.EVERYDAY ->

            "🏠 Everyday"


        ProtectionScenario.TRAVEL ->

            "🌎 Travel"


        ProtectionScenario.STREAMING ->

            "🎬 Streaming"


        ProtectionScenario.GAMING ->

            "🎮 Gaming"


        ProtectionScenario.MAX_SECURITY ->

            "🔒 Maximum Security"


    }



    val description = when(scenario) {


        ProtectionScenario.EVERYDAY ->

            "Balanced protection"


        ProtectionScenario.TRAVEL ->

            "Public WiFi protection"


        ProtectionScenario.STREAMING ->

            "Optimized speed"


        ProtectionScenario.GAMING ->

            "Low latency mode"


        ProtectionScenario.MAX_SECURITY ->

            "Full protection"

    }




    Column(

        modifier = Modifier

            .fillMaxWidth()

            .height(150.dp)

            .background(

                Color.White,

                RoundedCornerShape(26.dp)

            )

            .clickable {

                onClick()

            }

            .padding(20.dp)

    ) {



        Text(

            text = "Protection Scenario",

            color = Color(0xFF64748B),

            fontSize = 12.sp

        )



        Spacer(

            modifier = Modifier.height(12.dp)

        )



        Text(

            text = title,

            color = Color(0xFF111827),

            fontSize = 22.sp

        )



        Spacer(

            modifier = Modifier.height(6.dp)

        )



        Text(

            text = description,

            color = Color(0xFF64748B),

            fontSize = 13.sp

        )



        Spacer(

            modifier = Modifier.weight(1f)

        )




        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.End,

            verticalAlignment = Alignment.CenterVertically

        ) {



            Text(

                text = "Change scenario  →",

                color = Color(0xFF0284C7),

                fontSize = 13.sp

            )


        }



    }


}