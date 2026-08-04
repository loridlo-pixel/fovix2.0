package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

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

    PUBLIC_WIFI,

    TRAVEL,

    STREAMING,

    GAMING,

    EXPERT

}



@Composable
fun ProtectionScenarioCard(

    scenario: ProtectionScenario,

    onClick: (ProtectionScenario) -> Unit

) {


    val scenarios = listOf(

        ScenarioItem(
            ProtectionScenario.EVERYDAY,
            "🏠",
            "Home"
        ),

        ScenarioItem(
            ProtectionScenario.PUBLIC_WIFI,
            "☕",
            "WiFi"
        ),

        ScenarioItem(
            ProtectionScenario.TRAVEL,
            "✈",
            "Travel"
        ),

        ScenarioItem(
            ProtectionScenario.STREAMING,
            "▶",
            "Stream"
        ),

        ScenarioItem(
            ProtectionScenario.GAMING,
            "🎮",
            "Game"
        ),

        ScenarioItem(
            ProtectionScenario.EXPERT,
            "⚡",
            "Expert"
        )

    )



    Column(

        modifier = Modifier

            .fillMaxWidth()

            .height(112.dp)

            .background(

                Color.White,

                RoundedCornerShape(24.dp)

            )

            .padding(

                horizontal = 12.dp,

                vertical = 10.dp

            )

    ) {


        Text(

            text = "Protection Scenario",

            color = Color(0xFF64748B),

            fontSize = 11.sp

        )



        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {


            scenarios.forEach { item ->


                val selected = item.type == scenario



                Column(

                    modifier = Modifier

                        .clickable {

                            onClick(item.type)

                        },

                    horizontalAlignment = Alignment.CenterHorizontally

                ) {


                    Text(

                        text = item.icon,

                        fontSize = 18.sp

                    )



                    Text(

                        text = item.name,

                        fontSize = 9.sp,

                        color = if(selected)

                            Color(0xFF0284C7)

                        else

                            Color(0xFF94A3B8)

                    )



                    androidx.compose.foundation.layout.Box(

                        modifier = Modifier

                            .padding(top = 3.dp)

                            .size(10.dp)

                            .background(

                                if(selected)

                                    Color(0xFF38BDF8)

                                else

                                    Color(0xFFE2E8F0),

                                RoundedCornerShape(50)

                            )

                    )

                }

            }

        }

    }

}



private data class ScenarioItem(

    val type: ProtectionScenario,

    val icon: String,

    val name: String

)