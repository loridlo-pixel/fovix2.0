package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.app.R



enum class ProtectionScenario {

    EVERYDAY,

    PUBLIC_WIFI,

    TRAVEL,

    STREAMING,

    GAMING,

    EXPERT

}



private data class ScenarioItem(

    val type: ProtectionScenario,

    val icon: String,

    val title: Int,

    val description: Int

)



@Composable
fun ProtectionScenarioCard(

    scenario: ProtectionScenario,

    onClick: (ProtectionScenario) -> Unit

) {


    val scenarios = listOf(


        ScenarioItem(

            ProtectionScenario.EVERYDAY,

            "⌂",

            R.string.scenario_everyday,

            R.string.scenario_everyday_desc

        ),



        ScenarioItem(

            ProtectionScenario.PUBLIC_WIFI,

            "◌",

            R.string.scenario_wifi,

            R.string.scenario_wifi_desc

        ),



        ScenarioItem(

            ProtectionScenario.TRAVEL,

            "✈",

            R.string.scenario_travel,

            R.string.scenario_travel_desc

        ),



        ScenarioItem(

            ProtectionScenario.STREAMING,

            "▶",

            R.string.scenario_streaming,

            R.string.scenario_streaming_desc

        ),



        ScenarioItem(

            ProtectionScenario.GAMING,

            "◇",

            R.string.scenario_gaming,

            R.string.scenario_gaming_desc

        ),



        ScenarioItem(

            ProtectionScenario.EXPERT,

            "⬢",

            R.string.scenario_expert,

            R.string.scenario_expert_desc

        )

    )




    val selected = scenarios.first {

        it.type == scenario

    }




    Column(

        modifier = Modifier

            .fillMaxWidth()

            .height(132.dp)

            .shadow(

                elevation = 8.dp,

                shape = RoundedCornerShape(24.dp),

                ambientColor = Color.Black.copy(alpha = 0.08f),

                spotColor = Color.Black.copy(alpha = 0.10f)

            )

            .background(

                Color.White,

                RoundedCornerShape(24.dp)

            )

            .border(

                width = 1.dp,

                color = Color(0xFFE6EAF0),

                shape = RoundedCornerShape(24.dp)

            )

            .padding(

                horizontal = 12.dp,

                vertical = 10.dp

            )

    ) {



        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {



            scenarios.forEach { item ->



                val active = item.type == scenario




                Column(

                    modifier = Modifier

                        .clickable {

                            onClick(item.type)

                        },

                    horizontalAlignment = Alignment.CenterHorizontally

                ) {



                    Text(

                        text = item.icon,

                        fontSize = 19.sp,

                        color = if(active)

                            Color(0xFF0284C7)

                        else

                            Color(0xFF64748B)

                    )





                    Text(

                        text = stringResource(item.title),

                        fontSize = 8.sp,

                        color = if(active)

                            Color(0xFF0284C7)

                        else

                            Color(0xFF94A3B8)

                    )





                    Box(

                        modifier = Modifier

                            .padding(top = 4.dp)

                            .size(

                                width = 4.dp,

                                height = 13.dp

                            )

                            .background(

                                color = if(active)

                                    Color(0xFF38BDF8)

                                else

                                    Color(0xFFE2E8F0),

                                shape = RoundedCornerShape(10.dp)

                            )

                    )

                }

            }


        }





        Spacer(

            modifier = Modifier.height(9.dp)

        )





        Text(

            text = stringResource(

                selected.description

            ),

            color = Color(0xFF94A3B8),

            fontSize = 10.sp

        )


    }


}